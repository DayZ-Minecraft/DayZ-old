package dayz.common.items;

import net.minecraft.src.Item;

public class ItemEmptyCan extends Item
{
    public ItemEmptyCan(int i)
    {
        super(i);
        maxStackSize = 1;
    }
    
    @Override
    public String getTextureFile()
    {
        return "/dayz/images/food.png";
    }
}
