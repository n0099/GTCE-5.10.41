package gregtech.common.tools;

import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.items.GT_MetaGenerated_Tool;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;
import java.util.Random;

public class GT_Tool_Chainsaw_LV extends GT_Tool_Saw {

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
        return 800;
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
        return GregTech_API.sSoundList.get(104);
    }

    @Override
    public String getEntityHitSound() {
        return GregTech_API.sSoundList.get(105);
    }

    @Override
    public String getBreakingSound() {
        return GregTech_API.sSoundList.get(0);
    }

    @Override
    public String getMiningSound() {
        return GregTech_API.sSoundList.get(104);
    }

    @Override
    public boolean canBlock() {
        return false;
    }

    @Override
    public boolean isChainsaw(){
    	return true;
    }
    
    @Override
    public boolean isWeapon() {
        return true;
    }

    @Override
    public void onToolCrafted(ItemStack aStack, EntityPlayer aPlayer) {
        super.onToolCrafted(aStack, aPlayer);
        GT_Mod.achievements.issueAchievement(aPlayer, "brrrr");
        GT_Mod.achievements.issueAchievement(aPlayer, "buildChainsaw");
    }
    
    @Override
    public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, IBlockState aBlock, BlockPos pos, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
        int rAmount = 0;
        if ((aBlock.func_185904_a() == Material.field_151584_j) && aBlock.func_177230_c() instanceof IShearable) {
            IShearable shearable = (IShearable) aBlock.func_177230_c();
            if (shearable.isShearable(aStack, aPlayer.field_70170_p, pos)) {
                List<ItemStack> tDrops = shearable.onSheared(aStack, aPlayer.field_70170_p, pos, aFortune);
                aDrops.clear();
                aDrops.addAll(tDrops);
                aEvent.setDropChance(1.0F);
                for (ItemStack stack : tDrops) {
                    Random itemRand = new Random();
                    float f = 0.7F;
                    double d = itemRand.nextFloat() * f + (1.0F - f) * 0.5D;
                    double d1 = itemRand.nextFloat() * f + (1.0F - f) * 0.5D;
                    double d2 = itemRand.nextFloat() * f + (1.0F - f) * 0.5D;
                    EntityItem entityitem = new EntityItem(aPlayer.field_70170_p,
                            pos.func_177958_n() + d,
                            pos.func_177956_o() + d1,
                            pos.func_177952_p() + d2, stack);
                    entityitem.func_174869_p();
                    aPlayer.field_70170_p.func_72838_d(entityitem);
                }
                aPlayer.func_71064_a(StatList.field_188096_e.get(Block.func_149682_b(aBlock.func_177230_c())), 1);
            }
            aPlayer.field_70170_p.func_175698_g(pos);
        } else 
        	if ((aBlock.func_185904_a() == Material.field_151588_w ||
                    aBlock.func_185904_a() == Material.field_151598_x) &&
                    aDrops.isEmpty()) {
            aDrops.add(getBlockStack(aBlock));
            aPlayer.field_70170_p.func_175698_g(pos);
            aEvent.setDropChance(1.0F);
            return 1;
        }
        if (GregTech_API.sTimber && !aPlayer.func_70093_af() &&
                OrePrefixes.log.contains(getBlockStack(aBlock))) {
            for (int y = 0; y < aPlayer.field_70170_p.func_72800_K() - pos.func_177984_a().func_177956_o(); y++) {
                BlockPos block = pos.func_177981_b(y);
                if (!isStateEqual(aPlayer.field_70170_p.func_180495_p(block), aBlock) ||
                        !aPlayer.field_70170_p.func_175655_b(block, true)) break;
                rAmount++;
            }
        }
        return rAmount;
    }

    @Override
    public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
        return aIsToolHead ? GT_MetaGenerated_Tool.getPrimaryMaterial(aStack).mIconSet.mTextures[gregtech.api.enums.OrePrefixes.toolHeadChainsaw.mTextureIndex] : Textures.ItemIcons.POWER_UNIT_LV;
    }

    @Override
    public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
        return aIsToolHead ? GT_MetaGenerated_Tool.getPrimaryMaterial(aStack).mRGBa : GT_MetaGenerated_Tool.getSecondaryMaterial(aStack).mRGBa;
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase aPlayer, EntityLivingBase aEntity) {
        return new TextComponentString(TextFormatting.GREEN + "")
                .func_150257_a(aPlayer.func_145748_c_())
                .func_150258_a(TextFormatting.WHITE + " was massacred " + TextFormatting.RED)
                .func_150257_a(aEntity.func_145748_c_());
    }
    
}
