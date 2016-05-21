package com.mandatoryfun.ultramegafactory.block.machinery.blast_furnace;

import com.mandatoryfun.ultramegafactory.block.machinery.BlockGenericTierCasing;
import com.mandatoryfun.ultramegafactory.block.machinery.BlockHeater;
import com.mandatoryfun.ultramegafactory.block.machinery.IBlockHeatable;
import com.mandatoryfun.ultramegafactory.init.UMFRecipes;
import com.mandatoryfun.ultramegafactory.init.UMFRegistry;
import com.mandatoryfun.ultramegafactory.init.recipe.BlastFurnaceRecipe;
import com.mandatoryfun.ultramegafactory.lib.FacingRotator;
import com.mandatoryfun.ultramegafactory.lib.IFieldsSuck;
import com.mandatoryfun.ultramegafactory.lib.RelativeDirection;
import com.mandatoryfun.ultramegafactory.lib.UMFLogger;
import com.mandatoryfun.ultramegafactory.tileentity.TileEntityBlastFurnaceController;
import com.mandatoryfun.ultramegafactory.tileentity.TileEntityGenericTier;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cendr_000 on 03.04.2016.
 */
public class BlastFurnaceMultiblock {

    private HashMap<BlockPos, IBlockState> blocks = new HashMap<BlockPos, IBlockState>();
    private Data data;

    public BlastFurnaceMultiblock() {

    }

    public void invalidate(World world)
    {
        data = null;
        for(Map.Entry<BlockPos, IBlockState> pair : blocks.entrySet())
        {
            TileEntity tileEntity = world.getTileEntity(pair.getKey());
            if(tileEntity instanceof TileEntityGenericTier)
                ((TileEntityGenericTier)tileEntity).setControllerPos(null);
        }
    }

    public String rebuild(EnumFacing facing, BlockPos controllerPos, World world, int controllerTier) {
        blocks.clear();
        FacingRotator rotator = new FacingRotator(facing);
        BlockPos mainHeater = controllerPos.offset(rotator.getAbsoluteDirection(RelativeDirection.FORWARD), 2).down();
        if (!addIfHeater(mainHeater, world, controllerPos))
            return "The main heater not found (FORWARD, FORWARD, DOWN)";

        BlockPos left;
        int totalOffsetLeft = 0;
        do {
            totalOffsetLeft++;
            left = mainHeater.offset(rotator.getAbsoluteDirection(RelativeDirection.LEFT), totalOffsetLeft);
        }
        while (addIfHeater(left, world, controllerPos));
        totalOffsetLeft--;

        BlockPos right;
        int totalOffsetRight = 0;
        do {
            totalOffsetRight++;
            right = mainHeater.offset(rotator.getAbsoluteDirection(RelativeDirection.RIGHT), totalOffsetRight);
        }
        while (addIfHeater(right, world, controllerPos));
        totalOffsetRight--;

        if (totalOffsetLeft != totalOffsetRight)
            return "Heater platform must be 3x3 or 5x5 or 7x7 or 9x9";

        int totalOffset = Math.min(totalOffsetLeft, totalOffsetRight);
        if (totalOffset < 1)
            return "Heater platform cannot be smaller than 3x3";
        if (totalOffset > 4)
            return "Heater platform cannot be bigger than 9x9";

        for (int offsetRow = 1; offsetRow <= (totalOffset * 2); offsetRow++)
            for (int offsetCol = -totalOffset; offsetCol <= totalOffset; offsetCol++)
                if (!addIfHeater(mainHeater.offset(rotator.getAbsoluteDirection(RelativeDirection.FORWARD), offsetRow).offset(rotator.getAbsoluteDirection(RelativeDirection.LEFT), offsetCol), world, controllerPos))
                    return "Heater platform must be 3x3 or 5x5 or 7x7 or 9x9";

        BlockPos backwardBorder = mainHeater.up().offset(rotator.getAbsoluteDirection(RelativeDirection.BACKWARD));
        BlockPos forwardBorder = mainHeater.up().offset(rotator.getAbsoluteDirection(RelativeDirection.FORWARD), totalOffset * 2 + 1);
        BlockPos middleHeater = mainHeater.offset(rotator.getAbsoluteDirection(RelativeDirection.FORWARD), totalOffset);
        BlockPos leftBorder = middleHeater.up().offset(rotator.getAbsoluteDirection(RelativeDirection.LEFT), totalOffset + 1);
        BlockPos rightBorder = middleHeater.up().offset(rotator.getAbsoluteDirection(RelativeDirection.RIGHT), totalOffset + 1);

        int backwardLayers = addCasingWall(backwardBorder, totalOffset, RelativeDirection.LEFT, rotator, world, controllerPos);
        int forwardLayers = addCasingWall(forwardBorder, totalOffset, RelativeDirection.LEFT, rotator, world, controllerPos);
        int leftLayers = addCasingWall(leftBorder, totalOffset, RelativeDirection.FORWARD, rotator, world, controllerPos);
        int rightLayers = addCasingWall(rightBorder, totalOffset, RelativeDirection.FORWARD, rotator, world, controllerPos);

        int multiblockHeight = Math.min(Math.min(backwardLayers, forwardLayers), Math.min(leftLayers, rightLayers));
        if (multiblockHeight < 2)
            return "Walls need to be at least two blocks high";
        if (multiblockHeight > 30)
            return "Walls cannot be higher than 30 blocks";

        data = new Data(blocks, totalOffset * 2 + 1, multiblockHeight, controllerTier);
        return "SUCCESS";
    }

