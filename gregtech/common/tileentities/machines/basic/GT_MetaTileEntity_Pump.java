package gregtech.common.tileentities.machines.basic;

import gregtech.api.enums.Textures;
import gregtech.api.gui.GT_Container_BasicTank;
import gregtech.api.gui.GT_GUIContainer_BasicTank;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.BaseTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Utility;
import ic2.core.block.machine.BlockMiningPipe;
import ic2.core.ref.BlockName;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;

import java.util.ArrayList;
import java.util.Iterator;

import static gregtech.api.enums.GT_Values.V;

public class GT_MetaTileEntity_Pump extends GT_MetaTileEntity_Hatch {

    private static IBlockState miningPipe = GT_ModHandler.getIC2BlockState(BlockName.mining_pipe, BlockMiningPipe.MiningPipeType.pipe);
    private static IBlockState miningPipeTip = GT_ModHandler.getIC2BlockState(BlockName.mining_pipe, BlockMiningPipe.MiningPipeType.tip);

    public ArrayList<BlockPos> mPumpList = new ArrayList<>();
    public int mPumpTimer = 0;
    public int mPumpCountBelow = 0;
    public Block mPumpedBlock1 = null;
    public Block mPumpedBlock2 = null;

    public GT_MetaTileEntity_Pump(int aID, String aName, String aNameRegional, int aTier) {
        super(aID, aName, aNameRegional, aTier, 3, "The best way of emptying Oceans!");
    }

