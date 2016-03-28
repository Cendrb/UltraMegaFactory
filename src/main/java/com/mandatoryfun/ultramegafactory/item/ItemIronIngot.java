package com.mandatoryfun.ultramegafactory.item;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by cendr_000 on 28.03.2016.
 */
public class ItemIronIngot extends ItemIngotGeneric {


    public ItemIronIngot(String unlocalizedName, String formula, String[] description) {
        super(unlocalizedName, formula, description);
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        super.onCreated(stack, worldIn, playerIn);
        initTag(stack);
    }

    private void initTag(ItemStack itemStack)
    {
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setInteger("iron_tier", 1);
        itemStack.setTagCompound(tagCompound);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if(!stack.hasTagCompound())
            initTag(stack);
        String tierString = String.valueOf(stack.getTagCompound().getInteger("iron_tier")) + ". tier";
        if (!formula.isEmpty())
            tooltip.add(formula + " (" + tierString + ")");
        if (GuiScreen.isShiftKeyDown()) {
            for (int i = 0; i < description.length; i++)
                tooltip.add(description[i]);
        } else
            tooltip.add("Press SHIFT for more information");
    }
}
