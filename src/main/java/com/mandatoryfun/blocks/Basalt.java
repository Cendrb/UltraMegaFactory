package com.wester_west.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Basalt extends Block{

	protected Basalt(Material arg0)
	{
		super(arg0);
		this.setHarvestLevel("pickaxe", 3);
	}
}
