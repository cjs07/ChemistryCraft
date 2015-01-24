package org.theshadowsoftime.chemistrycraft.drugs.common.crops;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class CropCoca extends BlockCrops {

    private String name = "cropCoca";

    protected CropCoca() {
        super();
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.5F, 1.0F);
        setTickRandomly(true);
        setBlockName("ccdrugs_" + name);
        setBlockTextureName("ccdrugs:" + name);
        GameRegistry.registerBlock(this, name);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    @Override
    public int getRenderType() {
        return 6;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (world.getBlockMetadata(x, y, z) == 1) {
            return;
        }
        if (random.nextInt(isFertile(world, x, y, z) ? 12 : 25) != 0) {
            return;
        }
        world.setBlockMetadataWithNotify(x, y, z, 1, 3);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        if (!canBlockStay(world, x, y, z)) {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z) , 0);
            world.setBlock(x, y, z, Blocks.air, 0, 3);
        }
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        Block soil = world.getBlock(x, y - 1, z);
        if (soil != null && soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, null)) {
            Block plant = world.getBlock(x, y, z);
            if (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int damageDropped(int meta) {
        switch(meta) {
            case 0: return 0;
            case 1: return 1;
            default: return -1;
        }
    }
}
