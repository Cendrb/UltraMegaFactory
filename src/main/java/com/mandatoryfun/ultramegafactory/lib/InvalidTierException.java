package com.mandatoryfun.ultramegafactory.lib;

import com.mandatoryfun.ultramegafactory.block.machinery.BlockGenericTierCasing;

/**
 * Created by cendr_000 on 04.04.2016.
 */
public class InvalidTierException extends IllegalArgumentException {
    public InvalidTierException(BlockGenericTierCasing tierable) {
        super("Tier not valid, only " + tierable.getMinTier() + ".." + tierable.getMaxTier() + " range supported");
    }
}
