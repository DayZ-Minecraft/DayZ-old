package dayz.common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.DamageSource;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class DayZDamageSource extends DamageSource
{
	public static final List<DayZDamageSource> damageSources = new ArrayList<DayZDamageSource>();

	/**
	 * Use this damage source for all types of electrical attacks.
	 */
	public static final DayZDamageSource bleedOut = (DayZDamageSource) new DayZDamageSource("bleedOut", "%1$s lost too much blood").setDamageBypassesArmor();
	public static final DayZDamageSource zombieInfection = (DayZDamageSource) new DayZDamageSource("zombieInfection", "%1$s became a zombie").setDamageBypassesArmor();
	public static final DayZDamageSource thirstDeath = (DayZDamageSource) new DayZDamageSource("thirstDeath", "%1$s ran out of water").setDamageBypassesArmor();

	public String deathMessage;

	public DayZDamageSource(String damageType)
	{
		super(damageType);
		damageSources.add(this);
	}

	public DayZDamageSource(String damageType, String deathMessage)
	{
		this(damageType);
		this.setDeathMessage(deathMessage);
	}

	public DayZDamageSource setDeathMessage(String deathMessage)
	{
		this.deathMessage = deathMessage;
		return this;
	}

	public DamageSource setDamageBypassesArmor()
	{
		return super.setDamageBypassesArmor();
	}

	public DamageSource setDamageAllowedInCreativeMode()
	{
		return super.setDamageAllowedInCreativeMode();
	}

	public DamageSource setFireDamage()
	{
		return super.setFireDamage();
	}

	public void registerDeathMessage()
	{
		LanguageRegistry.instance().addStringLocalization("death." + this.damageType, this.deathMessage);
	}
}