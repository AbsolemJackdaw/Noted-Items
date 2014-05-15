package net.subaraki.note;

import net.minecraft.nbt.NBTTagCompound;

public class StackUtils {

	public static String ID  = "notedItemName";
	public static String AMT = "notedAmount";
	public static String DMG = "notedDamage";
	public static String ITM = "notedItemID";

	public StackUtils(){
	}

	public NBTTagCompound createNotedNbt(int amt, String name, int damage, byte itemid){
		NBTTagCompound tag = new NBTTagCompound();

		tag.setInteger(AMT, amt);
		tag.setString(ID, name);
		tag.setInteger(DMG, damage);
		tag.setShort(ITM, itemid);

		return tag;
	}


}
