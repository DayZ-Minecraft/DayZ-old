package dayz.common;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.WeightedRandomChestContent;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import dayz.DayZ;
import dayz.DayZLog;
import dayz.Updater;
import dayz.Util;
import dayz.common.blocks.BlockBarbedWire;
import dayz.common.blocks.BlockBase;
import dayz.common.blocks.BlockChestAll;
import dayz.common.blocks.BlockChestCommon;
import dayz.common.blocks.BlockChestRare;
import dayz.common.blocks.BlockFence;
import dayz.common.blocks.BlockNails;
import dayz.common.entities.EntityBandit;
import dayz.common.entities.EntityBullet;
import dayz.common.entities.EntityCrawler;
import dayz.common.entities.EntityGrenade;
import dayz.common.entities.EntityZombieDayZ;

public class CommonProxy implements IPlayerTracker 
{	
	public static void DayZpreload(FMLPreInitializationEvent event) 
	{
    	DayZLog.configureLogging();

        DayZ.properties = new Properties(new File("dayz.properties"));
        
    	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		try 
		{
			config.load();

			DayZ.barbedwireID = config.getBlock("barbedwireID", 160).getInt();
			DayZ.dayzchestallID = config.getBlock("dayzchestallID", 161).getInt();
			DayZ.dayzchestrareID = config.getBlock("dayzchestrareID", 162).getInt();
			DayZ.dayzchestcommonID = config.getBlock("dayzchestcommonID", 163).getInt();
			DayZ.chainlinkfenceID = config.getBlock("chainlinkfenceID", 164).getInt();
			DayZ.sandbagblockID = config.getBlock("sandbagblockID", 165).getInt();
			DayZ.nailsID = config.getBlock("nailsID", 170).getInt();

		}
		catch (final Exception e) 
		{
			DayZLog.log(Level.SEVERE, e, "DayZ had a problem loading it's configuration.");
		} 
		finally 
		{
			config.save();
			DayZLog.info("Config Loaded");
		}
				
		if (DayZ.canCheckUpdate == true)
		{
			if (Updater.isUpdated() == false)
			{
		        DayZLog.info("Day Z is not up to date. The latest release is " + Updater.getWebVersion() + ". You have " + Util.VERSION + Updater.preRelease());
		        DayZ.isUpToDate = false;
			}
		}
		
		/* Properties */
		
        DayZ.logger.info("Loading properties");
        
        PropertiesManager.setCanCheckUpdate(DayZ.properties.getBooleanProperty("show-debug-screen", true));
        PropertiesManager.setCanShowDebugScreen(DayZ.properties.getBooleanProperty("show-name-on-debug-screen", true));
        PropertiesManager.setCanShowNameOnDebugScreen(DayZ.properties.getBooleanProperty("show-coordinates-on-debug-screen", true));
        PropertiesManager.setCanShowCoordinatesOnDebugScreen(DayZ.properties.getBooleanProperty("check-update", true));
        PropertiesManager.setCanGenerateExplosives(DayZ.properties.getBooleanProperty("can-generate-explosives", true));
        DayZ.chanceToRegenChestContents = DayZ.properties.getIntProperty("chance-to-regen-chest-contents", 5);

        DayZ.barbedwireID = config.getBlock("barbedwireID", 160).getInt();
		DayZ.dayzchestallID = config.getBlock("dayzchestallID", 161).getInt();
		DayZ.dayzchestrareID = config.getBlock("dayzchestrareID", 162).getInt();
		DayZ.dayzchestcommonID = config.getBlock("dayzchestcommonID", 163).getInt();
		DayZ.chainlinkfenceID = config.getBlock("chainlinkfenceID", 164).getInt();
		DayZ.sandbagblockID = config.getBlock("sandbagblockID", 165).getInt();
		DayZ.nailsID = config.getBlock("nailsID", 170).getInt();		
        DayZ.logger.info("Properties Loaded");
        
	}
    
