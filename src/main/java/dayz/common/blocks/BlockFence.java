package dayz.common.blocks;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import dayz.DayZ;
import dayz.common.misc.Util;

public class BlockFence extends BlockPane
{
    public BlockFence(int blockId, String texture, Material material)
    {
        super(blockId, Util.ID + ":" + texture.substring(texture.indexOf(".") + 1), Util.ID + ":" + texture.substring(texture.indexOf(".") + 1), material, false);
        setCreativeTab(DayZ.creativeTab);
    }
}