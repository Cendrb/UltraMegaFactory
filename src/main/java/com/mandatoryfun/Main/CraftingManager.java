package com.wester_west.Main;

import com.example.ultramegafactory.init.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.example.ultramegafactory.init.ModItems;

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
			GameRegistry.addRecipe(new ItemStack(ModBlocks.CompStone, 1), new Object[]{"XXX","XXX","XXX", 'X', Blocks.stone});
			
			//Obsidian stick
			GameRegistry.addShapedRecipe(new ItemStack(ModItems.oStick,1), new Object[]{" X ", " X ", 'X', Blocks.obsidian});
		//Shapeless
			//GameRegistry.addShapelessRecipe(new ItemStack(Items.diamond_hoe, 1), new Object[]{"SCO", 'S', Blocks.stone, 'C', ModBlocks.CompStone, 'O', Blocks.obsidian});
	}
	
	public static void addSmeltingRec()
	{
		GameRegistry.addSmelting(Blocks.coal_block, new ItemStack(Blocks.obsidian), 20.0f);
	}
}
