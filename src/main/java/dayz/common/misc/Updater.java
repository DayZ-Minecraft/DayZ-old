package dayz.common.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Updater
{
    public static double webVersion;

    /**
     * Check this instead of using the internet to check status.
     * Set by CommonProxy.DayZpreload().
     */
    public static boolean isUpToDate = true;

    public static boolean isUpdated()
    {
        double i = Double.parseDouble(Util.WEBVERSION);
        double j = Double.parseDouble(Util.VERSION);
        if (i > j)
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
        return "https://github.com/DayZ-Minecraft/DayZ/master/version.txt";
    }

    public static void getWebVersion()
    {
        ChatHandler.logInfo("Checking for update...");
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
            ChatHandler.logInfo("Could not get update!");
        }
    }
}