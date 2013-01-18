package dayz.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import dayz.DayZ;
import dayz.common.playerdata.PlayerStats;

public class ItemWhiskeybottleFull extends ItemDayzDrink
{
    public final int field_35430_a = 32;
    private final float saturationModifier;
    private final int thirstReplenish;
    private boolean alwaysEdible;
    private int potionId;
    private int potionDuration;
    private int potionAmplifier;
    private float potionEffectProbability;

    
    public ItemWhiskeybottleFull(int itemID, int thirstreplenish, float saturationmodifier)
    {
        super(itemID, 0, saturationmodifier);
        this.saturationModifier = saturationmodifier;
        this.maxStackSize = 1;
        this.thirstReplenish = thirstreplenish;
    }


    public ItemWhiskeybottleFull(int par1, int par2, boolean par3)
    {
        this(par1, par2, 0.6F);
    }

    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par1ItemStack.stackSize--;
        PlayerStats.subtractThirst(par3EntityPlayer, 3000);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 30 * 20, 6));
        par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.confusion.id, 30 * 20, 6));

        if (par1ItemStack.stackSize <= 0)
        {
            return new ItemStack(DayZ.whiskeybottleempty);
        }
        else
        {
            par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(DayZ.whiskeybottleempty));
        }

        return par1ItemStack;
    }

    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.drink;
    }
    
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    { 	
        entityplayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
        return itemstack;
    }

    @Override
    public String getTextureFile()
    {
        return "/dayz/images/food.png";
    }
}
