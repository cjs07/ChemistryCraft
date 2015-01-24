package org.theshadowsoftime.chemistrycraft.blocks.generators;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public abstract class ChemistryCraftGenerator extends BlockContainer {

    String name;
    IIcon[] icons = new IIcon[6];

    public ChemistryCraftGenerator(Material material) {
        super(material);
    }

    @Override
    public abstract TileEntity createNewTileEntity(World world, int i);
}
