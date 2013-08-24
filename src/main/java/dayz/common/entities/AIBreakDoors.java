package dayz.common.entities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBreakDoor;

public class AIBreakDoors extends EntityAIBreakDoor
{
    private int breakingTime;
    private int field_75358_j = -1;

    public AIBreakDoors(EntityLiving par1EntityLiving)
    {
        super(par1EntityLiving);
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask()
    {
        super.updateTask();

        if (theEntity.getRNG().nextInt(20) == 0)
        {
            theEntity.worldObj.playAuxSFX(1010, entityPosX, entityPosY, entityPosZ, 0);
        }

        ++breakingTime;
        int var1 = (int) (breakingTime / 240.0F * 10.0F);

        if (var1 != field_75358_j)
        {
            theEntity.worldObj.destroyBlockInWorldPartially(theEntity.entityId, entityPosX, entityPosY, entityPosZ, var1);
            field_75358_j = var1;
        }

        if (breakingTime == 240)
        {
            targetDoor.onPoweredBlockChange(theEntity.worldObj, entityPosX, entityPosY, entityPosZ, true);
            // this.theEntity.worldObj.setBlockWithNotify(this.entityPosX,
            // this.entityPosY, this.entityPosZ, 0);
            theEntity.worldObj.playAuxSFX(1012, entityPosX, entityPosY, entityPosZ, 0);
            theEntity.worldObj.playAuxSFX(2001, entityPosX, entityPosY, entityPosZ, targetDoor.blockID);
        }
    }
}
