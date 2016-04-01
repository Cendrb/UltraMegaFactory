package com.mandatoryfun.ultramegafactory.item;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by cendr_000 on 28.03.2016.
 */
public class ItemIronIngot extends ItemIngotGeneric {


    public ItemIronIngot(String unlocalizedName, String formula, String[] description) {
        super(unlocalizedName, formula, description);
        setRegisterRender(false); // do not register regularly - has subids
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        subItems.add(new ItemStack(itemIn, 1, 0));
        subItems.add(new ItemStack(itemIn, 1, 1));
        subItems.add(new ItemStack(itemIn, 1, 2));
        subItems.add(new ItemStack(itemIn, 1, 3));
        subItems.add(new ItemStack(itemIn, 1, 4));
    }

    @Override
    public boolean getHasSubtypes() {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        String tierString = String.valueOf(stack.getItemDamage() + 1) + ". tier";
        if (!formula.isEmpty())
            tooltip.add(formula + " (" + tierString + ")");
        if (GuiScreen.isShiftKeyDown()) {
            for (int i = 0; i < description.length; i++)
                tooltip.add(description[i]);
        } else
            tooltip.add("Press SHIFT for more information");
    }
}
