package com.mandatoryfun.ultramegafactory.client.render.item;

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
        register(ModItems.Ingot.iron);
        register(ModItems.Ingot.steel);
        register(ModItems.Ingot.enrichedSteel);
        register(ModItems.Ingot.copper);
        register(ModItems.Ingot.tin);
        register(ModItems.Ingot.silver);
        register(ModItems.Ingot.aluminium);
        register(ModItems.Ingot.titanium);
        register(ModItems.Ingot.zinc);
        register(ModItems.Ingot.antimony);
        register(ModItems.Ingot.lead);
        register(ModItems.Ingot.cobalt);
        register(ModItems.Ingot.chrome);
        register(ModItems.Ingot.nickel);
        register(ModItems.Ingot.bronze);
        register(ModItems.Ingot.electrum);
        register(ModItems.Ingot.brass);
        register(ModItems.Ingot.PbSnSbAlloy);
        register(ModItems.Ingot.gold);
    }

    private static void register(ItemGeneric item) {
        System.out.println(item.getIdentifier());
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getIdentifier(), "inventory"));
    }

    private static void register(ItemGeneric item, int meta) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(item.getIdentifier(), "inventory"));
    }
}
