package net.subaraki.note.proxy;

import net.subaraki.note.block.TileEntityNoteTable;
import net.subaraki.note.block.TileEntitySpecialRenderingNoteTable;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends ServerProxy {

	public ClientProxy() {
	}

	@Override
	public void registerRendering() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNoteTable.class, new TileEntitySpecialRenderingNoteTable());
	}
}
