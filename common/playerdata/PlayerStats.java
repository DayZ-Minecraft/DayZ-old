package dayz.common.playerdata;

import java.util.HashMap;

import dayz.DayZ;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerStats
{
	private static HashMap<String, PlayerStats> playerDataMap = new HashMap<String, PlayerStats>();

	public static PlayerStats getPlayerData(EntityPlayer player)
	{
		PlayerStats info = playerDataMap.get(player.username);

		if (info == null)
		{
			info = new PlayerStats(player);
			playerDataMap.put(player.username, info);
		}
		return info;
	}
	
	public static PlayerStats getPlayerData(String username)
	{
		PlayerStats info = playerDataMap.get(username);
		return info;
	}

	public int totalPlayerKills;
	public int totalZombieKills;
	public int thirstLevel;
	
	private PlayerStats(EntityPlayer player)
	{
		totalPlayerKills = 0;
		totalZombieKills = 0;
		thirstLevel = 0;
	}
	
	public static void saveData(EntityPlayer player)
	{
		NBTTagCompound dayzNBT = player.getEntityData();
		PlayerStats stats = PlayerStats.getPlayerData(player);
		
		dayzNBT.setInteger("totalPlayerKills" + player.username, stats.totalPlayerKills);
		dayzNBT.setInteger("totalZombieKills" + player.username, stats.totalZombieKills);
		dayzNBT.setInteger("thirstLevel" + player.username, stats.thirstLevel);
	}
	
	public static void loadData(EntityPlayer player)
	{
		NBTTagCompound dayzNBT = player.getEntityData();
		PlayerStats stats = PlayerStats.getPlayerData(player);

		stats.totalPlayerKills = dayzNBT.getInteger("totalPlayerKills" + player.username);
		stats.totalZombieKills = dayzNBT.getInteger("totalZombieKills" + player.username);
		stats.thirstLevel = dayzNBT.getInteger("thirstLevel" + player.username);
	}
	
	public static void addThirst(EntityPlayer player, int addAmount)
	{
		PlayerStats stats = PlayerStats.getPlayerData(player);

		stats.thirstLevel = stats.thirstLevel + addAmount;
		PlayerStats.saveData(player);
	}
	
	public static void subtractThirst(EntityPlayer player, int subtractAmount)
	{
		PlayerStats stats = PlayerStats.getPlayerData(player);
		
		if (stats.thirstLevel >= 6000)
		{
			stats.thirstLevel = stats.thirstLevel - subtractAmount;
		}
		else
		{
			stats.thirstLevel = 0;
		}
		
		PlayerStats.saveData(player);
	}
}
