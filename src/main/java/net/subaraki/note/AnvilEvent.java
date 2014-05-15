package net.subaraki.note;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class AnvilEvent {

	public AnvilEvent() {
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
	}
	
	
	@SubscribeEvent
	public void onAnvilUpdateEvent(AnvilUpdateEvent evt){
		System.out.println("");
		if(evt.left != null && evt.right != null){
			if(evt.left.getItem().equals(Notes.note)){
				NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger(StackUtils.AMT, evt.right.stackSize);
				
				ItemStack noted = new ItemStack(Notes.note, 1,0);
				evt.output = noted;
				evt.cost = 0;
			}
		}
		
		evt.setCanceled(true);
	}

}
