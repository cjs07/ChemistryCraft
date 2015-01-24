package org.theshadowsoftime.chemistrycraft.drugs.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.VillagerRegistry;
import org.theshadowsoftime.chemistrycraft.drugs.common.crops.CCDCrops;
import org.theshadowsoftime.chemistrycraft.drugs.common.items.CCDDrugs;
import org.theshadowsoftime.chemistrycraft.drugs.common.items.seeds.CCDSeeds;

@Mod(modid = "ccdrugs", name = "Chemistry Craft Drugs", version = "1.7.10-0.0.1")
public class CCDrugs {

    @Mod.Instance("ccdrugs")
    public static CCDrugs instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        CCDSeeds.init();
        CCDCrops.init();
        CCDDrugs.init();
        VillagerRegistry.instance().registerVillageTradeHandler(5, new CCDTradeHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {}

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {}
}
