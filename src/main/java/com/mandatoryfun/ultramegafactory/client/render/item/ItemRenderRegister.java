package com.mandatoryfun.ultramegafactory.client.render.item;

import com.mandatoryfun.ultramegafactory.block.BlockGeneric;
import com.mandatoryfun.ultramegafactory.init.ModBlocks;
import com.mandatoryfun.ultramegafactory.init.ModItems;
import com.mandatoryfun.ultramegafactory.item.ItemGeneric;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

/**
 * Created by cendr_000 on 25.03.2016.
 */
public class ItemRenderRegister {

    public static void init() {
        for (ItemGeneric blockGeneric : ModItems.getAllItems())
            register(blockGeneric);
    }

    private static void register(ItemGeneric item) {
        System.out.println(item.getIdentifier());
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getIdentifier(), "inventory"));
    }

    private static void register(ItemGeneric item, int meta) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(item.getIdentifier(), "inventory"));
    }
}
