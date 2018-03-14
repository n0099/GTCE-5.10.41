package gregtech.common.blocks;

import com.google.common.collect.ImmutableList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_OreDictUnificator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class GT_Block_Concretes extends GT_Block_Stones_Abstract {

    public GT_Block_Concretes() {
        super(GT_Item_Concretes.class, "gt.blockconcretes");
        func_149752_b(20.0F);
        this.field_149765_K = 0.9F;
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".0.name", "Dark Concrete");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".1.name", "Dark Concrete Cobblestone");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".2.name", "Mossy Dark Concrete Cobblestone");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".3.name", "Dark Concrete Bricks");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".4.name", "Cracked Dark Concrete Bricks");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".5.name", "Mossy Dark Concrete Bricks");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".6.name", "Chiseled Dark Concrete");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".7.name", "Smooth Dark Concrete");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".8.name", "Light Concrete");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".9.name", "Light Concrete Cobblestone");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".10.name", "Mossy Light Concrete Cobblestone");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".11.name", "Light Concrete Bricks");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".12.name", "Cracked Light Concrete Bricks");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".13.name", "Mossy Light Concrete Bricks");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".14.name", "Chiseled Light Concrete");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".15.name", "Smooth Light Concrete");
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Concrete, new ItemStack(this, 1, 0));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Concrete, new ItemStack(this, 1, 1));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Concrete, new ItemStack(this, 1, 2));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Concrete, new ItemStack(this, 1, 3));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Concrete, new ItemStack(this, 1, 4));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Concrete, new ItemStack(this, 1, 5));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Concrete, new ItemStack(this, 1, 6));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Concrete, new ItemStack(this, 1, 7));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Concrete, new ItemStack(this, 1, 8));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Concrete, new ItemStack(this, 1, 9));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Concrete, new ItemStack(this, 1, 10));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Concrete, new ItemStack(this, 1, 11));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Concrete, new ItemStack(this, 1, 12));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Concrete, new ItemStack(this, 1, 13));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Concrete, new ItemStack(this, 1, 14));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Concrete, new ItemStack(this, 1, 15));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getIcon(EnumFacing aSide, int aMeta) {
        if ((aMeta >= 0) && (aMeta < 16)) {
            return gregtech.api.enums.Textures.BlockIcons.CONCRETES[aMeta].getIcon();
        }
        return gregtech.api.enums.Textures.BlockIcons.CONCRETES[0].getIcon();
    }

    @Override
    public void func_180634_a(World worldIn, BlockPos pos, IBlockState state, Entity aEntity) {
        if (aEntity.field_70122_E && !aEntity.func_70090_H()) {
            if (aEntity.func_70093_af()) {
                aEntity.field_70159_w *= 0.8999999761581421D;
                aEntity.field_70179_y *= 0.8999999761581421D;
            } else {
                if (aEntity.field_70159_w < 6.0 && aEntity.field_70179_y < 6.0) {
                    aEntity.field_70159_w *= 1.100000023841858D;
                    aEntity.field_70179_y *= 1.100000023841858D;
                }
            }
        }
    }

    @Nullable
    @Override
    public AxisAlignedBB func_180646_a(IBlockState blockState, World aWorld, BlockPos pos) {
        Block tBlock = aWorld.func_180495_p(pos.func_177984_a()).func_177230_c();
        if (tBlock instanceof IFluidBlock || tBlock instanceof BlockLiquid) {
            return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        }
        return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);
    }

}
