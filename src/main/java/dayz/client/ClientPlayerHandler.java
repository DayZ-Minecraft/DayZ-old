package dayz.client;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;
import dayz.DayZ;
import dayz.common.misc.ChatHandler;
import dayz.common.misc.Updater;
import dayz.common.misc.Util;

public class ClientPlayerHandler implements IPlayerTracker
{
    @Override
    public void onPlayerLogin(EntityPlayer player)
    {
        if (DayZ.isUpToDate == false && DayZ.canCheckUpdate == true)
        {
            ChatHandler.chatError(player, "Day Z is not up to date. The latest release is " + Util.WEBVERSION + ". You have " + Util.VERSION + Updater.preRelease() + ".");
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
