package dayz.common.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemAmmo extends Item
{
    private int textureIndex;

    public ItemAmmo(int i, int j)
    {
        super(i);
        textureIndex = j;
        maxStackSize = 1;
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        switch (textureIndex)
        {
            case 0:
                itemIcon = par1IconRegister.registerIcon("dayz:ak74uammo");
                return;
            case 1:
                itemIcon = par1IconRegister.registerIcon("dayz:makarovammo");
                return;
            case 2:
                itemIcon = par1IconRegister.registerIcon("dayz:remingtonammo");
                return;
            case 3:
                itemIcon = par1IconRegister.registerIcon("dayz:leeenfieldammo");
                return;
            case 4:
                itemIcon = par1IconRegister.registerIcon("dayz:g17ammo");
                return;
            case 5:
                itemIcon = par1IconRegister.registerIcon("dayz:dbshotammo");
                return;
        }
    }
}
