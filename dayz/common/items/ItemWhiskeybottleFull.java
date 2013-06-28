package dayz.common.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import dayz.DayZ;
import dayz.common.misc.Util;
import dayz.common.playerdata.PlayerStats;

public class ItemWhiskeybottleFull extends ItemDayzDrink
{
    private int thirst;
    private Item returnItem;
    private int textureIndex;

    public ItemWhiskeybottleFull(int itemID, int thirst, Item returnItem, int textureIndex)
    {
        super(itemID, thirst, 0);
        this.thirst = thirst;
        this.returnItem = returnItem;
        this.maxStackSize = 1;
        this.textureIndex = textureIndex;
    }

    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par1ItemStack.stackSize--;
        PlayerStats.subtractThirst(par3EntityPlayer, thirst);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 30 * 20, 6));
        par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.confusion.id, 30 * 20, 6));

        if (par1ItemStack.stackSize <= 0)
        {
            return new ItemStack(DayZ.whiskeybottleempty);
        }
        else
        {
            par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(returnItem));
        }

        return par1ItemStack;
    }

    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.drink;
    }
    
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    { 	
        entityplayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
        return itemstack;
    }

    public void registerIcons(IconRegister par1IconRegister)
    {
    	switch(this.textureIndex)
    	{
    		case 0: this.itemIcon = par1IconRegister.registerIcon("dayz:vodkafull"); return;
    		case 1: this.itemIcon = par1IconRegister.registerIcon("dayz:ciderfull"); return;
    		case 2: this.itemIcon = par1IconRegister.registerIcon("dayz:whiskeybottlefull"); return;
    	}
    }
}
