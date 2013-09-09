package dayz.common.items.food;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dayz.common.items.ItemMod;
import dayz.common.misc.Util;

public class ItemCanEmpty extends ItemMod
{
    private Icon[] icons;

    public ItemCanEmpty(int itemId)
    {
        super(itemId);
        setHasSubtypes(true);
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
    public void registerIcons(IconRegister par1IconRegister)
    {
        icons = new Icon[6];

        for (int damage = 0; damage < 6; ++damage)
        {
            icons[damage] = par1IconRegister.registerIcon(Util.ID + ":" + getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1) + damage);
        }
    }
}