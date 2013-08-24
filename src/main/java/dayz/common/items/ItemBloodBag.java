package dayz.common.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import dayz.common.misc.Util;

public class ItemBloodBag extends Item
{
    private EnumAction acionOnUse;

    public ItemBloodBag(int i)
    {
        super(i);
        maxStackSize = 1;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack itemstack, EntityPlayer player, Entity entity)
    {
        if (entity instanceof EntityPlayer)
        {
            EntityPlayer entityliving = (EntityPlayer) entity;
            if (!entityliving.isOnLadder() && !entityliving.isAirBorne && !entityliving.isBurning() && !entityliving.isEating() && !entityliving.isSprinting() && !entityliving.isWet() && Util.getHealth(entityliving) < 20)
            {
                itemstack.stackSize--;
                entityliving.heal(20);
            }
        }

        if (entity instanceof EntityPlayerMP)
        {
            EntityPlayer entityliving = (EntityPlayer) entity;
            if (!entityliving.isOnLadder() && !entityliving.isAirBorne && !entityliving.isBurning() && !entityliving.isEating() && !entityliving.isSprinting() && !entityliving.isWet() && Util.getHealth(entityliving) < 20)
            {
                itemstack.stackSize--;
                entityliving.heal(20);
            }
        }
        return true;
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        itemIcon = par1IconRegister.registerIcon("dayz:bloodbag");
    }
}