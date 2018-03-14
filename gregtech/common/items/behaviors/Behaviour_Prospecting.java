package gregtech.common.items.behaviors;

import gregtech.api.GregTech_API;
import gregtech.api.items.GT_MetaBase_Item;
import gregtech.api.items.GT_MetaGenerated_Tool;
import gregtech.api.objects.ItemData;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidBlock;

import java.util.List;

public class Behaviour_Prospecting extends Behaviour_None {

    private final int mVanillaCosts;
    private final int mEUCosts;
    private final String mTooltip = GT_LanguageManager.addStringLocalization("gt.behaviour.prospecting", "Usable for Prospecting");

    public Behaviour_Prospecting(int aVanillaCosts, int aEUCosts) {
        this.mVanillaCosts = aVanillaCosts;
        this.mEUCosts = aEUCosts;
    }

    @Override
    public boolean onItemUseFirst(GT_MetaBase_Item aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, BlockPos blockPos, EnumFacing aSide, float hitX, float hitY, float hitZ, EnumHand hand) {
        if (aWorld.field_72995_K || !aWorld.func_175623_d(blockPos)) {
            return false;
        }
        IBlockState blockState = aWorld.func_180495_p(blockPos);
        Block block = blockState.func_177230_c();
        int blockMeta = block.func_176201_c(blockState);

        ItemData tAssotiation = GT_OreDictUnificator.getAssociation(new ItemStack(block, 1, blockMeta));
        if ((tAssotiation != null) && (tAssotiation.mPrefix.toString().startsWith("ore"))) {
            GT_Utility.sendChatToPlayer(aPlayer, "This is " + tAssotiation.mMaterial.mMaterial.mDefaultLocalName + " Ore.");
            GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(1), 1.0F, -1.0F, blockPos);
            return true;
        }
        if ((block.isReplaceableOreGen(blockState, aWorld, blockPos, state ->
                state.func_177230_c() == Blocks.field_150348_b ||
                state.func_177230_c() == Blocks.field_150377_bs ||
                state.func_177230_c() == Blocks.field_150424_aL))) {
            if (GT_ModHandler.damageOrDechargeItem(aStack, this.mVanillaCosts, this.mEUCosts, aPlayer)) {
                GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(1), 1.0F, -1.0F, blockPos);
                int tMetaID = 0;
                int tQuality = (aItem instanceof GT_MetaGenerated_Tool) ? aItem.getHarvestLevel(aStack, "") : 0;
                int scanRadius = 6 + tQuality;
                for(int x = -scanRadius; x < scanRadius; x++) {
                    boolean breakIt = false;
                    if(breakIt) break;
                    for(int z = -scanRadius; z < scanRadius; z++) {
                        if(breakIt) break;
                        for(int y = 0; y < scanRadius; y++) {
                            if(breakIt) break;
                            BlockPos scanPos = blockPos.func_177982_a(x, -(y + 1), z);
                            IBlockState scanState = aWorld.func_180495_p(scanPos);
                            if(scanState.func_177230_c() == Blocks.field_150353_l || scanState.func_177230_c() == Blocks.field_150356_k) {
                                GT_Utility.sendChatToPlayer(aPlayer, "There is Lava behind this Rock");
                                breakIt = true;
                            } else if(scanState.func_177230_c() == Blocks.field_150355_j || scanState.func_177230_c() == Blocks.field_150358_i) {
                                GT_Utility.sendChatToPlayer(aPlayer, "There is Water behind this Rock");
                                breakIt = true;
                            } else if(scanState.func_177230_c() instanceof IFluidBlock) {
                                GT_Utility.sendChatToPlayer(aPlayer, "There is Fluid behind this Rock");
                                breakIt = true;
                            } else if(scanState.func_177230_c() == Blocks.field_150350_a ||
                                    scanState.func_177230_c() == Blocks.field_150418_aU ||
                                    !GT_Utility.hasBlockHitBox(aWorld, scanPos)) {
                                GT_Utility.sendChatToPlayer(aPlayer, "There is an Air Pocket behind this Rock.");
                                breakIt = true;
                            } else if(scanState.func_177230_c() != block) {
                                GT_Utility.sendChatToPlayer(aPlayer, "Material is changing behind this Rock.");
                                breakIt = true;
                            }
                        }
                    }
                }

                GT_Utility.sendChatToPlayer(aPlayer, "No Ores found.");
            }
            return true;
        }
        return false;
    }

    @Override
    public List<String> getAdditionalToolTips(GT_MetaBase_Item aItem, List<String> aList, ItemStack aStack) {
        aList.add(this.mTooltip);
        return aList;
    }

}
