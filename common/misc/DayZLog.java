package dayz.common.misc;

import java.util.logging.Level;
import java.util.logging.Logger;

public enum DayZLog 
{
	INSTANCE;
 
	private static boolean configured;
    private static Logger myLog;

	public static void configureLogging() 
	{
		if (configured) return;
		configured = true;

		myLog = Logger.getLogger("DAY Z");
		myLog.setParent(Logger.getLogger("ForgeModLoader"));
	}

    public static void log(Level level, String format, Object... data)
    {
    	myLog.log(level, String.format(format, data));
    }

    public static void log(Level level, Throwable ex, String format, Object... data)
    {
    	myLog.log(level, String.format(format, data), ex);
    }

    public static void severe(String format, Object... data)
    {
        log(Level.SEVERE, format, data);
    }

    public static void warning(String format, Object... data)
    {
        log(Level.WARNING, format, data);
    }

    public static void info(String format, Object... data)
    {
        log(Level.INFO, format, data);
    }

    public static void fine(String format, Object... data)
    {
        log(Level.FINE, format, data);
    }

    public static void finer(String format, Object... data)
    {
        log(Level.FINER, format, data);
    }

    public static void finest(String format, Object... data)
    {
        log(Level.FINEST, format, data);
    }
    public static Logger getLogger()
    {
        return myLog;
    }
}