package dayz;

import java.util.Random;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
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
import dayz.client.ClientTickHandler;
import dayz.common.CommonEvents;
import dayz.common.CommonPlayerHandler;
import dayz.common.CommonProxy;
import dayz.common.CommonTickHandler;
import dayz.common.items.ItemAk74u;
import dayz.common.items.ItemAmmo;
import dayz.common.items.ItemBloodBag;
import dayz.common.items.ItemCamo;
import dayz.common.items.ItemDayzDrink;
import dayz.common.items.ItemDayzFood;
import dayz.common.items.ItemDayzHeal;
import dayz.common.items.ItemDbShotgun;
import dayz.common.items.ItemEmptyBottle;
import dayz.common.items.ItemEmptyCan;
import dayz.common.items.ItemEnfield;
import dayz.common.items.ItemFirestarter;
import dayz.common.items.ItemGlock17;
import dayz.common.items.ItemGrenade;
import dayz.common.items.ItemMakarov;
import dayz.common.items.ItemRemington;
import dayz.common.items.ItemWaterbottleDirty;
import dayz.common.items.ItemWaterbottleFull;
import dayz.common.items.ItemWeaponMelee;
import dayz.common.items.ItemWhiskeybottleFull;
import dayz.common.misc.ChatHandler;
import dayz.common.misc.CreativeTabDayZ;
import dayz.common.misc.Updater;
import dayz.common.misc.Util;
import dayz.common.world.WorldTypeBase;
import dayz.common.world.WorldTypeSnow;
import dayz.common.world.WorldTypeTaiga;
import dayz.common.world.biomes.BiomeGenForest;
import dayz.common.world.biomes.BiomeGenPlainsDayZ;
import dayz.common.world.biomes.BiomeGenRiverDayZ;
import dayz.common.world.biomes.BiomeGenSnowDayZ;

@NetworkMod(clientSideRequired = true, serverSideRequired = false, versionBounds = Util.VERSION)
@Mod(modid = Util.ID, name = Util.NAME, version = Util.VERSION)
public class DayZ
{
    public static Logger logger = Logger.getLogger("Minecraft");
    public static EnumArmorMaterial enumArmorMaterialCamo = EnumHelper.addArmorMaterial("camo", 29, new int[]
    { 2, 6, 5, 2 }, 9);
    public static final BiomeGenBase biomeDayZForest = (new BiomeGenForest(25));
    public static final BiomeGenBase biomeDayZPlains = (new BiomeGenPlainsDayZ(26));
    public static final BiomeGenBase biomeDayZRiver = (new BiomeGenRiverDayZ(27));
    public static final BiomeGenBase biomeDayZSnowPlains = (new BiomeGenSnowDayZ(28).setMinMaxHeight(0.0F, 0.0F).setBiomeName("Snow Plains").setEnableSnow());
    public static final BiomeGenBase biomeDayZSnowMountains = (new BiomeGenSnowDayZ(29).setMinMaxHeight(0.0F, 0.5F).setBiomeName("Snow Mountains").setEnableSnow());
    public static WorldTypeBase worldTypeBase = new WorldTypeTaiga();
    public static WorldTypeBase worldTypeSnow = new WorldTypeSnow();
    public static CreativeTabs creativeTabDayZ = new CreativeTabDayZ();
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
    
    public static int barbedwireID;
    public static int dayzChestID;
    public static int chainlinkfenceID;
    public static int sandbagblockID;
    public static int nailsID;
    public static boolean canCheckUpdate;
    public static boolean canShowDebugScreen;
    public static boolean canShowNameOnDebugScreen;
    public static boolean canShowCoordinatesOnDebugScreen;
    public static boolean canGenerateExplosives;
    public static int chanceToRegenChestContents;
    public static boolean canSpawnZombiesInDefaultWorld;

    public static final Item matches = (new ItemFirestarter(3000, 8)).setUnlocalizedName("matches");
    public static final Item emptyCan = new ItemEmptyCan(3043).setUnlocalizedName("emptycan").setCreativeTab(DayZ.creativeTabDayZ);

