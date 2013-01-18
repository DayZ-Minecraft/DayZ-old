package dayz.common.blocks;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockFence extends BlockPane
{
    private int sideTextureIndex;
	private boolean canDropItself;

	public BlockFence(int par1, int par2, int par3, Material par4Material, boolean par5)
    {
        super(par1, par2, par3, par4Material, par5);
        this.sideTextureIndex = par3;
        this.canDropItself = par5;
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }
    
    @Override
    public String getTextureFile()
    {
        return "/dayz/images/terrain.png";
    }
}
