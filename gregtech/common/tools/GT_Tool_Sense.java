package gregtech.common.tools;

import gregtech.api.interfaces.IIconContainer;
import gregtech.api.items.GT_MetaGenerated_Tool;
import gregtech.common.items.behaviors.Behaviour_Sense;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;

public class GT_Tool_Sense extends GT_Tool {

    private ThreadLocal<Object> sIsHarvestingRightNow = new ThreadLocal();

    @Override
    public float getBaseDamage() {
        return 3.0F;
    }

    @Override
    public float getMaxDurabilityMultiplier() {
        return 4.0F;
    }

    @Override
    public boolean isMinableBlock(IBlockState aBlock) {
        String tTool = aBlock.func_177230_c().getHarvestTool(aBlock);
        return (tTool != null && (tTool.equals("sense") || tTool.equals("scythe"))) ||
                aBlock.func_185904_a() == Material.field_151585_k ||
                aBlock.func_185904_a() == Material.field_151584_j;
    }

    @Override
    public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, IBlockState aBlock, BlockPos pos, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
        int rConversions = 0;
        if (this.sIsHarvestingRightNow.get() == null && !aPlayer.field_70170_p.field_72995_K) {
            this.sIsHarvestingRightNow.set(this);
            for (int i = -2; i < 3; i++) {
                for (int j = -2; j < 3; j++) {
                    for (int k = -2; k < 3; k++) {
                        BlockPos block = pos.func_177982_a(i, j, k);
                        IBlockState blockState = aPlayer.field_70170_p.func_180495_p(block);
                        if ((i != 0 || j != 0 || k != 0) && aStack.func_150997_a(blockState) > 0.0F &&
                                ((EntityPlayerMP) aPlayer).field_71134_c.func_180237_b(block))
                            rConversions++;
                    }
                }
            }
        }
        return rConversions;
    }


    @Override
    public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
        return aIsToolHead ? GT_MetaGenerated_Tool.getPrimaryMaterial(aStack).mIconSet.mTextures[gregtech.api.enums.OrePrefixes.toolHeadSense.mTextureIndex] : GT_MetaGenerated_Tool.getSecondaryMaterial(aStack).mIconSet.mTextures[gregtech.api.enums.OrePrefixes.stick.mTextureIndex];
    }

    @Override
    public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
        return aIsToolHead ? GT_MetaGenerated_Tool.getPrimaryMaterial(aStack).mRGBa : GT_MetaGenerated_Tool.getSecondaryMaterial(aStack).mRGBa;
    }

    @Override
    public void onStatsAddedToTool(GT_MetaGenerated_Tool aItem, int aID) {
        aItem.addItemBehavior(aID, new Behaviour_Sense(getToolDamagePerBlockBreak()));
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase aPlayer, EntityLivingBase aEntity) {
        return new TextComponentString(TextFormatting.GREEN + "")
                .func_150257_a(aPlayer.func_145748_c_())
                .func_150258_a(TextFormatting.WHITE + " has taken the Soul of " + TextFormatting.RED)
                .func_150257_a(aEntity.func_145748_c_());
    }

}
