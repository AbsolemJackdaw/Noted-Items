package net.subaraki.powermasks.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelWraith extends ModelSimpleBiped {

	public ModelRenderer[] clothpiece = new ModelRenderer[6];

	public ModelWraith(){
		for(int i = 0; i < clothpiece.length; i++){
			clothpiece[i] = new ModelRenderer(this, 0, 20);
			clothpiece[i].addBox(0f, 0f, 0f, 2, 1, 1);
		}
		
		for (int var3 = 0; var3 < clothpiece.length - 1; var3++) {
			this.clothpiece[var3].addChild(this.clothpiece[var3 + 1]);
		}
		
		bipedLeftArm.addChild(clothpiece[0]);
	}

	private int progress = (int) (((System.currentTimeMillis() - System.currentTimeMillis()) / 50F) / 3.97F);

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4,
			float par5, float par6, float par7) {
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		setRotationAngles( par2, par3, par4, par5, par6, par7, par1Entity);
	}
	
	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		
	}

	@Override
	public void renderModel(float f5, float f) {
		super.renderModel(f5, f);
		for (ModelRenderer element : this.clothpiece) {
			element.setRotationPoint(0, 0, 1);
			
		}
		
		clothpiece[0].setRotationPoint(0, 0, 1);
		clothpiece[0].render(0.0625f);
		
		float var4 = 0.5F * 0.75F;
		float var6 = f * 0.6662F + this.progress * 0.6662f;

		for(int j = 0; j < clothpiece.length; j++)
			clothpiece[j].rotateAngleX = MathHelper.sin((var6 * 2 - (j + 1) * 1F) * var4) / 10f;
	}

}
