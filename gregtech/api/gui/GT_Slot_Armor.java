package gregtech.api.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Slot_Armor extends Slot {
    final EntityEquipmentSlot mArmorType;
    final EntityPlayer mPlayer;

    public GT_Slot_Armor(IInventory par2IInventory, int par3, int par4, int par5, EntityEquipmentSlot par6, EntityPlayer aPlayer) {
        super(par2IInventory, par3, par4, par5);
        mArmorType = par6;
        mPlayer = aPlayer;
    }

    @Override
    public int func_75219_a() {
        return 1;
    }

    @Override
    public boolean func_75214_a(ItemStack aStack) {
        return aStack != null && aStack.func_77973_b() != null && aStack.func_77973_b().isValidArmor(aStack, mArmorType, mPlayer);
    }
}
