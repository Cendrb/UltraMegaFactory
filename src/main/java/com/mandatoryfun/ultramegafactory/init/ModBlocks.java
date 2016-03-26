package com.mandatoryfun.ultramegafactory.init;

import com.mandatoryfun.ultramegafactory.block.BlockGeneric;
import com.mandatoryfun.ultramegafactory.block.BlockGenericOre;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
    public static BlockGeneric magnetiteOre;

    public static void init() {
        magnetiteOre = register(new BlockGenericOre("magnetite_ore", "Fe3O4"));
    }

    private static BlockGeneric register(BlockGeneric block)
    {
        GameRegistry.registerBlock(block, block.getPureName());
        return block;
    }
}
