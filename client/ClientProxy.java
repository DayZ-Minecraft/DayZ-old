package dayz.client;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModelBiped;
import net.minecraft.src.RenderBiped;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.SoundManager;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import dayz.DayZ;
import dayz.Updater;
import dayz.Util;
import dayz.client.entities.ModelCrawler;
import dayz.client.entities.ModelZombieDayZ;
import dayz.client.entities.RenderBullet;
import dayz.client.entities.RenderCrawler;
import dayz.client.entities.RenderGrenade;
import dayz.common.entities.EntityBandit;
import dayz.common.entities.EntityBullet;
import dayz.common.entities.EntityCrawler;
import dayz.common.entities.EntityGrenade;
import dayz.common.entities.EntityZombieDayZ;

public class ClientProxy implements ITickHandler, IPlayerTracker
{	
	public static void DayZpreload(FMLPreInitializationEvent event) 
	{

	}

	public static void DayZload(FMLInitializationEvent event) 
	{
        RenderingRegistry.registerEntityRenderingHandler(EntityZombieDayZ.class, new RenderBiped(new ModelZombieDayZ(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityCrawler.class, new RenderCrawler(new ModelCrawler(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityBandit.class, new RenderBiped(new ModelBiped(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderBullet());
        RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderGrenade());
        TickRegistry.registerTickHandler(new ClientProxy(), Side.CLIENT);
        KeyBindingRegistry.registerKeyBinding(new KeyHandlerDayZ());
        GameRegistry.registerPlayerTracker(new ClientProxy());
	}
	
	public static void DayZpostload(FMLPostInitializationEvent event) 
	{
		
	}
	
	@ForgeSubscribe
	public void onSoundsLoaded(SoundLoadEvent event)
	{
		SoundManager manager = event.manager;
		String [] soundFiles = 
		{
			"ak74u.ogg", "makarov.ogg", "remington.ogg", "reload.ogg", "leeenfield.ogg", "glock.ogg", "dbshotgun.ogg"
	   	};
		for (int i = 0; i < soundFiles.length; i++)
	    {
			manager.soundPoolSounds.addSound(soundFiles[i], this.getClass().getResource("/dayz/sounds/" + soundFiles[i]));
	    }
	}

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
		return "DayZ";
	}
	
	public void onRenderTick(EnumSet<TickType> type, Object... tickData)
    {
    	Minecraft mc = Minecraft.getMinecraft();
    	ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        int i = scaledresolution.getScaledWidth();
        int j = scaledresolution.getScaledHeight();
        
    	if (DayZ.canShowDebugScreen == true)
    	{
	        if (mc.inGameHasFocus && mc.isGuiEnabled())
	        {
	            int zombies = (mc.theWorld.countEntities(EntityZombieDayZ.class) + (mc.theWorld.countEntities(EntityCrawler.class)));
	            if (DayZ.canShowNameOnDebugScreen == true)
	            {
		            FMLClientHandler.instance().getClient().fontRenderer.drawString("Name: " + mc.thePlayer.username, i - 110, 18, 0xffffff);
	            }
	            FMLClientHandler.instance().getClient().fontRenderer.drawString("Blood: " + (mc.thePlayer.getHealth() * 600), i - 110, 28, 0xffffff);
	            FMLClientHandler.instance().getClient().fontRenderer.drawString("Zombies: " + zombies, i - 110, 38, 0xffffff);
	            FMLClientHandler.instance().getClient().fontRenderer.drawString("Version: " + Util.VERSION + Updater.preRelease(), i - 110, 48, 0xffffff);
	            if (DayZ.canShowCoordinatesOnDebugScreen == true)
	            {
		            FMLClientHandler.instance().getClient().fontRenderer.drawString("Coords: " + (int)mc.thePlayer.posX + ", " + (int)mc.thePlayer.posZ, i - 110, 58, 0xffffff);
	            }
	        }
    	}
    }

	@Override
	public void onPlayerLogin(EntityPlayer player) 
	{
		if (DayZ.isUpToDate == false && DayZ.canCheckUpdate == true)
        {
			player.sendChatToPlayer("Day Z is not up to date. The latest release is " + Updater.getWebVersion() + ". You have " + Util.VERSION + Updater.preRelease());
        }
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) 
	{
		
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