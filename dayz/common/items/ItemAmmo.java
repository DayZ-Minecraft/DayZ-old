package dayz.common.items;

import java.awt.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import dayz.common.misc.Util;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public class ItemAmmo extends Item
{
	private int textureIndex;
    public ItemAmmo(int i, int j)
    {
        super(i);
        textureIndex = j;
        maxStackSize = 1;
    }

    public void registerIcons(IconRegister par1IconRegister)
    {
    	switch(this.textureIndex)
    	{
    		case 0: this.itemIcon = par1IconRegister.registerIcon("dayz:ak74uammo"); return;
    		case 1: this.itemIcon = par1IconRegister.registerIcon("dayz:makarovammo"); return;
    		case 2: this.itemIcon = par1IconRegister.registerIcon("dayz:remingtonammo"); return;
    		case 3: this.itemIcon = par1IconRegister.registerIcon("dayz:leeenfieldammo"); return;
    		case 4: this.itemIcon = par1IconRegister.registerIcon("dayz:g17ammo"); return;
    		case 5: this.itemIcon = par1IconRegister.registerIcon("dayz:dbshotammo");return;
    	}
    }
}
