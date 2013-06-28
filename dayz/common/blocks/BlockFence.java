package dayz.common.blocks;

import dayz.common.misc.Util;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class BlockFence extends BlockPane
{
	private boolean canDropItself;

	public BlockFence(int par1, String par2, String par3, Material par4Material, boolean par5)
    {
        super(par1, ("dayz:chainfence"), ("dayz:chainfence"), par4Material, par5);
        this.canDropItself = par5;
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }
}
