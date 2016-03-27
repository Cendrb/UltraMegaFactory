package com.mandatoryfun.ultramegafactory.init;

import com.mandatoryfun.ultramegafactory.block.BlockGeneric;
import com.mandatoryfun.ultramegafactory.block.BlockGenericOre;
import com.mandatoryfun.ultramegafactory.block.ItemBlockGenericOre;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
    public static class Ore {
        public static BlockGenericOre magnetite;
        public static BlockGenericOre hematite;
        public static BlockGenericOre siderite;
        public static BlockGenericOre pyrite;

        public static BlockGenericOre chalcopyrite;
        public static BlockGenericOre cuprite;
        public static BlockGenericOre tetrahedrite;

        public static BlockGenericOre cassiterite;
        public static BlockGenericOre teallite;

        public static BlockGenericOre galena;

        public static BlockGenericOre sphalerite;

        public static BlockGenericOre bauxite;
        public static BlockGenericOre kaolinite;

        public static BlockGenericOre lignite;
        public static BlockGenericOre bituminousCoal;
        public static BlockGenericOre peat;

        public static BlockGenericOre limestone;
    }

    public static void init() {
        // add calcite?
        // add gems ruby Al2O3Cr - source of chromium, sapphire
        // which to put into the nether? - sulfur-ish ones?
        // add end ores

        Ore.magnetite = registerOre("magnetite_ore", "Fe\u2083O\u2084", constructArray("Iron ore", "The best one out there", "Can be found under seas and beaches"), 3, 1);
        Ore.hematite = registerOre("hematite_ore", "Fe\u2082O\u2083", constructArray("Iron ore", "Only slightly worse than magnetite", "Can be found under seas and beaches"), 3, 1);
        Ore.siderite = registerOre("siderite_ore", "FeCO\u2083", constructArray("Iron ore", "Much more energy consumptive than magnetite and hematite", "Can be found everywhere! YaY!"), 3, 1);
        Ore.pyrite = registerOre("pyrite_ore", "FeS\u2082", constructArray("Iron sulfide", "Mainly used to create sulfuric acid", "Can be found together with gold ore/sphalerite", "beware looks kinda similar"), 3, 1);

        Ore.chalcopyrite = registerOre("chalcopyrite_ore", "CuFeS\u2082", constructArray("Major copper ore", "Can be found everywhere together with pyrite"), 3, 1);
        Ore.cuprite = registerOre("cuprite_ore", "Cu\u2082O", constructArray("Minor copper ore", "Can be found under savannas"), 3, 1);
        Ore.tetrahedrite = registerOre("tetrahedrite_ore", "Fe\u2081\u2082Sb\u2084S\u2081\u2083", constructArray("Minor copper ore, source of antimony", "Can be found everywhere"), 3, 1);

        Ore.cassiterite = registerOre("cassiterite_ore", "SnO\u2082", constructArray("Major tin ore", "Can be found in exotic jungle places"), 3, 1);
        Ore.teallite = registerOre("teallite_ore", "PbSnS\u2082", constructArray("Minor tin ore", "Can be found everywhere"), 3, 1);

        Ore.galena = registerOre("galena_ore", "PbS", constructArray("Major lead ore, source of silver", "Toxic - Do not eat or drink!", "Can be found with sphalerite in mountain massifs"), 3, 1);

        Ore.sphalerite = registerOre("sphalerite_ore", "ZnS", constructArray("Major zinc ore", "Can be found with galena in mountain massifs"), 3, 1);

        Ore.bauxite = registerOre("bauxite_ore", "Al(OH)\u2083", constructArray("Major aluminium ore, source of titanium", "Can be found below savannas, plains and jungles"), 3, 1);
        Ore.kaolinite = registerOre("kaolinite_ore", "Al\u2082Si\u2082O\u2085(OH)\u2084", constructArray(""), 3, 1);

        Ore.lignite = registerOre("lignite", "brown coal", constructArray("Less power effective than bitumen", "Can be found under swamps"), 3, 1);
        Ore.bituminousCoal = registerOre("bituminous_coal", "black coal", constructArray("More power effective than lignite", "Can be found on the bottom of the Minecraft world"), 3, 1);
        Ore.peat = registerOre("peat", "", constructArray("PEAT!!!"), 3, 0);

        Ore.limestone = registerOre("limestone", "CaCO\u2083", constructArray("Used to make lime", "Not an actual ore ;-)"), 3, 0);

    }

    private static String[] constructArray(String... strings) {
        return strings;
    }

    private static BlockGeneric register(BlockGeneric block) {
        GameRegistry.registerBlock(block, block.getPureName());
        return block;
    }

    private static BlockGenericOre registerOre(String unlocalizedName, String formula, String[] description, float hardness, int toolLevel) {
        BlockGenericOre block;
        GameRegistry.registerBlock(block = new BlockGenericOre(unlocalizedName, Material.rock, hardness, 15, "pickaxe", toolLevel, formula, description), ItemBlockGenericOre.class, block.getPureName());
        return block;
    }
}
