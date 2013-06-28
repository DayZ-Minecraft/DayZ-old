package dayz.client;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import dayz.DayZ;
import dayz.common.entities.EntityCrawler;
import dayz.common.entities.EntityZombieDayZ;
import dayz.common.misc.Updater;
import dayz.common.misc.Util;
import dayz.common.playerdata.PlayerStats;

public class ClientTickHandler implements ITickHandler 
{
	public static float playerRecoil;
	public static float antiRecoil;
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) 
	{

	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) 
	{
		if (type.equals(EnumSet.of(TickType.RENDER))) 
		{
			onRenderTick(type, tickData);
		} 
	}

	@Override
	public EnumSet<TickType> ticks() 
	{
        return EnumSet.of(TickType.RENDER, TickType.CLIENT);
	}

	@Override
	public String getLabel() 
	{
		return "DayZClient";
	}
	
	private void onTickInGame() 
	{
    	Minecraft mc = Minecraft.getMinecraft();

		if (playerRecoil > 0)
		{
			playerRecoil *= 0.8F;
		}
		mc.thePlayer.rotationPitch -= playerRecoil;
		antiRecoil += playerRecoil;

		mc.thePlayer.rotationPitch += antiRecoil * 0.2F;
		antiRecoil *= 0.8F;
	}
	
	public void onRenderTick(EnumSet<TickType> type, Object... tickData)
    {
    	Minecraft mc = Minecraft.getMinecraft();
    	ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        int i = scaledresolution.getScaledWidth();
        int j = scaledresolution.getScaledHeight();
        
    	if (DayZ.canShowDebugScreen == true)
    	{
	        if (mc.inGameHasFocus/* && mc.isGuiEnabled()*/)
	        {    			
	            int zombies = (mc.theWorld.countEntities(EntityZombieDayZ.class) + (mc.theWorld.countEntities(EntityCrawler.class)));
	            if (DayZ.canShowNameOnDebugScreen == true)
	            {
		            FMLClientHandler.instance().getClient().fontRenderer.drawString("Name: " + mc.thePlayer.username, i - 110, 18, 0xffffff);
	            }
	            FMLClientHandler.instance().getClient().fontRenderer.drawString("Blood: " + (mc.thePlayer.getHealth() * 600), i - 110, 28, 0xffffff);
	            FMLClientHandler.instance().getClient().fontRenderer.drawString("Zombies: " + zombies, i - 110, 38, 0xffffff);
	            FMLClientHandler.instance().getClient().fontRenderer.drawString("Zombies killed: " + PlayerStats.getPlayerData(mc.thePlayer).totalZombieKills, i - 110, 48, 0xffffff);
	            FMLClientHandler.instance().getClient().fontRenderer.drawString("Players killed: " + PlayerStats.getPlayerData(mc.thePlayer).totalPlayerKills, i - 110, 58, 0xffffff);
	            FMLClientHandler.instance().getClient().fontRenderer.drawString("Thirst: " + PlayerStats.getPlayerData(mc.thePlayer.username).thirstLevel, i - 110, 68, 0xffffff);
	            FMLClientHandler.instance().getClient().fontRenderer.drawString("Version: " + Util.VERSION + Updater.preRelease(), i - 110, 78, 0xffffff);
	            if (DayZ.canShowCoordinatesOnDebugScreen == true)
	            {
		            FMLClientHandler.instance().getClient().fontRenderer.drawString("Coords: " + (int)mc.thePlayer.posX + ", " + (int)mc.thePlayer.posZ, i - 110, 88, 0xffffff);
	            }
	        }
    	}
    }
}
