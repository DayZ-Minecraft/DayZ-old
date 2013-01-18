package dayz.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import dayz.common.playerdata.PlayerStats;

public class ItemDayzDrink extends ItemFood
{
    private final float saturationModifier;
    private final int thirstReplenish;

    /**
     * Day Z Drink Item
     * @param itemID The ID of the item
     * @param saturationmodifier The hunger bar saturation to be restored
     * @param drinkEffect Potion effect of the drink
     */
    public ItemDayzDrink(int itemID, int thirstreplenish, float saturationmodifier)
    {
        super(itemID, 0, saturationmodifier, false);
        this.saturationModifier = saturationmodifier;
        this.maxStackSize = 1;
        this.thirstReplenish = thirstreplenish;
    }

    @Override
    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	PlayerStats.subtractThirst(par3EntityPlayer, 3000);
    	par1ItemStack.stackSize--;
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        return par1ItemStack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.drink;
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 32;
    }
    
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    { 	
        entityplayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
        return itemstack;
    }
    
    @Override
    public String getTextureFile()
    {
        return "/dayz/images/food.png";
    }
}
