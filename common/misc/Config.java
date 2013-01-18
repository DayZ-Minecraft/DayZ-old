package dayz.common.misc;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import dayz.DayZ;

public class Config 
{
	public static void init(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(new File(Minecraft.getMinecraftDir(), "DayZ/DayZ Config.txt"));

        config.load();

        /*
         * Blocks
         */
        
        DayZ.barbedwireID = config.get(Configuration.CATEGORY_BLOCK, "barbedwireID", 160, "Barbed Wire Block ID").getInt();
        DayZ.dayzchestallID = config.get(Configuration.CATEGORY_BLOCK, "dayzchestallID", 161, "All Item Chest Block ID").getInt();
        DayZ.dayzchestrareID = config.get(Configuration.CATEGORY_BLOCK, "dayzchestrareID", 162, "Rare Item Chest Block ID").getInt();
        DayZ.dayzchestcommonID = config.get(Configuration.CATEGORY_BLOCK, "dayzchestcommonID", 163, "Common Item Chest ID").getInt();
        DayZ.chainlinkfenceID = config.get(Configuration.CATEGORY_BLOCK, "chainlinkfenceID", 164, "Chainlink Fence Block ID").getInt();
        DayZ.sandbagblockID = config.get(Configuration.CATEGORY_BLOCK, "sandbagblockID", 165, "Sandbag Block ID").getInt();
        DayZ.nailsID = config.get(Configuration.CATEGORY_BLOCK, "nailsID", 170, "Nail Block ID").getInt();
        
        /*
         * General
         */        
        
        DayZ.canCheckUpdate = config.get(Configuration.CATEGORY_GENERAL, "canCheckUpdate", true, "Should DayZ check for updates?").getBoolean(true);
        DayZ.canShowDebugScreen = config.get(Configuration.CATEGORY_GENERAL, "canShowDebugScreen", true, "Should DayZ show the debug screen?").getBoolean(true);
        DayZ.canShowNameOnDebugScreen = config.get(Configuration.CATEGORY_GENERAL, "canShowNameOnDebugScreen", true, "Should the player's name be shown on the debug screen?").getBoolean(true);
        DayZ.canShowCoordinatesOnDebugScreen = config.get(Configuration.CATEGORY_GENERAL, "canShowCoordinatesOnDebugScreen", true, "Should the player's coordinates be shown on the debug screen?").getBoolean(true);
        DayZ.canGenerateExplosives = config.get(Configuration.CATEGORY_GENERAL, "canGenerateExplosives", true, "Should DayZ chests generate explosives?").getBoolean(true);        
        DayZ.chanceToRegenChestContents = config.get(Configuration.CATEGORY_GENERAL, "chanceToRegenChestContents", 5, "Rate of chest item regeneration.").getInt(5);        
        DayZ.canSpawnZombiesInDefaultWorld = config.get(Configuration.CATEGORY_GENERAL, "canSpawnZombiesInDefaultWorld", true, "Should DayZ zombies generate in the surface world?").getBoolean(true);
        
		config.save();
	}
}
