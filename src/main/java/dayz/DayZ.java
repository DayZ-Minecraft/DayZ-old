package dayz;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.EnumHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import dayz.common.CommonProxy;
import dayz.common.blocks.Blocks;
import dayz.common.items.Items;
import dayz.common.misc.CreativeTab;
import dayz.common.misc.Util;
import dayz.common.thirst.Thirst;
import dayz.common.world.biomes.Biomes;

@NetworkMod(clientSideRequired = true, serverSideRequired = false, versionBounds = Util.VERSION)
@Mod(modid = Util.ID, name = Util.NAME, version = Util.VERSION)
public class DayZ
{
    private Blocks block = new Blocks();
    private Biomes biome = new Biomes();
    private Items item = new Items();
    private Thirst thirst = new Thirst();

    public static EnumArmorMaterial enumArmorMaterialCamo = EnumHelper.addArmorMaterial("camo", 29, new int[]
    { 2, 6, 5, 2 }, 9);

    public static CreativeTabs creativeTab = new CreativeTab();

    @Instance(Util.ID)
    public static DayZ INSTANCE;

    @SidedProxy(modId = Util.ID, clientSide = "dayz.client.ClientProxy", serverSide = "dayz.common.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preload(FMLPreInitializationEvent event)
    {
        proxy.preload(event);
    }

    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        proxy.load(event);
    }

    @EventHandler
    public void postload(FMLPostInitializationEvent event)
    {
        proxy.postload(event);
    }

    /**
     * Instance of the items class used in DayZ
     * 
     * @return
     */
    public static Items item()
    {
        return INSTANCE.item;
    }

    /**
     * Instance of the blocks class used in DayZ
     * 
     * @return
     */
    public static Blocks block()
    {
        return INSTANCE.block;
    }

    /**
     * Instance of the thirst class used in DayZ
     * 
     * @return
     */
    public static Thirst thirst()
    {
        return INSTANCE.thirst;
    }

    /**
     * Instance of the biomes class used in DayZ
     * 
     * @return
     */
    public static Biomes biomes()
    {
        return INSTANCE.biome;
    }
}