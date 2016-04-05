package com.mandatoryfun.ultramegafactory.block;

import com.mandatoryfun.ultramegafactory.init.ModItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by Wester on 3/28/2016.
 */
public class BlockKaolineOre extends BlockGeneric
{
    public BlockKaolineOre()
    {
        super("kaoline_ore", Material.clay, 1, 5, "shovel", 0);

    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModItems.Dust.kaoline;

    }

    @Override
    public int quantityDropped(Random random) {
        return 4;
    }
}
