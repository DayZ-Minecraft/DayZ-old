package dayz.common.items.food;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dayz.DayZ;
import dayz.common.items.ItemMod;
import dayz.common.items.Items;

public class ItemDrinkCanned extends ItemMod
{
    private final int healAmount;
    private int potionId;
    private int potionDuration;
    private int potionAmplifier;
    private float potionEffectProbability;
    @SideOnly(Side.CLIENT)
    private Icon[] icons;
	private String[] names = new String[] {"Beer", "Lemon_Soda", "Cola", "Cola", "Energy_Drink", "Orange_Soda"};

    public ItemDrinkCanned(int itemId, int healAmount)
    {
        super(itemId);
        this.healAmount = healAmount;
        setHasSubtypes(true);
    }

    @Override
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
		entityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.drinkCanEmpty, 1, itemStack.getItemDamage()));
        --itemStack.stackSize;
        DayZ.thirst().subtractThirst(entityPlayer, healAmount);
        onFoodEaten(itemStack, world, entityPlayer);
        return itemStack;
    }

    protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        if (!world.isRemote && potionId > 0 && world.rand.nextFloat() < potionEffectProbability)
        {
            entityPlayer.addPotionEffect(new PotionEffect(potionId, potionDuration * 20, potionAmplifier));
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.drinkCanEmpty, getDamage(itemStack)));
        }
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 32;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.drink;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
    	int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, 5);
        return super.getUnlocalizedName() + "." + names[i].toLowerCase();
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        entityPlayer.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));
        return itemStack;
    }

    public ItemDrinkCanned setPotionEffect(int potionId, int potionDuration, int potionAmplifier, float potionEffectProbability)
    {
        this.potionId = potionId;
        this.potionDuration = potionDuration;
        this.potionAmplifier = potionAmplifier;
        this.potionEffectProbability = potionEffectProbability;
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int damage)
    {
    	int j = MathHelper.clamp_int(damage, 0, 5);
        return icons[j];
    }

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
            icons[damage] = register.registerIcon(DayZ.meta.modId + ":" + new String("drinkCanned").substring(new String("drinkCanned").indexOf(".") + 1) + damage);
        }
    }
}
