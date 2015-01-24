package org.theshadowsoftime.chemistrycraft.common;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.theshadowsoftime.chemistrycraft.common.blocks.ChemistryCraftBlocks;
import org.theshadowsoftime.chemistrycraft.common.items.ChemistryCraftItems;

public class ChemistryCraftRecipes {

    static ItemStack purifiedGlassStack = new ItemStack(ChemistryCraftBlocks.purifiedGlass);
    static ItemStack untreatedCorkStack = new ItemStack(ChemistryCraftItems.untreatedCork);
    static ItemStack oakPlanksStack = new ItemStack(Blocks.planks);
    static ItemStack treatedCorkStack = new ItemStack(ChemistryCraftItems.treatedCork);
    static ItemStack corkStopperStack = new ItemStack(ChemistryCraftItems.corkStopper);
    static ItemStack purifiedVialStack = new ItemStack(ChemistryCraftItems.purifiedVial);
    static ItemStack pureVialStack = new ItemStack((Item) ChemistryCraftItems.purifiedVial);
    static ItemStack gasHopperStack = new ItemStack(ChemistryCraftBlocks.gasHopper);
    static ItemStack gasCollectorStack = new ItemStack(ChemistryCraftBlocks.gasCollector);
    static ItemStack nickelIngotStack = new ItemStack(ChemistryCraftItems.ingotNickel);
    static ItemStack copperIngotStack = new ItemStack(ChemistryCraftItems.ingotCopper);
    static ItemStack zincIngotStack = new ItemStack(ChemistryCraftItems.ingotZinc);
    static ItemStack silverIngotStack = new ItemStack(ChemistryCraftItems.ingotSilver);
    static ItemStack cadmiumIngotStack = new ItemStack(ChemistryCraftItems.ingotCadmium);
    static ItemStack indiumIngotStack = new ItemStack(ChemistryCraftItems.ingotIndium);
    static ItemStack tungstenIngotStack = new ItemStack(ChemistryCraftItems.ingotTungsten);
    static ItemStack tinIngotStack = new ItemStack(ChemistryCraftItems.ingotTin);
    static ItemStack osmiumIngotStack = new ItemStack(ChemistryCraftItems.ingotOsmium);
    static ItemStack iridiumIngotStack = new ItemStack(ChemistryCraftItems.ingotIridium);
    static ItemStack platinumIngotStack = new ItemStack(ChemistryCraftItems.ingotPlatinum);
    static ItemStack leadnIgotStack = new ItemStack(ChemistryCraftItems.ingotLead);

    public static void init() {
        oakPlanksStack.setItemDamage(1);
        initCrafting();
        initSmelting();
    }

    static void initCrafting() {
        //shapeless recipes
        GameRegistry.addShapelessRecipe(corkStopperStack, treatedCorkStack);


        //shaped recipes
        GameRegistry.addShapedRecipe(purifiedVialStack, "xyx", "x x", "xxx", 'x', purifiedGlassStack, 'y', corkStopperStack);
        GameRegistry.addShapedRecipe(gasHopperStack, " x ", " y ", "   ", 'x', ChemistryCraftItems.purifiedVial, 'y', Blocks.hopper);
        GameRegistry.addShapedRecipe(gasCollectorStack, "xyx", "x x", "xzx", 'x', Items.iron_ingot, 'y', ChemistryCraftBlocks.gasHopper,
                'z', ChemistryCraftItems.purifiedVial);
    }

    static void initSmelting() {
        GameRegistry.addSmelting(Blocks.glass, purifiedGlassStack, 0.3f);
        GameRegistry.addSmelting(Blocks.planks, untreatedCorkStack, 0.3f);
        GameRegistry.addSmelting(untreatedCorkStack, treatedCorkStack, 0.3f);
        //ore to ingot smelting
        GameRegistry.addSmelting(ChemistryCraftBlocks.oreNickel, nickelIngotStack, 0.7f);
        GameRegistry.addSmelting(ChemistryCraftBlocks.oreCopper, copperIngotStack, 0.7f);
        GameRegistry.addSmelting(ChemistryCraftBlocks.oreZinc, zincIngotStack, 0.7f);
        GameRegistry.addSmelting(ChemistryCraftBlocks.oreSilver, silverIngotStack, 1.0f);
        GameRegistry.addSmelting(ChemistryCraftBlocks.oreCadmium, cadmiumIngotStack, 1.0f);
        GameRegistry.addSmelting(ChemistryCraftBlocks.oreIndium, indiumIngotStack, 1.0f);
        GameRegistry.addSmelting(ChemistryCraftBlocks.oreTin, tinIngotStack, 1.0f);
        GameRegistry.addSmelting(ChemistryCraftBlocks.oreTungsten, tungstenIngotStack, 1.0f);
        GameRegistry.addSmelting(ChemistryCraftBlocks.oreOsmium, osmiumIngotStack, 1.0f);
        GameRegistry.addSmelting(ChemistryCraftBlocks.oreIridium, iridiumIngotStack, 1.5f);
        GameRegistry.addSmelting(ChemistryCraftBlocks.orePlatinum, platinumIngotStack, 1.0f);
        GameRegistry.addSmelting(ChemistryCraftBlocks.oreLead, leadnIgotStack,1.0f);
    }
}