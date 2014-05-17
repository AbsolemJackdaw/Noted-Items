package net.subaraki.note.block.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerNotingBlock extends Container {

	public ContainerNotingBlock(TileEntityNoteTable te, EntityPlayer player) {

		InventoryPlayer inv = player.inventory;

		this.addSlotToContainer(new SlotNote(te, 0, 32, 16));

		this.addSlotToContainer(new SlotNoteResult(te, 10, 144, 32));
		this.addSlotToContainer(new SlotNote(te, 11, 32, 48));

		for (int l = 0; l < 3; ++l)
			for (int i1 = 0; i1 < 3; ++i1)
				this.addSlotToContainer(new Slot(te, 1 +  i1 + (l * 3), 78 + (i1 * 18), 16 + (l * 18)));

		for (int i = 0; i < 3; i++)
			for (int k = 0; k < 9; k++)
				addSlotToContainer(new Slot(inv, k + (i * 9) + 9, 8 + (k * 18), (68 - 2) + (i * 18) + 18));

		for (int j = 0; j < 9; j++)
			addSlotToContainer(new Slot(inv, j, 8 + (j * 18), (126 - 2) + 18));
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}


	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(par2);

		//0 = input 1
		//2 = input 2
		//1 = output
		// 3 - 11 = inputs
		// 12 - 47 = vanilla 39-47hotbar

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 == 1)
			{
				if (!this.mergeItemStack(itemstack1, 12, 47, true))
				{
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (par2 >= 12 && par2 <= 47)
			{
				if (!this.mergeItemStack(itemstack1, 3, 12, false))
				{
					return null;
				}
			}
			else if (par2 >= 3 && par2 <= 11)
			{
				if (!this.mergeItemStack(itemstack1, 12, 48, false))
				{
					return null;
				}
			}

			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack)null);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);

		}

		return itemstack;

	}
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
	}

}
