package org.theshadowsoftime.chemistrycraft.api.energy;

import ic2.api.energy.EnergyNet;
import ic2.api.energy.IEnergyNet;
import ic2.api.energy.NodeStats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

public class CCEnergyNet implements IEnergyNet {


    static ArrayList<TileEntity> networkTileEntities;

    public CCEnergyNet() {
        initEnergyNet();
    }

    public void initEnergyNet() {
        networkTileEntities = new ArrayList<TileEntity>();
        EnergyNet.instance = this;
    }

    public void registerTileEntity(TileEntity tile) {
        if (tile != null) {
            networkTileEntities.add(tile);
        }
    }

    @Override
    public TileEntity getTileEntity(World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (networkTileEntities.contains(te)) {
            return te;
        } else {
            return null;
        }
    }

    @Override
    public TileEntity getNeighbor(TileEntity te, ForgeDirection dir) {
        if (te != null) {
            World world = te.getWorldObj();
            int x = te.xCoord + dir.offsetX, y = te.yCoord + dir.offsetY, z = te.zCoord + dir.offsetZ;
            TileEntity neighbor = world.getTileEntity(x, y, z);
            if (neighbor != null) {
                return neighbor;
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public double getTotalEnergyEmitted(TileEntity tileEntity) {
        return 0;
    }

    @Override
    public double getTotalEnergySunken(TileEntity tileEntity) {
        return 0;
    }

    @Override
    public NodeStats getNodeStats(TileEntity te) {
        return null;
    }

    @Override
    public double getPowerFromTier(int tier) {
        return tier == 1 ? 32 : tier == 2 ? 128 : tier == 3 ? 512 : tier == 4 ? 2048 : 0;
    }

    @Override
    public int getTierFromPower(double power) {
        return 0;
    }
}
