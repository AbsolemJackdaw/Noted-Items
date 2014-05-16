package net.subaraki.note;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.subaraki.note.block.NoteContainer;
import net.subaraki.note.block.NoteGui;
import net.subaraki.note.block.TileEntityNoteTable;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public GuiHandler() {
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return new NoteContainer(world.getTileEntity(x, y, z), player);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return new NoteGui((TileEntityNoteTable) world.getTileEntity(x, y, z), player);
	}

}
