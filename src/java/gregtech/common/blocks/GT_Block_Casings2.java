package gregtech.common.blocks;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.util.GT_LanguageManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GT_Block_Casings2 extends GT_Block_Casings_Abstract {

    public GT_Block_Casings2() {
        super(GT_Item_Casings2.class, "gt.blockcasings2", GT_Material_Casings.INSTANCE);
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".0.name", "Solid Steel Machine Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".1.name", "Frost Proof Machine Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".2.name", "Bronze Gear Box Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".3.name", "Steel Gear Box Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".4.name", "Titanium Gear Box Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".5.name", "Assembling Line Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".6.name", "Processor Machine Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".7.name", "Data Drive Machine Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".8.name", "Containment Field Machine Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".9.name", "Assembler Machine Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".10.name", "Pump Machine Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".11.name", "Motor Machine Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".12.name", "Bronze Pipe Machine Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".13.name", "Steel Pipe Machine Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".14.name", "Titanium Pipe Machine Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".15.name", "Tungstensteel Pipe Machine Casing");
        ItemList.Casing_SolidSteel.set(new ItemStack(this, 1, 0));
        ItemList.Casing_FrostProof.set(new ItemStack(this, 1, 1));
        ItemList.Casing_Gearbox_Bronze.set(new ItemStack(this, 1, 2));
        ItemList.Casing_Gearbox_Steel.set(new ItemStack(this, 1, 3));
        ItemList.Casing_Gearbox_Titanium.set(new ItemStack(this, 1, 4));
        ItemList.Casing_Gearbox_TungstenSteel.set(new ItemStack(this, 1, 5));
        ItemList.Casing_Processor.set(new ItemStack(this, 1, 6));
        ItemList.Casing_DataDrive.set(new ItemStack(this, 1, 7));
        ItemList.Casing_ContainmentField.set(new ItemStack(this, 1, 8));
        ItemList.Casing_Assembler.set(new ItemStack(this, 1, 9));
        ItemList.Casing_Pump.set(new ItemStack(this, 1, 10));
        ItemList.Casing_Motor.set(new ItemStack(this, 1, 11));
        ItemList.Casing_Pipe_Bronze.set(new ItemStack(this, 1, 12));
        ItemList.Casing_Pipe_Steel.set(new ItemStack(this, 1, 13));
        ItemList.Casing_Pipe_Titanium.set(new ItemStack(this, 1, 14));
        ItemList.Casing_Pipe_TungstenSteel.set(new ItemStack(this, 1, 15));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getIcon(EnumFacing aSide, int aMeta) {
        return getIconContainer(aSide, aMeta).getIcon();
    }

    public static IIconContainer getIconContainer(EnumFacing aSide, int aMeta) {
        switch (aMeta) {
            case 0:
                return Textures.BlockIcons.MACHINE_CASING_SOLID_STEEL;
            case 1:
                return Textures.BlockIcons.MACHINE_CASING_FROST_PROOF;
            case 2:
                return Textures.BlockIcons.MACHINE_CASING_GEARBOX_BRONZE;
            case 3:
                return Textures.BlockIcons.MACHINE_CASING_GEARBOX_STEEL;
            case 4:
                return Textures.BlockIcons.MACHINE_CASING_GEARBOX_TITANIUM;
            case 5:
                return Textures.BlockIcons.MACHINE_CASING_GEARBOX_TUNGSTENSTEEL;
            case 6:
                return Textures.BlockIcons.MACHINE_CASING_PROCESSOR;
            case 7:
                return Textures.BlockIcons.MACHINE_CASING_DATA_DRIVE;
            case 8:
                return Textures.BlockIcons.MACHINE_CASING_CONTAINMENT_FIELD;
            case 9:
                return Textures.BlockIcons.MACHINE_CASING_ASSEMBLER;
            case 10:
                return Textures.BlockIcons.MACHINE_CASING_PUMP;
            case 11:
                return Textures.BlockIcons.MACHINE_CASING_MOTOR;
            case 12:
                return Textures.BlockIcons.MACHINE_CASING_PIPE_BRONZE;
            case 13:
                return Textures.BlockIcons.MACHINE_CASING_PIPE_STEEL;
            case 14:
                return Textures.BlockIcons.MACHINE_CASING_PIPE_TITANIUM;
            case 15:
                return Textures.BlockIcons.MACHINE_CASING_PIPE_TUNGSTENSTEEL;
        }
        return Textures.BlockIcons.MACHINE_CASING_SOLID_STEEL;
    }

    @Override
    public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion) {
        return world.func_180495_p(pos).func_177229_b(METADATA) == 8 ?
                Blocks.field_150357_h.getExplosionResistance(world, pos, exploder, explosion) :
                super.getExplosionResistance(world, pos, exploder, explosion);
    }

}
