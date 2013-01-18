package dayz.common.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import dayz.DayZ;

public class BlockBarbedWire extends BlockBase
{
    public BlockBarbedWire(int i, int j)
    {
        super(i, j, Material.iron);
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        par5Entity.attackEntityFrom(DamageSource.generic, 1);
        par5Entity.setInWeb();
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int i)
    {
        return null;
    }

    @Override
    public int getRenderType()
    {
        return 6;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return DayZ.barbedwire.blockID;
    }
}