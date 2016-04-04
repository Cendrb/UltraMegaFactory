package com.mandatoryfun.ultramegafactory.block.machinery;

import com.mandatoryfun.ultramegafactory.block.machinery.BlockGenericTier;
import com.mandatoryfun.ultramegafactory.lib.InvalidTierException;

/**
 * Created by cendr_000 on 03.04.2016.
 */
public class BlockElectricHeater extends BlockHeater {
    public BlockElectricHeater() {
        super("electric_heater");
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
    public float getEfficiency(int tier) {
        switch (tier)
        {
            case 1:
                return 0.75f;
            case 2:
                return 0.90f;
            default:
                throw new InvalidTierException(this);
        }
    }

    @Override
    public int getInputPower(int tier) {
        switch (tier)
        {
            case 1:
                return 0;
            case 2:
                return 0;
            default:
                throw new InvalidTierException(this);
        }
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
}
