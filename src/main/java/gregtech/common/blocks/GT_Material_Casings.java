package gregtech.common.blocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class GT_Material_Casings
        extends Material {
    public static final Material INSTANCE = new GT_Material_Casings();

    private GT_Material_Casings() {
        super(MapColor.field_151668_h);
        func_76221_f();
    }

    public boolean func_76218_k() {
        return false;
    }
}
