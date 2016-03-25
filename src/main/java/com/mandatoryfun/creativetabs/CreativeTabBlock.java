package com.wester_west.creativetabs;

import com.wester_west.blocks.MBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabBlock extends CreativeTabs
{

	public CreativeTabBlock(String arg0)
	{
		super(arg0);
	}

	@Override
	public Item getTabIconItem()
	{
		return Item.getItemFromBlock(MBlocks.MagnetiteOre);
	}

}