	public static void DayZload(FMLInitializationEvent event) 
	{
    	/************* 						Blocks 							*************/
    	
        DayZ.barbedwire = new BlockBarbedWire(DayZ.barbedwireID, 0).setBlockName("barbedwire").setHardness(3F).setResistance(2F).setCreativeTab(DayZ.creativeTabDayZ);
        DayZ.dayzchestall = new BlockChestAll(DayZ.dayzchestallID).setBlockName("dayzchestall").setBlockUnbreakable().setCreativeTab(DayZ.creativeTabDayZ);
        DayZ.dayzchestrare = new BlockChestRare(DayZ.dayzchestrareID).setBlockName("dayzchestrare").setBlockUnbreakable().setCreativeTab(DayZ.creativeTabDayZ);
        DayZ.dayzchestcommon = new BlockChestCommon(DayZ.dayzchestcommonID).setBlockName("dayzchestcommon").setBlockUnbreakable().setCreativeTab(DayZ.creativeTabDayZ);
        DayZ.chainlinkfence = (new BlockFence(DayZ.chainlinkfenceID, 1, 1, Material.iron, false)).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setBlockName("chainlinkfence").setCreativeTab(DayZ.creativeTabDayZ);
        DayZ.sandbagblock = (new BlockBase(DayZ.sandbagblockID, 2, Material.clay)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundGrassFootstep).setBlockName("sandbagblock").setCreativeTab(DayZ.creativeTabDayZ);
        DayZ.nails = new BlockNails(DayZ.nailsID, 3, Material.leaves).setBlockName("nails").setHardness(1F).setResistance(1F).setCreativeTab(DayZ.creativeTabDayZ);
        
    	GameRegistry.registerBlock(DayZ.barbedwire);
    	GameRegistry.registerBlock(DayZ.dayzchestall);
    	GameRegistry.registerBlock(DayZ.dayzchestrare);
    	GameRegistry.registerBlock(DayZ.dayzchestcommon);
    	GameRegistry.registerBlock(DayZ.chainlinkfence);
    	GameRegistry.registerBlock(DayZ.sandbagblock);
    	GameRegistry.registerBlock(DayZ.nails);
   
    /************* 						Entities 							*************/
        
        EntityRegistry.registerGlobalEntityID(EntityZombieDayZ.class, "Zombie", EntityRegistry.findGlobalUniqueEntityId(), 1, 2);        
        EntityRegistry.registerGlobalEntityID(EntityBandit.class, "Bandit", EntityRegistry.findGlobalUniqueEntityId(), 1, 2);        
        EntityRegistry.registerGlobalEntityID(EntityCrawler.class, "Crawler", EntityRegistry.findGlobalUniqueEntityId(), 1, 2);        
        EntityRegistry.registerGlobalEntityID(EntityGrenade.class, "Grenade", EntityRegistry.findGlobalUniqueEntityId());        

        EntityRegistry.registerModEntity(EntityBullet.class, "Bullet", 1, DayZ.INSTANCE, 250, 5, true);
        EntityRegistry.registerModEntity(EntityGrenade.class, "Grenade", 1, DayZ.INSTANCE, 250, 5, true);
               
        EntityRegistry.addSpawn(EntityZombieDayZ.class, 200, 1, 4, EnumCreatureType.creature, DayZ.biomeDayZForest, DayZ.biomeDayZPlains, DayZ.biomeDayZRiver);
        EntityRegistry.addSpawn(EntityCrawler.class, 100, 1, 4, EnumCreatureType.creature, DayZ.biomeDayZForest, DayZ.biomeDayZPlains, DayZ.biomeDayZRiver);
        EntityRegistry.addSpawn(EntityBandit.class, 10, 1, 4, EnumCreatureType.creature, DayZ.biomeDayZForest, DayZ.biomeDayZPlains, DayZ.biomeDayZRiver);

    /************* 						Names 							*************/
        
        LanguageRegistry.instance().addStringLocalization("entity.Crawler.name", "en_US", "Crawler");
        LanguageRegistry.instance().addStringLocalization("entity.DayZZombie.name", "en_US", "Zombie");
        LanguageRegistry.instance().addStringLocalization("entity.Bandit.name", "en_US", "Bandit");
        LanguageRegistry.instance().addStringLocalization("generator.DAYZBASE", "en_US", "Day Z Original");
        LanguageRegistry.instance().addStringLocalization("itemGroup.creativeTabDayZ", "en_US", "Day Z");

        
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
        LanguageRegistry.addName(DayZ.whiskeybottlefull, "Full Whiskey Bottle");
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
        
    /************* 						Recipes 							*************/
        
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
        	"#", "!", "!",
        	Character.valueOf('#'), Block.planks,
        	Character.valueOf('!'), Item.stick
        });
        
        GameRegistry.addSmelting(DayZ.waterbottledirty.shiftedIndex, new ItemStack(DayZ.waterbottlefull, 1), 0.2F);
        
    /************* 						Generation 							*************/

        ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.ak74uammo.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.leeenfieldammo.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.dbshotgunammo.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.bandage.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.baseballbatnailed.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.makarov.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.glock17.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.pipe.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.planknailed.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.remingtonammo.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Item.map.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Item.coal.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Item.ingotIron.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Item.writableBook.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Item.arrow.shiftedIndex, 0, 1, 8, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Item.bone.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.bloodbag.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Block.tripWireSource.blockID, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Item.silk.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Item.boat.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Item.emerald.shiftedIndex, 0, 1, 3, 5));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.baseballbat.shiftedIndex, 0, 1, 1, 7));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.makarovammo.shiftedIndex, 0, 1, 1, 7));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.glock17ammo.shiftedIndex, 0, 1, 1, 7));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.plank.shiftedIndex, 0, 1, 1, 7));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.whiskeybottlefull.shiftedIndex, 0, 1, 1, 7));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.nails.blockID, 0, 1, 1, 7));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Item.beefRaw.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Item.beefCooked.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Item.porkRaw.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Item.porkCooked.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Item.fishRaw.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Item.fishCooked.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Item.appleRed.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Item.bowlSoup.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Item.melon.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Item.cookie.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.cannedbeans.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.cannedfish.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.cannedspag.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.cannedpasta.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.heinz.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.chocolate.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.lemonade.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.antibiotics.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.waterbottledirty.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.waterbottleempty.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.whiskeybottleempty.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(DayZ.waterbottledirty.shiftedIndex, 0, 1, 1, 9));
    	
        ChestGenHooks.removeItem(ChestGenHooks.BONUS_CHEST, new ItemStack(Item.stick));
        ChestGenHooks.removeItem(ChestGenHooks.BONUS_CHEST, new ItemStack(Block.planks));
        ChestGenHooks.removeItem(ChestGenHooks.BONUS_CHEST, new ItemStack(Block.wood));
        ChestGenHooks.removeItem(ChestGenHooks.BONUS_CHEST, new ItemStack(Item.axeStone));
        ChestGenHooks.removeItem(ChestGenHooks.BONUS_CHEST, new ItemStack(Item.axeWood));
        ChestGenHooks.removeItem(ChestGenHooks.BONUS_CHEST, new ItemStack(Item.pickaxeStone));
        ChestGenHooks.removeItem(ChestGenHooks.BONUS_CHEST, new ItemStack(Item.pickaxeWood));
        ChestGenHooks.removeItem(ChestGenHooks.BONUS_CHEST, new ItemStack(Item.appleRed));
        ChestGenHooks.removeItem(ChestGenHooks.BONUS_CHEST, new ItemStack(Item.bread));
        
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.leeenfield.shiftedIndex, 0, 1, 1, 1));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.dbshotgun.shiftedIndex, 0, 1, 1, 1));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.ak74u.shiftedIndex, 0, 1, 1, 1));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.remington.shiftedIndex, 0, 1, 1, 1));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.barbedwire.blockID, 0, 1, 1, 3));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.camohelmet.shiftedIndex, 0, 1, 1, 3));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.camochest.shiftedIndex, 0, 1, 1, 3));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.camolegs.shiftedIndex, 0, 1, 1, 3));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.camoboots.shiftedIndex, 0, 1, 1, 3));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Block.cake.blockID, 0, 1, 1, 3));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Item.bow.shiftedIndex, 0, 1, 1, 3));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.ak74uammo.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.leeenfieldammo.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.dbshotgunammo.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.bandage.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.baseballbatnailed.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.makarov.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.glock17.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.pipe.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.planknailed.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.remingtonammo.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Item.map.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Item.coal.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Item.ingotIron.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Item.writableBook.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Item.arrow.shiftedIndex, 0, 1, 8, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Item.bone.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.bloodbag.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Block.tripWireSource.blockID, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Item.silk.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Item.boat.shiftedIndex, 0, 1, 1, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Item.emerald.shiftedIndex, 0, 1, 3, 5));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.baseballbat.shiftedIndex, 0, 1, 1, 7));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.makarovammo.shiftedIndex, 0, 1, 1, 7));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.glock17ammo.shiftedIndex, 0, 1, 1, 7));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.plank.shiftedIndex, 0, 1, 1, 7));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.whiskeybottlefull.shiftedIndex, 0, 1, 1, 7));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.nails.blockID, 0, 1, 1, 7));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Item.beefRaw.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Item.beefCooked.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Item.porkRaw.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Item.porkCooked.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Item.fishRaw.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Item.fishCooked.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Item.appleRed.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Item.bowlSoup.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Item.melon.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(Item.cookie.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.cannedbeans.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.cannedfish.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.cannedspag.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.cannedpasta.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.heinz.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.chocolate.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.lemonade.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.antibiotics.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.waterbottledirty.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.waterbottleempty.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.whiskeybottleempty.shiftedIndex, 0, 1, 1, 9));
    	ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(DayZ.waterbottledirty.shiftedIndex, 0, 1, 1, 9));
        
    	ChestGenHooks.removeItem(ChestGenHooks.VILLAGE_BLACKSMITH, new ItemStack(Item.ingotIron));
        ChestGenHooks.removeItem(ChestGenHooks.VILLAGE_BLACKSMITH, new ItemStack(Item.ingotGold));
        ChestGenHooks.removeItem(ChestGenHooks.VILLAGE_BLACKSMITH, new ItemStack(Item.diamond));
        ChestGenHooks.removeItem(ChestGenHooks.VILLAGE_BLACKSMITH, new ItemStack(Item.bread));
        ChestGenHooks.removeItem(ChestGenHooks.VILLAGE_BLACKSMITH, new ItemStack(Item.appleRed));
        ChestGenHooks.removeItem(ChestGenHooks.VILLAGE_BLACKSMITH, new ItemStack(Item.pickaxeSteel));
        ChestGenHooks.removeItem(ChestGenHooks.VILLAGE_BLACKSMITH, new ItemStack(Item.swordSteel));
        ChestGenHooks.removeItem(ChestGenHooks.VILLAGE_BLACKSMITH, new ItemStack(Item.plateSteel));
        ChestGenHooks.removeItem(ChestGenHooks.VILLAGE_BLACKSMITH, new ItemStack(Item.helmetSteel));
        ChestGenHooks.removeItem(ChestGenHooks.VILLAGE_BLACKSMITH, new ItemStack(Item.bootsSteel));
        ChestGenHooks.removeItem(ChestGenHooks.VILLAGE_BLACKSMITH, new ItemStack(Item.legsSteel));
        ChestGenHooks.removeItem(ChestGenHooks.VILLAGE_BLACKSMITH, new ItemStack(Block.obsidian));
        ChestGenHooks.removeItem(ChestGenHooks.VILLAGE_BLACKSMITH, new ItemStack(Block.sapling));

        VillagerRegistry.addExtraVillageComponents(Util.piecesToAdd(), DayZ.random, 1);
        BiomeManager.addVillageBiome(DayZ.biomeDayZForest, true);
        BiomeManager.addVillageBiome(DayZ.biomeDayZPlains, true);
        BiomeManager.addVillageBiome(DayZ.biomeDayZRiver, true);
        GameRegistry.registerPlayerTracker(new CommonProxy());
	}
	
	public static void DayZpostload(FMLPostInitializationEvent event) 
	{
		boolean isServer = FMLCommonHandler.instance().getSide().isServer();

    	if (Loader.isModLoaded("ThirstMod"))
    	{
	        DayZLog.info("Thirst Mod Found!.");
    	}
    	
		if (isServer) 
		{
			if (Updater.isUpdated() == false && DayZ.canCheckUpdate == true)
			{
				DayZ.logger.info("Day Z is not up to date. The latest release is " + Updater.getWebVersion() + ". You have " + Util.VERSION + Updater.preRelease());
			}
			else
			{
				DayZ.logger.info("Day Z " + Util.VERSION + Updater.preRelease() + " Loaded.");
			}
			DayZ.logger.info("Make sure your server.properties has one of the lines to create a DayZ world.");
			DayZ.logger.info("level-type=DAYZBASE            - To create the original DayZ world.");
		}	
	}

	@Override
	public void onPlayerLogin(EntityPlayer player) 
	{

	}

	@Override
	public void onPlayerLogout(EntityPlayer player) 
	{
		
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) 
	{
		
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) 
	{
		
	}		
}