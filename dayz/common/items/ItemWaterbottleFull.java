package dayz.common.items;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import dayz.DayZ;
import dayz.common.entities.EntityBullet;
import dayz.common.misc.Util;
import dayz.common.playerdata.PlayerStats;

public class ItemWaterbottleFull extends ItemDayzDrink
{
    private int thirst;

    public ItemWaterbottleFull(int itemID, int thirst)
    {
        super(itemID, 0, 0);
        this.maxStackSize = 1;
        this.thirst = thirst;
    }

    @Override
    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par1ItemStack.stackSize--;
        PlayerStats.subtractThirst(par3EntityPlayer, thirst);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);

        if (par1ItemStack.stackSize <= 0)
        {
            return new ItemStack(DayZ.waterbottleempty);
        }
        else
        {
            par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(DayZ.waterbottleempty));
        }

        return par1ItemStack;
    }

    @Override
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
    	this.itemIcon = par1IconRegister.registerIcon("dayz:waterbottlefull");
    }
}
