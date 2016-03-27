package com.mandatoryfun.ultramegafactory.world;

import com.google.common.base.Predicate;
import com.mandatoryfun.ultramegafactory.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenSpikes;
import net.minecraftforge.fml.common.IWorldGenerator;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;

public class OreGenerator implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case -1:
                generateNether(random, chunkX * 16, chunkZ * 16, world);
                break;
            case 0:
                BiomeGenBase biome = world.getBiomeGenForCoords(new BlockPos(chunkX * 16, 0, chunkZ * 16));
                generateOverworld(random, chunkX * 16, chunkZ * 16, world, biome);
                break;

            case 1:
                generateEnd(random, chunkX * 16, chunkZ * 16, world);
                break;
        }
    }

    private void addOre(IBlockState block, final Block blockspawn, Random random, World world, int posX, int posZ, int minY, int maxY, int minVein, int maxVein, int spawnChance) {
        for (int i = 0; i < spawnChance; i++) {
            int defaultChunkSize = 16;

            int xPos = posX + random.nextInt(defaultChunkSize);

            int zPos = posZ + random.nextInt(defaultChunkSize);

            int yPos = minY + random.nextInt(maxY - minY);

            new WorldGenMinable(block, (minVein + random.nextInt(maxVein - minVein)), new Predicate<IBlockState>() {
                @Override
                public boolean apply(@Nullable IBlockState input) {
                    return input.getBlock() == blockspawn;
                }
            }).generate(world, random, new BlockPos(xPos, yPos, zPos));
        }
    }


    private void generateEnd(Random random, int worldX, int chunkZ, World world) {

    }

    private void generateOverworld(Random random, int worldX, int chunkZ, World world, BiomeGenBase biome) {
        if (isBiome(biome, 0, 10, 24, 16, 25, 26))
            addOre(ModBlocks.Ore.magnetite.getDefaultState(), Blocks.stone, random, world, worldX, chunkZ, 5, 60, 2, 8, 5);
            addOre(ModBlocks.Ore.cassiterite.getDefaultState(), Blocks.stone, random, world, worldX, chunkZ, 5, 100, 2, 10, 20);
            addOre(ModBlocks.Ore.hematite.getDefaultState(), Blocks.stone, random, world, worldX, chunkZ, 5, 100, 4, 8, 20);
    }

    private void generateNether(Random random, int worldX, int chunkZ, World world) {

    }

    private boolean isBiome(BiomeGenBase input, int... allowedBiomeIds) {
        ArrayList<BiomeGenBase> allowedBiomes = new ArrayList();
        for (int biomeId : allowedBiomeIds)
            allowedBiomes.add(BiomeGenBase.getBiome(biomeId));

        return allowedBiomes.contains(input);
    }
}