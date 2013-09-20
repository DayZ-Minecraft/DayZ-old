package dayz.common.world.biomes;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.Configuration;

public class Biomes
{
    public static BiomeGenBase biomeForest;
    private static int biomeForestId;
    public static BiomeGenBase biomePlains;
    private static int biomePlainsId;
    public static BiomeGenBase biomeRiver;
    private static int biomeRiverId;
    public static BiomeGenBase biomeSnowPlains;
    private static int biomeSnowPlainsId;
    public static BiomeGenBase biomeSnowMountains;
    private static int biomeSnowMountainsId;

    public static void loadBiomes()
    {
        biomeForest = (new BiomeForest(biomeForestId));
        biomePlains = (new BiomePlains(biomePlainsId));
        biomeRiver = (new BiomeRiver(biomeRiverId));
        biomeSnowPlains = (new BiomeSnow(biomeSnowPlainsId).setMinMaxHeight(0.0F, 0.0F).setBiomeName("Snow Plains").setEnableSnow());
        biomeSnowMountains = (new BiomeSnow(biomeSnowMountainsId).setMinMaxHeight(0.0F, 0.5F).setBiomeName("Snow Mountains").setEnableSnow());
    }

    public static void biomeConfig(Configuration config)
    {
        biomeForestId = config.get("biome", "biomeForestId", 25).getInt();
        biomePlainsId = config.get("biome", "biomePlainsId", 26).getInt();
        biomeRiverId = config.get("biome", "biomeRiverId", 27).getInt();
        biomeSnowPlainsId = config.get("biome", "biomeSnowPlainsId", 28).getInt();
        biomeSnowMountainsId = config.get("biome", "biomeSnowMountainsId", 29).getInt();
    }

    public static void addVillages()
    {
        BiomeManager.addVillageBiome(Biomes.biomeForest, true);
        BiomeManager.addVillageBiome(Biomes.biomePlains, true);
        BiomeManager.addVillageBiome(Biomes.biomeRiver, true);
        BiomeManager.addVillageBiome(Biomes.biomeSnowPlains, true);
        BiomeManager.addVillageBiome(Biomes.biomeSnowMountains, true);
    }
}
