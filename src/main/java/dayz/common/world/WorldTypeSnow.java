package dayz.common.world;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import dayz.DayZ;

public class WorldTypeSnow extends WorldTypeBase
{
    public static final BiomeGenBase[] allowedBiomes = new BiomeGenBase[]
    { DayZ.biomeDayZSnowPlains };

    public WorldTypeSnow()
    {
        super(13, "DAYZSNOW");
    }

    @Override
    public BiomeGenBase[] setMajorBiomes()
    {
        return allowedBiomes;
    }

    @Override
    public BiomeGenBase setMinorBiomes(BiomeGenBase biomegenbase, GenLayer genlayer)
    {
        return DayZ.biomeDayZSnowMountains;
    }

    @Override
    public BiomeGenBase setRiverBiomes(BiomeGenBase biomegenbase, GenLayer genlayer)
    {
        return DayZ.biomeDayZRiver;
    }

}
