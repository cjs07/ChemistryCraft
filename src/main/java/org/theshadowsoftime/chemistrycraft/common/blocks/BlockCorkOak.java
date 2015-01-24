package org.theshadowsoftime.chemistrycraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWood;
import net.minecraft.item.Item;
import org.theshadowsoftime.chemistrycraft.common.items.ChemistryCraftItems;

import java.util.Random;

public class BlockCorkOak extends BlockWood {

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_1496d50_3_) {
        int rand = p_149650_2_.nextInt(2);
        if (rand == 1 && Block.isEqualTo(this, Block.getBlockFromName("wood"))) {
            return ChemistryCraftItems.untreatedCork;
        } else {
            return super.getItemDropped(p_149650_1_, p_149650_2_, p_1496d50_3_);
        }
    }
}
