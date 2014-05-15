package net.subaraki.note;

import net.minecraft.nbt.NBTTagCompound;

public class StackUtils {

	public static String ID = "notedItem";
	public static String AMT = "notedAmount";
	public static String DMG = "notedDamage";
	public StackUtils(){
	}
	
	
	public NBTTagCompound createNotedNbt(int amt, String name, int damage){
		NBTTagCompound tag = new NBTTagCompound();
		
		tag.setInteger(AMT, amt);
		tag.setString(ID, name);
		//tag.setInteger(DMG, damage);
		
		return null;
	}
	

}
