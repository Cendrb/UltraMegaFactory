package com.mandatoryfun.ultramegafactory.init;

import com.mandatoryfun.ultramegafactory.block.BlockGeneric;
import com.mandatoryfun.ultramegafactory.block.BlockGenericOre;
import com.mandatoryfun.ultramegafactory.block.ItemBlockGeneric;
import com.mandatoryfun.ultramegafactory.block.ItemBlockGenericOre;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
    public static class Ore {
        public static BlockGenericOre magnetite;
        public static BlockGenericOre hematite;
        public static BlockGenericOre siderite;
        public static BlockGenericOre pyrite;

        public static BlockGenericOre malachite;
        public static BlockGenericOre chalcopyrite;
        public static BlockGenericOre cuprite;
        public static BlockGenericOre tetrahedrite;

        public static BlockGenericOre cassiterite;
        public static BlockGenericOre teallite;

        public static BlockGenericOre galenite;

        public static BlockGenericOre lignite;
        public static BlockGenericOre bituminousCoal;

        public static BlockGenericOre limestone;
    }


    public static void init() {
        Ore.magnetite = registerOre("magnetite_ore", "Fe\u2083O\u2084", "", 3, 1);
        Ore.hematite = registerOre("hematite_ore", "Fe\u2082O\u2083", "", 3, 1);
        Ore.siderite = registerOre("siderite_ore", "FeCO\u2083", "", 3, 1);
        Ore.pyrite = registerOre("pyrite_ore", "FeS\u2082", "", 3, 1);

        Ore.malachite = registerOre("malachite_ore", "Cu\u2082CO\u2083(OH)\u2082", "", 3, 1);
        Ore.chalcopyrite = registerOre("chalcopyrite_ore", "CuFeS\u2082", "", 3, 1);
        Ore.cuprite = registerOre("cuprite_ore", "Cu\u2082O", "", 3, 1);
        Ore.tetrahedrite = registerOre("tetrahedrite_ore", "Fe\u2081\u2082Sb\u2084S\u2081\u2083", "", 3, 1);

        Ore.cassiterite = registerOre("cassiterite_ore", "SnO\u2082", "", 3, 1);
        Ore.teallite = registerOre("teallite_ore", "PbSnS\u2082", "", 3, 1);

        Ore.galenite = registerOre("galenite_ore", "PbS", "", 3, 1);

        Ore.lignite = registerOre("lignite", "brown coal", "", 3, 1);
        Ore.bituminousCoal = registerOre("bituminous_coal", "black coal", "", 3, 1);

        Ore.limestone = registerOre("limestone", "CaCO\u2083", "", 3, 0);

    }

    private static BlockGeneric register(BlockGeneric block)
    {
        GameRegistry.registerBlock(block, block.getPureName());
        return block;
    }
    private static BlockGenericOre registerOre(String unlocalizedName, String formula, String description, float hardness, int toolLevel)
    {
        BlockGenericOre block;
        GameRegistry.registerBlock(block = new BlockGenericOre(unlocalizedName, Material.rock, hardness, 15, "pickaxe", toolLevel, formula, description), ItemBlockGenericOre.class, block.getPureName());
        return block;
    }
}
