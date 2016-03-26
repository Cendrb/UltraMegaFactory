package com.mandatoryfun.ultramegafactory.init;

import com.mandatoryfun.ultramegafactory.block.BlockGeneric;
import com.mandatoryfun.ultramegafactory.block.BlockGenericOre;
import com.mandatoryfun.ultramegafactory.block.ItemBlockGeneric;
import com.mandatoryfun.ultramegafactory.block.ItemBlockGenericOre;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
    public static BlockGeneric magnetiteOre;
    public static BlockGeneric hematite;
    public static BlockGeneric limonite;
    public static BlockGeneric malachite;

    public static void init() {
        magnetiteOre = registerOre(new BlockGenericOre("magnetite_ore", "Fe3O4"));
    }

    private static BlockGeneric register(BlockGeneric block)
    {
        GameRegistry.registerBlock(block, block.getPureName());
        return block;
    }
    private static BlockGeneric registerOre(BlockGeneric block)
    {
        GameRegistry.registerBlock(block, ItemBlockGenericOre.class, block.getPureName());
        return block;
    }
}
