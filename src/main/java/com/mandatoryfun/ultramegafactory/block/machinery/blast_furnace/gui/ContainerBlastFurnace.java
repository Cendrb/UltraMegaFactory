package com.mandatoryfun.ultramegafactory.block.machinery.blast_furnace.gui;

import com.mandatoryfun.ultramegafactory.block.machinery.blast_furnace.BlastFurnaceMultiblock;
import com.mandatoryfun.ultramegafactory.tileentity.TileEntityBlastFurnaceController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Created by cendr_000 on 30.03.2016.
 */
public class ContainerBlastFurnace extends Container {

    TileEntityBlastFurnaceController tileEntity;
    private float currentTemperature;
    private int burnTimeLeft;
    private int burnTimeOrig;
    private int reactionTimeLeft;
    private int reactionTimeOrig;

    public ContainerBlastFurnace(InventoryPlayer playerInventory, TileEntityBlastFurnaceController blastFurnaceController) {
        this.tileEntity = blastFurnaceController;

        // generate main ores inventory
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlotToContainer(new SlotItemHandler(tileEntity.getHandlerInput(), col + row * 9, 8 + col * 18, 18 + row * 18));
            }
        }

        // generate fuel slot
        this.addSlotToContainer(new SlotItemHandler(tileEntity.getHandlerFuel(), 0, 152, 90));

        // generate sample slot
        this.addSlotToContainer(new SlotItemHandler(tileEntity.getHandlerSample(), 0, 8, 119));

        // generate output slot
        this.addSlotToContainer(new SlotItemHandler(tileEntity.getHandlerOutput(), 0, 152, 119));

        // generate inventory slots
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 140 + i * 18));
            }
        }
        // generate hotbar slots
        for (int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 198));
        }
    }

    public void onCraftGuiOpened(ICrafting listener) {
        super.onCraftGuiOpened(listener);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        BlastFurnaceMultiblock.Data data = tileEntity.getData();
        if (data != null) {
            for (int i = 0; i < this.crafters.size(); ++i) {
                ICrafting icrafting = this.crafters.get(i);

                if (this.currentTemperature != data.getField(0)) {
                    icrafting.sendProgressBarUpdate(this, 0, data.getField(0));
                }
                if (this.burnTimeLeft != data.getField(1)) {
                    icrafting.sendProgressBarUpdate(this, 1, data.getField(1));
                }
                if (this.burnTimeOrig != data.getField(2)) {
                    icrafting.sendProgressBarUpdate(this, 2, data.getField(2));
                }
                if (this.reactionTimeLeft != data.getField(3)) {
                    icrafting.sendProgressBarUpdate(this, 3, data.getField(3));
                }
                if (this.reactionTimeOrig != data.getField(4)) {
                    icrafting.sendProgressBarUpdate(this, 4, data.getField(4));
                }

            }

            this.currentTemperature = data.getField(0);
            this.burnTimeLeft = data.getField(1);
            this.burnTimeOrig = data.getField(2);
            this.reactionTimeLeft = data.getField(3);
            this.reactionTimeOrig = data.getField(4);
        }
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value) {
        BlastFurnaceMultiblock.Data data = tileEntity.getData();
        if (data != null)
            data.setField(id, value);
    }


    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
