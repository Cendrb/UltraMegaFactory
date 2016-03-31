package com.mandatoryfun.ultramegafactory.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.IItemHandler;

/**
 * Created by cendr_000 on 30.03.2016.
 */
public class TileEntityBlastFurnaceController extends TileEntity implements IItemHandler, ITickable {

    public static final int INPUT_SLOTS_COUNT = 27;
    public static final int OUTPUT_SLOTS_COUNT = 1;
    public static final int TOTAL_SLOTS_COUNT = INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT;

    public static final int FIRST_INPUT_SLOT = 0;
    public static final int FIRST_OUTPUT_SLOT = FIRST_INPUT_SLOT + INPUT_SLOTS_COUNT;

    private ItemStack[] inventoryStacks = new ItemStack[TOTAL_SLOTS_COUNT];

    @Override
    public int getSlots() {
        return TOTAL_SLOTS_COUNT;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inventoryStacks[i];
    }

    @Override
    public ItemStack insertItem(int i, ItemStack itemStack, boolean b) {
        return null;
    }

    @Override
    public ItemStack extractItem(int i, int i1, boolean b) {
        return null;
    }

    @Override
    public void update() {

    }
}
