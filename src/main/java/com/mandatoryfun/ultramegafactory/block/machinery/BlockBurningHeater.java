package com.mandatoryfun.ultramegafactory.block.machinery;

import com.mandatoryfun.ultramegafactory.block.machinery.BlockGenericTier;

/**
 * Created by cendr_000 on 03.04.2016.
 */
public class BlockBurningHeater extends BlockHeater {
    public BlockBurningHeater() {
        super("burning_heater");
    }

    @Override
    protected int getMinTier() {
        return 1;
    }

    @Override
    protected int getMaxTier() {
        return 2;
    }

    @Override
    public float getEfficiency(int tier) {
        if(tier == 1)
            return 0.75f;
        else if(tier == 2)
            return 0.95f;
        else
            return 1;
    }

    @Override
    public int getInputPower(int tier) {
        return 0;
    }
}
