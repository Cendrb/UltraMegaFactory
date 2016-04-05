package com.mandatoryfun.ultramegafactory.block;

import com.mandatoryfun.ultramegafactory.init.ModItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by Wester on 4/5/2016.
 */
public class BlockPeatOre extends BlockGenericOre
{

        public BlockPeatOre(String[] descr)
        {
            super("peat_ore", Material.ground, 1, 5, "shovel", 0, "", descr);
        }

        @Override
        public Item getItemDropped(IBlockState state, Random rand, int fortune) {
            return ModItems.peat;

        }

        @Override
        public int quantityDropped(Random random) {
            return 4;
        }
}
