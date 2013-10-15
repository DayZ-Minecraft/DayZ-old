package dayz.common.world.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import dayz.common.world.WorldTypes;

public abstract class GenLayerDayZ extends GenLayer
{
    public GenLayerDayZ(long seed)
    {
        super(seed);
    }

    public static GenLayer[] getGenLayers(long l, WorldTypes worldtype)
    {
        GenLayer genlayer = new GenLayerIsland(1L);
        genlayer = new GenLayerFuzzyZoom(2000L, genlayer);
        genlayer = new GenLayerDayZOcean1(1L, genlayer, worldtype);
        genlayer = new GenLayerZoom(2001L, genlayer);
        genlayer = new GenLayerDayZOcean1(2L, genlayer, worldtype);
        genlayer = new GenLayerZoom(2002L, genlayer);
        genlayer = new GenLayerDayZOcean1(3L, genlayer, worldtype);
        genlayer = new GenLayerZoom(2003L, genlayer);
        genlayer = new GenLayerDayZOcean1(4L, genlayer, worldtype);
        byte byte0 = 4;
        GenLayer genlayer1 = genlayer;
        genlayer1 = GenLayerZoom.magnify(1000L, genlayer1, 0);
        genlayer1 = new GenLayerDayZOcean2(100L, genlayer1, worldtype);
        genlayer1 = GenLayerZoom.magnify(1000L, genlayer1, byte0 + 2);
        genlayer1 = new GenLayerDayZRiver(1L, genlayer1, worldtype);
        genlayer1 = new GenLayerSmooth(1000L, genlayer1);
        GenLayer genlayer2 = genlayer;
        genlayer2 = GenLayerZoom.magnify(1000L, genlayer2, 0);
        genlayer2 = new GenLayerDayZMajorBiome(200L, genlayer2, worldtype);
        genlayer2 = GenLayerZoom.magnify(1000L, genlayer2, 2);
        genlayer2 = new GenLayerDayZMinorBiome(1000L, genlayer2, worldtype);
        genlayer2 = new GenLayerDayZMiscBiome(5L, genlayer2, worldtype);
        for (int i = 0; i < byte0; i++)
        {
            genlayer2 = new GenLayerZoom(1000 + i, genlayer2);
            if (i == 0)
            {
                genlayer2 = new GenLayerDayZOcean1(3L, genlayer2, worldtype);
            }
            if (i == 1)
            {
                genlayer2 = new GenLayerDayZBorderBiome(1000L, genlayer2, worldtype);
            }
        }
        genlayer2 = new GenLayerSmooth(1000L, genlayer2);
        genlayer2 = new GenLayerDayZWater(100L, genlayer2, genlayer1, worldtype);
        GenLayerDayZWater genlayerdimensionwater = ((GenLayerDayZWater) (genlayer2));
        GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, genlayer2);
        genlayer2.initWorldGenSeed(l);
        genlayervoronoizoom.initWorldGenSeed(l);
        GenLayer genlayer3 = genlayer2;
        return (new GenLayer[]
        { genlayer3, genlayervoronoizoom, genlayerdimensionwater });
    }
}
