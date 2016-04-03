package com.mandatoryfun.ultramegafactory.block.machinery.blast_furnace;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.mandatoryfun.ultramegafactory.init.UMFRegistry;
import com.mandatoryfun.ultramegafactory.lib.FacingRotator;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Dictionary;
import java.util.HashMap;

/**
 * Created by cendr_000 on 03.04.2016.
 */
public class BlastFurnaceMultiblock {

    private HashMap<BlockPos, IBlockState> blocks = new HashMap<BlockPos, IBlockState>();

    public BlastFurnaceMultiblock()
    {

    }

    public void rebuild(EnumFacing facing, BlockPos controllerPos, World world)
    {
        blocks.clear();
        FacingRotator rotator = new FacingRotator(facing);
        BlockPos floorBeginning = controllerPos.offset(rotator.getAbsoluteDirection(FacingRotator.RelativeDirection.FORWARD), 2).down();
        if(!addIfCasing(floorBeginning, world))
            return;

    }

    private boolean addIfHeater(BlockPos pos, World world)
    {
        IBlockState state = world.getBlockState(pos);
        if(UMFRegistry.BlastFurnaceParts.isHeater(state))
        {
            blocks.put(pos, state);
            return true;
        }
        else
            return false;
    }
    private boolean addIfCasing(BlockPos pos, World world)
    {
        IBlockState state = world.getBlockState(pos);
        if(UMFRegistry.BlastFurnaceParts.isCasing(state))
        {
            blocks.put(pos, state);
            return true;
        }
        else
            return false;
    }
}
