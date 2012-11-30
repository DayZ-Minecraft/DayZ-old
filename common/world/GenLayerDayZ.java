package dayz.common.world;

import net.minecraft.src.GenLayer;
import net.minecraft.src.GenLayerAddMushroomIsland;
import net.minecraft.src.GenLayerAddSnow;
import net.minecraft.src.GenLayerBiome;
import net.minecraft.src.GenLayerFuzzyZoom;
import net.minecraft.src.GenLayerHills;
import net.minecraft.src.GenLayerIsland;
import net.minecraft.src.GenLayerRiver;
import net.minecraft.src.GenLayerRiverInit;
import net.minecraft.src.GenLayerShore;
import net.minecraft.src.GenLayerSmooth;
import net.minecraft.src.GenLayerSwampRivers;
import net.minecraft.src.GenLayerZoom;
import net.minecraft.src.WorldType;

public abstract class GenLayerDayZ extends GenLayer
{
    /** seed from World#getWorldSeed that is used in the LCG prng */
    private long worldGenSeed;

    /** parent GenLayer that was provided via the constructor */
    protected GenLayer parent;

    /**
     * final part of the LCG prng that uses the chunk X, Z coords along with the other two seeds to generate
     * pseudorandom numbers
     */
    private long chunkSeed;

    /** base seed to the LCG prng provided via the constructor */
    private long baseSeed;

    /**
     * the first array item is a linked list of the bioms, the second is the zoom function, the third is the same as the
     * first.
     */
    public static GenLayerDayZ[] initializeAllBiomeGenerators(long par0, WorldType par2WorldType)
    {
        GenLayerIsland var3 = new GenLayerIsland(1L);
        GenLayerFuzzyZoom var9 = new GenLayerFuzzyZoom(2000L, var3);
        GenLayerAddIslandDayZ var10 = new GenLayerAddIslandDayZ(1L, var9);
        GenLayerZoom var11 = new GenLayerZoom(2001L, var10);
        var10 = new GenLayerAddIslandDayZ(2L, var11);
        GenLayerAddSnow var12 = new GenLayerAddSnow(2L, var10);
        var11 = new GenLayerZoom(2002L, var12);
        var10 = new GenLayerAddIslandDayZ(3L, var11);
        var11 = new GenLayerZoom(2003L, var10);
        var10 = new GenLayerAddIslandDayZ(4L, var11);
        GenLayerAddMushroomIsland var16 = new GenLayerAddMushroomIsland(5L, var10);
        byte var4 = 4;

        if (par2WorldType == WorldType.LARGE_BIOMES)
        {
            var4 = 6;
        }

        GenLayer var5 = GenLayerZoom.func_75915_a(1000L, var16, 0);
        GenLayerRiverInit var13 = new GenLayerRiverInit(100L, var5);
        var5 = GenLayerZoom.func_75915_a(1000L, var13, var4 + 2);
        GenLayerRiver var14 = new GenLayerRiver(1L, var5);
        GenLayerSmooth var15 = new GenLayerSmooth(1000L, var14);
        GenLayer var6 = GenLayerZoom.func_75915_a(1000L, var16, 0);
        GenLayerBiome var17 = new GenLayerBiome(200L, var6, par2WorldType);
        var6 = GenLayerZoom.func_75915_a(1000L, var17, 2);
        Object var18 = new GenLayerHills(1000L, var6);

        for (int var7 = 0; var7 < var4; ++var7)
        {
            var18 = new GenLayerZoom((long)(1000 + var7), (GenLayer)var18);

            if (var7 == 0)
            {
                var18 = new GenLayerAddIslandDayZ(3L, (GenLayer)var18);
            }

            if (var7 == 1)
            {
                var18 = new GenLayerShore(1000L, (GenLayer)var18);
            }

            if (var7 == 1)
            {
                var18 = new GenLayerSwampRivers(1000L, (GenLayer)var18);
            }
        }

        GenLayerSmooth var19 = new GenLayerSmooth(1000L, (GenLayer)var18);
        GenLayerDayZ var20 = new GenLayerRiverMixDayZ(100L, var19, var15);
        GenLayerDayZ var8 = new GenLayerVoronoiZoomDayZ(10L, var20);
        var20.initWorldGenSeed(par0);
        var8.initWorldGenSeed(par0);
        return new GenLayerDayZ[] {var20, var8, var20};
    }

    public GenLayerDayZ(long par1)
    {
    	super(par1);
        this.baseSeed = par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
    }

    /**
     * Initialize layer's local worldGenSeed based on its own baseSeed and the world's global seed (passed in as an
     * argument).
     */
    public void initWorldGenSeed(long par1)
    {
        this.worldGenSeed = par1;

        if (this.parent != null)
        {
            this.parent.initWorldGenSeed(par1);
        }

        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
    }

    /**
     * Initialize layer's current chunkSeed based on the local worldGenSeed and the (x,z) chunk coordinates.
     */
    public void initChunkSeed(long par1, long par3)
    {
        this.chunkSeed = this.worldGenSeed;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par1;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par3;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par1;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par3;
    }

    /**
     * returns a LCG pseudo random number from [0, x). Args: int x
     */
    protected int nextInt(int par1)
    {
        int var2 = (int)((this.chunkSeed >> 24) % (long)par1);

        if (var2 < 0)
        {
            var2 += par1;
        }

        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += this.worldGenSeed;
        return var2;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public abstract int[] getInts(int var1, int var2, int var3, int var4);
}
