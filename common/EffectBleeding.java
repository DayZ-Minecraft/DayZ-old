package dayz.common;

import java.util.Arrays;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import dayz.DayZ;

public class EffectBleeding extends DayZEffect
{
	public static final DayZEffect INSTANCE = new EffectBleeding(21, true, 5149489, "Bleeding");
	public static java.util.List<ItemStack> curativeItems =  Arrays.asList(new ItemStack(DayZ.bandage));

	public EffectBleeding(int id, boolean isBadEffect, int color, String name)
	{
		super(id, isBadEffect, color, name);
		this.setIconIndex(6, 0);
	}

	@Override
    public void performEffect(EntityLiving par1EntityLiving, int par2)
    {
        par1EntityLiving.attackEntityFrom(DayZDamageSource.bleedOut, 2);
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
