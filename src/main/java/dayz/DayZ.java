package dayz;

import java.util.Random;
import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import dayz.client.ClientEvents;
import dayz.client.ClientPlayerHandler;
import dayz.client.ClientProxy;
import dayz.common.CommonEvents;
import dayz.common.CommonEventsTerrain;
import dayz.common.CommonPlayerHandler;
import dayz.common.CommonProxy;
import dayz.common.CommonTickHandler;
import dayz.common.blocks.Blocks;
import dayz.common.items.Items;
import dayz.common.misc.ChatHandler;
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

    public static Random random = new Random();

    /**
     * Check this instead of using the internet to check status.
     * Set by CommonProxy.DayZpreload().
     */
    public static boolean isUpToDate = true;

    /**
     * The instance of the mod.
     */
    @Instance(Util.ID)
    public static DayZ INSTANCE;

    /**
     * The metadata - contents of the mcmod.info
     */
    @Metadata(Util.ID)
    public static ModMetadata meta;

    @EventHandler
    public void preload(FMLPreInitializationEvent event)
    {
        ChatHandler.log = Logger.getLogger(Util.ID);
        MinecraftForge.EVENT_BUS.register(new CommonEvents());
        MinecraftForge.TERRAIN_GEN_BUS.register(new CommonEventsTerrain());
        CommonProxy.preload(event);
        if (FMLCommonHandler.instance().getSide().isClient())
        {
            MinecraftForge.EVENT_BUS.register(new ClientEvents());
            GameRegistry.registerPlayerTracker(new ClientPlayerHandler());
            ClientProxy.preload(event);
        }
    }

    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        CommonProxy.load(event);
        GameRegistry.registerPlayerTracker(new CommonPlayerHandler());
        TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);
        if (FMLCommonHandler.instance().getSide().isClient())
        {
            ClientProxy.load(event);
        }
    }

    @EventHandler
    public void postload(FMLPostInitializationEvent event)
    {
        CommonProxy.postload(event);
        if (FMLCommonHandler.instance().getSide().isClient())
        {
            ClientProxy.postload(event);
        }
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