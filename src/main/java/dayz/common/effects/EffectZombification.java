package dayz.common.effects;

import net.minecraft.entity.EntityLivingBase;
import dayz.common.entities.EntityZombieDayZ;
import dayz.common.misc.DamageType;

public class EffectZombification extends DayZEffect
{
    public static final DayZEffect INSTANCE = new EffectZombification(22, true, 5149489, "Zombification");

    public EffectZombification(int id, boolean isBadEffect, int color, String name)
    {
        super(id, isBadEffect, color, name);
        setIconIndex(6, 0);
    }

    @Override
    public void performEffect(EntityLivingBase entityliving, int par2)
    {
        if (entityliving.func_110143_aJ() > 1)
        {
            entityliving.attackEntityFrom(DamageType.zombieInfection, 1);
        }
        else
        {
            EntityZombieDayZ var2 = new EntityZombieDayZ(entityliving.worldObj);
            var2.setLocationAndAngles(entityliving.posX, entityliving.posY, entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
            entityliving.worldObj.spawnEntityInWorld(var2);
            entityliving.attackEntityFrom(DamageType.zombieInfection, 1);
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        if (duration % 100 == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
