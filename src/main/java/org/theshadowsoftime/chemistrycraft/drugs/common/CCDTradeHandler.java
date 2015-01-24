package org.theshadowsoftime.chemistrycraft.drugs.common;

import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import org.theshadowsoftime.chemistrycraft.drugs.common.items.CCDDrugs;

import java.util.Random;

public class CCDTradeHandler implements VillagerRegistry.IVillageTradeHandler {

    @Override
    public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
        if (villager.getProfession() == 5) {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 3 + random.nextInt(4)),
                    new ItemStack(CCDDrugs.drugCocaine, 3)));
        } else {
            return;
        }
    }
}
