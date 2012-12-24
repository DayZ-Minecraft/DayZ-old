package dayz.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import dayz.DayZ;

public class CreativeTabDayZ extends CreativeTabs
{
	public CreativeTabDayZ()
	{
		super("creativeTabDayZ");
	}

	@Override
	public ItemStack getIconItemStack()
	{
		return new ItemStack(DayZ.ak74u, 1, 0);
	}
}