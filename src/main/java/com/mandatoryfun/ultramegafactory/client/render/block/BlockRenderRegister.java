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


    static ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

    public static void init() {
        register(ModBlocks.magnetiteOre, 0);
    }

    private static void register(BlockGeneric block, int meta) {
        mesher.register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(block.getIdentifier(), "inventory"));
    }

}
