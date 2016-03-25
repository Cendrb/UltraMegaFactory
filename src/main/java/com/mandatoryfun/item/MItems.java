package com.wester_west.item;

import com.wester_west.creativetabs.MCreativeTabs;
import com.wester_west.lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MItems
{
	public static void mainRegistry()
	{
		initializeItem();
		registerItem();
	}
	
	public static Item oStick;
	
	public static void initializeItem()
	{
		oStick = new Item().setUnlocalizedName("oStick").setCreativeTab(MCreativeTabs.tabItems).setTextureName(RefStrings.MODID + ":oStick");
	}
	
	public static void registerItem()
	{
		GameRegistry.registerItem(oStick, oStick.getUnlocalizedName());
	}
}
