package dayz.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.BiomeManager;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import dayz.DayZ;
import dayz.common.blocks.BlockBarbedWire;
import dayz.common.blocks.BlockBase;
import dayz.common.blocks.BlockChestDayZ;
import dayz.common.blocks.BlockFence;
import dayz.common.blocks.BlockNails;
import dayz.common.blocks.EnumChestType;
import dayz.common.effects.EffectBleeding;
import dayz.common.effects.EffectZombification;
import dayz.common.entities.EntityBandit;
import dayz.common.entities.EntityBullet;
import dayz.common.entities.EntityCrawler;
import dayz.common.entities.EntityGrenade;
import dayz.common.entities.EntityZombieDayZ;
import dayz.common.misc.ChestHookRegistry;
import dayz.common.misc.Config;
import dayz.common.misc.DayZDamageSource;
import dayz.common.misc.DayZLog;
import dayz.common.misc.Updater;
import dayz.common.misc.Util;

public class CommonProxy
{	
	public static void DayZpreload(FMLPreInitializationEvent event) 
	{
    	DayZLog.configureLogging();
    	Updater.getWebVersion();
    	Config.init(event);
    	DayZLog.info("Config loaded.");
	}
    
	public static void DayZload(FMLInitializationEvent event) 
	{
    	/************* 						Blocks 							*************/
    	
        DayZ.barbedwire = new BlockBarbedWire(DayZ.barbedwireID).setUnlocalizedName("barbedwire").setHardness(3F).setResistance(2F).setCreativeTab(DayZ.creativeTabDayZ);
        DayZ.dayzchestall = new BlockChestDayZ(DayZ.dayzchestallID, 0,EnumChestType.ALL).setUnlocalizedName("dayzchestall");
        DayZ.dayzchestrare = new BlockChestDayZ(DayZ.dayzchestrareID, 0,EnumChestType.RARE).setUnlocalizedName("dayzchestrare").setBlockUnbreakable().setCreativeTab(DayZ.creativeTabDayZ);
        DayZ.dayzchestcommon = new BlockChestDayZ(DayZ.dayzchestcommonID, 0,EnumChestType.COMMON).setUnlocalizedName("dayzchestcommon").setBlockUnbreakable().setCreativeTab(DayZ.creativeTabDayZ);
        DayZ.chainlinkfence = (new BlockFence(DayZ.chainlinkfenceID, "1", "1", Material.iron, false)).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("chainlinkfence").setCreativeTab(DayZ.creativeTabDayZ);
        DayZ.sandbagblock = (new BlockBase(DayZ.sandbagblockID, Material.clay)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("sandbagblock").setCreativeTab(DayZ.creativeTabDayZ);
        DayZ.nails = new BlockNails(DayZ.nailsID, Material.circuits).setUnlocalizedName("nails").setHardness(1F).setResistance(1F).setCreativeTab(DayZ.creativeTabDayZ);
        
        GameRegistry.registerBlock(DayZ.barbedwire, "barbedwire");
    	GameRegistry.registerBlock(DayZ.dayzchestall, "dayzchestall");
    	GameRegistry.registerBlock(DayZ.dayzchestrare, "dayzchestrare");
    	GameRegistry.registerBlock(DayZ.dayzchestcommon, "dayzchestcommon");
    	GameRegistry.registerBlock(DayZ.chainlinkfence, "chainlinkfence");
    	GameRegistry.registerBlock(DayZ.sandbagblock, "sandbagblock");
    	GameRegistry.registerBlock(DayZ.nails, "nails");
   
    /************* 						Entities 							*************/
        
        EntityRegistry.registerGlobalEntityID(EntityZombieDayZ.class, "Zombie", EntityRegistry.findGlobalUniqueEntityId(), 1, 2);        
        EntityRegistry.registerGlobalEntityID(EntityBandit.class, "Bandit", EntityRegistry.findGlobalUniqueEntityId(), 1, 2);        
        EntityRegistry.registerGlobalEntityID(EntityCrawler.class, "Crawler", EntityRegistry.findGlobalUniqueEntityId(), 1, 2);        
        EntityRegistry.registerGlobalEntityID(EntityGrenade.class, "Grenade", EntityRegistry.findGlobalUniqueEntityId());        

        EntityRegistry.registerModEntity(EntityBullet.class, "Bullet", 1, DayZ.INSTANCE, 250, 5, true);
        EntityRegistry.registerModEntity(EntityGrenade.class, "Grenade", 1, DayZ.INSTANCE, 250, 5, true);
               
        EntityRegistry.addSpawn(EntityZombieDayZ.class, 200, 1, 4, EnumCreatureType.creature, DayZ.biomeDayZForest, DayZ.biomeDayZPlains, DayZ.biomeDayZRiver, DayZ.biomeDayZSnowMountains, DayZ.biomeDayZSnowPlains);
        EntityRegistry.addSpawn(EntityCrawler.class, 100, 1, 4, EnumCreatureType.creature, DayZ.biomeDayZForest, DayZ.biomeDayZPlains, DayZ.biomeDayZRiver, DayZ.biomeDayZSnowMountains, DayZ.biomeDayZSnowPlains);
        EntityRegistry.addSpawn(EntityBandit.class, 10, 1, 4, EnumCreatureType.creature, DayZ.biomeDayZForest, DayZ.biomeDayZPlains, DayZ.biomeDayZRiver, DayZ.biomeDayZSnowMountains, DayZ.biomeDayZSnowPlains);

        if (DayZ.canSpawnZombiesInDefaultWorld == true)
        {
            EntityRegistry.addSpawn(EntityZombieDayZ.class, 200, 1, 4, EnumCreatureType.creature, WorldType.base12Biomes);
            EntityRegistry.addSpawn(EntityCrawler.class, 100, 1, 4, EnumCreatureType.creature, WorldType.base12Biomes);
            EntityRegistry.addSpawn(EntityBandit.class, 10, 1, 4, EnumCreatureType.creature, WorldType.base12Biomes);
        }
        
        LanguageRegistry.instance().addStringLocalization("entity.Crawler.name", "en_US", "Crawler");
        LanguageRegistry.instance().addStringLocalization("entity.DayZZombie.name", "en_US", "Zombie");
        LanguageRegistry.instance().addStringLocalization("entity.Bandit.name", "en_US", "Bandit");
        LanguageRegistry.instance().addStringLocalization("generator.DAYZBASE", "en_US", "Day Z Original");
        LanguageRegistry.instance().addStringLocalization("generator.DAYZSNOW", "en_US", "Day Z Snow");
        LanguageRegistry.instance().addStringLocalization("itemGroup.creativeTabDayZ", "en_US", "Day Z");
        LanguageRegistry.instance().addStringLocalization("container.ALL", "en_US", "DayZ Chest");
        LanguageRegistry.instance().addStringLocalization("container.RARE", "en_US", "DayZ Rare Chest");
        LanguageRegistry.instance().addStringLocalization("container.COMMON", "en_US", "DayZ Common Chest");

        LanguageRegistry.addName(DayZ.matches, "Matches");
        LanguageRegistry.addName(DayZ.dbshotgun, "Doublebarrel Shotgun");
        LanguageRegistry.addName(DayZ.dbshotgunammo, "12 gauge");
        LanguageRegistry.addName(DayZ.cannedpasta, "Franco-American Canned Pasta");
        LanguageRegistry.addName(DayZ.heinz, "Heinz Canned Baked Beans");
        LanguageRegistry.addName(DayZ.glock17, "Glock 17");
        LanguageRegistry.addName(DayZ.glock17ammo, "9mm Ammo");
        LanguageRegistry.addName(DayZ.leeenfield, "Lee Enfield");
        LanguageRegistry.addName(DayZ.leeenfieldammo, ".303");
        LanguageRegistry.addName(DayZ.cannedspag, "Can of Spagetti");
        LanguageRegistry.addName(DayZ.cannedbeans, "Can of Beans");
        LanguageRegistry.addName(DayZ.cannedfish, "Can of Sardines");
        LanguageRegistry.addName(DayZ.waterbottlefull, "Full Waterbottle");
        LanguageRegistry.addName(DayZ.waterbottleempty, "Empty Waterbottle");
        LanguageRegistry.addName(DayZ.whiskeybottlefull, "Whiskey");
        LanguageRegistry.addName(DayZ.whiskeybottleempty, "Empty Whiskey Bottle");
        LanguageRegistry.addName(DayZ.bandage, "Bandage");
        LanguageRegistry.addName(DayZ.antibiotics, "Anti-biotics");
        LanguageRegistry.addName(DayZ.lemonade, "Can of Lemonade");
        LanguageRegistry.addName(DayZ.makarovammo, "Makarov Magazine");
        LanguageRegistry.addName(DayZ.makarov, "Makarov");
        LanguageRegistry.addName(DayZ.ak74uammo, "AK74u Magazine");
        LanguageRegistry.addName(DayZ.ak74u, "AK74u");
        LanguageRegistry.addName(DayZ.remingtonammo, "12g Slugs");
        LanguageRegistry.addName(DayZ.remington, "Remington Shotgun");
        LanguageRegistry.addName(DayZ.chocolate, "Chocolate Bar");
        LanguageRegistry.addName(DayZ.camohelmet, "Camo Helmet");
        LanguageRegistry.addName(DayZ.camochest, "Camo Chestplate");
        LanguageRegistry.addName(DayZ.camolegs, "Camo Pants");
        LanguageRegistry.addName(DayZ.camoboots, "Camo Boots");
        LanguageRegistry.addName(DayZ.barbedwire, "Barbed Wire");
        LanguageRegistry.addName(DayZ.sandbagblock, "Sandbag Block");
        LanguageRegistry.addName(DayZ.chainlinkfence, "Chain-link Fence");
        LanguageRegistry.addName(DayZ.dayzchestall, "Day Z Chest");
        LanguageRegistry.addName(DayZ.dayzchestrare, "Day Z Rare Chest");
        LanguageRegistry.addName(DayZ.dayzchestcommon, "Day Z Common Chest");
        LanguageRegistry.addName(DayZ.baseballbat, "Baseball Bat");
        LanguageRegistry.addName(DayZ.baseballbatnailed, "Nailed Baseball Bat");
        LanguageRegistry.addName(DayZ.plank, "Plank");
        LanguageRegistry.addName(DayZ.planknailed, "Nailed Plank");
        LanguageRegistry.addName(DayZ.pipe, "Pipe");
        LanguageRegistry.addName(DayZ.waterbottledirty, "Dirty Water Bottle");
        LanguageRegistry.addName(DayZ.bloodbag, "Blood Bag");
        LanguageRegistry.addName(DayZ.matches, "Book of Matches");
        LanguageRegistry.addName(DayZ.nails, "Nails"); 
        LanguageRegistry.addName(DayZ.grenade, "Grenade"); 
        LanguageRegistry.addName(DayZ.crowbar, "Crowbar"); 
        LanguageRegistry.addName(DayZ.machete, "Machete"); 
        LanguageRegistry.addName(DayZ.emptyCan, "Empty Can");
        LanguageRegistry.addName(DayZ.orangeDrink, "Orange Drink");
        LanguageRegistry.addName(DayZ.colaDrink, "Cola");
        LanguageRegistry.addName(DayZ.colaDrink2, "Cola");
        LanguageRegistry.addName(DayZ.energyDrink, "Energy Drink");
        LanguageRegistry.addName(DayZ.colaDrink3, "Cola");
        LanguageRegistry.addName(DayZ.appleDrink, "Apple Drink"); 
        LanguageRegistry.addName(DayZ.vodkabottleempty, "Empty Vodka Bottle"); 
        LanguageRegistry.addName(DayZ.vodkabottlefull, "Vodka"); 
        LanguageRegistry.addName(DayZ.ciderbottleempty, "Empty Cider Bottle"); 
        LanguageRegistry.addName(DayZ.ciderbottlefull, "Cider"); 
                
        GameRegistry.addShapelessRecipe(new ItemStack(DayZ.baseballbatnailed, 1), new Object[] 
        {
        	new ItemStack(DayZ.baseballbat, 1),
        	new ItemStack(DayZ.nails, 1, 0)
        });
        
        GameRegistry.addShapelessRecipe(new ItemStack(DayZ.planknailed, 1), new Object[] 
        {
        	new ItemStack(DayZ.plank, 1),
        	new ItemStack(DayZ.nails, 1, 0)
        });
        
        GameRegistry.addRecipe(new ItemStack(DayZ.nails, 8), new Object [] 
        {
        	"#", "#",
        	Character.valueOf('#'), Item.ingotIron
        });
        
        GameRegistry.addRecipe(new ItemStack(DayZ.plank, 1), new Object [] 
        {
        	"#", "#", "#",
        	Character.valueOf('#'), Block.planks
        });
        
        GameRegistry.addRecipe(new ItemStack(DayZ.baseballbat, 1), new Object [] 
        {
        	"##!",
        	Character.valueOf('#'), Block.planks,
        	Character.valueOf('!'), Item.stick
        });
        
        GameRegistry.addSmelting(DayZ.waterbottledirty.itemID, new ItemStack(DayZ.waterbottlefull, 1), 0.2F);

        ChestHookRegistry.init();
        BiomeManager.addVillageBiome(DayZ.biomeDayZForest, true);
        BiomeManager.addVillageBiome(DayZ.biomeDayZPlains, true);
        BiomeManager.addVillageBiome(DayZ.biomeDayZRiver, true);
        EffectBleeding.INSTANCE.register();
        EffectZombification.INSTANCE.register();
        DayZDamageSource.bleedOut.registerDeathMessage();
        DayZDamageSource.zombieInfection.registerDeathMessage();
        DayZDamageSource.thirstDeath.registerDeathMessage();
	}
	
	public static void DayZpostload(FMLPostInitializationEvent event) 
	{
		ChestHookRegistry.postInit();
		
		boolean isServer = FMLCommonHandler.instance().getSide().isServer();

    	if (Loader.isModLoaded("ThirstMod"))
    	{
    		DayZLog.severe("Thirst Mod is not compatible with DayZ, DayZ has it's own thirst system. Remove the Thirst Mod to fix this error.");
    		Minecraft.getMinecraft().shutdownMinecraftApplet();
    	}
    	
		if (isServer) 
		{
			if (Updater.isUpdated() == false && DayZ.canCheckUpdate == true)
			{
				DayZ.logger.info("Day Z is not up to date. The latest release is " + Util.WEBVERSION + ". You have " + Util.VERSION + Updater.preRelease());
			}
			DayZ.logger.info("Day Z " + Util.VERSION + Updater.preRelease() + " Loaded.");
			
			DayZ.logger.info("Make sure your server.properties has one of the lines to create a DayZ world.");
			DayZ.logger.info("level-type=DAYZBASE            - To create the original DayZ world.");
			DayZ.logger.info("level-type=DAYZSNOW            - To create snowy DayZ world.");
		}	
	}
}