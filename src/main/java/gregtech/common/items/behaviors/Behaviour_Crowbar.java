package gregtech.common.items.behaviors;

import gregtech.api.items.GT_MetaBase_Item;
import gregtech.api.util.GT_ModHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Behaviour_Crowbar
        extends Behaviour_None {
    private final int mVanillaCosts;
    private final int mEUCosts;

    public Behaviour_Crowbar(int aVanillaCosts, int aEUCosts) {
        this.mVanillaCosts = aVanillaCosts;
        this.mEUCosts = aEUCosts;
    }

    @Override
    public boolean onItemUseFirst(GT_MetaBase_Item aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, BlockPos blockPos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if(!aWorld.func_175623_d(blockPos) && !aWorld.field_72995_K) {
            IBlockState blockState = aWorld.func_180495_p(blockPos);
            if(blockState.func_177230_c() instanceof BlockRailBase) {
                if(aPlayer.func_70093_af()) {
                    if(aWorld.canMineBlockBody(aPlayer, blockPos)) {
                        if(blockState.func_177230_c().canHarvestBlock(aWorld, blockPos, aPlayer)) {
                            if (GT_ModHandler.damageOrDechargeItem(aStack, this.mVanillaCosts / 2, this.mEUCosts / 2, aPlayer)) {
                                for (ItemStack drops : blockState.func_177230_c().getDrops(aWorld, blockPos, blockState, 0)) {
                                    Block.func_180635_a(aWorld, blockPos, drops);
                                }
                                blockState.func_177230_c().func_176206_d(aWorld, blockPos, blockState);
                                blockState.func_177230_c().func_176208_a(aWorld, blockPos, blockState, aPlayer);
                                blockState.func_177230_c().func_180663_b(aWorld, blockPos, blockState);
                                aWorld.func_175698_g(blockPos);
                                return true;
                            }
                        }
                    }
                } else {
                    if (GT_ModHandler.damageOrDechargeItem(aStack, this.mVanillaCosts, this.mEUCosts, aPlayer)) {
                        BlockRailBase blockRailBase = (BlockRailBase) blockState.func_177230_c();
                        int rotation = blockState.func_177229_b(blockRailBase.func_176560_l()).ordinal() + 1;
                        if (rotation >= BlockRailBase.EnumRailDirection.values().length) {
                            rotation = 0;
                        }
                        BlockRailBase.EnumRailDirection newDirection = BlockRailBase.EnumRailDirection.values()[rotation];
                        return aWorld.func_175656_a(blockPos, blockState.func_177226_a(blockRailBase.func_176560_l(), newDirection));
                    }
                }
            }
        }
        return false;
    }
}
