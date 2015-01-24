package org.theshadowsoftime.chemistrycraft.common.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.energy.prefab.BasicSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGeneratorCombustion extends TileEntity implements IInventory {

    ItemStack generatorItemStack;
    /** The number of ticks that the furnace will keep burning */
    public int generatorBurnTime;
    /** The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for */
    public int currentItemBurnTime;
    /** The number of ticks that the current item has been cooking for */
    public int generatorCookTime;

    //TODO: IF IT HAS A GAS OUTPUT STORE 1 MOL OF CO2 GAS IN IT
    /** Tells whether or not the generator block has a gas tank or other gas storage block above it */
    public boolean hasGasOutput = false;
    /** Tells whether or not the generator is has the required elements to begin combustion(lava source beneath it) */
    public boolean activated = false;

    public BasicSource source = new BasicSource(this, 1000, 1);

    /* IInventory */
    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return generatorItemStack;
    }

    @Override
    public ItemStack decrStackSize(int slot, int decreaseAmount) {
        if (generatorItemStack != null) {
            ItemStack itemstack;
            if (generatorItemStack.stackSize <= decreaseAmount) {
                itemstack = generatorItemStack;
                generatorItemStack = null;
                return itemstack;
            } else {
                itemstack = generatorItemStack.splitStack(decreaseAmount);
                if (generatorItemStack.stackSize == 0) {
                    generatorItemStack = null;
                }
                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return generatorItemStack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack contents) {
        generatorItemStack = contents;
        if (contents != null && contents.stackSize > getInventoryStackLimit()) {
            contents.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return "container.combustionGenerator";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        source.readFromNBT(nbt);

        NBTTagList tagList = nbt.getTagList("Items", 10);
        generatorItemStack = null;
        NBTTagCompound nbttagcompound1 = tagList.getCompoundTagAt(0);
        generatorItemStack = ItemStack.loadItemStackFromNBT(tagList.getCompoundTagAt(0));

        generatorBurnTime = nbt.getShort("BurnTime");
        generatorCookTime = nbt.getShort("CookTime");
        activated = nbt.getBoolean("Activated");
        hasGasOutput = nbt.getBoolean("HasGasOutput");
        currentItemBurnTime = getItemBurnTime(generatorItemStack);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        source.writeToNBT(nbt);
        nbt.setShort("BurnTime", (short) generatorBurnTime);
        nbt.setShort("CookTime", (short) generatorCookTime);
        nbt.setBoolean("Activated", activated);
        nbt.setBoolean("HasGasOutput", hasGasOutput);

        NBTTagList tagList = new NBTTagList();
        if (generatorItemStack != null) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte) 0);
            generatorItemStack.writeToNBT(nbttagcompound1);
            tagList.appendTag(nbttagcompound1);
        }
        nbt.setTag("Items", tagList);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int scale) {
        return generatorCookTime * scale / 200;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int scale) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = 200;
        }

        return generatorBurnTime * scale / this.currentItemBurnTime;
    }

    public boolean isBurning() {
        return generatorBurnTime > 0;
    }

    //TODO: BURN THROUGH ITEMS IN THE SLOT TO GENERATE POWER AND STORE IT IN THE INTERNAL BUFFER
    @Override
    public void updateEntity() {
        source.updateEntity();
        if (generatorBurnTime > 0) {
            generatorBurnTime--;
        }
        if (worldObj.getBlock(xCoord, yCoord - 1, zCoord) == Blocks.lava) {
            activated = true;
        }else{
           activated = false;
        }
        if (!worldObj.isRemote){
            System.out.println("[CC Debug] Generator updated server-side. Currently has " + source.getEnergyStored() +
                    " EU stored");
            if (generatorBurnTime != 0 || generatorItemStack != null) {
                if (generatorBurnTime == 0 && canBurn() && canGenerate()) {
                    System.out.println("[CC Debug] Generator is out of burn time but slot is not empty. BUrning item in" +
                            " slot.");
                    generatorItemStack.stackSize--;
                    currentItemBurnTime = generatorBurnTime = getItemBurnTime(generatorItemStack);
                }
                if (isBurning()) {
                    System.out.println("[CC Debug] Generator is burning. Has been burning for " + generatorCookTime +
                            " ticks. It will continue to burn for " + generatorBurnTime + " ticks.");
                    generatorCookTime++;
                    source.addEnergy(5);
                } else if (canBurn() && activated) {
                    System.out.println("[CC Debug] Generator is activated and can burn");
                    generatorBurnTime = getItemBurnTime(generatorItemStack);
                    //burnItem();
                }else {
                    System.out.println("[CC Debug] Generator is not burning or is not activated. Resetting cook time to 0");
                    generatorCookTime = 0;
                }
            }
        } else {
            System.out.println("[CC Debug] Generator updated client-side");
        }
        if (generatorBurnTime > 0) {
            System.out.println("[CC Debug] Marking generator as dirty.");
            markDirty();
        }
    }


    public boolean canBurn() {
        if (canGenerate()) {
            if (generatorItemStack == null) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean canGenerate() {
        if (isBurning()) {
            return true;
        } else {
            if (source.getFreeCapacity() == 0) {
                return false;
            }
        }
        return true;
    }
/*
    public void burnItem() {
        if (canBurn()) {
            generatorItemStack.stackSize--;
            if (generatorItemStack.stackSize <= 0) {
                generatorItemStack = null;
            }
        }
    }
    */

    public static int getItemBurnTime(ItemStack itemstack) {
        if (itemstack == null) {
            return 0;
        } else {
            Item item = itemstack.getItem();

            if (item == Items.coal) {
                return 1600;
            }
        }
        return 0;
    }

    public static boolean isItemFuel(ItemStack itemStack) {
        return getItemBurnTime(itemStack) > 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return itemStack.getItem() == Items.coal;
    }

    @Override
    public void invalidate() {
        source.invalidate();
        super.invalidate();
    }

    @Override
    public void onChunkUnload() {
        source.onChunkUnload();
    }
}
