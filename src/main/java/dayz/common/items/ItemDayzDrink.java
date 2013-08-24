package dayz.common.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDayzDrink extends ItemFood
{
    private final int thirst;
    private int textureIndex;

    /**
     * Day Z Drink Item
     * 
     * @param itemID
     *            The ID of the item
     * @param saturationmodifier
     *            The hunger bar saturation to be restored
     * @param drinkEffect
     *            Potion effect of the drink
     */
    public ItemDayzDrink(int itemID, int thirst, int index)
    {
        super(itemID, 0, thirst, false);
        this.thirst = thirst;
        maxStackSize = 1;
        textureIndex = index;
    }

    @Override
    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par1ItemStack.stackSize--;
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        return par1ItemStack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.drink;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 32;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        entityplayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
        return itemstack;
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        switch (textureIndex)
        {
            case 0:
                itemIcon = par1IconRegister.registerIcon("dayz:lemonade");
                return;
            case 1:
                itemIcon = par1IconRegister.registerIcon("dayz:cola1");
                return;
            case 2:
                itemIcon = par1IconRegister.registerIcon("dayz:cola2");
                return;
            case 3:
                itemIcon = par1IconRegister.registerIcon("dayz:energydrink");
                return;
            case 4:
                itemIcon = par1IconRegister.registerIcon("dayz:cola3");
                return;
            case 5:
                itemIcon = par1IconRegister.registerIcon("dayz:applecan");
                return;
            case 6:
                itemIcon = par1IconRegister.registerIcon("dayz:orangesoda");
                return;
        }
    }
}
