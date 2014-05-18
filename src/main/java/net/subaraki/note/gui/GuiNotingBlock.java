package net.subaraki.note.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.subaraki.note.block.inventory.ContainerNotingBlock;
import net.subaraki.note.block.inventory.TileEntityNoteTable;
import net.subaraki.note.config.Config;

import org.lwjgl.opengl.GL11;

public class GuiNotingBlock extends GuiContainer {

	EntityPlayer player;
	TileEntityNoteTable te;

	private ResourceLocation BG = new ResourceLocation("noteditems","textures/gui.png");

	public GuiNotingBlock(TileEntityNoteTable te, EntityPlayer player){
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

		if(te.getStackInSlot(0) != null && te.getStackInSlot(12) != null && te .getStackInSlot(11) == null){
			drawTexturedModalRect(j + 56, k + 22, 32, 176, 84, 40);

		}else if (te.getStackInSlot(11) != null && te .getStackInSlot(0) == null){
			drawTexturedModalRect(j + 56 , k + 50, 16, 176, 16, 16);
		}

		ItemStack stub = new ItemStack(Items.dye, 1 , 0);

		String s = Config.instance.inkPerStack + "";

		if(Config.instance.shouldShow)
			drawString(fontRendererObj, s, j+22, k+30, 0xffffff);
	}


}
