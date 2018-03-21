package gregtech.api.gui;

import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

import java.util.Iterator;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * <p/>
 * The Container I use for all my Basic Tanks
 */
public class GT_Container_BasicTank extends GT_ContainerMetaTile_Machine {

    public int mContent = 0;

    public GT_Container_BasicTank(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(aInventoryPlayer, aTileEntity);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity, 0, 80, 17));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 1, 80, 53));
        addSlotToContainer(new GT_Slot_Render(mTileEntity, 2, 59, 42));
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (mTileEntity.isClientSide() || mTileEntity.getMetaTileEntity() == null) return;
        if (((GT_MetaTileEntity_BasicTank) mTileEntity.getMetaTileEntity()).mFluid != null)
            mContent = ((GT_MetaTileEntity_BasicTank) mTileEntity.getMetaTileEntity()).mFluid.amount;
        else
            mContent = 0;
        for (IContainerListener var1 : this.listeners) {
            var1.sendProgressBarUpdate(this, 100, mContent & 65535);
            var1.sendProgressBarUpdate(this, 101, mContent >>> 16);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        super.updateProgressBar(par1, par2);
        switch (par1) {
            case 100:
                mContent = mContent & -65536 | par2;
                break;
            case 101:
                mContent = mContent & 65535 | par2 << 16;
                break;
        }
    }

    @Override
    public int getSlotCount() {
        return 2;
    }

    @Override
    public int getShiftClickSlotCount() {
        return 1;
    }
}
