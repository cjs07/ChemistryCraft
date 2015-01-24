package org.theshadowsoftime.chemistrycraft.api.energy;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class EnergyCable implements IEnergyCable {

    @Override
    public TileEntity[] getConnectedCables(TileEntity tileEntity) {
        TileEntity[] cables = new TileEntity[] {null, null, null, null, null, null};
        int x = tileEntity.xCoord, y = tileEntity.yCoord, z = tileEntity.zCoord;
        World world = tileEntity.getWorldObj();
        for (ForgeDirection to : ForgeDirection.VALID_DIRECTIONS) {
            if (world.getTileEntity(x, y, z) instanceof IEnergyCable) {
                cables[to.ordinal()] = world.getTileEntity(x + to.offsetX, y + to.offsetY, z + to.offsetZ);
            }
        }
        return cables;
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return 0;
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return 0;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }
}
