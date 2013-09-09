package dayz.common.items.food;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dayz.common.items.ItemMod;
import dayz.common.items.Items;
import dayz.common.misc.Util;

public class ItemFoodCanned extends ItemMod
{
    public ItemFoodCanned(int id, int healAmount)
    {
        super(id);
        setHasSubtypes(true);
        this.healAmount = healAmount;
        setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    private float saturationModifier;
    private int healAmount;
    private boolean alwaysEdible;

    @Override
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
		entityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.foodCanEmpty, 1, itemStack.getItemDamage()));
        --itemStack.stackSize;
        entityPlayer.getFoodStats().addStats(healAmount, saturationModifier);
        world.playSoundAtEntity(entityPlayer, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        onFoodEaten(itemStack, world, entityPlayer);
        return itemStack;
    }

    protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
		entityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.foodCanEmpty, getDamage(itemStack)));
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 32;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.eat;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        if (entityPlayer.canEat(alwaysEdible))
        {
            entityPlayer.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));
        }

        return itemStack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int damage)
    {
        return itemIcon = icons[damage];
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int itemId, CreativeTabs creativeTab, List containerList)
    {
        for (int damage = 0; damage < 6; ++damage)
        {
            containerList.add(new ItemStack(itemId, 1, damage));
        }
    }

    @Override
    public void registerIcons(IconRegister register)
    {
        icons = new Icon[6];

        for (int damage = 0; damage < 6; ++damage)
        {
            icons[damage] = register.registerIcon(Util.ID + ":" + getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1) + damage);
        }
    }
}