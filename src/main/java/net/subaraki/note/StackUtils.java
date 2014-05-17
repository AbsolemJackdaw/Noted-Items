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

	/**creates a new nbt tag with all requiered tags for a noted item
	 * @param amt stacksize
	 * @param name display name of the stack
	 * @param damage the damage of the item, meta or inflicted damage
	 * @param itemid the of the item stored
	 * */
	public NBTTagCompound createNotedNbt(int amt, String name, int damage, short itemid){
		NBTTagCompound tag = new NBTTagCompound();

		tag.setInteger(AMT, amt);
		tag.setString(ID, name);
		tag.setInteger(DMG, damage);
		tag.setShort(ITM, itemid);

		return tag;
	}

	/**merges two tag compounds.
	 * if the tagcompound is null, a new will be given
	 * if either one of both is null, the other one will be returned
	 * if both are not null, the amt, id, itm and dmg will be merged into a new nbt
	 * 
	 * no checks are made to check if the compound actually has one of the tags amt, id, itm or dmg
	 * */
	public NBTTagCompound fuseNbt(NBTTagCompound tag1, NBTTagCompound tag2) {
		NBTTagCompound tag = new NBTTagCompound();

		if((tag1 == null) && (tag2 == null)) {
			return tag;
		}

		if((tag1 == null) || tag1.hasNoTags()) {
			return tag2;
		}

		if((tag2 == null) || tag2.hasNoTags()) {
			return tag1;
		}

		tag.setInteger(AMT, tag1.getInteger(AMT) + tag2.getInteger(AMT));
		tag.setString(ID, tag1.getString(ID));
		tag.setInteger(DMG, tag1.getInteger(DMG));
		tag.setShort(ITM, tag1.getShort(ITM));

		return tag;
	}

	/**checks if the returning keys from both stacks are the same
	 * checks for damage, contained item, and display name
	 * */
	public boolean NBTAreEqual(NBTTagCompound tag1, NBTTagCompound tag2){

		if(tag1 == null) {
			return false;
		}
		if(tag2 == null) {
			return false;
		}

		if((tag1.getInteger(DMG) == tag2.getInteger(DMG)) &&
				(tag1.getShort(ITM) == tag2.getShort(ITM)) &&
				tag1.getString(ID).equals(tag2.getString(ID))) {
			return true;
		}

		return false;
	}

	/**checks of the stack matches the damage, displayname and item id stored in the tag*/
	public boolean itemEqualsNoteNBT(ItemStack stack, NBTTagCompound tag){

		if(tag.getString(ID).equals(stack.getDisplayName()) &&
				(tag.getInteger(DMG) == stack.getItemDamage()) &&
				(tag.getShort(ITM) == Item.getIdFromItem(stack.getItem()))) {
			return true;
		}

		return false;
	}

}
