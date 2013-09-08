package dayz.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks
{
    public static Block barbedWire;
    private static int barbedWireId;
    public static Block chestLoot;
    private static int chestLootId;
    public static Block chainFence;
    private static int chainFenceId;
    public static Block sandbagBlock;
    private static int sandbagBlockId;
    public static Block nailBlock;
    private static int nailBlockId;

    public static void loadBlocks()
    {
        barbedWire = new BlockBarbedWire(barbedWireId).setUnlocalizedName("barbedWire").setHardness(3F).setResistance(2F);
        GameRegistry.registerBlock(barbedWire, "barbedWire");
        LanguageRegistry.addName(barbedWire, "Barbed Wire");
        chestLoot = new BlockChestDayZ(chestLootId, 0).setUnlocalizedName("chestLoot");
        GameRegistry.registerBlock(chestLoot, "chestLoot");
        LanguageRegistry.addName(chestLoot, "Loot Chest");
        chainFence = (new BlockFence(chainFenceId, "chainFence", Material.iron)).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("chainFence");
        GameRegistry.registerBlock(chainFence, "chainFence");
        LanguageRegistry.addName(chainFence, "Chain-link fence");
        sandbagBlock = (new BlockMod(sandbagBlockId, Material.clay)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("sandbagBlock");
        GameRegistry.registerBlock(sandbagBlock, "sandbagBlock");
        LanguageRegistry.addName(sandbagBlock, "Sandbags");
        nailBlock = new BlockNails(nailBlockId, Material.circuits).setUnlocalizedName("nailBlock").setHardness(1F).setResistance(1F);
        GameRegistry.registerBlock(nailBlock, "nailBlock");
        LanguageRegistry.addName(nailBlock, "Nails");

        GameRegistry.addRecipe(new ItemStack(nailBlock, 8), new Object[]
        { "#", "#", Character.valueOf('#'), Item.ingotIron });
    }

    public void blockConfig(Configuration config)
    {
        barbedWireId = config.get(Configuration.CATEGORY_BLOCK, "barbedwireID", 160, "Barbed Wire Block ID").getInt();
        chestLootId = config.get(Configuration.CATEGORY_BLOCK, "dayzchestallID", 161, "All Item Chest Block ID").getInt();
        chainFenceId = config.get(Configuration.CATEGORY_BLOCK, "chainlinkfenceID", 164, "Chainlink Fence Block ID").getInt();
        sandbagBlockId = config.get(Configuration.CATEGORY_BLOCK, "sandbagblockID", 165, "Sandbag Block ID").getInt();
        nailBlockId = config.get(Configuration.CATEGORY_BLOCK, "nailsID", 166, "Nail Block ID").getInt();
    }
}
