package com.mandatoryfun.ultramegafactory.init;

import com.mandatoryfun.ultramegafactory.block.BlockGeneric;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
    public static BlockGeneric magnetiteOre;

    public static void init() {
        magnetiteOre = register("magnetite_ore");
    }

    private static BlockGeneric register(String unlocalizedName)
    {
        BlockGeneric block;
        GameRegistry.registerBlock(block = new BlockGeneric(unlocalizedName), unlocalizedName);
        return block;
    }
}
