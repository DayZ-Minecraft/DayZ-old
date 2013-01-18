package dayz.common;

import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import dayz.common.entities.EntityCrawler;
import dayz.common.entities.EntityZombieDayZ;
import dayz.common.playerdata.PlayerStats;

public class CommonEvents 
{
	@ForgeSubscribe
	public void playerKilledEntity(LivingDeathEvent event)
	{
		if (event.source.getEntity() instanceof EntityPlayer)
		{
			if (event.entityLiving instanceof EntityZombieDayZ)
			{
				int totalZombieKills = PlayerStats.getPlayerData((EntityPlayer)event.source.getEntity()).totalZombieKills;
				PlayerStats.getPlayerData((EntityPlayer)event.source.getEntity()).totalZombieKills = totalZombieKills + 1;
			}
			if (event.entityLiving instanceof EntityCrawler)
			{
				int totalZombieKills = PlayerStats.getPlayerData((EntityPlayer)event.source.getEntity()).totalZombieKills;
				PlayerStats.getPlayerData((EntityPlayer)event.source.getEntity()).totalZombieKills = totalZombieKills + 1;
			}
			if (event.entityLiving instanceof EntityPlayer)
			{
				int totalPlayerKills = PlayerStats.getPlayerData((EntityPlayer)event.source.getEntity()).totalPlayerKills;
				PlayerStats.getPlayerData((EntityPlayer)event.source.getEntity()).totalPlayerKills = totalPlayerKills + 1;
			}
		}
	}
}
