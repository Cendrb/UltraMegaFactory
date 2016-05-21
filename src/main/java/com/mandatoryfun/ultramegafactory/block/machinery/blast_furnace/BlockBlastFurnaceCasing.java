package com.mandatoryfun.ultramegafactory.block.machinery.blast_furnace;

import com.mandatoryfun.ultramegafactory.block.machinery.BlockGenericTierCasing;
import com.mandatoryfun.ultramegafactory.block.machinery.IBlockHeatable;
import com.mandatoryfun.ultramegafactory.lib.InvalidTierException;

/**
 * Created by cendr_000 on 03.04.2016.
 */
public class BlockBlastFurnaceCasing extends BlockGenericTierCasing implements IBlockHeatable {
    public BlockBlastFurnaceCasing() {
        super("blast_furnace_casing");
    }

    @Override
    public int getMinTier() {
        return 1;
    }

    @Override
    public int getMaxTier() {
        return 2;
    }

    @Override
    public int getThermalCapacityJoulePerDegree(int tier) {
        switch (tier)
        {
            case 1:
                return 1200;
            case 2:
                return 1500;
            default:
                throw new InvalidTierException(this);
        }
    }

    public int getJouleLeakedPerTick(int tier)
    {
        switch (tier)
        {
            case 1:
                return 60;
            case 2:
                return 30;
            default:
                throw new InvalidTierException(this);
        }
    }
}
