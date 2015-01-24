package org.theshadowsoftime.chemistrycraft.common;

import cpw.mods.fml.common.registry.GameRegistry;
import org.theshadowsoftime.chemistrycraft.common.tileentities.TileEntityGasCollector;
import org.theshadowsoftime.chemistrycraft.common.tileentities.TileEntityGeneratorCombustion;

public class CommonProxy {

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityGasCollector.class, TileEntityGasCollector.publicName);
        GameRegistry.registerTileEntity(TileEntityGeneratorCombustion.class, "generatorCombustion");
    }
}
