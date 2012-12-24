package dayz.common.playerdata;

import net.minecraft.entity.player.EntityPlayer;

public class Thirst 
{	
	public static PlayerData get(EntityPlayer player)
	{
		return PlayerData.getPlayerData(player);
	}
	
	public static PlayerData get(String username)
	{
		return PlayerData.getPlayerData(username);
	}
	
	public static int getLevel(EntityPlayer player)
	{
		return PlayerData.getPlayerData(player).thirstLevel;
	}
	
	public static void addThirst(EntityPlayer player, int addAmount)
	{
		PlayerData.addThirst(player, addAmount);
	}
	
	public static void subtractThirst(EntityPlayer player, int subtractAmount)
	{
		if (Thirst.getLevel(player) < subtractAmount)
		{
			Thirst.resetThirst(player);
		}
		else
		{
			PlayerData.subtractThirst(player, subtractAmount);
		}
	}
	
	public static void resetThirst(EntityPlayer player)
	{
		PlayerData.getPlayerData(player).thirstLevel = 0;
	}
}
