package com.mandatoryfun.ultramegafactory.tileentity;

import com.mandatoryfun.ultramegafactory.block.machinery.blast_furnace.BlastFurnaceMultiblock;
import com.mandatoryfun.ultramegafactory.block.machinery.blast_furnace.gui.ContainerBlastFurnace;
import com.mandatoryfun.ultramegafactory.init.ModItems;
import com.mandatoryfun.ultramegafactory.init.UMFRecipes;
import com.mandatoryfun.ultramegafactory.init.UMFRegistry;
import com.mandatoryfun.ultramegafactory.lib.UMFLogger;
import com.mandatoryfun.ultramegafactory.lib.WorldHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
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

import java.util.Arrays;

/**
 * Created by cendr_000 on 30.03.2016.
 */
public class TileEntityBlastFurnaceController extends TileEntity implements ITickable, IInteractionObject {

    private BlastFurnaceMultiblock multiblock;

    private InputItemStackHandler handlerInput;
    private OutputItemStackHandler handlerOutput;
    private FuelItemStackHandler handlerFuel;
    private SampleItemStackHandler handlerSample;

    private boolean initTried = false;

    public TileEntityBlastFurnaceController() {
        multiblock = new BlastFurnaceMultiblock();

        handlerInput = new InputItemStackHandler();
        handlerOutput = new OutputItemStackHandler();
        handlerFuel = new FuelItemStackHandler();
        handlerSample = new SampleItemStackHandler();
    }

