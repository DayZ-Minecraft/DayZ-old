package dayz.common.items.misc;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import dayz.common.items.ItemMod;

public class ItemFirestarter extends ItemMod
{
    public ItemFirestarter(int itemId, int numOfUses)
    {
        super(itemId);
        setMaxDamage(numOfUses);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int xCoord, int yCoord, int zCoord, int blockSide, float xHitCoord, float yHitCoord, float zHitCoord)
    {
        if (blockSide == 0)
        {
            --yCoord;
        }

        if (blockSide == 1)
        {
            ++yCoord;
        }

        if (blockSide == 2)
        {
            --zCoord;
        }

        if (blockSide == 3)
        {
            ++zCoord;
        }

        if (blockSide == 4)
        {
            --xCoord;
        }

        if (blockSide == 5)
        {
            ++xCoord;
        }

        if (!entityPlayer.canPlayerEdit(xCoord, yCoord, zCoord, blockSide, itemStack))
        {
            return false;
        }
        else
        {
            int var11 = world.getBlockId(xCoord, yCoord, zCoord);

            if (var11 == 0)
            {
                world.playSoundEffect(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                world.setBlock(xCoord, yCoord, zCoord, Block.fire.blockID);
            }

            itemStack.damageItem(1, entityPlayer);
            return true;
        }
    }
}
