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
		if(evt.left != null && evt.right != null){
			if(evt.left.getItem().equals(Notes.note)){

				if(evt.left.hasTagCompound() && !evt.left.stackTagCompound.getString(StackUtils.ID).equals(evt.right.getDisplayName()))
					return;

				evt.cost = 2;

				int size = 0;
				if(evt.left.hasTagCompound())
					size = evt.left.getTagCompound().getInteger(StackUtils.AMT);

				NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger(StackUtils.AMT, evt.right.stackSize + size);
				tag.setString(StackUtils.ID, evt.right.getDisplayName());

				ItemStack noted = new ItemStack(Notes.note, 1,0);
				noted.stackTagCompound = tag;
				evt.output = noted;
			}
		}
	}

}
