package com.wester_west.blocks;

import com.wester_west.creativetabs.MCreativeTabs;
import com.wester_west.lib.RefStrings;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;

public class MBlocks 
{
	public static void mainRegistry()
	{
		initializeItem();
		registerItem();
	}
	
	public static Block CompStone;
	public static Block MagnetiteOre;
	public static Block LimoniteOre;
	public static Block HematiteOre;
	public static Block AdvFurnace;
	public static Block Basalt;
	
	public static void initializeItem()
	{
		CompStone = new CompStone(Material.ground).setBlockName("CompStone").setCreativeTab(MCreativeTabs.tabBlock).setBlockTextureName(RefStrings.MODID + ":CompStone");
		MagnetiteOre = new MagnetiteOre(Material.ground).setBlockName("MagnetiteOre").setCreativeTab(MCreativeTabs.tabBlock).setBlockTextureName(RefStrings.MODID + ":MagnetiteOre");
		LimoniteOre = new LimoniteOre(Material.ground).setBlockName("LimoniteOre").setCreativeTab(MCreativeTabs.tabBlock).setBlockTextureName(RefStrings.MODID + ":LimoniteOre3");
		HematiteOre = new HematiteOre(Material.ground).setBlockName("HematiteOre").setCreativeTab(MCreativeTabs.tabBlock).setBlockTextureName(RefStrings.MODID + ":HematiteOre3");
		Basalt = new Basalt(Material.ground).setBlockName("Basalt").setCreativeTab(MCreativeTabs.tabBlock).setBlockTextureName(RefStrings.MODID + ":Basalt");		
	}
	
	public static void registerItem()
	{
		GameRegistry.registerBlock(Basalt, Basalt.getUnlocalizedName());
		GameRegistry.registerBlock(CompStone, CompStone.getUnlocalizedName());
		GameRegistry.registerBlock(MagnetiteOre, MagnetiteOre.getUnlocalizedName());
		GameRegistry.registerBlock(LimoniteOre, LimoniteOre.getUnlocalizedName());
		GameRegistry.registerBlock(HematiteOre, HematiteOre.getUnlocalizedName());
	}
}
