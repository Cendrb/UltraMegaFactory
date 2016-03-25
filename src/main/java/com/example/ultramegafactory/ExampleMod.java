package com.example.ultramegafactory;

import com.example.ultramegafactory.lib.RefStrings;
import com.example.ultramegafactory.proxy.CommonProxy;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = RefStrings.MODID, version = RefStrings.VERSION, name = RefStrings.NAME)
public class ExampleMod {
    public static final String MODID = "ultramegafactory";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = RefStrings.CLIENTSIDE, serverSide = RefStrings.SERVERSIDE, modId = RefStrings.MODID)
    public static CommonProxy proxy;

    @Mod.Instance
    public static ExampleMod instance = new ExampleMod();

    @EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        System.out.println("PREINIT for " + RefStrings.NAME);
        proxy.preInit(e);
        // create and register blocks and items
    }

    @EventHandler
    public void init(FMLInitializationEvent e)
    {
        proxy.init(e);
        System.out.println("INIT for " + RefStrings.NAME);
        // crafting recipes, new handlers
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
        proxy.postInit(e);
        System.out.println("POSTINIT for " + RefStrings.NAME);
        // communicate with other mods
    }
}
