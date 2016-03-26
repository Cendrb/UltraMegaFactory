

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

public class ModWorld
{
	public static void mainRegistry()
	{
		initializeWorldGen();
	}
	
	public static void initializeWorldGen()
	{
		registerWorldGen(new IWorldGenerator() {
            @Override
            public void generate(Random random, int i, int i1, World world, IChunkGenerator iChunkGenerator, IChunkProvider iChunkProvider) {

            }
        }, 1);
	}
	
	public static void registerWorldGen(IWorldGenerator worldGenClass, int weightedProbability)
	{
		
		GameRegistry.registerWorldGenerator(worldGenClass, weightedProbability);
	}
}
