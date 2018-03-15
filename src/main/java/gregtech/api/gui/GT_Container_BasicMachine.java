package gregtech.api.gui;

import net.minecraft.inventory.ClickType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.Iterator;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * <p/>
 * The Container I use for all my Basic Machines
 */
public class GT_Container_BasicMachine extends GT_Container_BasicTank {

    public boolean mFluidTransfer = false, mItemTransfer = false, mStuttering = false;

    public GT_Container_BasicMachine(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(aInventoryPlayer, aTileEntity);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        func_75146_a(new GT_Slot_Holo(mTileEntity, 0, 8, 63, false, true, 1));
        func_75146_a(new GT_Slot_Holo(mTileEntity, 0, 26, 63, false, true, 1));
        func_75146_a(new GT_Slot_Render(mTileEntity, 2, 107, 63));

        int tStartIndex = ((GT_MetaTileEntity_BasicMachine) mTileEntity.getMetaTileEntity()).getInputSlot();

        switch (((GT_MetaTileEntity_BasicMachine) mTileEntity.getMetaTileEntity()).mInputSlotCount) {
            case 0:
                break;
            case 1:
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 53, 25));
                break;
            case 2:
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 35, 25));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 53, 25));
                break;
            case 3:
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 17, 25));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 35, 25));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 53, 25));
                break;
            case 4:
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 35, 16));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 53, 16));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 35, 34));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 53, 34));
                break;
            case 5:
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 17, 16));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 35, 16));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 53, 16));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 35, 34));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 53, 34));
                break;
            case 6:
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 17, 16));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 35, 16));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 53, 16));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 17, 34));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 35, 34));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 53, 34));
                break;
            case 7:
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 17, 7));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 35, 7));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 53, 7));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 17, 25));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 35, 25));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 53, 25));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 17, 43));
                break;
            case 8:
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 17, 7));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 35, 7));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 53, 7));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 17, 25));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 35, 25));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 53, 25));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 17, 43));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 35, 43));
                break;
            default:
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 17, 7));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 35, 7));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 53, 7));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 17, 25));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 35, 25));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 53, 25));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 17, 43));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 35, 43));
                func_75146_a(new Slot(mTileEntity, tStartIndex++, 53, 43));
                break;
        }

        tStartIndex = ((GT_MetaTileEntity_BasicMachine) mTileEntity.getMetaTileEntity()).getOutputSlot();

        switch (((GT_MetaTileEntity_BasicMachine) mTileEntity.getMetaTileEntity()).mOutputItems.length) {
            case 0:
                break;
            case 1:
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 107, 25));
                break;
            case 2:
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 107, 25));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 125, 25));
                break;
            case 3:
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 107, 25));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 125, 25));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 143, 25));
                break;
            case 4:
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 107, 16));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 125, 16));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 107, 34));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 125, 34));
                break;
            case 5:
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 107, 16));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 125, 16));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 143, 16));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 107, 34));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 125, 34));
                break;
            case 6:
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 107, 16));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 125, 16));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 143, 16));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 107, 34));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 125, 34));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 143, 34));
                break;
            case 7:
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 107, 7));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 125, 7));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 143, 7));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 107, 25));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 125, 25));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 143, 25));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 107, 43));
                break;
            case 8:
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 107, 7));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 125, 7));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 143, 7));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 107, 25));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 125, 25));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 143, 25));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 107, 43));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 125, 43));
                break;
            default:
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 107, 7));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 125, 7));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 143, 7));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 107, 25));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 125, 25));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 143, 25));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 107, 43));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 125, 43));
                func_75146_a(new GT_Slot_Output(mTileEntity, tStartIndex++, 143, 43));
                break;
        }

        func_75146_a(new Slot(mTileEntity, 1, 80, 63));
        func_75146_a(new Slot(mTileEntity, 3, 125, 63));
        func_75146_a(new GT_Slot_Render(mTileEntity, tStartIndex++, 53, 63));
    }

    @Override
    public ItemStack func_184996_a(int aSlotIndex, int aMouseclick, ClickType aShifthold, EntityPlayer aPlayer) {
        switch (aSlotIndex) {
            case 0:
                if (mTileEntity.getMetaTileEntity() == null) return null;
                ((GT_MetaTileEntity_BasicMachine) mTileEntity.getMetaTileEntity()).mFluidTransfer = !((GT_MetaTileEntity_BasicMachine) mTileEntity.getMetaTileEntity()).mFluidTransfer;
                return null;
            case 1:
                if (mTileEntity.getMetaTileEntity() == null) return null;
                ((GT_MetaTileEntity_BasicMachine) mTileEntity.getMetaTileEntity()).mItemTransfer = !((GT_MetaTileEntity_BasicMachine) mTileEntity.getMetaTileEntity()).mItemTransfer;
                return null;
            default:
                return super.func_184996_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
        }
    }

    @Override
    public void func_75142_b() {
        super.func_75142_b();
        if (mTileEntity.isClientSide() || mTileEntity.getMetaTileEntity() == null) return;

        mFluidTransfer = ((GT_MetaTileEntity_BasicMachine) mTileEntity.getMetaTileEntity()).mFluidTransfer;
        mItemTransfer = ((GT_MetaTileEntity_BasicMachine) mTileEntity.getMetaTileEntity()).mItemTransfer;
        mStuttering = ((GT_MetaTileEntity_BasicMachine) mTileEntity.getMetaTileEntity()).mStuttering;

        Iterator<IContainerListener> var2 = this.field_75149_d.iterator();
        while (var2.hasNext()) {
            IContainerListener var1 = var2.next();
            var1.func_71112_a(this, 102, mFluidTransfer ? 1 : 0);
            var1.func_71112_a(this, 103, mItemTransfer ? 1 : 0);
            var1.func_71112_a(this, 104, mStuttering ? 1 : 0);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void func_75137_b(int par1, int par2) {
        super.func_75137_b(par1, par2);
        switch (par1) {
            case 102:
                mFluidTransfer = (par2 != 0);
                break;
            case 103:
                mItemTransfer = (par2 != 0);
                break;
            case 104:
                mStuttering = (par2 != 0);
                break;
        }
    }

    @Override
    public int getSlotStartIndex() {
        return 3;
    }

    @Override
    public int getShiftClickStartIndex() {
        return 3;
    }

    @Override
    public int getSlotCount() {
        return getShiftClickSlotCount() + ((GT_MetaTileEntity_BasicMachine) mTileEntity.getMetaTileEntity()).mOutputItems.length + 2;
    }

    @Override
    public int getShiftClickSlotCount() {
        return ((GT_MetaTileEntity_BasicMachine) mTileEntity.getMetaTileEntity()).mInputSlotCount;
    }
}
