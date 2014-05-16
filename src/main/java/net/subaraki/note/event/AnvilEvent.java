package net.subaraki.note.event;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.subaraki.note.Notes;
import net.subaraki.note.StackUtils;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class AnvilEvent {

	public AnvilEvent() {
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
	}

	@SubscribeEvent
	public void onAnvilUpdateEvent(AnvilUpdateEvent evt){
		if((evt.left != null) && (evt.right != null))
			if(evt.left.stackSize == 1)
				if(evt.left.getItem().equals(Notes.note) && !evt.right.getItem().equals(Notes.note)){

					if(evt.left.hasTagCompound() && !evt.left.stackTagCompound.getString(StackUtils.ID).equals(evt.right.getDisplayName()))
						return;

					if(evt.left.hasTagCompound() && (evt.left.stackTagCompound.getInteger(StackUtils.DMG) != evt.right.getItemDamage()))
						return;

					int size = 0;
					if(evt.left.hasTagCompound())
						size = evt.left.getTagCompound().getInteger(StackUtils.AMT);

					int amt = evt.right.stackSize + size;
					NBTTagCompound tag = new StackUtils().createNotedNbt(
							amt,
							evt.right.getDisplayName(),
							evt.right.getItemDamage(),
							(short) Item.getIdFromItem(evt.right.getItem()));

					evt.cost = (amt / 64) == 0 ? 1 : amt/64;

					ItemStack noted = new ItemStack(Notes.note, 1,0);
					noted.stackTagCompound = tag;

					evt.output = noted;

				}else if (evt.left.getItem().equals(Notes.note) && evt.right.getItem().equals(Notes.note))
					if(evt.left.hasTagCompound() && evt.right.hasTagCompound())
						if(evt.left.getTagCompound().getString(StackUtils.ID).equals(evt.right.getTagCompound().getString(StackUtils.ID)))
							if(evt.left.getTagCompound().getInteger(StackUtils.DMG) == evt.right.getTagCompound().getInteger(StackUtils.DMG)){

								ItemStack noted = new ItemStack(Notes.note, 1,0);
								NBTTagCompound tag = new StackUtils().createNotedNbt(
										0,
										evt.right.getTagCompound().getString(StackUtils.ID),
										evt.right.getTagCompound().getInteger(StackUtils.DMG),
										(short) Item.getIdFromItem(Item.getItemById(evt.right.getTagCompound().getShort(StackUtils.ITM))));

								int amt = evt.left.getTagCompound().getInteger(StackUtils.AMT) +
										evt.right.getTagCompound().getInteger(StackUtils.AMT);

								tag.setInteger(StackUtils.AMT, amt);

								noted.setTagCompound(tag);

								evt.output = noted;
								evt.cost = (amt / 64) == 0 ? 1 : amt/64;
							}
	}
}
