package dayz.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import dayz.DayZ;
import dayz.common.entities.EntityBullet;


public class ItemEnfield extends Item
{
    public ItemEnfield(int i)
    {
        super(i);
        maxStackSize = 1;
        setMaxDamage(11);
    }

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int i)
    {
        boolean flag = entityplayer.capabilities.isCreativeMode;

        if (itemstack.getItemDamage() < 11)
        {
            int j = getMaxItemUseDuration(itemstack) - i;
            float f = 1.0F;
            EntityBullet entitybullet = new EntityBullet(world, entityplayer, 8);
            world.playSoundAtEntity(entityplayer, "leeenfield", 1.0F, 1.0F);
            itemstack.damageItem(1, entityplayer);

            if (!world.isRemote)
            {
                world.spawnEntityInWorld(entitybullet);
            }
        }
        else if (itemstack.getItemDamage() >= 11 && entityplayer.inventory.hasItem(DayZ.leeenfieldammo.itemID))
        {
            int k = getMaxItemUseDuration(itemstack) - i;
            float f1 = (float)k / 20F;
            f1 = (f1 * f1 + f1 * 2.0F) / 3F;

            if (f1 >= 1.0F)
            {
                itemstack.setItemDamage(0);
                world.playSoundAtEntity(entityplayer, "reload", 1.0F, 1.0F);
                entityplayer.inventory.consumeInventoryItem(DayZ.leeenfieldammo.itemID);
            }
        }
        else
        {
            world.playSoundAtEntity(entityplayer, "random.click", 1.0F, 1.0F);
        }
    }

    public ItemStack onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        return itemstack;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack itemstack)
    {
        return 0x11940;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack itemstack)
    {
        if (itemstack.getItemDamage() < 11)
        {
            return null;
        }
        else
        {
            return EnumAction.block;
        }
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        entityplayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
        return itemstack;
    }
    @Override
    public String getTextureFile()
    {
        return "/dayz/images/weapons.png";
    }
}

