package gregtech.common.gui;

import gregtech.api.gui.GT_Container_MultiMachine;
import gregtech.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Utility;
import net.minecraft.entity.player.InventoryPlayer;

import static gregtech.api.enums.GT_Values.RES_PATH_GUI;

public class GT_GUIContainer_FusionReactor extends GT_GUIContainerMetaTile_Machine {

    public String mNEI;
    String mName = "";

    public GT_GUIContainer_FusionReactor(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, String aName, String aTextureFile, String aNEI) {
        super(new GT_Container_MultiMachine(aInventoryPlayer, aTileEntity, false), RES_PATH_GUI + "multimachines/" + (aTextureFile == null ? "MultiblockDisplay" : aTextureFile));
        mName = aName;
        mNEI = aNEI;
    }

    @Override
    protected void func_146979_b(int par1, int par2) {
        field_146289_q.func_78276_b(mName, 8, -10, 16448255);

        if (mContainer != null) {
            if ((((GT_Container_MultiMachine) mContainer).mDisplayErrorCode & 64) != 0)
                field_146289_q.func_78276_b("Incomplete Structure.", 10, 8, 16448255);

            if (((GT_Container_MultiMachine) mContainer).mDisplayErrorCode == 0) {
                if (((GT_Container_MultiMachine) mContainer).mActive == 0) {
                    field_146289_q.func_78276_b("Hit with Soft Hammer to (re-)start the Machine if it doesn't start.", -70, 170, 16448255);
                } else {
                    field_146289_q.func_78276_b("Running perfectly.", 10, 170, 16448255);
                }
            }
            field_146289_q.func_78276_b(GT_Utility.formatNumbers(this.mContainer.mEnergy) + " EU", 50, 155, 0x00ff0000);
        }
    }

    @Override
    protected void func_146976_a(float par1, int par2, int par3) {
        super.func_146976_a(par1, par2, par3);
        int x = (field_146294_l - field_146999_f) / 2;
        int y = (field_146295_m - field_147000_g) / 2;
        func_73729_b(x, y, 0, 0, field_146999_f, field_147000_g);
        if (this.mContainer != null) {
            double tScale = (double) this.mContainer.mEnergy / (double) this.mContainer.mStorage;
            func_73729_b(x + 5, y + 156, 0, 251, Math.min(147, (int) (tScale * 148)), 5);
        }
    }
}
