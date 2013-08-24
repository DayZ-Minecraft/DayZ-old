package dayz.common.misc;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.DamageSource;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class DamageType extends DamageSource
{
    public static final List<DamageType> damageSources = new ArrayList<DamageType>();

    /**
     * Use this damage source for all types of electrical attacks.
     */
    public static final DamageType bleedOut = (DamageType) new DamageType("bleedOut", "%1$s lost too much blood").setDamageBypassesArmor();
    public static final DamageType zombieInfection = (DamageType) new DamageType("zombieInfection", "%1$s became a zombie").setDamageBypassesArmor();
    public static final DamageType thirstDeath = (DamageType) new DamageType("thirstDeath", "%1$s ran out of water").setDamageBypassesArmor();

    public String deathMessage;

    public DamageType(String damageType)
    {
        super(damageType);
        damageSources.add(this);
    }

    public DamageType(String damageType, String deathMessage)
    {
        this(damageType);
        setDeathMessage(deathMessage);
    }

    public DamageType setDeathMessage(String deathMessage)
    {
        this.deathMessage = deathMessage;
        return this;
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

    public void registerDeathMessage()
    {
        LanguageRegistry.instance().addStringLocalization("death." + damageType, deathMessage);
    }
}