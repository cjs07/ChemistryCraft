package org.theshadowsoftime.chemistrycraft.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import org.theshadowsoftime.chemistrycraft.lib.Constants;

public class ItemUntreatedCork extends Item {

    private String name = "untreatedCork";

    public ItemUntreatedCork () {
        setUnlocalizedName(Constants.MODID + "_" + name);
        GameRegistry.registerItem(this, name);
        setCreativeTab(CreativeTabs.tabMaterials);
    }
}
