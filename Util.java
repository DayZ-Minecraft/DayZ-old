package dayz;

import java.util.ArrayList;

import net.minecraft.world.gen.structure.StructureVillagePieceWeight;
import dayz.common.world.ComponentVillageHallDayZ;
import dayz.common.world.ComponentVillageHouse1DayZ;
import dayz.common.world.ComponentVillageHouse2DayZ;

public class Util
{
	public static final String ID = "Day Z Minecraft";
	public static final String NAME = "Day Z Minecraft";
	public static final String VERSION = "7.1";
	public static final boolean ISPRERELEASE = true;
	public static final String MCVERSION = "1.4.5";
	public static final String DESC = "An unknown infection has wiped out most of the world's population. Go Solo or team up with friends to take on the world as you choose your path in this brutal and chilling landscape using whatever means you stumble upon to survive.";
	public static final String[] AUTHORLIST = {"1Jamster1", "algernon93", "creater admin", "dark3304", "ElTrazz", "HoBoS_TaCo", "Monstaboy13", "Nitrojoe20"};
	public static final String URL = "http://www.minecraftforum.net/topic/1304383-";
	
    public static ArrayList piecesToAdd()
    {
        ArrayList parts = new ArrayList();
        parts.add(new StructureVillagePieceWeight(ComponentVillageHouse1DayZ.class, 1, 10));
        parts.add(new StructureVillagePieceWeight(ComponentVillageHallDayZ.class, 1, 10));
        parts.add(new StructureVillagePieceWeight(ComponentVillageHouse2DayZ.class, 1, 10));
        return parts;
    } 
}