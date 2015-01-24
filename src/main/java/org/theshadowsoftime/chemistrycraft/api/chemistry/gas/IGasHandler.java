package org.theshadowsoftime.chemistrycraft.api.chemistry.gas;

import net.minecraftforge.common.util.ForgeDirection;

public interface IGasHandler {

    public int recieveGas(ForgeDirection side, GasStack stack);

    public GasStack drawGas(ForgeDirection side, int amount);

    public boolean canRecieveGas(ForgeDirection side, Gas type);

    public boolean canDrawGas(ForgeDirection side, Gas type);
}
