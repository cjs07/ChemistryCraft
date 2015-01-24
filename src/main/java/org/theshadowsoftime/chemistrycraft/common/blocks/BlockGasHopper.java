package org.theshadowsoftime.chemistrycraft.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import org.theshadowsoftime.chemistrycraft.lib.Constants;

public class BlockGasHopper extends Block {

    private String name = "gasHopper";

    public BlockGasHopper() {
        super(Material.anvil);
        setCreativeTab(CreativeTabs.tabBlock);
        setBlockName(Constants.MODID + "_" + name);
        GameRegistry.registerBlock(this, name);
    }
}
