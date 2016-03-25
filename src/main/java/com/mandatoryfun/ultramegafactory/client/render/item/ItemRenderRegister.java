package com.mandatoryfun.ultramegafactory.client.render.item;

import com.mandatoryfun.ultramegafactory.init.ModItems;
import com.mandatoryfun.ultramegafactory.item.ItemGeneric;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

/**
 * Created by cendr_000 on 25.03.2016.
 */
public class ItemRenderRegister {

    public static void init()
    {
        register(ModItems.johnCena, 0);
    }

    private static void register(ItemGeneric item, int meta)
    {
        System.out.println(item.getIdentifier());
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(item.getIdentifier(), "inventory"));
    }
}