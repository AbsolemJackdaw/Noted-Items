package net.subaraki.powermasks.event;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.subaraki.powermasks.item.ItemWraithMask;
import net.subaraki.powermasks.proxy.ClientProxy;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EventHandler {

	public EventHandler() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onLivingSetAttackTarget(LivingSetAttackTargetEvent event){



	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void renderplayer(RenderPlayerEvent.SetArmorModel evt){

		if(hasWraithMask(evt.entityPlayer)){
			ClientProxy.wraithModel.bipedHead.rotateAngleX = 
					evt.renderer.modelBipedMain.bipedHead.rotateAngleX;
			ClientProxy.wraithModel.bipedHead.rotateAngleY = 
					evt.renderer.modelBipedMain.bipedHead.rotateAngleY;
			ClientProxy.wraithModel.bipedHead.rotateAngleZ = 
					evt.renderer.modelBipedMain.bipedHead.rotateAngleZ;

			Minecraft.getMinecraft().renderEngine.bindTexture();
			GL11.glPushMatrix();
			ClientProxy.wraithModel.render(evt.entityPlayer,0,0,0,0,0,0.0625f);
			GL11.glPopMatrix();
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

		if(p.inventory.armorInventory[0] == null)
			return false;

		if(p.inventory.armorInventory[0].getItem() instanceof ItemWraithMask)
			return true;

		return false;
	}
}
