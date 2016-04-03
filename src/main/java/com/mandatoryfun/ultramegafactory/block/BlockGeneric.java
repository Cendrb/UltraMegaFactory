package com.mandatoryfun.ultramegafactory.block;

import com.mandatoryfun.ultramegafactory.creativetab.ModCreativeTabs;
import com.mandatoryfun.ultramegafactory.lib.IRegisterRender;
import com.mandatoryfun.ultramegafactory.lib.RefStrings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameData;

import java.util.Random;

/**
 * Created by cendr_000 on 3. 7. 2015.
 */
public class BlockGeneric extends Block implements IPureName, IRegisterRender {

    private String pureName;
    private boolean registerRender = true;

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

    @Override
    public void setRegisterRender(boolean registerRender) {
        this.registerRender = registerRender;
    }

    @Override
    public boolean getRegisterRender() {
        return registerRender;
    }
}
