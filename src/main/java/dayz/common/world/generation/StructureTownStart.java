package dayz.common.world.generation;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentVillageRoadPiece;
import net.minecraft.world.gen.structure.ComponentVillageStartPiece;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureVillagePieces;

class StructureTownStart extends StructureStart
{
    private boolean hasMoreThanTwoComponents = false;

    public StructureTownStart(World par1World, Random par2Random, int xCoord, int yCoord, int zCoord)
    {
        List weightedPieceList = StructureVillagePieces.getStructureVillageWeightedPieceList(par2Random, zCoord);
        ComponentVillageStartPiece startPiece = new ComponentVillageStartPiece(par1World.getWorldChunkManager(), 0, par2Random, (xCoord << 4) + 2, (yCoord << 4) + 2, weightedPieceList, zCoord);
        components.add(startPiece);
        startPiece.buildComponent(startPiece, components, par2Random);
        List list1 = startPiece.field_74930_j;
        List list2 = startPiece.field_74932_i;
        int l;

        while (!list1.isEmpty() || !list2.isEmpty())
        {
            StructureComponent structurecomponent;

            if (list1.isEmpty())
            {
                l = par2Random.nextInt(list2.size());
                structurecomponent = (StructureComponent) list2.remove(l);
                structurecomponent.buildComponent(startPiece, components, par2Random);
            }
            else
            {
                l = par2Random.nextInt(list1.size());
                structurecomponent = (StructureComponent) list1.remove(l);
                structurecomponent.buildComponent(startPiece, components, par2Random);
            }
        }

        updateBoundingBox();
        l = 0;
        Iterator iterator = components.iterator();

        while (iterator.hasNext())
        {
            StructureComponent structurecomponent1 = (StructureComponent) iterator.next();

            if (!(structurecomponent1 instanceof ComponentVillageRoadPiece))
            {
                ++l;
            }
        }

        hasMoreThanTwoComponents = l > 2;
    }

    @Override
    public boolean isSizeableStructure()
    {
        return hasMoreThanTwoComponents;
    }
}
