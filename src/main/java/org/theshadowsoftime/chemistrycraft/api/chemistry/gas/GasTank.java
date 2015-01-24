package org.theshadowsoftime.chemistrycraft.api.chemistry.gas;

public class GasTank {

    GasStack stored;
    int maxStored;

    public GasTank(int max) {
        maxStored = max;
    }

    public void setGas(GasStack stack) {
        stored = stack;
        if (stored != null) {
                stored.amount = Math.min(maxStored, stored.amount);
        }
    }

    public GasStack draw(int amount, boolean simulate) {
        if (stored == null || stored.amount <= 0) {
            return null;
        }
        GasStack drawn = new GasStack(stored.getGas(), Math.min(getStored(), amount));

        
    }
}
