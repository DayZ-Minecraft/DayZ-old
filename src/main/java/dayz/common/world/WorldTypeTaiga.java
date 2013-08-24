package dayz.common.world;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import dayz.DayZ;

public class WorldTypeTaiga extends WorldTypeBase
{
    public static final BiomeGenBase[] allowedBiomes = new BiomeGenBase[]
    { DayZ.biomeDayZForest };

    public WorldTypeTaiga()
    {
        super(12, "DAYZBASE");
    }

    @Override
    public BiomeGenBase[] setMajorBiomes()
    {
        return allowedBiomes;
    }

    @Override
    public BiomeGenBase setMinorBiomes(BiomeGenBase biomegenbase, GenLayer genlayer)
    {
        return DayZ.biomeDayZPlains;
    }

    @Override
    public BiomeGenBase setRiverBiomes(BiomeGenBase biomegenbase, GenLayer genlayer)
    {
        return DayZ.biomeDayZRiver;
    }
}