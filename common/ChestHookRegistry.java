package dayz.common;

import java.util.Random;

import dayz.DayZ;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.TileEntityChest;
import net.minecraft.src.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

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
	
    public static final WeightedRandomChestContent[] chestAllContents = new WeightedRandomChestContent[] 
    {
    	new WeightedRandomChestContent(DayZ.ak74u.shiftedIndex, 0, 1, 1, 1),
    	new WeightedRandomChestContent(DayZ.remington.shiftedIndex, 0, 1, 1, 1),
    	new WeightedRandomChestContent(DayZ.leeenfield.shiftedIndex, 0, 1, 1, 1),
    	new WeightedRandomChestContent(DayZ.dbshotgun.shiftedIndex, 0, 1, 1, 1),

    	new WeightedRandomChestContent(DayZ.barbedwire.blockID, 0, 1, 1, 3),
    	new WeightedRandomChestContent(DayZ.camohelmet.shiftedIndex, 0, 1, 1, 3),
    	new WeightedRandomChestContent(DayZ.camochest.shiftedIndex, 0, 1, 1, 3),
    	new WeightedRandomChestContent(DayZ.camolegs.shiftedIndex, 0, 1, 1, 3),
    	new WeightedRandomChestContent(DayZ.camoboots.shiftedIndex, 0, 1, 1, 3),
    	new WeightedRandomChestContent(Block.cake.blockID, 0, 1, 1, 3),
    	new WeightedRandomChestContent(Item.bow.shiftedIndex, 0, 1, 1, 3),
    	//new WeightedRandomChestContent(DayZ.grenade.shiftedIndex, 0, 1, 1, 3),
    	
    	new WeightedRandomChestContent(DayZ.ak74uammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.leeenfieldammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.dbshotgunammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.glock17.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.bandage.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.baseballbatnailed.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.makarov.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.pipe.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.planknailed.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.remingtonammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.map.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.coal.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.ingotIron.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.writableBook.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.arrow.shiftedIndex, 0, 1, 8, 5),
    	new WeightedRandomChestContent(Item.bone.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.bloodbag.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Block.tripWireSource.blockID, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.silk.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.boat.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.emerald.shiftedIndex, 0, 1, 3, 5),

    	new WeightedRandomChestContent(DayZ.baseballbat.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestContent(DayZ.makarovammo.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestContent(DayZ.glock17ammo.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestContent(DayZ.plank.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestContent(DayZ.whiskeybottlefull.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestContent(DayZ.nails.blockID, 0, 1, 1, 7),

    	new WeightedRandomChestContent(Item.beefRaw.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(Item.beefCooked.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(Item.porkRaw.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(Item.porkCooked.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(Item.fishRaw.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(Item.fishCooked.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(Item.appleRed.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(Item.bowlSoup.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(Item.melon.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(Item.cookie.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.cannedbeans.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.cannedfish.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.cannedspag.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.cannedpasta.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.heinz.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.chocolate.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.lemonade.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.antibiotics.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.waterbottledirty.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.waterbottleempty.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.whiskeybottleempty.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.waterbottledirty.shiftedIndex, 0, 1, 1, 9)
    };
    
    public static final WeightedRandomChestContent[] chestCommonContents = new WeightedRandomChestContent[] 
    {
    	new WeightedRandomChestContent(DayZ.ak74uammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.leeenfieldammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.dbshotgunammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.glock17.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.bandage.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.baseballbatnailed.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.makarov.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.pipe.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.planknailed.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.remingtonammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.map.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.coal.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.ingotIron.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.writableBook.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.arrow.shiftedIndex, 0, 1, 8, 5),
    	new WeightedRandomChestContent(Item.bone.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.bloodbag.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Block.tripWireSource.blockID, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.silk.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.boat.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.emerald.shiftedIndex, 0, 1, 3, 5),

    	new WeightedRandomChestContent(DayZ.baseballbat.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestContent(DayZ.makarovammo.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestContent(DayZ.glock17ammo.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestContent(DayZ.plank.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestContent(DayZ.whiskeybottlefull.shiftedIndex, 0, 1, 1, 7),
    	new WeightedRandomChestContent(DayZ.nails.blockID, 0, 1, 1, 7),

    	new WeightedRandomChestContent(Item.beefRaw.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(Item.beefCooked.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(Item.porkRaw.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(Item.porkCooked.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(Item.fishRaw.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(Item.fishCooked.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(Item.appleRed.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(Item.bowlSoup.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(Item.melon.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(Item.cookie.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.cannedbeans.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.cannedfish.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.cannedspag.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.cannedpasta.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.heinz.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.chocolate.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.lemonade.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.antibiotics.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.waterbottledirty.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.waterbottleempty.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.whiskeybottleempty.shiftedIndex, 0, 1, 1, 9),
    	new WeightedRandomChestContent(DayZ.waterbottledirty.shiftedIndex, 0, 1, 1, 9)
    };
    
    public static final WeightedRandomChestContent[] chestRareContents = new WeightedRandomChestContent[] 
    {
    	new WeightedRandomChestContent(DayZ.ak74u.shiftedIndex, 0, 1, 1, 1),
    	new WeightedRandomChestContent(DayZ.remington.shiftedIndex, 0, 1, 1, 1),
    	new WeightedRandomChestContent(DayZ.leeenfield.shiftedIndex, 0, 1, 1, 1),
    	new WeightedRandomChestContent(DayZ.dbshotgun.shiftedIndex, 0, 1, 1, 1),

    	new WeightedRandomChestContent(DayZ.barbedwire.blockID, 0, 1, 1, 3),
    	new WeightedRandomChestContent(DayZ.camohelmet.shiftedIndex, 0, 1, 1, 3),
    	new WeightedRandomChestContent(DayZ.camochest.shiftedIndex, 0, 1, 1, 3),
    	new WeightedRandomChestContent(DayZ.camolegs.shiftedIndex, 0, 1, 1, 3),
    	new WeightedRandomChestContent(DayZ.camoboots.shiftedIndex, 0, 1, 1, 3),
    	new WeightedRandomChestContent(Block.cake.blockID, 0, 1, 1, 3),
    	new WeightedRandomChestContent(Item.bow.shiftedIndex, 0, 1, 1, 3),
    	//new WeightedRandomChestContent(DayZ.grenade.shiftedIndex, 0, 1, 1, 3),

    	new WeightedRandomChestContent(DayZ.ak74uammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.leeenfieldammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.dbshotgunammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.glock17.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.bandage.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.baseballbatnailed.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.makarov.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.pipe.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.planknailed.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.remingtonammo.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.map.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.coal.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.ingotIron.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.writableBook.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.arrow.shiftedIndex, 0, 1, 8, 5),
    	new WeightedRandomChestContent(Item.bone.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(DayZ.bloodbag.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Block.tripWireSource.blockID, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.silk.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.boat.shiftedIndex, 0, 1, 1, 5),
    	new WeightedRandomChestContent(Item.emerald.shiftedIndex, 0, 1, 3, 5)
    };
}
