package dayz.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block 
{
	public BlockBase(int par1, int par2, Material par3Material) 
	{
		super(par1, par2, par3Material);
	}

    @Override
    public String getTextureFile()
    {
        return "/dayz/images/terrain.png";
    }
}
