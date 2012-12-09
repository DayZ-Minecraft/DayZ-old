package dayz.common.items;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import dayz.common.EffectBleeding;
import dayz.common.EffectZombification;

public class ItemDayzHeal extends Item
{
    private int healAmount;
    private boolean stopBleeding;
    private boolean stopInfection;

    public ItemDayzHeal(int i, int amountToHeal, boolean stopBleeding, boolean stopInfection)
    {
        super(i);
        maxStackSize = 1;
        healAmount = amountToHeal;
        this.stopBleeding = stopBleeding;
        this.stopInfection = stopInfection;
        
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
        if (stopBleeding == true)
        {
        	if (entityplayer.isPotionActive(EffectBleeding.INSTANCE))
        	{
        		entityplayer.removePotionEffect(EffectBleeding.INSTANCE.id);
        	}
        }
        if (stopInfection == true)
        {
        	if (entityplayer.isPotionActive(EffectZombification.INSTANCE))
        	{
        		entityplayer.removePotionEffect(EffectZombification.INSTANCE.id);
        	}
        }
        return itemstack;
    }

    @Override
    public String getTextureFile()
    {
        return "/dayz/images/heal.png";
    }
}
