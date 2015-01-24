package org.theshadowsoftime.chemistrycraft.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import org.theshadowsoftime.chemistrycraft.blocks.BlockGasCollector;
import org.theshadowsoftime.chemistrycraft.items.ItemPurifiedVial;

public class TileEntityGasCollector extends TileEntity implements IInventory{

    public static final String publicName = "tileEntityGasCollector";
    private String name = "tileEntityGasCollector";

    private BlockGasCollector gasCollector;

    private ItemStack[] gasCollectorItemStacks = new ItemStack[2];

    public TileEntityGasCollector () {}
/*
    @Override
    public void updateEntity() {
        if (!worldObj.isRemote) {
            if (getStackInSlot(0).getItem() instanceof ItemPurifiedVial) {
                ItemPurifiedVial vial = (ItemPurifiedVial) getStackInSlot(0).getItem();
                setInventorySlotContents(1, new ItemStack(vial));
                setInventorySlotContents(0, null);
            }
        }
    }
    */

    public int getSizeInventory() {
        return gasCollectorItemStacks.length;
    }

    public ItemStack getStackInSlot(int slot) {
        return gasCollectorItemStacks[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int count) {
        ItemStack itemstack = getStackInSlot(slot);

        if(itemstack != null) {
            if(itemstack.stackSize <= count) {
                setInventorySlotContents(slot, null);
            } else {
                itemstack = itemstack.splitStack(count);
            }
        }
        return itemstack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack itemstack = getStackInSlot(slot);
        setInventorySlotContents(slot, null);
        return itemstack;
    }

    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        gasCollectorItemStacks[slot] = itemStack;

        if(itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return "Gas Collector";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        if (slot == 0) {
            if (itemStack.getItem() instanceof ItemPurifiedVial) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        NBTTagList list = new NBTTagList();

        for(int i = 0; i < getSizeInventory(); i++) {
            ItemStack itemstack = getStackInSlot(i);

            if(itemstack != null) {
                NBTTagCompound item = new NBTTagCompound();

                item.setByte("SlotGasCollector", (byte) i);
                itemstack.writeToNBT(item);
                list.appendTag(item);
            }
        }

        compound.setTag("ItemsGasCollector", list);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        NBTTagList list = compound.getTagList("ItemsGasCollector", 10);

        for(int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound item = list.getCompoundTagAt(i);
            int slot = item.getByte("SlotGasCollector");

            if(slot >= 0 && slot < getSizeInventory()) {
                setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
            }
        }
    }

    public String getName() {
        return name;
    }
}
