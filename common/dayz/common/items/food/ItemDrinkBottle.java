package dayz.common.items.food;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import dayz.DayZ;
import dayz.common.items.ItemMod;

public class ItemDrinkBottle extends ItemMod
{
    private final int healAmount;

    private boolean isAlcohol;

    public ItemDrinkBottle(int itemId, int healAmount)
    {
        super(itemId);
        this.healAmount = healAmount;
        isAlcohol = false;
        setMaxDamage(3);
    }

    @Override
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        DayZ.thirst().subtractThirst(entityPlayer, healAmount);
        itemStack.damageItem(1, entityPlayer);
        if (isAlcohol == true)
        {
            entityPlayer.addPotionEffect(new PotionEffect(Potion.confusion.id, 30 * 20, 1));
        }
        world.playSoundAtEntity(entityPlayer, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        return itemStack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.drink;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 32;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        if (itemStack.getItemDamage() != 3)
        {
            entityPlayer.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));
        }
        return itemStack;
    }

    public Item isAlcohol(boolean isAlcohol)
    {
        this.isAlcohol = isAlcohol;
        return this;
    }
}
