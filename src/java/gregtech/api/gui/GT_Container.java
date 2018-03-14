package gregtech.api.gui;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * <p/>
 * Main Container-Class, used for all my GUIs
 */
public class GT_Container extends Container {
    public IGregTechTileEntity mTileEntity;
    public InventoryPlayer mPlayerInventory;

    public GT_Container(InventoryPlayer aPlayerInventory, IGregTechTileEntity aTileEntityInventory) {

        mTileEntity = aTileEntityInventory;
        mPlayerInventory = aPlayerInventory;
    }

    /**
     * To add the Slots to your GUI
     */
    public void addSlots(InventoryPlayer aPlayerInventory) {
        //
    }

    /**
     * Amount of regular Slots in the GUI (so, non-HoloSlots)
     */
    public int getSlotCount() {
        return 0;
    }

    /**
     * Amount of ALL Slots in the GUI including HoloSlots and ArmorSlots, but excluding regular Player Slots
     */
    protected final int getAllSlotCount() {
        if (field_75151_b != null) {
            if (doesBindPlayerInventory()) return field_75151_b.size() - 36;
            return field_75151_b.size();
        }
        return getSlotCount();
    }

    /**
     * Start-Index of the usable Slots (the first non-HoloSlot)
     */
    public int getSlotStartIndex() {
        return 0;
    }

    public int getShiftClickStartIndex() {
        return getSlotStartIndex();
    }

    /**
     * Amount of Slots in the GUI the player can Shift-Click into. Uses also getSlotStartIndex
     */
    public int getShiftClickSlotCount() {
        return 0;
    }

    /**
     * Is Player-Inventory visible?
     */
    public boolean doesBindPlayerInventory() {
        return true;
    }

    /**
     * Override this Function with something like "return mTileEntity.isUseableByPlayer(aPlayer);"
     */
    @Override
    public boolean func_75145_c(EntityPlayer aPlayer) {
        return false;
    }

