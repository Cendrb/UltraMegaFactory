package com.mandatoryfun.ultramegafactory.tileentity;

import com.mandatoryfun.ultramegafactory.init.ModItems;
import com.mandatoryfun.ultramegafactory.init.UMFRecipes;
import com.mandatoryfun.ultramegafactory.lib.UMFLogger;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

/**
 * Created by cendr_000 on 30.03.2016.
 */
public class TileEntityBlastFurnaceController extends TileEntity implements ITickable {

    private static final String INPUT_INVENTORY_KEY = "input_inventory";
    private static final String OUTPUT_INVENTORY_KEY = "output_inventory";

    private ItemStackHandler handlerInput;
    private ItemStackHandler handlerOutput = new ItemStackHandler();

    public TileEntityBlastFurnaceController() {
        handlerInput = new InputHandler(128);
    }

    @Override
    public void update() {
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return true;
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == EnumFacing.DOWN)
                return (T) handlerOutput;
            else
                return (T) handlerInput;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        handlerInput.deserializeNBT(compound.getCompoundTag(INPUT_INVENTORY_KEY));
        handlerOutput.deserializeNBT(compound.getCompoundTag(OUTPUT_INVENTORY_KEY));
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag(INPUT_INVENTORY_KEY, handlerInput.serializeNBT());
        compound.setTag(OUTPUT_INVENTORY_KEY, handlerOutput.serializeNBT());
    }

    public class InputHandler extends ItemStackHandler {

        private final int CATEGORIES_COUNT = 3;
        private final int SLOTS_PER_CATEGORY = 9;

        private final int ORE_CATEGORY_FIRST_SLOT = 0;
        private final int REDUCING_AGENT_CATEGORY_FIRST_SLOT = ORE_CATEGORY_FIRST_SLOT + 9;
        private final int BULLSHIT_CREATOR_CATEGORY_FIRST_SLOT = REDUCING_AGENT_CATEGORY_FIRST_SLOT + 9;

        private int currentNumberOfItems = 0;
        private int capacity;

        public InputHandler(int capacity) {
            super();
            setSize(CATEGORIES_COUNT * SLOTS_PER_CATEGORY);// set number of slots 3*9
            setCapacity(capacity);
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {

            if (stack == null || stack.stackSize == 0)
                return null;

            Item item = stack.getItem();

            if(UMFRecipes.BlastFurnace.isValidOre(item))
            {
                if(slot >= ORE_CATEGORY_FIRST_SLOT && slot < REDUCING_AGENT_CATEGORY_FIRST_SLOT)
                {
                    return insertInto(slot, stack);
                }
                else
                    return stack;
            }
            else if(UMFRecipes.BlastFurnace.isReducingAgent(item))
            {
                if(slot >= REDUCING_AGENT_CATEGORY_FIRST_SLOT && slot < BULLSHIT_CREATOR_CATEGORY_FIRST_SLOT)
                {
                    return insertInto(slot, stack);
                }
                else
                    return stack;
            }
            else if(UMFRecipes.BlastFurnace.isBullshitCreator(item))
            {
                if(slot >= BULLSHIT_CREATOR_CATEGORY_FIRST_SLOT && slot < BULLSHIT_CREATOR_CATEGORY_FIRST_SLOT + SLOTS_PER_CATEGORY)
                {
                    return insertInto(slot, stack);
                }
                else
                    return stack;
            }
            else
                return stack;
        }

        public void setCapacity(int capacity) {
            if (capacity > 0) {
                this.capacity = capacity;
            } else
                throw new RuntimeException("Capacity needs to be higher than zero");
        }

        private ItemStack insertInto(int slot, ItemStack stack)
        {
            // check for slot
            ItemStack superReturned = super.insertItem(slot, stack, true);

            // check for capacity
            int itemsLeft = canInsert(stack.stackSize);

            if((superReturned == null || superReturned.stackSize < stack.stackSize) && itemsLeft < stack.stackSize)
            {
                // slot is not full and capacity not reached
                int greaterLimitation = Math.max(superReturned.stackSize, itemsLeft);

                UMFLogger.logInfo("Inserting " + stack.getItem().getRegistryName() + " to slot " + slot);

                ItemStack existing = stacks[slot];
                if(existing == null)
                {
                    stacks[slot] = ItemHandlerHelper.copyStackWithSize(stack, stack.stackSize - greaterLimitation);
                }
                else {
                    existing.stackSize = existing.stackSize - (stack.stackSize - greaterLimitation);
                }

                if(greaterLimitation > 0)
                    // if limited return the rest
                    return ItemHandlerHelper.copyStackWithSize(stack, greaterLimitation);
                else
                    // if not then just return nothing
                    return null;
            }
            else
                return stack;
        }

        private int canInsert(int numberOfItems)
        {
            if(currentNumberOfItems + numberOfItems <= capacity)
            {
                // nothing is left; insert everything
                return 0;
            }
            else
            {
                // something will be left
                return (currentNumberOfItems + numberOfItems) - capacity;
            }
        }
    }
}
