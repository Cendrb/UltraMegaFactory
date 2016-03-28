package com.mandatoryfun.ultramegafactory.init;

import com.mandatoryfun.ultramegafactory.init.recipe.BlastFurnaceRecipe;

import java.util.ArrayList;

/**
 * Created by cendr_000 on 28.03.2016.
 */
public class Recipes {
    static ArrayList<BlastFurnaceRecipe> blastFurnaceRecipes = new ArrayList<BlastFurnaceRecipe>();
    public static void init()
    {
        // charcoal 9.7MJ
        // lignite 16.4MJ
        // bitumen 24.5MJ
        // carbon 27.5MJ

        int requiredTemperature = 1560;
        blastFurnaceRecipes.add(new BlastFurnaceRecipe(ModBlocks.Ore.magnetite, 3000, 4800, requiredTemperature, 1, 0.2f, 2.5f));
        blastFurnaceRecipes.add(new BlastFurnaceRecipe(ModBlocks.Ore.hematite, 3000, 7200, requiredTemperature, 1, 0.2f, 2));
        blastFurnaceRecipes.add(new BlastFurnaceRecipe(ModBlocks.Ore.limonite, 3000, 7200, requiredTemperature, 1, 0.2f, 1f));
        blastFurnaceRecipes.add(new BlastFurnaceRecipe(ModBlocks.Ore.siderite, 4000, 7200, requiredTemperature, 1, 0.2f, 1.5f));
    }
}
