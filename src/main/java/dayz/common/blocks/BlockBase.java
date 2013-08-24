package dayz.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockBase extends Block
{
    public BlockBase(int par1, Material par3Material)
    {
        super(par1, par3Material);
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        blockIcon = par1IconRegister.registerIcon("dayz:sandbag");
    }
}
