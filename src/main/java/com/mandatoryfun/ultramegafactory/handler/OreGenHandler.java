package com.mandatoryfun.ultramegafactory.handler;

import com.mandatoryfun.ultramegafactory.lib.UMFLogger;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;

/**
 * Created by Wester on 3/31/2016.
 */

public class OreGenHandler
{
    @SubscribeEvent
    public void OreGenHandler(OreGenEvent.GenerateMinable event)
    {
        if(event.getType()== OreGenEvent.GenerateMinable.EventType.IRON||event.getType()== OreGenEvent.GenerateMinable.EventType.COAL)
        {
            UMFLogger.logDebug("OreGenException Ruined");
            event.setResult(Event.Result.DENY);
        }
    }
}
