package com.mandatoryfun.ultramegafactory.block;

import com.mandatoryfun.ultramegafactory.init.ModBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;

/**
 * Created by cendr_000 on 28.03.2016.
 */
public class BlockBlastFurnaceController extends BlockGeneric {

    IProperty<Integer> tier;
    IProperty<EnumFacing> facing;

    public BlockBlastFurnaceController() {
        super("blast_furnace_controller", Material.iron, 3, 15, "pickaxe", 1);
        setDefaultState(blockState.getBaseState().withProperty(tier, 1).withProperty(facing, EnumFacing.NORTH));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        tier = PropertyInteger.create("tier", 1, 2);
        facing = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
        return new BlockStateContainer(ModBlocks.blastFurnaceController, tier, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        IBlockState base = blockState.getBaseState();
        if (base.withProperty(tier, 1).withProperty(facing, EnumFacing.NORTH) == state)
            return 0;
        else if (state.withProperty(tier, 1).withProperty(facing, EnumFacing.WEST) == state)
            return 1;
        else if (state.withProperty(tier, 1).withProperty(facing, EnumFacing.SOUTH) == state)
            return 2;
        else if (state.withProperty(tier, 1).withProperty(facing, EnumFacing.EAST) == state)
            return 3;
        else if (state.withProperty(tier, 2).withProperty(facing, EnumFacing.NORTH) == state)
            return 4;
        else if (state.withProperty(tier, 2).withProperty(facing, EnumFacing.WEST) == state)
            return 5;
        else if (state.withProperty(tier, 2).withProperty(facing, EnumFacing.SOUTH) == state)
            return 6;
        else if (state.withProperty(tier, 2).withProperty(facing, EnumFacing.EAST) == state)
            return 7;
        return 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState base = blockState.getBaseState();
        switch (meta) {
            case 0:
                return base.withProperty(tier, 1).withProperty(facing, EnumFacing.NORTH);
            case 1:
                return base.withProperty(tier, 1).withProperty(facing, EnumFacing.WEST);
            case 2:
                return base.withProperty(tier, 1).withProperty(facing, EnumFacing.SOUTH);
            case 3:
                return base.withProperty(tier, 1).withProperty(facing, EnumFacing.EAST);
            case 4:
                return base.withProperty(tier, 2).withProperty(facing, EnumFacing.NORTH);
            case 5:
                return base.withProperty(tier, 2).withProperty(facing, EnumFacing.WEST);
            case 6:
                return base.withProperty(tier, 2).withProperty(facing, EnumFacing.SOUTH);
            case 7:
                return base.withProperty(tier, 2).withProperty(facing, EnumFacing.EAST);
            default:
                return getDefaultState();
        }
    }
}
