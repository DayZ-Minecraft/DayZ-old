package dayz.common.effects;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class DayZEnactEffect extends PotionEffect
{
    /** List of ItemStack that can cure the potion effect **/
    private List<ItemStack> curativeItems;

    public DayZEnactEffect(int potionID, int duration, int amplifier)
    {
        super(potionID, duration, amplifier);
        curativeItems = new ArrayList<ItemStack>();
    }

    public DayZEnactEffect(Potion potion, int duration, int amplifier)
    {
        this(potion.getId(), duration, amplifier);
    }
}