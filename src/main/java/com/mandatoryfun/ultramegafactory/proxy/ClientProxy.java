package com.mandatoryfun.ultramegafactory.proxy;

import com.mandatoryfun.ultramegafactory.client.render.BlockRenderRegister;
import com.mandatoryfun.ultramegafactory.client.render.ItemRenderRegister;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by cendr_000 on 25.03.2016.
 */
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        BlockRenderRegister.preInit();
        ItemRenderRegister.preinit();
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        BlockRenderRegister.init();
        ItemRenderRegister.init();
        System.out.println("JONH CENA WWE Client INIT");
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
}
