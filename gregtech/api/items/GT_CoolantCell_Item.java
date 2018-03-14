package gregtech.api.items;

import gregtech.api.GregTech_API;
import ic2.core.util.StackUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class GT_CoolantCell_Item
        extends GT_Generic_Item {
    protected int heatStorage;

    public GT_CoolantCell_Item(String aUnlocalized, String aEnglish, int aMaxStore) {
        super(aUnlocalized, aEnglish, null);
        this.func_77625_d(1);
        this.func_77656_e(100);
        setNoRepair();
        this.heatStorage = aMaxStore;
        this.func_77637_a(GregTech_API.TAB_GREGTECH);
    }

    protected static int getHeatOfStack(ItemStack aStack) {
        NBTTagCompound tNBT = aStack.func_77978_p();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.func_77982_d(tNBT);
        }
        return tNBT.func_74762_e("heat");
    }

    protected void setHeatForStack(ItemStack aStack, int aHeat) {
        NBTTagCompound tNBT = aStack.func_77978_p();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.func_77982_d(tNBT);
        }
        tNBT.func_74768_a("heat", aHeat);
        if (this.heatStorage > 0) {
            double var4 = (double) aHeat / (double) this.heatStorage;
            int var6 = (int) (aStack.func_77958_k() * var4);
            if (var6 >= aStack.func_77958_k()) {
                var6 = aStack.func_77958_k() - 1;
            }
            aStack.func_77964_b(var6);
        }
    }

    public void addAdditionalToolTips(List aList, ItemStack aStack, EntityPlayer aPlayer) {
        super.addAdditionalToolTips(aList, aStack, aPlayer);
        aList.add("Stored Heat: " + getHeatOfStack(aStack));
        switch (getControlTagOfStack(aStack)) {
            case 1:
                aList.add(I18n.func_135052_a("ic2.reactoritem.heatwarning.line1"));
                aList.add(I18n.func_135052_a("ic2.reactoritem.heatwarning.line2"));
        }
    }

    public int getControlTagOfStack(ItemStack stack) {
        NBTTagCompound nbtData = StackUtil.getOrCreateNbtData(stack);
        return nbtData.func_74762_e("tag");
    }

    public void setControlTagOfStack(ItemStack stack, int tag) {
        NBTTagCompound nbtData = StackUtil.getOrCreateNbtData(stack);
        nbtData.func_74768_a("tag", tag);
    }
}
