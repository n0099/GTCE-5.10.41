package gregtech.api.gui;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class GT_Container_3by3 extends GT_ContainerMetaTile_Machine {

    public GT_Container_3by3(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(aInventoryPlayer, aTileEntity);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        func_75146_a(new Slot(mTileEntity, 0, 62, 17));
        func_75146_a(new Slot(mTileEntity, 1, 80, 17));
        func_75146_a(new Slot(mTileEntity, 2, 98, 17));
        func_75146_a(new Slot(mTileEntity, 3, 62, 35));
        func_75146_a(new Slot(mTileEntity, 4, 80, 35));
        func_75146_a(new Slot(mTileEntity, 5, 98, 35));
        func_75146_a(new Slot(mTileEntity, 6, 62, 53));
        func_75146_a(new Slot(mTileEntity, 7, 80, 53));
        func_75146_a(new Slot(mTileEntity, 8, 98, 53));
    }

    @Override
    public int getSlotCount() {
        return 9;
    }

    @Override
    public int getShiftClickSlotCount() {
        return 9;
    }
}
