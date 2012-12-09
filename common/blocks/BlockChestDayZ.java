package dayz.common.blocks;

import java.util.Random;

import net.minecraft.src.BlockChest;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import dayz.DayZ;

public class BlockChestDayZ extends BlockChest 
{
	private Random random;
    private EnumChestType chestType;

	public BlockChestDayZ(int id, EnumChestType chestType) 
	{
		super(id);
		setBlockUnbreakable();
		setRequiresSelfNotify();
		setBlockBounds(0.0625F, 0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		random = new Random();
		setCreativeTab(DayZ.creativeTabDayZ);
        blockIndexInTexture = 26;
    	this.chestType = chestType;
	}

	@Override
	public TileEntity createNewTileEntity(World world) 
	{
		return null;
	}

	@Override
	public boolean isOpaqueCube() 
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() 
	{
		return false;
	}

	@Override
	public int getRenderType() 
	{
		return 22;
 	 }

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) 
	{
		return new TileEntityChestDayZ(chestType);
	}
  
	@SideOnly(Side.CLIENT)
	@Override
	public int getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return 4;
	}

	@Override
	public int getBlockTextureFromSide(int par1)
	{
		return 4;
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int i1, float f1, float f2, float f3) 
	{
		TileEntity tileEntity = world.getBlockTileEntity(i, j, k);

		if (tileEntity == null || !(tileEntity instanceof TileEntityChestDayZ))
		{
			return true;
		}

		if (world.isBlockSolidOnSide(i, j + 1, k, ForgeDirection.DOWN))
		{
			return true;
		}

		if (world.isRemote) 
		{
			return true;
		}

	  
		player.displayGUIChest((IInventory)tileEntity);
		return true;
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) 
	{
		super.onBlockAdded(world, i, j, k);
		world.markBlockForUpdate(i, j, k);
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving) 
	{
		byte chestFacing = 0;
		int facing = MathHelper.floor_double((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
    	if (facing == 0) 
    	{
    		chestFacing = 2;
    	}
    	if (facing == 1) 
    	{
    		chestFacing = 5;
    	}
    	if (facing == 2) 
    	{
    	chestFacing = 3;
    	}
    	if (facing == 3) 
    	{
    		chestFacing = 4;
    	}
    	TileEntity te = world.getBlockTileEntity(i, j, k);
    	if (te != null && te instanceof TileEntityChestDayZ) 
    	{
    		((TileEntityChestDayZ) te).setFacing(chestFacing);
    		world.markBlockForUpdate(i, j, k);
    	}
	}

	@Override
	public void breakBlock(World world, int i, int j, int k, int i1, int i2)
	{
		TileEntityChestDayZ tileentitychest = (TileEntityChestDayZ) world.getBlockTileEntity(i, j, k);
		if (tileentitychest != null)
		{
			dropContent(0, tileentitychest, world, tileentitychest.xCoord, tileentitychest.yCoord, tileentitychest.zCoord);
		}
		super.breakBlock(world, i, j, k, i1, i2);
	}

	public void dropContent(int newSize, IInventory chest, World world, int xCoord, int yCoord, int zCoord) 
	{
		for (int l = newSize; l < chest.getSizeInventory(); l++)
		{
			ItemStack itemstack = chest.getStackInSlot(l);
			if (itemstack == null)
			{
				continue;
			}
			float f = random.nextFloat() * 0.8F + 0.1F;
			float f1 = random.nextFloat() * 0.8F + 0.1F;
			float f2 = random.nextFloat() * 0.8F + 0.1F;
			while (itemstack.stackSize > 0)
			{
				int i1 = random.nextInt(21) + 10;
				if (i1 > itemstack.stackSize)
				{
					i1 = itemstack.stackSize;
				}
				itemstack.stackSize -= i1;
				EntityItem entityitem = new EntityItem(world, (float) xCoord + f, (float) yCoord + (newSize > 0 ? 1 : 0) + f1, (float) zCoord + f2,
						new ItemStack(itemstack.itemID, i1, itemstack.getItemDamage()));
				float f3 = 0.05F;
				entityitem.motionX = (float) random.nextGaussian() * f3;
				entityitem.motionY = (float) random.nextGaussian() * f3 + 0.2F;
				entityitem.motionZ = (float) random.nextGaussian() * f3;
				if (itemstack.hasTagCompound())
				{
					entityitem.item.setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
				}
				world.spawnEntityInWorld(entityitem);
			}
		}
	}
}