package com.mandatoryfun.ultramegafactory.block.blast_furnace.gui;

import com.mandatoryfun.ultramegafactory.tileentity.TileEntityBlastFurnaceController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Created by cendr_000 on 30.03.2016.
 */
public class ContainerBlastFurnace extends Container {

    TileEntityBlastFurnaceController tileEntity;

    public ContainerBlastFurnace(InventoryPlayer playerInventory, TileEntityBlastFurnaceController blastFurnaceController)
    {
        this.tileEntity = blastFurnaceController;

        // generate main ores inventory
        for (int row = 0; row < 3; ++row)
        {
            for (int col = 0; col < 9; ++col)
            {
                this.addSlotToContainer(new SlotItemHandler(tileEntity.getHandlerInput(), col + row * 9, 8 + col * 18, 10 + row * 18));
            }
        }


        // generate inventory slots
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        // generate hotbar slots
        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    @Override
    public ItemStack func_184996_a(int p_184996_1_, int dragType, ClickType clickTypeIn, EntityPlayer player) {
        return super.func_184996_a(p_184996_1_, dragType, clickTypeIn, player);
    }



    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
