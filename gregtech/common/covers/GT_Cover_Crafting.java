package gregtech.common.covers;

import gregtech.api.interfaces.tileentity.ICoverable;
import gregtech.api.util.GT_CoverBehavior;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.network.play.server.SPacketOpenWindow;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class GT_Cover_Crafting extends GT_CoverBehavior {

    public boolean onCoverRightclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
        if ((aPlayer instanceof EntityPlayerMP)) {
            EntityPlayerMP playerMP = (EntityPlayerMP) aPlayer;
            playerMP.func_71117_bO();

            playerMP.field_71135_a.func_147359_a(new SPacketOpenWindow(playerMP.field_71139_cq, "minecraft:crafting_table", new TextComponentString("Crafting")));
            playerMP.field_71070_bA = new ContainerWorkbench(playerMP.field_71071_by, playerMP.field_70170_p, new BlockPos(playerMP)) {
                @Override
                public boolean func_75145_c(EntityPlayer playerIn) {
                    return true;
                }
            };
        }
        return true;
    }
}
