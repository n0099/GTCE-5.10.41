package gregtech.api.util;

import gregtech.api.enums.ItemList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_CreativeTab extends CreativeTabs {
    public GT_CreativeTab(String aName, String aLocalName) {
        super("GregTech." + aName);
        GT_LanguageManager.addStringLocalization("itemGroup.GregTech." + aName, aLocalName);
    }

    @Override
    public ItemStack func_151244_d() {
        return ItemList.Tool_Cheat.get(1, new ItemStack(Blocks.field_150339_S, 1));
    }

    @Override
    public Item func_78016_d() {
        return ItemList.Circuit_Integrated.getItem();
    }
}
