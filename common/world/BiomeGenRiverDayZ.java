package dayz.common.world;

import net.minecraft.world.biome.BiomeGenRiver;

public class BiomeGenRiverDayZ extends BiomeGenRiver 
{
	public BiomeGenRiverDayZ(int id) 
	{
		super(id);
		setColor(747097);
		setBiomeName("River");
        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
		minHeight = -0.5F;
		maxHeight = 0.0F;
		theBiomeDecorator.flowersPerChunk = 0;
		theBiomeDecorator.deadBushPerChunk = 0;
		theBiomeDecorator.treesPerChunk = -999;
		theBiomeDecorator.grassPerChunk = 15;
    }
}
