package org.theshadowsoftime.chemistrycraft.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import org.theshadowsoftime.chemistrycraft.lib.Constants;

import java.util.Random;

public class ItemCorkStopper extends Item{

    private int diffusionRate;
    private String name = "corkStopper";

    public ItemCorkStopper(Random random) {
        setUnlocalizedName(Constants.MODID + "_" + name);
        GameRegistry.registerItem(this, name);
        setCreativeTab(CreativeTabs.tabMaterials);
        diffusionRate = random.nextInt(12000) + 6000;
    }

    public int getDiffusionRate() {
        return diffusionRate;
    }
}
