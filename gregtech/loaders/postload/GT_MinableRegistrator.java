package gregtech.loaders.postload;

import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_ModHandler;
import net.minecraft.init.Blocks;

public class GT_MinableRegistrator
        implements Runnable {
    public void run() {
        GT_Log.out.println("GT_Mod: Adding Blocks to the Miners Valuable List.");
        GT_ModHandler.addValuableOre(Blocks.field_150426_aN, 0, 1);
        GT_ModHandler.addValuableOre(Blocks.field_150425_aM, 0, 1);
    }
}
