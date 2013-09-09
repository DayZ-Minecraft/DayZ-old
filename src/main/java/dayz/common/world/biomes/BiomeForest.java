package dayz.common.world.biomes;

import java.util.Random;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeForest extends BiomeGenBase
{
    @SuppressWarnings("unchecked")
	public BiomeForest(int id)
    {
        super(id);
        setColor(747097);
        setBiomeName("Forest");
        temperature = BiomeGenBase.forest.temperature;
        rainfall = BiomeGenBase.forest.rainfall;
        minHeight = 0.1F;
        maxHeight = 0.2F;
        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
        spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 4, 4, 4));
        spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 4, 4, 4));
        spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 4, 4, 4));
        spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 4, 4, 4));
        spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 4, 4, 4));
        theBiomeDecorator.treesPerChunk = 7;
        theBiomeDecorator.biome.color = 1456435;
        theBiomeDecorator.flowersPerChunk = 4;
        theBiomeDecorator.grassPerChunk = 10;
        theBiomeDecorator.deadBushPerChunk = 4;
    }

    @Override
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
        return rand.nextInt(3) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false);
    }

}
