package gregtech.common.items.behaviors;

import gregtech.api.enums.GT_Values;
import gregtech.api.interfaces.IItemBehaviour;
import gregtech.api.items.GT_MetaBase_Item;
import gregtech.api.util.GT_Utility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Behaviour_Sonictron
        extends Behaviour_None {
    public static final IItemBehaviour<GT_MetaBase_Item> INSTANCE = new Behaviour_Sonictron();

    public static int getCurrentIndex(ItemStack aStack) {
        NBTTagCompound tNBTTagCompound = aStack.func_77978_p();
        if (tNBTTagCompound == null) {
            tNBTTagCompound = new NBTTagCompound();
        }
        return tNBTTagCompound.func_74762_e("mCurrentIndex");
    }

    public static int getTickTimer(ItemStack aStack) {
        NBTTagCompound tNBTTagCompound = aStack.func_77978_p();
        if (tNBTTagCompound == null) {
            tNBTTagCompound = new NBTTagCompound();
        }
        return tNBTTagCompound.func_74762_e("mTickTimer");
    }

    public static NBTTagCompound setCurrentIndex(ItemStack aStack, int aIndex) {
        NBTTagCompound tNBTTagCompound = aStack.func_77978_p();
        if (tNBTTagCompound == null) {
            tNBTTagCompound = new NBTTagCompound();
        }
        tNBTTagCompound.func_74768_a("mCurrentIndex", aIndex);
        return tNBTTagCompound;
    }

    public static NBTTagCompound setTickTimer(ItemStack aStack, int aTime) {
        NBTTagCompound tNBTTagCompound = aStack.func_77978_p();
        if (tNBTTagCompound == null) {
            tNBTTagCompound = new NBTTagCompound();
        }
        tNBTTagCompound.func_74768_a("mTickTimer", aTime);
        return tNBTTagCompound;
    }

    public static ItemStack[] getNBTInventory(ItemStack aStack) {
        ItemStack[] tInventory = new ItemStack[64];
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

    public static void copyInventory(ItemStack[] aInventory, ItemStack[] aNewContent, int aIndexlength) {
        for (int i = 0; i < aIndexlength; i++) {
            if (aNewContent[i] == null) {
                aInventory[i] = null;
            } else {
                aInventory[i] = GT_Utility.copy(aNewContent[i]);
            }
        }
    }

    @Override
    public boolean onItemUseFirst(GT_MetaBase_Item aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, BlockPos blockPos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        setCurrentIndex(aStack, -1);
        return false;
    }

    @Override
    public ItemStack onItemRightClick(GT_MetaBase_Item aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer, EnumHand hand) {
        setCurrentIndex(aStack, 0);
        return aStack;
    }

    @Override
    public void onUpdate(GT_MetaBase_Item aItem, ItemStack aStack, World aWorld, Entity aPlayer, int aTimer, boolean aIsInHand) {
        int tTickTimer = getTickTimer(aStack);
        int tCurrentIndex = getCurrentIndex(aStack);
        if ((tTickTimer++ % 2 == 0) && (tCurrentIndex > -1)) {
            ItemStack[] tInventory = getNBTInventory(aStack);
            GT_Values.GT.doSonictronSound(tInventory[tCurrentIndex], aPlayer.field_70170_p, aPlayer.field_70165_t, aPlayer.field_70163_u, aPlayer.field_70161_v);
            tCurrentIndex++;
            if (tCurrentIndex > 63) {
                tCurrentIndex = -1;
            }
        }
        setTickTimer(aStack, tTickTimer);
        setCurrentIndex(aStack, tCurrentIndex);
    }
}
