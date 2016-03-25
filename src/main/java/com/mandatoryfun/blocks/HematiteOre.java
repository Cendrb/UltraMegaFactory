package com.wester_west.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class HematiteOre extends Block
{

	protected HematiteOre(Material arg0)
	{
		super(arg0);
		this.setHarvestLevel("pickaxe", 2);
	}
	
}
