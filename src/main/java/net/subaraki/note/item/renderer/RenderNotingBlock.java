package net.subaraki.note.item.renderer;

import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.subaraki.note.block.model.ModelNotingTable;

import org.lwjgl.opengl.GL11;

public class RenderNotingBlock implements IItemRenderer {

	private ModelNotingTable table = new ModelNotingTable();
	private static final ResourceLocation texture = new ResourceLocation("noteditems","model/table.png");

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

		Minecraft.getMinecraft().renderEngine.bindTexture(texture);

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

		glTranslatef(0.5F ,2.25F ,0.5F);
		glScalef(1.0F, -1F, -1F);

		glTranslatef(0f, 0.75f, 0f);

		table.render();


		//TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityNoteTable(), 0D, 0D, 0D, 0F);

		GL11.glPopMatrix();
	}
}
