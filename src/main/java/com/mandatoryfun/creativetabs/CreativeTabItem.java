package com.wester_west.creativetabs;

import com.wester_west.item.MItems;

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
		return MItems.oStick;
	}

}
