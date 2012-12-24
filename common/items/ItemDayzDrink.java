package dayz.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import dayz.DayZ;
import dayz.common.playerdata.PlayerData;

public class ItemDayzDrink extends Item
{
	public int thirst;
	public Item returnItem;
	
    public ItemDayzDrink(int par1, int thirst, Item returnItem)
    {
        super(par1);
        this.setMaxStackSize(1);
        this.setCreativeTab(DayZ.creativeTabDayZ);
        this.thirst = thirst;
        this.returnItem = returnItem;
    }

    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	PlayerData.subtractThirst(par3EntityPlayer, thirst);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);

    	if (returnItem != null)
    	{
            return par1ItemStack.stackSize <= 0 ? new ItemStack(returnItem) : par1ItemStack;
    	}
    	else
    	{
            --par1ItemStack.stackSize;
    		return par1ItemStack;
    	}
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.drink;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }
}
