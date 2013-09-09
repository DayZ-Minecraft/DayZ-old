package dayz.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import dayz.DayZ;
import dayz.common.blocks.Blocks;
import dayz.common.effects.Effect;
import dayz.common.entities.EntityZombieDayZ;
import dayz.common.misc.ChatHandler;
import dayz.common.misc.DamageType;
import dayz.common.misc.LootManager;

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
                if (event.world.getBlockId(chest.xCoord, chest.yCoord, chest.zCoord) == Blocks.chestLoot.blockID)
                {
                    boolean continueChecking = true;
                    int slotNumber = 0;
                    while (continueChecking == true)
                    {
                        if (chest.getStackInSlot(slotNumber) == null && slotNumber < 27)
                        {
                            if (slotNumber == 26)
                            {
                                WeightedRandomChestContent.generateChestContents(event.world.rand, LootManager.loot, chest, event.world.rand.nextInt(5) + 1);
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

    @ForgeSubscribe
    public void playerInteract(EntityInteractEvent event)
    {
        if (event.target instanceof EntityPlayer && event.entityPlayer.getCurrentEquippedItem().isItemEqual(new ItemStack(DayZ.item().healBloodbag)))
        {
            ((EntityPlayer) event.target).heal(20F);
            event.entityPlayer.getCurrentEquippedItem().stackSize--;
        }
    }

    @ForgeSubscribe
    public void onEntityUpdate(LivingUpdateEvent event)
    {
        if (event.entityLiving.isPotionActive(Effect.bleeding))
        {
            if (event.entityLiving.getActivePotionEffect(Effect.bleeding).getDuration() == 0)
            {
                event.entityLiving.removePotionEffect(Effect.bleeding.id);
                return;
            }
            if (event.entityLiving.worldObj.rand.nextInt(20) == 0)
            {
                event.entityLiving.attackEntityFrom(DamageType.bleedOut, 2);
            }
        }
        if (event.entityLiving.isPotionActive(Effect.zombification))
        {
            if (event.entityLiving.getActivePotionEffect(Effect.zombification).getDuration() == 0)
            {
                event.entityLiving.removePotionEffect(Effect.zombification.id);
                return;
            }
            if (event.entityLiving.worldObj.rand.nextInt(20) == 0)
            {
                if (event.entityLiving.func_110143_aJ() > 1.0F)
                {
                    event.entityLiving.attackEntityFrom(DamageType.zombieInfection, 1.0F);
                }
                else
                {
                    EntityZombieDayZ var2 = new EntityZombieDayZ(event.entityLiving.worldObj);
                    var2.setLocationAndAngles(event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, event.entityLiving.rotationYaw, event.entityLiving.rotationPitch);
                    event.entityLiving.worldObj.spawnEntityInWorld(var2);
                    event.entityLiving.attackEntityFrom(DamageType.zombieInfection, 1.0F);
                }
            }
        }
    }
}
