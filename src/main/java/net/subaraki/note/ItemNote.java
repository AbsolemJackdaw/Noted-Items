package net.subaraki.note;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNote extends Item {

	//item stored
	//number of items
	public ItemNote() {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);

		if(par1ItemStack.hasTagCompound())
			par3List.add("Note worth : " + par1ItemStack.stackTagCompound.getInteger(StackUtils.AMT) +" "+
					par1ItemStack.stackTagCompound.getString(StackUtils.ID));

		if(par1ItemStack.hasTagCompound())
			par3List.add("Item Damaged ? " + 
					(par1ItemStack.stackTagCompound.getInteger(StackUtils.DMG) > 0 ? "Yes," + " " +
							par1ItemStack.stackTagCompound.getInteger(StackUtils.DMG)	
							: "No"));
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ) {

		if(world.getBlock(x, y, z).equals(Blocks.anvil)){
			if(stack.hasTagCompound()){
				ItemStack st = new ItemStack(Item.getItemById(stack.getTagCompound().getByte(StackUtils.ITM)));
				st.stackSize = stack.getTagCompound().getInteger(StackUtils.AMT);
				st.setItemDamage(stack.getTagCompound().getInteger(StackUtils.DMG));
			}
			return true;
		}

		return false;
	}

}
