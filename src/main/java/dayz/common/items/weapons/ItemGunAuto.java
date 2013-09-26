package dayz.common.items.weapons;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import dayz.DayZ;
import dayz.common.entities.EntityBullet;
import dayz.common.items.ItemMod;

public class ItemGunAuto extends ItemMod
{
    private IGun gun;

    public ItemGunAuto(int itemId, IGun iGun)
    {
        super(itemId);
        gun = iGun;
        maxStackSize = 1;
        setMaxDamage(gun.getRounds());
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int i)
    {
        if (itemstack.getItemDamage() >= gun.getRounds() && entityplayer.inventory.hasItem(gun.getAmmo().itemID))
        {
            int j = getMaxItemUseDuration(itemstack) - i;
            float f = j / 20F;
            f = (f * f + f * 2.0F) / 3F;

            if (f >= 1.0F)
            {
                world.playSoundAtEntity(entityplayer, DayZ.meta.modId + ":" + gun.getReloadSound(), 1.0F, 1.0F);
                itemstack.setItemDamage(0);
                entityplayer.inventory.consumeInventoryItem(gun.getAmmo().itemID);
            }
        }
        else
        {
            world.playSoundAtEntity(entityplayer, "random.click", 1.0F, 1.0F);
        }
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemstack)
    {
        return 0x11940;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemstack)
    {
        if (itemstack.getItemDamage() < gun.getRounds())
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
        if (itemstack.getItemDamage() < gun.getRounds())
        {
            EntityBullet entitybullet = new EntityBullet(world, entityplayer, gun.getDamage());
            world.playSoundAtEntity(entityplayer, DayZ.meta.modId + ":" + gun.getShootSound(), 1.0F, 1.0F);
            itemstack.damageItem(1, entityplayer);

            if (!world.isRemote)
            {
                world.spawnEntityInWorld(entitybullet);
            }
        }
        else
        {
            entityplayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
        }
        return itemstack;
    }
}
