package dayz.common.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Updater
{
	public static double webVersion;
	
	public static boolean isUpdated() 
	{
		double i = Double.parseDouble(Util.WEBVERSION);
		double j = Double.parseDouble(Util.VERSION);
		if (i > j)
		{
			return false;
		}
		else if (Util.ISPRERELEASE && (i == j))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	private static String getUpdateURL() 
	{
		return "https://dl.dropbox.com/u/45513981/Day%20Z/LatestVersion.txt";
	}
	
	public static void getWebVersion() 
	{
        DayZLog.info("Checking for update...");
		try 
		{
			URL url = new URL(getUpdateURL());
			BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()));
			String s = r.readLine();
			r.close();
			Util.WEBVERSION = s;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static String preRelease()
	{
		if (Util.ISPRERELEASE)
		{
			return "pre";
		}
		else
		{
			return "";
		}
	}
}