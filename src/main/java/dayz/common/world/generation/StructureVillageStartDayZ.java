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

class StructureVillageStartDayZ extends StructureStart
{
    /** well ... thats what it does */
    private boolean hasMoreThanTwoComponents = false;

    public StructureVillageStartDayZ(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        List list = StructureVillagePieces.getStructureVillageWeightedPieceList(par2Random, par5);
        ComponentVillageStartPiece componentvillagestartpiece = new ComponentVillageStartPiece(par1World.getWorldChunkManager(), 0, par2Random, (par3 << 4) + 2, (par4 << 4) + 2, list, par5);
        components.add(componentvillagestartpiece);
        componentvillagestartpiece.buildComponent(componentvillagestartpiece, components, par2Random);
        List list1 = componentvillagestartpiece.field_74930_j;
        List list2 = componentvillagestartpiece.field_74932_i;
        int l;

        while (!list1.isEmpty() || !list2.isEmpty())
        {
            StructureComponent structurecomponent;

            if (list1.isEmpty())
            {
                l = par2Random.nextInt(list2.size());
                structurecomponent = (StructureComponent) list2.remove(l);
                structurecomponent.buildComponent(componentvillagestartpiece, components, par2Random);
            }
            else
            {
                l = par2Random.nextInt(list1.size());
                structurecomponent = (StructureComponent) list1.remove(l);
                structurecomponent.buildComponent(componentvillagestartpiece, components, par2Random);
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

    /**
     * currently only defined for Villages, returns true if Village has more
     * than 2 non-road components
     */
    @Override
    public boolean isSizeableStructure()
    {
        return hasMoreThanTwoComponents;
    }
}
