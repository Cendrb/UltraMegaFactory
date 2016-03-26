package com.mandatoryfun.ultramegafactory.block;

import net.minecraft.block.material.Material;

/**
 * Created by cendr_000 on 26.03.2016.
 */
public class BlockGenericOre extends BlockGeneric {

    private String formula;

    public BlockGenericOre(String unlocalizedName, String formula) {
        super(unlocalizedName);
        this.formula = formula;
    }

    public BlockGenericOre(String unlocalizedName, Material material, String formula) {
        super(unlocalizedName, material);
        this.formula = formula;
    }

    public BlockGenericOre(String unlocalizedName, Material material, float hardness, float resistance, String formula) {
        super(unlocalizedName, material, hardness, resistance);
        this.formula = formula;
    }

    public BlockGenericOre(String unlocalizedName, Material material, float hardness, float resistance, String toolClass, int toolLevel, String formula) {
        super(unlocalizedName, material, hardness, resistance, toolClass, toolLevel);
        this.formula = formula;
    }

    public String getFormula()
    {
        return formula;
    }
}
