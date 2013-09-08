package dayz.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import dayz.DayZ;
import dayz.common.misc.Util;

public class BlockMod extends Block
{
    public BlockMod(int blockId, Material material)
    {
        super(blockId, material);
        setCreativeTab(DayZ.creativeTab);
    }

    @Override
    public void registerIcons(IconRegister register)
    {
        blockIcon = register.registerIcon(Util.ID + ":" + getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1));
    }
}
