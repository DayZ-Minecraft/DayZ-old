package dayz.common.entities;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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
        setSize(0.1F, 0.1F);
    }

    public EntityBullet(World par1World, EntityLivingBase par2EntityLivingBase, int damage)
    {
        super(par1World, par2EntityLivingBase);
        bulletdamage = damage;

        shootingEntity = par2EntityLivingBase;
        setSize(0.5F, 0.5F);
        setLocationAndAngles(par2EntityLivingBase.posX, par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight(), par2EntityLivingBase.posZ, par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);
        posX -= MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        posY -= 0.10000000149011612D;
        posZ -= MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        setPosition(posX, posY, posZ);
        yOffset = 0.0F;
        motionX = -MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI);
        motionZ = MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI);
        motionY = (-MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI));
        setThrowableHeading(motionX, motionY, motionZ, 1.5F, 1.0F);
    }

    public EntityBullet(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);

    }

    @Override
    protected float getGravityVelocity()
    {
        return 0.001F;
    }

    @Override
    public void setVelocity(double par1, double par3, double par5)
    {
        motionX = par1;
        motionY = par3;
        motionZ = par5;

        if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
            prevRotationYaw = rotationYaw = (float) ((Math.atan2(par1, par5) * 180D) / Math.PI);
            prevRotationPitch = rotationPitch = (float) ((Math.atan2(par3, f) * 180D) / Math.PI);
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
        par1 += rand.nextGaussian() * 0.0074999998323619366D * par8;
        par3 += rand.nextGaussian() * 0.0074999998323619366D * par8;
        par5 += rand.nextGaussian() * 0.0074999998323619366D * par8;
        par1 *= par7;
        par3 *= par7;
        par5 *= par7;
        motionX = par1;
        motionY = par3;
        motionZ = par5;
        float f1 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
        prevRotationYaw = rotationYaw = (float) ((Math.atan2(par1, par5) * 180D) / Math.PI);
        prevRotationPitch = rotationPitch = (float) ((Math.atan2(par3, f1) * 180D) / Math.PI);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    @Override
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
        if (par1MovingObjectPosition.entityHit != null)
        {
            int var2 = bulletdamage;

            if (par1MovingObjectPosition.entityHit instanceof EntityLivingBase)
            {
                ((EntityLivingBase) par1MovingObjectPosition.entityHit).attackEntityFrom(DamageSource.causeThrownDamage(this, shootingEntity), var2);
            }

            if (par1MovingObjectPosition.entityHit instanceof EntityPlayer)
            {
                if (worldObj.difficultySetting == 1)
                {
                    int j = rand.nextInt(10);
                    if (j == 0)
                    {
                        ((EntityLivingBase) par1MovingObjectPosition.entityHit).addPotionEffect(new DayZEnactEffect(EffectBleeding.INSTANCE.getId(), 20 * 120, 1));
                    }
                }
                else if (worldObj.difficultySetting == 2)
                {
                    int j = rand.nextInt(5);
                    if (j == 0)
                    {
                        ((EntityLivingBase) par1MovingObjectPosition.entityHit).addPotionEffect(new DayZEnactEffect(EffectBleeding.INSTANCE.getId(), 20 * 120, 1));
                    }
                }
                else if (worldObj.difficultySetting == 3)
                {
                    int j = rand.nextInt(3);
                    if (j == 0)
                    {
                        ((EntityLivingBase) par1MovingObjectPosition.entityHit).addPotionEffect(new DayZEnactEffect(EffectBleeding.INSTANCE.getId(), 20 * 120, 1));
                    }
                }
            }
        }
        else if (par1MovingObjectPosition.typeOfHit == EnumMovingObjectType.TILE)
        {
            if (!worldObj.isRemote)
            {
                setDead();
            }
            if (worldObj.getBlockId(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY, par1MovingObjectPosition.blockZ) == Block.glass.blockID || worldObj.getBlockId(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY, par1MovingObjectPosition.blockZ) == Block.thinGlass.blockID)
            {
                worldObj.setBlock(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY, par1MovingObjectPosition.blockZ, 0);
                worldObj.playSoundEffect(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY, par1MovingObjectPosition.blockZ, "random.glass", 1.0F, 1.0F);
                setDead();
            }
            else if (worldObj.getBlockId(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY, par1MovingObjectPosition.blockZ) == Block.tallGrass.blockID)
            {
                worldObj.setBlock(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY, par1MovingObjectPosition.blockZ, 0);
                worldObj.playSoundEffect(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY, par1MovingObjectPosition.blockZ, "step.grass", 1.0F, 1.0F);
                setDead();
            }
        }
    }
}
