package dayz.common.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import dayz.DayZ;
import dayz.common.misc.Util;

public class ItemCamo extends ItemArmor
{
    private Object iconIndex;

    public ItemCamo(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
    {
        super(par1, par2EnumArmorMaterial, par3, par4);
    }

    /**
     * To Add Armor Texture to Player when worn.
     */
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
        if (stack.itemID == DayZ.camohelmet.itemID || stack.itemID == DayZ.camochest.itemID || stack.itemID == DayZ.camoboots.itemID)
        {
            return Util.ID + ":textures/armor/camo_1.png";
        }
        if (stack.itemID == DayZ.camolegs.itemID)
        {
            return Util.ID + ":textures/armor/camo_2.png";
        }
        return Util.ID + ":textures/armor/camo_2.png";
    }

    /**
     * To Add Armor Texture to Item Armor (when held or in inventory)
     */
    @Override
    public void registerIcons(IconRegister iconReg)
    {
        if (itemID == DayZ.camoboots.itemID)
        {
            itemIcon = iconReg.registerIcon(Util.ID + ":camoboots");
        }
        else if (itemID == DayZ.camolegs.itemID)
        {
            itemIcon = iconReg.registerIcon(Util.ID + ":camopants");
        }
        else if (itemID == DayZ.camochest.itemID)
        {
            itemIcon = iconReg.registerIcon(Util.ID + ":camochest");
        }
        else if (itemID == DayZ.camohelmet.itemID)
        {
            itemIcon = iconReg.registerIcon(Util.ID + ":camohelmet");
        }
    }
}