package org.theshadowsoftime.chemistrycraft.drugs.common.items.seeds;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import org.theshadowsoftime.chemistrycraft.drugs.common.crops.CCDCrops;

public class CCDSeeds {

    public static Item seedCoca;

    public static void init() {
        seedCoca = new SeedCoca(CCDCrops.cropCoca, Blocks.farmland);
    }
}
