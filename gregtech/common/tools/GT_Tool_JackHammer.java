package gregtech.common.tools;

import gregtech.GT_Mod;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.Block;
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

public class GT_Tool_JackHammer extends GT_Tool_Drill_LV {

    @Override
    public int getToolDamagePerBlockBreak() {
        return GT_Mod.gregtechproxy.mHardRock ? 200 : 400;
    }

    @Override
    public int getToolDamagePerDropConversion() {
        return 400;
    }

    @Override
    public int getToolDamagePerContainerCraft() {
        return 3200;
    }

    @Override
    public int getToolDamagePerEntityAttack() {
        return 800;
    }

    @Override
    public int getBaseQuality() {
        return 1;
    }

    @Override
    public float getBaseDamage() {
        return 3.0F;
    }

    @Override
    public float getSpeedMultiplier() {
        return 12.0F;
    }

    @Override
    public float getMaxDurabilityMultiplier() {
        return 2.0F;
    }

    @Override
    public boolean isMinableBlock(IBlockState aBlock) {
        String tTool = aBlock.func_177230_c().getHarvestTool(aBlock);
        return ((tTool != null) && (tTool.equals("pickaxe"))) ||
                (aBlock.func_185904_a() == Material.field_151576_e) ||
                (aBlock.func_185904_a() == Material.field_151592_s) ||
                (aBlock.func_185904_a() == Material.field_151588_w) ||
                (aBlock.func_185904_a() == Material.field_151598_x);
    }

    @Override
    public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, IBlockState aBlock, BlockPos pos, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
        int rConversions = 0;
        GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sHammerRecipes.findRecipe(null, true, 2147483647L, null, getBlockStack(aBlock));
        if ((tRecipe == null) || (aBlock.func_177230_c().hasTileEntity(aBlock))) {
            for (ItemStack tDrop : aDrops) {
                tRecipe = GT_Recipe.GT_Recipe_Map.sHammerRecipes.findRecipe(null, true, 2147483647L, null, GT_Utility.copyAmount(1L, tDrop));
                if (tRecipe != null) {
                    ItemStack tHammeringOutput = tRecipe.getOutput(0);
                    if (tHammeringOutput != null) {
                        rConversions += tDrop.field_77994_a;
                        tDrop.field_77994_a *= tHammeringOutput.field_77994_a;
                        tHammeringOutput.field_77994_a = tDrop.field_77994_a;
                        GT_Utility.setStack(tDrop, tHammeringOutput);
                    }
                }
            }
        } else {
            aDrops.clear();
            aDrops.add(tRecipe.getOutput(0));
            rConversions++;
        }
        return rConversions;
    }

    @Override
    public void onToolCrafted(ItemStack aStack, EntityPlayer aPlayer) {
        super.onToolCrafted(aStack, aPlayer);
        GT_Mod.achievements.issueAchievement(aPlayer, "hammertime");

    }


    @Override
    public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
        return aIsToolHead ? Textures.ItemIcons.JACKHAMMER : null;
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase aPlayer, EntityLivingBase aEntity) {
        return new TextComponentString(TextFormatting.RED + "")
                .func_150257_a(aEntity.func_145748_c_())
                .func_150258_a(TextFormatting.WHITE + " has been jackhammered into pieces by " + TextFormatting.GREEN)
                .func_150257_a(aPlayer.func_145748_c_());
    }

}
