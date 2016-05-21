package com.mandatoryfun.ultramegafactory.block.machinery;

import com.google.common.base.Predicate;
import com.mandatoryfun.ultramegafactory.block.BlockGeneric;
import com.mandatoryfun.ultramegafactory.block.IBlockMultipleMetas;
import com.mandatoryfun.ultramegafactory.lib.CasingModelType;
import com.mandatoryfun.ultramegafactory.lib.NameHelper;
import com.mandatoryfun.ultramegafactory.lib.RefStrings;
import com.mandatoryfun.ultramegafactory.tileentity.TileEntityGenericTier;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by cendr_000 on 03.04.2016.
 */
public abstract class BlockGenericTierCasing extends BlockGeneric implements IBlockMultipleMetas, ITileEntityProvider {

    private IProperty<Integer> TIER;
    private IProperty<CasingModelType> CASING_MODEL_TYPE;

    public BlockGenericTierCasing(String unlocalizedName) {
        super(unlocalizedName, Material.iron, 3, 15, "pickaxe", 1);
        setDefaultState(blockState.getBaseState().withProperty(getTIER(), getMinTier()));

    }

    private IProperty<CasingModelType> getCASING_MODEL_TYPE()
    {
        if(CASING_MODEL_TYPE == null)
            CASING_MODEL_TYPE = PropertyEnum.create("casing_model_type", CasingModelType.class);
        return CASING_MODEL_TYPE;
    }

    private IProperty<Integer> getTIER() {
        if (TIER == null)
            TIER = PropertyInteger.create("tier", getMinTier(), getMaxTier());
        return TIER;
    }

    public IProperty<Integer> getTier() {
        return TIER;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, getTIER());
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(TIER) - 1;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        state = state.withProperty(TIER, stack.getItemDamage() + 1);

        worldIn.setBlockState(pos, state);

        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public void getSubBlocks(final Item itemIn, CreativeTabs tab, final List<ItemStack> list) {
        // add items to creative tabs in both states - both "damages"
        runForEachTier(new Predicate<Integer>() {
            @Override
            public boolean apply(@Nullable Integer input) {
                list.add(new ItemStack(itemIn, 1, input - 1));
                return true;
            }
        });
    }


    @Override
    public IBlockState getStateFromMeta(int meta) {
        return blockState.getBaseState().withProperty(TIER, meta + 1);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return damageDropped(state);
    }

    @Override
    public String getSpecialNameEnding(ItemStack stack) {
        return NameHelper.getSpecialEndingWithNumber("t", stack.getItemDamage() + 1, "");
    }

    @Override
    public String getItemModelPath(int meta) {
        return RefStrings.MODID + ":blast_furnace/" + getPureName() + "_" + NameHelper.getSpecialEndingWithNumber("t", meta + 1, "");
    }

    public void runForEachTier(Predicate<Integer> runnable) {
        for (int tier = getMinTier(); tier <= getMaxTier(); tier++)
            runnable.apply(tier);
    }

    public abstract int getMinTier();

    public abstract int getMaxTier();

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityGenericTier();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        ((TileEntityGenericTier) worldIn.getTileEntity(pos)).notifyControllerToRebuild();
        super.breakBlock(worldIn, pos, state);
    }
}
