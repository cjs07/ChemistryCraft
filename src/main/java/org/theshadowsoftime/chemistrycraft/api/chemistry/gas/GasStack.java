package org.theshadowsoftime.chemistrycraft.api.chemistry.gas;

import net.minecraft.nbt.NBTTagCompound;

public class GasStack {

    Gas type;

    int amount;

    public GasStack(int id, int amount) {
        type = GasRegistry.getGas(id);
        this.amount = amount;
    }

    public GasStack(Gas gas, int amount) {
        type = gas;
        this.amount = amount;
    }

    private GasStack(){}

    public Gas getGas() {
        return type;
    }

    public NBTTagCompound write(NBTTagCompound tag) {
        type.write(tag);
        tag.setInteger("amount", amount);
        return tag;
    }

    public void read(NBTTagCompound tag) {
        type = Gas.readFromNBT(tag);
        amount = tag.getInteger("amount");
    }

    public static GasStack readNBT(NBTTagCompound tag) {
        if (tag == null || tag.hasNoTags()) {
            return null;
        }

        GasStack stack = new GasStack();
        stack.read(tag);
        if (stack.getGas() == null || stack.amount <= 0) {
            return null;
        }
        return stack;
    }

    public GasStack copy() {
        return new GasStack(type, amount);
    }

    public boolean isGasEqual(GasStack stack) {
        return stack != null && getGas() == stack.getGas();
    }

    @Override
    public String toString() {
        return amount + " mL of " + type.getName() + ".";
    }
}
