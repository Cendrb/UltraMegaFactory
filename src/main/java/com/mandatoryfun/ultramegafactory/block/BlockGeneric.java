package com.mandatoryfun.ultramegafactory.block;

import com.mandatoryfun.ultramegafactory.creativetab.ModCreativeTabs;
import com.mandatoryfun.ultramegafactory.lib.RefStrings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by cendr_000 on 3. 7. 2015.
 */
public class BlockGeneric extends Block implements IPureName {

    private String pureName;
    private boolean registerRender = true;

    public BlockGeneric(String registryName) {
        this(registryName, Material.rock);
    }


    public BlockGeneric(String registryName, Material material) {
        super(material);
        setCreativeTab(ModCreativeTabs.tabBlock);
        setUnlocalizedName(registryName);
        setRegistryName(registryName);
    }

    public BlockGeneric(String registryName, Material material, float hardness, float resistance) {
        super(material);
        setHardness(hardness);
        setResistance(resistance);
        setCreativeTab(ModCreativeTabs.tabBlock);
        setUnlocalizedName(registryName);
        setRegistryName(registryName);
    }

    public BlockGeneric(String registryName, Material material, float hardness, float resistance, String toolClass, int toolLevel) {
        super(material);
        setHarvestLevel(toolClass, toolLevel);
        setHardness(hardness);
        setResistance(resistance);
        setCreativeTab(ModCreativeTabs.tabBlock);
        setUnlocalizedName(registryName);
        setRegistryName(registryName);
    }

    @Override
    public final Block setUnlocalizedName(String name) {
        pureName = name;
        return super.setUnlocalizedName(name);
    }

    public String getItemModelPath(int meta) {
        return RefStrings.MODID + ":" + getPureName();
    }

    public final String getPureName() {
        return pureName;
    }
}
