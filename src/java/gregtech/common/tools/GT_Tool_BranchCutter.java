package gregtech.common.tools;

import gregtech.api.enums.Textures;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.items.GT_MetaGenerated_Tool;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Utility;
import ic2.core.block.Ic2Leaves;
import ic2.core.ref.BlockName;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;

public class GT_Tool_BranchCutter extends GT_Tool {

    @Override
    public float getBaseDamage() {
        return 2.5F;
    }

    @Override
    public float getSpeedMultiplier() {
        return 0.25F;
    }

    @Override
    public float getMaxDurabilityMultiplier() {
        return 0.25F;
    }

    @Override
    public boolean isGrafter() {
        return true;
    }

    @Override
    public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, IBlockState aBlock, BlockPos blockPos, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
        if (aBlock.func_185904_a() == Material.field_151584_j) {
            aEvent.setDropChance(Math.min(1.0F, Math.max(aEvent.getDropChance(), (aStack.func_77973_b().getHarvestLevel(aStack, "") + 1) * 0.2F)));
            if (aBlock.func_177230_c() == Blocks.field_150362_t) {
                aDrops.clear();
                if ((aBlock.func_177229_b(BlockOldLeaf.field_176239_P) == BlockPlanks.EnumType.OAK &&
                        aPlayer.field_70170_p.field_73012_v.nextInt(9) <= aFortune * 2)) {
                    aDrops.add(new ItemStack(Items.field_151034_e, 1, 0));
                } else {
                    aDrops.add(new ItemStack(Blocks.field_150345_g, 1, aBlock
                            .func_177229_b(BlockOldLeaf.field_176239_P).func_176839_a()));
                }
            } else if (aBlock == Blocks.field_150361_u) {
                aDrops.clear();
                aDrops.add(new ItemStack(Blocks.field_150345_g, 1, aBlock
                        .func_177229_b(BlockNewLeaf.field_176240_P).func_176839_a()));
            } else if (aBlock == GT_Utility.getBlockFromStack(GT_ModHandler.getIC2Item(BlockName.leaves, 1))) {
                aDrops.clear();
                aDrops.add(GT_ModHandler.getIC2Item(BlockName.sapling, 1));
            }
        }
        return 0;
    }

    @Override
    public boolean isMinableBlock(IBlockState aBlock) {
        String tTool = aBlock.func_177230_c().getHarvestTool(aBlock);
        return ((tTool != null) && (tTool.equals("grafter"))) || (aBlock.func_185904_a() == Material.field_151584_j);
    }

    @Override
    public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
        return aIsToolHead ? Textures.ItemIcons.GRAFTER : null;
    }

    @Override
    public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
        return aIsToolHead ? GT_MetaGenerated_Tool.getPrimaryMaterial(aStack).mRGBa : GT_MetaGenerated_Tool.getSecondaryMaterial(aStack).mRGBa;
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase aPlayer, EntityLivingBase aEntity) {
        return new TextComponentString(TextFormatting.RED + "")
                .func_150257_a(aEntity.func_145748_c_())
                .func_150258_a(TextFormatting.WHITE + " has been trimmed by " + TextFormatting.GREEN)
                .func_150257_a(aPlayer.func_145748_c_());
    }

}
