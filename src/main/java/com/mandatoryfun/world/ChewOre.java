package com.wester_west.world;

import java.util.Random;

import com.example.ultramegafactory.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ChewOre implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch(world.provider.dimensionId)
		{
			case -1:
				generateNether(random, chunkX * 16, chunkZ * 16, world);
			break;
			
			case 0:
				generateOverworld(random, chunkX * 16, chunkZ * 16, world);
			break;
			
			case 1:
				generateEnd(random, chunkX * 16, chunkZ * 16, world);
			break;
		}
	}
  
	
	private void addOre(Block block, Block blockspawn, Random random, World world, int posX, int posZ, int minY, int maxY, int minVein, int maxVein, int spawnChance)
	{
		for(int i = 0; i < spawnChance; i++)
		{
			int defaultChunkSize = 16;
			
			int xPos = posX + random.nextInt(defaultChunkSize);
			
			int zPos = posZ + random.nextInt(defaultChunkSize);
			
			int yPos = minY + random.nextInt(maxY - minY);
			
			new WorldGenMinable(block, (minVein + random.nextInt(maxVein - minVein)), blockspawn).generate(world, random, xPos, yPos, zPos);
		}
	}
		
	
	private void generateEnd(Random random, int chunkX, int chunkZ, World world)
	{
		
	}

	private void generateOverworld(Random random, int chunkX, int chunkZ, World world)
	{
		addOre(ModBlocks.MagnetiteOre, Blocks.stone, random, world, chunkX, chunkZ, 5, 100, 1, 7, 10);
		addOre(ModBlocks.LimoniteOre, Blocks.stone, random, world, chunkX, chunkZ, 5, 100, 2, 10, 20);
		addOre(ModBlocks.HematiteOre, Blocks.stone, random, world, chunkX, chunkZ, 5, 100, 4, 8, 20);
		//addOre(ModBlocks.Basalt, Blocks.stone, random, world, chunkX, chunkZ, 0, 5, 1279, 1280, 1000);
	}

	private void generateNether(Random random, int chunkX, int chunkZ, World world) 
	{
		
	}

}
