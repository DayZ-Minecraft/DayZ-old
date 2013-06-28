package dayz.client;

import net.minecraft.client.audio.SoundManager;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class ClientEvents 
{
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

}
