package dayz.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import dayz.DayZ;
import dayz.common.blocks.Blocks;
import dayz.common.effects.Effect;
import dayz.common.items.food.ItemCanEmpty;
import dayz.common.items.food.ItemDrinkBottle;
import dayz.common.items.food.ItemDrinkCanned;
import dayz.common.items.food.ItemFoodCanned;
import dayz.common.items.misc.ItemFirestarter;
import dayz.common.items.misc.ItemHeal;
import dayz.common.items.weapons.ItemAk74u;
import dayz.common.items.weapons.ItemAmmo;
import dayz.common.items.weapons.ItemCamo;
import dayz.common.items.weapons.ItemDbShotgun;
import dayz.common.items.weapons.ItemEnfield;
import dayz.common.items.weapons.ItemGlock17;
import dayz.common.items.weapons.ItemGunAuto;
import dayz.common.items.weapons.ItemGunSemi;
import dayz.common.items.weapons.ItemMakarov;
import dayz.common.items.weapons.ItemMelee;
import dayz.common.items.weapons.ItemRemington;

public class Items
{
    public static Item itemMatches;
    private static int itemMatchesId;
    public static Item healBandage;
    private static int healBandageId;
    public static Item healAntibiotics;
    private static int healAntibioticsId;
    public static Item healBloodbag;
    private static int healBloodbagId;

    public static Item gunAk74u;
    private static int gunAk74uId;
    public static Item gunMakarov;
    private static int gunMakarovId;
    public static Item gunRemington;
    private static int gunRemingtonId;
    public static Item gunLeeEnfield;
    private static int gunLeeEnfieldId;
    public static Item gunGlock17;
    private static int gunGlock17Id;
    public static Item gunDoubleBarreledShotgun;
    private static int gunDoubleBarreledShotgunId;

    public static Item ammoAk74u;
    private static int ammoAk74uId;
    public static Item ammoMakarov;
    private static int ammoMakarovId;
    public static Item ammoRemington;
    private static int ammoRemingtonId;
    public static Item ammoLeeEnfield;
    private static int ammoLeeEnfieldId;
    public static Item ammoGlock17;
    private static int ammoGlock17Id;
    public static Item ammoDoubleBarreledShotgun;
    private static int ammoDoubleBarreledShotgunId;

    public static Item camoHelmet;
    private static int camoHelmetId;
    public static Item camoChest;
    private static int camoChestId;
    public static Item camoLegs;
    private static int camoLegsId;
    public static Item camoBoots;
    private static int camoBootsId;

    public static Item foodCanned;
    private static int foodCannedId;
    public static Item foodCanEmpty;
    private static int foodCanEmptyId;
    public static Item drinkCanEmpty;
    private static int drinkCanEmptyId;
    public static Item drinkCanned;
    private static int drinkCannedId;

    public static Item drinkWhiskeyBottle;
    private static int drinkWhiskeyBottleId;
    public static Item drinkCiderBottle;
    private static int drinkCiderBottleId;
    public static Item drinkVodkaBottle;
    private static int drinkVodkaBottleId;

    public static Item meleeBaseballBat;
    private static int meleeBaseballBatId;
    public static Item meleeBaseballBatNailed;
    private static int meleeBaseballBatNailedId;
    public static Item meleePlank;
    private static int meleePlankId;
    public static Item meleePlankNailed;
    private static int meleePlankNailedId;
    public static Item meleePipe;
    private static int meleePipeId;
    public static Item meleeCrowbar;
    private static int meleeCrowbarId;
    public static Item meleeMachete;
    private static int meleeMacheteId;

