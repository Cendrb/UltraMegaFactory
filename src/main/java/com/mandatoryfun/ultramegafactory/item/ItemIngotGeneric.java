package com.mandatoryfun.ultramegafactory.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Objects;

/**
 * Created by cendr_000 on 27.03.2016.
 */
public class ItemIngotGeneric extends ItemGeneric {

    private String formula;

    public ItemIngotGeneric(String unlocalizedName, String formula) {
        super(unlocalizedName);
        this.formula = formula;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if (!Objects.equals(formula, ""))
            tooltip.add(formula);
        super.addInformation(stack, playerIn, tooltip, advanced);
    }
}
