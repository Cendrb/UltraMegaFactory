package com.mandatoryfun.ultramegafactory.tileentity;

import com.mandatoryfun.ultramegafactory.block.machinery.blast_furnace.BlastFurnaceMultiblock;
import com.mandatoryfun.ultramegafactory.block.machinery.blast_furnace.gui.ContainerBlastFurnace;
import com.mandatoryfun.ultramegafactory.init.ModBlocks;
import com.mandatoryfun.ultramegafactory.init.ModItems;
import com.mandatoryfun.ultramegafactory.init.UMFRecipes;
import com.mandatoryfun.ultramegafactory.init.UMFRegistry;
import com.mandatoryfun.ultramegafactory.init.recipe.BlastFurnaceRecipe;
import com.mandatoryfun.ultramegafactory.lib.UMFLogger;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

/**
 * Created by cendr_000 on 30.03.2016.
 */
public class TileEntityBlastFurnaceController extends TileEntity implements ITickable, IInteractionObject {

    private BlastFurnaceMultiblock multiblock;

    private InputItemStackHandler handlerInput;
    private OutputItemStackHandler handlerOutput;
    private FuelItemStackHandler handlerFuel;
    private SampleItemStackHandler handlerSample;

    private BlastFurnaceMultiblock.Data data;

    public TileEntityBlastFurnaceController() {
        multiblock = new BlastFurnaceMultiblock();

        handlerInput = new InputItemStackHandler(128);
        handlerOutput = new OutputItemStackHandler();
        handlerFuel = new FuelItemStackHandler();
        handlerSample = new SampleItemStackHandler();
    }

    @Override
    public void update() {
        if(data != null) {
            if (!data.isBurning() && handlerFuel.isFueled()) {
                data.burnFuel(handlerFuel.consumeFuel());
            }
            int ironQuality = data.update();
            if(ironQuality > 0)
            {
                int meta = ironQuality / 1000;
                handlerOutput.setItems(new ItemStack(ModItems.Ingot.iron, 1, meta), 420);
            }
        }
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return true;
        return super.hasCapability(capability, facing);
    }

    public String rebuildMultiblock(EnumFacing facing, BlockPos pos, World world, int tier) {
        return multiblock.rebuild(facing, pos, world, tier);
    }

