package com.example.ultramegafactory.creativetab;

import com.example.ultramegafactory.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabItem extends CreativeTabs
{

	public CreativeTabItem(String arg0)
	{
		super(arg0);
	}

	@Override
	public Item getTabIconItem()
	{
		return ModItems.oStick;
	}

}
