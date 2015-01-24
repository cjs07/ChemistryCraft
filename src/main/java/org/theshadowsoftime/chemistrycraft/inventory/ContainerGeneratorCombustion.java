package org.theshadowsoftime.chemistrycraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.theshadowsoftime.chemistrycraft.tileentities.TileEntityGeneratorCombustion;

public class ContainerGeneratorCombustion extends Container {

    TileEntityGeneratorCombustion generator;

    public ContainerGeneratorCombustion(InventoryPlayer invPlayer, TileEntityGeneratorCombustion generator) {
        this.generator = generator;

        addSlotToContainer(new SlotGeneratorCombustion(invPlayer, 0, 81, 53));

        //player inventory
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x  < 9; x++) {
                addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 8 + 18 * x, 132 + y * 18));
            }
        }

        //hotbar slots
        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 190));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return generator.isUseableByPlayer(player);
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNum) {
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(slotNum);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotNum == 0) {
                if (!mergeItemStack(itemstack1, 1, 37, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (slotNum >= 1) {
                if (TileEntityGeneratorCombustion.isItemFuel(itemstack1)) {
                    if (!mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                }
            } else if (!mergeItemStack(itemstack1, 1, 37, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }
        return itemstack;
    }
}
