package gregtech.common.items.behaviors;

import gregtech.api.GregTech_API;
import gregtech.api.items.GT_MetaBase_Item;
import gregtech.api.items.GT_MetaGenerated_Tool;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_Utility;
import ic2.api.tile.IWrenchable;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class Behaviour_Wrench
        extends Behaviour_None {
    private final int mCosts;
    private final String mTooltip = GT_LanguageManager.addStringLocalization("gt.behaviour.wrench", "Rotates Blocks on Rightclick");

    public Behaviour_Wrench(int aCosts) {
        this.mCosts = aCosts;
    }

    @Override
    public boolean onItemUseFirst(GT_MetaBase_Item aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, BlockPos blockPos, EnumFacing aSide, float hitX, float hitY, float hitZ, EnumHand hand) {
        if(!aWorld.field_72995_K && !aWorld.func_175623_d(blockPos)) {

            TileEntity tileEntity = aWorld.func_175625_s(blockPos);
            if (tileEntity instanceof IWrenchable) {
                IWrenchable wrenchable = (IWrenchable) tileEntity;
                if (aPlayer.func_70093_af()) {
                    if (wrenchable.wrenchCanRemove(aWorld, blockPos, aPlayer)) {
                        List<ItemStack> wrenchDrops = wrenchable.getWrenchDrops(aWorld, blockPos, aWorld.func_180495_p(blockPos), tileEntity, aPlayer, 0);
                        for (ItemStack wrenchDrop : wrenchDrops) {
                            if (!aPlayer.field_71071_by.func_70441_a(wrenchDrop)) {
                                Block.func_180635_a(aWorld, blockPos, wrenchDrop);
                            }
                        }
                        aWorld.func_175698_g(blockPos);
                        aWorld.func_175713_t(blockPos);
                        ((GT_MetaGenerated_Tool) aItem).doDamage(aStack, this.mCosts);
                        GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(100), 1.0F, -1.0F, blockPos);
                        return true;
                    }
                } else {
                    if (wrenchable.getFacing(aWorld, blockPos) != aSide) {
                        if (wrenchable.setFacing(aWorld, blockPos, aSide, aPlayer)) {
                            ((GT_MetaGenerated_Tool) aItem).doDamage(aStack, this.mCosts);
                            GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(100), 1.0F, -1.0F, blockPos);
                            return true;
                        }
                    }
                }
            }

            IBlockState blockState = aWorld.func_180495_p(blockPos);
            for (IProperty property : blockState.func_177227_a()) {
                if (property.func_177701_a().equals("facing")) {
                    if(property.func_177700_c().contains(aSide)) {
                        aWorld.func_175656_a(blockPos, blockState.func_177226_a(property, aSide));
                        return true;
                    }
                    return false;
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
