package com.mandatoryfun.ultramegafactory.block;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by cendr_000 on 26.03.2016.
 */
public class ItemBlockGenericOre extends ItemBlockGeneric {

    BlockGenericOre blockGenericOre;

    public ItemBlockGenericOre(Block block) {
        super(block);
        if (block.getClass() == BlockGenericOre.class) {
            blockGenericOre = (BlockGenericOre) block;
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if (blockGenericOre != null) {
            tooltip.add(blockGenericOre.getFormula());
            if (GuiScreen.isShiftKeyDown()) {
                String[] descriptions = blockGenericOre.getDescription();
                for (int i = 0; i < descriptions.length; i++)
                    tooltip.add(descriptions[i]);
            } else
                tooltip.add("Press SHIFT for more information");
        }
        super.addInformation(stack, playerIn, tooltip, advanced);
    }
}
