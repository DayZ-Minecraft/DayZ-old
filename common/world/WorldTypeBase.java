package dayz.common.world;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.GenLayerHills;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldChunkManager;
import net.minecraft.src.WorldType;
import dayz.DayZ;

public class WorldTypeBase extends WorldType
{	
    public WorldTypeBase(int par1, String par2Str)
    {
        super(par1, par2Str, 0);
    }

    //Sets up the biome and WorldChunkManager.
    @Override
    public WorldChunkManager getChunkManager(World world)
    {
        return new ChunkManagerDayZ(world);
    }

    //Sets up the ChunkProvider
    @Override
    public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        return new ChunkProviderDayZ(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
    }
	
	/**Returns an array of large, "major" biomes, such
	 * as forest, desert, or taiga in the overworld.
	 *
	 * @see GenLayerBiomes (for the array of biomes used in the overworld)
	 */
	public BiomeGenBase[] setMajorBiomes()
	{
		return null;
	}
	
	/**Returns a "minor" biome based on conditions of
	 * modder's choosing. Examples of such biomes in
	 * the overworld include the hilly or mountainous areas 
	 * within larger biomes such as tundra or jungle, as
	 * well as the splotches of forest across the plains.
	 *
	 * @param biomegenbase	the "major" biome(s) on top of which this biome generates
	 * @param genlayer		the GenLayer generating these biomes; use to retrieve the 
	 *							genlayer.nextInt() method for random biome generation
	 *							instead of random.nextInt()
	 * @see GenLayerHills (for the overworld's minor biome generation)
	 */
	public BiomeGenBase setMinorBiomes(BiomeGenBase biomegenbase, GenLayer genlayer)
	{
		return null;
	}
	
	/**Returns a biome generated in the pattern of
	 * overworld oceans; note that this does not mean
	 * the biome generated is or must be an actual ocean 
	 * biome.
	 *
	 * @params See the "setMinorBiomes" method.
	 */
	public BiomeGenBase setOceanBiomes(BiomeGenBase biomegenbase, GenLayer genlayer)
	{
		return null;
	}
	
	/**Returns a biome generated in the pattern of
	 * overworld rivers; note that this does not mean
	 * the biome generated is or must be an actual river 
	 * biome.
	 *
	 * @params See the "setMinorBiomes" method.
	 */
	public BiomeGenBase setRiverBiomes(BiomeGenBase biomegenbase, GenLayer genlayer)
	{
		return null;
	}
	
	/**Returns a biome generated consistently along the
	 * border of two separate biomes.
	 *
	 * @param biomegenbase		the biome on one side of the border
	 * @param biomegenbase1		the biome on the other side of the border
	 * @param genlayer			See the "setMinorBiomes" method.
	 */
	public BiomeGenBase setBorderBiomes(BiomeGenBase biomegenbase, BiomeGenBase biomegenbase1, GenLayer genlayer)
	{
		return null;
	}
	
	/**Returns a biome that can be generated anywhere;
	 * only the rarity of the biome, not its generation
	 * overtop of other biomes, can be defined.
	 *
	 * @param genlayer		See the "setMinorBiomes" method.
	 */
	public BiomeGenBase setMiscellaneousBiomes(GenLayer genlayer)
	{
		return null;
	}
	
	@Override
    public int getSpawnFuzz()
    {
        return 100;
    }
}