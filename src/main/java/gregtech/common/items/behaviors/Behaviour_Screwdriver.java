package gregtech.common.items.behaviors;

import gregtech.api.items.GT_MetaBase_Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Behaviour_Screwdriver
        extends Behaviour_None {
    private final int mVanillaCosts;
    private final int mEUCosts;

    public Behaviour_Screwdriver(int aVanillaCosts, int aEUCosts) {
        this.mVanillaCosts = aVanillaCosts;
        this.mEUCosts = aEUCosts;
    }

    @Override
    public boolean onItemUseFirst(GT_MetaBase_Item aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, BlockPos pos, EnumFacing aSide, float hitX, float hitY, float hitZ, EnumHand hand) {
        return false; //TODO
    }
}
