package net.subaraki.note.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.subaraki.note.StackUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNote extends Item {

	private IIcon blockicon;
	private IIcon emptyIcon;

	public ItemNote() {
		maxStackSize = 16;
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World,
			Entity par3Entity, int par4, boolean par5) {
		super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);

		if(par1ItemStack.stackSize > 1)
			if(par1ItemStack.hasTagCompound())
				if(par1ItemStack.getTagCompound().hasKey(StackUtils.ID))
					par1ItemStack.getItem().setMaxStackSize(1);
	}

	@Override
	public String getItemStackDisplayName(ItemStack par1ItemStack) {

		String s = "";

		if(par1ItemStack.hasTagCompound())
			s= par1ItemStack.getTagCompound().getString(StackUtils.ID);

		return s.length() > 0 ?  I18n.format("noted.item") + " " + s : super.getItemStackDisplayName(par1ItemStack) + s ;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}


	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		blockicon = par1IconRegister.registerIcon("noteditems:block");
		emptyIcon = par1IconRegister.registerIcon("noteditems:empty");
		itemIcon = par1IconRegister.registerIcon("map_empty");
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass) {

		Item i = null;
		Block b = null;
		if(stack.hasTagCompound()){
			i = Item.getItemById(stack.getTagCompound().getShort(StackUtils.ITM));
			b = Block.getBlockById(stack.getTagCompound().getShort(StackUtils.ITM));
		}

		IIcon icon = emptyIcon;

		if(pass > 0){
			if((b != null) || (i != null))
				if(b instanceof BlockAir){ //if the stack has an item, the blockid will/should return a blockair
					if(i != null)
						icon = i.getIconFromDamageForRenderPass(stack.getTagCompound().getInteger(StackUtils.DMG), pass -1);
				}else
					icon = blockicon;
		}else
			icon = super.getIcon(stack, 0);

		if (icon == null)
			icon = emptyIcon;


		return icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);

		if(par1ItemStack.hasTagCompound()){
			ItemStack s = new ItemStack(Item.getItemById(par1ItemStack.stackTagCompound.getInteger(StackUtils.ITM)));
			par3List.add("Note worth : " + par1ItemStack.stackTagCompound.getInteger(StackUtils.AMT) +" "+ s.getDisplayName()
					);
		}

		if(par1ItemStack.hasTagCompound())
			par3List.add("Item Damaged ? " +
					(par1ItemStack.stackTagCompound.getInteger(StackUtils.DMG) > 0 ? "Yes," + " " +
							par1ItemStack.stackTagCompound.getInteger(StackUtils.DMG)
							: "No"));
	}

	@Override
	public int getRenderPasses(int metadata) {
		return 5;
	}
}
