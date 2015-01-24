package org.theshadowsoftime.chemistrycraft.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import org.theshadowsoftime.chemistrycraft.lib.Constants;

public class ItemMetalIngot extends Item {

    String name;

    public ItemMetalIngot(String name) {
        this.name = name;
        setUnlocalizedName(Constants.MODID + "_" + name);
        GameRegistry.registerItem(this, name);
        setCreativeTab(CreativeTabs.tabMaterials);
        OreDictionary.registerOre(name, this);
        setTextureName(Constants.MODID + ":" + name);
    }
}
