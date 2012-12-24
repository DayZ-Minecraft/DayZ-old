package dayz.common.playerdata;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerData
{
	private static HashMap<String, PlayerData> playerDataMap = new HashMap<String, PlayerData>();

	public static PlayerData getPlayerData(EntityPlayer player)
	{
		PlayerData info = playerDataMap.get(player.username);

		if (info == null)
		{
			info = new PlayerData(player);
			playerDataMap.put(player.username, info);
		}
		return info;
	}
	
	public static PlayerData getPlayerData(String username)
	{
		PlayerData info = playerDataMap.get(username);
		return info;
	}

	public int totalPlayerKills;
	public int totalZombieKills;
	public int thirstLevel;
	
	private PlayerData(EntityPlayer player)
	{

	}
	
	public static void saveData(EntityPlayer player)
	{
		NBTTagCompound dayzNBT = player.getEntityData();
		dayzNBT.setInteger("DayZ_totalPlayerKills-" + player.username, PlayerData.getPlayerData(player).totalPlayerKills);
		dayzNBT.setInteger("DayZ_totalZombieKills-" + player.username, PlayerData.getPlayerData(player).totalZombieKills);
		dayzNBT.setInteger("DayZ_thirstLevel-" + player.username, PlayerData.getPlayerData(player).thirstLevel);
	}
	
	public static void loadData(EntityPlayer player)
	{
		PlayerData.doesPlayerDataExist(player);
		NBTTagCompound dayzNBT = player.getEntityData();
		PlayerData.getPlayerData(player).totalPlayerKills = dayzNBT.getInteger("DayZ_totalPlayerKills-" + player.username);
		PlayerData.getPlayerData(player).totalZombieKills = dayzNBT.getInteger("DayZ_totalZombieKills-" + player.username);
		PlayerData.getPlayerData(player).thirstLevel = dayzNBT.getInteger("DayZ_thirstLevel-" + player.username);
	}
	
	public static void doesPlayerDataExist(EntityPlayer player)
	{
		if (!(PlayerData.getPlayerData(player).totalPlayerKills <= 0 || PlayerData.getPlayerData(player).totalPlayerKills >= 0))
		{
			PlayerData.getPlayerData(player).totalPlayerKills = 0;			
			PlayerData.getPlayerData(player).totalZombieKills = 0;
			PlayerData.getPlayerData(player).thirstLevel = 0;
			PlayerData.saveData(player);
		}
	}
	
	public static void addThirst(EntityPlayer player, int addAmount)
	{
		PlayerData.getPlayerData(player).thirstLevel = PlayerData.getPlayerData(player).thirstLevel + addAmount;
		PlayerData.saveData(player);
	}
	
	public static void subtractThirst(EntityPlayer player, int subtractAmount)
	{
		PlayerData.getPlayerData(player).thirstLevel = PlayerData.getPlayerData(player).thirstLevel - subtractAmount;
		PlayerData.saveData(player);
	}
}
