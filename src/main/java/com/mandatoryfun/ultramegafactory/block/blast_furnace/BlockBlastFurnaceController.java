package com.mandatoryfun.ultramegafactory.block.blast_furnace;

import com.mandatoryfun.ultramegafactory.Core;
import com.mandatoryfun.ultramegafactory.block.BlockGenericContainer;
import com.mandatoryfun.ultramegafactory.block.IBlockMultipleNames;
import com.mandatoryfun.ultramegafactory.client.gui.GuiHandler;
import com.mandatoryfun.ultramegafactory.lib.UMFLogger;
import com.mandatoryfun.ultramegafactory.tileentity.TileEntityBlastFurnaceController;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by cendr_000 on 28.03.2016.
 */
public class BlockBlastFurnaceController extends BlockGenericContainer implements IBlockMultipleNames {

    final static IProperty<Integer> TIER = PropertyInteger.create("tier", 1, 2);
    final static IProperty<EnumFacing> FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public BlockBlastFurnaceController() {
        super("blast_furnace_controller", Material.iron);
        setDefaultState(blockState.getBaseState().withProperty(TIER, 1).withProperty(FACING, EnumFacing.NORTH));
        setHardness(3);
        setResistance(15);
        setHarvestLevel("pickaxe", 1);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, TIER, FACING);
    }

    @Override
    public int damageDropped(IBlockState state) {
        int stateTier = state.getValue(TIER);
        if (stateTier == 1)
            return 0;
        else if (stateTier == 2)
            return 1;
        else
            return 0;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.getItemFromBlock(this), 1, damageDropped(world.getBlockState(pos)));
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        // add items to creative tabs in both states - both "damages"
        list.add(new ItemStack(itemIn, 1, 0));
        list.add(new ItemStack(itemIn, 1, 1));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (stack.getItemDamage() == 0)
            // tier 1
            state = state.withProperty(TIER, 1);
        else
            // tier 2
            state = state.withProperty(TIER, 2);

        state = state.withProperty(FACING, placer.getHorizontalFacing());

        worldIn.setBlockState(pos, state);

        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        IBlockState base = blockState.getBaseState();
        if (base.withProperty(TIER, 1).withProperty(FACING, EnumFacing.NORTH) == state)
            return 0;
        else if (state.withProperty(TIER, 1).withProperty(FACING, EnumFacing.WEST) == state)
            return 1;
        else if (state.withProperty(TIER, 1).withProperty(FACING, EnumFacing.SOUTH) == state)
            return 2;
        else if (state.withProperty(TIER, 1).withProperty(FACING, EnumFacing.EAST) == state)
            return 3;
        else if (state.withProperty(TIER, 2).withProperty(FACING, EnumFacing.NORTH) == state)
            return 4;
        else if (state.withProperty(TIER, 2).withProperty(FACING, EnumFacing.WEST) == state)
            return 5;
        else if (state.withProperty(TIER, 2).withProperty(FACING, EnumFacing.SOUTH) == state)
            return 6;
        else if (state.withProperty(TIER, 2).withProperty(FACING, EnumFacing.EAST) == state)
            return 7;
        return 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState base = blockState.getBaseState();
        switch (meta) {
            case 0:
                return base.withProperty(TIER, 1).withProperty(FACING, EnumFacing.NORTH);
            case 1:
                return base.withProperty(TIER, 1).withProperty(FACING, EnumFacing.WEST);
            case 2:
                return base.withProperty(TIER, 1).withProperty(FACING, EnumFacing.SOUTH);
            case 3:
                return base.withProperty(TIER, 1).withProperty(FACING, EnumFacing.EAST);
            case 4:
                return base.withProperty(TIER, 2).withProperty(FACING, EnumFacing.NORTH);
            case 5:
                return base.withProperty(TIER, 2).withProperty(FACING, EnumFacing.WEST);
            case 6:
                return base.withProperty(TIER, 2).withProperty(FACING, EnumFacing.SOUTH);
            case 7:
                return base.withProperty(TIER, 2).withProperty(FACING, EnumFacing.EAST);
            default:
                return getDefaultState();
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

        if (worldIn.isRemote)
        {
            // not needed on server side - server gets called automatically
            return true;
        }
        else
        {
            UMFLogger.logInfo("PENISOSOSIOSOsSJODJOSJDOOSJDOJSODJOSJDPOSJJODOSJDOJSODJOSJDOJSODJOSJDO_JSO_DjOSJDOSJDOjs");
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityBlastFurnaceController)
            {
                UMFLogger.logInfo("DILDOIDLIDLIDLIDLIDLID");
                playerIn.openGui(Core.instance, GuiHandler.GuiEnum.BLAST_FURNACE.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
                // calls GuiHandler
            }

            return true;
        }
    }

    @Override
    public String getSpecialNameEnding(ItemStack stack) {
        int damage = stack.getItemDamage();
        switch (damage) {
            case 0:
                return "t1";
            case 1:
                return "t2";
            default:
                return "unknownDamageValue";
        }
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityBlastFurnaceController();
    }
}
