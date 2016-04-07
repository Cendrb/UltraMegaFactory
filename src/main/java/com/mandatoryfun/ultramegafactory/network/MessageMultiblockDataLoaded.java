package com.mandatoryfun.ultramegafactory.network;

import com.mandatoryfun.ultramegafactory.block.machinery.blast_furnace.BlastFurnaceMultiblock;
import com.mandatoryfun.ultramegafactory.tileentity.TileEntityBlastFurnaceController;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldManager;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by cendr_000 on 07.04.2016.
 */
public class MessageMultiblockDataLoaded implements IMessage {

    private BlockPos tileEntityLocation;
    private BlastFurnaceMultiblock.Data data;

    public MessageMultiblockDataLoaded(BlastFurnaceMultiblock.Data data, TileEntityBlastFurnaceController tileEntity)
    {
        this.data = data;
        this.tileEntityLocation = tileEntity.getPos();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        tileEntityLocation = new BlockPos(ByteBufUtils.readVarInt(buf, 4), ByteBufUtils.readVarInt(buf, 4), ByteBufUtils.readVarInt(buf, 4));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, data.serializeNBT());
        ByteBufUtils.writeVarInt(buf, tileEntityLocation.getX(), 4);
        ByteBufUtils.writeVarInt(buf, tileEntityLocation.getY(), 4);
        ByteBufUtils.writeVarInt(buf, tileEntityLocation.getZ(), 4);

    }

    public static class Handler implements IMessageHandler<MessageMultiblockDataLoaded, IMessage>
    {

        @Override
        public IMessage onMessage(MessageMultiblockDataLoaded message, MessageContext ctx) {
            return null; // fuck your response
        }
    }
}
