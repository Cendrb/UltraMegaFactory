package com.mandatoryfun.ultramegafactory.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by MandatoryFun
 */
public class BlockGenericOre extends BlockGeneric {

    private String formula;
    private String[] description;

    public BlockGenericOre(String unlocalizedName, String formula, String[] description) {
        super(unlocalizedName);
        this.formula = formula;
        this.description = description;
    }

    public BlockGenericOre(String unlocalizedName, Material material, String formula, String[] description) {
        super(unlocalizedName, material);
        this.formula = formula;
        this.description = description;
    }

    public BlockGenericOre(String unlocalizedName, Material material, float hardness, float resistance, String formula, String[] description) {
        super(unlocalizedName, material, hardness, resistance);
        this.formula = formula;
        this.description = description;
    }

    public BlockGenericOre(String unlocalizedName, Material material, float hardness, float resistance, String toolClass, int toolLevel, String formula, String[] description) {
        super(unlocalizedName, material, hardness, resistance, toolClass, toolLevel);
        this.formula = formula;
        this.description = description;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        tooltip.add(getFormula());
        if (GuiScreen.isShiftKeyDown()) {
            String[] descriptions = getDescription();
            for (int i = 0; i < descriptions.length; i++)
                tooltip.add(descriptions[i]);
        } else
            tooltip.add("Press SHIFT for more information");
        super.addInformation(stack, player, tooltip, advanced);
    }

    public String getFormula() {
        return formula;
    }

    public String[] getDescription() {
        return description;
    }
}
