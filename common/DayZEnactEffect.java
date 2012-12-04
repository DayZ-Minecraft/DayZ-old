package dayz.common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.ItemStack;
import net.minecraft.src.Potion;
import net.minecraft.src.PotionEffect;

public class DayZEnactEffect extends PotionEffect
{
	public DayZEnactEffect(int potionID, int duration, int amplifier)
	{
		super(potionID, duration, amplifier);
	}

	public DayZEnactEffect(Potion potion, int duration, int amplifier)
	{
		this(potion.getId(), duration, amplifier);
	}
}