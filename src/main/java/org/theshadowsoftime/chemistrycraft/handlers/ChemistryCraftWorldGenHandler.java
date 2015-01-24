package org.theshadowsoftime.chemistrycraft.handlers;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import org.theshadowsoftime.chemistrycraft.blocks.ChemistryCraftBlocks;

import java.util.Random;

public class ChemistryCraftWorldGenHandler implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
                         IChunkProvider chunkProvider) {
        switch (world.provider.dimensionId) {
            case 0: generateOverworld(random, chunkX * 16, chunkZ * 16, world); break;
            case 1: generateEnd(random, chunkX * 16, chunkZ * 16, world); break;
            case -1: generateNether(random, chunkX * 16, chunkZ * 16, world); break;
        }
    }

    public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int minVeinSize,
                            int maxVeinSize, int chancesToSpawn, int minY, int maxY ) {
        WorldGenMinable minable = new WorldGenMinable(block, (minVeinSize + random.nextInt(maxVeinSize - minVeinSize)),
                Blocks.stone);
        for(int i = 0; i < chancesToSpawn; i++)
        {
            int posX = blockXPos + random.nextInt(16);
            int posY = minY + random.nextInt(maxY - minY);
            int posZ = blockZPos + random.nextInt(16);
            minable.generate(world, random, posX, posY, posZ);
        }
    }

    public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int minVeinSize,
                            int maxVeinSize, int chancesToSpawn, int minY, int maxY, boolean requiresLava ) {
        WorldGenMinable minable = new WorldGenMinable(block, (minVeinSize + random.nextInt(maxVeinSize - minVeinSize)),
                Blocks.stone);
        for(int i = 0; i < chancesToSpawn; i++)
        {
            int posX = blockXPos + random.nextInt(16);
            int posY = minY + random.nextInt(maxY - minY);
            int posZ = blockZPos + random.nextInt(16);

            if(requiresLava && !(world.getBlock(posX, posY + 1, posZ) == Blocks.lava)){
                return;
            } else {
                minable.generate(world, random, posX, posY, posZ);
            }
        }
    }

    private void generateOverworld(Random random, int x, int z, World world) {
        this.addOreSpawn(ChemistryCraftBlocks.oreNickel, world, random, x, z, 4, 10, 5, 0, 50);
        this.addOreSpawn(ChemistryCraftBlocks.oreCopper, world, random, x, z, 6, 15, 9, 16, 72);
        this.addOreSpawn(ChemistryCraftBlocks.oreZinc, world, random, x, z, 2, 8, 6, 0, 48);
        this.addOreSpawn(ChemistryCraftBlocks.oreSilver, world, random, x, z, 2, 8, 5, 0, 42);
        this.addOreSpawn(ChemistryCraftBlocks.oreCadmium, world, random, x, z, 2, 8, 4, 0, 44);
        this.addOreSpawn(ChemistryCraftBlocks.oreIndium, world, random, x, z, 2, 8, 4, 0, 44);
        this.addOreSpawn(ChemistryCraftBlocks.oreTin, world, random, x, z, 2, 8, 6, 0, 44);
        this.addOreSpawn(ChemistryCraftBlocks.oreTungsten, world, random, x, z, 2, 5, 4, 0, 22);
        this.addOreSpawn(ChemistryCraftBlocks.oreOsmium, world, random, x, z, 4, 12, 7, 0, 128);
        this.addOreSpawn(ChemistryCraftBlocks.oreIridium, world, random, x, z, 1, 3, 2, 0, 16);
        this.addOreSpawn(ChemistryCraftBlocks.orePlatinum, world, random, x, z, 2, 8, 4, 0, 32);
        this.addOreSpawn(ChemistryCraftBlocks.oreLead, world, random, x, z, 2, 8, 4, 0, 46);
    }

    private void generateEnd(Random random, int i, int i1, World world) {
    }

    private void generateNether(Random random, int i, int i1, World world) {
    }
}
