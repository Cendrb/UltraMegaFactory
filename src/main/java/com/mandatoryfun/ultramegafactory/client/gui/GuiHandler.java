package com.mandatoryfun.ultramegafactory.client.gui;

import com.mandatoryfun.ultramegafactory.block.blast_furnace.gui.ContainerBlastFurnace;
import com.mandatoryfun.ultramegafactory.block.blast_furnace.gui.GuiBlastFurnaceController;
import com.mandatoryfun.ultramegafactory.lib.UMFLogger;
import com.mandatoryfun.ultramegafactory.tileentity.TileEntityBlastFurnaceController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by cendr_000 on 30.03.2016.
 */
public class GuiHandler implements IGuiHandler {

    public enum GuiEnum {BLAST_FURNACE}

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

        if (tileEntity != null) {
            if (ID == GuiEnum.BLAST_FURNACE.ordinal()) {
                if (tileEntity instanceof TileEntityBlastFurnaceController)
                    return new ContainerBlastFurnace(player.inventory, (TileEntityBlastFurnaceController) tileEntity);

            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

        if (tileEntity != null) {
            if (ID == GuiEnum.BLAST_FURNACE.ordinal()) {
                if(tileEntity instanceof TileEntityBlastFurnaceController)
                    return new GuiBlastFurnaceController(player.inventory, (TileEntityBlastFurnaceController) tileEntity);

            }
        }
        return null;
    }
}
