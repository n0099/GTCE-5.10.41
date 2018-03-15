package gregtech.api.gui;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Maintenance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;

public class GT_Container_MaintenanceHatch extends GT_ContainerMetaTile_Machine {

    public GT_Container_MaintenanceHatch(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(aInventoryPlayer, aTileEntity);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        func_75146_a(new GT_Slot_Holo(mTileEntity, 0, 80, 35, false, false, 1));
    }

    @Override
    public ItemStack func_184996_a(int aSlotIndex, int aMouseclick, ClickType aShifthold, EntityPlayer aPlayer) {
        if (aSlotIndex != 0) return super.func_184996_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
        ItemStack tStack = aPlayer.field_71071_by.func_70445_o();
        if (tStack != null) {
            ((GT_MetaTileEntity_Hatch_Maintenance) mTileEntity.getMetaTileEntity()).onToolClick(tStack, aPlayer);
            if (tStack.field_77994_a <= 0) aPlayer.field_71071_by.func_70437_b(null);
        }
        return null;
    }
}
