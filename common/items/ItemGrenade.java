package dayz.common.items;

import dayz.DayZ;
import dayz.common.entities.EntityGrenade;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemGrenade extends Item
{
    public static int grenadeID;

    public ItemGrenade(int itemIndex)
    {
        super(itemIndex);
        grenadeID = shiftedIndex;
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
    public String getTextureFile()
    {
        return "/dayz/images/weapons.png";
    }
}