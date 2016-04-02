package com.mandatoryfun.ultramegafactory.init;

import com.mandatoryfun.ultramegafactory.init.recipe.BlastFurnaceRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cendr_000 on 31.03.2016.
 */
public class UMFRecipes {
    public static class BlastFurnace {
        static ArrayList<BlastFurnaceRecipe> blastFurnaceRecipes = new ArrayList<BlastFurnaceRecipe>();
        static HashMap<Item, Float> reducingAgents = new HashMap<Item, Float>();
        static HashMap<Item, Float> bullshitCreators = new HashMap<Item, Float>();

        public static void init() {
            int requiredTemperature = 1560;
            blastFurnaceRecipes.add(new BlastFurnaceRecipe(ModBlocks.Ore.magnetite, 3000, 4800, requiredTemperature, 1, 0.2f, 2.5f));
            blastFurnaceRecipes.add(new BlastFurnaceRecipe(ModBlocks.Ore.hematite, 3000, 7200, requiredTemperature, 1, 0.2f, 2));
            blastFurnaceRecipes.add(new BlastFurnaceRecipe(ModBlocks.Ore.limonite, 3000, 7200, requiredTemperature, 1, 0.2f, 1f));
            blastFurnaceRecipes.add(new BlastFurnaceRecipe(ModBlocks.Ore.siderite, 4000, 7200, requiredTemperature, 1, 0.2f, 1.5f));

            reducingAgents.put(ModItems.bitumen, 1.0f);
            reducingAgents.put(ModItems.carbon, 1.5f);
        }

        public static boolean isValidOre(Item item) {
            for (BlastFurnaceRecipe recipe : blastFurnaceRecipes)
                if (Item.getItemFromBlock(recipe.getOre()) == item)
                    return true;
            return false;
        }

        public static boolean isReducingAgent(Item item) {
            return reducingAgents.containsKey(item);
        }

        public static boolean isBullshitCreator(Item item) {
            return bullshitCreators.containsKey(item);
        }

        public static float getReducingAgentValue(Item item) {
            return reducingAgents.get(item);
        }

        public static float getBullshitCreatorValue(Item item) {
            return bullshitCreators.get(item);
        }
    }

    public static class CraftingManager {
        public static void init() {
            addCraftingRec();
            addSmeltingRec();
        }

        public static void addCraftingRec() {
            //Shaped
            //Compressed stone
            GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(ModBlocks.blastFurnaceController), 1, 0), "CGC", "BFB", "CCC", 'F', Blocks.furnace, 'C', Items.brick, 'G', Blocks.glass, 'B', Blocks.brick_block);
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.getItemFromBlock(ModBlocks.blastFurnaceController), 1, 1), "PIP", "IBI", "PIP", 'P', ModItems.porcelain, 'I', "ingotIronT4", 'B', new ItemStack(Item.getItemFromBlock(ModBlocks.blastFurnaceController), 1, 0)));

            //Shapeless

        }

        public static void addSmeltingRec() {
            GameRegistry.addSmelting(ModItems.Dust.kaoline, new ItemStack(ModItems.porcelain), 20.0f);
        }
    }

}