    private int addCasingWall(BlockPos middleBlock, int offset, RelativeDirection wallDirection, FacingRotator rotator, World world, BlockPos controllerPos) {
        int currentLayer = 0;
        boolean layerComplete = true;
        while (layerComplete) {
            for (int currentOffset = -offset; currentOffset <= offset; currentOffset++) {
                if (!addIfCasing(middleBlock.offset(rotator.getAbsoluteDirection(wallDirection), currentOffset).up(currentLayer), world, controllerPos))
                    layerComplete = false;
            }
            currentLayer++;
        }
        return currentLayer - 1; // returns the height of the wall
    }

    private boolean addIfHeater(BlockPos pos, World world, BlockPos controllerPos) {
        IBlockState state = world.getBlockState(pos);
        if (UMFRegistry.BlastFurnaceParts.isHeater(state)) {
            blocks.put(pos, state);
            ((TileEntityGenericTier)world.getTileEntity(pos)).setControllerPos(controllerPos);
            return true;
        } else
            return false;
    }

    private boolean addIfCasing(BlockPos pos, World world, BlockPos controllerPos) {
        IBlockState state = world.getBlockState(pos);
        if (UMFRegistry.BlastFurnaceParts.isCasing(state)) {
            blocks.put(pos, state);
            ((TileEntityGenericTier)world.getTileEntity(pos)).setControllerPos(controllerPos);
            return true;
        } else
            return false;
    }

    public Data getData() {
        return data;
    }

    public void deserializeData(NBTTagCompound nbt) {
        data = new Data(nbt);
    }

    public static class Data implements IFieldsSuck {
        private HashMap<BlockPos, IBlockState> blocks;

        private int furnaceInteriorHeight;
        private int furnaceInteriorWidth;
        private int controllerTier;
        private int capacity;

        // heaters
        private int heaterCount = 0;
        private float burningEfficiency = 0;
        private int joulesPerTickIncome = 0;
        private int joulesPerTickOutcome = 0;

        // thermal capacity - all blocks
        private int joulesPerDegreeThermalCapacity = 0;

        // isolation - casings
        private int casingCount = 0;
        private int joulesPerTickLost = 0;

        private int maximumIronQuality = 0;

        // realtime variables
        private float currentTemperature = 20;
        private int burnTimeLeft = 0;
        private int burnTimeOrig = 0;
        private int reactionTimeLeft = 0;
        private int reactionTimeOrig = 0;
        private float ironQualityMultiplier = 1;
        private BlastFurnaceRecipe currentRecipe = null;
        private ItemStack[] currentStacks;

        public Data(NBTTagCompound nbt) {
            deserializeNBT(nbt);
        }

        public Data(HashMap<BlockPos, IBlockState> pBlocks, int pFurnaceInteriorHeight, int pFurnaceInteriorWidth, int pControllerTier) {
            this.blocks = pBlocks;
            this.furnaceInteriorWidth = pFurnaceInteriorWidth;
            this.furnaceInteriorHeight = pFurnaceInteriorHeight;
            this.controllerTier = pControllerTier;

            initializeMultiblockBasedValues();
        }

