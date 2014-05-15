package net.subaraki.powermasks.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.subaraki.powermasks.item.ItemPowerMask;
import net.subaraki.powermasks.item.ItemWraithMask;
import net.subaraki.powermasks.proxy.ClientProxy;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PowerMaskEvents {

	public PowerMaskEvents() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onLivingSetAttackTarget(LivingSetAttackTargetEvent event){

	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void renderplayer(RenderPlayerEvent.SetArmorModel evt){

		if(evt.entityLiving instanceof EntityPlayer){
			if(hasWraithMask((EntityPlayer) evt.entityLiving)){
				evt.entityLiving.setInvisible(true);
			}else{
				evt.entityLiving.setInvisible(false);
			}
		}
		
		if(hasWraithMask(evt.entityPlayer)){
			if(evt.slot == 3){
				syncBipedModels(ClientProxy.wraithModel, evt.renderer.modelBipedMain);
				
				Minecraft.getMinecraft().renderEngine.bindTexture(((ItemPowerMask)evt.stack.getItem()).getSkin());

				GL11.glPushMatrix();
				ClientProxy.wraithModel.renderModel(0.0625f);
				GL11.glPopMatrix();
				evt.result = 0;
			}
		}
	}

	@SubscribeEvent
	public void onLivingUpdateEvent(LivingUpdateEvent evt){

		if(evt.entityLiving instanceof EntityPlayer){
			if(hasWraithMask((EntityPlayer) evt.entityLiving)){
				evt.entityLiving.setInvisible(true);
			}else{
				evt.entityLiving.setInvisible(false);
			}
		}
	}

	@SubscribeEvent
	public void onJumpEvent(LivingJumpEvent evt){

		if(evt.entityLiving instanceof EntityPlayer)
			if(hasWraithMask((EntityPlayer) evt.entityLiving))
				evt.entityLiving.motionY = 0;

	}

	private boolean hasWraithMask(EntityPlayer p){

		if(p.inventory.armorInventory[3] == null)
			return false;

		if(p.inventory.armorInventory[3].getItem() instanceof ItemWraithMask)
			return true;

		return false;
	}
	
	
	private void syncBipedModels(ModelBiped model1, ModelBiped model2){
		model1.bipedHead.rotateAngleX = model2.bipedHead.rotateAngleX;
		model1.bipedHead.rotateAngleY = model2.bipedHead.rotateAngleY;
		model1.bipedHead.rotateAngleZ = model2.bipedHead.rotateAngleZ;

		model1.bipedHeadwear.rotateAngleX = model2.bipedHeadwear.rotateAngleX;
		model1.bipedHeadwear.rotateAngleY = model2.bipedHeadwear.rotateAngleY;
		model1.bipedHeadwear.rotateAngleZ = model2.bipedHeadwear.rotateAngleZ;

		model1.bipedLeftArm.rotateAngleX = model2.bipedLeftArm.rotateAngleX;
		model1.bipedLeftArm.rotateAngleY = model2.bipedLeftArm.rotateAngleY;
		model1.bipedLeftArm.rotateAngleZ = model2.bipedLeftArm.rotateAngleZ;

		model1.bipedRightArm.rotateAngleX = model2.bipedRightArm.rotateAngleX;
		model1.bipedRightArm.rotateAngleY = model2.bipedRightArm.rotateAngleY;
		model1.bipedRightArm.rotateAngleZ = model2.bipedRightArm.rotateAngleZ;
		
		model1.bipedLeftLeg.rotateAngleX = model2.bipedLeftLeg.rotateAngleX;
		model1.bipedLeftLeg.rotateAngleY = model2.bipedLeftLeg.rotateAngleY;
		model1.bipedLeftLeg.rotateAngleZ = model2.bipedLeftLeg.rotateAngleZ;
		
		model1.bipedRightLeg.rotateAngleX = model2.bipedRightLeg.rotateAngleX;
		model1.bipedRightLeg.rotateAngleY = model2.bipedRightLeg.rotateAngleY;
		model1.bipedRightLeg.rotateAngleZ = model2.bipedRightLeg.rotateAngleZ;
		
		model1.bipedBody.rotateAngleX = model2.bipedBody.rotateAngleX;
		model1.bipedBody.rotateAngleY = model2.bipedBody.rotateAngleY;
		model1.bipedBody.rotateAngleZ = model2.bipedBody.rotateAngleZ;
		
	}
}
