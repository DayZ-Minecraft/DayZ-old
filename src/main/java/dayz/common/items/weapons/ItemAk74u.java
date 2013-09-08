package dayz.common.items.weapons;

import net.minecraft.item.Item;
import dayz.common.items.Items;

public class ItemAk74u implements IGun
{
    @Override
    public int getRounds()
    {
        return 30;
    }

    @Override
    public int getDamage()
    {
        return 10;
    }

    @Override
    public String getReloadSound()
    {
        return "reload";
    }

    @Override
    public String getShootSound()
    {
        return "ak74u";
    }

    @Override
    public Item getAmmo()
    {
        return Items.ammoAk74u;
    }
}