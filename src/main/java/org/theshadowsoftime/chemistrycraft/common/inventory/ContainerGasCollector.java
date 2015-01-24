package org.theshadowsoftime.chemistrycraft.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.theshadowsoftime.chemistrycraft.common.tileentities.TileEntityGasCollector;

public class ContainerGasCollector extends Container{

    TileEntityGasCollector tileEntity;

    public ContainerGasCollector(InventoryPlayer playerInventory, TileEntityGasCollector tileEntity){
        this.tileEntity = tileEntity;

        this.addSlotToContainer(new Slot(playerInventory, 0, 80, 6));
        this.addSlotToContainer(new Slot(playerInventory, 1, 80, 65));

        int i;
        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 7 + j * 18, 108 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(playerInventory, i, 7 + i * 18, 168));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int i) {
        Slot slot = getSlot(i);

        if(slot != null && slot.getHasStack()) {
            ItemStack itemstack = slot.getStack();
            ItemStack result = itemstack.copy();

            if(i >= 36) {
                if(!mergeItemStack(itemstack, 0, 36, false)) {
                    return null;
                }
            } else if(!mergeItemStack(itemstack, 36, 36 + tileEntity.getSizeInventory(), false)) {
                return null;
            }

            if(itemstack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
            slot.onPickupFromSlot(player, itemstack);
            return result;
        }
        return null;
    }
}
