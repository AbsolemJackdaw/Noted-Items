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
		
		float f = 1.0f;

		GL11.glPushMatrix();

		switch (type) {
		case EQUIPPED_FIRST_PERSON:
//			GL11.glTranslatef(0, -1.8f, -0.6f);
//			GL11.glScalef(f, f, f);
			break;

		case EQUIPPED:
//			GL11.glRotatef(-55, 1, 0, 0);
//			GL11.glRotatef(-40, 0, 0, 1);
//			GL11.glTranslatef(0, -1.2f, 0);
//			GL11.glScalef(f, f, f);
			break;

		case ENTITY:
			GL11.glTranslatef(-0.5f, 0, -0.5f);
			break;

		case INVENTORY:
			GL11.glScalef(f, f, f);
			GL11.glTranslatef(0, -0.15f, 0);
			break;

		default:
			break;
		}
		
		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityNoteTable(), 0D, 0D, 0D, 0F);
		
		GL11.glPopMatrix();


	}

}
