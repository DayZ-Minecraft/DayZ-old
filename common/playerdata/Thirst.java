package dayz.common.playerdata;

import net.minecraft.entity.player.EntityPlayer;

public class Thirst 
{	
	public static PlayerStats get(EntityPlayer player)
	{
		return PlayerStats.getPlayerData(player);
	}

	public static PlayerStats get(String username)
	{
		return PlayerStats.getPlayerData(username);
	}

	public static int getLevel(EntityPlayer player)
	{
		return PlayerStats.getPlayerData(player).thirstLevel;
	}

	public static void addThirst(EntityPlayer player, int addAmount)
	{
		PlayerStats.addThirst(player, addAmount);
	}

	public static void subtractThirst(EntityPlayer player, int subtractAmount)
	{
		if (Thirst.getLevel(player) < subtractAmount)
		{
			Thirst.resetThirst(player);
		}
		else
		{
			PlayerStats.subtractThirst(player, subtractAmount);
		}
	}

	public static void resetThirst(EntityPlayer player)
	{
		PlayerStats.getPlayerData(player).thirstLevel = 0;
	}
}