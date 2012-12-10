package dayz.common;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntityChest;
import net.minecraft.src.WeightedRandom;
import net.minecraft.src.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import dayz.DayZ;

public class ChestHookRegistry
{	
    /**
     * Adds the item to the blacksmith's chest.
     * 
     * @param theItemId The item's id. Eg Item.book.shiftedIndex
     * @param theItemDamage The item's metadate/damage.
     * @param theMinimumChanceToGenerateItem The minimum chance to generate item.
     * @param theMaximumChanceToGenerateItem Maximum number of items.
     * @param theItemWorth The items's value.
     */
	public static void addToBlacksmithChest(int theItemId, int theItemDamage, int theMinimumChanceToGenerateItem, int theMaximumChanceToGenerateItem, int theItemWorth)
	{
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(theItemId, theItemDamage, theMinimumChanceToGenerateItem, theMaximumChanceToGenerateItem, theItemWorth));
	}
	
    /**
     * Adds the item to the bonus chest.
     * 
     * @param theItemId The item's id. Eg Item.book.shiftedIndex
     * @param theItemDamage The item's metadate/damage.
     * @param theMinimumChanceToGenerateItem The minimum chance to generate item.
     * @param theMaximumChanceToGenerateItem Maximum number of items.
     * @param theItemWorth The items's value.
     */
	public static void addToBonusChest(int theItemId, int theItemDamage, int theMinimumChanceToGenerateItem, int theMaximumChanceToGenerateItem, int theItemWorth)
	{
        ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(theItemId, theItemDamage, theMinimumChanceToGenerateItem, theMaximumChanceToGenerateItem, theItemWorth));
	}
	
	/*                                     This section can be ignored                                     */
	
    public static final WeightedRandomChestDayZ[] chestAllContents = new WeightedRandomChestDayZ[] 
    {
    	new WeightedRandomChestDayZ(DayZ.ak74u.shiftedIndex, 0, 1, 1, 1),
    	new WeightedRandomChestDayZ(DayZ.remington.shiftedIndex, 0, 1, 1, 1),
    	new WeightedRandomChestDayZ(DayZ.leeenfield.shiftedIndex, 0, 1, 1, 1),
    	new WeightedRandomChestDayZ(DayZ.dbshotgun.shiftedIndex, 0, 1, 1, 1),

    	new WeightedRandomChestDayZ(DayZ.barbedwire.blockID, 0, 1, 1, 3),
    	new WeightedRandomChestDayZ(DayZ.camohelmet.shiftedIndex, 0, 1, 1, 3),
    	new WeightedRandomChestDayZ(DayZ.camochest.shiftedIndex, 0, 1, 1, 3),
    	new WeightedRandomChestDayZ(DayZ.camolegs.shiftedIndex, 0, 1, 1, 3),
    	new WeightedRandomChestDayZ(DayZ.camoboots.shiftedIndex, 0, 1, 1, 3),
    	new WeightedRandomChestDayZ(Block.cake.blockID, 0, 1, 1, 3),
    	new WeightedRandomChestDayZ(Item.bow.shiftedIndex, 0, 1, 1, 3),
    	//new WeightedRandomChestDayZ(DayZ.grenade.shiftedIndex, 0, 1, 1, 3),
    	
    	new WeightedRandomChestDayZ(DayZ.ak74uammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.leeenfieldammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.dbshotgunammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.glock17.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.baseballbatnailed.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.makarov.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.pipe.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.planknailed.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.remingtonammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.map.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.coal.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.ingotIron.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.writableBook.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.arrow.shiftedIndex, 0, 1, 8, 5),
    	new WeightedRandomChestDayZ(Item.bone.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.bloodbag.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Block.tripWireSource.blockID, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.silk.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.boat.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.emerald.shiftedIndex, 0, 1, 3, 5),

    	new WeightedRandomChestDayZ(DayZ.bandage.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestDayZ(DayZ.baseballbat.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestDayZ(DayZ.makarovammo.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestDayZ(DayZ.glock17ammo.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestDayZ(DayZ.plank.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestDayZ(DayZ.whiskeybottlefull.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestDayZ(DayZ.nails.blockID, 0, 1, 1, 7),

    	new WeightedRandomChestDayZ(Item.beefRaw.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(Item.beefCooked.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(Item.porkRaw.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(Item.porkCooked.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(Item.fishRaw.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(Item.fishCooked.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(Item.appleRed.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(Item.bowlSoup.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(Item.melon.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(Item.cookie.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.cannedbeans.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.cannedfish.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.cannedspag.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.cannedpasta.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.heinz.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.chocolate.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.lemonade.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.antibiotics.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.waterbottledirty.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.waterbottleempty.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.whiskeybottleempty.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.waterbottledirty.shiftedIndex, 0, 1, 1, 9)
    };
    
    public static final WeightedRandomChestDayZ[] chestCommonContents = new WeightedRandomChestDayZ[] 
    {
    	new WeightedRandomChestDayZ(DayZ.ak74uammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.leeenfieldammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.dbshotgunammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.glock17.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.baseballbatnailed.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.makarov.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.pipe.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.planknailed.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.remingtonammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.map.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.coal.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.ingotIron.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.writableBook.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.arrow.shiftedIndex, 0, 1, 8, 5),
    	new WeightedRandomChestDayZ(Item.bone.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.bloodbag.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Block.tripWireSource.blockID, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.silk.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.boat.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.emerald.shiftedIndex, 0, 1, 3, 5),

    	new WeightedRandomChestDayZ(DayZ.bandage.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestDayZ(DayZ.baseballbat.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestDayZ(DayZ.makarovammo.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestDayZ(DayZ.glock17ammo.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestDayZ(DayZ.plank.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestDayZ(DayZ.whiskeybottlefull.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestDayZ(DayZ.nails.blockID, 0, 1, 1, 7),

    	new WeightedRandomChestDayZ(Item.beefRaw.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(Item.beefCooked.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(Item.porkRaw.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(Item.porkCooked.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(Item.fishRaw.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(Item.fishCooked.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(Item.appleRed.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(Item.bowlSoup.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(Item.melon.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(Item.cookie.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.cannedbeans.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.cannedfish.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.cannedspag.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.cannedpasta.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.heinz.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.chocolate.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.lemonade.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.antibiotics.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.waterbottledirty.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.waterbottleempty.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.whiskeybottleempty.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestDayZ(DayZ.waterbottledirty.shiftedIndex, 0, 1, 1, 9)
    };
    
    public static final WeightedRandomChestDayZ[] chestRareContents = new WeightedRandomChestDayZ[] 
    {
    	new WeightedRandomChestDayZ(DayZ.ak74u.shiftedIndex, 0, 1, 1, 1),
    	new WeightedRandomChestDayZ(DayZ.remington.shiftedIndex, 0, 1, 1, 1),
    	new WeightedRandomChestDayZ(DayZ.leeenfield.shiftedIndex, 0, 1, 1, 1),
    	new WeightedRandomChestDayZ(DayZ.dbshotgun.shiftedIndex, 0, 1, 1, 1),

    	new WeightedRandomChestDayZ(DayZ.barbedwire.blockID, 0, 1, 1, 3),
    	new WeightedRandomChestDayZ(DayZ.camohelmet.shiftedIndex, 0, 1, 1, 3),
    	new WeightedRandomChestDayZ(DayZ.camochest.shiftedIndex, 0, 1, 1, 3),
    	new WeightedRandomChestDayZ(DayZ.camolegs.shiftedIndex, 0, 1, 1, 3),
    	new WeightedRandomChestDayZ(DayZ.camoboots.shiftedIndex, 0, 1, 1, 3),
    	new WeightedRandomChestDayZ(Block.cake.blockID, 0, 1, 1, 3),
    	new WeightedRandomChestDayZ(Item.bow.shiftedIndex, 0, 1, 1, 3),
    	//new WeightedRandomChestDayZ(DayZ.grenade.shiftedIndex, 0, 1, 1, 3),

    	new WeightedRandomChestDayZ(DayZ.ak74uammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.leeenfieldammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.dbshotgunammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.glock17.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.baseballbatnailed.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.makarov.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.pipe.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.planknailed.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.remingtonammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.map.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.coal.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.ingotIron.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.writableBook.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.arrow.shiftedIndex, 0, 1, 8, 5),
    	new WeightedRandomChestDayZ(Item.bone.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(DayZ.bloodbag.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Block.tripWireSource.blockID, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.silk.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.boat.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestDayZ(Item.emerald.shiftedIndex, 0, 1, 3, 5)
    };
}
