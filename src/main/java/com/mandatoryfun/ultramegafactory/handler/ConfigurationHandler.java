package com.mandatoryfun.ultramegafactory.handler;

import com.mandatoryfun.ultramegafactory.lib.RefStrings;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

/**
 * Created by cendr_000 on 30.03.2016.
 */
public class ConfigurationHandler {
    public static Configuration configuration;

    public static void init(File configFile)
    {
        if(configuration == null)
        {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {/*
     // error: modID has private access in ConfigChangedEvent
        if(event.modID.equalsIgnoreCase(RefStrings.MODID))
            loadConfiguration();*/
    }

    private static void loadConfiguration()
    {
        //randomAllahuAkbar = configuration.getBoolean("randomAllahuAkbar", Configuration.CATEGORY_GENERAL, false, "Responsible for random Allahu Akbar");

        if(configuration.hasChanged())
            configuration.save();
    }
}
