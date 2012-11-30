package dayz.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Properties
{
    /** Reference to the logger. */
    public static Logger logger = Logger.getLogger("Minecraft");
    private java.util.Properties properties = new java.util.Properties();
    private File associatedFile;

    public Properties(File par1File)
    {
        this.associatedFile = par1File;

        if (par1File.exists())
        {
            FileInputStream var2 = null;

            try
            {
                var2 = new FileInputStream(par1File);
                this.properties.load(var2);
            }
            catch (Exception var12)
            {
                logger.log(Level.WARNING, "Failed to load " + par1File, var12);
                this.logMessageAndSave();
            }
            finally
            {
                if (var2 != null)
                {
                    try
                    {
                        var2.close();
                    }
                    catch (IOException var11)
                    {
                        ;
                    }
                }
            }
        }
        else
        {
            logger.log(Level.WARNING, par1File + " does not exist");
            this.logMessageAndSave();
        }
    }

    /**
     * logs an info message then calls saveSettingsToFile Yes this appears to be a potential stack overflow - these 2
     * functions call each other repeatdly if an exception occurs.
     */
    public void logMessageAndSave()
    {
        logger.log(Level.INFO, "Generating new properties file");
        this.saveProperties();
    }

    /**
     * Writes the properties to the properties file.
     */
    public void saveProperties()
    {
        FileOutputStream var1 = null;

        try
        {
            var1 = new FileOutputStream(this.associatedFile);
            this.properties.store(var1, "Minecraft DayZ Properties");
        }
        catch (Exception var11)
        {
            logger.log(Level.WARNING, "Failed to save " + this.associatedFile, var11);
            this.logMessageAndSave();
        }
        finally
        {
            if (var1 != null)
            {
                try
                {
                    var1.close();
                }
                catch (IOException var10)
                {
                    ;
                }
            }
        }
    }

    /**
     * Returns this PropertyManager's file object used for property saving.
     */
    public File getPropertiesFile()
    {
        return this.associatedFile;
    }

    /**
     * Gets a property. If it does not exist, set it to the specified value.
     */
    public String getProperty(String par1Str, String par2Str)
    {
        if (!this.properties.containsKey(par1Str))
        {
            this.properties.setProperty(par1Str, par2Str);
            this.saveProperties();
        }

        return this.properties.getProperty(par1Str, par2Str);
    }

    /**
     * Gets an integer property. If it does not exist, set it to the specified value.
     */
    public int getIntProperty(String par1Str, int par2)
    {
        try
        {
            return Integer.parseInt(this.getProperty(par1Str, "" + par2));
        }
        catch (Exception var4)
        {
            this.properties.setProperty(par1Str, "" + par2);
            return par2;
        }
    }

    /**
     * Gets a boolean property. If it does not exist, set it to the specified value.
     */
    public boolean getBooleanProperty(String par1Str, boolean par2)
    {
        try
        {
            return Boolean.parseBoolean(this.getProperty(par1Str, "" + par2));
        }
        catch (Exception var4)
        {
            this.properties.setProperty(par1Str, "" + par2);
            return par2;
        }
    }

    /**
     * Saves an Object with the given property name.
     */
    public void setProperty(String par1Str, Object par2Obj)
    {
        this.properties.setProperty(par1Str, "" + par2Obj);
    }
}
