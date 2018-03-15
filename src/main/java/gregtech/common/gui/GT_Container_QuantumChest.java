package gregtech.common.gui;

import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import gregtech.api.gui.GT_ContainerMetaTile_Machine;
import gregtech.api.gui.GT_Slot_Output;
import gregtech.api.gui.GT_Slot_Render;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.common.tileentities.storage.GT_MetaTileEntity_QuantumChest;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class GT_Container_QuantumChest extends GT_ContainerMetaTile_Machine {

    public int mContent = 0;

    public GT_Container_QuantumChest(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(aInventoryPlayer, aTileEntity);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        func_75146_a(new Slot(mTileEntity, 0, 80, 17));
        func_75146_a(new GT_Slot_Output(mTileEntity, 1, 80, 53));
        func_75146_a(new GT_Slot_Render(mTileEntity, 2, 59, 42));
    }

    @Override
    public void func_75142_b() {
        super.func_75142_b();

        if (mTileEntity.isClientSide() || mTileEntity.getMetaTileEntity() == null) return;
        if (mTileEntity.getMetaTileEntity() instanceof GT_MetaTileEntity_QuantumChest) {
            mContent = ((GT_MetaTileEntity_QuantumChest) mTileEntity.getMetaTileEntity()).mItemCount;
        } else {
            mContent = 0;
        }

        for (IContainerListener var1 : this.field_75149_d) {
            var1.func_71112_a(this, 100, mContent & 65535);
            var1.func_71112_a(this, 101, mContent >>> 16);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void func_75137_b(int par1, int par2) {
        super.func_75137_b(par1, par2);
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
