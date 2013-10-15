package dayz.common.thirst;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerDataNBT
{
    private static HashMap<String, PlayerDataNBT> playerDataMap = new HashMap<String, PlayerDataNBT>();

    public static PlayerDataNBT getPlayerData(EntityPlayer player)
    {
        PlayerDataNBT info = playerDataMap.get(player.username);

        if (info == null)
        {
            info = new PlayerDataNBT(player);
            playerDataMap.put(player.username, info);
        }
        return info;
    }

    public static void putPlayerData(String username, PlayerDataNBT data)
    {
        playerDataMap.put(username, data);
    }

    public int thirstLevel;

    private PlayerDataNBT(EntityPlayer player)
    {
        thirstLevel = 0;
    }

    public static void saveData(EntityPlayer player)
    {
        NBTTagCompound dayzNBT = player.getEntityData();
        PlayerDataNBT stats = PlayerDataNBT.getPlayerData(player);

        dayzNBT.setInteger("thirstLevel" + player.username, stats.thirstLevel);
    }

    public static void loadData(EntityPlayer player)
    {
        NBTTagCompound dayzNBT = player.getEntityData();
        PlayerDataNBT stats = PlayerDataNBT.getPlayerData(player);

        stats.thirstLevel = dayzNBT.getInteger("thirstLevel" + player.username);
    }
}