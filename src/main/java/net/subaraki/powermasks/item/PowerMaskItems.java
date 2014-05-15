package net.subaraki.powermasks.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class PowerMaskItems {

	public static ItemWraithMask sandWraitMask;
	public static final ArmorMaterial wraithMask = EnumHelper.addArmorMaterial("wraithMask", 0, new int[]{0,0,0,0}, 0);
	public static ItemStoneMask stoneMask;
	public static final ArmorMaterial stone = EnumHelper.addArmorMaterial("stoneMask", 0, new int[]{0,0,0,0}, 0);

	public PowerMaskItems() {

		initItems();
		registerItems();
		
	}

	private void registerItems() {
		GameRegistry.registerItem(sandWraitMask, "wraithMask", "powermasks");
		GameRegistry.registerItem(stoneMask, "stoneMask", "powermasks");

	}

	private void initItems() {
		sandWraitMask = (ItemWraithMask) new ItemWraithMask(wraithMask, 0, 0, "powermasks:armor/wraithMask.png").setUnlocalizedName("wraithMask");
		stoneMask = (ItemStoneMask) new ItemStoneMask(stone, 0, 0, "powermasks:armor/wraithMask.png").setUnlocalizedName("stoneMask");

	}

}