    public static void loadItems()
    {
        gunAk74u = new ItemGunAuto(gunAk74uId, new ItemAk74u()).setUnlocalizedName("gunAk74u");
        LanguageRegistry.addName(gunAk74u, "Ak74u");
        gunMakarov = new ItemGunSemi(gunMakarovId, new ItemMakarov()).setUnlocalizedName("gunMakarov");
        LanguageRegistry.addName(gunMakarov, "Makarov");
        gunRemington = new ItemGunSemi(gunRemingtonId, new ItemRemington()).setUnlocalizedName("gunRemington");
        LanguageRegistry.addName(gunRemington, "Remington");
        gunLeeEnfield = new ItemGunSemi(gunLeeEnfieldId, new ItemEnfield()).setUnlocalizedName("gunLeeEnfield");
        LanguageRegistry.addName(gunLeeEnfield, "Lee Enfield");
        gunGlock17 = new ItemGunSemi(gunGlock17Id, new ItemGlock17()).setUnlocalizedName("gunGlock17");
        LanguageRegistry.addName(gunGlock17, "Glock 17");
        gunDoubleBarreledShotgun = new ItemGunSemi(gunDoubleBarreledShotgunId, new ItemDbShotgun()).setUnlocalizedName("gunDoubleBarreledShotgun");
        LanguageRegistry.addName(gunDoubleBarreledShotgun, "Double-barreled Shotgun");

        ammoAk74u = new ItemAmmo(ammoAk74uId).setUnlocalizedName("ammoAk74u");
        LanguageRegistry.addName(ammoAk74u, "Ak74u Magazine");
        ammoMakarov = new ItemAmmo(ammoMakarovId).setUnlocalizedName("ammoMakarov");
        LanguageRegistry.addName(ammoMakarov, "Makarov Magazine");
        ammoRemington = new ItemAmmo(ammoRemingtonId).setUnlocalizedName("ammoRemington");
        LanguageRegistry.addName(ammoRemington, "Remington Shells");
        ammoLeeEnfield = new ItemAmmo(ammoLeeEnfieldId).setUnlocalizedName("ammoLeeEnfield");
        LanguageRegistry.addName(ammoLeeEnfield, "Lee Enfield Magazine");
        ammoGlock17 = new ItemAmmo(ammoGlock17Id).setUnlocalizedName("ammoGlock17");
        LanguageRegistry.addName(ammoGlock17, "Glock 17 Magazine");
        ammoDoubleBarreledShotgun = new ItemAmmo(ammoDoubleBarreledShotgunId).setUnlocalizedName("ammoDoubleBarreledShotgun");
        LanguageRegistry.addName(ammoDoubleBarreledShotgun, "Double-barreled Shotgun Shells");

        itemMatches = (new ItemFirestarter(itemMatchesId, 8)).setUnlocalizedName("itemMatches");
        LanguageRegistry.addName(itemMatches, "Book of Matches");

        healBandage = new ItemHeal(healBandageId, 0, Effect.bleeding).subNames("Stops bleeding").setUnlocalizedName("healBandage");
        LanguageRegistry.addName(healBandage, "Bandage");
        healAntibiotics = new ItemHeal(healAntibioticsId, 0, Effect.zombification).subNames("Stops infection").setUnlocalizedName("healAntibiotics");
        LanguageRegistry.addName(healAntibiotics, "Antibiotics");
        healBloodbag = new ItemMod(healBloodbagId).subNames("Cannot be self-applied").setUnlocalizedName("healBloodbag");
        LanguageRegistry.addName(healBloodbag, "Bloodbag");

        camoHelmet = (new ItemCamo(camoHelmetId, DayZ.enumArmorMaterialCamo, 4, 0)).setUnlocalizedName("camoHelmet");
        LanguageRegistry.addName(camoHelmet, "Camouflage Helmet");
        camoChest = (new ItemCamo(camoChestId, DayZ.enumArmorMaterialCamo, 4, 1)).setUnlocalizedName("camoChest");
        LanguageRegistry.addName(camoChest, "Camouflage Shirt");
        camoLegs = (new ItemCamo(camoLegsId, DayZ.enumArmorMaterialCamo, 4, 2)).setUnlocalizedName("camoLegs");
        LanguageRegistry.addName(camoLegs, "Camouflage Pants");
        camoBoots = (new ItemCamo(camoBootsId, DayZ.enumArmorMaterialCamo, 4, 3)).setUnlocalizedName("camoBoots");
        LanguageRegistry.addName(camoBoots, "Camouflage Boots");

        foodCanEmpty = new ItemCanEmpty(foodCanEmptyId).setUnlocalizedName("foodCanEmpty");
        LanguageRegistry.addName(foodCanEmpty, "Empty Can");
        foodCanned = new ItemFoodCanned(foodCannedId, 4).setUnlocalizedName("foodCanned");
        LanguageRegistry.addName(foodCanned, "Canned Food");

        drinkCanEmpty = new ItemCanEmpty(drinkCanEmptyId).setUnlocalizedName("drinkCanEmpty");
        LanguageRegistry.addName(drinkCanEmpty, "Empty Can");
        drinkCanned = new ItemDrinkCanned(drinkCannedId, 4).setUnlocalizedName("drinkCanned");
        LanguageRegistry.addName(drinkCanned, "Canned Drink");

        drinkWhiskeyBottle = new ItemDrinkBottle(drinkWhiskeyBottleId, 4).isAlcohol(true).setUnlocalizedName("drinkWhiskeyBottle");
        LanguageRegistry.addName(drinkWhiskeyBottle, "Bottle of Whiskey");
        drinkCiderBottle = new ItemDrinkBottle(drinkCiderBottleId, 4).setUnlocalizedName("drinkCiderBottle");
        LanguageRegistry.addName(drinkCiderBottle, "Bottle of Cider");
        drinkVodkaBottle = new ItemDrinkBottle(drinkVodkaBottleId, 4).isAlcohol(true).setUnlocalizedName("drinkVodkaBottle");
        LanguageRegistry.addName(drinkVodkaBottle, "Bottle of Vodka");

        meleeBaseballBat = new ItemMelee(meleeBaseballBatId, 6).setUnlocalizedName("meleeBaseballBat");
        LanguageRegistry.addName(meleeBaseballBat, "Baseball Bat");
        meleeBaseballBatNailed = new ItemMelee(meleeBaseballBatNailedId, 8).setUnlocalizedName("meleeBaseballBatNailed");
        LanguageRegistry.addName(meleeBaseballBatNailed, "Nailed Baseball Bat");
        meleePlank = new ItemMelee(meleePlankId, 7).setUnlocalizedName("meleePlank");
        LanguageRegistry.addName(meleePlank, "Plank");
        meleePlankNailed = new ItemMelee(meleePlankNailedId, 8).setUnlocalizedName("meleePlankNailed");
        LanguageRegistry.addName(meleePlankNailed, "Nailed Plank");
        meleePipe = new ItemMelee(meleePipeId, 8).setUnlocalizedName("meleePipe");
        LanguageRegistry.addName(meleePipe, "Steel Pipe");
        meleeCrowbar = new ItemMelee(meleeCrowbarId, 8).setUnlocalizedName("meleeCrowbar");
        LanguageRegistry.addName(meleeCrowbar, "Crowbar");
        meleeMachete = new ItemMelee(meleeMacheteId, 7).setUnlocalizedName("meleeMachete");
        LanguageRegistry.addName(meleeMachete, "Machete");

        GameRegistry.addShapelessRecipe(new ItemStack(meleeBaseballBatNailed, 1), new Object[]
        { new ItemStack(meleeBaseballBat, 1), new ItemStack(Blocks.nailBlock, 1, 0) });

        GameRegistry.addShapelessRecipe(new ItemStack(meleePlankNailed, 1), new Object[]
        { new ItemStack(meleePlank, 1), new ItemStack(Blocks.nailBlock, 1, 0) });

        GameRegistry.addRecipe(new ItemStack(meleePlank, 1), new Object[]
        { "#", "#", "#", Character.valueOf('#'), Block.planks });

        GameRegistry.addRecipe(new ItemStack(meleeBaseballBat, 1), new Object[]
        { "##!", Character.valueOf('#'), Block.planks, Character.valueOf('!'), Item.stick });
    }

