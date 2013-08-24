package dayz.common.effects;

import net.minecraft.entity.EntityLivingBase;
import dayz.common.misc.DamageType;

public class EffectBleeding extends DayZEffect
{
    public static final DayZEffect INSTANCE = new EffectBleeding(21, true, 5149489, "Bleeding");

    public EffectBleeding(int id, boolean isBadEffect, int color, String name)
    {
        super(id, isBadEffect, color, name);
        setIconIndex(6, 0);
    }

    @Override
    public void performEffect(EntityLivingBase par1EntityLiving, int par2)
    {
        par1EntityLiving.attackEntityFrom(DamageType.bleedOut, 2);
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
