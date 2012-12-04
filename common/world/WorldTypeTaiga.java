package dayz.common.world;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.GenLayerHills;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldChunkManager;
import net.minecraft.src.WorldType;
import dayz.DayZ;

public class WorldTypeTaiga extends WorldTypeBase
{	
    public static final BiomeGenBase[] allowedBiomes = new BiomeGenBase[] {DayZ.biomeDayZForest};

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