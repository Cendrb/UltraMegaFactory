package com.wester_west.creativetabs;

import com.wester_west.item.MItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabTool extends CreativeTabs
{

	public CreativeTabTool(String string)
	{
		super(string);
	}

	@Override
	public Item getTabIconItem() 
	{
		return MItems.oStick;
	}
	
}
