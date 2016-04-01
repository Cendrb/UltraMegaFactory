package com.mandatoryfun.ultramegafactory.init.recipe;

import com.mandatoryfun.ultramegafactory.block.BlockGenericOre;
import com.mandatoryfun.ultramegafactory.init.UMFRecipes;
import com.mandatoryfun.ultramegafactory.lib.UMFLogger;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by cendr_000 on 28.03.2016.
 */
public class BlastFurnaceRecipe {

    private BlockGenericOre ore;
    private float energyPerOre;
    private int baseReactionTime;
    private int requiredTemperature;
    private float reducingAgentPerOre;
    private float limePerOre;
    private float outputPerOre;

    public BlastFurnaceRecipe(BlockGenericOre ore, float energyPerOre, int baseReactionTime, int requiredTemperature, float reducingAgentPerOre, float limePerOre, float outputPerOre) {
        this.ore = ore;
        this.energyPerOre = energyPerOre;
        this.baseReactionTime = baseReactionTime;
        this.requiredTemperature = requiredTemperature;
        this.reducingAgentPerOre = reducingAgentPerOre;
        this.limePerOre = limePerOre;
        this.outputPerOre = outputPerOre;
    }

    public int getRequiredEnergy(int numberOfOres) {
        return (int) (numberOfOres * energyPerOre);
    }

    public float getIngotQuality(ItemStack[] stacks, int maxIronQuality) {
        int oreInserted = 0;
        float limeInserted = 0;
        float reducingAgentInserted = 0;
        Item requiredOre = Item.getItemFromBlock(this.ore);
        for (ItemStack itemStack : stacks) {
            Item item = itemStack.getItem();

            if (UMFRecipes.BlastFurnace.isReducingAgent(item))
                reducingAgentInserted += UMFRecipes.BlastFurnace.getReducingAgentValue(item) * itemStack.stackSize;

            if (UMFRecipes.BlastFurnace.isBullshitCreator(item))
                limeInserted += UMFRecipes.BlastFurnace.getBullshitCreatorValue(item) * itemStack.stackSize;

            if (item == requiredOre)
                oreInserted += itemStack.stackSize;
        }

        float reducingAgentMultiplier = (reducingAgentInserted / reducingAgentPerOre) / oreInserted;
        UMFLogger.logInfo(reducingAgentMultiplier);
        reducingAgentMultiplier = Math.abs((float) Math.log(reducingAgentMultiplier));
        UMFLogger.logInfo(reducingAgentMultiplier);

        float limeMultiplier = (limeInserted / limePerOre) / oreInserted;
        UMFLogger.logInfo(limeInserted);
        limeMultiplier = 0.8f + 0.2f * Math.abs((float) Math.log(limeMultiplier));
        UMFLogger.logInfo(limeMultiplier);

        float ironQuality = maxIronQuality * reducingAgentMultiplier * limeMultiplier;
        UMFLogger.logInfo(ironQuality);

        return ironQuality;
    }

    public int getIngotCount(int numberOfOres) {
        return (int) (numberOfOres * outputPerOre);
    }

    public int getRequiredOreHeatupEnergy(ItemStack[] stacks) {
        Item requiredOre = Item.getItemFromBlock(this.ore);
        int oreInserted = 0;
        for (ItemStack itemStack : stacks) {
            Item item = itemStack.getItem();


            if (item == requiredOre)
                oreInserted += itemStack.stackSize;
        }
        return (int) (energyPerOre * oreInserted);
    }

    public int getBaseReactionTime() {
        return baseReactionTime;
    }

    public int getRequiredTemperature() {
        return requiredTemperature;
    }

    public Block getOre() {
        return ore;
    }
}