    protected void bindPlayerInventory(InventoryPlayer aInventoryPlayer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                func_75146_a(new Slot(aInventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            func_75146_a(new Slot(aInventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack func_184996_a(int aSlotIndex, int aMouseclick, ClickType aShifthold, EntityPlayer aPlayer) {
        mTileEntity.func_70296_d();

        if (aSlotIndex >= 0) {
            if (field_75151_b.get(aSlotIndex) == null || field_75151_b.get(aSlotIndex) instanceof GT_Slot_Holo)
                return null;
            if (!(field_75151_b.get(aSlotIndex) instanceof GT_Slot_Armor)) if (aSlotIndex < getAllSlotCount())
                if (aSlotIndex < getSlotStartIndex() || aSlotIndex >= getSlotStartIndex() + getSlotCount()) return null;
        }

        try {
            return super.func_184996_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
        } catch (Throwable e) {
            e.printStackTrace(GT_Log.err);
        }

        ItemStack rStack = null;
        InventoryPlayer aPlayerInventory = aPlayer.field_71071_by;
        Slot aSlot;
        ItemStack tTempStack;
        int tTempStackSize;
        ItemStack aHoldStack;

        if ((aShifthold == ClickType.THROW || aShifthold == ClickType.PICKUP) && (aMouseclick == 0 || aMouseclick == 1)) {
            if (aSlotIndex == -999) {
                if (aPlayerInventory.func_70445_o() != null) {
                    if (aMouseclick == 0) {
                        aPlayer.func_71019_a(aPlayerInventory.func_70445_o(), true);
                        aPlayerInventory.func_70437_b(null);
                    }
                    if (aMouseclick == 1) {
                        aPlayer.func_71019_a(aPlayerInventory.func_70445_o().func_77979_a(1), true);
                        if (aPlayerInventory.func_70445_o().field_77994_a == 0) {
                            aPlayerInventory.func_70437_b(null);
                        }
                    }
                }
            } else if (aShifthold == ClickType.PICKUP_ALL) {
                aSlot = (Slot) this.field_75151_b.get(aSlotIndex);
                if (aSlot != null && aSlot.func_82869_a(aPlayer)) {
                    tTempStack = this.func_82846_b(aPlayer, aSlotIndex);
                    if (tTempStack != null) {
                        rStack = GT_Utility.copy(tTempStack);
                        if (aSlot.func_75211_c() != null && aSlot.func_75211_c().func_77973_b() == tTempStack.func_77973_b()) {
                            func_184996_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
                        }
                    }
                }
            } else {
                if (aSlotIndex < 0) {
                    return null;
                }
                aSlot = (Slot) this.field_75151_b.get(aSlotIndex);
                if (aSlot != null) {
                    tTempStack = aSlot.func_75211_c();
                    ItemStack var13 = aPlayerInventory.func_70445_o();
                    if (tTempStack != null) {
                        rStack = GT_Utility.copy(tTempStack);
                    }
                    if (tTempStack == null) {
                        if (var13 != null && aSlot.func_75214_a(var13)) {
                            tTempStackSize = aMouseclick == 0 ? var13.field_77994_a : 1;
                            if (tTempStackSize > aSlot.func_75219_a()) {
                                tTempStackSize = aSlot.func_75219_a();
                            }
                            aSlot.func_75215_d(var13.func_77979_a(tTempStackSize));

                            if (var13.field_77994_a == 0) {
                                aPlayerInventory.func_70437_b((ItemStack) null);
                            }
                        }
                    } else if (aSlot.func_82869_a(aPlayer)) {
                        if (var13 == null) {
                            tTempStackSize = aMouseclick == 0 ? tTempStack.field_77994_a : (tTempStack.field_77994_a + 1) / 2;
                            aHoldStack = aSlot.func_75209_a(tTempStackSize);
                            aPlayerInventory.func_70437_b(aHoldStack);
                            if (tTempStack.field_77994_a == 0) {
                                aSlot.func_75215_d((ItemStack) null);
                            }
                            aSlot.func_82870_a(aPlayer, aPlayerInventory.func_70445_o());
                        } else if (aSlot.func_75214_a(var13)) {
                            if (tTempStack.func_77973_b() == var13.func_77973_b() && tTempStack.func_77952_i() == var13.func_77952_i() && ItemStack.func_77970_a(tTempStack, var13)) {
                                tTempStackSize = aMouseclick == 0 ? var13.field_77994_a : 1;
                                if (tTempStackSize > aSlot.func_75219_a() - tTempStack.field_77994_a) {
                                    tTempStackSize = aSlot.func_75219_a() - tTempStack.field_77994_a;
                                }
                                if (tTempStackSize > var13.func_77976_d() - tTempStack.field_77994_a) {
                                    tTempStackSize = var13.func_77976_d() - tTempStack.field_77994_a;
                                }
                                var13.func_77979_a(tTempStackSize);
                                if (var13.field_77994_a == 0) {
                                    aPlayerInventory.func_70437_b((ItemStack) null);
                                }
                                tTempStack.field_77994_a += tTempStackSize;
                            } else if (var13.field_77994_a <= aSlot.func_75219_a()) {
                                aSlot.func_75215_d(var13);
                                aPlayerInventory.func_70437_b(tTempStack);
                            }
                        } else if (tTempStack.func_77973_b() == var13.func_77973_b() && var13.func_77976_d() > 1 && (!tTempStack.func_77981_g() || tTempStack.func_77952_i() == var13.func_77952_i()) && ItemStack.func_77970_a(tTempStack, var13)) {
                            tTempStackSize = tTempStack.field_77994_a;

                            if (tTempStackSize > 0 && tTempStackSize + var13.field_77994_a <= var13.func_77976_d()) {
                                var13.field_77994_a += tTempStackSize;
                                tTempStack = aSlot.func_75209_a(tTempStackSize);

                                if (tTempStack.field_77994_a == 0) {
                                    aSlot.func_75215_d((ItemStack) null);
                                }

                                aSlot.func_82870_a(aPlayer, aPlayerInventory.func_70445_o());
                            }
                        }
                    }
                    aSlot.func_75218_e();
                }
            }
        } else if (aShifthold == ClickType.QUICK_MOVE && aMouseclick >= 0 && aMouseclick < 9) {
            aSlot = (Slot) this.field_75151_b.get(aSlotIndex);

            if (aSlot.func_82869_a(aPlayer)) {
                tTempStack = aPlayerInventory.func_70301_a(aMouseclick);
                boolean var9 = tTempStack == null || aSlot.field_75224_c == aPlayerInventory && aSlot.func_75214_a(tTempStack);
                tTempStackSize = -1;

                if (!var9) {
                    tTempStackSize = aPlayerInventory.func_70447_i();
                    var9 |= tTempStackSize > -1;
                }

                if (var9 && aSlot.func_75216_d()) {
                    aHoldStack = aSlot.func_75211_c();
                    aPlayerInventory.func_70299_a(aMouseclick, aHoldStack);

                    if (tTempStack != null && (aSlot.field_75224_c != aPlayerInventory || !aSlot.func_75214_a(tTempStack))) {
                        if (tTempStackSize > -1) {
                            aPlayerInventory.func_70441_a(tTempStack);
                            aSlot.func_75209_a(aHoldStack.field_77994_a);
                            aSlot.func_75215_d((ItemStack) null);
                            aSlot.func_82870_a(aPlayer, aHoldStack);
                        }
                    } else {
                        aSlot.func_75209_a(aHoldStack.field_77994_a);
                        aSlot.func_75215_d(tTempStack);
                        aSlot.func_82870_a(aPlayer, aHoldStack);
                    }
                } else if (tTempStack != null && !aSlot.func_75216_d() && aSlot.func_75214_a(tTempStack)) {
                    aPlayerInventory.func_70299_a(aMouseclick, (ItemStack) null);
                    aSlot.func_75215_d(tTempStack);
                }
            }
        } else if (aShifthold == ClickType.CLONE && aPlayer.field_71075_bZ.field_75098_d && aPlayerInventory.func_70445_o() == null && aSlotIndex >= 0) {
            aSlot = (Slot) this.field_75151_b.get(aSlotIndex);
            if (aSlot != null && aSlot.func_75216_d()) {
                tTempStack = GT_Utility.copy(aSlot.func_75211_c());
                tTempStack.field_77994_a = tTempStack.func_77976_d();
                aPlayerInventory.func_70437_b(tTempStack);
            }
        }
        return rStack;
    }

    @Override
    public ItemStack func_82846_b(EntityPlayer aPlayer, int aSlotIndex) {
        ItemStack stack = null;
        Slot slotObject = (Slot) field_75151_b.get(aSlotIndex);

        mTileEntity.func_70296_d();

        if (getSlotCount() > 0 && !(slotObject instanceof GT_Slot_Holo) && slotObject.func_75216_d()) {
            ItemStack stackInSlot = slotObject.func_75211_c();
            stack = GT_Utility.copy(stackInSlot);

            //TileEntity -> Player
            if (aSlotIndex < getAllSlotCount()) {
                if (doesBindPlayerInventory())
                    if (!func_75135_a(stackInSlot, getAllSlotCount(), getAllSlotCount() + 36, true)) {
                        return null;
                    }
                //Player -> TileEntity
            } else if (!func_75135_a(stackInSlot, getShiftClickStartIndex(), getShiftClickStartIndex() + getShiftClickSlotCount(), false)) {
                return null;
            }

            if (stackInSlot.field_77994_a == 0) {
                slotObject.func_75215_d(null);
            } else {
                slotObject.func_75218_e();
            }
        }
        return stack;
    }

    /**
     * merges provided ItemStack with the first avaliable one in the container/player inventory
     */
    @Override
    protected boolean func_75135_a(ItemStack aStack, int aStartIndex, int aSlotCount, boolean par4) {
        boolean var5 = false;
        int var6 = aStartIndex;

        mTileEntity.func_70296_d();

        if (par4) {
            var6 = aSlotCount - 1;
        }

        Slot var7;
        ItemStack var8;

        if (aStack.func_77985_e()) {
            while (aStack.field_77994_a > 0 && (!par4 && var6 < aSlotCount || par4 && var6 >= aStartIndex)) {
                var7 = (Slot) this.field_75151_b.get(var6);
                var8 = var7.func_75211_c();

                if (!(var7 instanceof GT_Slot_Holo) && !(var7 instanceof GT_Slot_Output) && var8 != null && var8.func_77973_b() == aStack.func_77973_b() && (!aStack.func_77981_g() || aStack.func_77952_i() == var8.func_77952_i()) && ItemStack.func_77970_a(aStack, var8)) {
                    int var9 = var8.field_77994_a + aStack.field_77994_a;

                    if (var9 <= aStack.func_77976_d()) {
                        aStack.field_77994_a = 0;
                        var8.field_77994_a = var9;
                        var7.func_75218_e();
                        var5 = true;
                    } else if (var8.field_77994_a < aStack.func_77976_d()) {
                        aStack.field_77994_a -= aStack.func_77976_d() - var8.field_77994_a;
                        var8.field_77994_a = aStack.func_77976_d();
                        var7.func_75218_e();
                        var5 = true;
                    }
                }

                if (par4) {
                    --var6;
                } else {
                    ++var6;
                }
            }
        }

        if (aStack.field_77994_a > 0) {
            if (par4) {
                var6 = aSlotCount - 1;
            } else {
                var6 = aStartIndex;
            }

            while (!par4 && var6 < aSlotCount || par4 && var6 >= aStartIndex) {
                var7 = (Slot) this.field_75151_b.get(var6);
                var8 = var7.func_75211_c();

                if (var8 == null) {
                    var7.func_75215_d(GT_Utility.copy(aStack));
                    var7.func_75218_e();
                    aStack.field_77994_a = 0;
                    var5 = true;
                    break;
                }

                if (par4) {
                    --var6;
                } else {
                    ++var6;
                }
            }
        }

        return var5;
    }

    @Override
    protected Slot func_75146_a(Slot par1Slot) {
        try {
            return super.func_75146_a(par1Slot);
        } catch (Throwable e) {
            e.printStackTrace(GT_Log.err);
        }
        return par1Slot;
    }

    @Override
    public void func_75132_a(IContainerListener par1ICrafting) {
        try {
            super.func_75132_a(par1ICrafting);
        } catch (Throwable e) {
            e.printStackTrace(GT_Log.err);
        }
    }

    @Override
    public List<ItemStack> func_75138_a() {
        try {
            return super.func_75138_a();
        } catch (Throwable e) {
            e.printStackTrace(GT_Log.err);
        }
        return null;
    }

    @Override
    public void func_82847_b(IContainerListener par1ICrafting) {
        try {
            super.func_82847_b(par1ICrafting);
        } catch (Throwable e) {
            e.printStackTrace(GT_Log.err);
        }
    }

    @Override
    public void func_75142_b() {
        try {
            super.func_75142_b();
        } catch (Throwable e) {
            e.printStackTrace(GT_Log.err);
        }
    }

    @Override
    public boolean func_75140_a(EntityPlayer par1EntityPlayer, int par2) {
        try {
            return super.func_75140_a(par1EntityPlayer, par2);
        } catch (Throwable e) {
            e.printStackTrace(GT_Log.err);
        }
        return false;
    }

    @Override
    public Slot func_75147_a(IInventory par1IInventory, int par2) {
        try {
            return super.func_75147_a(par1IInventory, par2);
        } catch (Throwable e) {
            e.printStackTrace(GT_Log.err);
        }
        return null;
    }

    @Override
    public Slot func_75139_a(int par1) {
        try {
            if (this.field_75151_b.size() > par1) return super.func_75139_a(par1);
        } catch (Throwable e) {
            e.printStackTrace(GT_Log.err);
        }
        return null;
    }

    @Override
    protected void func_75133_b(int par1, int par2, boolean par3, EntityPlayer par4EntityPlayer) {
        try {
            super.func_75133_b(par1, par2, par3, par4EntityPlayer);
        } catch (Throwable e) {
            e.printStackTrace(GT_Log.err);
        }
    }

    @Override
    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        try {
            super.func_75134_a(par1EntityPlayer);
        } catch (Throwable e) {
            e.printStackTrace(GT_Log.err);
        }
    }

    @Override
    public void func_75130_a(IInventory par1IInventory) {
        try {
            super.func_75130_a(par1IInventory);
        } catch (Throwable e) {
            e.printStackTrace(GT_Log.err);
        }
    }

    @Override
    public void func_75141_a(int par1, ItemStack par2ItemStack) {
        try {
            super.func_75141_a(par1, par2ItemStack);
        } catch (Throwable e) {
            e.printStackTrace(GT_Log.err);
        }
    }

    @Override
    public void func_75131_a(ItemStack[] par1ArrayOfItemStack) {
        try {
            super.func_75131_a(par1ArrayOfItemStack);
        } catch (Throwable e) {
            e.printStackTrace(GT_Log.err);
        }
    }

    @Override
    public void func_75137_b(int par1, int par2) {
        try {
            super.func_75137_b(par1, par2);
        } catch (Throwable e) {
            e.printStackTrace(GT_Log.err);
        }
    }

    @Override
    public short func_75136_a(InventoryPlayer par1InventoryPlayer) {
        try {
            return super.func_75136_a(par1InventoryPlayer);
        } catch (Throwable e) {
            e.printStackTrace(GT_Log.err);
        }
        return 0;
    }

    @Override
    public boolean func_94531_b(Slot par1Slot) {
        try {
            return super.func_94531_b(par1Slot);
        } catch (Throwable e) {
            e.printStackTrace(GT_Log.err);
        }
        return true;
    }
}
