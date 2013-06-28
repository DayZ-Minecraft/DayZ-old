package dayz.common.items;

import dayz.common.misc.Util;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemEmptyCan extends Item
{
    public ItemEmptyCan(int i)
    {
        super(i);
        maxStackSize = 1;
    }
    
    public void registerIcons(IconRegister par1IconRegister)
    {
    	this.itemIcon = par1IconRegister.registerIcon("dayz:emptycan");
    }
}
