package dayz.common.items.weapons;

import net.minecraft.item.Item;
import dayz.common.items.Items;

public class ItemEnfield implements IGun
{
    @Override
    public int getRounds()
    {
        return 10;
    }

    @Override
    public int getDamage()
    {
        return 8;
    }

    @Override
    public String getReloadSound()
    {
        return "reload";
    }

    @Override
    public String getShootSound()
    {
        return "leeenfield";
    }

    @Override
    public Item getAmmo()
    {
        return Items.ammoLeeEnfield;
    }
}