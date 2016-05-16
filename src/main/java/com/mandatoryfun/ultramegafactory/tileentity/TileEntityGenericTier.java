package com.mandatoryfun.ultramegafactory.tileentity;

import com.mandatoryfun.ultramegafactory.block.machinery.blast_furnace.BlockBlastFurnaceController;
import com.mandatoryfun.ultramegafactory.lib.UMFLogger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by cendr_000 on 15.05.2016.
 */
public class TileEntityGenericTier extends TileEntity {

    private BlockPos controllerPos;

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if(controllerPos != null) {
            compound.setLong("controllerPos", controllerPos.toLong());
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if(compound.hasKey("controllerPos"))
        {
            controllerPos = BlockPos.fromLong(compound.getLong("controllerPos"));
        }
    }

    @Override
    public Packet<?> getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new SPacketUpdateTileEntity(pos, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    public void notifyControllerToRebuild()
    {
        if(controllerPos != null)
        {
            IBlockState blockState = worldObj.getBlockState(controllerPos);
            TileEntity tileEntity = worldObj.getTileEntity(controllerPos);
            if(tileEntity != null && blockState != null)
            {
                TileEntityBlastFurnaceController blastFurnaceController = (TileEntityBlastFurnaceController) tileEntity;
                blastFurnaceController.rebuildMultiblock(blockState.getValue(BlockBlastFurnaceController.FACING), controllerPos, worldObj, blockState.getValue(BlockBlastFurnaceController.TIER));
            }
            else {
                UMFLogger.logError("Unable to find tileentity for " + controllerPos.toString());
                controllerPos = null;
            }
        }
    }

    public void setControllerPos(BlockPos controllerPos)
    {
        this.controllerPos = controllerPos;
        markDirty();
    }
}
