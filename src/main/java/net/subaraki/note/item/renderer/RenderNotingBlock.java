package net.subaraki.note.item.renderer;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.subaraki.note.block.inventory.TileEntityNoteTable;

import org.lwjgl.opengl.GL11;

public class RenderNotingBlock implements IItemRenderer {

	public RenderNotingBlock() {
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		GL11.glPushMatrix();

		switch (type) {
		case ENTITY:
			GL11.glTranslatef(-0.5f, 0, -0.5f);
			break;

		case INVENTORY:
			GL11.glTranslatef(0, -0.15f, 0);
			break;

		default:
			break;
		}

		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityNoteTable(), 0D, 0D, 0D, 0F);

		GL11.glPopMatrix();
	}
}
