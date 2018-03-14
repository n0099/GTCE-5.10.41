package gregtech.common.blocks;

import java.util.List;

import gregtech.api.GregTech_API;
import gregtech.api.util.GT_LanguageManager;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public abstract class GT_Item_Casings_Abstract
        extends ItemBlock {
    protected final String mNoMobsToolTip = GT_LanguageManager.addStringLocalization("gt.nomobspawnsonthisblock", "Mobs cannot Spawn on this Block");
    protected final String mNoTileEntityToolTip = GT_LanguageManager.addStringLocalization("gt.notileentityinthisblock", "This is NOT a TileEntity!");
    protected final String mCoil01Tooltip = GT_LanguageManager.addStringLocalization("gt.coil01tooltip", "Base Heating Capacity = 1800 Kelvin");
    protected final String mCoil02Tooltip = GT_LanguageManager.addStringLocalization("gt.coil02tooltip", "Base Heating Capacity = 2700 Kelvin");
    protected final String mCoil03Tooltip = GT_LanguageManager.addStringLocalization("gt.coil03tooltip", "Base Heating Capacity = 3600 Kelvin");
    protected final String mCoil04Tooltip = GT_LanguageManager.addStringLocalization("gt.coil04tooltip", "Base Heating Capacity = 4500 Kelvin");
    protected final String mCoil05Tooltip = GT_LanguageManager.addStringLocalization("gt.coil05tooltip", "Base Heating Capacity = 5400 Kelvin");
    protected final String mCoil06Tooltip = GT_LanguageManager.addStringLocalization("gt.coil06tooltip", "Base Heating Capacity = 7200 Kelvin");
    protected final String mCoil07Tooltip = GT_LanguageManager.addStringLocalization("gt.coil07tooltip", "Base Heating Capacity = 9001 Kelvin");
    protected final String mCoilOverheated1Tooltip = GT_LanguageManager.addStringLocalization("gt.coil.overheated1.tooltip", "These coils are deprecated");
    protected final String mCoilOverheated2Tooltip = GT_LanguageManager.addStringLocalization("gt.coil.overheated2.tooltip", "Place in crafting grid to get regular coils");
    protected final String mBlastProofTooltip = GT_LanguageManager.addStringLocalization("gt.blastprooftooltip", "This Block is Blast Proof");
    public GT_Item_Casings_Abstract(Block par1) {
        super(par1);
        func_77656_e(0);
        func_77627_a(true);
        func_77637_a(GregTech_API.TAB_GREGTECH_MATERIALS);
    }

    @Override
    public int func_77647_b(int aMeta) {
        return aMeta;
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
    public void func_77624_a(ItemStack aStack, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
        super.func_77624_a(aStack, aPlayer, aList, aF3_H);
        aList.add(this.mNoMobsToolTip);
        aList.add(this.mNoTileEntityToolTip);
    }
}
