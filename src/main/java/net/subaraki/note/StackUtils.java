package net.subaraki.note;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class StackUtils {

	/**display name*/
	public static String ID  = "notedItemName";
	/**amount of items stored*/
	public static String AMT = "notedAmount";
	/**damage of the stored item*/
	public static String DMG = "notedDamage";
	/**a short: the item ID*/
	public static String ITM = "notedItemID";

	public StackUtils(){
	}

	/**creates a new nbt tag with all requiered tags for a noted item*/
	public NBTTagCompound createNotedNbt(int amt, String name, int damage, short itemid){
		NBTTagCompound tag = new NBTTagCompound();

		tag.setInteger(AMT, amt);
		tag.setString(ID, name);
		tag.setInteger(DMG, damage);
		tag.setShort(ITM, itemid);

		return tag;
	}

	public NBTTagCompound fuseNbt(NBTTagCompound tag1, NBTTagCompound tag2) {
		NBTTagCompound tag = new NBTTagCompound();
		if(tag1 == null || tag1.hasNoTags())
			return tag2;
		
		if(tag2 == null || tag2.hasNoTags())
			return tag1;

		tag.setInteger(AMT, tag1.getInteger(AMT) + tag2.getInteger(AMT));
		tag.setString(ID, tag1.getString(ID));
		tag.setInteger(DMG, tag1.getInteger(DMG));
		tag.setShort(ITM, tag1.getShort(ITM));

		return tag;
	}

	public boolean NBTAreEqual(NBTTagCompound tag1, NBTTagCompound tag2){

		if(tag1 == null)
			return false;
		if(tag2 == null)
			return false;
		
		if((tag1.getInteger(DMG) == tag2.getInteger(DMG)) &&
				(tag1.getShort(ITM) == tag2.getShort(ITM)) &&
				tag1.getString(ID).equals(tag2.getString(ID)))
			return true;

		return false;
	}

	public boolean itemEqualsNoteNBT(ItemStack stack, NBTTagCompound tag){

		if(tag.getString(ID).equals(stack.getDisplayName()) &&
				(tag.getInteger(DMG) == stack.getItemDamage()) &&
				(tag.getShort(ITM) == Item.getIdFromItem(stack.getItem())))
			return true;


		return false;
	}

}
