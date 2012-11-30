package dayz.common.external;

import java.util.Random;

import dayz.DayZLog;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import tarun1998.thirstmod.ConfigHelper;
import tarun1998.thirstmod.utils.ThirstUtils;

public class ThirstModHooks
{
	public final static void addThirst(EntityPlayer entityplayer, int thirstReplenish, float saturationReplenish)
	{
		try
		{
			ThirstUtils.getUtilsFor(entityplayer.username).getStats().addStats(thirstReplenish, saturationReplenish);
		}
		catch(Exception e)
		{
			DayZLog.severe("ThirstUtils class not found for " + entityplayer.username, e);
		}
	}
}
