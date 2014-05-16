package net.subaraki.note.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;

public class NoteContainer extends Container {

	public NoteContainer(TileEntity te, EntityPlayer player) {
		
		
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

}
