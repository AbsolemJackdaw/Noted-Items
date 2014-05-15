package net.subaraki.powermasks;

import net.subaraki.powermasks.item.PowerMaskItems;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "powermasks", name = "Power Masks", version = "ModJamDev")
public class PowerMasks {

	
	@EventHandler
	public void init(FMLInitializationEvent evt){
		
		new net.subaraki.powermasks.event.EventHandler();
		
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent evt){
		
		new PowerMaskItems();
		
	}
	
}
