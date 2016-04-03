package com.mandatoryfun.ultramegafactory.block.machinery.blast_furnace;

import com.mandatoryfun.ultramegafactory.block.machinery.BlockGenericTier;

/**
 * Created by cendr_000 on 03.04.2016.
 */
public class BlockBlastFurnaceCasing extends BlockGenericTier {
    public BlockBlastFurnaceCasing() {
        super("blast_furnace_casing");
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
