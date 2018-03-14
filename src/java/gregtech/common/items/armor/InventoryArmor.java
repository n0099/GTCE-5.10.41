package gregtech.common.items.armor;

import gregtech.api.util.GT_Utility;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;

public class InventoryArmor implements IInventory {

    public ItemStack[] parts;
    public ItemStack parent;
    //	float[] def = new float[32];
    public int maxCharge;
    public int charge;
    public ArmorData data;

    public InventoryArmor(Class<ModularArmor_Item> class1, ItemStack currentEquippedItem) {
        this.parts = new ItemStack[16];
        this.parent = currentEquippedItem;
        setUID(false);
        readFromNBT(currentEquippedItem.func_77978_p());
//		for (int i = 0; i < def.length; i++) {
//			def[i] = 0.0f;
//		}
        if (currentEquippedItem.func_77973_b() instanceof ModularArmor_Item) {
            data = ((ModularArmor_Item) currentEquippedItem.func_77973_b()).data;
        }

    }

    @Override
    public int func_70302_i_() {
        return this.parts.length;
    }

    @Override
    public ItemStack func_70301_a(int i) {
        return parts[i];
    }

    @Override
    public ItemStack func_70298_a(int i, int j) {
        if (parts[i] == null) {
            return null;
        }

        ItemStack product;
        if (parts[i].field_77994_a <= j) {
            product = parts[i];
            parts[i] = null;
//			def = ArmorCalculation.calculateArmor(parts);
            data.calculateArmor(parts);
            return product;
        } else {
            product = parts[i].func_77979_a(j);
            if (parts[i].field_77994_a == 0) {
                parts[i] = null;
            }
//			def = ArmorCalculation.calculateArmor(parts);
            data.calculateArmor(parts);
            return product;
        }
    }

    @Override
    public int func_174887_a_(int id) {
        return 0;
    }

    @Override
    public void func_174885_b(int id, int value) {
    }

    @Override
    public int func_174890_g() {
        return 0;
    }

    @Override
    public void func_174888_l() {
    }

    @Override
    public ITextComponent func_145748_c_() {
        return new TextComponentString(func_70005_c_());
    }

    @Override
    public ItemStack func_70304_b(int slot) {
        if (parts[slot] == null) {
            return null;
        }
        ItemStack toReturn = parts[slot];
        parts[slot] = null;
        return toReturn;
    }

    @Override
    public void func_70299_a(int i, ItemStack itemstack) {
        parts[i] = itemstack;
//		def = ArmorCalculation.calculateArmor(parts);
        data.calculateArmor(parts);
    }

    @Override
    public String func_70005_c_() {
        return "container.armor";
    }

    @Override
    public boolean func_145818_k_() {
        return true;
    }

    @Override
    public int func_70297_j_() {
        return 1;
    }

    @Override
    public void func_70296_d() {
    }

    @Override
    public boolean func_70300_a(EntityPlayer p_70300_1_) {
        return true;
    }

    @Override
    public void func_174889_b(EntityPlayer entityPlayer) {
    }

    @Override
    public void func_174886_c(EntityPlayer entityPlayer) {
    }

    @Override
    public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
        return true;
    }

    public void onGuiSaved(EntityPlayer entityplayer) {
        parent = findParentInInventory(entityplayer);
        if (parent != null) {
            save();
        }
    }

    public void save() {
        NBTTagCompound nbt = parent.func_77978_p();
        if (nbt == null) {
            nbt = new NBTTagCompound();
        }
        writeToNBT(nbt);
        ModularArmor_Item tmp = (ModularArmor_Item) parent.func_77973_b();
        tmp.data.calculateArmor(parts);
        parent.func_77982_d(nbt);
    }

    public void writeToNBT(NBTTagCompound nbt) {
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < parts.length; i++) {
            if (parts[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.func_74774_a("Slot", (byte) i);
                parts[i].func_77955_b(nbttagcompound1);
                nbttaglist.func_74742_a(nbttagcompound1);
            }
        }
        nbt.func_74782_a("Items", nbttaglist);
    }

    public ItemStack findParentInInventory(EntityPlayer player) {
        for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
            ItemStack stack = player.field_71071_by.func_70301_a(i);
            if (isIdenticalItem(stack, parent)) {
                return stack;
            }
        }
        return parent;
    }

    public static boolean isIdenticalItem(ItemStack lhs, ItemStack rhs) {
        if (lhs == null || rhs == null) {
            return false;
        }

        if (lhs.func_77973_b() != rhs.func_77973_b()) {
            return false;
        }

        if (lhs.func_77952_i() != OreDictionary.WILDCARD_VALUE) {
            if (lhs.func_77952_i() != rhs.func_77952_i()) {
                return false;
            }
        }

        return ItemStack.func_77970_a(lhs, rhs);
    }

    public void readFromNBT(NBTTagCompound nbt) {
        if (nbt == null) {
            return;
        }
        if (nbt.func_74764_b("Items")) {
            NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
            parts = new ItemStack[func_70302_i_()];
            for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
                NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
                byte byte0 = nbttagcompound1.func_74771_c("Slot");
                if (byte0 >= 0 && byte0 < parts.length) {
                    parts[byte0] = ItemStack.func_77949_a(nbttagcompound1);
                    //parts[12]= UT.Fluids.display(UT.Fluids.water(1234), true);
                }
            }
        }

    }

    protected void setUID(boolean override) {
        if (parent.func_77978_p() == null) {
            parent.func_77982_d(new NBTTagCompound());
        }
        NBTTagCompound nbt = parent.func_77978_p();
        if (override || !nbt.func_74764_b("UID")) {
            nbt.func_74768_a("UID", new Random().nextInt());
        }
    }

    public static int getOccupiedSlotCount(ItemStack itemStack) {
        NBTTagCompound nbt = itemStack.func_77978_p();
        if (nbt == null) {
            return 0;
        }

        int count = 0;
        if (nbt.func_74764_b("Items")) {
            NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
            for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
                NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
                ItemStack itemStack1 = ItemStack.func_77949_a(nbttagcompound1);
                if (itemStack1 != null && itemStack1.field_77994_a > 0) {
                    count++;
                }
            }
        }
        return count;
    }

}
