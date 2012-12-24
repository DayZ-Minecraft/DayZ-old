package dayz.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
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
        	entityplayer.removePotionEffect(EffectBleeding.INSTANCE.id);
        }
        if (stopInfection == true)
        {
        	entityplayer.removePotionEffect(EffectZombification.INSTANCE.id);
        }
        return itemstack;
    }

    @Override
    public String getTextureFile()
    {
        return "/dayz/images/heal.png";
    }
}
