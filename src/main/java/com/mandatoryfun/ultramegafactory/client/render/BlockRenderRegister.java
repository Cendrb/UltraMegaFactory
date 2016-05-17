package com.mandatoryfun.ultramegafactory.client.render;

import com.google.common.base.Predicate;
import com.mandatoryfun.ultramegafactory.block.IPureName;
import com.mandatoryfun.ultramegafactory.block.machinery.BlockGenericTier;
import com.mandatoryfun.ultramegafactory.init.ModBlocks;
import com.mandatoryfun.ultramegafactory.lib.IRegisterRender;
import com.mandatoryfun.ultramegafactory.lib.RefStrings;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nullable;

/**
 * Created by cendr_000 on 25.03.2016.
 */
public class BlockRenderRegister {

    public static void preInit() {
        //ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.blastFurnaceController), new ResourceLocation(RefStrings.MODID, "blast_furnace_controller_t1"), new ResourceLocation(RefStrings.MODID, "blast_furnace_controller_t2"));
        //ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.blastFurnaceCasing), new ResourceLocation(RefStrings.MODID, "blast_furnace_casing_t1"), new ResourceLocation(RefStrings.MODID, "blast_furnace_casing_t2"));
        //ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.blastFurnaceBurningHeater), new ResourceLocation(RefStrings.MODID, "burning_heater_t1"), new ResourceLocation(RefStrings.MODID, "burning_heater_t2"));
        //ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.blastFurnaceElectricHeater), new ResourceLocation(RefStrings.MODID, "electric_heater_t1"), new ResourceLocation(RefStrings.MODID, "electric_heater_t2"));
    }

    public static void init() {
        for (final Block blockGeneric : ModBlocks.getAllBlocks())
            if (((IRegisterRender) blockGeneric).getRegisterRender())
                register(blockGeneric);
            else if (blockGeneric instanceof BlockGenericTier) {
                ((BlockGenericTier)blockGeneric).runForEachTier(new Predicate<Integer>() {
                    @Override
                    public boolean apply(@Nullable Integer input) {
                        register(blockGeneric, input - 1, ((BlockGenericTier) blockGeneric).getNameForTier(input));
                        return true;
                    }
                });
            }
        register(ModBlocks.blastFurnaceController, 0, "blast_furnace_controller_t1");
        register(ModBlocks.blastFurnaceController, 1, "blast_furnace_controller_t2");
    }

    private static void register(Block block, int meta) {
        IPureName blockWithName = (IPureName) block;
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(blockWithName.getIdentifier(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(blockWithName.getIdentifier(), "inventory"));
    }

    private static void register(Block block, int meta, String file) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(RefStrings.MODID + ":" + file, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(RefStrings.MODID + ":" + file, "inventory"));
    }

    private static void register(Block block) {
        register(block, 0);
    }

}
