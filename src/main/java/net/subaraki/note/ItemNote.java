package net.subaraki.note;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNote extends Item {

	private IIcon blockicon;

	//item stored
	//number of items
	public ItemNote() {
		maxStackSize = 6;
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

		IIcon icon = null;
		if(b != null || i != null){
			if(b instanceof BlockAir){ //if the stack has an item, the blockid will/should return a blockair
				if( i != null){
					icon = i.getIconFromDamageForRenderPass(stack.getTagCompound().getInteger(StackUtils.DMG), pass);
				}else
					icon = super.getIcon(stack, 0);
			}else{
				icon = blockicon;
			}
		}else{
			icon = super.getIcon(stack, 0);
		}

		return pass == 0 ? super.getIcon(stack, 0) : icon;
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
	public boolean onItemUse(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ) {

		if(world.getBlock(x, y, z).equals(Blocks.anvil)){

			if(stack.hasTagCompound()){
				Item item = Item.getItemById(stack.getTagCompound().getShort(StackUtils.ITM));

				ItemStack st = new ItemStack(item);
				st.stackSize = stack.getTagCompound().getInteger(StackUtils.AMT);
				st.setItemDamage(stack.getTagCompound().getInteger(StackUtils.DMG));
				EntityItem ei = new EntityItem(world, x, y, z, st);

				if(!world.isRemote)
					world.spawnEntityInWorld(ei);

				player.setCurrentItemOrArmor(0, new ItemStack(Notes.note));
				return true;
			}
		}
		return false;
	}

	@Override
	public int getRenderPasses(int metadata) {
		return 3;
	}
}
