package org.theshadowsoftime.chemistrycraft.proxy;

import cpw.mods.fml.common.registry.GameRegistry;
import org.theshadowsoftime.chemistrycraft.tileentities.TileEntityGasCollector;

public class CommonProxy {

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityGasCollector.class, TileEntityGasCollector.publicName);
    }
}
