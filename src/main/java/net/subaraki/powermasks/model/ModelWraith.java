package net.subaraki.powermasks.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelWraith extends ModelSimpleBiped {

	private int progress = (int) (((System.currentTimeMillis() - System.currentTimeMillis()) / 50F) / 3.97F);

	public ModelRenderer[] clothPieceArmLeft = new ModelRenderer[6];
	public ModelRenderer[] clothPieceArmLeft2 = new ModelRenderer[6];

	public ModelRenderer[] clothPieceHead = new ModelRenderer[6];
	public ModelRenderer[] clothPieceHead2 = new ModelRenderer[6];
	public ModelRenderer[] clothPieceBody = new ModelRenderer[6];
	public ModelRenderer[] clothPieceBody2 = new ModelRenderer[6];

	public ModelWraith(){
		initLeftArm();
		initHead();
		initBody();
	}

	@Override
	public void renderModel(float f5, float f) {
		super.renderModel(f5, f);

		for (ModelRenderer element : this.clothPieceArmLeft) {
			element.setRotationPoint(0, 0, 1);
			clothPieceArmLeft[0].setRotationPoint(0.7f, -1.8f, 1);
		}

		for (ModelRenderer element : this.clothPieceArmLeft2) {
			element.setRotationPoint(0, 0, 1);
			clothPieceArmLeft2[0].setRotationPoint(0.5f, 6.5f, 1);
		}

		for (ModelRenderer element : this.clothPieceHead) {
			element.setRotationPoint(0, 0, 1);
			clothPieceHead[0].setRotationPoint(1.7f, -2.8f, 1);
		}
		
		for (ModelRenderer element : this.clothPieceHead2) {
			element.setRotationPoint(0, 0, 1);
			clothPieceHead2[0].setRotationPoint(-3.7f, -8f, 1);
		}
		
		for (ModelRenderer element : this.clothPieceBody) {
			element.setRotationPoint(0, 0, 1);
			clothPieceBody[0].setRotationPoint(0,0,0);
		}
		
		for (ModelRenderer element : this.clothPieceBody2) {
			element.setRotationPoint(0, 0, 1);
			clothPieceBody2[0].setRotationPoint(-3.5f, 4f, 1f);
		}
		
		float var4 = 0.5F * 0.75F;
		float var6 = f * 0.6662F + this.progress * 0.6662f;

		for(int j = 0; j < clothPieceArmLeft.length; j++){
			clothPieceArmLeft[j].rotateAngleX =clothPieceArmLeft[j].rotateAngleZ = MathHelper.sin((var6 * 1.2f - (j + 1) * 1F) * var4) / 10f;
			clothPieceArmLeft2[j].rotateAngleY = MathHelper.sin((var6 * 0.5f - (j + 1) * 1F) * var4) / 10f;
			
			clothPieceHead[j].rotateAngleX =clothPieceArmLeft[j].rotateAngleZ = MathHelper.sin((var6 * 1.3f - (j + 1) * 1F) * var4) / 10f;
			clothPieceHead2[j].rotateAngleY = MathHelper.sin((var6 * 1.4f - (j + 1) * 1F) * var4) / 10f;
			
			clothPieceBody[j].rotateAngleX =clothPieceArmLeft[j].rotateAngleZ = MathHelper.sin((var6 * 1.f - (j + 1) * 1F) * var4) / 10f;
			clothPieceBody2[j].rotateAngleY = MathHelper.sin((var6 * 1.1f - (j + 1) * 1F) * var4) / 10f;
			clothPieceBody2[0].rotateAngleZ= -0.5f;
		}
		
	}


	private void initLeftArm(){
		for(int i = 0; i < clothPieceArmLeft.length; i++){
			clothPieceArmLeft[i] = new ModelRenderer(this, 0, 0);
			clothPieceArmLeft[i].addBox(0f, 0f, 0f, 2, 1, 1);

			clothPieceArmLeft2[i] = new ModelRenderer(this, 0, 0);
			clothPieceArmLeft2[i].addBox(0f, 0f, 0f, 1, 2, 1);
		}

		for (int var3 = 0; var3 < clothPieceArmLeft.length - 1; var3++) {
			this.clothPieceArmLeft[var3].addChild(this.clothPieceArmLeft[var3 + 1]);
			this.clothPieceArmLeft2[var3].addChild(this.clothPieceArmLeft2[var3 + 1]);
		}

		bipedLeftArm.addChild(clothPieceArmLeft[0]);
		bipedLeftArm.addChild(clothPieceArmLeft2[0]);
	}

	private void initHead(){
		for(int i = 0; i < clothPieceHead.length; i++){
			clothPieceHead[i] = new ModelRenderer(this, 0, 0);
			clothPieceHead[i].addBox(0f, 0f, 0f, 2, 1, 1);

			clothPieceHead2[i] = new ModelRenderer(this, 0, 0);
			clothPieceHead2[i].addBox(0f, 0f, 0f, 1, 2, 1);
		}

		for (int var3 = 0; var3 < clothPieceArmLeft.length - 1; var3++) {
			this.clothPieceHead[var3].addChild(this.clothPieceHead[var3 + 1]);
			this.clothPieceHead2[var3].addChild(this.clothPieceHead2[var3 + 1]);
		}
		
		bipedHead.addChild(clothPieceHead[0]);
		bipedHead.addChild(clothPieceHead2[0]);
	}
	
	private void initBody(){
		for(int i = 0; i < clothPieceBody.length; i++){
			clothPieceBody[i] = new ModelRenderer(this, 0, 0);
			clothPieceBody[i].addBox(0f, 0f, 0f, 1, 1, 1);

			clothPieceBody2[i] = new ModelRenderer(this, 0, 0);
			clothPieceBody2[i].addBox(0f, 0f, 0f, 1, 2, 1);
		}

		for (int var3 = 0; var3 < clothPieceArmLeft.length - 1; var3++) {
			this.clothPieceBody[var3].addChild(this.clothPieceBody[var3 + 1]);
			this.clothPieceBody2[var3].addChild(this.clothPieceBody2[var3 + 1]);
		}
		
		bipedBody.addChild(clothPieceBody[0]);
		bipedBody.addChild(clothPieceBody2[0]);
	}
	
}
