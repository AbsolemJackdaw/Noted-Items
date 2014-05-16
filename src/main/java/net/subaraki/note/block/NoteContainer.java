package net.subaraki.note.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class NoteContainer extends Container {

	public NoteContainer(TileEntityNoteTable te, EntityPlayer player) {

		InventoryPlayer inv = player.inventory;

		this.addSlotToContainer(new Slot(te, 0, 32, 34));

		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				this.addSlotToContainer(new Slot(te, 0, 78 + i *16, 16 + k*16));
			}
		}

		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 9; k++) {
				addSlotToContainer(new Slot(inv, k + (i * 9) + 9, 8 + (k * 18), 68 + (i * 18) + 18));
			}
		}

		for (int j = 0; j < 9; j++) {
			addSlotToContainer(new Slot(inv, j, 8 + (j * 18), 126 + 18));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

}
