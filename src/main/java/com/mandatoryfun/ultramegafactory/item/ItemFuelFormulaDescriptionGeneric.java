package com.mandatoryfun.ultramegafactory.item;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by cendr_000 on 28.03.2016.
 */
public class ItemFuelFormulaDescriptionGeneric extends ItemFormulaDescriptionGeneric {

    private int energyValue;

    public ItemFuelFormulaDescriptionGeneric(String unlocalizedName, String formula, String[] description, int kJPerItem) {
        super(unlocalizedName, formula, description);
        this.energyValue = kJPerItem;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);
        if(GuiScreen.isShiftKeyDown())
            tooltip.add("Energy value: " + energyValue + "kJ per item");
    }
}
