package net.subaraki.note.block.inventory;

import net.minecraft.entity.player.EntityPlayer;
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

	ItemStack slots[] = new ItemStack[13];

	@Override
	public int getSizeInventory() {
		return 13;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return slots[var1];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt) {

		markDirty();

		if(slot == 10){
			ItemStack ink = slots[12];
			int allStacks = 0;
			for(int i = 0; i < 10; i ++){

				if( (i > 0) && (slots[i] != null))
					allStacks += 1;

				slots[i] = null;
			}
			ink.stackSize -= allStacks;
			if(ink.stackSize <= 0)
				ink = null;

			slots[12] = ink;
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
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (this.slots[slot] != null){

			ItemStack itemstack = this.slots[slot];
			this.slots[slot] = null;
			return itemstack;
		}
		else
			return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack){

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
		craft();
	}

	/**checks for need items and places the resulting note in the result stack*/
	public void craft(){

		ItemStack note = getStackInSlot(0);
		ItemStack reverse = getStackInSlot(11);
		ItemStack ink = getStackInSlot(12);

		//writing items to the note
		if((note != null) && (note.stackSize == 1) && (reverse == null) && (ink != null)){
			if(note.getItem() instanceof ItemNote){

				Item itemInNote = null;

				if(note.hasTagCompound())
					itemInNote = Item.getItemById(note.getTagCompound().getShort(StackUtils.ITM));

				ItemStack sample = null;

				//setting a sample that will be compared to all other items in the slot
				//the sample is the first stack encountered.
				for(int i = 1; i < 10; i++)
					if(getStackInSlot(i) != null){
						sample = getStackInSlot(i);
						break;
					}

				//the size that will be set to nbt, accounted all stack sizes from item stacks in the 3x3 grid
				int newStacksize = 0;

				if(sample != null){
					//if the sample has an nbt (enchanted items, etc...), dont make a recipe
					//unless the sample is an item note. this allows for fusing notes
					if(sample.hasTagCompound() && !(sample.getItem() instanceof ItemNote)){
						setInventorySlotContents(10, null);
						return;
					}

					NBTTagCompound notedItemTag = new NBTTagCompound();

					//the number of stacks currently in the 3x3 grid. can be maximum 9. used for referring the need of ink
					int numberOfStacks = 0;

					for(int i = 1; i < 10; i++)
						if(getStackInSlot(i) != null){

							//if the sample (first stack found) does not match another itemstack in the 3x3 grid, break out of the loop,
							//setting the result to null
							if(!getStackInSlot(i).getItem().equals(sample.getItem())){
								sample = null;
								setInventorySlotContents(10, null);
								break;
							}

							numberOfStacks += 1;

							//if the items have the same damage (we dont want to merge itemdyes...)
							if(getStackInSlot(i).getItemDamage() == sample.getItemDamage())

								if((getStackInSlot(i).getItem() instanceof ItemNote)){

									if(new StackUtils().NBTAreEqual(sample.stackTagCompound, getStackInSlot(i).getTagCompound()))
										notedItemTag = new StackUtils().fuseNbt(notedItemTag, getStackInSlot(i).getTagCompound());
									else{
										setInventorySlotContents(10, null);
										return;
									}

								}else
									newStacksize += getStackInSlot(i).stackSize;
						}

					//if the player did not provide enough ink, you can't write notes
					if(numberOfStacks > ink.stackSize){
						setInventorySlotContents(10, null);
						return;
					}

					//sample might have been previously set to null
					if(sample != null){

						//if the contained item in the note, if any, is not the same as the sample,  dont make a note.
						if(note.hasTagCompound() && (itemInNote != null) && !sample.getItem().equals(itemInNote)){
							setInventorySlotContents(10, null);
							return;
						}

						NBTTagCompound tag = new StackUtils().createNotedNbt(
								newStacksize,
								sample.getDisplayName(),
								sample.getItemDamage(),
								(short) Item.getIdFromItem(sample.getItem()));

						if(!note.hasTagCompound() && sample.hasTagCompound() && (sample.getItem() instanceof ItemNote))
							tag = notedItemTag;

						if(note.hasTagCompound()){
							tag = new StackUtils().fuseNbt(note.getTagCompound(), tag);

							if(sample.hasTagCompound() && (sample.getItem() instanceof ItemNote))
								tag = new StackUtils().fuseNbt(note.getTagCompound(), notedItemTag);
						}


						ItemStack noted = new ItemStack(Notes.note, 1,0);
						noted.stackTagCompound = tag;

						setInventorySlotContents(10, noted);
						markDirty();
					}else
						setInventorySlotContents(10, null);
				} else
					setInventorySlotContents(10, null);
			}
			markDirty();
		}

		//getting items from the noted item
		else if ((reverse != null) && (note == null) && (reverse.stackSize == 1)){
			if(reverse.hasTagCompound()){

				Item containedItem = Item.getItemById(reverse.getTagCompound().getShort(StackUtils.ITM));

				int storedItemsTotal = reverse.getTagCompound().getInteger(StackUtils.AMT);

				ItemStack stub = new ItemStack(containedItem);
				int maxStackSize = stub.getMaxStackSize();

				int stacksizeToSubstract = 0;

				//iterate over all slots
				for(int slot = 1; slot < 10; slot++ )
					//check ALL slots for 1 stack. if the first encountered stack is empty,
					//the stack will be set to that slot and break out of this loop, then runs the same code for
					//the next slot.
					//this allows for a new stack to be set to any empty stack, not the stack corresponding to it's slot
					for(int runStacks = 1; runStacks < 10; runStacks ++){
						int stackIndex = slot-1;

						ItemStack newStack = new ItemStack(containedItem);
						newStack.stackSize = Math.min(maxStackSize, storedItemsTotal- (stackIndex*maxStackSize));

						newStack.setItemDamage(reverse.getTagCompound().getInteger(StackUtils.DMG));

						if(newStack.stackSize <= 0)
							newStack = null;

						if((getStackInSlot(runStacks) == null) && (newStack != null)){

							setInventorySlotContents(runStacks, newStack);
							stacksizeToSubstract += newStack.stackSize;
							reverse.getTagCompound().setInteger(StackUtils.AMT, storedItemsTotal - stacksizeToSubstract);
							break;
						}
					}

				if(reverse.getTagCompound().getInteger(StackUtils.AMT) <= 0)
					reverse = new ItemStack(Notes.note);

				setInventorySlotContents(11, reverse);
				markDirty();
			}
		} else
			setInventorySlotContents(10, null);

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
