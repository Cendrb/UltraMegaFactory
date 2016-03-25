package com.example.ultramegafactory.creativetabs;

import com.example.ultramegafactory.init.ModItems;

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
		return ModItems.oStick;
	}
	
}
