package gregtech.common.covers;

import gregtech.api.enums.Materials;
import gregtech.api.interfaces.tileentity.ICoverable;
import gregtech.api.interfaces.tileentity.IMachineProgress;
import gregtech.api.util.GT_CoverBehavior;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.IFluidHandler;

public class GT_Cover_Drain
        extends GT_CoverBehavior {
    public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity, long aTimer) {
        EnumFacing sideFacing = EnumFacing.field_82609_l[aSide];
        if ((aCoverVariable % 3 > 1) && ((aTileEntity instanceof IMachineProgress))) {
            if (((IMachineProgress) aTileEntity).isAllowedToWork() != aCoverVariable % 3 < 2) {
                return aCoverVariable;
            }
        }
        if (aSide != 6) {
            Block tBlock = aTileEntity.getBlockAtSide(aSide);
            if ((aCoverVariable < 3) && ((aTileEntity instanceof IFluidHandler))) {
                if ((aSide == 1) &&
                        (aTileEntity.getWorldObj().func_72896_J()) &&
                        (aTileEntity.getWorldObj().func_175725_q(aTileEntity.getWorldPos()).func_177956_o() - 2 < aTileEntity.getYCoord())) {
                    int tAmount = (int) (aTileEntity.getBiome().func_76727_i() * 10.0F);
                    if (tAmount > 0) {
                        ((IFluidHandler) aTileEntity).fill(sideFacing, Materials.Water.getFluid(aTileEntity.getWorldObj().func_72911_I() ? tAmount * 2 : tAmount), true);
                    }
                }
                FluidStack tLiquid = null;
                if (tBlock != null) {
                    if (((tBlock == Blocks.field_150355_j) || (tBlock == Blocks.field_150358_i)) && (aTileEntity.getMetaIDAtSide(aSide) == 0)) {
                        tLiquid = Materials.Water.getFluid(1000L);
                    } else if (((tBlock == Blocks.field_150353_l) || (tBlock == Blocks.field_150356_k)) && (aTileEntity.getMetaIDAtSide(aSide) == 0)) {
                        tLiquid = Materials.Lava.getFluid(1000L);
                    } else if ((tBlock instanceof IFluidBlock)) {
                        tLiquid = ((IFluidBlock) tBlock).drain(aTileEntity.getWorldObj(), aTileEntity.getWorldPos().func_177972_a(sideFacing), false);
                    }
                    if ((tLiquid != null) && (tLiquid.getFluid() != null) && ((aSide > 1) || ((aSide == 0) && (tLiquid.getFluid().getDensity() <= 0)) || ((aSide == 1) && (tLiquid.getFluid().getDensity() >= 0))) &&
                            (((IFluidHandler) aTileEntity).fill(sideFacing, tLiquid, false) == tLiquid.amount)) {
                        ((IFluidHandler) aTileEntity).fill(sideFacing, tLiquid, true);
                        aTileEntity.getWorldObj().func_175698_g(aTileEntity.getWorldPos().func_177972_a(sideFacing));
                    }
                }
            }
            if ((aCoverVariable >= 3) && (tBlock != null) && (tBlock instanceof IFluidBlock || tBlock instanceof BlockDynamicLiquid || tBlock instanceof BlockStaticLiquid)) {
                aTileEntity.getWorldObj().func_175698_g(aTileEntity.getWorldPos().func_177972_a(sideFacing));
            }
        }
        return aCoverVariable;
    }

    public int onCoverScrewdriverclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
        aCoverVariable = (aCoverVariable + (aPlayer.func_70093_af()? -1 : 1)) % 6;
        if(aCoverVariable <0){aCoverVariable = 5;}
        switch(aCoverVariable) {
            case 0: GT_Utility.sendChatToPlayer(aPlayer, "Import"); break;
            case 1: GT_Utility.sendChatToPlayer(aPlayer, "Import (conditional)"); break;
            case 2: GT_Utility.sendChatToPlayer(aPlayer, "Import (invert cond)"); break;
            case 3: GT_Utility.sendChatToPlayer(aPlayer, "Keep Liquids Away"); break;
            case 4: GT_Utility.sendChatToPlayer(aPlayer, "Keep Liquids Away (conditional)"); break;
            case 5: GT_Utility.sendChatToPlayer(aPlayer, "Keep Liquids Away (invert cond)"); break;
        }
        return aCoverVariable;
    }

    public boolean letsFluidIn(byte aSide, int aCoverID, int aCoverVariable, Fluid aFluid, ICoverable aTileEntity) {
        return ((IMachineProgress) aTileEntity).isAllowedToWork() == aCoverVariable < 2;
    }

    public boolean alwaysLookConnected(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return true;
    }

    public int getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return aCoverVariable < 3 ? 50 : 1;
    }
}
