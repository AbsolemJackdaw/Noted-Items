package net.subaraki.note;

import java.lang.reflect.InvocationTargetException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class RenderNote implements IItemRenderer {

	public RenderNote() {
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
		if(type == ItemRenderType.INVENTORY){
			try {
				drawItem(item, 0);

				
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
		}
	}


	private void drawItem(ItemStack var18, int par2) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		IIcon var4 = var18.getIconIndex();

		if (!(var4 instanceof TextureAtlasSprite)) {

			return;
		}

		TextureAtlasSprite icon = (TextureAtlasSprite) var4;

		GL11.glPushMatrix();
		Tessellator var8 = Tessellator.instance;

		float c1 = var4.getMinU();
		float c2 = var4.getMaxU();
		float c3 = var4.getMinV();
		float c4 = var4.getMaxV();

		float var14 = 0.5F;
		float var15 = 0.25F;

		float var16 = 0.0625F;
		float var17 = 0.021875F;
		int var19 = var18.stackSize;
		byte var24;
		if (var19 < 2) {
			var24 = 1;
		}
		else {
			if (var19 < 16) {
				var24 = 2;
			}
			else {
				if (var19 < 32) {
					var24 = 3;
				}
				else {
					var24 = 4;
				}
			}
		}
		GL11.glTranslatef(-var14, -var15, -((var16 + var17) * var24 / 2.0F));

		for (int var20 = 0; var20 < var24; var20++) {
			GL11.glTranslatef(0.0F, 0.0F, var16 + var17);

			bindTextureMap(var18);

			int k1 = var18.getItem().getColorFromItemStack(var18, par2);
			float f4 = (k1 >> 16 & 0xFF) / 255.0F;
			float f6 = (k1 >> 8 & 0xFF) / 255.0F;
			float f8 = (k1 & 0xFF) / 255.0F;
			GL11.glColor4f(f4, f6, f8, 1.0F);
			ItemRenderer.renderItemIn2D(var8, c2, c3, c1, c4, icon.getIconHeight(), icon.getIconWidth(), 0.0625F);

			if ((var18 != null) && (var18.hasEffect(par2)) && (par2 == 0)) {
				GL11.glDepthFunc(514);
				GL11.glDisable(2896);
				//Minecraft.getMinecraft().renderEngine.bindTexture(glint);
				GL11.glEnable(3042);
				GL11.glBlendFunc(768, 1);
				float colorMult = 0.76F;
				GL11.glColor4f(0.5F * colorMult, 0.25F * colorMult, 0.8F * colorMult, 1.0F);
				GL11.glMatrixMode(5890);
				GL11.glPushMatrix();
				float scale = 0.125F;
				GL11.glScalef(scale, scale, scale);
				float time = Minecraft.getSystemTime() % 3000L / 3000.0F * 8.0F;
				GL11.glTranslatef(time, 0.0F, 0.0F);
				GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
				ItemRenderer.renderItemIn2D(var8, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glScalef(scale, scale, scale);
				time = Minecraft.getSystemTime() % 4873L / 4873.0F * 8.0F;
				GL11.glTranslatef(-time, 0.0F, 0.0F);
				GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
				ItemRenderer.renderItemIn2D(var8, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
				GL11.glPopMatrix();
				GL11.glMatrixMode(5888);
				GL11.glDisable(3042);
				GL11.glEnable(2896);
				GL11.glDepthFunc(515);
			}
		}
		GL11.glPopMatrix();
	}

	private void bindTextureMap(ItemStack item) {
		Minecraft.getMinecraft().renderEngine.bindTexture(RenderManager.instance.renderEngine.getResourceLocation(item.getItemSpriteNumber()));
	}
}
