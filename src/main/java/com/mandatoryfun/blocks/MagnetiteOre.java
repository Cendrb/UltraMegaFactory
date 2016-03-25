package com.wester_west.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class MagnetiteOre extends Block
{

	protected MagnetiteOre(Material arg0) {
		super(arg0);
		this.setHarvestLevel("pickaxe", 2);
	}
	
}
