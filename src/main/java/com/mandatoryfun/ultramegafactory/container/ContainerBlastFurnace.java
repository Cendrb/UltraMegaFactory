package com.mandatoryfun.ultramegafactory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 * Created by cendr_000 on 30.03.2016.
 */
public class ContainerBlastFurnace extends Container {
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return false;
    }
}
