package com.mandatoryfun.ultramegafactory.init;

import com.mandatoryfun.ultramegafactory.init.recipe.BlastFurnaceRecipe;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cendr_000 on 31.03.2016.
 */
public class UMFRegistry {
    public static class Fuels {

        static HashMap<Item, Integer> fuels = new HashMap<Item, Integer>();

        public static void init() {
            // charcoal 9.7MJ
            // lignite 16.4MJ
            // bitumen 24.5MJ
            // carbon 27.5MJ

            // register vanilla fuels
            registerFuel(Items.coal, 9700);
        }

        public static void registerFuel(Item item, int kJEnergyValue) {
            fuels.put(item, kJEnergyValue);
        }

        public static int getKJEnergyValue(Item item) {
            return fuels.get(item);
        }

        public static boolean isFuel(Item item) {
            return fuels.containsKey(item);
        }
    }

    public static class OreDitionaryManager
    {
        public static void init()
        {
            // iron
            registerAllMetas("ingotIron", ModItems.Ingot.iron, 1, 4);
            registerAllMetas("ingotIronT1", ModItems.Ingot.iron, 1, 4);
            registerAllMetas("ingotIronT2", ModItems.Ingot.iron, 1, 4);
            registerAllMetas("ingotIronT3", ModItems.Ingot.iron, 2, 4);
            registerAllMetas("ingotIronT4", ModItems.Ingot.iron, 3, 4);
            registerAllMetas("ingotIronT5", ModItems.Ingot.iron, 4, 4);
        }

        private static void register(String name, Item result, int meta)
        {
            OreDictionary.registerOre(name, new ItemStack(result, 1, meta));
        }
        private static void registerAllMetas(String name, Item result, int min, int max)
        {
            for(int meta = min; meta <= max; meta++)
            {
                register(name, result, meta);
            }
        }
    }
}
