package dayz.common.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import dayz.common.effects.DayZEnactEffect;
import dayz.common.effects.EffectBleeding;

public class EntityBullet extends EntityThrowable
{
	private int bulletdamage;
    public Entity shootingEntity;
	
	public EntityBullet(World par1World)
    {
        super(par1World);
        this.setSize(0.1F, 0.1F);
    }
    	
    public EntityBullet(World par1World, EntityLiving par2EntityLiving, int damage)
    {
        super(par1World, par2EntityLiving);
        this.bulletdamage = damage;
        
        this.shootingEntity = par2EntityLiving;
        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY + (double)par2EntityLiving.getEyeHeight(), par2EntityLiving.posZ, par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);
        this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.posY -= 0.10000000149011612D;
        this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, 1.5F, 1.0F);
    }

    public EntityBullet(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
        
    }

    protected float getGravityVelocity()
    {
        return 0.001F;
    }

    public void setVelocity(double par1, double par3, double par5)
    {
        motionX = par1;
        motionY = par3;
        motionZ = par5;

        if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
            prevRotationYaw = rotationYaw = (float)((Math.atan2(par1, par5) * 180D) / Math.PI);
            prevRotationPitch = rotationPitch = (float)((Math.atan2(par3, f) * 180D) / Math.PI);
            prevRotationPitch = rotationPitch;
            prevRotationYaw = rotationYaw;
            setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
        }
    }

    public void setArrowHeading(double par1, double par3, double par5, float par7, float par8)
    {
        float f = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
        par1 /= f;
        par3 /= f;
        par5 /= f;
        par1 += rand.nextGaussian() * 0.0074999998323619366D * (double)par8;
        par3 += rand.nextGaussian() * 0.0074999998323619366D * (double)par8;
        par5 += rand.nextGaussian() * 0.0074999998323619366D * (double)par8;
        par1 *= par7;
        par3 *= par7;
        par5 *= par7;
        motionX = par1;
        motionY = par3;
        motionZ = par5;
        float f1 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
        prevRotationYaw = rotationYaw = (float)((Math.atan2(par1, par5) * 180D) / Math.PI);
        prevRotationPitch = rotationPitch = (float)((Math.atan2(par3, f1) * 180D) / Math.PI);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
        if (par1MovingObjectPosition.entityHit != null)
        {
            int var2 = bulletdamage;

            if(par1MovingObjectPosition.entityHit instanceof EntityLiving)
            {
            	((EntityLiving)par1MovingObjectPosition.entityHit).attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), var2);
            }
            
            if (par1MovingObjectPosition.entityHit instanceof EntityPlayer)
            {
            	if (this.worldObj.difficultySetting == 1)
            	{
            		int j = rand.nextInt(10);
                    if (j == 0)
                    {
                    	((EntityLiving)par1MovingObjectPosition.entityHit).addPotionEffect(new DayZEnactEffect(EffectBleeding.INSTANCE.getId(), 20 * 120, 1));
                    }
                }
            	else if (this.worldObj.difficultySetting == 2)
                {
            		int j = rand.nextInt(5);
                    if (j == 0)
                    {
                    	((EntityLiving)par1MovingObjectPosition.entityHit).addPotionEffect(new DayZEnactEffect(EffectBleeding.INSTANCE.getId(), 20 * 120, 1));
                    }
                }
                else if (this.worldObj.difficultySetting == 3)
                {
                	int j = rand.nextInt(3);
                    if (j == 0)
                    {
                    	((EntityLiving)par1MovingObjectPosition.entityHit).addPotionEffect(new DayZEnactEffect(EffectBleeding.INSTANCE.getId(), 20 * 120, 1));
                    }
                }
            }
        }
        else if (par1MovingObjectPosition.typeOfHit == EnumMovingObjectType.TILE)
        {
            if (!this.worldObj.isRemote)
            {
                this.setDead();
            }
        	if (this.worldObj.getBlockId(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY, par1MovingObjectPosition.blockZ) == Block.glass.blockID || this.worldObj.getBlockId(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY, par1MovingObjectPosition.blockZ) == Block.thinGlass.blockID)
        	{
        		this.worldObj.setBlock(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY, par1MovingObjectPosition.blockZ, 0);
           		this.worldObj.playSoundEffect(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY, par1MovingObjectPosition.blockZ, "random.glass", 1.0F, 1.0F);
                this.setDead();
        	}
        	else if (this.worldObj.getBlockId(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY, par1MovingObjectPosition.blockZ) == Block.tallGrass.blockID)
        	{
        		this.worldObj.setBlock(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY, par1MovingObjectPosition.blockZ, 0);
        		this.worldObj.playSoundEffect(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY, par1MovingObjectPosition.blockZ, "step.grass", 1.0F, 1.0F);
                this.setDead();
        	}
        }
    }
}
