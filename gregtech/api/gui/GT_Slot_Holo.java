package gregtech.api.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Slot_Holo extends Slot {
    public final int mSlotIndex;
    public boolean mCanInsertItem, mCanStackItem;
    public int mMaxStacksize = 127;

    public GT_Slot_Holo(IInventory par1iInventory, int par2, int par3, int par4, boolean aCanInsertItem, boolean aCanStackItem, int aMaxStacksize) {
        super(par1iInventory, par2, par3, par4);
        mCanInsertItem = aCanInsertItem;
        mCanStackItem = aCanStackItem;
        mMaxStacksize = aMaxStacksize;
        mSlotIndex = par2;
    }

    @Override
    public boolean func_75214_a(ItemStack par1ItemStack) {
        return mCanInsertItem;
    }

    @Override
    public int func_75219_a() {
        return mMaxStacksize;
    }

    //@Override
    //public boolean getHasStack() {
    //    return true;
    //}

    @Override
    public ItemStack func_75209_a(int par1) {
        if (!mCanStackItem) return null;
        return super.func_75209_a(par1);
    }

    @Override
    public boolean func_82869_a(EntityPlayer par1EntityPlayer) {
        return false;
    }
}
