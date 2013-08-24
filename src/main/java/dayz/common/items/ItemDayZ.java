package dayz.common.items;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.LanguageRegistry;
import dayz.DayZ;

public class ItemDayZ extends Item
{
    public ItemDayZ(int par1, String name, String code, int itemWorth)
    {
        super(par1);
        maxStackSize = 1;
        LanguageRegistry.instance().addStringLocalization("item." + code.toLowerCase(), name);
        setCreativeTab(DayZ.creativeTabDayZ);
    }
}
