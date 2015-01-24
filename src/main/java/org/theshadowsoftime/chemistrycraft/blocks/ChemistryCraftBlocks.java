package org.theshadowsoftime.chemistrycraft.blocks;

import net.minecraft.block.Block;
import org.theshadowsoftime.chemistrycraft.blocks.generators.GeneratorCombustion;

public class ChemistryCraftBlocks {

    public static Block purifiedGlass;
    public static Block pureGlass;
    public static Block gasHopper;
    public static Block gasCollector;

    //ores
    public static Block oreNickel;
    public static Block oreCopper;
    public static Block oreZinc;
    public static Block oreSilver;
    public static Block oreCadmium;
    public static Block oreIndium;
    public static Block oreTin;
    public static Block oreTungsten;
    public static Block oreOsmium;
    public static Block oreIridium;
    public static Block orePlatinum;
    public static Block oreLead;

    //generators
    public static Block combustiongenerator;

    public static void init() {
        purifiedGlass = new BlockPurifiedGlass();
        gasHopper = new BlockGasHopper();
        gasCollector = new BlockGasCollector();

        //ores
        oreNickel = new BlockMetalOre("oreNickel", 1);
        oreCopper = new BlockMetalOre("oreCopper", 1);
        oreZinc = new BlockMetalOre("oreZinc", 1);
        oreSilver = new BlockMetalOre("oreSilver", 2);
        oreCadmium = new BlockMetalOre("oreCadmium", 2);
        oreIndium = new BlockMetalOre("oreIndium", 2);
        oreTin = new BlockMetalOre("oreTin", 1);
        oreTungsten = new BlockMetalOre("oreTungsten", 2);
        oreOsmium = new BlockMetalOre("oreOsmium", 2);
        oreIridium = new BlockMetalOre("oreIridium", 3);
        orePlatinum = new BlockMetalOre("orePlatinum", 2);
        oreLead = new BlockMetalOre("oreLead", 2);

        //generators
        combustiongenerator = new GeneratorCombustion();
    }
}