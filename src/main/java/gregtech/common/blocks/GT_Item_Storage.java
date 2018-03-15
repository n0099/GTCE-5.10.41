package gregtech.common.blocks;

import gregtech.api.GregTech_API;
import gregtech.api.util.GT_LanguageManager;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

public class GT_Item_Storage extends ItemBlock {
    public GT_Item_Storage(Block par1) {
        super(par1);
        func_77656_e(0);
        func_77627_a(true);
        func_77637_a(GregTech_API.TAB_GREGTECH_MATERIALS);
    }

    @Override
    public String func_77653_i(ItemStack stack) {
        return GT_LanguageManager.getTranslation(func_77667_c(stack) + ".name");
    }

    @Override
    public String func_77667_c(ItemStack aStack) {
        return this.field_150939_a.func_149739_a() + "." + getDamage(aStack);
    }

    @Override
    public int func_77647_b(int aMeta) {
        return aMeta;
    }

}
