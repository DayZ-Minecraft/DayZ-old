package dayz.common.items.weapons;

import net.minecraft.item.Item;
import dayz.common.items.Items;

public class ItemGlock17 implements IGun
{
    @Override
    public int getRounds()
    {
        return 17;
    }

    @Override
    public int getDamage()
    {
        return 6;
    }

    @Override
    public String getReloadSound()
    {
        return "reload";
    }

    @Override
    public String getShootSound()
    {
        return "glock";
    }

    @Override
    public Item getAmmo()
    {
        return Items.ammoGlock17;
    }
}