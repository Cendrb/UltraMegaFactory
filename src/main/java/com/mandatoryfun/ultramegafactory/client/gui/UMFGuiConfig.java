package com.mandatoryfun.ultramegafactory.client.gui;

import com.mandatoryfun.ultramegafactory.handler.ConfigurationHandler;
import com.mandatoryfun.ultramegafactory.lib.RefStrings;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.List;

/**
 * Created by cendr_000 on 30.03.2016.
 */
public class UMFGuiConfig extends GuiConfig {
    public UMFGuiConfig(GuiScreen parentScreen, List<IConfigElement> configElements) {
        super(parentScreen, configElements, RefStrings.MODID, true, false, GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
    }
}
