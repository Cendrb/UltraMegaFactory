package com.mandatoryfun.ultramegafactory.block.machinery.blast_furnace.gui;

import com.mandatoryfun.ultramegafactory.lib.RefStrings;
import com.mandatoryfun.ultramegafactory.tileentity.TileEntityBlastFurnaceController;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by cendr_000 on 30.03.2016.
 */
public class GuiBlastFurnaceController extends GuiContainer {

    public static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID, "textures/gui/blast_furnace_controller.png");

    private TileEntityBlastFurnaceController tileEntity;

    public GuiBlastFurnaceController(InventoryPlayer playerInv, TileEntityBlastFurnaceController blastFurnaceController) {
        super(new ContainerBlastFurnace(playerInv, blastFurnaceController));
        xSize = 175;
        ySize = 221;
        tileEntity = blastFurnaceController;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        String inventoryName = I18n.format(tileEntity.getDisplayName().getFormattedText());
        fontRendererObj.drawString(inventoryName, 8, 6, 4210752);
        fontRendererObj.drawString("Inventory", 8, 129, 4210752);

        fontRendererObj.drawString(String.valueOf(tileEntity.getHandlerInput().getCurrentNumberOfItems() + "/" + tileEntity.getHandlerInput().getCapacity()), 8, 75, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
