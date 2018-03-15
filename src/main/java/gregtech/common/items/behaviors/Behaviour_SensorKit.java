package gregtech.common.items.behaviors;

import gregtech.api.enums.ItemList;
import gregtech.api.interfaces.tileentity.IGregTechDeviceInformation;
import gregtech.api.items.GT_MetaBase_Item;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class Behaviour_SensorKit
        extends Behaviour_None {
    private final String mTooltip = GT_LanguageManager.addStringLocalization("gt.behaviour.sensorkit.tooltip", "Used to display Information using the Mod Nuclear Control");

    @Override
    public boolean onItemUseFirst(GT_MetaBase_Item aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, BlockPos pos, EnumFacing aSide, float hitX, float hitY, float hitZ, EnumHand hand) {
        if ((aPlayer instanceof EntityPlayerMP)) {
            TileEntity tTileEntity = aWorld.func_175625_s(pos);
            if (((tTileEntity instanceof IInventory)) && (!((IInventory) tTileEntity).func_70300_a(aPlayer))) {
                return false;
            }
            if (((tTileEntity instanceof IGregTechDeviceInformation)) && (((IGregTechDeviceInformation) tTileEntity).isGivingInformation())) {
                GT_Utility.setStack(aStack, ItemList.NC_SensorCard.get(aStack.field_77994_a));
                NBTTagCompound tNBT = aStack.func_77978_p();
                if (tNBT == null) {
                    tNBT = new NBTTagCompound();
                }
                tNBT.func_74768_a("x", pos.func_177958_n());
                tNBT.func_74768_a("y", pos.func_177956_o());
                tNBT.func_74768_a("z", pos.func_177952_p());
                aStack.func_77982_d(tNBT);
            }
            return true;
        }
        return false;
    }

    public List<String> getAdditionalToolTips(GT_MetaBase_Item aItem, List<String> aList, ItemStack aStack) {
        aList.add(this.mTooltip);
        return aList;
    }
}
