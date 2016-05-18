package com.mandatoryfun.ultramegafactory.block.itemblock;

import com.mandatoryfun.ultramegafactory.block.IBlockMultipleMetas;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * Created by cendr_000 on 29.03.2016.
 */
public class ItemBlockMultipleNames extends ItemBlockGeneric {
    public ItemBlockMultipleNames(Block block) {
        super(block);
        if (!(block instanceof IBlockMultipleMetas)) {
            throw new IllegalArgumentException(String.format("The given Block %s is not an instance of IBlockMultipleMetas!", block.getUnlocalizedName()));
        }
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + "_" + ((IBlockMultipleMetas)this.block).getSpecialNameEnding(stack);
    }
}
