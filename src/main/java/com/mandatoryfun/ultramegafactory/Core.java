package com.mandatoryfun.ultramegafactory;

import com.mandatoryfun.ultramegafactory.handler.TooltipHandler;
import com.mandatoryfun.ultramegafactory.lib.RefStrings;
import com.mandatoryfun.ultramegafactory.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = RefStrings.MODID, version = RefStrings.VERSION, name = RefStrings.NAME)
public class Core {

    @SidedProxy(clientSide = RefStrings.CLIENTSIDE, serverSide = RefStrings.SERVERSIDE, modId = RefStrings.MODID)
    public static CommonProxy proxy;

    @Mod.Instance
    public static Core instance = new Core();

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        System.out.println("PREINIT for " + RefStrings.NAME);
        proxy.preInit(e);
        // create and register blocks and item
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
        System.out.println("INIT for " + RefStrings.NAME);
        // MinecraftForge.EVENT_BUS.register(new TooltipHandler());
        // crafting recipes, new handlers
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
        System.out.println("POSTINIT for " + RefStrings.NAME);
        // communicate with other mods
    }
}
