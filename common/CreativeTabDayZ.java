package dayz.common;

import dayz.DayZ;
import net.minecraft.src.*;

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