package dayz.common.misc;

import java.io.File;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import dayz.common.blocks.Blocks;
import dayz.common.effects.Effect;
import dayz.common.items.Items;
import dayz.common.world.WorldTypes;
import dayz.common.world.biomes.Biomes;

public class Config
{
    public static boolean debug;
    public static int structureGenerationChance;
    public static boolean canSpawnZombiesInDefaultWorld;

    public static void init(FMLPreInitializationEvent event)
    {
        Configuration config = new Configuration(new File(event.getModConfigurationDirectory(), "DayZ Config.txt"));

        config.load();

        Blocks.blockConfig(config);
        Items.itemConfig(config);
        Biomes.biomeConfig(config);
        WorldTypes.worldTypeConfig(config);
        Effect.effectConfig(config);

        debug = config.get(Configuration.CATEGORY_GENERAL, "debug", false, "Should DayZ log extra information?").getBoolean(false);
        structureGenerationChance = config.get(Configuration.CATEGORY_GENERAL, "structureGenerationChance", 5, "1 in x chance to generate a structure in a given chunk").getInt(5);
        canSpawnZombiesInDefaultWorld = config.get(Configuration.CATEGORY_GENERAL, "canSpawnZombiesInDefaultWorld", false, "Should DayZ zombies generate in the surface world?").getBoolean(false);

        config.save();
    }
}