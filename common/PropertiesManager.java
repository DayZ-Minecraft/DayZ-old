package dayz.common;

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
	
    public static void setbarbedwireID(int par1)
    {
    	DayZ.barbedwireID = par1;
    } 
    
    public static void setdayzchestallID(int par1)
    {
    	DayZ.dayzchestallID = par1;
    } 
    
    public static void setdayzchestrareID(int par1)
    {
    	DayZ.dayzchestrareID = par1;
    } 
    
    public static void setdayzchestcommonID(int par1)
    {
    	DayZ.dayzchestcommonID = par1;
    } 
    
    public static void setchainlinkfenceID(int par1)
    {
    	DayZ.chainlinkfenceID = par1;
    } 
    
    public static void setsandbagblockID(int par1)
    {
    	DayZ.sandbagblockID = par1;
    } 
    
    public static void setnailsID(int par1)
    {
    	DayZ.nailsID = par1;
    } 
    
    public static void setcanSpawnZombiesInDefaultWorld(boolean par1)
    {
    	DayZ.canSpawnZombiesInDefaultWorld = par1;
    } 
}
