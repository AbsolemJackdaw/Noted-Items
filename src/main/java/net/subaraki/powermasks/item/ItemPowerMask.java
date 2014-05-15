package net.subaraki.powermasks.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemPowerMask extends ItemArmor{

	String texture;

	public ItemPowerMask(ArmorMaterial mat, int i,
			int b, String s) {
		super(mat, i, b);
		
		texture = s;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			String type) {

		if(slot == 0){
			return texture;
		}
		
		return super.getArmorTexture(stack, entity, slot, type);
	}
	
	public ResourceLocation getSkin(){
		return null;
	}
}
