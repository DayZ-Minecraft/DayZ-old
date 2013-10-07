package dayz.common.effects;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class EnactEffect extends PotionEffect
{
    public EnactEffect(int potionID, int duration, int amplifier)
    {
        super(potionID, duration, amplifier);
    }

    public EnactEffect(Potion potion, int duration, int amplifier)
    {
        this(potion.getId(), duration, amplifier);
    }

    /**
     * Creates a potion effect with custom curable items.
     * 
     * @param curativeItems
     *            - ItemStacks that can cure this potion effect
     */
    public EnactEffect(int potionID, int duration, int amplifier, List<ItemStack> curativeItems)
    {
        super(potionID, duration, amplifier);

        if (curativeItems == null)
        {
            setCurativeItems(new ArrayList<ItemStack>());
        }
        else
        {
            setCurativeItems(curativeItems);
        }
    }
}