package gregtech.common.gui;

import gregtech.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Utility;
import net.minecraft.entity.player.InventoryPlayer;

import static gregtech.api.enums.GT_Values.RES_PATH_GUI;

public class GT_GUIContainer_Teleporter
        extends GT_GUIContainerMetaTile_Machine {
    public GT_GUIContainer_Teleporter(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_Teleporter(aInventoryPlayer, aTileEntity), RES_PATH_GUI + "Teleporter.png");
    }

    @Override
    protected void func_146979_b(int par1, int par2) {
        this.field_146289_q.func_78276_b("Teleporter", 46, 8, 16448255);
        if (this.mContainer != null) {
            this.field_146289_q.func_78276_b("X: " + GT_Utility.parseNumberToString(((GT_Container_Teleporter) this.mContainer).mTargetX), 46, 16, 16448255);
            this.field_146289_q.func_78276_b("Y: " + GT_Utility.parseNumberToString(((GT_Container_Teleporter) this.mContainer).mTargetY), 46, 24, 16448255);
            this.field_146289_q.func_78276_b("Z: " + GT_Utility.parseNumberToString(((GT_Container_Teleporter) this.mContainer).mTargetZ), 46, 32, 16448255);
            if (((GT_Container_Teleporter) this.mContainer).mEgg > 0) {
                this.field_146289_q.func_78276_b("Dim: " + GT_Utility.parseNumberToString(((GT_Container_Teleporter) this.mContainer).mTargetD), 46, 40, 16448255);
            }
        }
    }

    @Override
    protected void func_146976_a(float par1, int par2, int par3) {
        super.func_146976_a(par1, par2, par3);
        int x = (this.field_146294_l - this.field_146999_f) / 2;
        int y = (this.field_146295_m - this.field_147000_g) / 2;
        func_73729_b(x, y, 0, 0, this.field_146999_f, this.field_147000_g);
    }
}
