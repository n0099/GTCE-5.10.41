package gregtech.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class GT_Item_GeneratedOres extends ItemBlock {


    public GT_Item_GeneratedOres(Block block) {
        super(block);
        func_77627_a(true);
    }

    @Override
    public String func_77667_c(ItemStack stack) {
        return ((GT_Block_GeneratedOres) field_150939_a).mUnlocalizedName + "." + stack.func_77952_i();
    }

    @Override
    public int func_77647_b(int damage) {
        return Math.max(0, Math.min(16, damage)); //checks to prevent outofbounds
    }

}
