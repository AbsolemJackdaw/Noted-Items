package net.subaraki.note.block.renderer;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.subaraki.note.block.model.ModelNotingTable;

public class TileEntitySpecialRenderingNoteTable extends
TileEntitySpecialRenderer {

	private ModelNotingTable table;
	private static final ResourceLocation texture = new ResourceLocation("noteditems","model/table.png");

	public TileEntitySpecialRenderingNoteTable() {
		table = new ModelNotingTable();
	}

	@Override
	public void renderTileEntityAt(TileEntity var1, double x, double y,
			double z, float var8) {

		glPushMatrix();

		glTranslatef((float) x + 0.5F, (float) y + 2.25F, (float) z + 0.5F);
		glScalef(1.0F, -1F, -1F);

		glTranslatef(0f, 0.75f, 0f);

		bindTexture(texture);

		table.render();

		glPopMatrix();
	}

}
