package dayz.common.misc;

import net.minecraft.util.DamageSource;

public class DamageType extends DamageSource
{
    public static final DamageType bleedOut = (DamageType) new DamageType("bleedOut").setDamageBypassesArmor();
    public static final DamageType zombieInfection = (DamageType) new DamageType("zombieInfection").setDamageBypassesArmor();
    public static final DamageType thirstDeath = (DamageType) new DamageType("thirstDeath").setDamageBypassesArmor();

    public DamageType(String damageType)
    {
        super(damageType);
    }

    @Override
    public DamageSource setDamageBypassesArmor()
    {
        return super.setDamageBypassesArmor();
    }

    @Override
    public DamageSource setDamageAllowedInCreativeMode()
    {
        return super.setDamageAllowedInCreativeMode();
    }

    @Override
    public DamageSource setFireDamage()
    {
        return super.setFireDamage();
    }
}