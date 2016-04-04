package com.mandatoryfun.ultramegafactory.block.machinery;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by cendr_000 on 03.04.2016.
 */
public abstract class BlockHeater extends BlockGenericTier implements IBlockHeatable {
    public BlockHeater(String unlocalizedName) {
        super(unlocalizedName);
    }
    
    public abstract float getEfficiency(int tier);
    public abstract int getInputPower(int tier);
}
