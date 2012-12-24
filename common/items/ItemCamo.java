package dayz.common.items;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;
import dayz.DayZ;

public class ItemCamo extends ItemArmor implements IArmorTextureProvider
{
    /** Holds the 'base' maxDamage that each armorType have. */
    private static final int[] maxDamageArray = new int[] {11, 16, 15, 13};

    /**
     * Stores the armor type: 0 is helmet, 1 is plate, 2 is legs and 3 is boots
     */
    public final int armorType;

    /** Holds the amount of damage that the armor reduces at full durability. */
    public final int damageReduceAmount;

    /**
     * Used on RenderPlayer to select the correspondent armor to be rendered on the player: 0 is cloth, 1 is chain, 2 is
     * iron, 3 is diamond and 4 is gold.
     */
    public final int renderIndex;

    /** The EnumArmorMaterial used for this ItemArmor */
    private final EnumArmorMaterial material;

    public ItemCamo(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
    {
        super(par1, par2EnumArmorMaterial, par4, par4);
        this.material = par2EnumArmorMaterial;
        this.armorType = par4;
        this.renderIndex = par3;
        this.damageReduceAmount = par2EnumArmorMaterial.getDamageReductionAmount(par4);
        this.setMaxDamage(par2EnumArmorMaterial.getDurability(par4));
        this.maxStackSize = 1;
        this.setCreativeTab(DayZ.creativeTabDayZ);
    }

    @Override
    public String getTextureFile()
    {
        return "/dayz/images/armor.png";
    }

    @Override
    public String getArmorTextureFile(ItemStack itemstack)
    {
        if (itemstack.itemID == DayZ.camohelmet.shiftedIndex || itemstack.itemID == DayZ.camochest.shiftedIndex || itemstack.itemID == DayZ.camoboots.shiftedIndex)
        {
            return "/dayz/images/armor/camo_1.png";
        }

        if (itemstack.itemID == DayZ.camolegs.shiftedIndex)
        {
            return "/dayz/images/armor/camo_2.png";
        }

        return "/dayz/images/armor/camo_2.png";
    }
}