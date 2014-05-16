package net.subaraki.note.block;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.subaraki.note.ItemNote;

public class NoteSlot extends Slot {

	public NoteSlot(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack) {
		return par1ItemStack.getItem() instanceof ItemNote;
	}
}