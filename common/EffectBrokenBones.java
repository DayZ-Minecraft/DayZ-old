package dayz.common;

import java.util.Arrays;

import net.minecraft.item.ItemStack;
import dayz.DayZ;

public class EffectBrokenBones extends DayZEffect
{
	public static final DayZEffect INSTANCE = new EffectBrokenBones(23, true, 5149489, "Broken Bone");
	public static java.util.List<ItemStack> curativeItems = Arrays.asList(new ItemStack(DayZ.bandage));

	public EffectBrokenBones(int id, boolean isBadEffect, int color, String name)
	{
		super(id, isBadEffect, color, name);
		this.setIconIndex(6, 0);
	}
	    
    @Override
    public boolean isReady(int duration, int amplifier)
    {
    	return true;
    }
}
