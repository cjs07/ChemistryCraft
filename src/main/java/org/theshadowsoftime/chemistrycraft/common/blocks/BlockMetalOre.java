package org.theshadowsoftime.chemistrycraft.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import org.theshadowsoftime.chemistrycraft.lib.Constants;

import java.util.Random;

public class BlockMetalOre extends Block{

    private String name;

    public BlockMetalOre(String name, int requiredMiningLevel) {
        super(Material.rock);
        this.name = name;
        setBlockName(Constants.MODID + "_" + name);
        setCreativeTab(CreativeTabs.tabBlock);
        GameRegistry.registerBlock(this, name);
        setBlockTextureName(Constants.MODID + ":" + name);
        setHardness(3.0f);
        setHarvestLevel("pickaxe", requiredMiningLevel);
        OreDictionary.registerOre(name, this);
    }

    @Override
    public Item getItemDropped(int par1, Random random, int par3) {
        return Item.getItemFromBlock(this);
    }
}
