package com.mandatoryfun.ultramegafactory.client.render.block;

import com.mandatoryfun.ultramegafactory.block.BlockGeneric;
import com.mandatoryfun.ultramegafactory.init.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * Created by cendr_000 on 25.03.2016.
 */
public class BlockRenderRegister {

    public static void init() {
        register(ModBlocks.magnetiteOre, 0);
    }

    private static void register(BlockGeneric block, int meta) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(block.getIdentifier(), "inventory"));
    }

}
