package com.mandatoryfun.ultramegafactory.block.machinery;

/**
 * Created by cendr_000 on 03.04.2016.
 */
public abstract class BlockHeater extends BlockGenericTierCasing implements IBlockHeatable {
    public BlockHeater(String unlocalizedName) {
        super(unlocalizedName);
    }
    
    public abstract float getEfficiency(int tier);
    public abstract int getInputPower(int tier);
}
