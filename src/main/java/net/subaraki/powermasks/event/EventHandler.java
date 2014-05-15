package net.subaraki.powermasks.event;

import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
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
		
		
		
	}
	
	@SubscribeEvent
	public void onLivingUpdateEvent(LivingUpdateEvent evt){
		
		
		
	}
	
	@SubscribeEvent
	public void onJumpEvent(LivingJumpEvent evt){
		
		
		
	}
}
