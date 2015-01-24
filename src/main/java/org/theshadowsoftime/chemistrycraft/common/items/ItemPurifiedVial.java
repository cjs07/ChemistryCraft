package org.theshadowsoftime.chemistrycraft.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.theshadowsoftime.chemistrycraft.lib.Constants;

import java.util.Random;

public class ItemPurifiedVial extends Item {

    private boolean isPure; //represents if the vial is pure, false if made from purified glass
    private boolean isFilled;
    private int diffusionRate;

    String name = "purifiedVial";


    public ItemPurifiedVial(boolean isPure, int diffusionRate) {
        this.isPure = isPure;
        this.diffusionRate = diffusionRate;
        isFilled = false;

        setUnlocalizedName(Constants.MODID + "_" + name);
        GameRegistry.registerItem(this, name);
        setCreativeTab(CreativeTabs.tabMaterials);
        setMaxStackSize(1);
    }

    public ItemPurifiedVial(boolean isPure, Random random) {
        this(isPure, random.nextInt(12000) + 6000);
    }

    public ItemPurifiedVial(Random random) {
        this(false, random.nextInt(12000) + 6000);
    }

    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10){
        System.out.println("[ChemistryCraft] " + name + " used by " + player + " at " + x + " " + y + " " + z);
        if(world.isRemote) {
           if(!world.isAirBlock(x, y, z)) {
                isFilled = true;
                InventoryPlayer playerInv = player.inventory;
                playerInv.consumeInventoryItem(playerInv.getCurrentItem().getItem());
               //TODO: REPLACE EMPTY VIAL WITH SAMPLE OF ITEM (DIRT, GRAVEL, ETC.)
            }
        }
        return false;
    }

    public boolean getIsPure() {
        return isPure;
    }

    public void setIsPure(boolean isPure) {
        this.isPure = isPure;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }

    public int getDiffusionRate() {
        return diffusionRate;
    }
}
