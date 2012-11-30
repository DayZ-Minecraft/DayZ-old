package dayz.common.entities;

import net.minecraft.src.EntityAIBreakDoor;
import net.minecraft.src.EntityLiving;

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
    public void updateTask()
    {
        super.updateTask();

        if (this.theEntity.getRNG().nextInt(20) == 0)
        {
            this.theEntity.worldObj.playAuxSFX(1010, this.entityPosX, this.entityPosY, this.entityPosZ, 0);
        }

        ++this.breakingTime;
        int var1 = (int)((float)this.breakingTime / 240.0F * 10.0F);

        if (var1 != this.field_75358_j)
        {
            this.theEntity.worldObj.destroyBlockInWorldPartially(this.theEntity.entityId, this.entityPosX, this.entityPosY, this.entityPosZ, var1);
            this.field_75358_j = var1;
        }

        if (this.breakingTime == 240)
        {
            this.targetDoor.onPoweredBlockChange(this.theEntity.worldObj, this.entityPosX, this.entityPosY, this.entityPosZ, true);
            //this.theEntity.worldObj.setBlockWithNotify(this.entityPosX, this.entityPosY, this.entityPosZ, 0);
            this.theEntity.worldObj.playAuxSFX(1012, this.entityPosX, this.entityPosY, this.entityPosZ, 0);
            this.theEntity.worldObj.playAuxSFX(2001, this.entityPosX, this.entityPosY, this.entityPosZ, this.targetDoor.blockID);
        }
    }
}
