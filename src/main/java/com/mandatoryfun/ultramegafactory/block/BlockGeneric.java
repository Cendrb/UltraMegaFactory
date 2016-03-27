package com.mandatoryfun.ultramegafactory.block;

import com.mandatoryfun.ultramegafactory.creativetab.ModCreativeTabs;
import com.mandatoryfun.ultramegafactory.lib.RefStrings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameData;

/**
 * Created by cendr_000 on 3. 7. 2015.
 */
public class BlockGeneric extends Block {

    private String pureName;

    public BlockGeneric(String unlocalizedName) {
        this(unlocalizedName, Material.rock);
    }


    public BlockGeneric(String unlocalizedName, Material material) {
        super(material);
        setCreativeTab(ModCreativeTabs.tabBlock);
        setUnlocalizedName(unlocalizedName);
    }

    public BlockGeneric(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);
        setHardness(hardness);
        setResistance(resistance);
        setCreativeTab(ModCreativeTabs.tabBlock);
        setUnlocalizedName(unlocalizedName);
    }

    public BlockGeneric(String unlocalizedName, Material material, float hardness, float resistance, String toolClass, int toolLevel) {
        super(material);
        setHarvestLevel(toolClass, toolLevel);
        setHardness(hardness);
        setResistance(resistance);
        setCreativeTab(ModCreativeTabs.tabBlock);
        setUnlocalizedName(unlocalizedName);
    }

    @Override
    public Block setUnlocalizedName(String name) {
        pureName = name;
        return super.setUnlocalizedName(name);
    }

    public String getIdentifier() {
        return RefStrings.MODID + ":" + pureName;
    }

    public String getPureName() {
        return pureName;
    }
}
