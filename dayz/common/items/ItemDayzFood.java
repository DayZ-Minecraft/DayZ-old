package dayz.common.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import dayz.DayZ;
import dayz.common.misc.Util;

public class ItemDayzFood extends ItemFood
{
    /** The amount this food item heals the player. */
    private final int healAmount;

    /** The amount this food item restores the saturation. */
    private final float saturationModifier;

    /** Whether wolves like this food (true for raw and cooked porkchop). */
    private final boolean isWolfsFavoriteMeat;
    
    private int textureIndex;

    public ItemDayzFood(int par1, int par2, float par3, boolean par4, int index)
    {
        super(par1, par2, par3, par4);
        healAmount = par2;
        isWolfsFavoriteMeat = par4;
        saturationModifier = par3;
        maxStackSize = 1;
        this.textureIndex = index;
    }

    public ItemDayzFood(int par1, int par2, boolean par3, int index)
    {
        this(par1, par2, 0.6F, par3, index);
    }
    
    @Override
    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        --par1ItemStack.stackSize;
        par3EntityPlayer.heal(healAmount);
        par3EntityPlayer.getFoodStats().addStats(this);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        this.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
        return new ItemStack(DayZ.emptyCan);
    }

    public void registerIcons(IconRegister par1IconRegister)
    {
    	switch(this.textureIndex)
    	{
    		case 0: this.itemIcon = par1IconRegister.registerIcon("dayz:can2"); return;
    		case 1: this.itemIcon = par1IconRegister.registerIcon("dayz:can4"); return;
    		case 2: this.itemIcon = par1IconRegister.registerIcon("dayz:can1"); return;
    		case 3: this.itemIcon = par1IconRegister.registerIcon("dayz:sardines"); return;
    		case 4: this.itemIcon = par1IconRegister.registerIcon("dayz:chocolatebar"); return;
    		case 5: this.itemIcon = par1IconRegister.registerIcon("dayz:can5");return;
    	}
    }
}