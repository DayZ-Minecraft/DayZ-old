package dayz.client;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import dayz.DayZ;

public class ClientEvents
{
    @ForgeSubscribe
    public void onSoundsLoaded(SoundLoadEvent event)
    {
        String[] soundFiles =
        { "ak74", "ak74u", "makarov", "remington", "reload", "leeenfield", "glock", "dbshotgun", "usp" };
        for (String soundFile : soundFiles)
        {
            event.manager.addSound(DayZ.meta.modId + ":" + soundFile + ".ogg");
        }
    }
}