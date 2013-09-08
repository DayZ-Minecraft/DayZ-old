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

public class LootManager
{
    public static WeightedRandomChestContent[] loot = new WeightedRandomChestContent[] {};

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
        Set<WeightedRandomChestContent> contents = Sets.newLinkedHashSet(Arrays.asList(loot));
        contents.add(new WeightedRandomChestContent(itemStack, 1, 1, itemWorth));
        loot = contents.toArray(new WeightedRandomChestContent[0]);

        ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(itemStack, 1, 1, itemWorth));
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(itemStack, 1, 1, itemWorth));
    }

    public static void registerAllItems()
    {
        addLoot(new ItemStack(DayZ.item().gunLeeEnfield), 1);
        addLoot(new ItemStack(DayZ.item().gunDoubleBarreledShotgun), 1);
        addLoot(new ItemStack(DayZ.item().gunAk74u), 1);
        addLoot(new ItemStack(DayZ.item().gunRemington), 1);
        addLoot(new ItemStack(DayZ.block().barbedWire), 3);
        addLoot(new ItemStack(DayZ.item().camoHelmet), 3);
        addLoot(new ItemStack(DayZ.item().camoChest), 3);
        addLoot(new ItemStack(DayZ.item().camoLegs), 3);
        addLoot(new ItemStack(DayZ.item().camoBoots), 3);
        addLoot(new ItemStack(Block.cake), 3);
        addLoot(new ItemStack(Item.bow), 5);
        addLoot(new ItemStack(DayZ.item().ammoAk74u), 5);
        addLoot(new ItemStack(DayZ.item().ammoLeeEnfield), 5);
        addLoot(new ItemStack(DayZ.item().ammoDoubleBarreledShotgun), 5);
        addLoot(new ItemStack(DayZ.item().meleeBaseballBatNailed), 5);
        addLoot(new ItemStack(DayZ.item().gunMakarov), 5);
        addLoot(new ItemStack(DayZ.item().gunGlock17), 5);
        addLoot(new ItemStack(DayZ.item().meleePipe), 5);
        addLoot(new ItemStack(DayZ.item().meleePlankNailed), 5);
        addLoot(new ItemStack(DayZ.item().meleeCrowbar), 5);
        addLoot(new ItemStack(DayZ.item().meleeMachete), 5);
        addLoot(new ItemStack(DayZ.item().gunRemington), 5);
        addLoot(new ItemStack(Item.map), 5);
        addLoot(new ItemStack(Item.coal), 5);
        addLoot(new ItemStack(Item.ingotIron), 5);
        addLoot(new ItemStack(Item.writableBook), 5);
        addLoot(new ItemStack(Item.arrow), 5);
        addLoot(new ItemStack(Item.bone), 5);
        addLoot(new ItemStack(DayZ.item().healBloodbag), 5);
        addLoot(new ItemStack(Block.tripWireSource), 5);
        addLoot(new ItemStack(Item.silk), 5);
        addLoot(new ItemStack(Item.boat), 5);
        addLoot(new ItemStack(Item.emerald), 5);
        addLoot(new ItemStack(DayZ.item().healBandage), 7);
        addLoot(new ItemStack(DayZ.item().meleeBaseballBat), 7);
        addLoot(new ItemStack(DayZ.item().gunMakarov), 7);
        addLoot(new ItemStack(DayZ.item().gunGlock17), 7);
        addLoot(new ItemStack(DayZ.item().meleePlank), 7);
        addLoot(new ItemStack(DayZ.item().drinkWhiskeyBottle), 7);
        addLoot(new ItemStack(DayZ.item().drinkCiderBottle), 7);
        addLoot(new ItemStack(DayZ.item().drinkVodkaBottle), 7);
        addLoot(new ItemStack(DayZ.block().nailBlock), 7);
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
        addLoot(new ItemStack(DayZ.item().foodCanned, 1, DayZ.random.nextInt(6)), 9);
        addLoot(new ItemStack(DayZ.item().drinkCanned, 1, DayZ.random.nextInt(6)), 9);
        addLoot(new ItemStack(DayZ.item().healAntibiotics), 9);
        addLoot(new ItemStack(DayZ.item().drinkCanEmpty, 1, DayZ.random.nextInt(6)), 11);
        addLoot(new ItemStack(DayZ.item().foodCanEmpty, 1, DayZ.random.nextInt(6)), 11);
    }
}
