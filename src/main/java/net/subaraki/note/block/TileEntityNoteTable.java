package net.subaraki.note.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.subaraki.note.ItemNote;

public class TileEntityNoteTable extends TileEntity implements IInventory {

	ItemStack slots[] = new ItemStack[10];

	public TileEntityNoteTable() {
	}

	@Override
	public int getSizeInventory() {

		return 10;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {

		return slots[var1];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt) {

		if (this.slots[slot] != null)
		{
			ItemStack itemstack;

			if (this.slots[slot].stackSize <= amt)
			{
				itemstack = this.slots[slot];
				this.slots[slot] = null;
				this.markDirty();
				return itemstack;
			}
			else
			{
				itemstack = this.slots[slot].splitStack(amt);

				if (this.slots[slot].stackSize == 0)
				{
					this.slots[slot] = null;
				}

				this.markDirty();
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

	public void craft(){
		int amt = 0;

		ItemStack note = getStackInSlot(0);
		
		if(note != null){
			if(note.getItem() instanceof ItemNote){
				
				for(ItemStack stack : slots){
					if(stack != null){
						
					}
				}
			}
		}
	}

}
