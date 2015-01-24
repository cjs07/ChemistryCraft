package org.theshadowsoftime.chemistrycraft.drugs.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import org.theshadowsoftime.chemistrycraft.drugs.common.EnumDrugType;

public class ItemDrug extends ItemFood {

    String name;
    EnumDrugType drugType;
    PotionEffect[] sideEffects;
    int dosesTaken;
    boolean influened;

    public ItemDrug(int healAmount, float saturationModifier, boolean isWolfsFavoriteMeat, String name, EnumDrugType drugType,
                    PotionEffect... sideEffects) {
        super(healAmount, saturationModifier, isWolfsFavoriteMeat);
        this.drugType = drugType;
        this.sideEffects = sideEffects;
        this.name = name;

        setUnlocalizedName("ccdrugs_" + name);
        setTextureName("ccdrugs:" + name);
        setCreativeTab(CreativeTabs.tabBrewing);
        GameRegistry.registerItem(this, name);
        setAlwaysEdible();
    }

    @Override
    public void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player) {
        super.onFoodEaten(itemStack, world, player);

        dosesTaken++;
        for (int i = 0; i < sideEffects.length; i++) {
            if (!world.isRemote && sideEffects[i].getPotionID() > 0) {
                player.addPotionEffect(new PotionEffect(sideEffects[i].getPotionID(), sideEffects[i].getDuration(),
                        sideEffects[i].getAmplifier(), sideEffects[i].getIsAmbient()));
            }
        }
    }
}
