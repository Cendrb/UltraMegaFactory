package com.mandatoryfun.ultramegafactory.init;

import com.mandatoryfun.ultramegafactory.block.*;
import com.mandatoryfun.ultramegafactory.block.machinery.blast_furnace.*;
import com.mandatoryfun.ultramegafactory.block.itemblock.ItemBlockMultipleNames;
import com.mandatoryfun.ultramegafactory.block.machinery.BlockBurningHeater;
import com.mandatoryfun.ultramegafactory.block.machinery.BlockElectricHeater;
import com.mandatoryfun.ultramegafactory.lib.UMFLogger;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

public class ModBlocks {
    private static ArrayList<Block> allBlocks = new ArrayList<Block>();
    private static ArrayList<ItemBlock> allItemBlocks = new ArrayList<ItemBlock>();

    public static class Ore {
        public static BlockGenericOre magnetite;
        public static BlockGenericOre hematite;
        public static BlockGenericOre siderite;
        public static BlockGenericOre limonite;
        public static BlockGenericOre pyrite;

        public static BlockGenericOre chalcopyrite;
        public static BlockGenericOre cuprite;
        public static BlockGenericOre tetrahedrite;

        public static BlockGenericOre cassiterite;
        public static BlockGenericOre teallite;

        public static BlockGenericOre galena;

        public static BlockGenericOre sphalerite;

        public static BlockGenericOre bauxite;

        public static BlockGenericOre lignite;
        public static BlockGenericOre bituminousCoal;
        public static BlockGenericOre peat_ore;

        public static BlockGenericOre limestone;
    }

    public static BlockKaolineOre kaolineOre;

    public static BlockBlastFurnaceController blastFurnaceController;
    public static BlockBlastFurnaceCasing blastFurnaceCasing;
    public static BlockBurningHeater blastFurnaceBurningHeater;
    public static BlockElectricHeater blastFurnaceElectricHeater;

    public static void init() {
        // add gems ruby Al2O3Cr - source of chromium, sapphire
        // which to put into the nether? - sulfur-ish ones?
        // add end ores

        Ore.magnetite = registerOre("magnetite_ore", "Fe\u2083O\u2084", constructArray("Iron ore 72% Fe", "The best one out there", "Can be found under seas and beaches"), 3, 1);
        Ore.hematite = registerOre("hematite_ore", "Fe\u2082O\u2083", constructArray("Iron ore 70% Fe", "Only slightly worse than magnetite", "Can be found under seas and beaches"), 3, 1);
        Ore.limonite = registerOre("limonite_ore", "FeO(OH).nH\u2082O", constructArray("Iron ore 60% Fe", "A bit worse than hematite", "Can be found everywhere"), 3, 1);
        Ore.siderite = registerOre("siderite_ore", "FeCO\u2083", constructArray("Iron ore 48% Fe", "Much more energy consumptive than magnetite, hematite and limonite", "Can be found everywhere! YaY!"), 3, 1);

        Ore.pyrite = registerOre("pyrite_ore", "FeS\u2082", constructArray("Iron sulfide", "Mainly used to create sulfuric acid", "Can be found together with gold ore/sphalerite", "beware looks kinda similar"), 3, 1);

        Ore.chalcopyrite = registerOre("chalcopyrite_ore", "CuFeS\u2082", constructArray("Major copper ore", "Can be found everywhere together with pyrite"), 3, 1);
        Ore.cuprite = registerOre("cuprite_ore", "Cu\u2082O", constructArray("Minor copper ore", "Can be found under savannas"), 3, 1);
        Ore.tetrahedrite = registerOre("tetrahedrite_ore", "Fe\u2081\u2082Sb\u2084S\u2081\u2083", constructArray("Minor copper ore, source of antimony", "Founding it is hell"), 3, 1);

        Ore.cassiterite = registerOre("cassiterite_ore", "SnO\u2082", constructArray("Major tin ore", "Can be found in exotic jungle places"), 3, 1);
        Ore.teallite = registerOre("teallite_ore", "PbSnS\u2082", constructArray("Minor tin ore", "Can be found everywhere"), 3, 1);

        Ore.galena = registerOre("galena_ore", "PbS.nAg", constructArray("Major lead ore, source of silver", "Toxic - Do not eat or drink!", "Can be found with sphalerite in mountain massifs"), 3, 1);

        Ore.sphalerite = registerOre("sphalerite_ore", "ZnS", constructArray("Major zinc ore", "Can be found with galena in mountain massifs"), 3, 1);

        Ore.bauxite = registerOre("bauxite_ore", "Al(OH)\u2083", constructArray("Major aluminium ore, source of titanium", "Can be found below savannas, plains and jungles"), 3, 1);

        Ore.lignite = registerOre("lignite_ore", "brown coal", constructArray("Less power effective than bitumen", "Can be found under swamps"), 3, 1);
        Ore.bituminousCoal = registerOre("bitumen_ore", "bituminous coal/black coal", constructArray("More power effective than lignite", "Can be found at the bottom of the Minecraft world"), 3, 1);
        Ore.peat_ore = registerWithItem(new BlockPeatOre(constructArray("You should better have silktouch")));

        Ore.limestone = registerOre("limestone", "CaCO\u2083", constructArray("Used to make lime", "Not an actual ore ;-)"), 3, 0);


        // kaoline
        kaolineOre = registerWithItem(new BlockKaolineOre());

        // blast furnace
        blastFurnaceController = registerWithItem(new BlockBlastFurnaceController(), ItemBlockMultipleNames.class);
        blastFurnaceCasing = registerWithItem(new BlockBlastFurnaceCasing(), ItemBlockMultipleNames.class);
        blastFurnaceBurningHeater = registerWithItem(new BlockBurningHeater(), ItemBlockMultipleNames.class);
        blastFurnaceElectricHeater = registerWithItem(new BlockElectricHeater(), ItemBlockMultipleNames.class);
    }

    private static String[] constructArray(String... strings) {
        return strings;
    }

    private static BlockGenericOre registerOre(String unlocalizedName, String formula, String[] description, float hardness, int toolLevel) {
        BlockGenericOre block;
        registerWithItem(block = new BlockGenericOre(unlocalizedName, Material.rock, hardness, 15, "pickaxe", toolLevel, formula, description));
        return block;
    }

    private static <T extends Block>T registerBlock(T block)
    {
        GameRegistry.register(block);
        allBlocks.add(block);
        return block;
    }

    private static <T extends Block>T registerWithItem(T block) {
        registerBlock(block);
        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setUnlocalizedName(block.getUnlocalizedName());
        itemBlock.setRegistryName(block.getRegistryName());
        GameRegistry.register(itemBlock);
        allItemBlocks.add(itemBlock);
        return block;
    }

    private static <T extends Block>T registerWithItem(T block, Class<? extends ItemBlock> itemBlockClass) {
        registerBlock(block);
        try {
            ItemBlock itemBlock;
            itemBlock = (ItemBlock) itemBlockClass.getConstructors()[0].newInstance(block);
            itemBlock.setUnlocalizedName(block.getUnlocalizedName());
            itemBlock.setRegistryName(block.getRegistryName());
            GameRegistry.register(itemBlock);
            allItemBlocks.add(itemBlock);
        } catch (Exception e) {
            UMFLogger.logError("Unable to instantiate ItemBlock class " + itemBlockClass.toString());
            e.printStackTrace();
            throw new IllegalArgumentException("Supplied ItemBlock class is not supported", e);
        }

        return block;
    }

    public static ArrayList<Block> getAllBlocks() {
        return allBlocks;
    }
}
