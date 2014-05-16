package net.subaraki.note;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = "noteditems", name = "Noted Items", version = "ModJamBeta")
public class Notes {

	public static Item note;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e){

		note = new ItemNote().setUnlocalizedName("notedItem").setTextureName("map_empty").setCreativeTab(CreativeTabs.tabAllSearch);
		GameRegistry.registerItem(note, "notedItem");
		
		GameRegistry.addRecipe(new ItemStack(note), new Object[]{
			"xxx","xpx","xxx", 'x', Items.paper, 'p' , Items.feather
		});
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e){
		new AnvilEvent();
	}
	
}
