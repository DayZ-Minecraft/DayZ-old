package dayz.common.items;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumAction;
import net.minecraft.src.ItemFood;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import cpw.mods.fml.common.Loader;
import dayz.common.external.*;

public class ItemDayzDrink extends ItemFood
{
    private final float saturationModifier;
    private final int thirstReplenish;

    /**
     * Day Z Drink Item
     * @param itemID The ID of the item
     * @param saturationmodifier The hunger bar saturation to be restored
     * @param drinkEffect Potion effect of the drink
     */
    public ItemDayzDrink(int itemID, int thirstreplenish, float saturationmodifier)
    {
        super(itemID, 0, saturationmodifier, false);
        this.saturationModifier = saturationmodifier;
        this.maxStackSize = 1;
        this.thirstReplenish = thirstreplenish;
    }

    @Override
    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (Loader.isModLoaded("ThirstMod"))
    	{
        	ThirstModHooks.addThirst(par3EntityPlayer, thirstReplenish, saturationModifier);
    	}
    	par1ItemStack.stackSize--;
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        return par1ItemStack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.drink;
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 32;
    }
    
    @Override
    public String getTextureFile()
    {
        return "/dayz/images/food.png";
    }
}
