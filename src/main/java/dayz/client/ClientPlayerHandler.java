package dayz.client;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.relauncher.Side;
import dayz.DayZ;
import dayz.common.misc.ChatHandler;
import dayz.common.misc.Config;
import dayz.common.misc.Util;

public class ClientPlayerHandler implements IPlayerTracker
{
    @Override
    public void onPlayerLogin(EntityPlayer entityPlayer)
    {
        if (DayZ.isUpToDate == false && Config.canCheckUpdate == true && FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            ChatHandler.chatError(entityPlayer, "Day Z is not up to date. The latest release is " + Util.WEBVERSION + ". You have " + Util.VERSION + ".");
        }
    }

    @Override
    public void onPlayerLogout(EntityPlayer entityPlayer)
    {

    }

    @Override
    public void onPlayerChangedDimension(EntityPlayer entityPlayer)
    {

    }

    @Override
    public void onPlayerRespawn(EntityPlayer entityPlayer)
    {

    }
}