        public int[] update() {
            if (isBurning()) {
                burnTimeLeft--;
                currentTemperature += ((float) joulesPerTickOutcome / (float) joulesPerDegreeThermalCapacity);
            }

            if (isReactionInProgress()) {
                // only if temperature is higher than required - 200
                if (currentTemperature > currentRecipe.getRequiredTemperature() - 200) {
                    reactionTimeLeft--;
                    if (reactionTimeLeft == 0) {
                        // FINISHED
                        float[] ingotData = currentRecipe.getIngotQuality(currentStacks, maximumIronQuality);
                        int ingotQuality = (int) (ironQualityMultiplier * ingotData[1]);
                        int ingotCount = (int) (ingotData[0]);
                        return new int[]{ingotCount, ingotQuality};
                    }
                    int temperatureOffset = Math.abs((int) currentTemperature - currentRecipe.getRequiredTemperature());
                    ironQualityMultiplier -= Math.pow(temperatureOffset, currentRecipe.getTemperatureFuckupMultiplier());
                    if(ironQualityMultiplier > 1)
                        ironQualityMultiplier = 1;
                    if(ironQualityMultiplier < 0.3f)
                        ironQualityMultiplier = 0.3f;
                }
            }

            if (currentTemperature > 20)
                currentTemperature -= ((float) joulesPerTickLost / (float) joulesPerDegreeThermalCapacity); // TODO make heat loss dependent on temperature difference
            return new int[]{0, 0};
        }

        public boolean startReaction(TileEntityBlastFurnaceController.InputItemStackHandler inputItemStackHandler) {
            BlastFurnaceRecipe recipe = UMFRecipes.BlastFurnace.getRecipeForOre(inputItemStackHandler.getCurrentOre());
            if (recipe != null && !isReactionInProgress() && currentTemperature > recipe.getRequiredTemperature() - 200) {
                currentStacks = inputItemStackHandler.clear();
                reactionTimeLeft = 0;
                ironQualityMultiplier = 1;
                currentRecipe = recipe;
                reactionTimeOrig = currentRecipe.getBaseReactionTime(); // TODO make reactionTimeLeft changeable by multiblock
                reactionTimeLeft = reactionTimeOrig;
                return true;
            } else
                return false;
        }

        public boolean burnFuel(int joules) {
            if (!isBurning()) {
                burnTimeOrig = joules / joulesPerTickIncome;
                burnTimeLeft = burnTimeOrig;
                return true;
            } else {
                UMFLogger.logWarning("Blast furnace tried to burn additional fuel!");
                return false;
            }
        }

        public boolean isBurning() {
            return burnTimeLeft > 0;
        }

        public boolean isReactionInProgress() {
            return reactionTimeLeft > 0;
        }

        public int getCapacity() {
            return capacity;
        }

        public void loadCurrentBlockstates(World world)
        {
            for (Map.Entry<BlockPos, IBlockState> pair : blocks.entrySet()) {
                pair.setValue(world.getBlockState(pair.getKey()));
            }
            initializeMultiblockBasedValues();
        }

        private void initializeMultiblockBasedValues() {
            capacity = furnaceInteriorHeight * furnaceInteriorWidth * furnaceInteriorWidth;

            float sumBurningEfficiency = 0;

            for (Map.Entry<BlockPos, IBlockState> pair : blocks.entrySet()) {
                IBlockState state = pair.getValue();
                BlockGenericTierCasing block = (BlockGenericTierCasing) state.getBlock();
                int tier = state.getValue(block.getTier());

                joulesPerDegreeThermalCapacity += ((IBlockHeatable) block).getThermalCapacityJoulePerDegree(tier);

                if (block instanceof BlockHeater) {
                    BlockHeater blockHeater = (BlockHeater) block;
                    heaterCount++;
                    sumBurningEfficiency += blockHeater.getEfficiency(tier);
                    joulesPerTickIncome += blockHeater.getInputPower(tier);
                }

                if (block instanceof BlockBlastFurnaceCasing) {
                    BlockBlastFurnaceCasing blockCasing = (BlockBlastFurnaceCasing) block;
                    casingCount++;
                    joulesPerTickLost += blockCasing.getJouleLeakedPerTick(tier);
                }
            }
            burningEfficiency = sumBurningEfficiency / heaterCount;
            joulesPerTickOutcome = (int) (joulesPerTickIncome * burningEfficiency);

            maximumIronQuality = 5000;
        }

        @Override
        public int getField(int id) {
            switch (id) {
                case 0:
                    return (int) currentTemperature;
                case 1:
                    return burnTimeLeft;
                case 2:
                    return burnTimeOrig;
                case 3:
                    return reactionTimeLeft;
                case 4:
                    return reactionTimeOrig;
            }
            return 0;
        }

        @Override
        public void setField(int id, int value) {
            switch (id) {
                case 0:
                    currentTemperature = value;
                    break;
                case 1:
                    burnTimeLeft = value;
                    break;
                case 2:
                    burnTimeOrig = value;
                    break;
                case 3:
                    reactionTimeLeft = value;
                    break;
                case 4:
                    reactionTimeOrig = value;
                    break;
            }
        }

