package net.subaraki.note.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class NoteContainer extends Container {

	public NoteContainer() {
		
		
		
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

}
