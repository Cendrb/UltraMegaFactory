package com.mandatoryfun.ultramegafactory.block;

import com.mandatoryfun.ultramegafactory.creativetab.ModCreativeTabs;
import com.mandatoryfun.ultramegafactory.lib.RefStrings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

/**
 * Created by cendr_000 on 30.03.2016.
 */
public abstract class BlockGenericContainer extends BlockContainer implements IPureName {

    private String pureName;

    protected BlockGenericContainer(String registryName, Material materialIn) {
        super(materialIn);
        setUnlocalizedName(registryName);
        setCreativeTab(ModCreativeTabs.tabBlock);
        setRegistryName(registryName);
    }

    @Override
    public Block setUnlocalizedName(String name) {
        pureName = name;
        return super.setUnlocalizedName(name);
    }

    public String getPureName() {
        return pureName;
    }

    @Override
    public String getItemModelPath(int meta) {
        return RefStrings.MODID + ":" + pureName;
    }
}
