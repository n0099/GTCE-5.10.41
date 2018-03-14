package gregtech.api.gui;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.enums.GT_Values.RES_PATH_GUI;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * <p/>
 * The GUI-Container I use for all my Basic Machines
 * <p/>
 * As the NEI-RecipeTransferRect Handler can't handle one GUI-Class for all GUIs I needed to produce some dummy-classes which extend this class
 */
public class GT_GUIContainer_BasicMachine extends GT_GUIContainerMetaTile_Machine {

    public final String mName, mNEI;
    public final byte mProgressBarDirection, mProgressBarAmount;

    public GT_GUIContainer_BasicMachine(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, String aName, String aTextureFile, String aNEI) {
        this(aInventoryPlayer, aTileEntity, aName, aTextureFile, aNEI, (byte) 0, (byte) 1);
    }

    public GT_GUIContainer_BasicMachine(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, String aName, String aTextureFile, String aNEI, byte aProgressBarDirection, byte aProgressBarAmount) {
        super(new GT_Container_BasicMachine(aInventoryPlayer, aTileEntity), RES_PATH_GUI + "basicmachines/" + aTextureFile);
        mProgressBarDirection = aProgressBarDirection;
        mProgressBarAmount = (byte) Math.max(1, aProgressBarAmount);
        mName = aName;
        mNEI = aNEI;
    }

    @Override
    protected void func_146979_b(int par1, int par2) {
        field_146289_q.func_78276_b(mName, 8, 4, 4210752);
        drawTooltip(par1, par2);
    }

    private void drawTooltip(int x2, int y2) {
        int xStart = (field_146294_l - field_146999_f) / 2;
        int yStart = (field_146295_m - field_147000_g) / 2;
        int x = x2 - xStart;
        int y = y2 - yStart + 5;
        List<String> list = new ArrayList<String>();
        if (y >= 67 && y <= 84) {
            if (x >= 7 && x <= 24) {
                list.add("Fluid Auto-Output");
            }
            if (x >= 25 && x <= 42) {
                list.add("Item Auto-Output");
            }
        }
        if (!list.isEmpty())
            drawHoveringText(list, x, y, field_146289_q);
    }

    @Override
    protected void func_146976_a(float par1, int par2, int par3) {
        super.func_146976_a(par1, par2, par3);
        int x = (field_146294_l - field_146999_f) / 2;
        int y = (field_146295_m - field_147000_g) / 2;
        func_73729_b(x, y, 0, 0, field_146999_f, field_147000_g);
        if (mContainer != null) {
            if (((GT_Container_BasicMachine) mContainer).mFluidTransfer)
                func_73729_b(x + 7, y + 62, 176, 18, 18, 18);
            if (((GT_Container_BasicMachine) mContainer).mItemTransfer)
                func_73729_b(x + 25, y + 62, 176, 36, 18, 18);
            if (((GT_Container_BasicMachine) mContainer).mStuttering)
                func_73729_b(x + 79, y + 44, 176, 54, 18, 18);

            if (mContainer.mMaxProgressTime > 0) {
                int tSize = (mProgressBarDirection < 2 ? 20 : 18), tProgress = Math.max(1, Math.min(tSize * mProgressBarAmount, (mContainer.mProgressTime > 0 ? 1 : 0) + (mContainer.mProgressTime * tSize * mProgressBarAmount) / mContainer.mMaxProgressTime)) % (tSize + 1);

                switch (mProgressBarDirection) { // yes, my OCD was mad at me before I did the Tabs.
                    case 0:
                        func_73729_b(x + 78, y + 24, 176, 0, tProgress, 18);
                        break;
                    case 1:
                        func_73729_b(x + 78 + 20 - tProgress, y + 24, 176 + 20 - tProgress, 0, tProgress, 18);
                        break;
                    case 2:
                        func_73729_b(x + 78, y + 24, 176, 0, 20, tProgress);
                        break;
                    case 3:
                        func_73729_b(x + 78, y + 24 + 18 - tProgress, 176, 18 - tProgress, 20, tProgress);
                        break;
                    case 4:
                        tProgress = 20 - tProgress;
                        func_73729_b(x + 78, y + 24, 176, 0, tProgress, 18);
                        break;
                    case 5:
                        tProgress = 20 - tProgress;
                        func_73729_b(x + 78 + 20 - tProgress, y + 24, 176 + 20 - tProgress, 0, tProgress, 18);
                        break;
                    case 6:
                        tProgress = 18 - tProgress;
                        func_73729_b(x + 78, y + 24, 176, 0, 20, tProgress);
                        break;
                    case 7:
                        tProgress = 18 - tProgress;
                        func_73729_b(x + 78, y + 24 + 18 - tProgress, 176, 18 - tProgress, 20, tProgress);
                        break;
                }
            }
        }
    }
}
