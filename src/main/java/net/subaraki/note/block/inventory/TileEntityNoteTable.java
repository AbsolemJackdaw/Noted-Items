package net.subaraki.note.block.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.subaraki.note.Notes;
import net.subaraki.note.StackUtils;
import net.subaraki.note.item.ItemNote;

public class TileEntityNoteTable extends TileEntity implements IInventory{

	ItemStack slots[] = new ItemStack[12];

	@Override
	public int getSizeInventory() {
		return 12;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return slots[var1];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		markDirty();
		if(slot == 10){
			for(int i = 0; i < 10; i ++)
				slots[i] = null;
			return slots[slot];
		}

		ItemStack stack = getStackInSlot(slot);

		if (stack != null)
			if (stack.stackSize <= amt)
				setInventorySlotContents(slot, null);
			else {
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0)
					setInventorySlotContents(slot, null);
			}
		return stack;
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
		markDirty();
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
		markDirty();
		craft();
	}

	public void craft(){
		int amt = 0;

		ItemStack note = getStackInSlot(0);
		ItemStack reverse = getStackInSlot(11);

		if((note != null) && (note.stackSize == 1) && (reverse == null)){
			if(note.getItem() instanceof ItemNote){

				ItemStack sample = null;
				for(int i = 1; i < 10; i++)
					if(getStackInSlot(i) != null){
						sample = getStackInSlot(i);
						break;
					}

				int amount = 0;

				if(sample != null){

					if(note.hasTagCompound())
						if(!(sample.getItem() instanceof ItemNote))
							if(! new StackUtils().itemEqualsNoteNBT(sample, note.stackTagCompound)){
								setInventorySlotContents(10, null);
								return;
							}

					for(int i = 1; i < 10; i++)
						if(getStackInSlot(i) != null){
							if(!getStackInSlot(i).getItem().equals(sample.getItem())){
								sample = null;
								break;
							}

							if(getStackInSlot(i).getItemDamage() == sample.getItemDamage())
								if((getStackInSlot(i).getItem() instanceof ItemNote) && getStackInSlot(i).hasTagCompound()){
									if(new StackUtils().NBTAreEqual(sample.stackTagCompound, getStackInSlot(i).getTagCompound()))
										amount += getStackInSlot(i).getTagCompound().getInteger(StackUtils.AMT);
								}else
									amount += getStackInSlot(i).stackSize;
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
					}else{
						setInventorySlotContents(10, null);
					}
				}
				else{
					setInventorySlotContents(10, null);
				}
			}
		}else if ((reverse != null) && (note == null) && (reverse.stackSize == 1)){
			if(reverse.hasTagCompound()){

				Item item = Item.getItemById(reverse.getTagCompound().getShort(StackUtils.ITM));
				int stackamt = reverse.getTagCompound().getInteger(StackUtils.AMT);

				ItemStack stub = new ItemStack(item);
				int max = stub.getMaxStackSize();

				int lenght = (stackamt/max)+1;

				int substractamt = 0;



				//size of all stacks included 32
				//size of a stack 64
				//size of all slots = 9
				//size of total stacks = 1
				//run code for all stacks
				//get size from allstacks

				for(int slot = 1; slot < 10; slot++ ){
					int index = slot-1;

					ItemStack st = new ItemStack(item);
					st.stackSize = Math.min(64, stackamt- (index*64));

					st.setItemDamage(reverse.getTagCompound().getInteger(StackUtils.DMG));
					
					if(st.stackSize <= 0)
						st = null;
					
					if(getStackInSlot(slot) == null && st != null){
						setInventorySlotContents(slot, st);
						substractamt += st.stackSize;
						reverse.getTagCompound().setInteger(StackUtils.AMT, stackamt - substractamt);
					}
				}

				if(reverse.getTagCompound().getInteger(StackUtils.AMT) <= 0)
					reverse = new ItemStack(Notes.note);

				setInventorySlotContents(11, reverse);
			}
		}else{
			setInventorySlotContents(10, null);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList tagList = nbt.getTagList("Inventory", 10);
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if ((slot >= 0) && (slot < slots.length))
				slots[slot] = ItemStack.loadItemStackFromNBT(tag);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < slots.length; i++) {
			ItemStack stack = slots[i];
			if (stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		nbt.setTag("Inventory", itemList);
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g()); 
	}
	
	
}
