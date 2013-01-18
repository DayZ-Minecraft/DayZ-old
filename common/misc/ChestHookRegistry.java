package dayz.common.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.ComponentVillageHouse2;
import net.minecraftforge.common.ChestGenHooks;

import com.google.common.collect.Sets;

import dayz.DayZ;
import dayz.common.items.ItemDayZ;

public class ChestHookRegistry
{	
    protected static WeightedRandomChestDayZ[] commonLoot = new WeightedRandomChestDayZ[] {};
    protected static WeightedRandomChestDayZ[] rareLoot = new WeightedRandomChestDayZ[] {};
    protected static WeightedRandomChestDayZ[] allLoot = new WeightedRandomChestDayZ[] {};
    
    public static void init()
    {    	
        ChestGenHooks.removeItem(ChestGenHooks.BONUS_CHEST, new ItemStack(Item.stick));
        ChestGenHooks.removeItem(ChestGenHooks.BONUS_CHEST, new ItemStack(Block.planks));
        ChestGenHooks.removeItem(ChestGenHooks.BONUS_CHEST, new ItemStack(Block.wood));
        ChestGenHooks.removeItem(ChestGenHooks.BONUS_CHEST, new ItemStack(Item.axeStone));
        ChestGenHooks.removeItem(ChestGenHooks.BONUS_CHEST, new ItemStack(Item.axeWood));
        ChestGenHooks.removeItem(ChestGenHooks.BONUS_CHEST, new ItemStack(Item.pickaxeStone));
        ChestGenHooks.removeItem(ChestGenHooks.BONUS_CHEST, new ItemStack(Item.pickaxeWood));
        ChestGenHooks.removeItem(ChestGenHooks.BONUS_CHEST, new ItemStack(Item.appleRed));
        ChestGenHooks.removeItem(ChestGenHooks.BONUS_CHEST, new ItemStack(Item.bread));
        
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
        
        registerAllItems();
    }
    
    public static void postInit()
    {    	
        Set<WeightedRandomChestDayZ> allChestLoot = Sets.newLinkedHashSet(Arrays.asList(allLoot));
        Set<WeightedRandomChestDayZ> commonChestLoot = Sets.newLinkedHashSet(Arrays.asList(commonLoot));
        Set<WeightedRandomChestDayZ> rareChestLoot = Sets.newLinkedHashSet(Arrays.asList(rareLoot));

        allChestLoot.addAll(commonChestLoot);
        allChestLoot.addAll(rareChestLoot);

        allLoot = allChestLoot.toArray(new WeightedRandomChestDayZ[0]);
    }
    
	public static void addCommon(ItemStack itemStack, int itemWorth)
	{
        Set<WeightedRandomChestDayZ> contents = Sets.newLinkedHashSet(Arrays.asList(commonLoot));
        contents.add(new WeightedRandomChestDayZ(itemStack, 1, 1, itemWorth));
        commonLoot = contents.toArray(new WeightedRandomChestDayZ[0]);
        
        ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(itemStack, 1, 1, itemWorth));
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(itemStack, 1, 1, itemWorth));
	}
	
	public static void addRare(ItemStack itemStack, int itemWorth) 
	{
        Set<WeightedRandomChestDayZ> contents = Sets.newLinkedHashSet(Arrays.asList(rareLoot));
        contents.add(new WeightedRandomChestDayZ(itemStack, 1, 1, itemWorth));
        rareLoot = contents.toArray(new WeightedRandomChestDayZ[0]);
        
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(itemStack, 1, 1, itemWorth));
	}
	
	public static void registerAllItems()
	{
		addRare(new ItemStack(DayZ.leeenfield), 1);
		addRare(new ItemStack(DayZ.dbshotgun), 1);
		addRare(new ItemStack(DayZ.ak74u), 1);
		addRare(new ItemStack(DayZ.remington), 1);
		addRare(new ItemStack(DayZ.barbedwire), 3);
		addRare(new ItemStack(DayZ.camohelmet), 3);
		addRare(new ItemStack(DayZ.camochest), 3);
		addRare(new ItemStack(DayZ.camolegs), 3);
		addRare(new ItemStack(DayZ.camoboots), 3);
		addRare(new ItemStack(Block.cake), 3);

		addCommon(new ItemStack(Item.bow), 5);
		addCommon(new ItemStack(DayZ.ak74uammo), 5);
		addCommon(new ItemStack(DayZ.leeenfieldammo), 5);
		addCommon(new ItemStack(DayZ.dbshotgunammo), 5);
		addCommon(new ItemStack(DayZ.baseballbatnailed), 5);
		addCommon(new ItemStack(DayZ.makarov), 5);
		addCommon(new ItemStack(DayZ.glock17), 5);
		addCommon(new ItemStack(DayZ.pipe), 5);
		addCommon(new ItemStack(DayZ.planknailed), 5);
		addCommon(new ItemStack(DayZ.crowbar), 5);
		addCommon(new ItemStack(DayZ.machete), 5);
		addCommon(new ItemStack(DayZ.remingtonammo), 5);
		addCommon(new ItemStack(Item.map), 5);
		addCommon(new ItemStack(Item.coal), 5);
		addCommon(new ItemStack(Item.ingotIron), 5);
		addCommon(new ItemStack(Item.writableBook), 5);
		addCommon(new ItemStack(Item.arrow), 5);
		addCommon(new ItemStack(Item.bone), 5);
		addCommon(new ItemStack(DayZ.bloodbag), 5);
		addCommon(new ItemStack(Block.tripWireSource), 5);
		addCommon(new ItemStack(Item.silk), 5);
		addCommon(new ItemStack(Item.boat), 5);
		addCommon(new ItemStack(Item.emerald), 5);
		addCommon(new ItemStack(DayZ.bandage), 7);
		addCommon(new ItemStack(DayZ.baseballbat), 7);
		addCommon(new ItemStack(DayZ.makarovammo), 7);
		addCommon(new ItemStack(DayZ.glock17ammo), 7);
		addCommon(new ItemStack(DayZ.plank), 7);
		addCommon(new ItemStack(DayZ.whiskeybottlefull), 7);
		addCommon(new ItemStack(DayZ.nails), 7);
		addCommon(new ItemStack(Item.beefRaw), 9);
		addCommon(new ItemStack(Item.beefCooked), 9);
		addCommon(new ItemStack(Item.porkRaw), 9);
		addCommon(new ItemStack(Item.porkCooked), 9);
		addCommon(new ItemStack(Item.fishRaw), 9);
		addCommon(new ItemStack(Item.fishCooked), 9);
		addCommon(new ItemStack(Item.appleRed), 9);
		addCommon(new ItemStack(Item.bowlSoup), 9);
		addCommon(new ItemStack(Item.melon), 9);
		addCommon(new ItemStack(Item.cookie), 9);
		addCommon(new ItemStack(Item.bakedPotato), 9);
		addCommon(new ItemStack(DayZ.cannedbeans), 9);
		addCommon(new ItemStack(DayZ.cannedfish), 9);
		addCommon(new ItemStack(DayZ.cannedspag), 9);
		addCommon(new ItemStack(DayZ.cannedpasta), 9);
		addCommon(new ItemStack(DayZ.heinz), 9);
		addCommon(new ItemStack(DayZ.chocolate), 9);
		addCommon(new ItemStack(DayZ.lemonade), 9);
		addCommon(new ItemStack(DayZ.antibiotics), 9);
		addCommon(new ItemStack(DayZ.waterbottledirty), 9);
		addCommon(new ItemStack(DayZ.waterbottleempty), 9);
		addCommon(new ItemStack(DayZ.whiskeybottleempty), 9);
	}
}
