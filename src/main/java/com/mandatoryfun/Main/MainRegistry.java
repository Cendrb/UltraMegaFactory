package com.wester_west.Main;


import com.example.ultramegafactory.init.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

import com.wester_west.creativetabs.MCreativeTabs;
import com.example.ultramegafactory.init.ModItems;
import com.wester_west.lib.RefStrings;
import com.wester_west.world.MWorld;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = RefStrings.MODID, name = RefStrings.NAME, version = RefStrings.VERSION)

public class MainRegistry 
{
	@SidedProxy(clientSide = RefStrings.CLIENTSIDE, serverSide = RefStrings.SERVERSIDE)
	
	public static ServerProxy proxy;
	
	@EventHandler
	public static void PreLoad(FMLPreInitializationEvent evevnt)
	{
		MCreativeTabs.initializeTabs();
		MWorld.mainRegistry();
		ModItems.mainRegistry();
		ModBlocks.mainRegistry();
		CraftingManager.mainRegistry();
		proxy.registerRenderInfo();
		
	}
	
	@SubscribeEvent
    public void populateChunk(PopulateChunkEvent.Pre event) {
            Chunk chunk = event.world.getChunkFromChunkCoords(event.chunkX, event.chunkZ);
            for (ExtendedBlockStorage storage : chunk.getBlockStorageArray()) {
                    if (storage != null) {
                            for (int x = 0; x < 16; ++x) {
                                    for (int y = 0; y < 16; ++y) {
                                            for (int z = 0; z < 16; ++z) {
                                                    if (storage.getBlockByExtId(x, y, z) == Blocks.stone) {
                                                            storage.func_150818_a(x, y, z, Blocks.brick_block);
                                                    }
                                            }
                                    }
                            }
                    }
            }
    }
	
	@EventHandler
	public static void load(FMLInitializationEvent event)
	{
		
	}
	
	@EventHandler
	public static void PostLoad(FMLPostInitializationEvent event)
	{
		
	}
}
