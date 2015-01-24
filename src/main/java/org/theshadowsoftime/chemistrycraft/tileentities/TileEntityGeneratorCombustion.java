package org.theshadowsoftime.chemistrycraft.tileentities;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityGeneratorCombustion extends TileEntity implements IInventory, IEnergyProvider{

    ItemStack generatorItemStack;
    /** The number of ticks that the furnace will keep burning */
    public int generatorBurnTime;
    /** The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for */
    public int currentItemBurnTime;
    /** The number of ticks that the current item has been cooking for */
    public int generatorCookTime;

    protected EnergyStorage storage = new EnergyStorage(10000, 0, 500); //internal energy buffer

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
        storage.readFromNBT(nbt);

        NBTTagList tagList = nbt.getTagList("Items", 10);
        generatorItemStack = null;
        NBTTagCompound nbttagcompound1 = tagList.getCompoundTagAt(0);
        generatorItemStack = ItemStack.loadItemStackFromNBT(tagList.getCompoundTagAt(0));

        this.generatorBurnTime = nbt.getShort("BurnTime");
        this.generatorCookTime = nbt.getShort("CookTime");
        this.currentItemBurnTime = getItemBurnTime(generatorItemStack);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        storage.writeToNBT(nbt);
        nbt.setShort("BurnTime", (short) this.generatorBurnTime);
        nbt.setShort("CookTime", (short) this.generatorCookTime);

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
        return generatorItemStack.getItem() == Items.coal ? 64 : 1;
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int scale) {
        return generatorCookTime * scale / 200;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int scale)
    {
        if (this.currentItemBurnTime == 0)
        {
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
        if (!(generatorBurnTime > 0)) {
            generatorBurnTime--;
        }
        if (!worldObj.isRemote){
            if (generatorBurnTime != 0 && generatorItemStack != null) {
                if (canBurn() && canGenerate()) {
                    currentItemBurnTime = generatorBurnTime = getItemBurnTime(generatorItemStack);
                    if (generatorBurnTime > 0) {
                        generatorItemStack.stackSize--;
                        if (generatorItemStack.stackSize == 0) {
                            generatorItemStack = generatorItemStack.getItem().getContainerItem(generatorItemStack);
                        }
                    }
                }
                if (isBurning() && canBurn()) {
                    generatorCookTime++;
                } else if (!isBurning() && canBurn()) {
                    generatorBurnTime = getItemBurnTime(generatorItemStack);
                    burnItem();
                }else {
                    generatorCookTime = 0;
                }
            }
        }
        if (generatorBurnTime > 0) {
            markDirty();
        }
    }


    public boolean canBurn() {
        if (canGenerate()) {
            if (generatorItemStack == null) {
                return false;
            } else {
                if (generatorBurnTime > 0) {
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    public boolean canGenerate() {
        if (isBurning()) {
            return true;
        } else {
            if (storage.getEnergyStored() == storage.getMaxEnergyStored()) {
                return false;
            }
        }
        return true;
    }

    public void burnItem() {
        if (canBurn()) {
            generatorItemStack.stackSize--;
            if (generatorItemStack.stackSize <= 0) {
                generatorItemStack = null;
            }
        }
    }

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

    /* IEnergyConnection */
    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true; //power can be taken from any side
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

    /* IEnergyProvider */
    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return storage.extractEnergy(maxExtract, simulate);
    }

    /* IEnergyReceiver and IEnergyProvider */
    @Override
    public int getEnergyStored(ForgeDirection from) {
        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return storage.getMaxEnergyStored();
    }
}
