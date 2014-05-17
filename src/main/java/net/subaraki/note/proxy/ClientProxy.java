package net.subaraki.note.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.subaraki.note.Notes;
import net.subaraki.note.block.inventory.TileEntityNoteTable;
import net.subaraki.note.block.renderer.TileEntitySpecialRenderingNoteTable;
import net.subaraki.note.item.renderer.RenderNotingBlock;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends ServerProxy {

	public ClientProxy() {
	}

	@Override
	public void registerRendering() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNoteTable.class, new TileEntitySpecialRenderingNoteTable());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Notes.table), new RenderNotingBlock());
	}
}
