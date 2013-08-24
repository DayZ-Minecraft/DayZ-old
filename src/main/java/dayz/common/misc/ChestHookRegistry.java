package dayz.common.misc;

import java.util.Arrays;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.ComponentVillageHouse2;
import net.minecraftforge.common.ChestGenHooks;

import com.google.common.collect.Sets;

import dayz.DayZ;

public class ChestHookRegistry
{
    protected static WeightedRandomChestDayZ[] loot = new WeightedRandomChestDayZ[] {};

    public static void init()
    {
        for (WeightedRandomChestContent content : ComponentVillageHouse2.villageBlacksmithChestContents)
        {
            ChestGenHooks.removeItem(ChestGenHooks.VILLAGE_BLACKSMITH, content.theItemId);
        }
        
        for (WeightedRandomChestContent content : WorldServer.bonusChestContent)
        {
            ChestGenHooks.removeItem(ChestGenHooks.BONUS_CHEST, content.theItemId);
        }

        registerAllItems();
    }

    public static void addLoot(ItemStack itemStack, int itemWorth)
    {
        Set<WeightedRandomChestDayZ> contents = Sets.newLinkedHashSet(Arrays.asList(loot));
        contents.add(new WeightedRandomChestDayZ(itemStack, 1, 1, itemWorth));
        loot = contents.toArray(new WeightedRandomChestDayZ[0]);

        ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(itemStack, 1, 1, itemWorth));
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(itemStack, 1, 1, itemWorth));
    }

    public static void registerAllItems()
    {
        addLoot(new ItemStack(DayZ.leeenfield), 1);
        addLoot(new ItemStack(DayZ.dbshotgun), 1);
        addLoot(new ItemStack(DayZ.ak74u), 1);
        addLoot(new ItemStack(DayZ.remington), 1);
        addLoot(new ItemStack(DayZ.barbedwire), 3);
        addLoot(new ItemStack(DayZ.camohelmet), 3);
        addLoot(new ItemStack(DayZ.camochest), 3);
        addLoot(new ItemStack(DayZ.camolegs), 3);
        addLoot(new ItemStack(DayZ.camoboots), 3);
        addLoot(new ItemStack(Block.cake), 3);
        addLoot(new ItemStack(Item.bow), 5);
        addLoot(new ItemStack(DayZ.ak74uammo), 5);
        addLoot(new ItemStack(DayZ.leeenfieldammo), 5);
        addLoot(new ItemStack(DayZ.dbshotgunammo), 5);
        addLoot(new ItemStack(DayZ.baseballbatnailed), 5);
        addLoot(new ItemStack(DayZ.makarov), 5);
        addLoot(new ItemStack(DayZ.glock17), 5);
        addLoot(new ItemStack(DayZ.pipe), 5);
        addLoot(new ItemStack(DayZ.planknailed), 5);
        addLoot(new ItemStack(DayZ.crowbar), 5);
        addLoot(new ItemStack(DayZ.machete), 5);
        addLoot(new ItemStack(DayZ.remingtonammo), 5);
        addLoot(new ItemStack(Item.map), 5);
        addLoot(new ItemStack(Item.coal), 5);
        addLoot(new ItemStack(Item.ingotIron), 5);
        addLoot(new ItemStack(Item.writableBook), 5);
        addLoot(new ItemStack(Item.arrow), 5);
        addLoot(new ItemStack(Item.bone), 5);
        addLoot(new ItemStack(DayZ.bloodbag), 5);
        addLoot(new ItemStack(Block.tripWireSource), 5);
        addLoot(new ItemStack(Item.silk), 5);
        addLoot(new ItemStack(Item.boat), 5);
        addLoot(new ItemStack(Item.emerald), 5);
        addLoot(new ItemStack(DayZ.bandage), 7);
        addLoot(new ItemStack(DayZ.baseballbat), 7);
        addLoot(new ItemStack(DayZ.makarovammo), 7);
        addLoot(new ItemStack(DayZ.glock17ammo), 7);
        addLoot(new ItemStack(DayZ.plank), 7);
        addLoot(new ItemStack(DayZ.whiskeybottlefull), 7);
        addLoot(new ItemStack(DayZ.nails), 7);
        addLoot(new ItemStack(Item.beefRaw), 9);
        addLoot(new ItemStack(Item.beefCooked), 9);
        addLoot(new ItemStack(Item.porkRaw), 9);
        addLoot(new ItemStack(Item.porkCooked), 9);
        addLoot(new ItemStack(Item.fishRaw), 9);
        addLoot(new ItemStack(Item.fishCooked), 9);
        addLoot(new ItemStack(Item.appleRed), 9);
        addLoot(new ItemStack(Item.bowlSoup), 9);
        addLoot(new ItemStack(Item.melon), 9);
        addLoot(new ItemStack(Item.cookie), 9);
        addLoot(new ItemStack(Item.bakedPotato), 9);
        addLoot(new ItemStack(DayZ.cannedbeans), 9);
        addLoot(new ItemStack(DayZ.cannedfish), 9);
        addLoot(new ItemStack(DayZ.cannedspag), 9);
        addLoot(new ItemStack(DayZ.cannedpasta), 9);
        addLoot(new ItemStack(DayZ.heinz), 9);
        addLoot(new ItemStack(DayZ.chocolate), 9);
        addLoot(new ItemStack(DayZ.lemonade), 9);
        addLoot(new ItemStack(DayZ.antibiotics), 9);
        addLoot(new ItemStack(DayZ.waterbottledirty), 9);
        addLoot(new ItemStack(DayZ.waterbottleempty), 9);
        addLoot(new ItemStack(DayZ.whiskeybottleempty), 9);
    }
}
