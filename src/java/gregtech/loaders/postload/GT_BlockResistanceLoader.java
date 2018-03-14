package gregtech.loaders.postload;

import gregtech.GT_Mod;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;

import java.util.Set;

public class GT_BlockResistanceLoader
        implements Runnable {
    public void run() {
        Blocks.field_150348_b.func_149752_b(10.0F);
        Blocks.field_150347_e.func_149752_b(10.0F);
        Blocks.field_150417_aV.func_149752_b(10.0F);
        Blocks.field_150336_V.func_149752_b(20.0F);
        Blocks.field_150405_ch.func_149752_b(15.0F);
        Blocks.field_150406_ce.func_149752_b(15.0F);

        if (GT_Mod.gregtechproxy.mHardRock) {
            Blocks.field_150348_b.func_149711_c(16.0F);
            Blocks.field_150336_V.func_149711_c(32.0F);
            Blocks.field_150405_ch.func_149711_c(32.0F);
            Blocks.field_150406_ce.func_149711_c(32.0F);
            Blocks.field_150347_e.func_149711_c(12.0F);
            Blocks.field_150417_aV.func_149711_c(24.0F);
        }

        Blocks.field_150324_C.setHarvestLevel("axe", 0);
        Blocks.field_150407_cf.setHarvestLevel("axe", 0);
        Blocks.field_150335_W.setHarvestLevel("pickaxe", 0);
        Blocks.field_150360_v.setHarvestLevel("axe", 0);
        Blocks.field_150418_aU.setHarvestLevel("pickaxe", 0);

    }
}
