package org.theshadowsoftime.chemistrycraft.blocks;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import org.theshadowsoftime.chemistrycraft.ChemistryCraftMain;
import org.theshadowsoftime.chemistrycraft.lib.Constants;
import org.theshadowsoftime.chemistrycraft.tileentities.TileEntityGasCollector;

public class BlockGasCollector extends BlockContainer {

    private String name = "gasCollector";

    public BlockGasCollector() {
        super(Material.iron);
        setCreativeTab(CreativeTabs.tabBlock);
        setBlockName(Constants.MODID + "_" + name);
        GameRegistry.registerBlock(this, name);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX,
                                    float hitY, float hitZ) {
        if(!world.isRemote) {
            FMLNetworkHandler.openGui(player, ChemistryCraftMain.instance, 0, world, x, y, z);
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityGasCollector();
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }
}
