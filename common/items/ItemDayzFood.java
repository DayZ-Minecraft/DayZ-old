package dayz.common.items;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemFood;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemDayzFood extends ItemFood
{
    /** The amount this food item heals the player. */
    private final int healAmount;

    /** The amount this food item restores the saturation. */
    private final float saturationModifier;

    /** Whether wolves like this food (true for raw and cooked porkchop). */
    private final boolean isWolfsFavoriteMeat;

    public ItemDayzFood(int par1, int par2, float par3, boolean par4)
    {
        super(par1, par2, par3, par4);
        healAmount = par2;
        isWolfsFavoriteMeat = par4;
        saturationModifier = par3;
        maxStackSize = 1;
    }

    public ItemDayzFood(int par1, int par2, boolean par3)
    {
        this(par1, par2, 0.6F, par3);
    }
    
    @Override
    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        --par1ItemStack.stackSize;
        par3EntityPlayer.heal(healAmount);
        par3EntityPlayer.getFoodStats().addStats(this);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        this.func_77849_c(par1ItemStack, par2World, par3EntityPlayer);
        return par1ItemStack;
    }

    @Override
    public String getTextureFile()
    {
        return "/dayz/images/food.png";
    }
}