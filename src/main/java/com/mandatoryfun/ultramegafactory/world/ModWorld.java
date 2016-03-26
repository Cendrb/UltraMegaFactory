package com.mandatoryfun.ultramegafactory.world;

import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModWorld {
    public static void init() {
        registerWorldGen(new OreGenerator(), 69);
    }

    private static void registerWorldGen(IWorldGenerator worldGenClass, int weightedProbability) {

        GameRegistry.registerWorldGenerator(worldGenClass, weightedProbability);
    }
}
