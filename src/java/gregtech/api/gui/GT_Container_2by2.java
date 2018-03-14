package gregtech.api.gui;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class GT_Container_2by2 extends GT_ContainerMetaTile_Machine {

    public GT_Container_2by2(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(aInventoryPlayer, aTileEntity);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        func_75146_a(new Slot(mTileEntity, 0, 71, 26));
        func_75146_a(new Slot(mTileEntity, 1, 89, 26));
        func_75146_a(new Slot(mTileEntity, 2, 71, 44));
        func_75146_a(new Slot(mTileEntity, 3, 89, 44));
    }

    @Override
    public int getSlotCount() {
        return 4;
    }

    @Override
    public int getShiftClickSlotCount() {
        return 4;
    }
}
