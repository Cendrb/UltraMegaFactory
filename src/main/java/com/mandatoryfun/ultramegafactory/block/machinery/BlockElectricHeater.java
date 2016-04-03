package com.mandatoryfun.ultramegafactory.block.machinery;

import com.mandatoryfun.ultramegafactory.block.machinery.BlockGenericTier;

/**
 * Created by cendr_000 on 03.04.2016.
 */
public class BlockElectricHeater extends BlockGenericTier {
    public BlockElectricHeater() {
        super("electric_heater");
    }

    @Override
    protected int getMinTier() {
        return 1;
    }

    @Override
    protected int getMaxTier() {
        return 2;
    }
}
