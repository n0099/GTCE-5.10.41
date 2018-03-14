package gregtech.common.gui;

import gregtech.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_Boiler
        extends GT_GUIContainerMetaTile_Machine {
    public GT_GUIContainer_Boiler(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, String aTextureName, int aSteamCapacity) {
        super(new GT_Container_Boiler(aInventoryPlayer, aTileEntity, aSteamCapacity), "gregtech:textures/gui/" + aTextureName);
    }

    @Override
    protected void func_146979_b(int par1, int par2) {
        this.field_146289_q.func_78276_b("Boiler", 8, 4, 4210752);
    }

    @Override
    protected void func_146976_a(float par1, int par2, int par3) {
        super.func_146976_a(par1, par2, par3);
        int x = (this.field_146294_l - this.field_146999_f) / 2;
        int y = (this.field_146295_m - this.field_147000_g) / 2;
        func_73729_b(x, y, 0, 0, this.field_146999_f, this.field_147000_g);
        if (this.mContainer != null) {
            int tScale = ((GT_Container_Boiler) this.mContainer).mSteamAmount;
            if (tScale > 0) {
                func_73729_b(x + 70, y + 25 + 54 - tScale, 194, 54 - tScale, 10, tScale);
            }
            tScale = ((GT_Container_Boiler) this.mContainer).mWaterAmount;
            if (tScale > 0) {
                func_73729_b(x + 83, y + 25 + 54 - tScale, 204, 54 - tScale, 10, tScale);
            }
            tScale = ((GT_Container_Boiler) this.mContainer).mTemperature;
            if (tScale > 0) {
                func_73729_b(x + 96, y + 25 + 54 - tScale, 214, 54 - tScale, 10, tScale);
            }
            tScale = ((GT_Container_Boiler) this.mContainer).mProcessingEnergy;
            if (tScale > 0) {
                func_73729_b(x + 117, y + 44 + 14 - tScale, 177, 14 - tScale, 15, tScale + 1);
            }
        }
    }
}
