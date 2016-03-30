package com.mandatoryfun.ultramegafactory.client.render.block;

import com.mandatoryfun.ultramegafactory.block.BlockGeneric;
import com.mandatoryfun.ultramegafactory.init.ModBlocks;
import com.mandatoryfun.ultramegafactory.lib.RefStrings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * Created by cendr_000 on 25.03.2016.
 */
public class BlockRenderRegister {

    public static void preInit()
    {
        ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.blastFurnaceController), new ResourceLocation(RefStrings.MODID, "blast_furnace_controller_t1"), new ResourceLocation(RefStrings.MODID, "blast_furnace_controller_t2"));
    }

    public static void init() {
        for (BlockGeneric blockGeneric : ModBlocks.getAllBlocks())
            register(blockGeneric);
        register(ModBlocks.blastFurnaceController, 0, "blast_furnace_controller_t1");
        register(ModBlocks.blastFurnaceController, 1, "blast_furnace_controller_t2");
    }

    private static void register(BlockGeneric block, int meta) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(block.getIdentifier(), "inventory"));
    }

    private static void register(BlockGeneric block, int meta, String file) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(RefStrings.MODID + ":" + file, "inventory"));
    }

    private static void register(BlockGeneric block) {
        register(block, 0);
    }

}
