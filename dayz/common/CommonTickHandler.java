package dayz.common;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import dayz.common.misc.DayZDamageSource;
import dayz.common.playerdata.Thirst;

public class CommonTickHandler implements ITickHandler 
{
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) 
	{
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) 
	{
		onGameTick();
		if (type.equals(EnumSet.of(TickType.PLAYER)))
		{
			onPlayerTick((EntityPlayer)tickData[0]);
		}	
	}

	@Override
	public EnumSet<TickType> ticks() 
	{
        return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel() 
	{
		return "DayZ Thirst";
	}
	
	private void onPlayerTick(EntityPlayer player) 
	{
		if (Thirst.getLevel(player) == 20000)
		{
			player.sendChatToPlayer("I should find some water...");
			Thirst.addThirst(player, 1);
		}
		else if (Thirst.getLevel(player) >= 24000)
		{
			player.attackEntityFrom(DayZDamageSource.thirstDeath, 20);
		} 
		else if (player.isSprinting())
		{
			Thirst.addThirst(player, 2);
		}
		else if (player.isJumping)
		{
			Thirst.addThirst(player, 2);
		}
		else if (player.isDead)
		{
			return;
		}
		else
		{
			Thirst.addThirst(player, 1);
		}
	}
	
	private void onGameTick()
	{
		
	}
}
