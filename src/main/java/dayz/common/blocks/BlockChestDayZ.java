package dayz.common.blocks;

import java.util.Random;

import net.minecraft.block.BlockChest;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import dayz.DayZ;

public class BlockChestDayZ extends BlockChest
{
    private Random random;

    public BlockChestDayZ(int id, int isTrapped)
    {
        super(id, isTrapped);
        setBlockUnbreakable();
        setBlockBounds(0.0625F, 0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        random = new Random();
        setCreativeTab(DayZ.creativeTabDayZ);
    }
}