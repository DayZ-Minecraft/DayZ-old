package dayz.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockMine extends BlockMod
{
    public BlockMine(int blockId, Material material)
    {
        super(blockId, material);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int xCoord, int yCoord, int zCoord, Entity entity)
    {
        world.playSoundEffect(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "random.click", 0.3F, 0.6F);
        world.createExplosion(entity, xCoord, yCoord, zCoord, 4.0F, false);
    }

    @Override
    public boolean canPlaceBlockAt(World world, int xCoord, int yCoord, int zCoord)
    {
        return super.canPlaceBlockAt(world, xCoord, yCoord, zCoord) && canThisPlantGrowOnThisBlockID(world.getBlockId(xCoord, yCoord - 1, zCoord));
    }

    protected boolean canThisPlantGrowOnThisBlockID(int blockId)
    {
        return blockId == Block.grass.blockID || blockId == Block.dirt.blockID || blockId == Block.tilledField.blockID || blockId == Block.sand.blockID;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int xCoord, int yCoord, int zCoord)
    {
        return null;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return 1;
    }

    @Override
    public int idDropped(int metadata, Random rand, int fortune)
    {
        return 0;
    }
}
