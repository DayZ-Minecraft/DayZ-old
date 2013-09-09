package dayz.common.misc;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.ComponentVillageHouse2;
import net.minecraftforge.common.ChestGenHooks;
import dayz.common.blocks.Blocks;
import dayz.common.items.Items;

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
        ArrayList<WeightedRandomChestContent> contents = new ArrayList<WeightedRandomChestContent>(Arrays.asList(loot));
        contents.add(new WeightedRandomChestContent(itemStack, 1, 1, itemWorth));
        loot = contents.toArray(new WeightedRandomChestContent[0]);

        ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(itemStack, 1, 1, itemWorth));
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(itemStack, 1, 1, itemWorth));
    }

    public static void registerAllItems()
    {
        addLoot(new ItemStack(Items.gunLeeEnfield), 1);
        addLoot(new ItemStack(Items.gunDoubleBarreledShotgun), 1);
        addLoot(new ItemStack(Items.gunAk74u), 1);
        addLoot(new ItemStack(Items.gunRemington), 1);
        addLoot(new ItemStack(Blocks.barbedWire), 3);
        addLoot(new ItemStack(Items.camoHelmet), 3);
        addLoot(new ItemStack(Items.camoChest), 3);
        addLoot(new ItemStack(Items.camoLegs), 3);
        addLoot(new ItemStack(Items.camoBoots), 3);
        addLoot(new ItemStack(Block.cake), 3);
        addLoot(new ItemStack(Item.bow), 5);
        addLoot(new ItemStack(Items.ammoAk74u), 5);
        addLoot(new ItemStack(Items.ammoLeeEnfield), 5);
        addLoot(new ItemStack(Items.ammoDoubleBarreledShotgun), 5);
        addLoot(new ItemStack(Items.meleeBaseballBatNailed), 5);
        addLoot(new ItemStack(Items.gunMakarov), 5);
        addLoot(new ItemStack(Items.gunGlock17), 5);
        addLoot(new ItemStack(Items.meleePipe), 5);
        addLoot(new ItemStack(Items.meleePlankNailed), 5);
        addLoot(new ItemStack(Items.meleeCrowbar), 5);
        addLoot(new ItemStack(Items.meleeMachete), 5);
        addLoot(new ItemStack(Items.gunRemington), 5);
        addLoot(new ItemStack(Item.map), 5);
        addLoot(new ItemStack(Item.coal), 5);
        addLoot(new ItemStack(Item.ingotIron), 5);
        addLoot(new ItemStack(Item.writableBook), 5);
        addLoot(new ItemStack(Item.arrow), 5);
        addLoot(new ItemStack(Item.bone), 5);
        addLoot(new ItemStack(Items.healBloodbag), 5);
        addLoot(new ItemStack(Block.tripWireSource), 5);
        addLoot(new ItemStack(Item.silk), 5);
        addLoot(new ItemStack(Item.boat), 5);
        addLoot(new ItemStack(Item.emerald), 5);
        addLoot(new ItemStack(Items.healBandage), 7);
        addLoot(new ItemStack(Items.meleeBaseballBat), 7);
        addLoot(new ItemStack(Items.gunMakarov), 7);
        addLoot(new ItemStack(Items.gunGlock17), 7);
        addLoot(new ItemStack(Items.meleePlank), 7);
        addLoot(new ItemStack(Items.drinkWhiskeyBottle), 7);
        addLoot(new ItemStack(Items.drinkCiderBottle), 7);
        addLoot(new ItemStack(Items.drinkVodkaBottle), 7);
        addLoot(new ItemStack(Blocks.nailBlock), 7);
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
        addLoot(new ItemStack(Items.healAntibiotics), 9);

        for (int i = 0; i < 7; i++)
        {
            addLoot(new ItemStack(Items.foodCanned, 1, i), 9);
            addLoot(new ItemStack(Items.drinkCanned, 1, i), 9);
            addLoot(new ItemStack(Items.drinkCanEmpty, 1, i), 11);
            addLoot(new ItemStack(Items.foodCanEmpty, 1, i), 11);
        }
    }
}
