package net.subaraki.powermasks.model;

import net.minecraft.client.model.ModelBiped;

public class ModelSimpleBiped extends ModelBiped{

	public ModelSimpleBiped() {
	}


	public void renderModel(float f5, float f){
		bipedBody.render(f5);
		bipedHeadwear.render(f5);
		bipedHead.render(f5);
		bipedLeftArm.render(f5);
		bipedLeftLeg.render(f5);
		bipedRightArm.render(f5);
		bipedRightLeg.render(f5);
	}
}
