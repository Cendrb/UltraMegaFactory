package com.mandatoryfun.ultramegafactory.item;

import com.mandatoryfun.ultramegafactory.lib.RefStrings;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by cendr_000 on 27.03.2016.
 */
public class ItemIngotGeneric extends ItemGeneric {

    protected String formula;
    protected String[] description;

    public ItemIngotGeneric(String unlocalizedName, String formula, String[] description) {
        super(unlocalizedName);
        this.formula = formula;
        this.description = description;
    }

     @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if (!formula.isEmpty())
            tooltip.add(formula);
        if (GuiScreen.isShiftKeyDown()) {
            for (int i = 0; i < description.length; i++)
                tooltip.add(description[i]);
        } else
            tooltip.add("Press SHIFT for more information");
        super.addInformation(stack, playerIn, tooltip, advanced);
    }
}
