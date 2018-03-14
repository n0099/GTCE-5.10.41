package gregtech.common.tools;

import gregtech.api.GregTech_API;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.items.GT_MetaGenerated_Tool;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;

public class GT_Tool_Axe extends GT_Tool {

    @Override
    public int getToolDamagePerBlockBreak() {
        return 50;
    }

    @Override
    public int getToolDamagePerDropConversion() {
        return 100;
    }

    @Override
    public int getToolDamagePerContainerCraft() {
        return 100;
    }

    @Override
    public int getToolDamagePerEntityAttack() {
        return 200;
    }

    @Override
    public int getBaseQuality() {
        return 0;
    }

    @Override
    public float getBaseDamage() {
        return 3.0F;
    }

    @Override
    public float getSpeedMultiplier() {
        return 2.0F;
    }

    @Override
    public float getMaxDurabilityMultiplier() {
        return 1.0F;
    }

    @Override
    public String getCraftingSound() {
        return null;
    }

    @Override
    public String getEntityHitSound() {
        return null;
    }

    @Override
    public String getBreakingSound() {
        return (String) GregTech_API.sSoundList.get(Integer.valueOf(0));
    }

    @Override
    public String getMiningSound() {
        return null;
    }

    @Override
    public boolean canBlock() {
        return false;
    }

    @Override
    public boolean isCrowbar() {
        return false;
    }

    @Override
    public boolean isWeapon() {
        return true;
    }

    @Override
    public boolean isMinableBlock(IBlockState aBlock) {
        String tTool = aBlock.func_177230_c().getHarvestTool(aBlock);
        return "axe".equals(tTool) || (aBlock.func_185904_a() == Material.field_151575_d);
    }

    @Override
    public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, IBlockState aBlock, BlockPos pos, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
        int rAmount = 0;
        if (GregTech_API.sTimber && !aPlayer.func_70093_af() && OrePrefixes.log.contains(getBlockStack(aBlock))) {
            int tY = pos.func_177956_o() + 1;
            for (int tH = aPlayer.field_70170_p.func_72800_K(); tY < tH; tY++) {
                BlockPos block = new BlockPos(pos.func_177958_n(), tY, pos.func_177952_p());
                if (!isStateEqual(aPlayer.field_70170_p.func_180495_p(block), aBlock) ||
                        !aPlayer.field_70170_p.func_175655_b(block, true)) break;
                rAmount++;
            }
        }
        return rAmount;
    }

    @Override
    public ItemStack getBrokenItem(ItemStack aStack) {
        return null;
    }

    @Override
    public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
        return aIsToolHead ? GT_MetaGenerated_Tool.getPrimaryMaterial(aStack).mIconSet.mTextures[OrePrefixes.toolHeadAxe.mTextureIndex] : GT_MetaGenerated_Tool.getSecondaryMaterial(aStack).mIconSet.mTextures[OrePrefixes.stick.mTextureIndex];
    }

    @Override
    public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
        return aIsToolHead ? GT_MetaGenerated_Tool.getPrimaryMaterial(aStack).mRGBa : GT_MetaGenerated_Tool.getSecondaryMaterial(aStack).mRGBa;
    }

    @Override
    public void onStatsAddedToTool(GT_MetaGenerated_Tool aItem, int aID) {
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase aPlayer, EntityLivingBase aEntity) {
        return new TextComponentString(TextFormatting.RED + "")
                .func_150257_a(aEntity.func_145748_c_())
                .func_150258_a(TextFormatting.WHITE + " has been chopped by " + TextFormatting.GREEN)
                .func_150257_a(aPlayer.func_145748_c_());
    }

}
