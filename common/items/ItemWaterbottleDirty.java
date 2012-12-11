package dayz.common.items;

import dayz.DayZ;
import dayz.common.playerdata.PlayerData;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumAction;
import net.minecraft.src.Item;
import net.minecraft.src.ItemFood;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Potion;
import net.minecraft.src.PotionEffect;
import net.minecraft.src.World;

public class ItemWaterbottleDirty extends ItemFood
{
    public final int field_35430_a = 32;
    private final int healAmount;
    private final float saturationModifier;
    private final boolean isWolfsFavoriteMeat;
    private boolean alwaysEdible;
    private int potionId;
    private int potionDuration;
    private int potionAmplifier;
    private float potionEffectProbability;
    private boolean causeHarm;
    private Item itemToReturn;

    public ItemWaterbottleDirty(int par1, int par2, float par3, boolean par4)
    {
        super(par1, par2, par3, par4);
        healAmount = par2;
        isWolfsFavoriteMeat = par4;
        saturationModifier = par3;
        maxStackSize = 1;
    }

    public ItemWaterbottleDirty(int par1, int par2, boolean par3)
    {
        this(par1, par2, 0.6F, par3);
    }

    @Override
    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	par1ItemStack.stackSize--;
    	PlayerData.subtractThirst(par3EntityPlayer, 1000);
    	par3EntityPlayer.getFoodStats().addStats(this);
    	par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.poison.id, 30 * 20, 6));
    	par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 30 * 20, 6));
    	par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
    	new ItemStack(DayZ.waterbottleempty);
    	return par1ItemStack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.drink;
    }

    public int getHealAmount()
    {
        return healAmount;
    }

    @Override
    public String getTextureFile()
    {
        return "/dayz/images/food.png";
    }
}
