package com.mandatoryfun.ultramegafactory.block;

import net.minecraft.block.material.Material;

/**
 * Created by cendr_000 on 26.03.2016.
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

    public String getFormula()
    {
        return formula;
    }
    public String[] getDescription()
    {
        return description;
    }
}
