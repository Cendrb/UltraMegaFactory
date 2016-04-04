package com.mandatoryfun.ultramegafactory.block.machinery.blast_furnace;

import com.google.common.base.Predicate;
import com.mandatoryfun.ultramegafactory.block.machinery.BlockGenericTier;
import com.mandatoryfun.ultramegafactory.block.machinery.BlockHeater;
import com.mandatoryfun.ultramegafactory.block.machinery.IBlockHeatable;
import com.mandatoryfun.ultramegafactory.init.ModItems;
import com.mandatoryfun.ultramegafactory.init.UMFRegistry;
import com.mandatoryfun.ultramegafactory.init.recipe.BlastFurnaceRecipe;
import com.mandatoryfun.ultramegafactory.lib.FacingRotator;
import com.mandatoryfun.ultramegafactory.lib.RelativeDirection;
import com.mandatoryfun.ultramegafactory.lib.UMFLogger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;

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

    public String rebuild(EnumFacing facing, BlockPos controllerPos, World world, int controllerTier) {
        blocks.clear();
        FacingRotator rotator = new FacingRotator(facing);
        BlockPos mainHeater = controllerPos.offset(rotator.getAbsoluteDirection(RelativeDirection.FORWARD), 2).down();
        if (!addIfHeater(mainHeater, world))
            return "The main heater not found (FORWARD, FORWARD, DOWN)";

        BlockPos left;
        int totalOffsetLeft = 0;
        do {
            totalOffsetLeft++;
            left = mainHeater.offset(rotator.getAbsoluteDirection(RelativeDirection.LEFT), totalOffsetLeft);
        }
        while (addIfHeater(left, world));
        totalOffsetLeft--;

        BlockPos right;
        int totalOffsetRight = 0;
        do {
            totalOffsetRight++;
            right = mainHeater.offset(rotator.getAbsoluteDirection(RelativeDirection.RIGHT), totalOffsetRight);
        }
        while (addIfHeater(right, world));
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
                if (!addIfHeater(mainHeater.offset(rotator.getAbsoluteDirection(RelativeDirection.FORWARD), offsetRow).offset(rotator.getAbsoluteDirection(RelativeDirection.LEFT), offsetCol), world))
                    return "Heater platform must be 3x3 or 5x5 or 7x7 or 9x9";

        BlockPos backwardBorder = mainHeater.up().offset(rotator.getAbsoluteDirection(RelativeDirection.BACKWARD));
        BlockPos forwardBorder = mainHeater.up().offset(rotator.getAbsoluteDirection(RelativeDirection.FORWARD), totalOffset * 2 + 1);
        BlockPos middleHeater = mainHeater.offset(rotator.getAbsoluteDirection(RelativeDirection.FORWARD), totalOffset);
        BlockPos leftBorder = middleHeater.up().offset(rotator.getAbsoluteDirection(RelativeDirection.LEFT), totalOffset + 1);
        BlockPos rightBorder = middleHeater.up().offset(rotator.getAbsoluteDirection(RelativeDirection.RIGHT), totalOffset + 1);

        int backwardLayers = addCasingWall(backwardBorder, totalOffset, RelativeDirection.LEFT, rotator, world);
        int forwardLayers = addCasingWall(forwardBorder, totalOffset, RelativeDirection.LEFT, rotator, world);
        int leftLayers = addCasingWall(leftBorder, totalOffset, RelativeDirection.FORWARD, rotator, world);
        int rightLayers = addCasingWall(rightBorder, totalOffset, RelativeDirection.FORWARD, rotator, world);

        int multiblockHeight = Math.min(Math.min(backwardLayers, forwardLayers), Math.min(leftLayers, rightLayers));
        if (multiblockHeight < 2)
            return "Walls need to be at least two blocks high";
        if (multiblockHeight > 30)
            return "Walls cannot be higher than 30 blocks";

        data = new Data(blocks, totalOffset * 2 + 1, multiblockHeight, controllerTier);
        return "SUCCESS";
    }

    private int addCasingWall(BlockPos middleBlock, int offset, RelativeDirection wallDirection, FacingRotator rotator, World world) {
        int currentLayer = 0;
        boolean layerComplete = true;
        while (layerComplete) {
            for (int currentOffset = -offset; currentOffset <= offset; currentOffset++) {
                if (!addIfCasing(middleBlock.offset(rotator.getAbsoluteDirection(wallDirection), currentOffset).up(currentLayer), world))
                    layerComplete = false;
            }
            currentLayer++;
        }
        return currentLayer - 1; // returns the height of the wall
    }

    private boolean addIfHeater(BlockPos pos, World world) {
        IBlockState state = world.getBlockState(pos);
        if (UMFRegistry.BlastFurnaceParts.isHeater(state)) {
            blocks.put(pos, state);
            return true;
        } else
            return false;
    }

    private boolean addIfCasing(BlockPos pos, World world) {
        IBlockState state = world.getBlockState(pos);
        if (UMFRegistry.BlastFurnaceParts.isCasing(state)) {
            blocks.put(pos, state);
            return true;
        } else
            return false;
    }

    private Data getData()
    {
        return data;
    }

    public class Data implements INBTSerializable<NBTTagCompound> {
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
        private float ironQualityMultiplier = 1;
        private int reactionTimeLeft = 0;
        private BlastFurnaceRecipe currentRecipe = null;

        public Data(HashMap<BlockPos, IBlockState> pBlocks, int pFurnaceInteriorHeight, int pFurnaceInteriorWidth, int pControllerTier) {
            this.blocks = pBlocks;
            this.furnaceInteriorWidth = pFurnaceInteriorWidth;
            this.furnaceInteriorHeight = pFurnaceInteriorHeight;
            this.controllerTier = pControllerTier;

            capacity = furnaceInteriorHeight * furnaceInteriorWidth;

            float sumBurningEfficiency = 0;

            for (Map.Entry<BlockPos, IBlockState> pair : blocks.entrySet()) {
                IBlockState state = pair.getValue();
                BlockGenericTier block = (BlockGenericTier) state.getBlock();
                int tier = state.getValue(block.getTier());

                joulesPerDegreeThermalCapacity += ((IBlockHeatable) block).getThermalCapacityJoulePerDegree(tier);

                if (block instanceof BlockHeater) {
                    BlockHeater blockHeater = (BlockHeater)block;
                    heaterCount++;
                    sumBurningEfficiency += blockHeater.getEfficiency(tier);
                    joulesPerTickIncome += blockHeater.getInputPower(tier);
                }

                if(block instanceof BlockBlastFurnaceCasing)
                {
                    BlockBlastFurnaceCasing blockCasing = (BlockBlastFurnaceCasing)block;
                    casingCount++;
                    joulesPerTickIncome += blockCasing.getJouleLeakedPerTick(tier);
                }
            }
            burningEfficiency = sumBurningEfficiency / heaterCount;
            joulesPerTickOutcome = (int)(joulesPerTickIncome * burningEfficiency);

            maximumIronQuality = 5000;
        }

        public int update()
        {
            if(isBurning()) {
                burnTimeLeft--;
                currentTemperature += (joulesPerTickOutcome / joulesPerDegreeThermalCapacity);
            }

            if(isReactionInProgress())
            {
                // only if temperature is higher than required - 200
                if(currentTemperature > currentRecipe.getRequiredTemperature() - 200) {
                    reactionTimeLeft--;
                    if(reactionTimeLeft == 0)
                    {
                        // FINISHED
                        return (int)(maximumIronQuality * ironQualityMultiplier);
                    }
                    if(ironQualityMultiplier > 0.3f) {
                        int temperatureOffset = Math.abs((int) currentTemperature - currentRecipe.getRequiredTemperature());
                        ironQualityMultiplier -= Math.log10(Math.pow(temperatureOffset, currentRecipe.getTemperatureFuckupMultiplier()) / currentRecipe.getBaseReactionTime() / 20);
                    }
                }
            }

            if(currentTemperature > 20)
                currentTemperature -= (joulesPerTickLost / joulesPerDegreeThermalCapacity);
            return 0;
        }

        public boolean startReaction(BlastFurnaceRecipe recipe)
        {
            if(!isReactionInProgress() && currentTemperature > recipe.getRequiredTemperature() - 200)
            {
                reactionTimeLeft = 0;
                ironQualityMultiplier = 1;
                currentRecipe = recipe;
                reactionTimeLeft = currentRecipe.getBaseReactionTime(); // TODO make reactionTimeLeft changeable by multiblock
                return true;
            }
            else
                return false;
        }

        public boolean burnFuel(int joules)
        {
            if(!isBurning())
            {
                burnTimeLeft = joules / joulesPerTickIncome;
                return true;
            }
            else {
                UMFLogger.logWarning("Blast furnace tried to burn additional fuel!");
                return false;
            }
        }

        public boolean isBurning()
        {
            return burnTimeLeft > 0;
        }

        public float getCurrentTemperature() {
            return currentTemperature;
        }

        public boolean isReactionInProgress()
        {
            return reactionTimeLeft > 0;
        }

        @Override
        public NBTTagCompound serializeNBT() {/*
            blocks = new HashMap<BlockPos, IBlockState>();

            NBTTagCompound compound = new NBTTagCompound();
            NBTTagList blocksList = new NBTTagList();
            for(Map.Entry<BlockPos, IBlockState> pair : blocks.entrySet())
            {
                BlockPos position = pair.getKey();
                NBTTagCompound compoundBlock = new NBTTagCompound();
                compound.setInteger("x", position.getX());
                compound.setInteger("y", position.getY());
                compound.setInteger("z", position.getZ());
                compound.setString("blockName", pair.getValue().getBlock().getRegistryName().toString());
                blocksList.appendTag(compoundBlock);
            }
            compound.setTag("blocks", blocksList);
            compound.setInteger("furnaceInteriorHeight", furnaceInteriorHeight);
            compound.setInteger("furnaceInteriorWidth", furnaceInteriorWidth);
            compound.setInteger("controllerTier", controllerTier);
            return compound;*/
            return new NBTTagCompound();
        }

        @Override
        public void deserializeNBT(NBTTagCompound compound) {/*
            NBTTagList blocksList = compound.getTagList("blocks", 10); // 10 = NBTTagCompound
            for(int index = 0; index < blocksList.tagCount(); index++)
            {
                NBTTagCompound compoundBlock = blocksList.getCompoundTagAt(index);
                BlockPos pos = new BlockPos(compoundBlock.getInteger("x"), compound.getInteger("y"), compound.getInteger("z"));
                String blockResource = compoundBlock.getString("blockName");
                String[] parts = blockResource.split(":");
                blocks.put(pos, GameRegistry.findBlock(parts[0], parts[1]));
            }*/
        }
    }
}
