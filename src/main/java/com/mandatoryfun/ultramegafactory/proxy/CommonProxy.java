package com.mandatoryfun.ultramegafactory.proxy;

import com.mandatoryfun.ultramegafactory.creativetab.ModCreativeTabs;
import com.mandatoryfun.ultramegafactory.init.ModBlocks;
import com.mandatoryfun.ultramegafactory.init.ModItems;
import com.mandatoryfun.ultramegafactory.world.ModWorld;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by cendr_000 on 25.03.2016.
 */
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        ModCreativeTabs.init();
        ModItems.init();
        ModBlocks.init();
    }

    public void init(FMLInitializationEvent e) {
        ModWorld.init();
    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
