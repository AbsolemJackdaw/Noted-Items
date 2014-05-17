package net.subaraki.note.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.subaraki.note.Notes;
import net.subaraki.note.block.inventory.TileEntityNoteTable;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockNotingTable extends Block{

	public BlockNotingTable(Material mat) {
		super(mat);
		setCreativeTab(CreativeTabs.tabAllSearch);
	}

	@Override
	public boolean onBlockActivated(World world, int x,
			int y, int z, EntityPlayer player,
			int p_149727_6_, float p_149727_7_, float p_149727_8_,
			float p_149727_9_) {

		player.openGui(Notes.instance, 0, world, x, y, z);

		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y,
			int z, Block block, int meta) {

		TileEntityNoteTable te = (TileEntityNoteTable) world.getTileEntity(x, y, z);

		for(int i = 0; i < te.getSizeInventory(); i ++) {
			if(i != 10) {
				if(te.getStackInSlot(i)!=null){
					EntityItem ei = new EntityItem(world, x, y, z, te.getStackInSlot(i));
					if(!world.isRemote) {
						world.spawnEntityInWorld(ei);
					}
				}
			}
		}

		world.removeTileEntity(x, y, z);
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityNoteTable();
	}

	@Override
	public int getRenderType() {

		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public boolean isOpaqueCube() {

		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {

		return false;
	}


}
