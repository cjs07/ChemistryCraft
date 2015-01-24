package org.theshadowsoftime.chemistrycraft.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import org.theshadowsoftime.chemistrycraft.lib.Constants;

public class ItemTreatedCork extends Item {

    private String name = "treatedCork";

    public ItemTreatedCork() {
        setUnlocalizedName(Constants.MODID + "_" +name);
        GameRegistry.registerItem(this, name);
        setCreativeTab(CreativeTabs.tabMaterials);
    }
}
