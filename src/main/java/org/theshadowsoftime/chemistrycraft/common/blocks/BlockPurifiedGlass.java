package org.theshadowsoftime.chemistrycraft.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import org.theshadowsoftime.chemistrycraft.lib.Constants;
import java.util.Random;

public class BlockPurifiedGlass extends Block {

    private String name = "purifiedGlass";
    public BlockPurifiedGlass() {
        super(Material.glass);
        setCreativeTab(CreativeTabs.tabBlock);
        setLightOpacity(0);
        setHardness(0.3f);
        setStepSound(soundTypeGlass);
        setBlockName(Constants.MODID + "_" + name);
        setBlockTextureName(Constants.MODID + ":" + name);
        GameRegistry.registerBlock(this, name);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }


    @Override
    public boolean canSilkHarvest() {
        return true;
    }
}
