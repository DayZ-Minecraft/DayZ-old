package dayz.common.items.weapons;

import net.minecraft.item.Item;

public interface IGun
{
    int getRounds();

    int getDamage();

    String getReloadSound();

    String getShootSound();

    Item getAmmo();
}
