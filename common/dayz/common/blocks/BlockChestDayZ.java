package dayz.common.blocks;

import net.minecraft.block.BlockChest;
import dayz.DayZ;

public class BlockChestDayZ extends BlockChest
{
    public BlockChestDayZ(int blockId, int isTrapped)
    {
        super(blockId, isTrapped);
        setBlockUnbreakable();
        setBlockBounds(0.0625F, 0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        setCreativeTab(DayZ.creativeTab);
    }
}