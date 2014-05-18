package net.subaraki.note.block.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.subaraki.note.item.ItemNote;

public class ContainerNotingBlock extends Container {

	EntityPlayer p;
	TileEntityNoteTable te;

	public ContainerNotingBlock(TileEntityNoteTable te, EntityPlayer player) {

		p = player;
		this.te = te;

		InventoryPlayer inv = player.inventory;

		this.addSlotToContainer(new SlotNote(te, 0, 32, 16));

		this.addSlotToContainer(new SlotNoteResult(te, 10, 144, 32));
		this.addSlotToContainer(new SlotNote(te, 11, 32, 48));
		this.addSlotToContainer(new SlotInk(te, 12, 6, 16));

		for (int l = 0; l < 3; ++l) {
			for (int i1 = 0; i1 < 3; ++i1) {
				this.addSlotToContainer(new Slot(te, 1 +  i1 + (l * 3), 78 + (i1 * 18), 16 + (l * 18)));
			}
		}

		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 9; k++) {
				addSlotToContainer(new Slot(inv, k + (i * 9) + 9, 8 + (k * 18), (68 - 2) + (i * 18) + 18));
			}
		}

		for (int j = 0; j < 9; j++) {
			addSlotToContainer(new Slot(inv, j, 8 + (j * 18), (126 - 2) + 18));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}


	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(par2);

		int input = 0;
		int result = 1;
		int exchange = 2;
		int ink = 3;

		int inputsMax = 12;
		int inputsMin = 4;

		int vanillaMax = 48;
		int vanillaMin = 13;

		if ((slot != null) && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if ((par2 == ink) || (par2 == input) || (par2 == exchange)){
				if (!this.mergeItemStack(itemstack1, vanillaMin, vanillaMax+1, true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
			}

			if(par2 == result){
				if (!this.mergeItemStack(itemstack1, vanillaMin, vanillaMax+1, true)) {
					return null;
				}
				slot.decrStackSize(1);
			}

			if ((par2 >= vanillaMin) && (par2 <= vanillaMax))
			{
				if((itemstack.getItem() instanceof ItemDye) && (itemstack.getItemDamage() == 0)){
					if (!this.mergeItemStack(itemstack1, ink, ink+1, false)) {
						return null;
					}
				}
				else if(itemstack.getItem() instanceof ItemNote){
					if(!((Slot)inventorySlots.get(input)).getHasStack()){
						if (!this.mergeItemStack(itemstack1, input, input+1, false)) {
							return null;
						}
					}
					else
						if (!this.mergeItemStack(itemstack1, inputsMin, inputsMax+1, false)) {
							return null;
						}
				}
				else if (!this.mergeItemStack(itemstack1, inputsMin, inputsMax+1, false)) {
					return null;
				}
			}
			else if ((par2 >= inputsMin) && (par2 <= inputsMax)) {
				if (!this.mergeItemStack(itemstack1, vanillaMin, vanillaMax +1, false)) {
					return null;
				}
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack)null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);

		}

		return itemstack;

	}

	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer)
	{
		super.onContainerClosed(par1EntityPlayer);

		if (!this.p.worldObj.isRemote) {
			for (int i = 1; i < 10; ++i) {
				ItemStack itemstack = this.te.getStackInSlotOnClosing(i);

				if (itemstack != null) {
					par1EntityPlayer.dropPlayerItemWithRandomChoice(itemstack, false);
				}
			}
		}
		te.markDirty();
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
	}

}
