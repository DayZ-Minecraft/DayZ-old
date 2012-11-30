package dayz.common.world;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntityChest;
import net.minecraft.src.WeightedRandomChestContent;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import dayz.DayZ;
import dayz.DayZLog;
import dayz.Util;
import dayz.common.ChestHookRegistry;

public class WorldGenHouse extends WorldGenerator
{
    public WorldGenHouse() {}
    private Random rand;
    public int chestcontents;
    
    public boolean generate(World world, Random rand, int i, int j, int k)
    {
        int bID = 2;

        if (world.getBlockId(i, j, k) != bID || world.getBlockId(i, j + 1, k) != 0 || world.getBlockId(i + 7, j, k) != bID || world.getBlockId(i + 7, j, k + 7) != bID || world.getBlockId(i, j, k + 7) != bID || world.getBlockId(i + 7, j + 1, k) != 0 || world.getBlockId(i + 7, j + 1, k + 7) != 0 || world.getBlockId(i, j + 1, k + 7) != 0)
        {
            return false;
        }

        world.setBlock(i + 1, j + 0, k + 9, 4);
        world.setBlock(i + 1, j + 0, k + 8, 4);
        world.setBlock(i + 1, j + 0, k + 7, 4);
        world.setBlock(i + 1, j + 0, k + 6, 4);
        world.setBlock(i + 1, j + 0, k + 5, 4);
        world.setBlock(i + 1, j + 0, k + 4, 4);
        world.setBlock(i + 1, j + 0, k + 3, 4);
        world.setBlock(i + 1, j + 0, k + 2, 4);
        world.setBlock(i + 1, j + 0, k + 1, 4);
        world.setBlock(i + 2, j + 0, k + 9, 4);
        world.setBlock(i + 2, j + 0, k + 8, 5);
        world.setBlock(i + 2, j + 0, k + 7, 5);
        world.setBlock(i + 2, j + 0, k + 6, 5);
        world.setBlock(i + 2, j + 0, k + 5, 5);
        world.setBlock(i + 2, j + 0, k + 4, 5);
        world.setBlock(i + 2, j + 0, k + 3, 5);
        world.setBlock(i + 2, j + 0, k + 2, 5);
        world.setBlock(i + 2, j + 0, k + 1, 4);
        world.setBlock(i + 3, j + 0, k + 9, 4);
        world.setBlock(i + 3, j + 0, k + 8, 5);
        world.setBlock(i + 3, j + 0, k + 7, 5);
        world.setBlock(i + 3, j + 0, k + 6, 5);
        world.setBlock(i + 3, j + 0, k + 5, 5);
        world.setBlock(i + 3, j + 0, k + 4, 5);
        world.setBlock(i + 3, j + 0, k + 3, 5);
        world.setBlock(i + 3, j + 0, k + 2, 5);
        world.setBlock(i + 3, j + 0, k + 1, 4);
        world.setBlock(i + 4, j + 0, k + 9, 4);
        world.setBlock(i + 4, j + 0, k + 8, 5);
        world.setBlock(i + 4, j + 0, k + 7, 5);
        world.setBlock(i + 4, j + 0, k + 6, 5);
        world.setBlock(i + 4, j + 0, k + 5, 5);
        world.setBlock(i + 4, j + 0, k + 4, 5);
        world.setBlock(i + 4, j + 0, k + 3, 5);
        world.setBlock(i + 4, j + 0, k + 2, 5);
        world.setBlock(i + 4, j + 0, k + 1, 4);
        world.setBlock(i + 5, j + 0, k + 9, 4);
        world.setBlock(i + 5, j + 0, k + 8, 5);
        world.setBlock(i + 5, j + 0, k + 7, 5);
        world.setBlock(i + 5, j + 0, k + 6, 5);
        world.setBlock(i + 5, j + 0, k + 5, 5);
        world.setBlock(i + 5, j + 0, k + 4, 5);
        world.setBlock(i + 5, j + 0, k + 3, 5);
        world.setBlock(i + 5, j + 0, k + 2, 5);
        world.setBlock(i + 5, j + 0, k + 1, 4);
        world.setBlock(i + 6, j + 0, k + 9, 4);
        world.setBlock(i + 6, j + 0, k + 8, 5);
        world.setBlock(i + 6, j + 0, k + 7, 5);
        world.setBlock(i + 6, j + 0, k + 6, 5);
        world.setBlock(i + 6, j + 0, k + 5, 5);
        world.setBlock(i + 6, j + 0, k + 4, 5);
        world.setBlock(i + 6, j + 0, k + 3, 5);
        world.setBlock(i + 6, j + 0, k + 2, 5);
        world.setBlock(i + 6, j + 0, k + 1, 4);
        world.setBlock(i + 7, j + 0, k + 9, 4);
        world.setBlock(i + 7, j + 0, k + 8, 5);
        world.setBlock(i + 7, j + 0, k + 7, 5);
        world.setBlock(i + 7, j + 0, k + 6, 5);
        world.setBlock(i + 7, j + 0, k + 5, 5);
        world.setBlock(i + 7, j + 0, k + 4, 5);
        world.setBlock(i + 7, j + 0, k + 3, 5);
        world.setBlock(i + 7, j + 0, k + 2, 5);
        world.setBlock(i + 7, j + 0, k + 1, 4);
        world.setBlock(i + 8, j + 0, k + 9, 4);
        world.setBlock(i + 8, j + 0, k + 8, 5);
        world.setBlock(i + 8, j + 0, k + 7, 5);
        world.setBlock(i + 8, j + 0, k + 6, 5);
        world.setBlock(i + 8, j + 0, k + 5, 5);
        world.setBlock(i + 8, j + 0, k + 4, 5);
        world.setBlock(i + 8, j + 0, k + 3, 5);
        world.setBlock(i + 8, j + 0, k + 2, 5);
        world.setBlock(i + 8, j + 0, k + 1, 4);
        world.setBlock(i + 9, j + 0, k + 9, 4);
        world.setBlock(i + 9, j + 0, k + 8, 4);
        world.setBlock(i + 9, j + 0, k + 7, 4);
        world.setBlock(i + 9, j + 0, k + 6, 4);
        world.setBlock(i + 9, j + 0, k + 5, 4);
        world.setBlock(i + 9, j + 0, k + 4, 4);
        world.setBlock(i + 9, j + 0, k + 3, 4);
        world.setBlock(i + 9, j + 0, k + 2, 4);
        world.setBlock(i + 9, j + 0, k + 1, 4);
        world.setBlock(i + 1, j + 1, k + 9, 17);
        world.setBlock(i + 1, j + 1, k + 8, 5);
        world.setBlock(i + 1, j + 1, k + 7, 5);
        world.setBlock(i + 1, j + 1, k + 6, 5);
        boolean flag2 = false;
        int doorstate = 0;
        world.setBlockAndMetadataWithNotify(i + 1, j + 1, k + 5, Block.doorWood.blockID, doorstate);
        world.setBlockAndMetadataWithNotify(i + 1, j + 2, k + 5, Block.doorWood.blockID, 8 | (flag2 ? 1 : doorstate));
        world.setBlock(i + 1, j + 1, k + 4, 5);
        world.setBlock(i + 1, j + 1, k + 3, 5);
        world.setBlock(i + 1, j + 1, k + 2, 5);
        world.setBlock(i + 1, j + 1, k + 1, 17);
        world.setBlock(i + 2, j + 1, k + 9, 5);
        world.setBlock(i + 2, j + 1, k + 8, 0);
        world.setBlock(i + 2, j + 1, k + 7, 0);
        world.setBlock(i + 2, j + 1, k + 6, 0);
        world.setBlock(i + 2, j + 1, k + 5, 0);
        world.setBlock(i + 2, j + 1, k + 4, 0);
        world.setBlock(i + 2, j + 1, k + 3, 0);
        world.setBlock(i + 2, j + 1, k + 2, 47);
        world.setBlock(i + 2, j + 1, k + 1, 5);
        world.setBlock(i + 3, j + 1, k + 9, 5);
        world.setBlock(i + 3, j + 1, k + 8, 0);
        world.setBlock(i + 3, j + 1, k + 7, 85);
        world.setBlock(i + 3, j + 1, k + 6, 0);
        world.setBlock(i + 3, j + 1, k + 5, 0);
        world.setBlock(i + 3, j + 1, k + 4, 0);
        world.setBlock(i + 3, j + 1, k + 3, 0);
        world.setBlock(i + 3, j + 1, k + 2, 47);
        world.setBlock(i + 3, j + 1, k + 1, 5);
        world.setBlock(i + 4, j + 1, k + 9, 5);
        world.setBlock(i + 4, j + 1, k + 8, 0);
        world.setBlock(i + 4, j + 1, k + 7, 0);
        world.setBlock(i + 4, j + 1, k + 6, 0);
        world.setBlock(i + 4, j + 1, k + 5, 0);
        world.setBlock(i + 4, j + 1, k + 4, 0);
        world.setBlock(i + 4, j + 1, k + 3, 0);
        world.setBlock(i + 4, j + 1, k + 2, 47);
        world.setBlock(i + 4, j + 1, k + 1, 5);
        world.setBlock(i + 5, j + 1, k + 9, 5);
        world.setBlock(i + 5, j + 1, k + 8, 0);
        world.setBlock(i + 5, j + 1, k + 7, 0);
        world.setBlock(i + 5, j + 1, k + 6, 0);
        world.setBlock(i + 5, j + 1, k + 5, 0);
        world.setBlock(i + 5, j + 1, k + 4, 0);
        world.setBlock(i + 5, j + 1, k + 3, 0);
        world.setBlock(i + 5, j + 1, k + 2, 47);
        world.setBlock(i + 5, j + 1, k + 1, 5);
        world.setBlock(i + 6, j + 1, k + 9, 5);
        world.setBlock(i + 6, j + 1, k + 8, 0);
        world.setBlock(i + 6, j + 1, k + 7, 85);
        world.setBlock(i + 6, j + 1, k + 6, 0);
        world.setBlock(i + 6, j + 1, k + 5, 0);
        world.setBlock(i + 6, j + 1, k + 4, 0);
        world.setBlock(i + 6, j + 1, k + 3, 0);
        world.setBlock(i + 6, j + 1, k + 2, 47);
        world.setBlock(i + 6, j + 1, k + 1, 5);
        world.setBlock(i + 7, j + 1, k + 9, 5);
        world.setBlock(i + 7, j + 1, k + 8, 0);
        world.setBlock(i + 7, j + 1, k + 7, 0);
        world.setBlock(i + 7, j + 1, k + 6, 0);
        world.setBlock(i + 7, j + 1, k + 5, 0);
        world.setBlock(i + 7, j + 1, k + 4, 0);
        world.setBlock(i + 7, j + 1, k + 3, 0);
        world.setBlock(i + 7, j + 1, k + 2, 47);
        world.setBlock(i + 7, j + 1, k + 1, 5);
        world.setBlock(i + 8, j + 1, k + 9, 5);
        world.setBlock(i + 8, j + 1, k + 8, 47);
        world.setBlock(i + 8, j + 1, k + 7, 61);
        world.setBlock(i + 8, j + 1, k + 6, 61);
        
        int chest = DayZ.dayzchestall.blockID;
        int lootType = rand.nextInt(4);
        int itemlocation = rand.nextInt(27);
        int numofcontents = rand.nextInt(10);
        
        world.setBlockWithNotify(i + 8, j + 1, k + 5, chest);
        TileEntityChest tileentitychest = (TileEntityChest)world.getBlockTileEntity(i + 8, j + 1, k + 5);
		WeightedRandomChestContent.generateChestContents(rand, ChestHookRegistry.chestCommonContents, tileentitychest, rand.nextInt(5) + 1);	
        DayZLog.info("Day Z House Chest Created At " + (i + 8) + ", " + (j + 1) + ", " + (k + 5) + ".");
        
        world.setBlock(i + 8, j + 1, k + 4, 58);
        world.setBlock(i + 8, j + 1, k + 3, 0);
        world.setBlock(i + 8, j + 1, k + 2, 47);
        world.setBlock(i + 8, j + 1, k + 1, 5);
        world.setBlock(i + 9, j + 1, k + 9, 17);
        world.setBlock(i + 9, j + 1, k + 8, 5);
        world.setBlock(i + 9, j + 1, k + 7, 5);
        world.setBlock(i + 9, j + 1, k + 6, 5);
        world.setBlock(i + 9, j + 1, k + 5, 5);
        world.setBlock(i + 9, j + 1, k + 4, 5);
        world.setBlock(i + 9, j + 1, k + 3, 5);
        world.setBlock(i + 9, j + 1, k + 2, 5);
        world.setBlock(i + 9, j + 1, k + 1, 17);
        world.setBlock(i + 1, j + 2, k + 9, 17);
        world.setBlock(i + 1, j + 2, k + 8, 20);
        world.setBlock(i + 1, j + 2, k + 7, 20);
        world.setBlock(i + 1, j + 2, k + 6, 5);
        world.setBlock(i + 1, j + 2, k + 4, 5);
        world.setBlock(i + 1, j + 2, k + 3, 20);
        world.setBlock(i + 1, j + 2, k + 2, 20);
        world.setBlock(i + 1, j + 2, k + 1, 17);
        world.setBlock(i + 2, j + 2, k + 9, 5);
        world.setBlock(i + 2, j + 2, k + 8, 0);
        world.setBlock(i + 2, j + 2, k + 7, 0);
        world.setBlock(i + 2, j + 2, k + 6, 0);
        world.setBlock(i + 2, j + 2, k + 5, 0);
        world.setBlock(i + 2, j + 2, k + 4, 0);
        world.setBlock(i + 2, j + 2, k + 3, 0);
        world.setBlock(i + 2, j + 2, k + 2, 47);
        world.setBlock(i + 2, j + 2, k + 1, 5);
        world.setBlock(i + 3, j + 2, k + 9, 20);
        world.setBlock(i + 3, j + 2, k + 8, 0);
        world.setBlock(i + 3, j + 2, k + 7, 72);
        world.setBlock(i + 3, j + 2, k + 6, 0);
        world.setBlock(i + 3, j + 2, k + 5, 0);
        world.setBlock(i + 3, j + 2, k + 4, 0);
        world.setBlock(i + 3, j + 2, k + 3, 0);
        world.setBlock(i + 3, j + 2, k + 2, 0);
        world.setBlock(i + 3, j + 2, k + 1, 20);
        world.setBlock(i + 4, j + 2, k + 9, 20);
        world.setBlock(i + 4, j + 2, k + 8, 0);
        world.setBlock(i + 4, j + 2, k + 7, 0);
        world.setBlock(i + 4, j + 2, k + 6, 0);
        world.setBlock(i + 4, j + 2, k + 5, 0);
        world.setBlock(i + 4, j + 2, k + 4, 0);
        world.setBlock(i + 4, j + 2, k + 3, 0);
        world.setBlock(i + 4, j + 2, k + 2, 0);
        world.setBlock(i + 4, j + 2, k + 1, 20);
        world.setBlockWithNotify(i + 5, j + 2, k + 10, 50);
        world.setBlock(i + 5, j + 2, k + 9, 5);
        world.setBlockWithNotify(i + 5, j + 2, k + 8, 50);
        world.setBlock(i + 5, j + 2, k + 7, 0);
        world.setBlock(i + 5, j + 2, k + 6, 0);
        world.setBlock(i + 5, j + 2, k + 5, 0);
        world.setBlock(i + 5, j + 2, k + 4, 0);
        world.setBlock(i + 5, j + 2, k + 3, 0);
        world.setBlockWithNotify(i + 5, j + 2, k + 2, 50);
        world.setBlock(i + 5, j + 2, k + 1, 5);
        world.setBlockWithNotify(i + 5, j + 2, k + 0, 50);
        world.setBlock(i + 6, j + 2, k + 9, 20);
        world.setBlock(i + 6, j + 2, k + 8, 0);
        world.setBlock(i + 6, j + 2, k + 7, 72);
        world.setBlock(i + 6, j + 2, k + 6, 0);
        world.setBlock(i + 6, j + 2, k + 5, 0);
        world.setBlock(i + 6, j + 2, k + 4, 0);
        world.setBlock(i + 6, j + 2, k + 3, 0);
        world.setBlock(i + 6, j + 2, k + 2, 0);
        world.setBlock(i + 6, j + 2, k + 1, 20);
        world.setBlock(i + 7, j + 2, k + 9, 20);
        world.setBlock(i + 7, j + 2, k + 8, 0);
        world.setBlock(i + 7, j + 2, k + 7, 0);
        world.setBlock(i + 7, j + 2, k + 6, 0);
        world.setBlock(i + 7, j + 2, k + 5, 0);
        world.setBlock(i + 7, j + 2, k + 4, 0);
        world.setBlock(i + 7, j + 2, k + 3, 0);
        world.setBlock(i + 7, j + 2, k + 2, 0);
        world.setBlock(i + 7, j + 2, k + 1, 20);
        world.setBlock(i + 8, j + 2, k + 9, 5);
        world.setBlock(i + 8, j + 2, k + 8, 47);
        world.setBlock(i + 8, j + 2, k + 7, 0);
        world.setBlock(i + 8, j + 2, k + 6, 0);
        world.setBlock(i + 8, j + 2, k + 5, 0);
        world.setBlock(i + 8, j + 2, k + 4, 0);
        world.setBlock(i + 8, j + 2, k + 3, 0);
        world.setBlock(i + 8, j + 2, k + 2, 47);
        world.setBlock(i + 8, j + 2, k + 1, 5);
        world.setBlock(i + 9, j + 2, k + 9, 17);
        world.setBlock(i + 9, j + 2, k + 8, 5);
        world.setBlock(i + 9, j + 2, k + 7, 20);
        world.setBlock(i + 9, j + 2, k + 6, 20);
        world.setBlock(i + 9, j + 2, k + 5, 5);
        world.setBlock(i + 9, j + 2, k + 4, 20);
        world.setBlock(i + 9, j + 2, k + 3, 20);
        world.setBlock(i + 9, j + 2, k + 2, 5);
        world.setBlock(i + 9, j + 2, k + 1, 17);
        world.setBlockWithNotify(i + 10, j + 2, k + 5, 50);
        world.setBlockWithNotify(i + 0, j + 3, k + 5, 50);
        world.setBlock(i + 1, j + 3, k + 10, 4);
        world.setBlock(i + 1, j + 3, k + 9, 17);
        world.setBlock(i + 1, j + 3, k + 8, 5);
        world.setBlock(i + 1, j + 3, k + 7, 5);
        world.setBlock(i + 1, j + 3, k + 6, 5);
        world.setBlock(i + 1, j + 3, k + 5, 5);
        world.setBlock(i + 1, j + 3, k + 4, 5);
        world.setBlock(i + 1, j + 3, k + 3, 5);
        world.setBlock(i + 1, j + 3, k + 2, 5);
        world.setBlock(i + 1, j + 3, k + 1, 17);
        world.setBlock(i + 1, j + 3, k + 0, 4);
        world.setBlock(i + 2, j + 3, k + 10, 4);
        world.setBlock(i + 2, j + 3, k + 9, 5);
        world.setBlock(i + 2, j + 3, k + 8, 0);
        world.setBlockWithNotify(i + 2, j + 3, k + 7, 50);
        world.setBlock(i + 2, j + 3, k + 6, 0);
        world.setBlock(i + 2, j + 3, k + 5, 0);
        world.setBlockWithNotify(i + 2, j + 3, k + 4, 50);
        world.setBlock(i + 2, j + 3, k + 3, 0);
        world.setBlock(i + 2, j + 3, k + 2, 47);
        world.setBlock(i + 2, j + 3, k + 1, 5);
        world.setBlock(i + 2, j + 3, k + 0, 4);
        world.setBlock(i + 3, j + 3, k + 10, 4);
        world.setBlock(i + 3, j + 3, k + 9, 5);
        world.setBlock(i + 3, j + 3, k + 8, 0);
        world.setBlock(i + 3, j + 3, k + 7, 0);
        world.setBlock(i + 3, j + 3, k + 6, 0);
        world.setBlock(i + 3, j + 3, k + 5, 0);
        world.setBlock(i + 3, j + 3, k + 4, 0);
        world.setBlock(i + 3, j + 3, k + 3, 0);
        world.setBlock(i + 3, j + 3, k + 2, 0);
        world.setBlock(i + 3, j + 3, k + 1, 5);
        world.setBlock(i + 3, j + 3, k + 0, 4);
        world.setBlock(i + 4, j + 3, k + 10, 4);
        world.setBlock(i + 4, j + 3, k + 9, 5);
        world.setBlock(i + 4, j + 3, k + 8, 0);
        world.setBlock(i + 4, j + 3, k + 7, 0);
        world.setBlock(i + 4, j + 3, k + 6, 0);
        world.setBlock(i + 4, j + 3, k + 5, 0);
        world.setBlock(i + 4, j + 3, k + 4, 0);
        world.setBlock(i + 4, j + 3, k + 3, 0);
        world.setBlock(i + 4, j + 3, k + 2, 0);
        world.setBlock(i + 4, j + 3, k + 1, 5);
        world.setBlock(i + 4, j + 3, k + 0, 4);
        world.setBlock(i + 5, j + 3, k + 10, 4);
        world.setBlock(i + 5, j + 3, k + 9, 5);
        world.setBlock(i + 5, j + 3, k + 8, 0);
        world.setBlock(i + 5, j + 3, k + 7, 0);
        world.setBlock(i + 5, j + 3, k + 6, 0);
        world.setBlock(i + 5, j + 3, k + 5, 0);
        world.setBlock(i + 5, j + 3, k + 4, 0);
        world.setBlock(i + 5, j + 3, k + 3, 0);
        world.setBlock(i + 5, j + 3, k + 2, 0);
        world.setBlock(i + 5, j + 3, k + 1, 5);
        world.setBlock(i + 5, j + 3, k + 0, 4);
        world.setBlock(i + 6, j + 3, k + 10, 4);
        world.setBlock(i + 6, j + 3, k + 9, 5);
        world.setBlock(i + 6, j + 3, k + 8, 0);
        world.setBlock(i + 6, j + 3, k + 7, 0);
        world.setBlock(i + 6, j + 3, k + 6, 0);
        world.setBlock(i + 6, j + 3, k + 5, 0);
        world.setBlock(i + 6, j + 3, k + 4, 0);
        world.setBlock(i + 6, j + 3, k + 3, 0);
        world.setBlock(i + 6, j + 3, k + 2, 0);
        world.setBlock(i + 6, j + 3, k + 1, 5);
        world.setBlock(i + 6, j + 3, k + 0, 4);
        world.setBlock(i + 7, j + 3, k + 10, 4);
        world.setBlock(i + 7, j + 3, k + 9, 5);
        world.setBlock(i + 7, j + 3, k + 8, 0);
        world.setBlock(i + 7, j + 3, k + 7, 0);
        world.setBlock(i + 7, j + 3, k + 6, 0);
        world.setBlock(i + 7, j + 3, k + 5, 0);
        world.setBlock(i + 7, j + 3, k + 4, 0);
        world.setBlock(i + 7, j + 3, k + 3, 0);
        world.setBlock(i + 7, j + 3, k + 2, 0);
        world.setBlock(i + 7, j + 3, k + 1, 5);
        world.setBlock(i + 7, j + 3, k + 0, 4);
        world.setBlock(i + 8, j + 3, k + 10, 4);
        world.setBlock(i + 8, j + 3, k + 9, 5);
        world.setBlock(i + 8, j + 3, k + 8, 0);
        world.setBlockWithNotify(i + 8, j + 3, k + 7, 50);
        world.setBlock(i + 8, j + 3, k + 6, 0);
        world.setBlock(i + 8, j + 3, k + 5, 0);
        world.setBlockWithNotify(i + 8, j + 3, k + 4, 50);
        world.setBlock(i + 8, j + 3, k + 3, 0);
        world.setBlock(i + 8, j + 3, k + 2, 47);
        world.setBlock(i + 8, j + 3, k + 1, 5);
        world.setBlock(i + 8, j + 3, k + 0, 4);
        world.setBlock(i + 9, j + 3, k + 10, 4);
        world.setBlock(i + 9, j + 3, k + 9, 17);
        world.setBlock(i + 9, j + 3, k + 8, 5);
        world.setBlock(i + 9, j + 3, k + 7, 5);
        world.setBlock(i + 9, j + 3, k + 6, 5);
        world.setBlock(i + 9, j + 3, k + 5, 5);
        world.setBlock(i + 9, j + 3, k + 4, 5);
        world.setBlock(i + 9, j + 3, k + 3, 5);
        world.setBlock(i + 9, j + 3, k + 2, 5);
        world.setBlock(i + 9, j + 3, k + 1, 17);
        world.setBlock(i + 9, j + 3, k + 0, 4);
        world.setBlock(i + 1, j + 4, k + 9, 4);
        world.setBlock(i + 1, j + 4, k + 8, 17);
        world.setBlock(i + 1, j + 4, k + 7, 5);
        world.setBlock(i + 1, j + 4, k + 6, 20);
        world.setBlock(i + 1, j + 4, k + 5, 20);
        world.setBlock(i + 1, j + 4, k + 4, 20);
        world.setBlock(i + 1, j + 4, k + 3, 5);
        world.setBlock(i + 1, j + 4, k + 2, 17);
        world.setBlock(i + 1, j + 4, k + 1, 4);
        world.setBlock(i + 2, j + 4, k + 9, 4);
        world.setBlock(i + 2, j + 4, k + 8, 0);
        world.setBlock(i + 2, j + 4, k + 7, 0);
        world.setBlock(i + 2, j + 4, k + 6, 0);
        world.setBlock(i + 2, j + 4, k + 5, 0);
        world.setBlock(i + 2, j + 4, k + 4, 0);
        world.setBlock(i + 2, j + 4, k + 3, 0);
        world.setBlock(i + 2, j + 4, k + 2, 0);
        world.setBlock(i + 2, j + 4, k + 1, 4);
        world.setBlock(i + 3, j + 4, k + 9, 4);
        world.setBlock(i + 3, j + 4, k + 8, 0);
        world.setBlock(i + 3, j + 4, k + 7, 0);
        world.setBlock(i + 3, j + 4, k + 6, 0);
        world.setBlock(i + 3, j + 4, k + 5, 0);
        world.setBlock(i + 3, j + 4, k + 4, 0);
        world.setBlock(i + 3, j + 4, k + 3, 0);
        world.setBlock(i + 3, j + 4, k + 2, 0);
        world.setBlock(i + 3, j + 4, k + 1, 4);
        world.setBlock(i + 4, j + 4, k + 9, 4);
        world.setBlock(i + 4, j + 4, k + 8, 0);
        world.setBlock(i + 4, j + 4, k + 7, 0);
        world.setBlock(i + 4, j + 4, k + 6, 0);
        world.setBlock(i + 4, j + 4, k + 5, 0);
        world.setBlock(i + 4, j + 4, k + 4, 0);
        world.setBlock(i + 4, j + 4, k + 3, 0);
        world.setBlock(i + 4, j + 4, k + 2, 0);
        world.setBlock(i + 4, j + 4, k + 1, 4);
        world.setBlock(i + 5, j + 4, k + 9, 4);
        world.setBlock(i + 5, j + 4, k + 8, 0);
        world.setBlock(i + 5, j + 4, k + 7, 0);
        world.setBlock(i + 5, j + 4, k + 6, 0);
        world.setBlock(i + 5, j + 4, k + 5, 0);
        world.setBlock(i + 5, j + 4, k + 4, 0);
        world.setBlock(i + 5, j + 4, k + 3, 0);
        world.setBlock(i + 5, j + 4, k + 2, 0);
        world.setBlock(i + 5, j + 4, k + 1, 4);
        world.setBlock(i + 6, j + 4, k + 9, 4);
        world.setBlock(i + 6, j + 4, k + 8, 0);
        world.setBlock(i + 6, j + 4, k + 7, 0);
        world.setBlock(i + 6, j + 4, k + 6, 0);
        world.setBlock(i + 6, j + 4, k + 5, 0);
        world.setBlock(i + 6, j + 4, k + 4, 0);
        world.setBlock(i + 6, j + 4, k + 3, 0);
        world.setBlock(i + 6, j + 4, k + 2, 0);
        world.setBlock(i + 6, j + 4, k + 1, 4);
        world.setBlock(i + 7, j + 4, k + 9, 4);
        world.setBlock(i + 7, j + 4, k + 8, 0);
        world.setBlock(i + 7, j + 4, k + 7, 0);
        world.setBlock(i + 7, j + 4, k + 6, 0);
        world.setBlock(i + 7, j + 4, k + 5, 0);
        world.setBlock(i + 7, j + 4, k + 4, 0);
        world.setBlock(i + 7, j + 4, k + 3, 0);
        world.setBlock(i + 7, j + 4, k + 2, 0);
        world.setBlock(i + 7, j + 4, k + 1, 4);
        world.setBlock(i + 8, j + 4, k + 9, 4);
        world.setBlock(i + 8, j + 4, k + 8, 0);
        world.setBlock(i + 8, j + 4, k + 7, 0);
        world.setBlock(i + 8, j + 4, k + 6, 0);
        world.setBlock(i + 8, j + 4, k + 5, 0);
        world.setBlock(i + 8, j + 4, k + 4, 0);
        world.setBlock(i + 8, j + 4, k + 3, 0);
        world.setBlock(i + 8, j + 4, k + 2, 0);
        world.setBlock(i + 8, j + 4, k + 1, 4);
        world.setBlock(i + 9, j + 4, k + 9, 4);
        world.setBlock(i + 9, j + 4, k + 8, 17);
        world.setBlock(i + 9, j + 4, k + 7, 5);
        world.setBlock(i + 9, j + 4, k + 6, 20);
        world.setBlock(i + 9, j + 4, k + 5, 20);
        world.setBlock(i + 9, j + 4, k + 4, 20);
        world.setBlock(i + 9, j + 4, k + 3, 5);
        world.setBlock(i + 9, j + 4, k + 2, 17);
        world.setBlock(i + 9, j + 4, k + 1, 4);
        world.setBlock(i + 1, j + 5, k + 8, 4);
        world.setBlock(i + 1, j + 5, k + 7, 17);
        world.setBlock(i + 1, j + 5, k + 6, 5);
        world.setBlock(i + 1, j + 5, k + 5, 20);
        world.setBlock(i + 1, j + 5, k + 4, 5);
        world.setBlock(i + 1, j + 5, k + 3, 17);
        world.setBlock(i + 1, j + 5, k + 2, 4);
        world.setBlock(i + 2, j + 5, k + 8, 4);
        world.setBlock(i + 2, j + 5, k + 7, 0);
        world.setBlock(i + 2, j + 5, k + 6, 0);
        world.setBlock(i + 2, j + 5, k + 5, 0);
        world.setBlock(i + 2, j + 5, k + 4, 0);
        world.setBlock(i + 2, j + 5, k + 3, 0);
        world.setBlock(i + 2, j + 5, k + 2, 4);
        world.setBlock(i + 3, j + 5, k + 8, 4);
        world.setBlock(i + 3, j + 5, k + 7, 0);
        world.setBlock(i + 3, j + 5, k + 6, 0);
        world.setBlock(i + 3, j + 5, k + 5, 0);
        world.setBlock(i + 3, j + 5, k + 4, 0);
        world.setBlock(i + 3, j + 5, k + 3, 0);
        world.setBlock(i + 3, j + 5, k + 2, 4);
        world.setBlock(i + 4, j + 5, k + 8, 4);
        world.setBlock(i + 4, j + 5, k + 7, 0);
        world.setBlock(i + 4, j + 5, k + 6, 0);
        world.setBlock(i + 4, j + 5, k + 5, 0);
        world.setBlock(i + 4, j + 5, k + 4, 0);
        world.setBlock(i + 4, j + 5, k + 3, 0);
        world.setBlock(i + 4, j + 5, k + 2, 4);
        world.setBlock(i + 5, j + 5, k + 8, 4);
        world.setBlock(i + 5, j + 5, k + 7, 0);
        world.setBlock(i + 5, j + 5, k + 6, 0);
        world.setBlock(i + 5, j + 5, k + 5, 0);
        world.setBlock(i + 5, j + 5, k + 4, 0);
        world.setBlock(i + 5, j + 5, k + 3, 0);
        world.setBlock(i + 5, j + 5, k + 2, 4);
        world.setBlock(i + 6, j + 5, k + 8, 4);
        world.setBlock(i + 6, j + 5, k + 7, 0);
        world.setBlock(i + 6, j + 5, k + 6, 0);
        world.setBlock(i + 6, j + 5, k + 5, 0);
        world.setBlock(i + 6, j + 5, k + 4, 0);
        world.setBlock(i + 6, j + 5, k + 3, 0);
        world.setBlock(i + 6, j + 5, k + 2, 4);
        world.setBlock(i + 7, j + 5, k + 8, 4);
        world.setBlock(i + 7, j + 5, k + 7, 0);
        world.setBlock(i + 7, j + 5, k + 6, 0);
        world.setBlock(i + 7, j + 5, k + 5, 0);
        world.setBlock(i + 7, j + 5, k + 4, 0);
        world.setBlock(i + 7, j + 5, k + 3, 0);
        world.setBlock(i + 7, j + 5, k + 2, 4);
        world.setBlock(i + 8, j + 5, k + 8, 4);
        world.setBlock(i + 8, j + 5, k + 7, 0);
        world.setBlock(i + 8, j + 5, k + 6, 0);
        world.setBlock(i + 8, j + 5, k + 5, 0);
        world.setBlock(i + 8, j + 5, k + 4, 0);
        world.setBlock(i + 8, j + 5, k + 3, 0);
        world.setBlock(i + 8, j + 5, k + 2, 4);
        world.setBlock(i + 9, j + 5, k + 8, 4);
        world.setBlock(i + 9, j + 5, k + 7, 17);
        world.setBlock(i + 9, j + 5, k + 6, 5);
        world.setBlock(i + 9, j + 5, k + 5, 20);
        world.setBlock(i + 9, j + 5, k + 4, 5);
        world.setBlock(i + 9, j + 5, k + 3, 17);
        world.setBlock(i + 9, j + 5, k + 2, 4);
        world.setBlock(i + 1, j + 6, k + 7, 4);
        world.setBlock(i + 1, j + 6, k + 6, 17);
        world.setBlock(i + 1, j + 6, k + 5, 5);
        world.setBlock(i + 1, j + 6, k + 4, 17);
        world.setBlock(i + 1, j + 6, k + 3, 4);
        world.setBlock(i + 2, j + 6, k + 7, 4);
        world.setBlock(i + 2, j + 6, k + 6, 0);
        world.setBlock(i + 2, j + 6, k + 5, 0);
        world.setBlock(i + 2, j + 6, k + 4, 0);
        world.setBlock(i + 2, j + 6, k + 3, 4);
        world.setBlock(i + 3, j + 6, k + 7, 4);
        world.setBlock(i + 3, j + 6, k + 6, 0);
        world.setBlock(i + 3, j + 6, k + 5, 0);
        world.setBlock(i + 3, j + 6, k + 4, 0);
        world.setBlock(i + 3, j + 6, k + 3, 4);
        world.setBlock(i + 4, j + 6, k + 7, 4);
        world.setBlock(i + 4, j + 6, k + 6, 0);
        world.setBlock(i + 4, j + 6, k + 5, 0);
        world.setBlock(i + 4, j + 6, k + 4, 0);
        world.setBlock(i + 4, j + 6, k + 3, 4);
        world.setBlock(i + 5, j + 6, k + 7, 4);
        world.setBlock(i + 5, j + 6, k + 6, 0);
        world.setBlock(i + 5, j + 6, k + 5, 0);
        world.setBlock(i + 5, j + 6, k + 4, 0);
        world.setBlock(i + 5, j + 6, k + 3, 4);
        world.setBlock(i + 6, j + 6, k + 7, 4);
        world.setBlock(i + 6, j + 6, k + 6, 0);
        world.setBlock(i + 6, j + 6, k + 5, 0);
        world.setBlock(i + 6, j + 6, k + 4, 0);
        world.setBlock(i + 6, j + 6, k + 3, 4);
        world.setBlock(i + 7, j + 6, k + 7, 4);
        world.setBlock(i + 7, j + 6, k + 6, 0);
        world.setBlock(i + 7, j + 6, k + 5, 0);
        world.setBlock(i + 7, j + 6, k + 4, 0);
        world.setBlock(i + 7, j + 6, k + 3, 4);
        world.setBlock(i + 8, j + 6, k + 7, 4);
        world.setBlock(i + 8, j + 6, k + 6, 0);
        world.setBlock(i + 8, j + 6, k + 5, 0);
        world.setBlock(i + 8, j + 6, k + 4, 0);
        world.setBlock(i + 8, j + 6, k + 3, 4);
        world.setBlock(i + 9, j + 6, k + 7, 4);
        world.setBlock(i + 9, j + 6, k + 6, 17);
        world.setBlock(i + 9, j + 6, k + 5, 5);
        world.setBlock(i + 9, j + 6, k + 4, 17);
        world.setBlock(i + 9, j + 6, k + 3, 4);
        world.setBlock(i + 1, j + 7, k + 6, 4);
        world.setBlock(i + 1, j + 7, k + 5, 17);
        world.setBlock(i + 1, j + 7, k + 4, 4);
        world.setBlock(i + 2, j + 7, k + 6, 4);
        world.setBlock(i + 2, j + 7, k + 5, 0);
        world.setBlock(i + 2, j + 7, k + 4, 4);
        world.setBlock(i + 3, j + 7, k + 6, 4);
        world.setBlock(i + 3, j + 7, k + 5, 0);
        world.setBlock(i + 3, j + 7, k + 4, 4);
        world.setBlock(i + 4, j + 7, k + 6, 4);
        world.setBlock(i + 4, j + 7, k + 5, 0);
        world.setBlock(i + 4, j + 7, k + 4, 4);
        world.setBlock(i + 5, j + 7, k + 6, 4);
        world.setBlock(i + 5, j + 7, k + 5, 0);
        world.setBlock(i + 5, j + 7, k + 4, 4);
        world.setBlock(i + 6, j + 7, k + 6, 4);
        world.setBlock(i + 6, j + 7, k + 5, 0);
        world.setBlock(i + 6, j + 7, k + 4, 4);
        world.setBlock(i + 7, j + 7, k + 6, 4);
        world.setBlock(i + 7, j + 7, k + 5, 0);
        world.setBlock(i + 7, j + 7, k + 4, 4);
        world.setBlock(i + 8, j + 7, k + 6, 4);
        world.setBlock(i + 8, j + 7, k + 5, 0);
        world.setBlock(i + 8, j + 7, k + 4, 4);
        world.setBlock(i + 9, j + 7, k + 6, 4);
        world.setBlock(i + 9, j + 7, k + 5, 17);
        world.setBlock(i + 9, j + 7, k + 4, 4);
        world.setBlock(i + 1, j + 8, k + 5, 4);
        world.setBlock(i + 2, j + 8, k + 5, 4);
        world.setBlock(i + 3, j + 8, k + 5, 4);
        world.setBlock(i + 4, j + 8, k + 5, 4);
        world.setBlock(i + 5, j + 8, k + 5, 4);
        world.setBlock(i + 6, j + 8, k + 5, 4);
        world.setBlock(i + 7, j + 8, k + 5, 4);
        world.setBlock(i + 8, j + 8, k + 5, 4);
        world.setBlock(i + 9, j + 8, k + 5, 4);
        return true;
    }
}