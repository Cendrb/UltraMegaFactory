package com.mandatoryfun.ultramegafactory.init;

import com.mandatoryfun.ultramegafactory.item.ItemGeneric;
import com.mandatoryfun.ultramegafactory.item.ItemIngotGeneric;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public final class ModItems {

    public static ItemGeneric johnCena;

    public static void init()
    {

    }

    private static ItemGeneric register(String unlocalizedName) {
        ItemGeneric item;
        GameRegistry.registerItem(item = new ItemGeneric(unlocalizedName), unlocalizedName);
        return item;
    }

    private static ItemIngotGeneric register(String unlocalizedName, String formula) {
        ItemIngotGeneric item;
        GameRegistry.registerItem(item = new ItemIngotGeneric(unlocalizedName, formula), unlocalizedName);
        return item;
    }
}
