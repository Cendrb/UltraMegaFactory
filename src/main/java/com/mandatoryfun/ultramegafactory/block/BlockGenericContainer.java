package com.mandatoryfun.ultramegafactory.block;

import com.mandatoryfun.ultramegafactory.lib.RefStrings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

/**
 * Created by cendr_000 on 30.03.2016.
 */
public abstract class BlockGenericContainer extends BlockContainer implements IPureName {

    private String pureName;

    protected BlockGenericContainer(String unlocalizedName, Material materialIn) {
        super(materialIn);
        setUnlocalizedName(unlocalizedName);
    }

    @Override
    public Block setUnlocalizedName(String name) {
        return super.setUnlocalizedName(name);
    }

    public String getIdentifier() {
        return RefStrings.MODID + ":" + pureName;
    }

    public String getPureName() {
        return pureName;
    }
}
