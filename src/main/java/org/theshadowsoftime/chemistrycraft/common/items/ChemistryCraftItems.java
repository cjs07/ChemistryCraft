package org.theshadowsoftime.chemistrycraft.common.items;

import net.minecraft.item.Item;

import java.util.Random;

public class ChemistryCraftItems {

    public static Item untreatedCork;
    public static Item treatedCork;
    public static Item corkStopper;
    public static ItemPurifiedVial purifiedVial;

    //Ore Ingots

    public static Item ingotNickel;
    public static Item ingotCopper;
    public static Item ingotZinc;
    public static Item ingotSilver;
    public static Item ingotCadmium;
    public static Item ingotIndium;
    public static Item ingotTin;
    public static Item ingotTungsten;
    public static Item ingotOsmium;
    public static Item ingotIridium;
    public static Item ingotPlatinum;
    public static Item ingotLead;


    static Random random;

    public static void init() {
        random = new Random();

        untreatedCork = new ItemUntreatedCork();
        treatedCork = new ItemTreatedCork();
        corkStopper = new ItemCorkStopper(random);
        purifiedVial = new ItemPurifiedVial(random);

        ingotNickel = new ItemMetalIngot("ingotNickel");
        ingotCopper = new ItemMetalIngot("ingotCopper");
        ingotZinc = new ItemMetalIngot("ingotZinc");
        ingotSilver = new ItemMetalIngot("ingotSilver");
        ingotCadmium = new ItemMetalIngot("ingotCadmium");
        ingotIndium = new ItemMetalIngot("ingotIndium");
        ingotTin = new ItemMetalIngot("ingotTin");
        ingotTungsten = new ItemMetalIngot("ingotTungsten");
        ingotOsmium = new ItemMetalIngot("ingotOsmium");
        ingotIridium = new ItemMetalIngot("ingotIridium");
        ingotPlatinum = new ItemMetalIngot("ingotPlatinum");
        ingotLead = new ItemMetalIngot("ingotLead");
    }
}