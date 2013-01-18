package dayz.common.items;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBloodBag extends Item
{
    private EnumAction acionOnUse;

    public ItemBloodBag(int i)
    {
        super(i);
        maxStackSize = 1;
    }
    
    @Override
    public boolean itemInteractionForEntity(ItemStack itemstack, EntityLiving entityliving)
    {
    	if (entityliving instanceof EntityPlayer)
	    {
	       	if (!entityliving.isOnLadder() && !entityliving.isAirBorne && !entityliving.isBurning() && !entityliving.isEating() && !entityliving.isSprinting() && !entityliving.isWet() && entityliving.getHealth() < 20)
	      	{
	       		itemstack.stackSize--;
	       		entityliving.heal(20);
	       	}
	   	}
	   	
    	if (entityliving instanceof EntityPlayerMP)
	   	{
	       	if (!entityliving.isOnLadder() && !entityliving.isAirBorne && !entityliving.isBurning() && !entityliving.isEating() && !entityliving.isSprinting() && !entityliving.isWet() && entityliving.getHealth() < 20)
	        {
	        	itemstack.stackSize--;
	        	entityliving.heal(20);
	        }
	    }    		
        return true;    
    }
	
    @Override
    public String getTextureFile()
    {
        return "/dayz/images/heal.png";
    }
}