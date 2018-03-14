package gregtech.common.items.behaviors;

import gregtech.api.GregTech_API;
import gregtech.api.items.GT_MetaBase_Item;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class Behaviour_Spray_Color
        extends Behaviour_None {
    private final ItemStack mEmpty;
    private final ItemStack mUsed;
    private final ItemStack mFull;
    private final long mUses;
    private final EnumDyeColor mColor;

    private final String mTooltip;
    private final String mTooltipUses = GT_LanguageManager.addStringLocalization("gt.behaviour.paintspray.uses", "Remaining Uses:");
    private final String mTooltipUnstackable = GT_LanguageManager.addStringLocalization("gt.behaviour.unstackable", "Not usable when stacked!");

    public Behaviour_Spray_Color(ItemStack aEmpty, ItemStack aUsed, ItemStack aFull, long aUses, int aColor) {
        this.mEmpty = aEmpty;
        this.mUsed = aUsed;
        this.mFull = aFull;
        this.mUses = aUses;
        this.mColor = EnumDyeColor.values()[aColor];
        this.mTooltip = GT_LanguageManager.addStringLocalization("gt.behaviour.paintspray." + this.mColor.func_176762_d() + ".tooltip", "Can Color things in " + this.mColor.func_176610_l());
    }

    @Override
    public boolean onItemUseFirst(GT_MetaBase_Item aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if ((aWorld.field_72995_K) || (aStack.field_77994_a != 1)) {
            return false;
        }
        boolean rOutput = false;
        if (!aPlayer.func_175151_a(pos, side, aStack)) {
            return false;
        }
        NBTTagCompound tNBT = aStack.func_77978_p();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
        }
        long tUses = tNBT.func_74763_f("GT.RemainingPaint");
        if (GT_Utility.areStacksEqual(aStack, this.mFull, true)) {
            aStack.func_150996_a(this.mUsed.func_77973_b());
            Items.field_151008_G.setDamage(aStack, Items.field_151008_G.getDamage(this.mUsed));
            tUses = this.mUses;
        }
        if ((GT_Utility.areStacksEqual(aStack, this.mUsed, true)) && (colorize(aWorld, pos, side))) {
            GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(102), 1.0F, 1.0F, pos);
            if (!aPlayer.field_71075_bZ.field_75098_d) {
                tUses -= 1L;
            }
            rOutput = true;
        }
        tNBT.func_82580_o("GT.RemainingPaint");
        if (tUses > 0L) {
            tNBT.func_74772_a("GT.RemainingPaint", tUses);
        }
        if (tNBT.func_82582_d()) {
            aStack.func_77982_d(null);
        } else {
            aStack.func_77982_d(tNBT);
        }
        if (tUses <= 0L) {
            if (this.mEmpty == null) {
                aStack.field_77994_a -= 1;
            } else {
                aStack.func_150996_a(this.mEmpty.func_77973_b());
                Items.field_151008_G.setDamage(aStack, Items.field_151008_G.getDamage(this.mEmpty));
            }
        }
        return rOutput;
    }

    private boolean colorize(World aWorld, BlockPos pos, EnumFacing side) {
        IBlockState aBlockState = aWorld.func_180495_p(pos);
        Block aBlock = aBlockState.func_177230_c();
        if (aBlock instanceof BlockColored) {
            aWorld.func_175656_a(pos, aBlockState.func_177226_a(BlockColored.field_176581_a, mColor));
            return true;
        }
        return aBlock.recolorBlock(aWorld, pos, side, this.mColor);
    }

    @Override
    public List<String> getAdditionalToolTips(GT_MetaBase_Item aItem, List<String> aList, ItemStack aStack) {
        aList.add(this.mTooltip);
        NBTTagCompound tNBT = aStack.func_77978_p();
        long tRemainingPaint = tNBT == null ? 0L : GT_Utility.areStacksEqual(aStack, this.mFull, true) ? this.mUses : tNBT.func_74763_f("GT.RemainingPaint");
        aList.add(this.mTooltipUses + " " + tRemainingPaint);
        aList.add(this.mTooltipUnstackable);
        return aList;
    }

}
