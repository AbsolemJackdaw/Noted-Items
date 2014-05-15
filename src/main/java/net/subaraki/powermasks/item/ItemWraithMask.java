package net.subaraki.powermasks.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWraithMask extends ItemPowerMask {

	public ItemWraithMask(ArmorMaterial mat, int i, int b, String s) {
		super(mat, i, b, s);
	}
	
	@Override
	public IIcon getIcon(ItemStack stack, int pass) {

		return itemIcon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {

		itemIcon = par1IconRegister.registerIcon("powermasks:wraithMask");
	
	}
}
