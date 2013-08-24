package dayz.common;

import dayz.DayZ;
import dayz.common.misc.ChatHandler;
import dayz.common.misc.WeightedRandomChestDayZ;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;

public class CommonEvents
{
    @ForgeSubscribe
    public void worldLoad(WorldEvent.Load event)
    {
        for (Object obj : event.world.loadedTileEntityList)
        {
            if (obj instanceof TileEntityChest)
            {
                TileEntityChest chest = (TileEntityChest) obj;
                if (event.world.getBlockId(chest.xCoord, chest.yCoord, chest.zCoord) == DayZ.dayzChestID)
                {
                    boolean continueChecking = true;
                    int slotNumber = 0;
                    while (continueChecking == true)
                    {
                        if (chest.getStackInSlot(slotNumber) == null && slotNumber < 27)
                        {
                            if(slotNumber == 26)
                            {
                                WeightedRandomChestDayZ.generateChestContents(DayZ.random, chest, DayZ.random.nextInt(5) + 1);    
                                ChatHandler.logDebug("Refilled chest at " + chest.xCoord + ", " + chest.yCoord + ", " + chest.zCoord + ".");
                                continueChecking = false;
                            }
                            else
                            {
                                slotNumber++;
                            }
                        }
                        else
                        {
                            continueChecking = false;
                        }
                    }  
                }
            }
        }
    }
}
