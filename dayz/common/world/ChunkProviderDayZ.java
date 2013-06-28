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
     * Used to store the 5x5 parabolic field that is used during terrain generation.
     */
    float[] parabolicField;
    int[][] field_73219_j = new int[32][32];

    public ChunkProviderDayZ(World par1World, long par2, boolean par4)
    {
    	super(par1World, par2, par4);
        this.worldObj = par1World;
        this.mapFeaturesEnabled = par4;
        this.rand = new Random(par2);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
    }

    @Override
    public Chunk provideChunk(int par1, int par2)
    {
        this.rand = new Random(this.worldObj.getSeed());
        this.rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
        byte[] var3 = new byte[32768];
        this.generateTerrain(par1, par2, var3);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.replaceBlocksForBiome(par1, par2, var3, this.biomesForGeneration);
        this.caveGenerator.generate(this, this.worldObj, par1, par2, var3);
        this.ravineGenerator.generate(this, this.worldObj, par1, par2, var3);

        if (this.mapFeaturesEnabled)
        {
            this.mineshaftGenerator.generate(this, this.worldObj, par1, par2, var3);
            this.villageGenerator.generate(this, this.worldObj, par1, par2, var3);
        }

        Chunk var4 = new Chunk(this.worldObj, var3, par1, par2);
        byte[] var5 = var4.getBiomeArray();

        for (int var6 = 0; var6 < var5.length; ++var6)
        {
            var5[var6] = (byte)this.biomesForGeneration[var6].biomeID;
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
        BiomeGenBase var6 = this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        long var7 = this.rand.nextLong() / 2L * 2L + 1L;
        long var9 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)par2 * var7 + (long)par3 * var9 ^ this.worldObj.getSeed());
        boolean var11 = false;

        if (this.mapFeaturesEnabled)
        {
            this.mineshaftGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
            var11 = this.villageGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
        }

        int var12;
        int var13;
        int var14;

        for (var12 = 0; var12 < 12; ++var12)
        {
            var13 = var4 + this.rand.nextInt(16) + 8;
            var14 = this.rand.nextInt(128);
            int var15 = var5 + this.rand.nextInt(16) + 8;
            
            DayZStructureHandler.DayZStructure(this.worldObj, this.rand, var13, var14, var15);
        }
        
        if (var6 != DayZ.biomeDayZSnowPlains)
        {
	        if (!var11 && this.rand.nextInt(4) == 0)
	        {
	            var12 = var4 + this.rand.nextInt(16) + 8;
	            var13 = this.rand.nextInt(128);
	            var14 = var5 + this.rand.nextInt(16) + 8;
	            (new WorldGenLakes(Block.waterStill.blockID)).generate(this.worldObj, this.rand, var12, var13, var14);
	        }
        }
        
        var6.decorate(this.worldObj, this.rand, var4, var5);
        SpawnerAnimals.performWorldGenSpawning(this.worldObj, var6, var4 + 8, var5 + 8, 16, 16, this.rand);
        var4 += 8;
        var5 += 8;

        for (var12 = 0; var12 < 16; ++var12)
        {
            for (var13 = 0; var13 < 16; ++var13)
            {
                var14 = this.worldObj.getPrecipitationHeight(var4 + var12, var5 + var13);

                if (this.worldObj.isBlockFreezable(var12 + var4, var14 - 1, var13 + var5))
                {
                    this.worldObj.setBlock(var12 + var4, var14 - 1, var13 + var5, Block.ice.blockID);
                }

                if (this.worldObj.canSnowAt(var12 + var4, var14, var13 + var5))
                {
                    this.worldObj.setBlock(var12 + var4, var14, var13 + var5, Block.snow.blockID);
                }
            }
        }

        BlockSand.fallInstantly = false;
    }

    @Override
    public void recreateStructures(int par1, int par2)
    {
        if (this.mapFeaturesEnabled)
        {
            this.mineshaftGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
            this.villageGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
        }
    }
}
