package dayz.common;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;
import dayz.common.playerdata.PlayerStats;

public class CommonPlayerHandler implements IPlayerTracker
{
	@Override
	public void onPlayerLogin(EntityPlayer player) 
	{
		PlayerStats.loadData(player);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) 
	{
		PlayerStats.saveData(player);
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) 
	{
		
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) 
	{
		
	}
}