    public void itemConfig(Configuration config)
    {
        itemMatchesId = config.get(Configuration.CATEGORY_ITEM, "itemMatchesId", 7100).getInt();
        healBandageId = config.get(Configuration.CATEGORY_ITEM, "healBandageId", 7101).getInt();
        healAntibioticsId = config.get(Configuration.CATEGORY_ITEM, "healAntibioticsId", 7102).getInt();
        healBloodbagId = config.get(Configuration.CATEGORY_ITEM, "healBloodbagId", 7103).getInt();

        gunAk74uId = config.get(Configuration.CATEGORY_ITEM, "gunAk74uId", 7104).getInt();
        gunMakarovId = config.get(Configuration.CATEGORY_ITEM, "gunMakarovId", 7105).getInt();
        gunRemingtonId = config.get(Configuration.CATEGORY_ITEM, "gunRemingtonId", 7106).getInt();
        gunLeeEnfieldId = config.get(Configuration.CATEGORY_ITEM, "gunLeeEnfieldId", 7107).getInt();
        gunGlock17Id = config.get(Configuration.CATEGORY_ITEM, "gunGlock17Id", 7108).getInt();
        gunDoubleBarreledShotgunId = config.get(Configuration.CATEGORY_ITEM, "gunDoubleBarreledShotgunId", 7109).getInt();

        ammoAk74uId = config.get(Configuration.CATEGORY_ITEM, "ammoAk74uId", 7110).getInt();
        ammoMakarovId = config.get(Configuration.CATEGORY_ITEM, "ammoMakarovId", 7111).getInt();
        ammoRemingtonId = config.get(Configuration.CATEGORY_ITEM, "ammoRemingtonId", 7112).getInt();
        ammoLeeEnfieldId = config.get(Configuration.CATEGORY_ITEM, "ammoLeeEnfieldId", 7113).getInt();
        ammoGlock17Id = config.get(Configuration.CATEGORY_ITEM, "ammoGlock17Id", 7114).getInt();
        ammoDoubleBarreledShotgunId = config.get(Configuration.CATEGORY_ITEM, "ammoDoubleBarreledShotgunId", 7115).getInt();

        camoHelmetId = config.get(Configuration.CATEGORY_ITEM, "camoHelmetId", 7116).getInt();
        camoChestId = config.get(Configuration.CATEGORY_ITEM, "camoChestId", 7117).getInt();
        camoLegsId = config.get(Configuration.CATEGORY_ITEM, "camoLegsId", 7118).getInt();
        camoBootsId = config.get(Configuration.CATEGORY_ITEM, "camoBootsId", 7119).getInt();

        foodCannedId = config.get(Configuration.CATEGORY_ITEM, "foodCannedId", 7120).getInt();
        foodCanEmptyId = config.get(Configuration.CATEGORY_ITEM, "foodCanEmptyId", 7121).getInt();
        drinkCanEmptyId = config.get(Configuration.CATEGORY_ITEM, "drinkCanEmptyId", 7122).getInt();
        drinkCannedId = config.get(Configuration.CATEGORY_ITEM, "drinkCannedId", 7123).getInt();

        drinkWhiskeyBottleId = config.get(Configuration.CATEGORY_ITEM, "drinkWhiskeyBottleId", 7124).getInt();
        drinkCiderBottleId = config.get(Configuration.CATEGORY_ITEM, "drinkCiderBottleId", 7125).getInt();
        drinkVodkaBottleId = config.get(Configuration.CATEGORY_ITEM, "drinkVodkaBottleId", 7126).getInt();

        meleeBaseballBatId = config.get(Configuration.CATEGORY_ITEM, "meleeBaseballBatId", 7127).getInt();
        meleeBaseballBatNailedId = config.get(Configuration.CATEGORY_ITEM, "meleeBaseballBatNailedId", 7128).getInt();
        meleePlankId = config.get(Configuration.CATEGORY_ITEM, "meleePlankId", 7129).getInt();
        meleePlankNailedId = config.get(Configuration.CATEGORY_ITEM, "meleePlankNailedId", 7130).getInt();
        meleePipeId = config.get(Configuration.CATEGORY_ITEM, "meleePipeId", 7131).getInt();
        meleeCrowbarId = config.get(Configuration.CATEGORY_ITEM, "meleeCrowbarId", 7132).getInt();
        meleeMacheteId = config.get(Configuration.CATEGORY_ITEM, "meleeMacheteId", 7133).getInt();
    }
}
