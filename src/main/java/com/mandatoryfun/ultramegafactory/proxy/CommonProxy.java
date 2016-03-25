package com.mandatoryfun.ultramegafactory.proxy;

import com.mandatoryfun.ultramegafactory.init.ModBlocks;
import com.mandatoryfun.ultramegafactory.init.ModItems;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by cendr_000 on 25.03.2016.
 */
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        ModItems.init();
        ModBlocks.init();
        System.out.println("JOHNCENA");
    }

    public void init(FMLInitializationEvent e) {

    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
