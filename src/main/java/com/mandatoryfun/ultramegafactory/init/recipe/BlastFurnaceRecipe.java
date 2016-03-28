package com.mandatoryfun.ultramegafactory.init.recipe;

import com.mandatoryfun.ultramegafactory.block.BlockGenericOre;
import com.mandatoryfun.ultramegafactory.init.ModItems;
import com.mandatoryfun.ultramegafactory.item.ItemFuelFormulaDescriptionGeneric;
import com.mandatoryfun.ultramegafactory.lib.UMFLogger;
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
            if (item instanceof ItemFuelFormulaDescriptionGeneric) {
                if (item == ModItems.carbon)
                    reducingAgentInserted += 1.5f * itemStack.stackSize;
                if (item == ModItems.bitumen)
                    reducingAgentInserted += 1.0f * itemStack.stackSize;
            }
            if (item == requiredOre)
                oreInserted += 1;
            if (item == ModItems.lime)
                limeInserted += 1;
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
}
