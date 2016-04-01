package com.mandatoryfun.ultramegafactory.init;

import com.mandatoryfun.ultramegafactory.init.recipe.BlastFurnaceRecipe;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

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
}
