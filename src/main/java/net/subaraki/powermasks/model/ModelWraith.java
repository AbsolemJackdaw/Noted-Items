package net.subaraki.powermasks.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelWraith extends ModelSimpleBiped {

	public ModelRenderer[] clothpiece = new ModelRenderer[6];

	public ModelWraith(){
		for(int i = 0; i < clothpiece.length; i++){
			clothpiece[i] = new ModelRenderer(this, 0, 0);
			clothpiece[i].addBox(0f, 0f, 0f, 1, 1, 2);
//			if(i < clothpiece.length -1)
//				clothpiece[i].addChild(clothpiece[i+1]);
		}
		
//		bipedLeftArm.addChild(clothpiece[5]);
	}

	private int progress = (int) (((System.currentTimeMillis() - System.currentTimeMillis()) / 50F) / 3.97F);

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		float var4 = 0.5F * 0.75F;
		float var6 = f * 0.6662F + this.progress * 0.6662f;

		for(int j = 0; j < clothpiece.length; j++)
			clothpiece[j].rotateAngleX = MathHelper.sin((var6 * 2 - (j + 1) * 1F) * var4) / 10f;	
	}

	@Override
	public void renderModel(float f5) {
		super.renderModel(f5);
		clothpiece[0].render(f5);
	}

}
