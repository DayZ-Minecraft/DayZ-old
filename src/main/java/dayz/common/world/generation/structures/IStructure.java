package dayz.common.world.generation.structures;

import java.util.Random;

import net.minecraft.world.World;

public interface IStructure
{
    public boolean generate(World world, Random rand, int xCoord, int yCoord, int zCoord);
}
