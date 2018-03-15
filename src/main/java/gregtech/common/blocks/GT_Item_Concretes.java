package gregtech.common.blocks;

import gregtech.api.util.GT_LanguageManager;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

public class GT_Item_Concretes
        extends GT_Item_Stones_Abstract {
    private final String mRunFasterToolTip = GT_LanguageManager.addStringLocalization("gt.runfastertooltip", "You can walk faster on this Block");

    public GT_Item_Concretes(Block par1) {
        super(par1);
    }

    @Override
    public void func_77624_a(ItemStack aStack, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
        super.func_77624_a(aStack, aPlayer, aList, aF3_H);
        aList.add(this.mRunFasterToolTip);
    }
}
