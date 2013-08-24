package dayz.common.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import dayz.DayZ;
import dayz.common.world.generation.DayZStructureHandler;
import dayz.common.world.generation.MapGenTown;

public class ChunkProviderDayZ extends ChunkProviderGenerate implements IChunkProvider
{
    /** RNG. */
    private Random rand;

    /** A NoiseGeneratorOctaves used in generating terrain */
    private NoiseGeneratorOctaves noiseGen1;

    /** A NoiseGeneratorOctaves used in generating terrain */
    private NoiseGeneratorOctaves noiseGen2;

    /** A NoiseGeneratorOctaves used in generating terrain */
    private NoiseGeneratorOctaves noiseGen3;

    /** A NoiseGeneratorOctaves used in generating terrain */
    private NoiseGeneratorOctaves noiseGen4;

    /** A NoiseGeneratorOctaves used in generating terrain */
    public NoiseGeneratorOctaves noiseGen5;

    /** A NoiseGeneratorOctaves used in generating terrain */
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves mobSpawnerNoise;

    /** Reference to the World object. */
    private World worldObj;

    /** are map structures going to be generated (e.g. strongholds) */
    private final boolean mapFeaturesEnabled;

    /** Holds the overall noise array used in chunk generation */
    private double[] noiseArray;
    private double[] stoneNoise = new double[256];
    private MapGenBase caveGenerator = new MapGenCaves();

    /** Holds Village Generator */
    private MapGenTown villageGenerator = new MapGenTown(1);

    /** Holds Mineshaft Generator */
    private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();

    /** Holds ravine generator */
    private MapGenBase ravineGenerator = new MapGenRavine();

    /** The biomes that are used to generate the chunk */
    private BiomeGenBase[] biomesForGeneration;

    /** A double array that hold terrain noise from noiseGen3 */
    double[] noise3;

    /** A double array that hold terrain noise */
    double[] noise1;

    /** A double array that hold terrain noise from noiseGen2 */
    double[] noise2;

    /** A double array that hold terrain noise from noiseGen5 */
    double[] noise5;

    /** A double array that holds terrain noise from noiseGen6 */
    double[] noise6;

    /**
     * Used to store the 5x5 parabolic field that is used during terrain
     * generation.
     */
    float[] parabolicField;
    int[][] field_73219_j = new int[32][32];

    public ChunkProviderDayZ(World par1World, long par2, boolean par4)
    {
        super(par1World, par2, par4);
        worldObj = par1World;
        mapFeaturesEnabled = par4;
        rand = new Random(par2);
        noiseGen1 = new NoiseGeneratorOctaves(rand, 16);
        noiseGen2 = new NoiseGeneratorOctaves(rand, 16);
        noiseGen3 = new NoiseGeneratorOctaves(rand, 8);
        noiseGen4 = new NoiseGeneratorOctaves(rand, 4);
        noiseGen5 = new NoiseGeneratorOctaves(rand, 10);
        noiseGen6 = new NoiseGeneratorOctaves(rand, 16);
        mobSpawnerNoise = new NoiseGeneratorOctaves(rand, 8);
    }

    @Override
    public Chunk provideChunk(int par1, int par2)
    {
        rand = new Random(worldObj.getSeed());
        rand.setSeed(par1 * 341873128712L + par2 * 132897987541L);
        byte[] var3 = new byte[32768];
        generateTerrain(par1, par2, var3);
        biomesForGeneration = worldObj.getWorldChunkManager().loadBlockGeneratorData(biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        replaceBlocksForBiome(par1, par2, var3, biomesForGeneration);
        caveGenerator.generate(this, worldObj, par1, par2, var3);
        ravineGenerator.generate(this, worldObj, par1, par2, var3);

        if (mapFeaturesEnabled)
        {
            mineshaftGenerator.generate(this, worldObj, par1, par2, var3);
            villageGenerator.generate(this, worldObj, par1, par2, var3);
        }

        Chunk var4 = new Chunk(worldObj, var3, par1, par2);
        byte[] var5 = var4.getBiomeArray();

        for (int var6 = 0; var6 < var5.length; ++var6)
        {
            var5[var6] = (byte) biomesForGeneration[var6].biomeID;
        }

        var4.generateSkylightMap();
        return var4;
    }

    @Override
    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
    {
        BlockSand.fallInstantly = true;
        int var4 = par2 * 16;
        int var5 = par3 * 16;
        BiomeGenBase var6 = worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
        rand.setSeed(worldObj.getSeed());
        long var7 = rand.nextLong() / 2L * 2L + 1L;
        long var9 = rand.nextLong() / 2L * 2L + 1L;
        rand.setSeed(par2 * var7 + par3 * var9 ^ worldObj.getSeed());
        boolean var11 = false;

        if (mapFeaturesEnabled)
        {
            mineshaftGenerator.generateStructuresInChunk(worldObj, rand, par2, par3);
            var11 = villageGenerator.generateStructuresInChunk(worldObj, rand, par2, par3);
        }

        int var12;
        int var13;
        int var14;

        for (var12 = 0; var12 < 12; ++var12)
        {
            var13 = var4 + rand.nextInt(16) + 8;
            var14 = rand.nextInt(128);
            int var15 = var5 + rand.nextInt(16) + 8;

            DayZStructureHandler.DayZStructure(worldObj, rand, var13, var14, var15);
        }

        if (var6 != DayZ.biomeDayZSnowPlains)
        {
            if (!var11 && rand.nextInt(4) == 0)
            {
                var12 = var4 + rand.nextInt(16) + 8;
                var13 = rand.nextInt(128);
                var14 = var5 + rand.nextInt(16) + 8;
                (new WorldGenLakes(Block.waterStill.blockID)).generate(worldObj, rand, var12, var13, var14);
            }
        }

        var6.decorate(worldObj, rand, var4, var5);
        SpawnerAnimals.performWorldGenSpawning(worldObj, var6, var4 + 8, var5 + 8, 16, 16, rand);
        var4 += 8;
        var5 += 8;

        for (var12 = 0; var12 < 16; ++var12)
        {
            for (var13 = 0; var13 < 16; ++var13)
            {
                var14 = worldObj.getPrecipitationHeight(var4 + var12, var5 + var13);

                if (worldObj.isBlockFreezable(var12 + var4, var14 - 1, var13 + var5))
                {
                    worldObj.setBlock(var12 + var4, var14 - 1, var13 + var5, Block.ice.blockID);
                }

                if (worldObj.canSnowAt(var12 + var4, var14, var13 + var5))
                {
                    worldObj.setBlock(var12 + var4, var14, var13 + var5, Block.snow.blockID);
                }
            }
        }

        BlockSand.fallInstantly = false;
    }

    @Override
    public void recreateStructures(int par1, int par2)
    {
        if (mapFeaturesEnabled)
        {
            mineshaftGenerator.generate(this, worldObj, par1, par2, (byte[]) null);
            villageGenerator.generate(this, worldObj, par1, par2, (byte[]) null);
        }
    }
}
