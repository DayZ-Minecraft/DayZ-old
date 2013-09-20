package dayz.common.blocks;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import dayz.DayZ;
import dayz.common.misc.Constants;

public class BlockFence extends BlockPane
{
    public BlockFence(int blockId, String texture, Material material)
    {
        super(blockId, Constants.ID + ":" + texture.substring(texture.indexOf(".") + 1), Constants.ID + ":" + texture.substring(texture.indexOf(".") + 1), material, false);
        setCreativeTab(DayZ.creativeTab);
    }
}