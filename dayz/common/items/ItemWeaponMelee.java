package dayz.common.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dayz.common.misc.Util;

public class ItemWeaponMelee extends Item
{
    private int weaponDamage;
    private final EnumToolMaterial toolMaterial;
    private int textureIndex;
    /*
     * Day Z Melee Weapon class. Params = Item Id, EnumToolMaterial, damage
     */
    public ItemWeaponMelee(int par1, EnumToolMaterial par2EnumToolMaterial, int damage, int textureIndex)
    {
        super(par1);
        this.toolMaterial = par2EnumToolMaterial;
        this.maxStackSize = 1;
        this.setMaxDamage(par2EnumToolMaterial.getMaxUses());
        this.weaponDamage = damage;
        this.textureIndex = textureIndex;
    }

     /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
    {
        par1ItemStack.damageItem(1, par3EntityLiving);
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLiving par7EntityLiving)
    {
        if ((double)Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D)
        {
            par1ItemStack.damageItem(2, par7EntityLiving);
        }

        return true;
    }

    /**
     * Returns the damage against a given entity.
     */
    @Override
    public int getDamageVsEntity(Entity par1Entity)
    {
        return this.weaponDamage;
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
     * returns the action that specifies what animation to play when the items is being used
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
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }

    public String func_77825_f()
    {
        return this.toolMaterial.toString();
    }
    
    public void registerIcons(IconRegister par1IconRegister)
    {
    	switch(this.textureIndex)
    	{
    		case 0: this.itemIcon = par1IconRegister.registerIcon("dayz:baseballbat"); return;
    		case 1: this.itemIcon = par1IconRegister.registerIcon("dayz:baseballbatnailed"); return;
    		case 2: this.itemIcon = par1IconRegister.registerIcon("dayz:plank"); return;
    		case 3: this.itemIcon = par1IconRegister.registerIcon("dayz:planknailed"); return;
    		case 4: this.itemIcon = par1IconRegister.registerIcon("dayz:pipe"); return;
    		case 5: this.itemIcon = par1IconRegister.registerIcon("dayz:crowbar"); return;
    		case 6: this.itemIcon = par1IconRegister.registerIcon("dayz:machete"); return;
    	}
    }
}
