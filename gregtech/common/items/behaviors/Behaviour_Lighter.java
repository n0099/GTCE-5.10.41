package gregtech.common.items.behaviors;

import gregtech.api.GregTech_API;
import gregtech.api.items.GT_MetaBase_Item;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_Utility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class Behaviour_Lighter
        extends Behaviour_None {
    private final ItemStack mEmptyLighter;
    private final ItemStack mUsedLighter;
    private final ItemStack mFullLighter;
    private final long mFuelAmount;
    private final String mTooltip = GT_LanguageManager.addStringLocalization("gt.behaviour.lighter.tooltip", "Can light things on Fire");
    private final String mTooltipUses = GT_LanguageManager.addStringLocalization("gt.behaviour.lighter.uses", "Remaining Uses:");
    private final String mTooltipUnstackable = GT_LanguageManager.addStringLocalization("gt.behaviour.unstackable", "Not usable when stacked!");

    public Behaviour_Lighter(ItemStack aEmptyLighter, ItemStack aUsedLighter, ItemStack aFullLighter, long aFuelAmount) {
        this.mFullLighter = aFullLighter;
        this.mUsedLighter = aUsedLighter;
        this.mEmptyLighter = aEmptyLighter;
        this.mFuelAmount = aFuelAmount;
    }

    @Override
    public boolean onLeftClickEntity(GT_MetaBase_Item aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity, EnumHand hand) {
        if ((aPlayer.field_70170_p.field_72995_K) || (aStack.field_77994_a != 1)) {
            return false;
        }
        boolean rOutput = false;
        if ((aEntity instanceof EntityCreeper)) {
            prepare(aStack);
            long tFuelAmount = GT_Utility.ItemNBT.getLighterFuel(aStack);
            if (GT_Utility.areStacksEqual(aStack, this.mUsedLighter, true)) {
                GT_Utility.sendSoundToPlayers(aPlayer.field_70170_p, GregTech_API.sSoundList.get(6), 1.0F, 1.0F, MathHelper.func_76128_c(aEntity.field_70165_t), MathHelper.func_76128_c(aEntity.field_70163_u), MathHelper.func_76128_c(aEntity.field_70161_v));
                ((EntityCreeper) aEntity).func_146079_cb();
                if (!aPlayer.field_71075_bZ.field_75098_d) {
                    tFuelAmount -= 1L;
                }
                rOutput = true;
            }
            GT_Utility.ItemNBT.setLighterFuel(aStack, tFuelAmount);
            if (tFuelAmount <= 0L) {
                useUp(aStack);
            }
        }
        return rOutput;
    }

    @Override
    public boolean onItemUse(GT_MetaBase_Item aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, BlockPos blockPos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        return false;
    }

    @Override
    public boolean onItemUseFirst(GT_MetaBase_Item aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, BlockPos blockPos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if ((aWorld.field_72995_K) || (aStack.field_77994_a != 1) || !aPlayer.func_175151_a(blockPos, side, aStack)) {
            return false;
        }

        long tFuelAmount = GT_Utility.ItemNBT.getLighterFuel(aStack);
        BlockPos clickedBlock = blockPos.func_177972_a(side);
        if(Blocks.field_150480_ab.canCatchFire(aWorld, blockPos, side)) {
            prepare(aStack);
            aWorld.func_175656_a(clickedBlock, Blocks.field_150480_ab.func_176223_P());
            GT_Utility.ItemNBT.setLighterFuel(aStack, --tFuelAmount);
            if(tFuelAmount == 0L) {
                useUp(aStack);
            }
        } else if(Blocks.field_150480_ab.canCatchFire(aWorld, clickedBlock, EnumFacing.UP)) {
            prepare(aStack);
            aWorld.func_175656_a(clickedBlock, Blocks.field_150480_ab.func_176223_P());
            GT_Utility.ItemNBT.setLighterFuel(aStack, --tFuelAmount);
            if(tFuelAmount == 0L) {
                useUp(aStack);
            }
        }

        return false;
    }

    private void prepare(ItemStack aStack) {
        if (GT_Utility.areStacksEqual(aStack, this.mFullLighter, true)) {
            aStack.func_150996_a(this.mUsedLighter.func_77973_b());
            Items.field_151008_G.setDamage(aStack, Items.field_151008_G.getDamage(this.mUsedLighter));
            GT_Utility.ItemNBT.setLighterFuel(aStack, this.mFuelAmount);
        }
    }

    private void useUp(ItemStack aStack) {
        if (this.mEmptyLighter == null) {
            aStack.field_77994_a -= 1;
        } else {
            aStack.func_150996_a(this.mEmptyLighter.func_77973_b());
            Items.field_151008_G.setDamage(aStack, Items.field_151008_G.getDamage(this.mEmptyLighter));
        }
    }

    public List<String> getAdditionalToolTips(GT_MetaBase_Item aItem, List<String> aList, ItemStack aStack) {
        aList.add(this.mTooltip);
        NBTTagCompound tNBT = aStack.func_77978_p();
        long tFuelAmount = tNBT == null ? 0L : GT_Utility.areStacksEqual(aStack, this.mFullLighter, true) ? this.mFuelAmount : tNBT.func_74763_f("GT.LighterFuel");
        aList.add(this.mTooltipUses + " " + tFuelAmount);
        aList.add(this.mTooltipUnstackable);
        return aList;
    }
}
