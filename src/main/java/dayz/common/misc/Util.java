package dayz.common.misc;

import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;

public class Util
{
    public static final String ID = "DayZ";
    public static final String NAME = "Day Z Minecraft";
    public static final String VERSION = "7.1";
    public static String WEBVERSION;
    public static final boolean ISPRERELEASE = false;
    public static final String MCVERSION = "1.6.2";
    public static final String DESC = "An unknown infection has wiped out most of the world's population. Go Solo or team up with friends to take on the world as you choose your path in this brutal and chilling landscape using whatever means you stumble upon to survive.";
    public static final List<String> AUTHORLIST = Arrays.asList("1Jamster1", "algernon93", "creater admin", "dark3304", "ElTrazz", "HoBoS_TaCo", "Monstaboy13", "Nitrojoe20");
    public static final String URL = "http://www.minecraftforum.net/topic/1304383-";

    public static float getHealth(EntityLivingBase entity)
    {
        return entity.func_110143_aJ();
    }

    public static float getMaxHealth(EntityLivingBase entity)
    {
        return entity.func_110138_aP();
    }
}