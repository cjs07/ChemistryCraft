package org.theshadowsoftime.chemistrycraft.drugs.common.items;

import net.minecraft.item.Item;

public class CCDDrugs {

    public static Item drugCocaine;

    public static void init() {
        drugCocaine = new DrugCocaine();
    }
}
