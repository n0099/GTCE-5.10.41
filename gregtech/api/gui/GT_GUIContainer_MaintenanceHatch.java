package gregtech.api.gui;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;

import static gregtech.api.enums.GT_Values.RES_PATH_GUI;

public class GT_GUIContainer_MaintenanceHatch extends GT_GUIContainerMetaTile_Machine {

    public GT_GUIContainer_MaintenanceHatch(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_MaintenanceHatch(aInventoryPlayer, aTileEntity), RES_PATH_GUI + "Maintenance.png");
    }

    @Override
    protected void func_146979_b(int par1, int par2) {
        field_146289_q.func_78276_b("Maintenance Hatch", 8, 4, 4210752);
        field_146289_q.func_78276_b("Click with Tool to repair.", 8, 12, 4210752);
    }

    @Override
    protected void func_146976_a(float par1, int par2, int par3) {
        super.func_146976_a(par1, par2, par3);
        int x = (field_146294_l - field_146999_f) / 2;
        int y = (field_146295_m - field_147000_g) / 2;
        func_73729_b(x, y, 0, 0, field_146999_f, field_147000_g);
    }
}
