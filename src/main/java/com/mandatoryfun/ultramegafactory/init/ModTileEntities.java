package com.mandatoryfun.ultramegafactory.init;

import com.mandatoryfun.ultramegafactory.tileentity.TileEntityBlastFurnaceController;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by cendr_000 on 02.04.2016.
 */
public class ModTileEntities {

    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityBlastFurnaceController.class, "blast_furnace_controller");
    }
}
