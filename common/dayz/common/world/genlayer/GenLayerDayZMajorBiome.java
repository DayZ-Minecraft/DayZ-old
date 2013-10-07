package dayz.common.world.genlayer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import dayz.common.world.WorldTypes;

public class GenLayerDayZMajorBiome extends GenLayer
{
    public GenLayerDayZMajorBiome(long l, GenLayer genlayer, WorldTypes worldtype)
    {
        super(l);
        parent = genlayer;
        worldType = worldtype;
        allowedBiomes = worldtype.setMajorBiomes();
    }

    @Override
    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int ai[] = parent.getInts(par1, par2, par3, par4);
        int ai1[] = IntCache.getIntCache(par3 * par4);

        for (int i = 0; i < par4; i++)
        {
            for (int j = 0; j < par3; j++)
            {
                initChunkSeed(j + par1, i + par2);
                int k = ai[j + i * par3];

                BiomeGenBase ocean = worldType.setOceanBiomes(null, this);
                if (ocean != null && k == ocean.biomeID)
                {
                    ai1[j + i * par3] = ocean.biomeID;
                }
                else
                {
                    ai1[j + i * par3] = allowedBiomes[nextInt(allowedBiomes.length)].biomeID;
                }
            }
        }

        return ai1;
    }

    private WorldTypes worldType;
    private BiomeGenBase allowedBiomes[];
}
