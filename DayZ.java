package dayz;

import java.util.Random;
import java.util.logging.Logger;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import dayz.client.ClientProxy;
import dayz.common.CommonProxy;
import dayz.common.CreativeTabDayZ;
import dayz.common.items.ItemAk74u;
import dayz.common.items.ItemAmmo;
import dayz.common.items.ItemBloodBag;
import dayz.common.items.ItemCamo;
import dayz.common.items.ItemDayzDrink;
import dayz.common.items.ItemDayzFood;
import dayz.common.items.ItemDayzHeal;
import dayz.common.items.ItemDbShotgun;
import dayz.common.items.ItemEmptyBottle;
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
import dayz.common.world.BiomeGenForest;
import dayz.common.world.BiomeGenPlainsDayZ;
import dayz.common.world.BiomeGenRiverDayZ;
import dayz.common.world.BiomeGenSnowDayZ;
import dayz.common.world.WorldTypeBase;
import dayz.common.world.WorldTypeSnow;
import dayz.common.world.WorldTypeTaiga;

@NetworkMod(clientSideRequired = true, serverSideRequired = false, versionBounds = Util.VERSION)
@Mod(modid = Util.ID, name = Util.NAME, version = Util.VERSION)
public class DayZ
{	
	public static Logger logger = Logger.getLogger("Minecraft");
	public static EnumArmorMaterial enumArmorMaterialCamo = EnumHelper.addArmorMaterial("camo", 29, new int[] {2, 6, 5, 2}, 9);
    public static final BiomeGenBase biomeDayZForest = (new BiomeGenForest(25));
    public static final BiomeGenBase biomeDayZPlains = (new BiomeGenPlainsDayZ(26));
    public static final BiomeGenBase biomeDayZRiver = (new BiomeGenRiverDayZ(27));
    public static final BiomeGenBase biomeDayZSnowPlains = (new BiomeGenSnowDayZ(28).setMinMaxHeight(0.0F, 0.0F).setBiomeName("Snow Plains").setEnableSnow());
    public static final BiomeGenBase biomeDayZSnowMountains = (new BiomeGenSnowDayZ(29).setMinMaxHeight(0.0F, 0.5F).setBiomeName("Snow Mountains").setEnableSnow());
	public static WorldTypeBase worldTypeBase = new WorldTypeTaiga();
	public static WorldTypeBase worldTypeSnow = new WorldTypeSnow();
	public static CreativeTabs creativeTabDayZ = new CreativeTabDayZ();
	public static Random random = new Random();
	public static dayz.common.Properties properties;
	
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
    	
	public static int barbedwireID;
	public static int dayzchestallID;
	public static int dayzchestrareID;
	public static int dayzchestcommonID;
	public static int chainlinkfenceID;
	public static int sandbagblockID;
	public static int nailsID;
	
	public static boolean canCheckUpdate;
	public static boolean canShowDebugScreen;
	public static boolean canShowNameOnDebugScreen;
	public static boolean canShowCoordinatesOnDebugScreen;
	public static boolean canGenerateExplosives;
	public static int chanceToRegenChestContents;
	
    public static final Item matches = (new ItemFirestarter(3000, 8)).setIconCoord(2, 0).setItemName("matches");

	public static final Item heinz = new ItemDayzFood(3002, 6, 1, false).setIconCoord(11, 0).setItemName("heinz").setCreativeTab(DayZ.creativeTabDayZ);
	public static final Item cannedspag = new ItemDayzFood(3003, 6, 1, false).setIconCoord(2, 0).setItemName("cannedspag").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item cannedbeans = new ItemDayzFood(3004, 6, 1, false).setIconCoord(0, 0).setItemName("cannedbeans").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item cannedfish = new ItemDayzFood(3005, 6, 1, false).setIconCoord(1, 0).setItemName("cannedfish").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item chocolate = new ItemDayzFood(3006, 4, 0.5F, false).setIconCoord(3, 0).setItemName("chocolate").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item cannedpasta = new ItemDayzFood(3035, 6, 1, false).setIconCoord(12, 0).setItemName("cannedpasta").setCreativeTab(DayZ.creativeTabDayZ);

    public static final Item waterbottlefull = new ItemWaterbottleFull(3007, 8, 1.0F).setIconCoord(5, 0).setItemName("waterbottlefull").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item waterbottleempty = new ItemEmptyBottle(3008, Block.waterMoving.blockID, true).setIconCoord(8, 0).setItemName("waterbottleempty").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item whiskeybottleempty = new ItemEmptyBottle(3009, Block.waterMoving.blockID, false).setIconCoord(6, 0).setItemName("whiskeybottleempty").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item whiskeybottlefull = new ItemWhiskeybottleFull(3010, 4, 1.0F).setIconCoord(7, 0).setItemName("whiskeybottlefull").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item lemonade = new ItemDayzDrink(3011, 4, 1.0F).setIconCoord(4, 0).setItemName("lemonade").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item waterbottledirty = new ItemWaterbottleDirty(3012, 0, 2, false).setIconCoord(9, 0).setItemName("waterbottledirty").setCreativeTab(DayZ.creativeTabDayZ);

