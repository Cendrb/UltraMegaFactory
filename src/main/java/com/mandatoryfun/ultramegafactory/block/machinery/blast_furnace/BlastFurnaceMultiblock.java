package com.mandatoryfun.ultramegafactory.block.machinery.blast_furnace;

import com.mandatoryfun.ultramegafactory.init.UMFRegistry;
import com.mandatoryfun.ultramegafactory.lib.FacingRotator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;

/**
 * Created by cendr_000 on 03.04.2016.
 */
public class BlastFurnaceMultiblock {

    private HashMap<BlockPos, IBlockState> blocks = new HashMap<BlockPos, IBlockState>();

    public BlastFurnaceMultiblock() {

    }

    public String rebuild(EnumFacing facing, BlockPos controllerPos, World world) {
        blocks.clear();
        FacingRotator rotator = new FacingRotator(facing);
        BlockPos mainHeater = controllerPos.offset(rotator.getAbsoluteDirection(FacingRotator.RelativeDirection.FORWARD), 2).down();
        if (!addIfHeater(mainHeater, world))
            return "The main heater not found (FORWARD, FORWARD, DOWN)";

        BlockPos left;
        int totalOffsetLeft = 0;
        do {
            totalOffsetLeft++;
            left = mainHeater.offset(rotator.getAbsoluteDirection(FacingRotator.RelativeDirection.LEFT), totalOffsetLeft);
        }
        while (addIfHeater(left, world));
        totalOffsetLeft--;

        BlockPos right;
        int totalOffsetRight = 0;
        do {
            totalOffsetRight++;
            right = mainHeater.offset(rotator.getAbsoluteDirection(FacingRotator.RelativeDirection.RIGHT), totalOffsetRight);
        }
        while (addIfHeater(right, world));
        totalOffsetRight--;
        
        if(totalOffsetLeft != totalOffsetRight)
            return "Heater platform must be 3x3 or 5x5 or 7x7 or 9x9";

        int totalOffset = Math.min(totalOffsetLeft, totalOffsetRight);
        if (totalOffset < 1)
            return "Heater platform cannot be smaller than 3x3";
        if(totalOffset > 4)
            return "Heater platform cannot be bigger than 9x9";

        for(int offsetRow = 1; offsetRow <= (totalOffset * 2); offsetRow++)
            for(int offsetCol = -totalOffset; offsetCol <= totalOffset; offsetCol++)
                if(!addIfHeater(mainHeater.offset(rotator.getAbsoluteDirection(FacingRotator.RelativeDirection.FORWARD), offsetRow).offset(rotator.getAbsoluteDirection(FacingRotator.RelativeDirection.LEFT), offsetCol), world))
                    return "Heater platform must be 3x3 or 5x5 or 7x7 or 9x9";

        return "SUCCESS";
    }

    private boolean addIfHeater(BlockPos pos, World world) {
        IBlockState state = world.getBlockState(pos);
        if (UMFRegistry.BlastFurnaceParts.isHeater(state)) {
            blocks.put(pos, state);
            return true;
        } else
            return false;
    }

    private boolean addIfCasing(BlockPos pos, World world) {
        IBlockState state = world.getBlockState(pos);
        if (UMFRegistry.BlastFurnaceParts.isCasing(state)) {
            blocks.put(pos, state);
            return true;
        } else
            return false;
    }
}
