package gregtech.common.items.behaviors;

import gregtech.api.GregTech_API;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.metatileentity.IMetaTileEntityItemPipe;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.items.GT_MetaBase_Item;
import gregtech.api.items.GT_MetaGenerated_Tool;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_Utility;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;

public class Behaviour_Plunger_Item
        extends Behaviour_None {
    private final int mCosts;
    private final String mTooltip = GT_LanguageManager.addStringLocalization("gt.behaviour.plunger.item", "Clears Items from Pipes");

    public Behaviour_Plunger_Item(int aCosts) {
        this.mCosts = aCosts;
    }

    public boolean onItemUseFirst(GT_MetaBase_Item aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, BlockPos blockPos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if (aWorld.field_72995_K) {
            return false;
        }
        TileEntity aTileEntity = aWorld.func_175625_s(blockPos);
        if ((aTileEntity instanceof IGregTechTileEntity)) {
            IMetaTileEntity tMetaTileEntity = ((IGregTechTileEntity) aTileEntity).getMetaTileEntity();
            if ((tMetaTileEntity instanceof IMetaTileEntityItemPipe)) {
                for (Object tTileEntity : GT_Utility.sortMapByValuesAcending(IMetaTileEntityItemPipe.Util.scanPipes((IMetaTileEntityItemPipe) tMetaTileEntity, new HashMap<>(), 0L, false, true)).keySet()) {
                    int i = 0;
                    for (int j = ((IMetaTileEntityItemPipe) tTileEntity).func_70302_i_(); i < j; i++) {
                        if (((IMetaTileEntityItemPipe) tTileEntity).isValidSlot(i)) {
                            if ((((IMetaTileEntityItemPipe) tTileEntity).func_70301_a(i) != null) && (
                                    (aPlayer.field_71075_bZ.field_75098_d) || (((GT_MetaGenerated_Tool) aItem).doDamage(aStack, this.mCosts)))) {
                                ItemStack tStack = ((IMetaTileEntityItemPipe) tTileEntity).func_70298_a(i, 64);
                                if (tStack != null) {
                                    BlockPos faced = blockPos.func_177972_a(side);
                                    EntityItem tEntity = new EntityItem(aWorld, faced.func_177958_n(), faced.func_177956_o(), faced.func_177952_p(), tStack);
                                    tEntity.field_70159_w = 0.0D;
                                    tEntity.field_70181_x = 0.0D;
                                    tEntity.field_70179_y = 0.0D;
                                    aWorld.func_72838_d(tEntity);
                                    GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(101), 1.0F, -1.0F, blockPos);
                                }
                                return true;
                            }
                        }
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
