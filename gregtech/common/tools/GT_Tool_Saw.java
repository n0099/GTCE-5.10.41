package gregtech.common.tools;

import gregtech.api.GregTech_API;
import gregtech.api.enums.Textures;
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
import net.minecraftforge.common.IShearable;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;

public class GT_Tool_Saw extends GT_Tool {

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
        return 200;
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
        return 1.75F;
    }

    @Override
    public float getSpeedMultiplier() {
        return 1.0F;
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
        return GregTech_API.sSoundList.get(0);
    }

    @Override
    public String getMiningSound() {
        return null;
    }

    @Override
    public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, IBlockState aBlock, BlockPos pos, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
        if (aBlock.func_185904_a() == Material.field_151584_j && aBlock.func_177230_c() instanceof IShearable) {
            IShearable shearable = (IShearable) aBlock.func_177230_c();
            if (shearable.isShearable(aStack, aPlayer.field_70170_p, pos)) {
                List<ItemStack> tDrops = shearable.onSheared(aStack, aPlayer.field_70170_p, pos, aFortune);
                aDrops.clear();
                aDrops.addAll(tDrops);
                aEvent.setDropChance(1.0F);
            }
            aPlayer.field_70170_p.func_175698_g(pos);
        } else if ((aBlock.func_185904_a() == Material.field_151588_w ||
                aBlock.func_185904_a() == Material.field_151598_x)
                && aDrops.isEmpty()) {
            aDrops.add(getBlockStack(aBlock));
            aPlayer.field_70170_p.func_175698_g(pos);
            aEvent.setDropChance(1.0F);
            return 1;
        }
        return 0;
    }

    @Override
    public boolean isMinableBlock(IBlockState aBlock) {
        String tTool = aBlock.func_177230_c().getHarvestTool(aBlock);
        return ((tTool != null) && ((tTool.equals("axe")) || (tTool.equals("saw")))) ||
                (aBlock.func_185904_a() == Material.field_151584_j) ||
                (aBlock.func_185904_a() == Material.field_151582_l) ||
                (aBlock.func_185904_a() == Material.field_151575_d) ||
                (aBlock.func_185904_a() == Material.field_151570_A) ||
                (aBlock.func_185904_a() == Material.field_151588_w) ||
                (aBlock.func_185904_a() == Material.field_151598_x);
    }

    @Override
    public ItemStack getBrokenItem(ItemStack aStack) {
        return null;
    }

    @Override
    public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
        return aIsToolHead ? GT_MetaGenerated_Tool.getPrimaryMaterial(aStack).mIconSet.mTextures[gregtech.api.enums.OrePrefixes.toolHeadSaw.mTextureIndex] : Textures.ItemIcons.HANDLE_SAW;
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
        return new TextComponentString(TextFormatting.GREEN + "")
                .func_150257_a(aPlayer.func_145748_c_())
                .func_150258_a(TextFormatting.WHITE + " was getting cut down " + TextFormatting.RED)
                .func_150257_a(aEntity.func_145748_c_());
    }


}
