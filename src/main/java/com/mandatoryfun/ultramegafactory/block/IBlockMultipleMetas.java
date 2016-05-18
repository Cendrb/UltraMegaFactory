package com.mandatoryfun.ultramegafactory.block;

import com.google.common.base.Predicate;
import net.minecraft.item.ItemStack;

/**
 * Created by cendr_000 on 29.03.2016.
 */
public interface IBlockMultipleMetas {
    String getSpecialNameEnding(ItemStack stack);
    void runForEachTier(Predicate<Integer> runnable);
}
