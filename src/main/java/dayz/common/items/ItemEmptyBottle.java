package dayz.common.items;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import dayz.DayZ;

public class ItemEmptyBottle extends Item
{
    /** field for checking if the bucket has been filled. */
    private int isFull;
    private boolean isRefillable;
    private int textureIndex;

    public ItemEmptyBottle(int i, int j, boolean refillable, int index)
    {
        super(i);
        maxStackSize = 1;
        isFull = j;
        isRefillable = refillable;
        textureIndex = index;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (isRefillable == true)
        {
            MovingObjectPosition var4 = getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

            if (var4 == null)
            {
                return par1ItemStack;
            }
            else
            {
                if (var4.typeOfHit == EnumMovingObjectType.TILE)
                {
                    int var5 = var4.blockX;
                    int var6 = var4.blockY;
                    int var7 = var4.blockZ;

                    if (!par2World.canMineBlock(par3EntityPlayer, var5, var6, var7))
                    {
                        return par1ItemStack;
                    }

                    if (par2World.getBlockMaterial(var5, var6, var7) == Material.water)
                    {
                        --par1ItemStack.stackSize;

                        if (par1ItemStack.stackSize <= 0)
                        {
                            return new ItemStack(DayZ.waterbottledirty);
                        }

                        if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(DayZ.waterbottledirty)))
                        {
                            par3EntityPlayer.dropPlayerItem(new ItemStack(DayZ.waterbottledirty, 1, 0));
                        }
                    }
                }
            }

        }
        return par1ItemStack;
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        switch (textureIndex)
        {
            case 0:
                itemIcon = par1IconRegister.registerIcon("dayz:emptywaterbottle");
                return;
            case 1:
                itemIcon = par1IconRegister.registerIcon("dayz:whiskeybottleempty");
                return;
            case 2:
                itemIcon = par1IconRegister.registerIcon("dayz:vodkaempty");
                return;
            case 3:
                itemIcon = par1IconRegister.registerIcon("dayz:ciderempty");
                return;
        }
    }
}
