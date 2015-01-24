package org.theshadowsoftime.chemistrycraft.api.energy;

import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.tileentity.TileEntity;

public interface IEnergyCable extends IEnergyReceiver, IEnergyProvider {

    public TileEntity[] getConnectedCables(TileEntity tileEntity);
}
