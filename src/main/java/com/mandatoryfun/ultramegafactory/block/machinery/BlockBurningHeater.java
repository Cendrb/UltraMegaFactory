package com.mandatoryfun.ultramegafactory.block.machinery;

import com.mandatoryfun.ultramegafactory.lib.InvalidTierException;

/**
 * Created by cendr_000 on 03.04.2016.
 */
public class BlockBurningHeater extends BlockHeater {
    public BlockBurningHeater() {
        super("burning_heater");
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
                return 0.9f;
            default:
                throw new InvalidTierException(this);
        }
    }

    @Override
    public int getInputPower(int tier) {
        switch (tier)
        {
            case 1:
                return 2000;
            case 2:
                return 5000;
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
