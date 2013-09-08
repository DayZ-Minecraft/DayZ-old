package dayz.common.items.weapons;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import dayz.common.entities.EntityBullet;
import dayz.common.items.ItemMod;
import dayz.common.misc.Util;

public class ItemGunSemi extends ItemMod
{
    private IGun gun;

    public ItemGunSemi(int i, IGun iGun)
    {
        super(i);
        gun = iGun;
        maxStackSize = 1;
        setMaxDamage(gun.getRounds());
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int i)
    {
        if (itemstack.getItemDamage() < gun.getRounds())
        {
            int j = getMaxItemUseDuration(itemstack) - i;
            float f = 1.0F;
            EntityBullet entitybullet = new EntityBullet(world, entityplayer, gun.getDamage());
            world.playSoundAtEntity(entityplayer, Util.ID + ":" + gun.getShootSound(), 1.0F, 1.0F);
            itemstack.damageItem(1, entityplayer);

            if (!world.isRemote)
            {
                world.spawnEntityInWorld(entitybullet);
            }
        }
        else if (itemstack.getItemDamage() >= gun.getRounds() && entityplayer.inventory.hasItem(gun.getAmmo().itemID))
        {
            int k = getMaxItemUseDuration(itemstack) - i;
            float f1 = k / 20F;
            f1 = (f1 * f1 + f1 * 2.0F) / 3F;

            if (f1 >= 1.0F)
            {
                itemstack.setItemDamage(0);
                world.playSoundAtEntity(entityplayer, Util.ID + ":" + gun.getReloadSound(), 1.0F, 1.0F);
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

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        entityplayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
        return itemstack;
    }
}