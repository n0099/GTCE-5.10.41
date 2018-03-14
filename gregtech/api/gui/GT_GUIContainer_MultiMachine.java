package gregtech.api.gui;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Utility;
import gregtech.common.items.GT_MetaGenerated_Tool_01;
import ic2.core.block.machine.BlockMiningPipe;
import ic2.core.ref.BlockName;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

import static gregtech.api.enums.GT_Values.RES_PATH_GUI;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * <p/>
 * The GUI-Container I use for all my Basic Machines
 * <p/>
 * As the NEI-RecipeTransferRect Handler can't handle one GUI-Class for all GUIs I needed to produce some dummy-classes which extend this class
 */
public class GT_GUIContainer_MultiMachine extends GT_GUIContainerMetaTile_Machine {

    String mName = "";

    public GT_GUIContainer_MultiMachine(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, String aName, String aTextureFile) {
        super(new GT_Container_MultiMachine(aInventoryPlayer, aTileEntity), RES_PATH_GUI + "multimachines/" + (aTextureFile == null ? "MultiblockDisplay" : aTextureFile));
        mName = aName;
    }

    @Override
    protected void func_146979_b(int par1, int par2) {
        field_146289_q.func_78276_b(mName, 10, 8, 16448255);

        if (mContainer != null) {
            if ((((GT_Container_MultiMachine) mContainer).mDisplayErrorCode & 1) != 0)
                field_146289_q.func_78276_b("Pipe is loose.", 10, 16, 16448255);
            if ((((GT_Container_MultiMachine) mContainer).mDisplayErrorCode & 2) != 0)
                field_146289_q.func_78276_b("Screws are missing.", 10, 24, 16448255);
            if ((((GT_Container_MultiMachine) mContainer).mDisplayErrorCode & 4) != 0)
                field_146289_q.func_78276_b("Something is stuck.", 10, 32, 16448255);
            if ((((GT_Container_MultiMachine) mContainer).mDisplayErrorCode & 8) != 0)
                field_146289_q.func_78276_b("Platings are dented.", 10, 40, 16448255);
            if ((((GT_Container_MultiMachine) mContainer).mDisplayErrorCode & 16) != 0)
                field_146289_q.func_78276_b("Circuitry burned out.", 10, 48, 16448255);
            if ((((GT_Container_MultiMachine) mContainer).mDisplayErrorCode & 32) != 0)
                field_146289_q.func_78276_b("That doesn't belong there.", 10, 56, 16448255);
            if ((((GT_Container_MultiMachine) mContainer).mDisplayErrorCode & 64) != 0)
                field_146289_q.func_78276_b("Incomplete Structure.", 10, 64, 16448255);

            if (((GT_Container_MultiMachine) mContainer).mDisplayErrorCode == 0) {
                if (((GT_Container_MultiMachine) mContainer).mActive == 0) {
                    field_146289_q.func_78276_b("Hit with Soft Hammer", 10, 16, 16448255);
                    field_146289_q.func_78276_b("to (re-)start the Machine", 10, 24, 16448255);
                    field_146289_q.func_78276_b("if it doesn't start.", 10, 32, 16448255);
                } else {
                    field_146289_q.func_78276_b("Running perfectly.", 10, 16, 16448255);
                }
                	int id = mContainer.mTileEntity.getMetaTileID();
                   if(id == 1157 || id == 1158){
                    	ItemStack tItem = mContainer.mTileEntity.getMetaTileEntity().func_70301_a(1);
                    	if(tItem==null || !GT_Utility.areStacksEqual(tItem, GT_ModHandler.getIC2Item(BlockName.mining_pipe, BlockMiningPipe.MiningPipeType.pipe, 1))){
                    		field_146289_q.func_78276_b("Missing Mining Pipe", 10,((GT_Container_MultiMachine) mContainer).mActive == 0 ? 40 : 24, 16448255);
                    	}
                    }else if(id == 1131 || id == 1151 || id == 1152 || id == 1153){
                    	ItemStack tItem = mContainer.mTileEntity.getMetaTileEntity().func_70301_a(1);
                    	if(tItem==null || !(tItem.func_77973_b()==GT_MetaGenerated_Tool_01.INSTANCE&&tItem.func_77952_i()>=170&&tItem.func_77952_i()<=177)){
                    		field_146289_q.func_78276_b("Missing Turbine Rotor", 10, ((GT_Container_MultiMachine) mContainer).mActive == 0 ? 40 : 24, 16448255);
                    	}
                    }                
            }
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
