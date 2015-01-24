package org.theshadowsoftime.chemistrycraft.common.blocks.generators;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.theshadowsoftime.chemistrycraft.common.ChemistryCraftMain;
import org.theshadowsoftime.chemistrycraft.lib.Constants;
import org.theshadowsoftime.chemistrycraft.common.tileentities.TileEntityGeneratorCombustion;

public class GeneratorCombustion extends ChemistryCraftGenerator{

    private TileEntityGeneratorCombustion generator;

    //TODO: REGISTER CREATIVE TABS FOR GENERATORS(CUSTOM TAB FOR THEM)
    public GeneratorCombustion() {
        super(Material.ground);
        name = "generatorCombustion";
        setCreativeTab(CreativeTabs.tabBlock);
        setBlockName(Constants.MODID + "_" + name);
        setHardness(2.5f);
        setBlockTextureName(name);
        GameRegistry.registerBlock(this, name);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return this.generator = new TileEntityGeneratorCombustion();
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        for (int i =0; i < icons.length; i++) {
            if (i == ForgeDirection.DOWN.ordinal()) {
                iconRegister.registerIcon(Constants.MODID + ":" + name + i);
            } else {
                iconRegister.registerIcon(Constants.MODID + ":" + name + "side");
            }
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return icons[side];
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack) {
        if (generator != null) {
            System.out.println("[CC Debug] The local tile entity instance in the generator's block class is not null");
            if (world.getTileEntity(x, y, z) != generator) {
                System.out.println("[CC Debug] The tile entity at the blocks location is not the same as the local tile " +
                        "entity in the generator's block class. This is an error. Patching over it now.");
                world.removeTileEntity(x, y, z);
                world.setTileEntity(x, y, z, generator);
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX,
                                    float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        } else {
            if (world.getTileEntity(x, y, z) != null) {
                player.openGui(ChemistryCraftMain.instance, 1, world, x, y, z);
                return true;
            }
        }
        return false;
    }
}