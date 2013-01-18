package dayz.common.blocks;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import dayz.DayZ;
import dayz.common.misc.DayZLog;
import dayz.common.misc.WeightedRandomChestDayZ;

public class TileEntityChestDayZ extends TileEntityChest implements IInventory
{
    private ItemStack[] chestContents = new ItemStack[36];
    public boolean adjacentChestChecked = false;
    public TileEntityChestDayZ adjacentChestZNeg;
    public TileEntityChestDayZ adjacentChestXPos;
    public TileEntityChestDayZ adjacentChestXNeg;
    public TileEntityChestDayZ adjacentChestZPosition;
    public float lidAngle;
    public float prevLidAngle;
    public int numUsingPlayers;
    private int ticksSinceSync;
    private EnumChestType chestType;

    public TileEntityChestDayZ(EnumChestType chestType) 
    {
    	this.chestType = chestType;
    }
    	
    @Override
    public String getInvName()
    {
        return "container." + chestType;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    private void func_90009_a(TileEntityChestDayZ par1TileEntityChest, int par2)
    {
        if (par1TileEntityChest.isInvalid())
        {
            this.adjacentChestChecked = false;
        }
        else if (this.adjacentChestChecked)
        {
            switch (par2)
            {
                case 0:
                    if (this.adjacentChestZPosition != par1TileEntityChest)
                    {
                        this.adjacentChestChecked = false;
                    }

                    break;
                case 1:
                    if (this.adjacentChestXNeg != par1TileEntityChest)
                    {
                        this.adjacentChestChecked = false;
                    }

                    break;
                case 2:
                    if (this.adjacentChestZNeg != par1TileEntityChest)
                    {
                        this.adjacentChestChecked = false;
                    }

                    break;
                case 3:
                    if (this.adjacentChestXPos != par1TileEntityChest)
                    {
                        this.adjacentChestChecked = false;
                    }
            }
        }
    }

    @Override
    public void checkForAdjacentChests()
    {
        if (!this.adjacentChestChecked)
        {
            this.adjacentChestChecked = true;
            this.adjacentChestZNeg = null;
            this.adjacentChestXPos = null;
            this.adjacentChestXNeg = null;
            this.adjacentChestZPosition = null;

            if (this.worldObj.getBlockId(this.xCoord - 1, this.yCoord, this.zCoord) == Block.chest.blockID)
            {
                this.adjacentChestXNeg = (TileEntityChestDayZ)this.worldObj.getBlockTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
            }

            if (this.worldObj.getBlockId(this.xCoord + 1, this.yCoord, this.zCoord) == Block.chest.blockID)
            {
                this.adjacentChestXPos = (TileEntityChestDayZ)this.worldObj.getBlockTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
            }

            if (this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord - 1) == Block.chest.blockID)
            {
                this.adjacentChestZNeg = (TileEntityChestDayZ)this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
            }

            if (this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord + 1) == Block.chest.blockID)
            {
                this.adjacentChestZPosition = (TileEntityChestDayZ)this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
            }

            if (this.adjacentChestZNeg != null)
            {
                this.adjacentChestZNeg.func_90009_a(this, 0);
            }

            if (this.adjacentChestZPosition != null)
            {
                this.adjacentChestZPosition.func_90009_a(this, 2);
            }

            if (this.adjacentChestXPos != null)
            {
                this.adjacentChestXPos.func_90009_a(this, 1);
            }

            if (this.adjacentChestXNeg != null)
            {
                this.adjacentChestXNeg.func_90009_a(this, 3);
            }
        }
    }

    @Override
    public void updateEntity()
    {	
    	long time = this.worldObj.getWorldTime() % 1000;
    	if (!(time >= 0 && time <= 24))
    	{
    		if (!(2 % time == 0))
    		{
        		return;
    		}
    	}
    	    	
    	int i = this.xCoord;
    	int j = this.yCoord;
    	int k = this.zCoord;
    	
    	boolean continueChecking = true;
    	int slotNumber = 0;
        while (continueChecking == true)
        {
        	if (this.getStackInSlot(slotNumber) == null && slotNumber < 27)
        	{
                if(slotNumber == 26)
                {
                	if (this.worldObj.getClosestPlayer(i, j, k, 64) == null)
                    {
                		WeightedRandomChestDayZ.generateChestContents(DayZ.random, chestType, this, DayZ.random.nextInt(5) + 1);	
	            		DayZLog.info("Refilled " + chestType.name() + " Chest at " + i + ", " + j + ", " + k + ".");
                    	continueChecking = false;
                    }
                	else
                	{
                    	continueChecking = false;
                	}
                }
                else
                {
                	slotNumber++;
                }
        	}
        	else
        	{
                continueChecking = false;
        	}
        }    	
        super.updateEntity();
        this.checkForAdjacentChests();
        ++this.ticksSinceSync;
        float var1;

        if (!this.worldObj.isRemote && this.numUsingPlayers != 0 && (this.ticksSinceSync + this.xCoord + this.yCoord + this.zCoord) % 200 == 0)
        {
            this.numUsingPlayers = 0;
            var1 = 5.0F;
            List var2 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double)((float)this.xCoord - var1), (double)((float)this.yCoord - var1), (double)((float)this.zCoord - var1), (double)((float)(this.xCoord + 1) + var1), (double)((float)(this.yCoord + 1) + var1), (double)((float)(this.zCoord + 1) + var1)));
            Iterator var3 = var2.iterator();

            while (var3.hasNext())
            {
                EntityPlayer var4 = (EntityPlayer)var3.next();

                if (var4.openContainer instanceof ContainerChest)
                {
                    IInventory var5 = ((ContainerChest)var4.openContainer).getLowerChestInventory();

                    if (var5 == this || var5 instanceof InventoryLargeChest && ((InventoryLargeChest)var5).isPartOfLargeChest(this))
                    {
                        ++this.numUsingPlayers;
                    }
                }
            }
        }

        this.prevLidAngle = this.lidAngle;
        var1 = 0.1F;
        double var11;

        if (this.numUsingPlayers > 0 && this.lidAngle == 0.0F && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null)
        {
            double var8 = (double)this.xCoord + 0.5D;
            var11 = (double)this.zCoord + 0.5D;

            if (this.adjacentChestZPosition != null)
            {
                var11 += 0.5D;
            }

            if (this.adjacentChestXPos != null)
            {
                var8 += 0.5D;
            }

            this.worldObj.playSoundEffect(var8, (double)this.yCoord + 0.5D, var11, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (this.numUsingPlayers == 0 && this.lidAngle > 0.0F || this.numUsingPlayers > 0 && this.lidAngle < 1.0F)
        {
            float var9 = this.lidAngle;

            if (this.numUsingPlayers > 0)
            {
                this.lidAngle += var1;
            }
            else
            {
                this.lidAngle -= var1;
            }

            if (this.lidAngle > 1.0F)
            {
                this.lidAngle = 1.0F;
            }

            float var10 = 0.5F;

            if (this.lidAngle < var10 && var9 >= var10 && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null)
            {
                var11 = (double)this.xCoord + 0.5D;
                double var6 = (double)this.zCoord + 0.5D;

                if (this.adjacentChestZPosition != null)
                {
                    var6 += 0.5D;
                }

                if (this.adjacentChestXPos != null)
                {
                    var11 += 0.5D;
                }

                this.worldObj.playSoundEffect(var11, (double)this.yCoord + 0.5D, var6, "random.chestclosed", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (this.lidAngle < 0.0F)
            {
                this.lidAngle = 0.0F;
            }
        }
    }
    
    public void setFacing(byte chestFacing) 
    {
        
    }
    
    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        this.chestContents = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            int var5 = var4.getByte("Slot") & 255;

            if (var5 >= 0 && var5 < this.chestContents.length)
            {
                this.chestContents[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.chestContents.length; ++var3)
        {
            if (this.chestContents[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.chestContents[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        par1NBTTagCompound.setTag("Items", var2);
    }
}