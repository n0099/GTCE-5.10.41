package gregtech.common.gui;

import gregtech.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_SuperBuffer
        extends GT_GUIContainerMetaTile_Machine {
    public GT_GUIContainer_SuperBuffer(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_SuperBuffer(aInventoryPlayer, aTileEntity), "gregtech:textures/gui/SuperBuffer.png");
    }

    @Override
    protected void func_146976_a(float par1, int par2, int par3) {
        super.func_146976_a(par1, par2, par3);
        int x = (this.field_146294_l - this.field_146999_f) / 2;
        int y = (this.field_146295_m - this.field_147000_g) / 2;
        func_73729_b(x, y, 0, 0, this.field_146999_f, this.field_147000_g);
    }
}
