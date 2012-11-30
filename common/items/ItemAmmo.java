package dayz.common.items;

import net.minecraft.src.Item;

public class ItemAmmo extends Item
{
    public ItemAmmo(int i)
    {
        super(i);
        maxStackSize = 1;
    }

    @Override
    public String getTextureFile()
    {
        return "/dayz/images/weapons.png";
    }
}
