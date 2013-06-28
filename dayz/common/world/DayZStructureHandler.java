package dayz.common.world;

import java.util.Random;

import net.minecraft.world.World;

public class DayZStructureHandler 
{
	public static boolean DayZStructure(World world, Random rand, int i, int j, int k)
	{
		int type = rand.nextInt(2);

		if (type == 0)
		{
            return new WorldGenFort().generate(world, rand, i, j, k);
        }
		else
		{
            return new WorldGenHouse().generate(world, rand, i, j, k);
		}
	}
}
