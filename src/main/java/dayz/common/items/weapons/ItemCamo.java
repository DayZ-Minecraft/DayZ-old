package dayz.common.items.weapons;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import dayz.DayZ;
import dayz.common.items.Items;
import dayz.common.misc.Util;

public class ItemCamo extends ItemArmor
{
    public ItemCamo(int itemId, EnumArmorMaterial par2EnumArmorMaterial, int renderIndex, int armourType)
    {
        super(itemId, par2EnumArmorMaterial, renderIndex, armourType);
        maxStackSize = 1;
        setCreativeTab(DayZ.creativeTab);
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, int slot, int layer)
    {
        if (itemStack.itemID == Items.camoHelmet.itemID || itemStack.itemID == Items.camoChest.itemID || itemStack.itemID == Items.camoBoots.itemID)
        {
            return Util.ID + ":textures/armor/camo_1.png";
        }
        if (itemStack.itemID == Items.camoLegs.itemID)
        {
            return Util.ID + ":textures/armor/camo_2.png";
        }
        return Util.ID + ":textures/armor/camo_2.png";
    }

    @Override
    public void registerIcons(IconRegister register)
    {
        if (itemID == Items.camoBoots.itemID)
        {
            itemIcon = register.registerIcon(Util.ID + ":camoBoots");
        }
        else
        {
            if (itemID == Items.camoLegs.itemID)
            {
                itemIcon = register.registerIcon(Util.ID + ":camoLegs");
            }
            else
            {
                if (itemID == Items.camoChest.itemID)
                {
                    itemIcon = register.registerIcon(Util.ID + ":camoChest");
                }
                else
                {
                    if (itemID == Items.camoHelmet.itemID)
                    {
                        itemIcon = register.registerIcon(Util.ID + ":camoHelmet");
                    }
                }
            }
        }
    }
}