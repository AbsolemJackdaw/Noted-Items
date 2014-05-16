package net.subaraki.note.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class NotingTable extends Block{

	public NotingTable(Material mat) {
		super(mat);
		setCreativeTab(CreativeTabs.tabAllSearch);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x,
			int y, int z, EntityPlayer player,
			int p_149727_6_, float p_149727_7_, float p_149727_8_,
			float p_149727_9_) {

		
		
		return true;
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
