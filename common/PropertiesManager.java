package dayz.common;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;
import java.util.logging.Level;

import net.minecraft.src.CryptManager;
import net.minecraft.src.DedicatedPlayerList;
import net.minecraft.src.DedicatedServerListenThread;
import net.minecraft.src.EnumGameType;
import net.minecraft.src.MathHelper;
import net.minecraft.src.PropertyManager;
import net.minecraft.src.RConThreadMain;
import net.minecraft.src.RConThreadQuery;
import net.minecraft.src.WorldSettings;
import net.minecraft.src.WorldType;
import cpw.mods.fml.common.FMLCommonHandler;
import dayz.DayZ;

public class PropertiesManager 
{
    public static void setCanCheckUpdate(boolean par1)
    {
        DayZ.canCheckUpdate = par1;
    }
    
    public static void setCanShowDebugScreen(boolean par1)
    {
    	DayZ.canShowDebugScreen = par1;
    }
    
    public static void setCanShowNameOnDebugScreen(boolean par1)
    {
    	DayZ.canShowNameOnDebugScreen = par1;
    }
    
    public static void setCanShowCoordinatesOnDebugScreen(boolean par1)
    {
    	DayZ.canShowCoordinatesOnDebugScreen = par1;
    }
    
    public static void setChanceToRegenChestContents(int par1)
    {
    	DayZ.chanceToRegenChestContents = par1;
    }
    
    public static void setCanGenerateExplosives(boolean par1)
    {
    	DayZ.canGenerateExplosives = par1;
    }   
}
