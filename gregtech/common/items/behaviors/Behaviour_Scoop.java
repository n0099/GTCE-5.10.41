package gregtech.common.items.behaviors;

import forestry.api.lepidopterology.EnumFlutterType;
import forestry.api.lepidopterology.IButterfly;
import forestry.api.lepidopterology.IEntityButterfly;
import gregtech.api.items.GT_MetaBase_Item;
import gregtech.api.items.GT_MetaGenerated_Tool;
import gregtech.api.util.GT_LanguageManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

import java.util.List;

public class Behaviour_Scoop extends Behaviour_None {

    private final int mCosts;
    private final String mTooltip = GT_LanguageManager.addStringLocalization("gt.behaviour.scoop", "Catches Butterflies on Leftclick");

    public Behaviour_Scoop(int aCosts) {
        this.mCosts = aCosts;
    }

    @Override
    public boolean onLeftClickEntity(GT_MetaBase_Item aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity, EnumHand hand) {
        if ((aEntity instanceof IEntityButterfly)) {
            if (aPlayer.field_70170_p.field_72995_K) {
                return true;
            }
            if ((aPlayer.field_71075_bZ.field_75098_d) || (((GT_MetaGenerated_Tool) aItem).doDamage(aStack, this.mCosts))) {
                Object tButterfly = ((IEntityButterfly) aEntity).getButterfly();
                ((IButterfly) tButterfly).getGenome().getPrimary().getRoot().getBreedingTracker(aEntity.field_70170_p, aPlayer.func_146103_bH()).registerCatch((IButterfly) tButterfly);
                aPlayer.field_70170_p.func_72838_d(new EntityItem(aPlayer.field_70170_p, aEntity.field_70165_t, aEntity.field_70163_u, aEntity.field_70161_v, ((IButterfly) tButterfly).getGenome().getPrimary().getRoot().getMemberStack(((IButterfly) tButterfly).copy(), EnumFlutterType.BUTTERFLY)));
                aEntity.func_70106_y();
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
