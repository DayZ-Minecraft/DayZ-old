package dayz.common.world.generation;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;
import dayz.common.world.biomes.Biomes;

public class MapGenTown extends MapGenStructure
{
    /** A list of all the biomes villages can spawn in. */
    public static List villageSpawnBiomes = Arrays.asList(new BiomeGenBase[]
    { Biomes.biomeForest, Biomes.biomePlains, Biomes.biomeSnowPlains });

    /**
     * Village size
     * 0 on Default
     * 1 on Flat (larger)
     * */
    private final int villageSize;

    public MapGenTown(int size)
    {
        villageSize = size;
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int xCoord, int zCoord)
    {
        byte xChance = 20;
        byte zChance = 5;
        int xCoordinate = xCoord;
        int zCoordinate = zCoord;

        if (xCoord < 0)
        {
            xCoord -= xChance - 1;
        }

        if (zCoord < 0)
        {
            zCoord -= xChance - 1;
        }

        int xChanceForCoord = xCoord / xChance;
        int zChanceForCoord = zCoord / xChance;
        Random randomSeed = worldObj.setRandomSeed(xChanceForCoord, zChanceForCoord, 10387312);
        xChanceForCoord *= xChance;
        zChanceForCoord *= xChance;
        xChanceForCoord += randomSeed.nextInt(xChance - zChance);
        zChanceForCoord += randomSeed.nextInt(xChance - zChance);

        if (xCoordinate == xChanceForCoord && zCoordinate == zChanceForCoord)
        {
            return true;
        }

        return false;
    }

    @Override
    protected StructureStart getStructureStart(int xCoord, int zCoord)
    {
        return new StructureTownStart(worldObj, rand, xCoord, zCoord, villageSize);
    }
}
