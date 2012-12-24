package dayz.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
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
import dayz.common.DayZEnactEffect;
import dayz.common.EffectBleeding;
import dayz.common.EffectZombification;

public class EntityZombieDayZ extends EntityMob
{
    protected int attackStrength = 6;

    public EntityZombieDayZ(World par1World)
    {
        super(par1World);
        this.texture = getRandomZombieTexture();
        this.moveSpeed = 0.3F;
        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayerMP.class, moveSpeed, false));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, moveSpeed, false));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityZombieDayZ.class, moveSpeed, true));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, moveSpeed, true));
        this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, moveSpeed, false));
        this.tasks.addTask(6, new EntityAIWander(this, moveSpeed));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(9, new AIBreakDoors(this));
        this.targetTasks.addTask(10, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(11, new EntityAINearestAttackableTarget(this, EntityPlayerMP.class, 16F, 0, true));
        this.targetTasks.addTask(12, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16F, 0, true));
        this.targetTasks.addTask(13, new EntityAINearestAttackableTarget(this, EntityVillager.class, 16F, 0, false));
        this.targetTasks.addTask(14, new EntityAINearestAttackableTarget(this, EntityBandit.class, 16F, 0, false));
    }

    private String getRandomZombieTexture()
    {
    	return "/dayz/images/mob/Zombie" + rand.nextInt(7) + ".png";
    }

    /**
     * Sets the Entity's health.
     */
    @Override
    public int getMaxHealth()
    {
        return 16;
    }
    
    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound()
    {
        return "mob.zombie.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound()
    {
        return "mob.zombie.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound()
    {
        return "mob.zombie.death";
    }
    
    /**
     * Plays step sound at given x, y, z for the entity
     */
    @Override
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.worldObj.playSoundAtEntity(this, "mob.zombie.step", 0.15F, 1.0F);
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

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
        if (super.attackEntityFrom(par1DamageSource, par2))
        {
            Entity entity = par1DamageSource.getEntity();

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
    public boolean attackEntityAsMob(Entity par1Entity)
    {
    	if (this.worldObj.difficultySetting == 1)
    	{
    		int j = rand.nextInt(10);
    		int k = rand.nextInt(20);
            if (j == 0)
            {
            	((EntityLiving)par1Entity).addPotionEffect(new DayZEnactEffect(EffectBleeding.INSTANCE.getId(), 20 * 120, 1));
            }
            if (k == 0)
            {
            	((EntityLiving)par1Entity).addPotionEffect(new DayZEnactEffect(EffectZombification.INSTANCE.getId(), 20 * 120, 1));
            }
    		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 1);
        }
    	else if (this.worldObj.difficultySetting == 2)
        {
    		int j = rand.nextInt(5);
    		int k = rand.nextInt(10);
            if (j == 0)
            {
            	((EntityLiving)par1Entity).addPotionEffect(new DayZEnactEffect(EffectBleeding.INSTANCE.getId(), 20 * 120, 1));
            }
            if (k == 0)
            {
            	((EntityLiving)par1Entity).addPotionEffect(new DayZEnactEffect(EffectZombification.INSTANCE.getId(), 20 * 120, 1));
            }
            return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2);
        }
        else if (this.worldObj.difficultySetting == 3)
        {
        	int j = rand.nextInt(3);
    		int k = rand.nextInt(6);
            if (j == 0)
            {
            	((EntityLiving)par1Entity).addPotionEffect(new DayZEnactEffect(EffectBleeding.INSTANCE.getId(), 20 * 120, 1));
            }
            if (k == 0)
            {
            	((EntityLiving)par1Entity).addPotionEffect(new DayZEnactEffect(EffectZombification.INSTANCE.getId(), 20 * 120, 1));
            }
            return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 4);
        }
        else
        {
        	return false;
        }
    }
    
    public boolean isWalking(EntityPlayer target)
    {
    	if (
    			!target.isJumping &&
    			!target.isBurning() &&
    			!target.isOnLadder() &&
    			!target.isRiding() && 
    			!target.isSprinting() &&
    			!target.isSneaking())
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        
        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == 0)
        {
            this.setDead();
        }        
    }
    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    @Override
    protected void attackEntity(Entity par1Entity, float par2)
    {
        if (attackTime <= 0 && par2 < 2.0F && par1Entity.boundingBox.maxY > boundingBox.minY && par1Entity.boundingBox.minY < boundingBox.maxY)
        {
            attackTime = 20;
            attackEntityAsMob(par1Entity);
        }
    }

    /**
     * Takes a coordinate in and returns a weight to determine how likely this creature will try to path to the block.
     * Args: x, y, z
     */
    @Override
    public float getBlockPathWeight(int par1, int par2, int par3)
    {
        return 0.5F - worldObj.getLightBrightness(par1, par2, par3);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
    }
}