        @Override
        public int getFieldCount() {
            return 5;
        }

        public NBTTagCompound serializeNBT() {
            NBTTagCompound compound = new NBTTagCompound();

            // MULTIBLOCK VALUES
            NBTTagList blocksTagList = new NBTTagList();
            for (Map.Entry<BlockPos, IBlockState> pair : blocks.entrySet()) {
                NBTTagCompound positionCompound = new NBTTagCompound();
                BlockPos pos = pair.getKey();
                positionCompound.setInteger("x", pos.getX());
                positionCompound.setInteger("y", pos.getY());
                positionCompound.setInteger("z", pos.getZ());
                blocksTagList.appendTag(positionCompound);
            }
            compound.setTag("blocksTagList", blocksTagList);
            compound.setInteger("furnaceInteriorHeight", furnaceInteriorHeight);
            compound.setInteger("furnaceInteriorWidth", furnaceInteriorWidth);
            compound.setInteger("controllerTier", controllerTier);

            // REALTIME VALUES
            compound.setFloat("currentTemperature", currentTemperature);
            compound.setInteger("burnTimeLeft", burnTimeLeft);
            compound.setInteger("burnTimeOrig", burnTimeOrig);
            compound.setInteger("reactionTimeLeft", reactionTimeLeft);
            compound.setInteger("reactionTimeOrig", reactionTimeOrig);
            compound.setFloat("ironQualityMultiplier", ironQualityMultiplier);
            if (currentRecipe != null)
                compound.setString("currentRecipeOre", currentRecipe.getOre().getRegistryName().toString());

            if (currentStacks != null) {
            NBTTagList itemsTagList = new NBTTagList();
                for (int i = 0; i < currentStacks.length; i++) {
                    if (currentStacks[i] != null) {
                        NBTTagCompound itemTag = new NBTTagCompound();
                        itemTag.setInteger("Slot", i);
                        currentStacks[i].writeToNBT(itemTag);
                        itemsTagList.appendTag(itemTag);
                    }
                }
                compound.setTag("currentStacks", itemsTagList);
                compound.setInteger("currentStacksSize", currentStacks.length);
            }

            return compound;
        }

        private void deserializeNBT(NBTTagCompound nbt) {
            // MULTIBLOCK VALUES
            NBTTagList blocksTagList = nbt.getTagList("blocksTagList", Constants.NBT.TAG_COMPOUND);
            blocks = new HashMap<BlockPos, IBlockState>();
            for (int i = 0; i < blocksTagList.tagCount(); i++) {
                NBTTagCompound posTags = blocksTagList.getCompoundTagAt(i);
                int x = posTags.getInteger("x");
                int y = posTags.getInteger("y");
                int z = posTags.getInteger("z");
                BlockPos pos = new BlockPos(x, y, z);
                blocks.put(pos, null);
            }
            furnaceInteriorHeight = nbt.getInteger("furnaceInteriorHeight");
            furnaceInteriorWidth = nbt.getInteger("furnaceInteriorWidth");
            controllerTier = nbt.getInteger("controllerTier");

            // REALTIME VALUES
            if(nbt.hasKey("currentStacks")) {
                NBTTagList itemsTagList = nbt.getTagList("currentStacks", Constants.NBT.TAG_COMPOUND);
                currentStacks = new ItemStack[nbt.getInteger("currentStacksSize")];
                for (int i = 0; i < itemsTagList.tagCount(); i++) {
                    NBTTagCompound itemTags = itemsTagList.getCompoundTagAt(i);
                    int slot = itemTags.getInteger("Slot");

                    if (slot >= 0 && slot < currentStacks.length) {
                        currentStacks[slot] = ItemStack.loadItemStackFromNBT(itemTags);
                    }
                }
            }
            currentTemperature = nbt.getFloat("currentTemperature");
            burnTimeLeft = nbt.getInteger("burnTimeLeft");
            burnTimeOrig = nbt.getInteger("burnTimeOrig");
            reactionTimeLeft = nbt.getInteger("reactionTimeLeft");
            reactionTimeOrig = nbt.getInteger("reactionTimeOrig");
            ironQualityMultiplier = nbt.getFloat("ironQualityMultiplier");
            if (nbt.hasKey("currentRecipeOre"))
                currentRecipe = UMFRecipes.BlastFurnace.getRecipeForOre(Item.getItemFromBlock(Block.blockRegistry.getObject(new ResourceLocation(nbt.getString("currentRecipeOre")))));
        }
    }
}
