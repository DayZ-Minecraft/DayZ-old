package dayz.common.world.biomes;

import net.minecraft.world.biome.BiomeGenRiver;

public class BiomeRiver extends BiomeGenRiver
{
    public BiomeRiver(int id)
    {
        super(id);
        setColor(747097);
        setBiomeName("DayZ River");
        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
        minHeight = -0.5F;
        maxHeight = 0.0F;
        theBiomeDecorator.treesPerChunk = -999;
        theBiomeDecorator.biome.color = 1456435;
        theBiomeDecorator.flowersPerChunk = 0;
        theBiomeDecorator.grassPerChunk = 15;
        theBiomeDecorator.deadBushPerChunk = 0;
    }
}