    public GT_MetaTileEntity_Pump(String aName, int aTier, String aDescription, ITexture[][][] aTextures) {
        super(aName, aTier, 3, aDescription, aTextures);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Pump(this.mName, this.mTier, this.mDescription, this.mTextures);
    }

    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
        aNBT.func_74778_a("mPumpedBlock1", this.mPumpedBlock1 == null ? "" : this.mPumpedBlock1.func_149739_a());
        aNBT.func_74778_a("mPumpedBlock2", this.mPumpedBlock2 == null ? "" : this.mPumpedBlock2.func_149739_a());
    }

    public void loadNBTData(NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
        this.mPumpedBlock1 = Block.func_149684_b(aNBT.func_74779_i("mPumpedBlock1"));
        this.mPumpedBlock2 = Block.func_149684_b(aNBT.func_74779_i("mPumpedBlock2"));
    }

    @Override
    public Object getServerGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
        return new GT_Container_BasicTank(aPlayerInventory, aBaseMetaTileEntity);
    }

    @Override
    public Object getClientGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
        return new GT_GUIContainer_BasicTank(aPlayerInventory, aBaseMetaTileEntity, getLocalName());
    }

    @Override
    public boolean doesFillContainers() {
        return true;
    }

    @Override
    public boolean doesEmptyContainers() {
        return false;
    }

    @Override
    public boolean canTankBeFilled() {
        return false;
    }

    @Override
    public boolean canTankBeEmptied() {
        return true;
    }

    @Override
    public boolean displaysItemStack() {
        return true;
    }

    @Override
    public boolean displaysStackSize() {
        return false;
    }

    @Override
    public boolean isFluidInputAllowed(FluidStack aFluid) {
        return false;
    }

    @Override
    public void onPostTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick) {
        super.onPostTick(aBaseMetaTileEntity, aTick);
        if (getBaseMetaTileEntity().isServerSide()) {
            this.mPumpTimer -= 1;
            if ((getBaseMetaTileEntity() instanceof BaseTileEntity)) {
                ((BaseTileEntity) getBaseMetaTileEntity()).ignoreUnloadedChunks = false;
            }
            this.doTickProfilingInThisTick = true;
            this.mPumpCountBelow = 0;
            IGregTechTileEntity tTileEntity;
            for (int i = 1; (i < 21) && ((tTileEntity = getBaseMetaTileEntity().getIGregTechTileEntityAtSideAndDistance((byte) 0, i)) != null)
                    && (tTileEntity.getMetaTileEntity() != null) && ((tTileEntity.getMetaTileEntity() instanceof GT_MetaTileEntity_Pump)); i++) {
                getBaseMetaTileEntity().setActive(tTileEntity.isActive());
                this.mPumpCountBelow += 1;
                ((GT_MetaTileEntity_Pump) tTileEntity.getMetaTileEntity()).mPumpTimer -= 1;
            }
            if (this.mPumpCountBelow <= 0) {
                if ((getBaseMetaTileEntity().isAllowedToWork()) && (getBaseMetaTileEntity().isUniversalEnergyStored(16 * ((int) Math.pow(4, this.mTier))))
                        && ((this.mFluid == null) || (this.mFluid.amount + 1000 <= getCapacity()))) {
                    boolean tMovedOneDown = false;
                    if ((this.mPumpList.isEmpty()) && (getBaseMetaTileEntity().getTimer() % 100L == 0L)) {
                        tMovedOneDown = moveOneDown();
                    }
                    if ((GT_Utility.isBlockInvalid(this.mPumpedBlock1)) || (GT_Utility.isBlockInvalid(this.mPumpedBlock2))) {
                        getFluidAt(getYOfPumpHead().func_177977_b());
                        if ((GT_Utility.isBlockInvalid(this.mPumpedBlock1)) || (GT_Utility.isBlockInvalid(this.mPumpedBlock2))) {
                            getFluidAt(getYOfPumpHead().func_177974_f());
                        }
                        if ((GT_Utility.isBlockInvalid(this.mPumpedBlock1)) || (GT_Utility.isBlockInvalid(this.mPumpedBlock2))) {
                            getFluidAt(getYOfPumpHead().func_177976_e());
                        }
                        if ((GT_Utility.isBlockInvalid(this.mPumpedBlock1)) || (GT_Utility.isBlockInvalid(this.mPumpedBlock2))) {
                            getFluidAt(getYOfPumpHead().func_177968_d());
                        }
                        if ((GT_Utility.isBlockInvalid(this.mPumpedBlock1)) || (GT_Utility.isBlockInvalid(this.mPumpedBlock2))) {
                            getFluidAt(getYOfPumpHead().func_177978_c());
                        }
                    } else if (getYOfPumpHead().func_177956_o() < getBaseMetaTileEntity().getYCoord()) {
                        if ((tMovedOneDown) || ((this.mPumpList.isEmpty()) && (getBaseMetaTileEntity().getTimer() % 200L == 100L)) || (getBaseMetaTileEntity().getTimer() % 72000L == 100L)) {
                            this.mPumpList.clear();
                            int y = getBaseMetaTileEntity().getYCoord() - 1;
                            for (int yHead = getYOfPumpHead().func_177956_o(); (this.mPumpList.isEmpty()) && (y >= yHead); y--) {
                                scanForFluid(getBaseMetaTileEntity().getXCoord(), y, getBaseMetaTileEntity().getZCoord(), this.mPumpList, getBaseMetaTileEntity().getXCoord(), getBaseMetaTileEntity().getZCoord(), 10 * ((int) Math.pow(1.6, this.mTier)));
                            }
                        }
                        if (!tMovedOneDown && this.mPumpTimer <= 0) {
                            while (!this.mPumpList.isEmpty() && !consumeFluid(this.mPumpList.get(this.mPumpList.size() - 1))) {}
                            this.mPumpTimer = 160 / ((int) Math.pow(2, this.mTier));
                        }
                    }
                }
                getBaseMetaTileEntity().setActive(!this.mPumpList.isEmpty());
            }
        }
    }

    private boolean moveOneDown() {
        if ((this.mInventory[0] == null) || (this.mInventory[0].field_77994_a < 1)
                || (!GT_Utility.areStacksEqual(this.mInventory[0], BlockName.mining_pipe.getItemStack(BlockMiningPipe.MiningPipeType.pipe)))) {
            return false;
        }
        BlockPos yHead = getYOfPumpHead();
        if (yHead.func_177956_o() <= 0) {
            return false;
        }
        if (!consumeFluid(yHead.func_177977_b()) && !getBaseMetaTileEntity().isAir(yHead.func_177977_b())) {
            return false;
        }
        if (!getBaseMetaTileEntity().setBlockState(yHead.func_177977_b(), miningPipeTip)) {
            return false;
        }
        if (yHead.func_177956_o() != getBaseMetaTileEntity().getYCoord()) {
            getBaseMetaTileEntity().setBlockState(yHead, miningPipeTip);
        }
        getBaseMetaTileEntity().func_70298_a(0, 1);
        return true;
    }

    private BlockPos getYOfPumpHead() {
        BlockPos yPos = new BlockPos.MutableBlockPos(getBaseMetaTileEntity().getWorldPos()).func_177977_b();
        while (getBaseMetaTileEntity().getBlockState(yPos) == miningPipe) {
            yPos.func_177977_b();
        }

        if (yPos.func_177956_o() == getBaseMetaTileEntity().getYCoord() - 1) {
            if (getBaseMetaTileEntity().getBlockState(yPos) != miningPipeTip) {
                return yPos.func_177984_a().func_185334_h();
            }

        } else if (getBaseMetaTileEntity().getBlockState(yPos) != miningPipeTip && this.mInventory[0] != null && this.mInventory[0].field_77994_a > 0 && GT_Utility.areStacksEqual(this.mInventory[0], BlockName.mining_pipe.getItemStack(BlockMiningPipe.MiningPipeType.pipe))) {
            getBaseMetaTileEntity().setBlockState(yPos, miningPipeTip);
            getBaseMetaTileEntity().func_70298_a(0, 1);
        }
        return yPos.func_185334_h();
    }

    private void scanForFluid(int aX, int aY, int aZ, ArrayList<BlockPos> aList, int mX, int mZ, int mDist) {
        doTickProfilingInThisTick = false;
        ArrayList tList1 = new ArrayList();
        ArrayList tList2 = new ArrayList();
        tList1.add(new BlockPos(aX, aY, aZ));
        while (!tList1.isEmpty()) {
            Iterator i$ = tList1.iterator();
            do {
                if (!i$.hasNext())
                    break;
                BlockPos tPos = (BlockPos) i$.next();
                if (tPos.func_177958_n() < mX + mDist)
                    addToFirstListIfFluidAndNotAlreadyAddedToAnyOfTheLists(tPos.func_177958_n() + 1, tPos.func_177956_o(), tPos.func_177952_p(), tList2, aList);
                if (tPos.func_177958_n() > mX - mDist)
                    addToFirstListIfFluidAndNotAlreadyAddedToAnyOfTheLists(tPos.func_177958_n() - 1, tPos.func_177956_o(), tPos.func_177952_p(), tList2, aList);
                if (tPos.func_177952_p() < mZ + mDist)
                    addToFirstListIfFluidAndNotAlreadyAddedToAnyOfTheLists(tPos.func_177958_n(), tPos.func_177956_o(), tPos.func_177952_p() + 1, tList2, aList);
                if (tPos.func_177952_p() > mZ - mDist)
                    addToFirstListIfFluidAndNotAlreadyAddedToAnyOfTheLists(tPos.func_177958_n(), tPos.func_177956_o(), tPos.func_177952_p() - 1, tList2, aList);
                addToFirstListIfFluidAndNotAlreadyAddedToAnyOfTheLists(tPos.func_177958_n(), tPos.func_177956_o() + 1, tPos.func_177952_p(), tList2, aList);
                BlockPos tCoordinate = new BlockPos(aX, aY + 1, aZ);
                if (tPos.func_177958_n() == mX && tPos.func_177952_p() == mZ && tPos.func_177956_o() < getBaseMetaTileEntity().getYCoord() && !aList.contains(tCoordinate) && !tList2.contains(tCoordinate))
                    tList2.add(tCoordinate);
            } while (true);
            aList.addAll(tList2);
            tList1 = tList2;
            tList2 = new ArrayList();
        }
        for (int y = getBaseMetaTileEntity().getYCoord(); y >= aY; y--)
            aList.remove(new BlockPos(aX, y, aZ));
    }

    private boolean addToFirstListIfFluidAndNotAlreadyAddedToAnyOfTheLists(int aX, int aY, int aZ, ArrayList<BlockPos> aList1,
                                                                           ArrayList<BlockPos> aList2) {
        BlockPos tCoordinate = new BlockPos(aX, aY, aZ);
        if ((!aList1.contains(tCoordinate)) && (!aList2.contains(tCoordinate))) {
            Block aBlock = getBaseMetaTileEntity().getBlock(aX, aY, aZ);
            if ((this.mPumpedBlock1 == aBlock) || (this.mPumpedBlock2 == aBlock)) {
                aList1.add(tCoordinate);
                return true;
            }
        }
        return false;
    }

    private void getFluidAt(BlockPos pos) {
        Block aBlock = getBaseMetaTileEntity().getBlockState(pos).func_177230_c();
        if (GT_Utility.isBlockValid(aBlock)) {
            if ((aBlock == Blocks.field_150355_j) || (aBlock == Blocks.field_150358_i)) {
                this.mPumpedBlock1 = Blocks.field_150355_j;
                this.mPumpedBlock2 = Blocks.field_150358_i;
                return;
            }
            if ((aBlock == Blocks.field_150353_l) || (aBlock == Blocks.field_150356_k)) {
                this.mPumpedBlock1 = Blocks.field_150353_l;
                this.mPumpedBlock2 = Blocks.field_150356_k;
                return;
            }
            if ((aBlock instanceof IFluidBlock)) {
                this.mPumpedBlock1 = aBlock;
                this.mPumpedBlock2 = aBlock;
                return;
            }
        }
        this.mPumpedBlock1 = null;
        this.mPumpedBlock2 = null;
    }

    private boolean consumeFluid(BlockPos pos) {
        IBlockState fluidState = getBaseMetaTileEntity().getBlockState(pos);
        Block aBlock = fluidState.func_177230_c();
        int aMeta = fluidState.func_177230_c().func_176201_c(fluidState);
        if ((GT_Utility.isBlockValid(aBlock)) && ((this.mPumpedBlock1 == aBlock) || (this.mPumpedBlock2 == aBlock))) {
            if ((aBlock == Blocks.field_150355_j) || (aBlock == Blocks.field_150358_i)) {
                if (aMeta == 0) {
                    if (this.mFluid == null) {
                        getBaseMetaTileEntity().decreaseStoredEnergyUnits(16 * ((int) Math.pow(4, this.mTier)), true);
                        this.mFluid = GT_ModHandler.getWater(1000L);
                    } else if (GT_ModHandler.isWater(this.mFluid)) {
                        getBaseMetaTileEntity().decreaseStoredEnergyUnits(16 * ((int) Math.pow(4, this.mTier)), true);
                        this.mFluid.amount += 1000;
                    } else {
                        return false;
                    }
                } else {
                    getBaseMetaTileEntity().decreaseStoredEnergyUnits(4 * ((int) Math.pow(4, this.mTier)), true);
                }
            }
            if ((aBlock == Blocks.field_150353_l) || (aBlock == Blocks.field_150356_k)) {
                if (aMeta == 0) {
                    if (this.mFluid == null) {
                        getBaseMetaTileEntity().decreaseStoredEnergyUnits(16 * ((int) Math.pow(4, this.mTier)), true);
                        this.mFluid = GT_ModHandler.getLava(1000L);
                    } else if (GT_ModHandler.isLava(this.mFluid)) {
                        getBaseMetaTileEntity().decreaseStoredEnergyUnits(16 * ((int) Math.pow(4, this.mTier)), true);
                        this.mFluid.amount += 1000;
                    } else {
                        return false;
                    }
                } else {
                    getBaseMetaTileEntity().decreaseStoredEnergyUnits(4 * ((int) Math.pow(4, this.mTier)), true);
                }
            }
            if ((aBlock instanceof IFluidBlock)) {
                if (this.mFluid == null) {
                    this.mFluid = ((IFluidBlock) aBlock).drain(getBaseMetaTileEntity().getWorldObj(), pos, true);
                    getBaseMetaTileEntity().decreaseStoredEnergyUnits(this.mFluid == null ? 1000 : this.mFluid.amount, true);
                } else if (this.mFluid.isFluidEqual(((IFluidBlock) aBlock).drain(getBaseMetaTileEntity().getWorldObj(), pos, false))) {
                    this.getBaseMetaTileEntity().setBlockToAir(pos);
                    this.mFluid.amount += 1000;
                    getBaseMetaTileEntity().decreaseStoredEnergyUnits(16 * ((int) Math.pow(4, this.mTier)), true);
                } else {
                    return false;
                }
            }
            this.getBaseMetaTileEntity().setBlockToAir(pos);
            return true;
        }
        return false;
    }

    @Override
    public boolean onRightclick(IGregTechTileEntity aBaseMetaTileEntity, EntityPlayer aPlayer, EnumHand hand) {
        if (aBaseMetaTileEntity.isClientSide()) return true;
        aBaseMetaTileEntity.openGUI(aPlayer);
        return true;
    }


    @Override
    public boolean isSimpleMachine() {
        return false;
    }

    @Override
    public boolean isOverclockerUpgradable() {
        return false;
    }

    @Override
    public boolean isTransformerUpgradable() {
        return false;
    }

    @Override
    public boolean isElectric() {
        return true;
    }

    @Override
    public boolean isFacingValid(byte aFacing) {
        return true;
    }

    @Override
    public boolean isEnetInput() {
        return true;
    }

    @Override
    public boolean isInputFacing(byte aSide) {
        return true;
    }

    @Override
    public boolean isOutputFacing(byte aSide) {
        return false;
    }

    @Override
    public boolean isTeleporterCompatible() {
        return false;
    }

    @Override
    public long getMinimumStoredEU() {
        return V[mTier] * 16;
    }

    @Override
    public long maxEUStore() {
        return V[mTier] * 64;
    }

    @Override
    public long maxEUInput() {
        return V[mTier];
    }

    @Override
    public long maxSteamStore() {
        return maxEUStore();
    }

    @Override
    public long maxAmperesIn() {
        return 2;
    }

    @Override
    public int getStackDisplaySlot() {
        return 2;
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }


    @Override
    public int getCapacity() {
        return 16000 * this.mTier;
    }

    @Override
    public int getTankPressure() {
        return 100;
    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, byte aSide, byte aFacing, byte aColorIndex, boolean aActive, boolean aRedstone) {
        return new ITexture[]{Textures.BlockIcons.MACHINE_CASINGS[mTier][aColorIndex + 1], (aSide == 0 || aSide == 1) ? null : new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ADV_PUMP)};
    }

    @Override
    public ITexture[] getTexturesActive(ITexture aBaseTexture) {
        return getTexturesInactive(aBaseTexture);
    }

    @Override
    public ITexture[] getTexturesInactive(ITexture aBaseTexture) {
        return new ITexture[]{
                new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ADV_PUMP), new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ADV_PUMP),
                new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ADV_PUMP), new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ADV_PUMP),};
    }
}
