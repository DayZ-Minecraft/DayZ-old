package dayz.common.misc;

import java.io.File;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import dayz.DayZ;
import dayz.common.effects.Effect;
import dayz.common.world.WorldTypes;

public class Config
{
    public static boolean debug;
    public static boolean canCheckUpdate;
    public static boolean canGenerateExplosives;
    public static int chanceToRegenChestContents;
    public static int structureGenerationChance;
    public static boolean canSpawnZombiesInDefaultWorld;

    public static void init(FMLPreInitializationEvent event)
    {
        Configuration config = new Configuration(new File(event.getModConfigurationDirectory(), "DayZ Config.txt"));

        config.load();

        DayZ.block().blockConfig(config);
        DayZ.item().itemConfig(config);
        DayZ.biomes().biomeConfig(config);
        WorldTypes.worldTypeConfig(config);
        Effect.effectConfig(config);

        canCheckUpdate = config.get(Configuration.CATEGORY_GENERAL, "canCheckUpdate", true, "Should DayZ check for updates?").getBoolean(true);
        debug = config.get(Configuration.CATEGORY_GENERAL, "debug", false, "Should DayZ log extra information?").getBoolean(false);
        canGenerateExplosives = config.get(Configuration.CATEGORY_GENERAL, "canGenerateExplosives", true, "Should DayZ chests generate explosives?").getBoolean(true);
        chanceToRegenChestContents = config.get(Configuration.CATEGORY_GENERAL, "chanceToRegenChestContents", 5, "Rate of chest item regeneration.").getInt(5);
        structureGenerationChance = config.get(Configuration.CATEGORY_GENERAL, "structureGenerationChance", 5, "1 in x chance to generate a structure in a given chunk").getInt(5);
        canSpawnZombiesInDefaultWorld = config.get(Configuration.CATEGORY_GENERAL, "canSpawnZombiesInDefaultWorld", false, "Should DayZ zombies generate in the surface world?").getBoolean(false);

        config.save();
    }
}