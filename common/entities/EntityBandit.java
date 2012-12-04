package dayz.common.entities;

import dayz.common.DayZEnactEffect;
import dayz.common.EffectBleeding;
import dayz.common.EffectZombification;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAIAttackOnCollide;
import net.minecraft.src.EntityAIHurtByTarget;
import net.minecraft.src.EntityAILookIdle;
import net.minecraft.src.EntityAIMoveThroughVillage;
import net.minecraft.src.EntityAINearestAttackableTarget;
import net.minecraft.src.EntityAISwimming;
import net.minecraft.src.EntityAIWander;
import net.minecraft.src.EntityAIWatchClosest;
import net.minecraft.src.EntityAgeable;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMob;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.EntityVillager;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public class EntityBandit extends EntityMob
{
    private static final ItemStack defaultHeldItem = new ItemStack(Item.swordStone, 1);

    public EntityBandit(World world)
    {
        super(world);
        texture = "/dayz/images/mob/bandit.png";
        moveSpeed = 0.4F;
        getNavigator().setBreakDoors(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new AIBreakDoors(this));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, moveSpeed, false));
        tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, moveSpeed, true));
        tasks.addTask(5, new EntityAIMoveThroughVillage(this, moveSpeed, false));
        tasks.addTask(6, new EntityAIWander(this, moveSpeed));
        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
        tasks.addTask(7, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayerMP.class, 16F, 0, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16F, 0, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 16F, 0, false));
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
     * Returns the entity's health.
     */
    public int getMaxHealth()
    {
        return 16;
    }

    @Override
    protected String getLivingSound()
    {
        return "mob.villager.default";
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.villager.defaulthurt";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.villager.defaultdeath";
    }

    @Override
    public ItemStack getHeldItem()
    {
        return defaultHeldItem;
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
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