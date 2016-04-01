package com.mandatoryfun.ultramegafactory.handler;

import com.mandatoryfun.ultramegafactory.init.UMFRegistry;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by cendr_000 on 28.03.2016.
 */
public class TooltipHandler {

    @SubscribeEvent
    public void onItemTooltipEvent(ItemTooltipEvent tooltipEvent) {
        if(UMFRegistry.Fuels.isFuel(tooltipEvent.getItemStack().getItem()) && tooltipEvent.isShowAdvancedItemTooltips())
        {
            int energyInKJ = UMFRegistry.Fuels.getKJEnergyValue(tooltipEvent.getItemStack().getItem());
            tooltipEvent.getToolTip().add("FeCuTiS energy value: " + energyInKJ + " kJ");
        }
    }


    @SubscribeEvent
    public void onItemTossEvent(ItemTossEvent tossEvent) {
        //EntityTNTPrimed tntPrimed = new EntityTNTPrimed(tossEvent.player.worldObj, tossEvent.player.posX, tossEvent.player.posY, tossEvent.player.posZ, tossEvent.player);
        //tossEvent.player.worldObj.spawnEntityInWorld(tntPrimed);
    }

    @SubscribeEvent
    public void onEntityGetHurt(LivingHurtEvent e) {
        //if (e..entity instanceof EntityPlayer)
        //    e.setCanceled(true);
    }
}
