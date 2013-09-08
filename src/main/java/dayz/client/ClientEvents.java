package dayz.client;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import dayz.common.misc.Util;

public class ClientEvents
{
    @ForgeSubscribe
    public void onSoundsLoaded(SoundLoadEvent event)
    {
        String[] soundFiles =
        { "ak74u", "makarov", "remington", "reload", "leeenfield", "glock", "dbshotgun" };
        for (String soundFile : soundFiles)
        {
            event.manager.addSound(Util.ID + ":" + soundFile + ".ogg");
        }
    }
}