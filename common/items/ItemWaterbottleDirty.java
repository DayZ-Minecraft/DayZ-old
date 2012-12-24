package dayz.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import dayz.DayZ;
import dayz.common.playerdata.PlayerData;

public class ItemWaterbottleDirty extends ItemDayzDrink
{
	public int thirst;
	public Item returnItem;
	
    public ItemWaterbottleDirty(int par1, int thirst, Item returnItem)
    {
        super(par1, thirst, returnItem);
        this.setMaxStackSize(1);
        this.setCreativeTab(DayZ.creativeTabDayZ);
        this.thirst = thirst;
        this.returnItem = returnItem;
    }

    @Override
    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	PlayerData.subtractThirst(par3EntityPlayer, thirst);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.poison.id, 30 * 20, 6));
        par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 30 * 20, 6));
    	if (returnItem != null)
    	{
            return par1ItemStack.stackSize <= 0 ? new ItemStack(Item.bucketEmpty) : par1ItemStack;
    	}
    	else
    	{
            --par1ItemStack.stackSize;
    		return par1ItemStack;
    	}
    }
}
