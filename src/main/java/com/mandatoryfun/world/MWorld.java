package com.wester_west.world;

import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class MWorld 
{
	public static void mainRegistry()
	{
		initializeWorldGen();
	}
	
	public static void initializeWorldGen()
	{
		registerWorldGen(new ChewOre(), 1);
	}
	
	public static void registerWorldGen(IWorldGenerator worldGenClass, int weightedProbability)
	{
		
		GameRegistry.registerWorldGenerator(worldGenClass, weightedProbability);
	}
}
