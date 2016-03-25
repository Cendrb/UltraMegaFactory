package com.example.ultramegafactory.blocks;

import com.cendrb.rododendron.creative_tab.CreativeTabRodo;
import com.cendrb.rododendron.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by cendr_000 on 3. 7. 2015.
 */
public class BlockGeneric extends Block {

    protected BlockGeneric(Material material) {
        super(material);
    }

    public BlockGeneric()
    {
        this(Material.rock);
        setCreativeTab(CreativeTabRodo.RODODENDRON_TAB);
        setHardness(2.0F);
        setHarvestLevel("pickaxe", 2);
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
