package net.subaraki.note.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class NoteContainer extends Container {

	public NoteContainer(TileEntityNoteTable te, EntityPlayer player) {

		InventoryPlayer inv = player.inventory;


		this.addSlotToContainer(new NoteSlot(te, 0, 32, 16));

		this.addSlotToContainer(new SlotResult(te, 10, 144, 32));
		this.addSlotToContainer(new NoteSlot(te, 11, 32, 48));

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
	
}
