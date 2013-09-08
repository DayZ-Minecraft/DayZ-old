package dayz.common;

import net.minecraft.block.Block;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import dayz.common.misc.Config;
import dayz.common.world.WorldTypes;
import dayz.common.world.biomes.Biomes;
import dayz.common.world.generation.StructureHandler;
import dayz.common.world.genlayer.GenLayerDayZ;

public class CommonEventsTerrain
{
    @ForgeSubscribe
    public void initBiomeGens(WorldTypeEvent.InitBiomeGens event)
    {
        if (event.worldType == WorldTypes.DAYZ || event.worldType == WorldTypes.SNOW)
        {
            event.newBiomeGens = GenLayerDayZ.getGenLayers(event.seed, (WorldTypes) event.worldType);
        }
    }

    @ForgeSubscribe
    public void villageBlock(BiomeEvent.GetVillageBlockID event)
    {
        if (event.biome == Biomes.biomeSnowMountains || event.biome == Biomes.biomeSnowPlains)
        {
            event.replacement = Block.blockSnow.blockID;
            event.setResult(Result.DENY);
        }
    }

    @ForgeSubscribe
    public void populateChunk(PopulateChunkEvent.Populate event)
    {
        if (event.world.getWorldInfo().getTerrainType() == WorldTypes.DAYZ)
        {
            if (event.type == PopulateChunkEvent.Populate.EventType.LAKE)
            {
                event.setResult(Result.DENY);
            }
            if (event.type == PopulateChunkEvent.Populate.EventType.LAVA)
            {
                event.setResult(Result.DENY);
            }
            if (event.type == PopulateChunkEvent.Populate.EventType.DUNGEON)
            {
                event.setResult(Result.DENY);
            }
        }
        else if (event.world.getWorldInfo().getTerrainType() == WorldTypes.SNOW)
        {
            if (event.type == PopulateChunkEvent.Populate.EventType.LAKE)
            {
                event.setResult(Result.DENY);
            }
            if (event.type == PopulateChunkEvent.Populate.EventType.LAVA)
            {
                event.setResult(Result.DENY);
            }
            if (event.type == PopulateChunkEvent.Populate.EventType.DUNGEON)
            {
                event.setResult(Result.DENY);
            }
        }

        if (event.world.getWorldInfo().getTerrainType() == WorldTypes.DAYZ && event.world.rand.nextInt(Config.structureGenerationChance) == 0)
        {
            for (int i = 0; i < 12; ++i)
            {
                int x = event.chunkX * 16 + event.rand.nextInt(16) + 8;
                int y = event.rand.nextInt(128);
                int z = event.chunkZ * 16 + event.rand.nextInt(16) + 8;

                StructureHandler.generateStructure(event.world, event.rand, x, y, z);
            }
        }
        else if (event.world.getWorldInfo().getTerrainType() == WorldTypes.SNOW)
        {
            for (int i = 0; i < 12; ++i)
            {
                int x = event.chunkX * 16 + event.rand.nextInt(16) + 8;
                int y = event.rand.nextInt(128);
                int z = event.chunkZ * 16 + event.rand.nextInt(16) + 8;

                StructureHandler.generateStructure(event.world, event.rand, x, y, z);
            }
        }
    }
}
