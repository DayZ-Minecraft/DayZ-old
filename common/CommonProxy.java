package dayz.common;

import java.io.File;
import java.util.EnumSet;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
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
import dayz.common.playerdata.PlayerData;
import dayz.common.playerdata.Thirst;

public class CommonProxy implements IPlayerTracker, ITickHandler
{	
	public static void DayZpreload(FMLPreInitializationEvent event) 
	{
    	DayZLog.configureLogging();

        DayZ.properties = new Properties(new File("dayz.properties"));
				
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
        PropertiesManager.setcanSpawnZombiesInDefaultWorld(DayZ.properties.getBooleanProperty("can-spawn-zombies-in-default-world", false));

        DayZ.barbedwireID = DayZ.properties.getIntProperty("barbedwireID", 160);
		DayZ.dayzchestallID = DayZ.properties.getIntProperty("dayzchestallID", 161);
		DayZ.dayzchestrareID = DayZ.properties.getIntProperty("dayzchestrareID", 162);
		DayZ.dayzchestcommonID = DayZ.properties.getIntProperty("dayzchestcommonID", 163);
		DayZ.chainlinkfenceID = DayZ.properties.getIntProperty("chainlinkfenceID", 164);
		DayZ.sandbagblockID = DayZ.properties.getIntProperty("sandbagblockID", 165);
		DayZ.nailsID = DayZ.properties.getIntProperty("nailsID", 170);
		
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
        DayZ.sandbagblock = (new BlockBase(DayZ.sandbagblockID, 2, Material.clay)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundSandFootstep).setBlockName("sandbagblock").setCreativeTab(DayZ.creativeTabDayZ);
        DayZ.nails = new BlockNails(DayZ.nailsID, 3, Material.circuits).setBlockName("nails").setHardness(1F).setResistance(1F).setCreativeTab(DayZ.creativeTabDayZ);
        
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
               
        EntityRegistry.addSpawn(EntityZombieDayZ.class, 200, 1, 4, EnumCreatureType.creature, DayZ.biomeDayZForest, DayZ.biomeDayZPlains, DayZ.biomeDayZRiver, DayZ.biomeDayZSnowMountains, DayZ.biomeDayZSnowPlains);
        EntityRegistry.addSpawn(EntityCrawler.class, 100, 1, 4, EnumCreatureType.creature, DayZ.biomeDayZForest, DayZ.biomeDayZPlains, DayZ.biomeDayZRiver, DayZ.biomeDayZSnowMountains, DayZ.biomeDayZSnowPlains);
        EntityRegistry.addSpawn(EntityBandit.class, 10, 1, 4, EnumCreatureType.creature, DayZ.biomeDayZForest, DayZ.biomeDayZPlains, DayZ.biomeDayZRiver, DayZ.biomeDayZSnowMountains, DayZ.biomeDayZSnowPlains);

        if (DayZ.canSpawnZombiesInDefaultWorld == true)
        {
            EntityRegistry.addSpawn(EntityZombieDayZ.class, 200, 1, 4, EnumCreatureType.creature, WorldType.base12Biomes);
            EntityRegistry.addSpawn(EntityCrawler.class, 100, 1, 4, EnumCreatureType.creature, WorldType.base12Biomes);
            EntityRegistry.addSpawn(EntityBandit.class, 10, 1, 4, EnumCreatureType.creature, WorldType.base12Biomes);
        }
    /************* 						Names 							*************/
        
        LanguageRegistry.instance().addStringLocalization("entity.Crawler.name", "en_US", "Crawler");
        LanguageRegistry.instance().addStringLocalization("entity.DayZZombie.name", "en_US", "Zombie");
        LanguageRegistry.instance().addStringLocalization("entity.Bandit.name", "en_US", "Bandit");
        LanguageRegistry.instance().addStringLocalization("generator.DAYZBASE", "en_US", "Day Z Original");
        LanguageRegistry.instance().addStringLocalization("generator.DAYZSNOW", "en_US", "Day Z Snow");
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
        LanguageRegistry.addName(DayZ.crowbar, "Crowbar"); 
        LanguageRegistry.addName(DayZ.machete, "Machete"); 
        
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

        ChestHookRegistry.chestGeneration();

        VillagerRegistry.addExtraVillageComponents(Util.piecesToAdd(), DayZ.random, 1);
        BiomeManager.addVillageBiome(DayZ.biomeDayZForest, true);
        BiomeManager.addVillageBiome(DayZ.biomeDayZPlains, true);
        BiomeManager.addVillageBiome(DayZ.biomeDayZRiver, true);
        GameRegistry.registerPlayerTracker(new CommonProxy());
        TickRegistry.registerTickHandler(new CommonProxy(), Side.SERVER);
        EffectBleeding.INSTANCE.register();
        EffectZombification.INSTANCE.register();
        EffectBrokenBones.INSTANCE.register();
        DayZDamageSource.bleedOut.registerDeathMessage();
        DayZDamageSource.zombieInfection.registerDeathMessage();
        DayZDamageSource.thirstDeath.registerDeathMessage();
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
			DayZ.logger.info("level-type=DAYZSNOW            - To create snowy DayZ world.");
		}	
	}

	@Override
	public void onPlayerLogin(EntityPlayer player) 
	{
		PlayerData.loadData(player);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) 
	{
		PlayerData.saveData(player);
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) 
	{
		PlayerData.saveData(player);
		PlayerData.loadData(player);
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) 
	{
		Thirst.resetThirst(player);
	}
	
	@ForgeSubscribe
	public void playerKilledEntity(LivingDeathEvent event)
	{
		if (event.source.getEntity() instanceof EntityPlayer)
		{
			if (event.entityLiving instanceof EntityZombieDayZ)
			{
				int totalZombieKills = PlayerData.getPlayerData((EntityPlayer)event.source.getEntity()).totalZombieKills;
				PlayerData.getPlayerData((EntityPlayer)event.source.getEntity()).totalZombieKills = totalZombieKills + 1;
			}
			if (event.entityLiving instanceof EntityCrawler)
			{
				int totalZombieKills = PlayerData.getPlayerData((EntityPlayer)event.source.getEntity()).totalZombieKills;
				PlayerData.getPlayerData((EntityPlayer)event.source.getEntity()).totalZombieKills = totalZombieKills + 1;
			}
			if (event.entityLiving instanceof EntityPlayer)
			{
				int totalPlayerKills = PlayerData.getPlayerData((EntityPlayer)event.source.getEntity()).totalPlayerKills;
				PlayerData.getPlayerData((EntityPlayer)event.source.getEntity()).totalPlayerKills = totalPlayerKills + 1;
			}
		}
	}

	@ForgeSubscribe
	public void playerfall(LivingFallEvent event)
	{
		if (event.distance > 10.0F)
		{
			event.entityLiving.addPotionEffect(new PotionEffect(EffectBrokenBones.INSTANCE.id, 600 * 20, 6));
		}
	}
	
	@ForgeSubscribe
	public void chatHandler(ServerChatEvent event)
	{
		int listLength = Util.AUTHORLIST.length;
		boolean isPlayerDev;
		for (int i = 0; i < listLength; i++)
		{
			if (event.username == Util.AUTHORLIST[1])
			{
				event.line = "&4[DEV]" + "<" + event.username + "> " + event.message;
			}
		}


	}
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) 
	{
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) 
	{
		if (type.equals(EnumSet.of(TickType.PLAYER)))
		{
			onPlayerTick((EntityPlayer)tickData[0]);
		}	
	}

	@Override
	public EnumSet<TickType> ticks() 
	{
        return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel() 
	{
		return "DayZ Thirst";
	}
	
	private void onPlayerTick(EntityPlayer player) 
	{
		if (Thirst.getLevel(player) == 20000)
		{
			player.sendChatToPlayer("I should find some water...");
			Thirst.addThirst(player, 1);
		}
		else if (Thirst.getLevel(player) >= 24000)
		{
			player.attackEntityFrom(DayZDamageSource.thirstDeath, 20);
		} 
		else if (player.isSprinting())
		{
			Thirst.addThirst(player, 2);
		}
		else if (player.isJumping)
		{
			Thirst.addThirst(player, 2);
		}
		else if (player.isDead)
		{
			return;
		}
		else
		{
			Thirst.addThirst(player, 1);
		}
		
		if (player.getActivePotionEffect(EffectBrokenBones.INSTANCE) != null)
		{
			player.landMovementFactor *= 0.75F;
		}	
	}
}