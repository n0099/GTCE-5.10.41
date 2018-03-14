package gregtech.common.items.behaviors;

import gregtech.api.items.GT_MetaBase_Item;
import gregtech.api.util.GT_Utility;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.List;

public class Behaviour_DataOrb
        extends Behaviour_None {
    public static void copyInventory(ItemStack[] aInventory, ItemStack[] aNewContent, int aIndexlength) {
        for (int i = 0; i < aIndexlength; i++) {
            if (aNewContent[i] == null) {
                aInventory[i] = null;
            } else {
                aInventory[i] = GT_Utility.copy(aNewContent[i]);
            }
        }
    }

    public static String getDataName(ItemStack aStack) {
        NBTTagCompound tNBT = aStack.func_77978_p();
        if (tNBT == null) {
            return "";
        }
        return tNBT.func_74779_i("mDataName");
    }

    public static String getDataTitle(ItemStack aStack) {
        NBTTagCompound tNBT = aStack.func_77978_p();
        if (tNBT == null) {
            return "";
        }
        return tNBT.func_74779_i("mDataTitle");
    }

    public static NBTTagCompound setDataName(ItemStack aStack, String aDataName) {
        NBTTagCompound tNBT = aStack.func_77978_p();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
        }
        tNBT.func_74778_a("mDataName", aDataName);
        aStack.func_77982_d(tNBT);
        return tNBT;
    }

    public static NBTTagCompound setDataTitle(ItemStack aStack, String aDataTitle) {
        NBTTagCompound tNBT = aStack.func_77978_p();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
        }
        tNBT.func_74778_a("mDataTitle", aDataTitle);
        aStack.func_77982_d(tNBT);
        return tNBT;
    }

    public static ItemStack[] getNBTInventory(ItemStack aStack) {
        ItemStack[] tInventory = new ItemStack[256];
        NBTTagCompound tNBT = aStack.func_77978_p();
        if (tNBT == null) {
            return tInventory;
        }
        NBTTagList tNBT_ItemList = tNBT.func_150295_c("Inventory", 10);
        for (int i = 0; i < tNBT_ItemList.func_74745_c(); i++) {
            NBTTagCompound tag = tNBT_ItemList.func_150305_b(i);
            byte slot = tag.func_74771_c("Slot");
            if ((slot >= 0) && (slot < tInventory.length)) {
                tInventory[slot] = GT_Utility.loadItem(tag);
            }
        }
        return tInventory;
    }

    public static NBTTagCompound setNBTInventory(ItemStack aStack, ItemStack[] aInventory) {
        NBTTagCompound tNBT = aStack.func_77978_p();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
        }
        NBTTagList tNBT_ItemList = new NBTTagList();
        for (int i = 0; i < aInventory.length; i++) {
            ItemStack stack = aInventory[i];
            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.func_74774_a("Slot", (byte) i);
                stack.func_77955_b(tag);
                tNBT_ItemList.func_74742_a(tag);
            }
        }
        tNBT.func_74782_a("Inventory", tNBT_ItemList);
        aStack.func_77982_d(tNBT);
        return tNBT;
    }

    @Override
    public List<String> getAdditionalToolTips(GT_MetaBase_Item aItem, List<String> aList, ItemStack aStack) {
        if (!(getDataTitle(aStack).length() == 0)) {
            aList.add(getDataTitle(aStack));
            aList.add(getDataName(aStack));
        }
        return aList;
    }

}
