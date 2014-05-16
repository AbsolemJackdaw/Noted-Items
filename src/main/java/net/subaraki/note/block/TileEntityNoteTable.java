package net.subaraki.note.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

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
	public ItemStack decrStackSize(int var1, int var2) {
		
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		
		return null;
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

		
		
	}

}
