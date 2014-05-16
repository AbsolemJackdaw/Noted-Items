package net.subaraki.note.block;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.subaraki.note.ItemNote;
import net.subaraki.note.Notes;
import net.subaraki.note.StackUtils;

public class TileEntityNoteTable extends TileEntity implements IInventory {

	ItemStack slots[] = new ItemStack[11];

	private boolean hasResult;

	public TileEntityNoteTable() {
	}

	@Override
	public int getSizeInventory() {

		return 11;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {

		return slots[var1];
	}


	@Override
	public ItemStack decrStackSize(int slot, int amt) {

		if(slot == 10){
			for(int i = 0; i < 10; i ++){
				slots[i] = null;
			}
			return slots[slot];
		}

		if (this.slots[slot] != null)
		{
			ItemStack itemstack;

			if (this.slots[slot].stackSize <= amt)
			{
				itemstack = this.slots[slot];
				this.slots[slot] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.slots[slot].splitStack(amt);

				if (this.slots[slot].stackSize == 0)
				{
					this.slots[slot] = null;
				}

				return itemstack;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {

		return slots[var1];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {

		slots[slot] = stack;

		if ((stack != null) && (stack.stackSize > getInventoryStackLimit()))
			stack.stackSize = getInventoryStackLimit();

	}

	public void addInventorySlotContents(int slot, ItemStack stack) {

		if (slots[slot] == null) {
			setInventorySlotContents(slot, stack);
			return;
		}

		if (slots[slot].stackSize + stack.stackSize <= 64) {
			slots[slot].stackSize += stack.stackSize;
			return;
		}
		else {
			int rest = slots[slot].stackSize + stack.stackSize - 64;
			stack.stackSize = rest;
			slots[slot].stackSize = 64;
			return;
		}
	}

	@Override
	public String getInventoryName() {

		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {

		return false;
	}

	@Override
	public int getInventoryStackLimit() {

		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {

		return true;
	}

	@Override
	public void openInventory() {


	}

	@Override
	public void closeInventory() {


	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {

		return true;
	}

	@Override
	public void updateEntity() {

		craft();

	}

	ArrayList<ItemStack> list = new ArrayList<ItemStack>();

	public void craft(){
		int amt = 0;

		ItemStack note = getStackInSlot(0);
		ItemStack reverse = getStackInSlot(11);
		
		if(note != null && note.stackSize == 1){
			if(note.getItem() instanceof ItemNote){

				ItemStack sample = null;
				for(int i = 1; i < 10; i++){
					if(getStackInSlot(i) != null){
						sample = getStackInSlot(i);
						break;
					}
				}

				int amount = 0;

				if(sample != null){

					if(note.hasTagCompound()){		
						if(!(sample.getItem() instanceof ItemNote))
							if(! new StackUtils().itemEqualsNoteNBT(sample, note.stackTagCompound)){
								setInventorySlotContents(10, null);
								hasResult = false;
								return;
							}
					}

					for(int i = 1; i < 10; i++){
						if(getStackInSlot(i) != null){
							if(!getStackInSlot(i).getItem().equals(sample.getItem())){
								sample = null;
								break;
							}

							if(getStackInSlot(i).getItemDamage() == sample.getItemDamage()){
								if(getStackInSlot(i).getItem() instanceof ItemNote && getStackInSlot(i).hasTagCompound()){
									if(new StackUtils().NBTAreEqual(sample.stackTagCompound, getStackInSlot(i).getTagCompound()))
										amount += getStackInSlot(i).getTagCompound().getInteger(StackUtils.AMT);
								}else
									amount += getStackInSlot(i).stackSize;
							}
						}
					}
					
					if(sample != null){
						NBTTagCompound tag = new StackUtils().createNotedNbt(
								amount, 
								sample.getDisplayName(), 
								sample.getItemDamage(),
								(short) Item.getIdFromItem(sample.getItem()));

						if(note.hasTagCompound())
							tag = new StackUtils().fuseNbt(note.getTagCompound(), tag);

						ItemStack noted = new ItemStack(Notes.note, 1,0);
						noted.stackTagCompound = tag;

						setInventorySlotContents(10, noted);
						hasResult = true;
					}else{
						setInventorySlotContents(10, null);
						hasResult = false;
					}
				}
				else{
					setInventorySlotContents(10, null);
					hasResult = false;
				}
			}
		}else{
			setInventorySlotContents(10, null);
			hasResult = false;
		}
	}
}
