package net.subaraki.note;

import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "notedItems", name = "Noted Items", version = "ModJamBeta")
public class Notes {

	
	public static Item note;
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e){
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e){
		
		note = new ItemNote().setUnlocalizedName("notedItem").setTextureName("map_empty");
		GameRegistry.registerItem(note, "notedItem", "notedItems");
	}
	
}
