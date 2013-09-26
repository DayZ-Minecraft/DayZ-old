package dayz.common.items.weapons;

import net.minecraft.item.Item;
import dayz.common.items.Items;

public class ItemAk74 implements IGun
{
    @Override
    public int getRounds()
    {
        return 32;
    }

    @Override
    public int getDamage()
    {
        return 12;
    }

    @Override
    public String getReloadSound()
    {
        return "reload";
    }

    @Override
    public String getShootSound()
    {
        return "ak74";
    }

    @Override
    public Item getAmmo()
    {
    	return null;
        //return Items.ammoAk74;
    }
}