    public static final Item bandage = new ItemDayzHeal(3013, 10, false).setIconCoord(1, 0).setItemName("bandage").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item antibiotics = new ItemDayzHeal(3014, 0, true).setIconCoord(0, 0).setItemName("antibiotics").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item bloodbag = new ItemBloodBag(3015).setIconCoord(2, 0).setItemName("bloodbag").setCreativeTab(DayZ.creativeTabDayZ);

    public static final Item ak74u = new ItemAk74u(3016).setIconCoord(0, 0).setItemName("ak74u").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item makarov = new ItemMakarov(3017).setIconCoord(2, 0).setItemName("markov").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item remington = new ItemRemington(3018).setIconCoord(4, 0).setItemName("remington").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item leeenfield = new ItemEnfield(3019).setIconCoord(4, 2).setItemName("leeenfield").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item glock17 = new ItemGlock17(3020).setIconCoord(2, 2).setItemName("glock17").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item dbshotgun = new ItemDbShotgun(3036).setIconCoord(0, 2).setItemName("doublebarrelshotgun").setCreativeTab(DayZ.creativeTabDayZ);

    public static final Item ak74uammo = new ItemAmmo(3021).setIconCoord(1, 0).setItemName("ak74uammo").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item makarovammo = new ItemAmmo(3022).setIconCoord(3, 0).setItemName("markovammo").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item remingtonammo = new ItemAmmo(3023).setIconCoord(5, 0).setItemName("remingtonammo").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item leeenfieldammo = new ItemAmmo(3024).setIconCoord(5, 2).setItemName("leeenfieldammo").setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item glock17ammo = new ItemAmmo(3025).setIconCoord(3, 2).setItemName("glock17ammo").setCreativeTab(DayZ.creativeTabDayZ);
	public static final Item dbshotgunammo = new ItemAmmo(3037).setIconCoord(1, 2).setItemName("dbshotgunammo").setCreativeTab(DayZ.creativeTabDayZ);

    public static final Item baseballbat = (new ItemWeaponMelee(3026, EnumToolMaterial.WOOD, 6)).setItemName("baseballbat").setIconCoord(0, 1).setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item baseballbatnailed = (new ItemWeaponMelee(3027, EnumToolMaterial.WOOD, 8)).setItemName("baseballbatnailed").setIconCoord(1, 1).setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item plank = (new ItemWeaponMelee(3028, EnumToolMaterial.WOOD, 7)).setItemName("plank").setIconCoord(2, 1).setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item planknailed = (new ItemWeaponMelee(3029, EnumToolMaterial.WOOD, 8)).setItemName("planknailed").setIconCoord(3, 1).setCreativeTab(DayZ.creativeTabDayZ);
    public static final Item pipe = (new ItemWeaponMelee(3030, EnumToolMaterial.WOOD, 8)).setItemName("pipe").setIconCoord(4, 1).setCreativeTab(DayZ.creativeTabDayZ);

    public static final Item camohelmet = (new ItemCamo(3031, enumArmorMaterialCamo, 5, 0)).setItemName("camohelmet").setIconCoord(0, 0);
    public static final Item camochest = (new ItemCamo(3032, enumArmorMaterialCamo, 5, 1)).setItemName("camochest").setIconCoord(1, 0);
    public static final Item camolegs = (new ItemCamo(3033, enumArmorMaterialCamo, 5, 2)).setItemName("camolegs").setIconCoord(2, 0);
    public static final Item camoboots = (new ItemCamo(3034, enumArmorMaterialCamo, 5, 3)).setItemName("camoboots").setIconCoord(3, 0);
    public static final Item grenade = (new ItemGrenade(3039)).setItemName("grenade").setIconCoord(6, 0);
    
    /**
     * Called at the "pre-initialization" phase of loading.
     * @param event FMLPreInitializationEvent
     */
    @PreInit
    public void DayZpreload(FMLPreInitializationEvent event)
    { 
		MinecraftForge.EVENT_BUS.register(new CommonProxy());
		CommonProxy.DayZpreload(event);
    	if (FMLCommonHandler.instance().getSide().isClient())
    	{
    		MinecraftForge.EVENT_BUS.register(new ClientProxy());
    		ClientProxy.DayZpreload(event);
    	}
        DayZLog.info("Day Z " + Util.VERSION + Updater.preRelease() + " Preload Complete");
    }

    /**
     * Called at the "initialization" phase of loading.
     * @param event FMLInitializationEvent
     */
    @Init
    public void DayZload(FMLInitializationEvent event)
    {
		CommonProxy.DayZload(event);
    	if (FMLCommonHandler.instance().getSide().isClient())
    	{
    		ClientProxy.DayZload(event);
    	}
        DayZLog.info("Day Z " + Util.VERSION + Updater.preRelease() + " Load Complete");
    }
    
    /**
     * Called at the "post-initialization" phase of loading.
     * @param event FMLPostInitializationEvent
     */
	@PostInit
	public void DayZpostload(FMLPostInitializationEvent event) 
	{
		CommonProxy.DayZpostload(event);
    	if (FMLCommonHandler.instance().getSide().isClient())
    	{
    		ClientProxy.DayZpostload(event);
    	}
        DayZLog.info("Day Z " + Util.VERSION + Updater.preRelease() + " Postload Complete");
	}
		   
    public static Block barbedwire;
    public static Block dayzchestall;
    public static Block dayzchestrare;
    public static Block dayzchestcommon;
    public static Block chainlinkfence;
    public static Block sandbagblock;
    public static Block nails;
}