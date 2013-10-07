package dayz.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import dayz.common.effects.Effect;
import dayz.common.effects.EnactEffect;

public class EntityCrawler extends EntityMob
{
    public EntityCrawler(World world)
    {
        super(world);
        setHealth(12F);
        float moveSpeed = 0.3F;
        getNavigator().setBreakDoors(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new AIBreakDoors(this));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayerMP.class, moveSpeed, false));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, moveSpeed, false));
        tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityZombieDayZ.class, moveSpeed, true));
        tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, moveSpeed, true));
        tasks.addTask(5, new EntityAIMoveThroughVillage(this, moveSpeed, false));
        tasks.addTask(6, new EntityAIWander(this, moveSpeed));
        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
        tasks.addTask(7, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayerMP.class, 0, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    @Override
    protected String getLivingSound()
    {
        return "mob.zombie.say";
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.zombie.hurt";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.zombie.death";
    }

    @Override
    protected Entity findPlayerToAttack()
    {
        EntityPlayer entityplayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16D);

        if (entityplayer != null && canEntityBeSeen(entityplayer))
        {
            return entityplayer;
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage)
    {
        if (super.attackEntityFrom(damageSource, damage))
        {
            Entity entity = damageSource.getEntity();

            if (riddenByEntity == entity || ridingEntity == entity)
            {
                return true;
            }

            if (entity != this)
            {
                entityToAttack = entity;
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        if (worldObj.difficultySetting == 1)
        {
            int j = rand.nextInt(10);
            int k = rand.nextInt(20);
            if (j == 0)
            {
                ((EntityLivingBase) entity).addPotionEffect(new EnactEffect(Effect.bleeding.getId(), 20 * 120, 1));
            }
            if (k == 0)
            {
                ((EntityLivingBase) entity).addPotionEffect(new EnactEffect(Effect.zombification.getId(), 20 * 120, 1));
            }
            return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2);
        }
        else if (worldObj.difficultySetting == 2)
        {
            int j = rand.nextInt(5);
            int k = rand.nextInt(10);
            if (j == 0)
            {
                ((EntityLivingBase) entity).addPotionEffect(new EnactEffect(Effect.bleeding.getId(), 20 * 120, 1));
            }
            if (k == 0)
            {
                ((EntityLivingBase) entity).addPotionEffect(new EnactEffect(Effect.zombification.getId(), 20 * 120, 1));
            }
            return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 3);
        }
        else if (worldObj.difficultySetting == 3)
        {
            int j = rand.nextInt(3);
            int k = rand.nextInt(6);
            if (j == 0)
            {
                ((EntityLivingBase) entity).addPotionEffect(new EnactEffect(Effect.bleeding.getId(), 20 * 120, 1));
            }
            if (k == 0)
            {
                ((EntityLivingBase) entity).addPotionEffect(new EnactEffect(Effect.zombification.getId(), 20 * 120, 1));
            }
            return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 6);
        }
        else
        {
            return false;
        }
    }

    @Override
    protected void attackEntity(Entity entity, float distanceToEntity)
    {
        if (attackTime <= 0 && distanceToEntity < 2.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackTime = 20;
            attackEntityAsMob(entity);
        }
    }

    @Override
    public float getBlockPathWeight(int xCoord, int yCoord, int zCoord)
    {
        return 0.5F - worldObj.getLightBrightness(xCoord, yCoord, zCoord);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        super.writeEntityToNBT(tagCompound);
    }
}