package com.wester_west.Main;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.wester_west.blocks.MBlocks;
import com.wester_west.item.MItems;

import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingManager 
{
	public static void mainRegistry()
	{
		addCraftingRec();
		addSmeltingRec();
	}
	
	public static void addCraftingRec()
	{
		//Shaped
			//Compressed stone
			GameRegistry.addRecipe(new ItemStack(MBlocks.CompStone, 1), new Object[]{"XXX","XXX","XXX", 'X', Blocks.stone});
			
			//Obsidian stick
			GameRegistry.addShapedRecipe(new ItemStack(MItems.oStick,1), new Object[]{" X ", " X ", 'X', Blocks.obsidian});
		//Shapeless
			//GameRegistry.addShapelessRecipe(new ItemStack(Items.diamond_hoe, 1), new Object[]{"SCO", 'S', Blocks.stone, 'C', MBlocks.CompStone, 'O', Blocks.obsidian});
	}
	
	public static void addSmeltingRec()
	{
		GameRegistry.addSmelting(Blocks.coal_block, new ItemStack(Blocks.obsidian), 20.0f);
	}
}
