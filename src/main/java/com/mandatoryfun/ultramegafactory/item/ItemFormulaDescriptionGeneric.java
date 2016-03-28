package com.mandatoryfun.ultramegafactory.item;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by cendr_000 on 28.03.2016.
 */
public class ItemFormulaDescriptionGeneric extends ItemGeneric {

    protected String formula;
    protected String[] description;

    public ItemFormulaDescriptionGeneric(String unlocalizedName, String formula, String[] description) {
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

    public String getFormula() {
        return formula;
    }

    public String[] getDescription() {
        return description;
    }
}