    public static final Item heinz = new ItemDayzFood(3002, 6, 1, false, 0).setUnlocalizedName("heinz").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item cannedspag = new ItemDayzFood(3003, 6, 1, false, 1).setUnlocalizedName("cannedspag").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item cannedbeans = new ItemDayzFood(3004, 6, 1, false, 2).setUnlocalizedName("cannedbeans").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item cannedfish = new ItemDayzFood(3005, 6, 1, false, 3).setUnlocalizedName("cannedfish").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item chocolate = new ItemDayzFood(3006, 4, 0.5F, false, 4).setUnlocalizedName("chocolate").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item cannedpasta = new ItemDayzFood(3035, 6, 1, false, 5).setUnlocalizedName("cannedpasta").setCreativeTab(DayZ.creativeTabDayZ);

    public static final Item waterbottlefull = new ItemWaterbottleFull(3007, 6000).setUnlocalizedName("waterbottlefull").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item waterbottledirty = new ItemWaterbottleDirty(3012, 0, 2, false).setUnlocalizedName("waterbottledirty").setCreativeTab(DayZ.creativeTabDayZ);

    public static final Item waterbottleempty = new ItemEmptyBottle(3008, Block.waterMoving.blockID, true, 0).setUnlocalizedName("waterbottleempty").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item whiskeybottleempty = new ItemEmptyBottle(3009, Block.waterMoving.blockID, false, 1).setUnlocalizedName("whiskeybottleempty").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item vodkabottleempty = new ItemEmptyBottle(3049, Block.waterMoving.blockID, false, 2).setUnlocalizedName("vodkabottleempty").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item ciderbottleempty = new ItemEmptyBottle(3051, Block.waterMoving.blockID, false, 3).setUnlocalizedName("ciderbottleempty").setCreativeTab(DayZ.creativeTabDayZ);

    public static final Item vodkabottlefull = new ItemWhiskeybottleFull(3050, 3000, vodkabottleempty, 0).setUnlocalizedName("vodkabottlefull").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item ciderbottlefull = new ItemWhiskeybottleFull(3052, 3000, ciderbottleempty, 1).setUnlocalizedName("ciderbottlefull").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item whiskeybottlefull = new ItemWhiskeybottleFull(3010, 3000, whiskeybottleempty, 2).setUnlocalizedName("whiskeybottlefull").setCreativeTab(DayZ.creativeTabDayZ);

    public static final Item lemonade = new ItemDayzDrink(3011, 3000, 0).setUnlocalizedName("lemonade").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item colaDrink = new ItemDayzDrink(3044, 3000, 1).setUnlocalizedName("colaDrink").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item colaDrink2 = new ItemDayzDrink(3045, 3000, 2).setUnlocalizedName("colaDrink2").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item energyDrink = new ItemDayzDrink(3046, 3000, 3).setUnlocalizedName("energyDrink").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item colaDrink3 = new ItemDayzDrink(3047, 3000, 4).setUnlocalizedName("colaDrink3").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item appleDrink = new ItemDayzDrink(3048, 3000, 5).setUnlocalizedName("appleDrink").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item orangeDrink = new ItemDayzDrink(3053, 3000, 6).setUnlocalizedName("orangeDrink").setCreativeTab(DayZ.creativeTabDayZ);

    public static final Item bandage = new ItemDayzHeal(3013, 0, true, false, 0).setUnlocalizedName("bandage").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item antibiotics = new ItemDayzHeal(3014, 0, false, true, 1).setUnlocalizedName("antibiotics").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item bloodbag = new ItemBloodBag(3015).setUnlocalizedName("bloodbag").setCreativeTab(DayZ.creativeTabDayZ);

    public static final Item ak74u = new ItemAk74u(3016).setUnlocalizedName("ak74u").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item makarov = new ItemMakarov(3017).setUnlocalizedName("markov").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item remington = new ItemRemington(3018).setUnlocalizedName("remington").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item leeenfield = new ItemEnfield(3019).setUnlocalizedName("leeenfield").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item glock17 = new ItemGlock17(3020).setUnlocalizedName("glock17").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item dbshotgun = new ItemDbShotgun(3036).setUnlocalizedName("doublebarrelshotgun").setCreativeTab(DayZ.creativeTabDayZ);