    @Override
    public void update() {
        if (!initTried) {
            initTried = true;
            if (getData() != null)
                getData().loadCurrentBlockstates(worldObj);
        }
        if (!worldObj.isRemote) {
            if (multiblock.getData() != null) {
                if (!multiblock.getData().isBurning() && handlerFuel.isFueled()) {
                    multiblock.getData().burnFuel(handlerFuel.consumeFuel());
                }
                int[] ironData = multiblock.getData().update();
                if (ironData[0] > 0) {
                    int meta = ironData[1] / 1000;
                    handlerSample.setSample(null);
                    handlerOutput.setItems(new ItemStack(ModItems.Ingot.iron, 1, meta), ironData[0]);
                }
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
        if(multiblock.getData() != null)
            dropAllItems();
        String result = multiblock.rebuild(facing, pos, world, tier);
        if(result != "SUCCESS")
            multiblock.invalidate();
        markDirty();
        return result;
    }

    public void startReaction() {
        if (multiblock.getData() != null && handlerOutput.isEmpty()) {
            Item ore = handlerInput.getCurrentOre();
            if (multiblock.getData().startReaction(handlerInput)) {
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

    public BlastFurnaceMultiblock.Data getData() {
        return multiblock.getData();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("data"))
            multiblock.deserializeData(compound.getCompoundTag("data"));
        handlerInput.deserializeNBT(compound.getCompoundTag("input"));
        handlerOutput.deserializeNBT(compound.getCompoundTag("output"));
        handlerFuel.deserializeNBT(compound.getCompoundTag("fuel"));
        handlerSample.deserializeNBT(compound.getCompoundTag("sample"));
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (getData() != null)
            compound.setTag("data", getData().serializeNBT());
        compound.setTag("input", handlerInput.serializeNBT());
        compound.setTag("output", handlerOutput.serializeNBT());
        compound.setTag("fuel", handlerFuel.serializeNBT());
        compound.setTag("sample", handlerSample.serializeNBT());
    }

    @Override
    public Packet<?> getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new SPacketUpdateTileEntity(pos, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    public void dropAllItems() {
        double dropX = pos.getX() + 0.5;
        double dropY = pos.getY() + 0.5;
        double dropZ = pos.getZ() + 0.5;

        for (int x = handlerInput.getSlots() - 1; x > -1; x--) {
            ItemStack stack;
            if ((stack = handlerInput.getStackInSlot(x)) != null)
                WorldHelper.spawnItemStack(worldObj, stack, dropX, dropY, dropZ);
        }
        handlerInput.clear();

        ItemStack stack;
        if ((stack = handlerFuel.getStackInSlot(0)) != null)
            WorldHelper.spawnItemStack(worldObj, stack, dropX, dropY, dropZ);
        handlerFuel.clear();

        int fullStacks = handlerOutput.currentNumberOfItems / 64;
        int lastStackSize = handlerOutput.currentNumberOfItems % 64;
        if (lastStackSize > 0)
            WorldHelper.spawnItemStack(worldObj, new ItemStack(handlerOutput.currentItem.getItem(), lastStackSize, handlerOutput.currentItem.getItemDamage()), dropX, dropY, dropZ);
        for(int remainingStacks = fullStacks; remainingStacks > 0; remainingStacks--)
            WorldHelper.spawnItemStack(worldObj, new ItemStack(handlerOutput.currentItem.getItem(), 64, handlerOutput.currentItem.getItemDamage()), dropX, dropY, dropZ);
        handlerOutput.clear();
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

        private Item currentOre;

        public InputItemStackHandler() {
            super();
            setSize(CATEGORIES_COUNT * SLOTS_PER_CATEGORY);// set number of slots 3*9
        }

        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            int totalItems = 0;
            for (int x = ORE_CATEGORY_FIRST_SLOT; x < 27; x++)
                if (stacks[x] != null)
                    totalItems += stacks[x].stackSize;
            currentNumberOfItems = totalItems;
        }

        @Override
        public void setStackInSlot(int slot, ItemStack stack) {
            UMFLogger.logInfo("Running setStackInSlot()...");
            ItemStack previous = getStackInSlot(slot);
            if (ItemStack.areItemStacksEqual(this.stacks[slot], stack)) // needs to be here too, the super one wont stop this method
                return;
            super.setStackInSlot(slot, stack);
            if (stack != null) {
                if (UMFRecipes.BlastFurnace.isValidOre(stack.getItem()))
                    currentOre = stack.getItem();
                UMFLogger.logInfo("Setting " + stack.stackSize + "*" + stack.getItem().getRegistryName() + " into " + slot);
                UMFLogger.logInfo("Current number of items: " + currentNumberOfItems);
            }
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {

            if (stack == null || stack.stackSize == 0)
                return null;

            Item item = stack.getItem();

            if (UMFRecipes.BlastFurnace.isValidOre(item) && (item == currentOre || currentOre == null)) {
                if (slot >= ORE_CATEGORY_FIRST_SLOT && slot < REDUCING_AGENT_CATEGORY_FIRST_SLOT) {
                    if (!simulate)
                        currentOre = item;
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

        public ItemStack[] clear() {
            ItemStack[] itemStacks = Arrays.copyOf(stacks, CATEGORIES_COUNT * SLOTS_PER_CATEGORY);
            currentOre = null;
            //currentNumberOfItems = 0;
            for (int x = ORE_CATEGORY_FIRST_SLOT; x < 27; x++)
                setStackInSlot(x, null);
            return itemStacks;
        }

        public int getCurrentNumberOfItems() {
            return currentNumberOfItems;
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
            if (!simulate && superReturned != null) {
                if (slot >= ORE_CATEGORY_FIRST_SLOT && slot < REDUCING_AGENT_CATEGORY_FIRST_SLOT) {
                    if (getStackInSlot(slot) == null) {
                        // check all ore slots
                        boolean slotsEmpty = true;
                        for (int x = ORE_CATEGORY_FIRST_SLOT; x < REDUCING_AGENT_CATEGORY_FIRST_SLOT; x++)
                            if (getStackInSlot(x) != null)
                                slotsEmpty = false;
                        if (slotsEmpty)
                            currentOre = null;
                    }
                }
            }
            UMFLogger.logInfo("Current number of items: " + currentNumberOfItems);
            return superReturned;
        }

        private int canInsert(int numberOfItems) {
            int capacity = getData().getCapacity();
            if (currentNumberOfItems + numberOfItems <= capacity) {
                // nothing is left; insert everything
                return 0;
            } else {
                // something will be left
                return (currentNumberOfItems + numberOfItems) - capacity;
            }
        }

        public Item getCurrentOre() {
            return currentOre;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return super.serializeNBT();
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            super.deserializeNBT(nbt);
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
            return stacks[0] != null;
        }


        private int consumeFuel() {
            if (isFueled()) {
                int energyValue = UMFRegistry.Fuels.getJEnergyValue(stacks[0].getItem());
                stacks[0].stackSize--;
                if (stacks[0].stackSize == 0)
                    stacks[0] = null;
                return energyValue;
            } else
                return 0;
        }

        public void clear() {
            stacks[0] = null;
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

        public void clear() {
            currentItem = null;
            currentNumberOfItems = 0;
            stacks[0] = null;
        }
    }

    private class SampleItemStackHandler extends ItemStackHandler {
        public SampleItemStackHandler() {
            setSize(1);

        }

        public void setSample(ItemStack stack) {
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
