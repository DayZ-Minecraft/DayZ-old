package dayz.common.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import dayz.DayZ;
import dayz.common.entities.EntityGrenade;

public class ItemGrenade extends Item
{
    public static int grenadeID;

    public ItemGrenade(int itemIndex)
    {
        super(itemIndex);
        grenadeID = itemID;
        maxStackSize = 1;
        if (DayZ.canGenerateExplosives)
        {
            setCreativeTab(DayZ.creativeTabDayZ);
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        world.spawnEntityInWorld(new EntityGrenade(world, entityplayer));
        itemstack.stackSize--;
        return itemstack;
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        itemIcon = par1IconRegister.registerIcon("dayz:grenade");
    }
}