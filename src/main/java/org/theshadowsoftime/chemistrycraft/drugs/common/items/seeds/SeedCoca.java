package org.theshadowsoftime.chemistrycraft.drugs.common.items.seeds;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSeeds;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.oredict.OreDictionary;

public class SeedCoca extends ItemSeeds implements IPlantable {

    private String name = "seedCoca";

    public SeedCoca(Block p_i45352_1_, Block p_i45352_2_) {
        super(p_i45352_1_, p_i45352_2_);
        setUnlocalizedName("ccdrugs_" + name);
        setTextureName("ccdrugs:" + name);
        GameRegistry.registerItem(this, name);
        OreDictionary.registerOre(name, this);
    }
}
