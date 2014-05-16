package net.subaraki.note.proxy;

import net.subaraki.note.block.inventory.TileEntityNoteTable;
import net.subaraki.note.block.renderer.TileEntitySpecialRenderingNoteTable;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends ServerProxy {

	public ClientProxy() {
	}

	@Override
	public void registerRendering() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNoteTable.class, new TileEntitySpecialRenderingNoteTable());
	}
}