    public void startReaction() {
        if (data != null) {
            Block ore = ModBlocks.Ore.magnetite;
            BlastFurnaceRecipe recipe = UMFRecipes.BlastFurnace.getRecipeForOre(ore);
            if (recipe != null) {
                data.startReaction(recipe);
                handlerInput.clear();
                handlerSample.setSample(new ItemStack(ore, 1));
            }
        }
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == EnumFacing.DOWN)
                return (T) handlerOutput;
            else if (facing == EnumFacing.UP)
                return (T) handlerFuel;
            else
                return (T) handlerInput;
        }
        return super.getCapability(capability, facing);
    }

    public InputItemStackHandler getHandlerInput() {
        return handlerInput;
    }

    public ItemStackHandler getHandlerFuel() {
        return handlerFuel;
    }

    public ItemStackHandler getHandlerOutput() {
        return handlerOutput;
    }

    public ItemStackHandler getHandlerSample() {
        return handlerSample;
    }

    public String getTemperature() {
        if (data != null)
            return String.valueOf(data.getCurrentTemperature());
        else
            return "Structure not complete!";
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        handlerInput.deserializeNBT(compound.getCompoundTag("input"));
        handlerOutput.deserializeNBT(compound.getCompoundTag("output"));
        handlerFuel.deserializeNBT(compound.getCompoundTag("fuel"));
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("input", handlerInput.serializeNBT());
        compound.setTag("output", handlerOutput.serializeNBT());
        compound.setTag("fuel", handlerFuel.serializeNBT());
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerBlastFurnace(playerInventory, this);
    }

    @Override
    public String getGuiID() {
        return "blast_furnace_controller";
    }

    @Override
    public String getName() {
        return "Blast Furnace Controller";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString("Blast Furnace Controller");
    }

    public class InputItemStackHandler extends ItemStackHandler {

        private final int CATEGORIES_COUNT = 3;
        private final int SLOTS_PER_CATEGORY = 9;

        private final int ORE_CATEGORY_FIRST_SLOT = 0;
        private final int REDUCING_AGENT_CATEGORY_FIRST_SLOT = ORE_CATEGORY_FIRST_SLOT + 9;
        private final int BULLSHIT_CREATOR_CATEGORY_FIRST_SLOT = REDUCING_AGENT_CATEGORY_FIRST_SLOT + 9;

        private int currentNumberOfItems = 0;

        private int capacity;

        public InputItemStackHandler(int capacity) {
            super();
            setSize(CATEGORIES_COUNT * SLOTS_PER_CATEGORY);// set number of slots 3*9
            setCapacity(capacity);
        }

        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
        }

        @Override
        public void setStackInSlot(int slot, ItemStack stack) {
            UMFLogger.logInfo("Running setStackInSlot()...");
            ItemStack previous = getStackInSlot(slot);
            if (ItemStack.areItemStacksEqual(this.stacks[slot], stack)) // needs to be here too, the super one wont stop this method
                return;
            super.setStackInSlot(slot, stack);
            UMFLogger.logInfo("Setting " + stack.stackSize + "*" + stack.getItem().getRegistryName() + " into " + slot);
            if (previous == null)
                currentNumberOfItems += stack.stackSize;
            else {
                UMFLogger.logInfo("Previous stack: " + previous.stackSize + "*" + previous.getItem().getRegistryName());
                currentNumberOfItems += stack.stackSize - previous.stackSize;
            }
            UMFLogger.logInfo("Current number of items: " + currentNumberOfItems);
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {

            if (stack == null || stack.stackSize == 0)
                return null;

            Item item = stack.getItem();

            if (UMFRecipes.BlastFurnace.isValidOre(item)) {
                if (slot >= ORE_CATEGORY_FIRST_SLOT && slot < REDUCING_AGENT_CATEGORY_FIRST_SLOT) {
                    return insertInto(slot, stack, simulate);
                } else
                    return stack;
            } else if (UMFRecipes.BlastFurnace.isReducingAgent(item)) {
                if (slot >= REDUCING_AGENT_CATEGORY_FIRST_SLOT && slot < BULLSHIT_CREATOR_CATEGORY_FIRST_SLOT) {
                    return insertInto(slot, stack, simulate);
                } else
                    return stack;
            } else if (UMFRecipes.BlastFurnace.isBullshitCreator(item)) {
                if (slot >= BULLSHIT_CREATOR_CATEGORY_FIRST_SLOT && slot < BULLSHIT_CREATOR_CATEGORY_FIRST_SLOT + SLOTS_PER_CATEGORY) {
                    return insertInto(slot, stack, simulate);
                } else
                    return stack;
            } else
                return stack;
        }

        public void clear()
        {
            for(int i = 0; i < getSlots(); i++)
                stacks[i] = null;
        }

        public int getCurrentNumberOfItems() {
            return currentNumberOfItems;
        }

        public void setCurrentNumberOfItems(int currentNumberOfItems) {
            this.currentNumberOfItems = currentNumberOfItems;
        }

        public void setCapacity(int capacity) {
            if (capacity > 0) {
                this.capacity = capacity;
            } else
                throw new RuntimeException("Capacity needs to be higher than zero");
        }

        public int getCapacity() {
            return capacity;
        }

        private ItemStack insertInto(int slot, ItemStack stack, boolean simulate) {
            // check for slot
            ItemStack superReturned = super.insertItem(slot, stack, true);
            int superStackSize = (superReturned == null) ? 0 : superReturned.stackSize;

            // check for capacity
            int itemsLeft = canInsert(stack.stackSize);

            if ((superStackSize < stack.stackSize) && itemsLeft < stack.stackSize) {
                // slot is not full and capacity not reached
                int greaterLimitation = Math.max(superStackSize, itemsLeft);

                UMFLogger.logInfo("Inserting " + stack.stackSize + "*" + stack.getItem().getRegistryName() + " to slot " + slot + " simulate " + simulate);

                if (!simulate) {
                    ItemStack existing = stacks[slot];
                    if (existing == null) {
                        stacks[slot] = ItemHandlerHelper.copyStackWithSize(stack, stack.stackSize - greaterLimitation);
                    } else {
                        existing.stackSize = existing.stackSize + (stack.stackSize - greaterLimitation);
                    }

                    currentNumberOfItems += stack.stackSize - greaterLimitation;
                }
                UMFLogger.logInfo("Current number of items: " + currentNumberOfItems);

                if (greaterLimitation > 0)
                    // if limited return the rest
                    return ItemHandlerHelper.copyStackWithSize(stack, greaterLimitation);
                else
                    // if not then just return nothing
                    return null;
            } else
                return stack;
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            ItemStack superReturned = super.extractItem(slot, amount, simulate);
            UMFLogger.logInfo("Extracting " + amount + " from " + slot + " simulate " + simulate);
            if (!simulate && superReturned != null)
                currentNumberOfItems -= superReturned.stackSize;
            UMFLogger.logInfo("Current number of items: " + currentNumberOfItems);
            return superReturned;
        }

        private int canInsert(int numberOfItems) {
            if (currentNumberOfItems + numberOfItems <= capacity) {
                // nothing is left; insert everything
                return 0;
            } else {
                // something will be left
                return (currentNumberOfItems + numberOfItems) - capacity;
            }
        }
    }

    private class FuelItemStackHandler extends ItemStackHandler {

        public FuelItemStackHandler() {
            setSize(1);
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            if (UMFRegistry.Fuels.isFuel(stack.getItem()))
                return super.insertItem(slot, stack, simulate);
            else
                return stack;
        }

        private boolean isFueled() {
            return getStackInSlot(0) != null;
        }


        private int consumeFuel() {
            if (isFueled()) {
                return UMFRegistry.Fuels.getJEnergyValue(getStackInSlot(0).getItem());
            } else
                return 0;
        }
    }

    private class OutputItemStackHandler extends ItemStackHandler {

        int currentNumberOfItems = 0;
        ItemStack currentItem;

        public OutputItemStackHandler() {

        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            return stack;
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            ItemStack superReturned = super.extractItem(slot, amount, simulate);

            if (!simulate && superReturned != null) {
                currentNumberOfItems -= superReturned.stackSize;
                updateSlot();
            }

            return superReturned;
        }

        private boolean isEmpty() {
            return currentNumberOfItems == 0;
        }

        private boolean setItems(ItemStack item, int count) {
            if (!isEmpty())
                return false;

            currentItem = item;
            currentNumberOfItems = count;

            updateSlot();

            return true;
        }

        private void updateSlot() {
            ItemStack currentStack = getStackInSlot(0);
            int currentStackSize = currentStack == null ? 0 : currentStack.stackSize;
            int requiredMissingToStack = 64 - currentStackSize;
            int toSet = 0;
            if (currentNumberOfItems > requiredMissingToStack)
                toSet = requiredMissingToStack;
            else
                toSet = currentNumberOfItems;
            if (currentStackSize == 0)
                setStackInSlot(0, ItemHandlerHelper.copyStackWithSize(currentItem, toSet));
            else
                currentStack.stackSize += toSet;
        }
    }

    private class SampleItemStackHandler extends ItemStackHandler {
        public SampleItemStackHandler() {
            setSize(1);

        }

        public void setSample(ItemStack stack)
        {
            setStackInSlot(0, stack);
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            return stack;
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            return null;
        }

        @Override
        protected int getStackLimit(int slot, ItemStack stack) {
            return 1;
        }
    }
}
