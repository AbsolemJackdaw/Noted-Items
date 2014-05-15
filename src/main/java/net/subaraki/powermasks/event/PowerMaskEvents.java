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
		for(int i = 0; i < model1.boxList.size(); i ++){
			((ModelRenderer)model1.boxList.get(i)).rotateAngleX = ((ModelRenderer)model2.boxList.get(i)).rotateAngleX;
			((ModelRenderer)model1.boxList.get(i)).rotateAngleY = ((ModelRenderer)model2.boxList.get(i)).rotateAngleY;
			((ModelRenderer)model1.boxList.get(i)).rotateAngleZ = ((ModelRenderer)model2.boxList.get(i)).rotateAngleZ;
		}
	}
}
