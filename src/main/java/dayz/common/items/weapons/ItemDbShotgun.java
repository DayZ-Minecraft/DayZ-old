package dayz.common.items.weapons;

import net.minecraft.item.Item;
import dayz.common.items.Items;

public class ItemDbShotgun implements IGun
{
    @Override
    public int getRounds()
    {
        return 1;
    }

    @Override
    public int getDamage()
    {
        return 20;
    }

    @Override
    public String getReloadSound()
    {
        return "reload";
    }

    @Override
    public String getShootSound()
    {
        return "dbshotgun";
    }

    @Override
    public Item getAmmo()
    {
        return Items.ammoDoubleBarreledShotgun;
    }
}