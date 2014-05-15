package net.subaraki.note;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
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
			if(evt.left.getItem().equals(Notes.note) && !evt.right.getItem().equals(Notes.note)){

				if(evt.left.hasTagCompound() && !evt.left.stackTagCompound.getString(StackUtils.ID).equals(evt.right.getDisplayName()))
					return;

				if(evt.left.hasTagCompound() && evt.left.stackTagCompound.getInteger(StackUtils.DMG) != evt.right.getItemDamage())
					return;

				evt.cost = 2;

				int size = 0;
				if(evt.left.hasTagCompound())
					size = evt.left.getTagCompound().getInteger(StackUtils.AMT);

				NBTTagCompound tag = new StackUtils().createNotedNbt(
						evt.right.stackSize + size, 
						evt.right.getDisplayName(), 
						evt.right.getItemDamage(),
						(byte) Item.getIdFromItem(evt.right.getItem()));

				ItemStack noted = new ItemStack(Notes.note, 1,0);
				noted.stackTagCompound = tag;
				evt.output = noted;
			}else if(evt.left.getItem().equals(Notes.note) && evt.right.getItem().equals(Notes.note)){
				ItemStack st = new ItemStack(Blocks.gold_block);
				st.stackSize = 64;
				evt.output = st;
			}
		}
	}

}
