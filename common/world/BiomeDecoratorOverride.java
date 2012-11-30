package dayz.common.world;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;

public class BiomeDecoratorOverride extends BiomeDecorator 
{

	public static class Builder 
	{
		// required parms
		private final BiomeGenBase biome;

		// optional parms - initialized to defaults
		private int waterlilyPerChunk = 0;
		private int treesPerChunk = 0;
		private int flowersPerChunk = 2;
		private int grassPerChunk = 1;
		private int deadBushPerChunk = 0;
		private int mushroomsPerChunk = 0;
		private int reedsPerChunk = 0;
		private int cactiPerChunk = 0;
		private int sandPerChunk = 1;
		private int sandPerChunk2 = 3;
		private int clayPerChunk = 1;
		private int bigMushroomsPerChunk = 0;
		private int biomeColour = 0;

		private Builder() 
		{
			this(null);
		}

		public BiomeDecoratorOverride build() 
		{
			return new BiomeDecoratorOverride(this);
		}

		public Builder(BiomeGenBase biome) 
		{
			this.biome = biome;
		}
		
		public Builder biomeColour(int val) 
		{
			biome.color = val;
			return this;
		}

		public Builder bigMushroomsPerChunk(int val) 
		{
			bigMushroomsPerChunk = val;
			return this;
		}

		public Builder cactiPerChunk(int val) 
		{
			cactiPerChunk = val;
			return this;
		}

		public Builder clayPerChunk(int val) 
		{
			clayPerChunk = val;
			return this;
		}

		public Builder deadBushPerChunk(int val) 
		{
			deadBushPerChunk = val;
			return this;
		}

		public Builder flowersPerChunk(int val) 
		{
			flowersPerChunk = val;
			return this;
		}

		public Builder grassPerChunk(int val) 
		{
			grassPerChunk = val;
			return this;
		}

		public Builder mushroomsPerChunk(int val) 
		{
			mushroomsPerChunk = val;
			return this;
		}

		public Builder reedsPerChunk(int val) 
		{
			reedsPerChunk = val;
			return this;
		}

		public Builder sandPerChunk(int val, int val2) 
		{
			sandPerChunk = val;
			sandPerChunk2 = val2;
			return this;
		}

		public Builder treesPerChunk(int val) 
		{
			treesPerChunk = val;
			return this;
		}

		public Builder waterlilyPerChunk(int val) 
		{
			waterlilyPerChunk = val;
			return this;
		}
	}

	private BiomeDecoratorOverride() 
	{
		super(null);
	}

	private BiomeDecoratorOverride(Builder builder) 
	{
		super(builder.biome);
		
		biome.color = builder.biomeColour;
		waterlilyPerChunk = builder.waterlilyPerChunk;
		treesPerChunk = builder.treesPerChunk;
		flowersPerChunk = builder.flowersPerChunk;
		grassPerChunk = builder.grassPerChunk;
		deadBushPerChunk = builder.deadBushPerChunk;
		mushroomsPerChunk = builder.mushroomsPerChunk;
		reedsPerChunk = builder.reedsPerChunk;
		cactiPerChunk = builder.cactiPerChunk;
		sandPerChunk = builder.sandPerChunk;
		sandPerChunk2 = builder.sandPerChunk2;
		clayPerChunk = builder.clayPerChunk;
		bigMushroomsPerChunk = builder.bigMushroomsPerChunk;
	}

}