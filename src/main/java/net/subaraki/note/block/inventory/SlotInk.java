package net.subaraki.note.block.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;

public class SlotInk extends Slot{

	public SlotInk(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack) {
		return ((par1ItemStack.getItem() instanceof ItemDye) && (par1ItemStack.getItemDamage() == 0));
	}


}
