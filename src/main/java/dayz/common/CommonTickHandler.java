package dayz.common;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class CommonTickHandler implements ITickHandler
{
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {

    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        onGameTick();
        if (type.equals(EnumSet.of(TickType.PLAYER)))
        {
            onPlayerTick((EntityPlayer) tickData[0]);
        }
    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.PLAYER);
    }

    @Override
    public String getLabel()
    {
        return "DayZ Thirst";
    }

    private void onPlayerTick(EntityPlayer player)
    {

    }

    private void onGameTick()
    {

    }
}
