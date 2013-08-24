package dayz.common.misc;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EnumChatFormatting;

public final class ChatHandler
{
    public static Logger log;

    /**
     * outputs a message in red text to the chat box of the given player.
     * 
     * @param msg
     *            the message to be chatted
     * @param player
     *            player to chat to.
     */
    public static void chatError(ICommandSender sender, String msg)
    {
        if (sender instanceof EntityPlayer)
        {
            sender.sendChatToPlayer(ChatMessageComponent.func_111066_d(EnumChatFormatting.RED + msg(msg)));
        }
        else
        {
            sender.sendChatToPlayer(ChatMessageComponent.func_111066_d(msg(msg)));
        }
    }

    /**
     * outputs a message in bright green to the chat box of the given player.
     * 
     * @param msg
     *            the message to be chatted
     * @param player
     *            player to chat to.
     */
    public static void chatConfirmation(ICommandSender sender, String msg)
    {
        if (sender instanceof EntityPlayer)
        {
            sender.sendChatToPlayer(ChatMessageComponent.func_111066_d(EnumChatFormatting.GREEN + msg(msg)));
        }
        else
        {
            sender.sendChatToPlayer(ChatMessageComponent.func_111066_d(msg(msg)));
        }
    }

    /**
     * outputs a message in yellow to the chat box of the given player.
     * 
     * @param msg
     *            the message to be chatted
     * @param player
     *            player to chat to.
     */
    public static void chatWarning(ICommandSender sender, String msg)
    {
        if (sender instanceof EntityPlayer)
        {
            sender.sendChatToPlayer(ChatMessageComponent.func_111066_d(EnumChatFormatting.YELLOW + msg(msg)));
        }
        else
        {
            sender.sendChatToPlayer(ChatMessageComponent.func_111066_d(msg(msg)));
        }
    }

    /**
     * Use this to throw errors that can continue without crashing the server.
     * 
     * @param level
     * @param message
     * @param error
     */
    public static void logEception(Level level, String message, Throwable error)
    {
        log.log(level, message, error);
    }

    /**
     * Use this to throw errors that can continue without crashing the server.
     * 
     * @param level
     * @param message
     */
    public static void logException(Level level, String message)
    {
        log.log(level, message);
    }

    /**
     * outputs a string to the console if Config.debug = true
     * 
     * @param msg
     *            message to be outputted
     */
    public static void logInfo(String msg)
    {
        log.info(msg);
    }

    public static String msg(String message)
    {
        char[] b = message.toCharArray();
        for (int i = 0; i < b.length - 1; i++)
        {
            if (b[i] == '&' && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1)
            {
                b[i] = '\u00a7';
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }
        return new String(b);
    }

    public static void logDebug(String string)
    {
        
    }
}