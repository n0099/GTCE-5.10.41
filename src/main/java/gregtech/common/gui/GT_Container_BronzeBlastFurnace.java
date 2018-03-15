package gregtech.common.gui;

import gregtech.api.gui.GT_ContainerMetaTile_Machine;
import gregtech.api.gui.GT_Slot_Output;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class GT_Container_BronzeBlastFurnace
        extends GT_ContainerMetaTile_Machine {
    public GT_Container_BronzeBlastFurnace(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(aInventoryPlayer, aTileEntity);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        func_75146_a(new Slot(this.mTileEntity, 0, 34, 16));
        func_75146_a(new Slot(this.mTileEntity, 1, 34, 34));
        func_75146_a(new GT_Slot_Output(this.mTileEntity, 2, 86, 25));
        func_75146_a(new GT_Slot_Output(this.mTileEntity, 3, 104, 25));
    }

    @Override
    public int getSlotCount() {
        return 4;
    }

    @Override
    public int getShiftClickSlotCount() {
        return 2;
    }
}
