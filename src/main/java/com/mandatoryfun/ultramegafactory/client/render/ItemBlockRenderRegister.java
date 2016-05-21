package com.mandatoryfun.ultramegafactory.client.render;

import com.google.common.base.Predicate;
import com.mandatoryfun.ultramegafactory.block.IBlockMultipleMetas;
import com.mandatoryfun.ultramegafactory.block.IPureName;
import com.mandatoryfun.ultramegafactory.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nullable;

/**
 * Created by cendr_000 on 25.03.2016.
 */
public class ItemBlockRenderRegister {

    public static void preInit() {

    }

    public static void init() {
        for (final Block blockGeneric : ModBlocks.getAllBlocks())
            if (blockGeneric instanceof IBlockMultipleMetas) {
                ((IBlockMultipleMetas) blockGeneric).runForEachTier(new Predicate<Integer>() {
                    @Override
                    public boolean apply(@Nullable Integer input) {
                        register(blockGeneric, input - 1, ((IPureName)blockGeneric).getItemModelPath(input - 1));
                        return true;
                    }
                });
            } else
                register(blockGeneric);
    }

    private static void register(Block block, int meta) {
        IPureName blockWithName = (IPureName) block;
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(blockWithName.getItemModelPath(0), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(blockWithName.getItemModelPath(0), "inventory"));
    }

    private static void register(Block block, int meta, String file) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(file, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(file, "inventory"));
    }

    private static void register(Block block) {
        register(block, 0);
    }

}
