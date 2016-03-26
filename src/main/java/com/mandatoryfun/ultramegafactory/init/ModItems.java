package com.mandatoryfun.ultramegafactory.init;

import com.mandatoryfun.ultramegafactory.item.ItemGeneric;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModItems {

    public static ItemGeneric johnCena;

    public static void init() {
        johnCena = register("john_cena");
    }

    private static ItemGeneric register(String unlocalizedName) {
        ItemGeneric item;
        GameRegistry.registerItem(item = new ItemGeneric(unlocalizedName), unlocalizedName);
        return item;
    }
}
