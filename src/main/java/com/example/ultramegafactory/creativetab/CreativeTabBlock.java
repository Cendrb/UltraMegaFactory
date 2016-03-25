package com.example.ultramegafactory.creativetab;

import com.example.ultramegafactory.init.ModBlocks;

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
		return Item.getItemFromBlock(ModBlocks.MagnetiteOre);
	}

}
