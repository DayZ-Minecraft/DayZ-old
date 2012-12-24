package dayz.common;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import cpw.mods.fml.common.registry.LanguageRegistry;

public abstract class DayZEffect extends Potion
{	
	public static java.util.List<ItemStack> curativeItems;

	/**
	 * Creates a new type of potion
	 * 
	 * @param id
	 *            - The ID of this potion. Make it greater than 20.
	 * @param isBadEffect
	 *            - Is this potion a good potion or a bad one?
	 * @param color
	 *            - The color of this potion.
	 * @param name
	 *            - The name of this potion.
	 */
	public DayZEffect(int id, boolean isBadEffect, int color, String name)
	{
		super(id, isBadEffect, color);
		this.setPotionName("potion." + name);
		LanguageRegistry.instance().addStringLocalization(this.getName(), name);
	}

	@Override
	public Potion setIconIndex(int par1, int par2)
	{
		super.setIconIndex(par1, par2);
		return this;
	}

	/**
	 * You must register all your potion effects during mod initialization!
	 */
	public void register()
	{
		Potion.potionTypes[this.getId()] = this;
	}
}