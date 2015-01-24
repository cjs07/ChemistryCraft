package org.theshadowsoftime.chemistrycraft.blocks.generators;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.theshadowsoftime.chemistrycraft.ChemistryCraftMain;
import org.theshadowsoftime.chemistrycraft.lib.Constants;
import org.theshadowsoftime.chemistrycraft.tileentities.TileEntityGeneratorCombustion;

public class GeneratorCombustion extends ChemistryCraftGenerator{

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
        return new TileEntityGeneratorCombustion();
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
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX,
                                    float hitY, float hitZ) {
        if (world.isRemote) {
            if (world.getTileEntity(x, y, z) != null) {
                player.openGui(ChemistryCraftMain.instance, 1, world, x, y, z);
                return true;
            } else {
                FMLNetworkHandler.openGui(player, ChemistryCraftMain.instance, 0, world, x, y, z);
                return true;
            }
        }
        return false;
    }
}