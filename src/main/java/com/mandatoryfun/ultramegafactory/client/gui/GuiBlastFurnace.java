package com.mandatoryfun.ultramegafactory.client.gui;

import com.mandatoryfun.ultramegafactory.lib.RefStrings;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

/**
 * Created by cendr_000 on 30.03.2016.
 */
public class GuiBlastFurnace extends GuiContainer {

    public static final ResourceLocation background = new ResourceLocation(RefStrings.MODID, "textures/gui/blast_furnace.png");

    public GuiBlastFurnace(Container inventorySlotsIn) {
        super(inventorySlotsIn);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }
}
