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

    private void generateOverworld(Random random, int worldX, int worldZ, World world, BiomeGenBase biome) {

        //ocean, froze ocean, deep ocean, beach, stone beach, cold beach
        if (isBiome(biome, 0, 10, 24, 16, 25, 26))
        {
            addOre(ModBlocks.Ore.magnetite.getDefaultState(), Blocks.stone, random, world, worldX, worldZ, 5, 60, 2, 8, 5);
            addOre(ModBlocks.Ore.hematite.getDefaultState(), ModBlocks.Ore.magnetite, random, world, worldX, worldZ, 5, 50, 2, 6, 5);
        }

        //savanna..., mesa...
        if (isBiome(biome, 2, 35, 36, 37, 38, 39))
            addOre(ModBlocks.Ore.cuprite.getDefaultState(), Blocks.stone, random, world, worldX, worldZ, 5, 40, 2, 8, 5);

        //jungle.., mushroom..
        if(isBiome(biome, 21, 22, 23, 14, 15))
            addOre(ModBlocks.Ore.cassiterite.getDefaultState(), Blocks.stone, random, world, worldX, worldZ, 5, 40, 2, 8, 5);

        //mountains...
        if(isBiome(biome, 3, 18, 20, 19, 22, 28, 31, 33, 34))
        {
            addOre(ModBlocks.Ore.galena.getDefaultState(), Blocks.stone, random, world, worldX, worldZ, 5, 40, 4, 8, 8);
            addOre(ModBlocks.Ore.sphalerite.getDefaultState(), Blocks.stone, random, world, worldX, worldZ, 5, 40, 2, 4, 4);
        }

        //plain..., savanna..., jungle..., forest...
        if(isBiome(biome, 1, 4, 21, 22, 23, 27, 28, 29, 35, 36))
            addOre(ModBlocks.Ore.bauxite.getDefaultState(), Blocks.stone, random, world, worldX, worldZ, 5, 40, 2, 8, 5);

        //swamps
        if(isBiome(biome, 6))
        {
            addOre(ModBlocks.Ore.lignite.getDefaultState(), Blocks.stone, random, world, worldX, worldZ, 40, 64, 10, 24, 5);
            addOre(ModBlocks.Ore.peat.getDefaultState(), Blocks.dirt, random, world, worldX, worldZ, 50, 255, 7, 12, 3);
        }

        //EVERYWHERE
        addOre(ModBlocks.kaolineOre.getDefaultState(), Blocks.stone, random, world, worldX, worldZ, 40, 100, 2, 5, 10);
        addOre(ModBlocks.Ore.bituminousCoal.getDefaultState(), Blocks.stone, random, world, worldX, worldZ, 5, 40, 6, 15, 1);
        addOre(ModBlocks.Ore.teallite.getDefaultState(), Blocks.stone, random, world, worldX, worldZ, 5, 40, 2, 8, 5);
        addOre(ModBlocks.Ore.siderite.getDefaultState(), Blocks.stone, random, world, worldX, worldZ, 5, 40, 2, 8, 5);
        addOre(ModBlocks.Ore.limonite.getDefaultState(), Blocks.stone, random, world, worldX, worldZ, 5, 40, 2, 8, 5);
        addOre(ModBlocks.Ore.pyrite.getDefaultState(), Blocks.stone, random, world, worldX, worldZ, 5, 20, 2, 8, 2);
        addOre(Blocks.gold_ore.getDefaultState(), Blocks.stone, random, world, worldX, worldZ, 5, 60, 4, 8, 12);
        addOre(ModBlocks.Ore.pyrite.getDefaultState(), Blocks.gold_ore, random, world, worldX, worldZ, 5, 60, 2, 4, 8);
        addOre(ModBlocks.Ore.chalcopyrite.getDefaultState(), ModBlocks.Ore.pyrite, random, world, worldX, worldZ, 5, 60, 1, 4, 4);


    }

    private void generateNether(Random random, int worldX, int worldZ, World world) {
        addOre(ModBlocks.Ore.tetrahedrite.getDefaultState(), Blocks.stone, random, world, worldX, worldZ, 5, 40, 2, 8, 5);
    }

    private boolean isBiome(BiomeGenBase input, int... allowedBiomeIds) {
        ArrayList<BiomeGenBase> allowedBiomes = new ArrayList();
        for (int biomeId : allowedBiomeIds)
            allowedBiomes.add(BiomeGenBase.getBiome(biomeId));

        return allowedBiomes.contains(input);
    }
}