package dayz.common.entities;

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
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.EntityVillager;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Potion;
import net.minecraft.src.PotionEffect;
import net.minecraft.src.World;

public class EntityCrawler extends EntityMob
{
    public EntityCrawler(World par1World)
    {
        super(par1World);
        texture = "/dayz/images/mob/crawler.png";
        moveSpeed = 0.2F;
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
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayerMP.class, 16F, 0, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16F, 0, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 16F, 0, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityBandit.class, 16F, 0, false));
    }
    
    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    @Override
    public int getMaxHealth()
    {
        return 12;
    }

    /**
     * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
     */
    @Override
    public int getTotalArmorValue()
    {
        return 2;
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
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
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
            if (j == 0)
            {
            	((EntityLiving)par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 120 * 20, 0));
            }
    		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2);
        }
    	else if (this.worldObj.difficultySetting == 2)
        {
    		int j = rand.nextInt(5);
            if (j == 0)
            {
            	((EntityLiving)par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 120 * 20, 0));
            }

            return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 3);
        }
        else if (this.worldObj.difficultySetting == 3)
        {
        	int j = rand.nextInt(3);
            if (j == 0)
            {
            	((EntityLiving)par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 120 * 20, 0));
            }
            return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 6);
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
        return 0.5F;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
    }
    
    @Override
    public void onKillEntity(EntityLiving entityliving)
    {
        if (!this.worldObj.isRemote)
        {
        	int chanceOfRespawn = 1; //rand.nextInt(2);
   		 	EntityCrawler var2 = new EntityCrawler(this.worldObj);

            if (entityliving instanceof EntityPlayer)
            {
            	if (chanceOfRespawn == 1)
            	{
            		var2.setLocationAndAngles(entityliving.posX, entityliving.posY, entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
            		 this.worldObj.spawnEntityInWorld(var2);
            	}
            }
            else if (entityliving instanceof EntityPlayerMP)
            {
            	if (chanceOfRespawn == 1)
            	{
            		var2.setLocationAndAngles(entityliving.posX, entityliving.posY, entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
            		 this.worldObj.spawnEntityInWorld(var2);
            	}
            }
            else if (entityliving instanceof EntityBandit)
            {
            	if (chanceOfRespawn == 1)
            	{
            		var2.setLocationAndAngles(entityliving.posX, entityliving.posY, entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
            		 this.worldObj.spawnEntityInWorld(var2);
            	}
            }
            else if (entityliving instanceof EntityVillager)
            {
            	if (chanceOfRespawn == 1)
            	{
            		 var2.setLocationAndAngles(entityliving.posX, entityliving.posY, entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
            		 this.worldObj.spawnEntityInWorld(var2);
            	}
            }
        }
    }
}