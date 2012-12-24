package dayz.common;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class DayZEnactEffect extends PotionEffect
{
    private List<ItemStack> curativeItems;

	public DayZEnactEffect(int potionID, int duration, int amplifier)
	{
		super(potionID, duration, amplifier);
	}

	public DayZEnactEffect(Potion potion, int duration, int amplifier)
	{
		this(potion.getId(), duration, amplifier);
	}
}