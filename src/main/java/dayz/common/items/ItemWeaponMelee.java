package dayz.common.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWeaponMelee extends Item
{
    private float weaponDamage;
    private final EnumToolMaterial toolMaterial;
    private int textureIndex;

    /*
     * Day Z Melee Weapon class. Params = Item Id, EnumToolMaterial, damage
     */
    public ItemWeaponMelee(int par1, EnumToolMaterial par2EnumToolMaterial, float damage, int textureIndex)
    {
        super(par1);
        toolMaterial = par2EnumToolMaterial;
        maxStackSize = 1;
        setMaxDamage(par2EnumToolMaterial.getMaxUses());
        weaponDamage = damage;
        this.textureIndex = textureIndex;
    }

    /**
     * Current implementations of this method in child classes do not use the
     * entry argument beside ev. They just raise
     * the damage on the stack.
     */
    @Override
    public boolean onLeftClickEntity(ItemStack itemstack, EntityPlayer player, Entity entity)
    {
        if (entity instanceof EntityLivingBase)
        {
            ((EntityLivingBase) entity).attackEntityFrom(DamageSource.causePlayerDamage(player), weaponDamage);
            itemstack.damageItem(1, (EntityLivingBase) entity);
        }
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLivingBase par7EntityLiving)
    {
        if (Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D)
        {
            par1ItemStack.damageItem(2, par7EntityLiving);
        }

        return true;
    }

    @SideOnly(Side.CLIENT)
    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    @Override
    public boolean isFull3D()
    {
        return true;
    }

    /**
     * returns the action that specifies what animation to play when the items
     * is being used
     */
    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.block;
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is
     * pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }

    public String func_77825_f()
    {
        return toolMaterial.toString();
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        switch (textureIndex)
        {
            case 0:
                itemIcon = par1IconRegister.registerIcon("dayz:baseballbat");
                return;
            case 1:
                itemIcon = par1IconRegister.registerIcon("dayz:baseballbatnailed");
                return;
            case 2:
                itemIcon = par1IconRegister.registerIcon("dayz:plank");
                return;
            case 3:
                itemIcon = par1IconRegister.registerIcon("dayz:planknailed");
                return;
            case 4:
                itemIcon = par1IconRegister.registerIcon("dayz:pipe");
                return;
            case 5:
                itemIcon = par1IconRegister.registerIcon("dayz:crowbar");
                return;
            case 6:
                itemIcon = par1IconRegister.registerIcon("dayz:machete");
                return;
        }
    }
}
