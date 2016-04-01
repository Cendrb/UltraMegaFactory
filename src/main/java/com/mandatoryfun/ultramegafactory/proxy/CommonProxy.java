package com.mandatoryfun.ultramegafactory.proxy;

import com.mandatoryfun.ultramegafactory.Core;
import com.mandatoryfun.ultramegafactory.client.gui.GuiHandler;
import com.mandatoryfun.ultramegafactory.creativetab.ModCreativeTabs;
import com.mandatoryfun.ultramegafactory.handler.ConfigurationHandler;
import com.mandatoryfun.ultramegafactory.handler.OreGenHandler;
import com.mandatoryfun.ultramegafactory.handler.TooltipHandler;
import com.mandatoryfun.ultramegafactory.init.ModBlocks;
import com.mandatoryfun.ultramegafactory.init.ModItems;
import com.mandatoryfun.ultramegafactory.init.UMFRecipes;
import com.mandatoryfun.ultramegafactory.init.UMFRegistry;
import com.mandatoryfun.ultramegafactory.world.ModWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by cendr_000 on 25.03.2016.
 */
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        ModCreativeTabs.init();
        ModItems.init();
        ModBlocks.init();

        UMFRegistry.Fuels.init();
        UMFRecipes.BlastFurnace.init();
        UMFRegistry.OreDitionaryManager.init();

        // config
        ConfigurationHandler.init(e.getSuggestedConfigurationFile());

        NetworkRegistry.INSTANCE.registerGuiHandler(Core.instance, new GuiHandler());
    }

    public void init(FMLInitializationEvent e) {
        ModWorld.init();

        UMFRecipes.CraftingManager.init();

        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        MinecraftForge.EVENT_BUS.register(new TooltipHandler());
        MinecraftForge.ORE_GEN_BUS.register(new OreGenHandler());
    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
