package com.mandatoryfun.ultramegafactory.block.machinery.blast_furnace.gui;

import com.mandatoryfun.ultramegafactory.block.machinery.blast_furnace.BlastFurnaceMultiblock;
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


        BlastFurnaceMultiblock.Data data = tileEntity.getData();
        if(data != null) {
            fontRendererObj.drawString(String.valueOf(data.getField(0)), 8, 90, 4210752);
            fontRendererObj.drawString(String.valueOf(data.getField(1)) + "/" + String.valueOf(data.getField(2)), 8, 105, 4210752);
            fontRendererObj.drawString(String.valueOf(data.getField(3)) + "/" + String.valueOf(data.getField(4)), 8, 120, 4210752);
            fontRendererObj.drawString(String.valueOf(tileEntity.getHandlerInput().getCurrentNumberOfItems() + "/" + data.getCapacity()), 8, 75, 4210752);
        }
        else
            fontRendererObj.drawString("Structure not initialized, right click with wooden pickaxe to initialize", 8, 90, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        BlastFurnaceMultiblock.Data data = tileEntity.getData();
        if(data != null) {
            // FLAMES
            int scaledBurnProgress = scaleOver(13, data.getField(1), data.getField(2));
            drawTexturedModalRect(guiLeft + 153, (guiTop + 73 + scaledBurnProgress), 176, scaledBurnProgress, 13, 13 - scaledBurnProgress);

            // PROGRESS ARROW
            int scaledReactionProgress = scaleOver(121, data.getField(3), data.getField(4));
            drawTexturedModalRect(guiLeft + 27, guiTop + 119, 0, 222, 121 - scaledReactionProgress, 15);
        }

    }

    private int scaleOver(int guiElementMax, float realVariableCurrent, float realVariableMax)
    {
        return (int)((realVariableCurrent / realVariableMax) * guiElementMax);
    }
}
