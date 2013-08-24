package dayz.common.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import dayz.DayZ;
import dayz.common.entities.EntityBullet;
import dayz.common.misc.Util;

public class ItemAk74u extends Item
{
    public ItemAk74u(int i)
    {
        super(i);
        maxStackSize = 1;
        setMaxDamage(30);
    }

    public static int recoilY, recoilX;

    /**
     * called when the player releases the use item button. Args: itemstack,
     * world, entityplayer, itemInUseCount
     */
    @Override
    public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int i)
    {
        boolean flag = entityplayer.capabilities.isCreativeMode;

        if (itemstack.getItemDamage() >= 30 && entityplayer.inventory.hasItem(DayZ.ak74uammo.itemID))
        {
            int j = getMaxItemUseDuration(itemstack) - i;
            float f = j / 20F;
            f = (f * f + f * 2.0F) / 3F;

            if (f >= 1.0F)
            {
                world.playSoundAtEntity(entityplayer, Util.ID + ":reload", 1.0F, 1.0F);
                itemstack.setItemDamage(0);
                entityplayer.inventory.consumeInventoryItem(DayZ.ak74uammo.itemID);
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
    @Override
    public int getMaxItemUseDuration(ItemStack itemstack)
    {
        return 0x11940;
    }

    /**
     * returns the action that specifies what animation to play when the items
     * is being used
     */
    @Override
    public EnumAction getItemUseAction(ItemStack itemstack)
    {
        if (itemstack.getItemDamage() < 30)
        {
            return null;
        }
        else
        {
            return EnumAction.block;
        }
    }

    /**
     * Called whenever this item is equipped and the right mouse button is
     * pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if (itemstack.getItemDamage() < 30)
        {
            EntityBullet entitybullet = new EntityBullet(world, entityplayer, 10);
            world.playSoundAtEntity(entityplayer, Util.ID + ":ak74u", 1.0F, 1.0F);
            itemstack.damageItem(1, entityplayer);

            if (!world.isRemote)
            {
                world.spawnEntityInWorld(entitybullet);
                applyRecoil(entityplayer);
            }
        }
        else
        {
            entityplayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
        }
        return itemstack;
    }

    private static void applyRecoil(EntityPlayer player)
    {
        double y = 0.0D;
        double y1 = recoilY;
        if (recoilY > 0.0D)
        {
            y = Math.min(Math.max(recoilY * 0.1000000014901161D, 0.5D), recoilY);
            recoilY -= y;
            player.rotationPitch -= y;
        }
        if (Math.abs(recoilX) > 0.0D)
        {
            double x;
            if (recoilX > 0.0D)
            {
                x = Math.min(Math.max(recoilX * 0.1000000014901161D / 2.0D, 0.25D), recoilX);
            }
            else
            {
                x = Math.max(Math.min(recoilX * 0.1000000014901161D / 2.0D, -0.25D), recoilX);
            }
            if (y != 0.0D)
            {
                double d3 = y / y1 * recoilX;
                if (recoilX > 0.0D)
                {
                    x = Math.min(d3, x);
                }
                else
                {
                    x = Math.max(d3, x);
                }
            }
            recoilX -= x;
            player.rotationYaw += x;
        }
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        itemIcon = par1IconRegister.registerIcon("dayz:ak74u");
    }
}