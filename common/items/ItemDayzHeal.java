package dayz.common.items;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumAction;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemDayzHeal extends Item
{
    private int healAmount;
    private boolean cancelEffects;
    private EnumAction acionOnUse;

    public ItemDayzHeal(int i, int amountToHeal, boolean getcancelEffects)
    {
        super(i);
        maxStackSize = 1;
        healAmount = amountToHeal;
        cancelEffects = getcancelEffects;
    }

    public int getHealAmount()
    {
        return healAmount;
    }
    
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
    	itemstack.stackSize--;
        entityplayer.heal(healAmount);
        if (cancelEffects == true)
        {
        	entityplayer.clearActivePotions();
        }
        return itemstack;
    }

    @Override
    public String getTextureFile()
    {
        return "/dayz/images/heal.png";
    }
}