    public static final Item ak74uammo = new ItemAmmo(3021, 0).setUnlocalizedName("ak74uammo").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item makarovammo = new ItemAmmo(3022, 1).setUnlocalizedName("markovammo").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item remingtonammo = new ItemAmmo(3023, 2).setUnlocalizedName("remingtonammo").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item leeenfieldammo = new ItemAmmo(3024, 3).setUnlocalizedName("leeenfieldammo").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item glock17ammo = new ItemAmmo(3025, 4).setUnlocalizedName("glock17ammo").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item dbshotgunammo = new ItemAmmo(3037, 5).setUnlocalizedName("dbshotgunammo").setCreativeTab(DayZ.creativeTabDayZ);

    public static final Item baseballbat = (new ItemWeaponMelee(3026, EnumToolMaterial.WOOD, 6, 0)).setUnlocalizedName("baseballbat").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item baseballbatnailed = (new ItemWeaponMelee(3027, EnumToolMaterial.WOOD, 8, 1)).setUnlocalizedName("baseballbatnailed").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item plank = (new ItemWeaponMelee(3028, EnumToolMaterial.WOOD, 7, 2)).setUnlocalizedName("plank").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item planknailed = (new ItemWeaponMelee(3029, EnumToolMaterial.WOOD, 8, 3)).setUnlocalizedName("planknailed").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item pipe = (new ItemWeaponMelee(3030, EnumToolMaterial.WOOD, 8, 4)).setUnlocalizedName("pipe").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item crowbar = (new ItemWeaponMelee(3041, EnumToolMaterial.WOOD, 8, 5)).setUnlocalizedName("crowbar").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item machete = (new ItemWeaponMelee(3042, EnumToolMaterial.WOOD, 7, 6)).setUnlocalizedName("machate").setCreativeTab(DayZ.creativeTabDayZ);

    public static final Item camohelmet = (new ItemCamo(3031, enumArmorMaterialCamo, 4, 0)).setUnlocalizedName("camohelmet");
    public static final Item camochest = (new ItemCamo(3032, enumArmorMaterialCamo, 4, 1)).setUnlocalizedName("camochest");
    public static final Item camolegs = (new ItemCamo(3033, enumArmorMaterialCamo, 4, 2)).setUnlocalizedName("camolegs");
    public static final Item camoboots = (new ItemCamo(3034, enumArmorMaterialCamo, 4, 3)).setUnlocalizedName("camoboots");
    public static final Item grenade = (new ItemGrenade(3039)).setUnlocalizedName("grenade");

    @EventHandler
    public void preload(FMLPreInitializationEvent event)
    {
        ChatHandler.log = Logger.getLogger(Util.ID);
        MinecraftForge.EVENT_BUS.register(new CommonEvents());
        CommonProxy.preload(event);
        if (FMLCommonHandler.instance().getSide().isClient())
        {
            MinecraftForge.EVENT_BUS.register(new ClientEvents());
            TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
            GameRegistry.registerPlayerTracker(new ClientPlayerHandler());
            ClientProxy.preload(event);
        }
        ChatHandler.logInfo("Day Z " + Util.VERSION + Updater.preRelease() + " Preload Complete");
    }

    /**
     * Called at the "initialization" phase of loading.
     * 
     * @param event
     *            FMLInitializationEvent
     */
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
        ChatHandler.logInfo("Day Z " + Util.VERSION + Updater.preRelease() + " Load Complete");
    }

    /**
     * Called at the "post-initialization" phase of loading.
     * 
     * @param event
     *            FMLPostInitializationEvent
     */
    @EventHandler
    public void postload(FMLPostInitializationEvent event)
    {
        CommonProxy.postload(event);
        if (FMLCommonHandler.instance().getSide().isClient())
        {
            ClientProxy.postload(event);
        }
        ChatHandler.logInfo("Day Z " + Util.VERSION + Updater.preRelease() + " Postload Complete");
    }

    public static Block barbedwire;
    public static Block dayzChest;
    public static Block chainlinkfence;
    public static Block sandbagblock;
    public static Block nails;
}