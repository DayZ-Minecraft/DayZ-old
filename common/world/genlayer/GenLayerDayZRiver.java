package dayz.common.world.genlayer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import dayz.common.world.WorldTypeBase;

public class GenLayerDayZRiver extends GenLayer
{
    public GenLayerDayZRiver(long l, GenLayer genlayer, WorldTypeBase worldtype)
    {
        super(l);
        super.parent = genlayer;
		worldType = worldtype;
    }

    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int i = par1 - 1;
        int j = par2 - 1;
        int k = par3 + 2;
        int l = par4 + 2;
        int ai[] = parent.getInts(i, j, k, l);
        int ai1[] = IntCache.getIntCache(par3 * par4);

        for (int i1 = 0; i1 < par4; i1++)
        {
            for (int j1 = 0; j1 < par3; j1++)
            {
                int k1 = ai[j1 + 0 + (i1 + 1) * k];
                int l1 = ai[j1 + 2 + (i1 + 1) * k];
                int i2 = ai[j1 + 1 + (i1 + 0) * k];
                int j2 = ai[j1 + 1 + (i1 + 2) * k];
                int k2 = ai[j1 + 1 + (i1 + 1) * k];

				BiomeGenBase river = worldType.setRiverBiomes(null, this);
                if (river != null && (k2 == 0 || k1 == 0 || l1 == 0 || i2 == 0 || j2 == 0))
                {
                    ai1[j1 + i1 * par3] = river.biomeID;
                    continue;
                }

                if (river != null && (k2 != k1 || k2 != i2 || k2 != l1 || k2 != j2))
                {
                    ai1[j1 + i1 * par3] = river.biomeID;
                }
                else
                {
                    ai1[j1 + i1 * par3] = -1;
                }
            }
        }

        return ai1;
    }
	
	private WorldTypeBase worldType;
}
