package dayz.common;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import dayz.DayZ;
import dayz.common.blocks.Blocks;
import dayz.common.effects.Effect;
import dayz.common.entities.EntityBullet;
import dayz.common.entities.EntityCrawler;
import dayz.common.entities.EntityZombieDayZ;
import dayz.common.items.Items;
import dayz.common.misc.ChatHandler;
import dayz.common.misc.Config;
import dayz.common.misc.LootManager;
import dayz.common.misc.Updater;
import dayz.common.misc.Util;
import dayz.common.world.WorldTypes;
import dayz.common.world.biomes.Biomes;
import dayz.common.world.generation.StructureHandler;

public class CommonProxy
{
    public void preload(FMLPreInitializationEvent event)
    {
        ChatHandler.log = Logger.getLogger(Util.ID);
        MinecraftForge.EVENT_BUS.register(new CommonEvents());
        MinecraftForge.TERRAIN_GEN_BUS.register(new CommonEventsTerrain());
        Updater.getWebVersion();
        Config.init(event);
        ChatHandler.logInfo("Config loaded.");
    }

    public void load(FMLInitializationEvent event)
    {
        GameRegistry.registerPlayerTracker(new CommonPlayerHandler());
        TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);

        Blocks.loadBlocks();
        Items.loadItems();
        Biomes.loadBiomes();
        Biomes.addVillages();
        WorldTypes.loadWorldTypes();
        Effect.loadEffects();
        StructureHandler.addDefaultStructures();
        LootManager.init();
        Effect.register();

        EntityRegistry.registerGlobalEntityID(EntityZombieDayZ.class, "Zombie", EntityRegistry.findGlobalUniqueEntityId(), 1, 2);
        EntityRegistry.registerGlobalEntityID(EntityCrawler.class, "Crawler", EntityRegistry.findGlobalUniqueEntityId(), 1, 2);

        EntityRegistry.registerModEntity(EntityBullet.class, "Bullet", 1, DayZ.INSTANCE, 250, 5, true);

        EntityRegistry.addSpawn(EntityZombieDayZ.class, 200, 1, 4, EnumCreatureType.creature, Biomes.biomeForest, Biomes.biomePlains, Biomes.biomeRiver, Biomes.biomeSnowMountains, Biomes.biomeSnowPlains);
        EntityRegistry.addSpawn(EntityCrawler.class, 100, 1, 4, EnumCreatureType.creature, Biomes.biomeForest, Biomes.biomePlains, Biomes.biomeRiver, Biomes.biomeSnowMountains, Biomes.biomeSnowPlains);

        if (Config.canSpawnZombiesInDefaultWorld == true)
        {
            EntityRegistry.addSpawn(EntityZombieDayZ.class, 200, 1, 4, EnumCreatureType.creature, WorldType.base12Biomes);
            EntityRegistry.addSpawn(EntityCrawler.class, 100, 1, 4, EnumCreatureType.creature, WorldType.base12Biomes);
        }

        LanguageRegistry.instance().addStringLocalization("entity.Crawler.name", "en_US", "Crawler");
        LanguageRegistry.instance().addStringLocalization("entity.DayZZombie.name", "en_US", "Zombie");
        LanguageRegistry.instance().addStringLocalization("generator.DAYZBASE", "en_US", "Day Z Original");
        LanguageRegistry.instance().addStringLocalization("generator.DAYZSNOW", "en_US", "Day Z Snow");
        LanguageRegistry.instance().addStringLocalization("itemGroup.creativeTabDayZ", "en_US", "Day Z");
        LanguageRegistry.instance().addStringLocalization("death.attack.bleedOut", "%1$s lost too much blood");
        LanguageRegistry.instance().addStringLocalization("death.attack.zombieInfection", "%1$s became a zombie");
        LanguageRegistry.instance().addStringLocalization("death.attack.thirstDeath", "%1$s ran out of water");
    }

    public void postload(FMLPostInitializationEvent event)
    {
        boolean isServer = FMLCommonHandler.instance().getSide().isServer();

        if (Loader.isModLoaded("ThirstMod"))
        {
            ChatHandler.logException(Level.SEVERE, "Thirst Mod is not compatible with DayZ, DayZ has it's own thirst system. Remove the Thirst Mod to fix this error.");
        }

        if (isServer)
        {
            if (Updater.isUpdated() == false && Config.canCheckUpdate == true)
            {
                Logger.getLogger("Minecraft").info("Day Z is not up to date. The latest release is " + Util.WEBVERSION + ". You have " + Util.VERSION);
            }
            Logger.getLogger("Minecraft").info("Day Z " + Util.VERSION + " Loaded.");

            Logger.getLogger("Minecraft").info("Make sure your server.properties has one of the lines to create a DayZ world.");
            Logger.getLogger("Minecraft").info("level-type=DAYZBASE            - To create the original DayZ world.");
            Logger.getLogger("Minecraft").info("level-type=DAYZSNOW            - To create snowy DayZ world.");
        }
    }
}