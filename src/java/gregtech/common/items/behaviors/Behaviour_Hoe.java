package gregtech.common.items.behaviors;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.eventhandler.Event;
import gregtech.api.items.GT_MetaBase_Item;
import gregtech.api.items.GT_MetaGenerated_Tool;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;

public class Behaviour_Hoe
        extends Behaviour_None {
    private final int mCosts;
    private final String mTooltip = GT_LanguageManager.addStringLocalization("gt.behaviour.hoe", "Can till Dirt");

    public Behaviour_Hoe(int aCosts) {
        this.mCosts = aCosts;
    }

    @Override
    public boolean onItemUse(GT_MetaBase_Item aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, BlockPos blockPos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if (aPlayer.func_175151_a(blockPos, side, aStack) && !aWorld.func_175623_d(blockPos)) {
            IBlockState blockState = aWorld.func_180495_p(blockPos);
            if (blockState.func_177230_c() == Blocks.field_150349_c || blockState.func_177230_c() == Blocks.field_150346_d) {
                if (blockState.func_177230_c() == Blocks.field_150349_c && aPlayer.func_70093_af()) {
                    if(((GT_MetaGenerated_Tool) aItem).doDamage(aStack, this.mCosts)) {
                        if (aWorld.field_73012_v.nextInt(3) == 0) {
                            ItemStack grassSeed = ForgeHooks.getGrassSeed(aWorld.field_73012_v, 0);
                            Block.func_180635_a(aWorld, blockPos.func_177984_a(), grassSeed);
                        }
                        aWorld.func_184133_a(null, blockPos, SoundEvents.field_187693_cj, SoundCategory.PLAYERS, 1.0F, 1.0F);
                        aWorld.func_175656_a(blockPos, Blocks.field_150346_d.func_176223_P()
                                .func_177226_a(BlockDirt.field_176386_a, BlockDirt.DirtType.COARSE_DIRT));
                        return true;
                    }
                } else if (blockState.func_177230_c() != Blocks.field_150346_d ||
                        blockState.func_177229_b(BlockDirt.field_176386_a) == BlockDirt.DirtType.DIRT) {
                    if(((GT_MetaGenerated_Tool) aItem).doDamage(aStack, this.mCosts)) {
                        aWorld.func_184133_a(null, blockPos, SoundEvents.field_187693_cj, SoundCategory.PLAYERS, 1.0F, 1.0F);
                        aWorld.func_175656_a(blockPos, Blocks.field_150458_ak.func_176223_P());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<String> getAdditionalToolTips(GT_MetaBase_Item aItem, List<String> aList, ItemStack aStack) {
        aList.add(this.mTooltip);
        return aList;
    }

}
