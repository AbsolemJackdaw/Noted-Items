package net.subaraki.note.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.subaraki.note.block.inventory.ContainerNotingBlock;
import net.subaraki.note.block.inventory.TileEntityNoteTable;

import org.lwjgl.opengl.GL11;

public class GuiNotingBlock extends GuiContainer {

	EntityPlayer player;
	TileEntityNoteTable te;

	private ResourceLocation BG = new ResourceLocation("noteditems","textures/gui.png");

	public GuiNotingBlock(TileEntityNoteTable te, EntityPlayer player) {
		super(new ContainerNotingBlock(te, player));
		this.te = te;
		this.player = player;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;

		mc.renderEngine.bindTexture(BG);
		drawTexturedModalRect(j, k, 0, 0, 176, 166);

	}


}
