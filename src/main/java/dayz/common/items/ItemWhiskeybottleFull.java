package dayz.common.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import dayz.DayZ;

public class ItemWhiskeybottleFull extends ItemDayzDrink
{
    private int thirst;
    private Item returnItem;
    private int textureIndex;

    public ItemWhiskeybottleFull(int itemID, int thirst, Item returnItem, int textureIndex)
    {
        super(itemID, thirst, 0);
        this.thirst = thirst;
        this.returnItem = returnItem;
        maxStackSize = 1;
        this.textureIndex = textureIndex;
    }

    @Override
    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par1ItemStack.stackSize--;
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 30 * 20, 6));
        par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.confusion.id, 30 * 20, 6));

        if (par1ItemStack.stackSize <= 0)
        {
            return new ItemStack(DayZ.whiskeybottleempty);
        }
        else
        {
            par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(returnItem));
        }

        return par1ItemStack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.drink;
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
                itemIcon = par1IconRegister.registerIcon("dayz:vodkafull");
                return;
            case 1:
                itemIcon = par1IconRegister.registerIcon("dayz:ciderfull");
                return;
            case 2:
                itemIcon = par1IconRegister.registerIcon("dayz:whiskeybottlefull");
                return;
        }
    }
}
