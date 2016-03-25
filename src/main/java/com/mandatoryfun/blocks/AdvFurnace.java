package com.wester_west.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class AdvFurnace extends BlockContainer
{
	@SideOnly(Side.CLIENT)
	public static IIcon topIcon;
	
	@SideOnly(Side.CLIENT)
	public static IIcon sideIcon;
	
	@SideOnly(Side.CLIENT)
	public static IIcon frontIcon;

	public AdvFurnace(int i,Material p_i45386_1_) {
		super(p_i45386_1_);
		this.setBlockName("AdvFurnace");
		this.setBlockTextureName("advancedmetalurgy:AdvFurnace");
		this.setHarvestLevel("pickaxe", 1);
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		
		
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconregister)
	{
		topIcon = iconregister.registerIcon("advancedmetalurgy:AdvFurnaceTop");
		sideIcon = iconregister.registerIcon("advancedmetalurgy:AdvFurnaceSide");
		frontIcon = iconregister.registerIcon("advancedmetalurgy:AdvFurnaceFront");
	}
	
	//@Override
	
}
