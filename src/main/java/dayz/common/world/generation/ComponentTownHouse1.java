package dayz.common.world.generation;

import static net.minecraftforge.common.ChestGenHooks.VILLAGE_BLACKSMITH;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentVillage;
import net.minecraft.world.gen.structure.ComponentVillageStartPiece;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.common.ChestGenHooks;

public class ComponentTownHouse1 extends ComponentVillage
{
    private int averageGroundLevel = -1;

    public ComponentTownHouse1(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5)
    {
        super(par1ComponentVillageStartPiece, par2);
        coordBaseMode = par5;
        boundingBox = par4StructureBoundingBox;
    }

    public static ComponentTownHouse1 func_74898_a(ComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
    {
        StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 9, 9, 6, par6);
        return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(par1List, var8) == null ? new ComponentTownHouse1(par0ComponentVillageStartPiece, par7, par2Random, var8, par6) : null;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs,
     * Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    @Override
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
        if (averageGroundLevel < 0)
        {
            averageGroundLevel = getAverageGroundLevel(par1World, par3StructureBoundingBox);

            if (averageGroundLevel < 0)
            {
                return true;
            }

            boundingBox.offset(0, averageGroundLevel - boundingBox.maxY + 9 - 1, 0);
        }

        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 1, 7, 5, 4, 0, 0, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 8, 0, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 5, 0, 8, 5, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 6, 1, 8, 6, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 7, 2, 8, 7, 3, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        int var4 = getMetadataWithOffset(Block.stairsWoodOak.blockID, 3);
        int var5 = getMetadataWithOffset(Block.stairsWoodOak.blockID, 2);
        int var6;
        int var7;

        for (var6 = -1; var6 <= 2; ++var6)
        {
            for (var7 = 0; var7 <= 8; ++var7)
            {
                placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, var4, var7, 6 + var6, var6, par3StructureBoundingBox);
                placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, var5, var7, 6 + var6, 5 - var6, par3StructureBoundingBox);
            }
        }

        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 0, 0, 1, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 5, 8, 1, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 8, 1, 0, 8, 1, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 2, 1, 0, 7, 1, 0, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 0, 4, 0, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 5, 0, 4, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 8, 2, 5, 8, 4, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 8, 2, 0, 8, 4, 0, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 1, 0, 4, 4, Block.planks.blockID, Block.planks.blockID, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 2, 5, 7, 4, 5, Block.planks.blockID, Block.planks.blockID, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 8, 2, 1, 8, 4, 4, Block.planks.blockID, Block.planks.blockID, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 2, 0, 7, 4, 0, Block.planks.blockID, Block.planks.blockID, false);
        placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 4, 2, 0, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 5, 2, 0, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 6, 2, 0, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 4, 3, 0, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 5, 3, 0, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 6, 3, 0, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 2, 2, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 2, 3, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 3, 2, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 3, 3, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 8, 2, 2, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 8, 2, 3, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 8, 3, 2, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 8, 3, 3, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 2, 2, 5, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 3, 2, 5, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 5, 2, 5, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 6, 2, 5, par3StructureBoundingBox);
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 4, 1, 7, 4, 1, Block.planks.blockID, Block.planks.blockID, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 4, 4, 7, 4, 4, Block.planks.blockID, Block.planks.blockID, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 3, 4, 7, 3, 4, Block.bookShelf.blockID, Block.bookShelf.blockID, false);
        placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 7, 1, 4, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, getMetadataWithOffset(Block.stairsWoodOak.blockID, 0), 7, 1, 3, par3StructureBoundingBox);
        var6 = getMetadataWithOffset(Block.stairsWoodOak.blockID, 3);
        placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, var6, 6, 1, 4, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, var6, 5, 1, 4, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, var6, 4, 1, 4, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, var6, 3, 1, 4, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 6, 1, 3, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.pressurePlatePlanks.blockID, 0, 6, 2, 3, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 4, 1, 3, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, Block.pressurePlatePlanks.blockID, 0, 4, 2, 3, par3StructureBoundingBox);
        // this.placeBlockAtCurrentPosition(par1World, Block.workbench.blockID,
        // 0, 7, 1, 1, par3StructureBoundingBox);
        generateStructureChestContents(par1World, par3StructureBoundingBox, par2Random, 7, 1, 1, ChestGenHooks.getItems(VILLAGE_BLACKSMITH, par2Random), ChestGenHooks.getCount(VILLAGE_BLACKSMITH, par2Random));
        placeBlockAtCurrentPosition(par1World, 0, 0, 1, 1, 0, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 0, 0, 1, 2, 0, par3StructureBoundingBox);
        placeDoorAtCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 1, 1, 0, getMetadataWithOffset(Block.doorWood.blockID, 1));

        if (getBlockIdAtCurrentPosition(par1World, 1, 0, -1, par3StructureBoundingBox) == 0 && getBlockIdAtCurrentPosition(par1World, 1, -1, -1, par3StructureBoundingBox) != 0)
        {
            placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, getMetadataWithOffset(Block.stairsCobblestone.blockID, 3), 1, 0, -1, par3StructureBoundingBox);
        }

        for (var7 = 0; var7 < 6; ++var7)
        {
            for (int var8 = 0; var8 < 9; ++var8)
            {
                clearCurrentPositionBlocksUpwards(par1World, var8, 9, var7, par3StructureBoundingBox);
                fillCurrentPositionBlocksDownwards(par1World, Block.cobblestone.blockID, 0, var8, -1, var7, par3StructureBoundingBox);
            }
        }

        spawnVillagers(par1World, par3StructureBoundingBox, 2, 1, 2, 1);
        return true;
    }

    /**
     * Returns the villager type to spawn in this component, based on the number
     * of villagers already spawned.
     */
    @Override
    protected int getVillagerType(int par1)
    {
        return 1;
    }
}
