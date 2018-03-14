package gregtech.api.gui;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Utility;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;

import static gregtech.api.enums.GT_Values.RES_PATH_GUI;

public class GT_GUIContainer_BasicTank extends GT_GUIContainerMetaTile_Machine {

    private final String mName;

    public GT_GUIContainer_BasicTank(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, String aName) {
        super(new GT_Container_BasicTank(aInventoryPlayer, aTileEntity), RES_PATH_GUI + "BasicTank.png");
        mName = aName;
    }

    @Override
    protected void func_146979_b(int par1, int par2) {
        field_146289_q.func_78276_b(I18n.func_135052_a("container.inventory"), 8, field_147000_g - 96 + 2, 4210752);
        field_146289_q.func_78276_b(mName, 8, 6, 4210752);
        if (mContainer != null) {
            field_146289_q.func_78276_b("Liquid Amount", 10, 20, 16448255);
            field_146289_q.func_78276_b(GT_Utility.parseNumberToString(((GT_Container_BasicTank) mContainer).mContent), 10, 30, 16448255);
        }
    }

    @Override
    protected void func_146976_a(float par1, int par2, int par3) {
        super.func_146976_a(par1, par2, par3);
        int x = (field_146294_l - field_146999_f) / 2;
        int y = (field_146295_m - field_147000_g) / 2;
        func_73729_b(x, y, 0, 0, field_146999_f, field_147000_g);
    }
}
