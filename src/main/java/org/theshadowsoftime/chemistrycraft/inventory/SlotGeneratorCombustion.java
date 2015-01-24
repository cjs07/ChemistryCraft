package org.theshadowsoftime.chemistrycraft.inventory;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotGeneratorCombustion extends Slot {

    public SlotGeneratorCombustion(IInventory inventory, int slotIndex, int x, int y) {
        super(inventory, slotIndex, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack item) {
        return item.getItem() == Items.coal;
    }
}
