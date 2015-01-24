package org.theshadowsoftime.chemistrycraft.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import org.theshadowsoftime.chemistrycraft.api.energy.CCEnergyNet;
import org.theshadowsoftime.chemistrycraft.common.blocks.ChemistryCraftBlocks;
import org.theshadowsoftime.chemistrycraft.common.world.ChemistryCraftWorldGenHandler;
import org.theshadowsoftime.chemistrycraft.common.items.ChemistryCraftItems;
import org.theshadowsoftime.chemistrycraft.lib.Constants;

@Mod(modid = Constants.MODID, name = Constants.MODNAME, version = Constants.VERSION)
public class ChemistryCraftMain {

    @Mod.Instance(Constants.MODID)
    public static ChemistryCraftMain instance;

    @SidedProxy(clientSide = Constants.CLIENT_PROXY_CLASS, serverSide = Constants.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    CCEnergyNet energyNet;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        energyNet = new CCEnergyNet();
        ChemistryCraftBlocks.init();
        ChemistryCraftItems.init();
        GameRegistry.registerWorldGenerator(new ChemistryCraftWorldGenHandler(), 1);
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ChemistryCraftRecipes.init();
        proxy.registerTileEntities();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new ChemistryCraftGUIHandler());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
