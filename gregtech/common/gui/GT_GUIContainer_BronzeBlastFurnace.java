package gregtech.common.gui;

import gregtech.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_BronzeBlastFurnace
        extends GT_GUIContainerMetaTile_Machine {
    public GT_GUIContainer_BronzeBlastFurnace(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_BronzeBlastFurnace(aInventoryPlayer, aTileEntity), "gregtech:textures/gui/BronzeBlastFurnace.png");
    }

    @Override
    protected void func_146979_b(int par1, int par2) {
        this.field_146289_q.func_78276_b("Bronze Blast Furnace", 8, 4, 4210752);
    }

    @Override
    protected void func_146976_a(float par1, int par2, int par3) {
        super.func_146976_a(par1, par2, par3);
        int x = (this.field_146294_l - this.field_146999_f) / 2;
        int y = (this.field_146295_m - this.field_147000_g) / 2;
        func_73729_b(x, y, 0, 0, this.field_146999_f, this.field_147000_g);
        if ((this.mContainer != null) &&
                (this.mContainer.mProgressTime > 0)) {
            func_73729_b(x + 58, y + 28, 176, 0, Math.max(0, Math.min(20, (1) + this.mContainer.mProgressTime * 20 / (this.mContainer.mMaxProgressTime < 1 ? 1 : this.mContainer.mMaxProgressTime))), 11);
        }
    }
}
