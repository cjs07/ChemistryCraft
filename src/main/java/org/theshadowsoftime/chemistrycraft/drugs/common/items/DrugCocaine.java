package org.theshadowsoftime.chemistrycraft.drugs.common.items;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.theshadowsoftime.chemistrycraft.drugs.common.EnumDrugType;

public class DrugCocaine extends ItemDrug {

    public DrugCocaine() {
        super(1, 0.1f, false, "drugCocaine", EnumDrugType.STIMULANT, new PotionEffect(Potion.hunger.getId(), 15 * 60 * 20, 2),
                new PotionEffect(Potion.weakness.getId(), 15 * 60 * 20, 3),
                new PotionEffect(Potion.moveSlowdown.getId(), 15 * 60 * 20, 3));
    }
}
