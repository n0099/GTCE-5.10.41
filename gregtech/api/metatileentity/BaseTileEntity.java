package gregtech.api.metatileentity;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.interfaces.tileentity.IHasWorldObjectAndCoords;
import gregtech.api.net.GT_Packet_Block_Event;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fluids.IFluidHandler;

import static gregtech.api.enums.GT_Values.GT;
import static gregtech.api.enums.GT_Values.NW;

/**
 * The Functions my old TileEntities and my BaseMetaTileEntities have in common.
 * <p/>
 * Basically everything a TileEntity should have.
 */
public abstract class BaseTileEntity extends TileEntity implements IHasWorldObjectAndCoords, ITickable {
    /**
     * Buffers adjacent TileEntities for faster access
     * <p/>
     * "this" means that there is no TileEntity, while "null" means that it doesn't know if there is even a TileEntity and still needs to check that if needed.
     */
    private final TileEntity[] mBufferedTileEntities = new TileEntity[6];
    /**
     * If this TileEntity checks for the Chunk to be loaded before returning World based values.
     * The AdvPump hacks this to false to ensure everything runs properly even when far Chunks are not actively loaded.
     * But anything else should not cause worfin' Chunks, uhh I mean orphan Chunks.
     */
    public boolean ignoreUnloadedChunks = true;
    /**
     * This Variable checks if this TileEntity is dead, because Minecraft is too stupid to have proper TileEntity unloading.
     */
    public boolean isDead = false;

    private final void clearNullMarkersFromTileEntityBuffer() {
        for (int i = 0; i < mBufferedTileEntities.length; i++)
            if (mBufferedTileEntities[i] == this) mBufferedTileEntities[i] = null;
    }

    /**
     * Called automatically when the Coordinates of this TileEntity have been changed
     */
    protected final void clearTileEntityBuffer() {
        for (int i = 0; i < mBufferedTileEntities.length; i++) mBufferedTileEntities[i] = null;
    }

    @Override
    public World getWorldObj() {
        return field_145850_b;
    }

    @Override
    public BlockPos getWorldPos() {
        return field_174879_c;
    }

    @Override
    public final int getXCoord() {
        return func_174877_v().func_177958_n();
    }

    @Override
    public final short getYCoord() {
        return (short) func_174877_v().func_177956_o();
    }

    @Override
    public final int getZCoord() {
        return func_174877_v().func_177952_p();
    }

    @Override
    public final boolean isServerSide() {
        return !field_145850_b.field_72995_K;
    }

    @Override
    public final boolean isClientSide() {
        return field_145850_b.field_72995_K;
    }

    @Override
    public final boolean openGUI(EntityPlayer aPlayer) {
        return openGUI(aPlayer, 0);
    }

    @Override
    public final boolean openGUI(EntityPlayer aPlayer, int aID) {
        if (aPlayer == null) return false;
        aPlayer.openGui(GT, aID, field_145850_b, getXCoord(), getYCoord(), getZCoord());
        return true;
    }

    @Override
    public final int getRandomNumber(int aRange) {
        return field_145850_b.field_73012_v.nextInt(aRange);
    }

    private BlockPos.MutableBlockPos M = new BlockPos.MutableBlockPos();



    @Override
    public final Biome getBiome(int aX, int aZ) {
        return field_145850_b.func_180494_b(M.func_181079_c(aX, 1, aZ));
    }

    @Override
    public final Biome getBiome() {
        return field_145850_b.func_180494_b(func_174877_v());
    }

    @Override
    public final IBlockState getBlockStateOffset(int aX, int aY, int aZ) {
        return field_145850_b.func_180495_p(M.func_181079_c(getXCoord() + aX, getYCoord() + aY, getZCoord() + aZ));
    }

    @Override
    public final Block getBlockOffset(int aX, int aY, int aZ) {
        return getBlockStateOffset(aX, aY, aZ).func_177230_c();
    }

    @Override
    public final Block getBlockAtSide(byte aSide) {
        return getBlockAtSideAndDistance(aSide, 1);
    }

    public final IBlockState getBlockStateAtSideAndDistance(byte aSide, int aDistance) {
        EnumFacing side = EnumFacing.field_82609_l[aSide];
        return getBlockStateOffset(side.func_82601_c() * aDistance, side.func_96559_d() * aDistance, side.func_82599_e() * aDistance);
    }

    @Override
    public final Block getBlockAtSideAndDistance(byte aSide, int aDistance) {
        return getBlockStateAtSideAndDistance(aSide, aDistance).func_177230_c();
    }

    @Override
    public final byte getMetaIDOffset(int aX, int aY, int aZ) {
        IBlockState blockState = getBlockStateOffset(aX, aY, aZ);
        return (byte) blockState.func_177230_c().func_176201_c(blockState);
    }

    @Override
    public final byte getMetaIDAtSide(byte aSide) {
        return getMetaIDAtSideAndDistance(aSide, 1);
    }

    @Override
    public final byte getMetaIDAtSideAndDistance(byte aSide, int aDistance) {
        IBlockState blockState = getBlockStateAtSideAndDistance(aSide, aDistance);
        return (byte) blockState.func_177230_c().func_176201_c(blockState);
    }

    @Override
    public final byte getLightLevelOffset(int aX, int aY, int aZ) {
        return (byte) field_145850_b.func_175699_k(M.func_181079_c(getXCoord() + aX, getYCoord() + aY, getZCoord() + aZ));
    }

    @Override
    public final byte getLightLevelAtSide(byte aSide) {
        return getLightLevelAtSideAndDistance(aSide, 1);
    }

    @Override
    public final byte getLightLevelAtSideAndDistance(byte aSide, int aDistance) {
        EnumFacing side = EnumFacing.field_82609_l[aSide];
        return getLightLevelOffset(side.func_82601_c() * aDistance, side.func_96559_d() * aDistance, side.func_82599_e() * aDistance);
    }

    @Override
    public final boolean getOpacityOffset(int aX, int aY, int aZ) {
        return getBlockStateOffset(aX, aY, aZ).func_185914_p();
    }

    @Override
    public final boolean getOpacityAtSide(byte aSide) {
        return getOpacityAtSideAndDistance(aSide, 1);
    }

    @Override
    public final boolean getOpacityAtSideAndDistance(byte aSide, int aDistance) {
        return getBlockStateAtSideAndDistance(aSide, aDistance).func_185914_p();
    }

    @Override
    public final boolean getSkyOffset(int aX, int aY, int aZ) {
        return field_145850_b.func_175678_i(M.func_181079_c(getXCoord() + aX, getYCoord() + aY, getZCoord() + aZ));
    }

    @Override
    public final boolean getSkyAtSide(byte aSide) {
        return getSkyAtSideAndDistance(aSide, 1);
    }

    @Override
    public final boolean getSkyAtSideAndDistance(byte aSide, int aDistance) {
        EnumFacing side = EnumFacing.field_82609_l[aSide];
        return getSkyOffset(side.func_82601_c() * aDistance, side.func_96559_d() * aDistance, side.func_82599_e() * aDistance);
    }

    @Override
    public final boolean getAirOffset(int aX, int aY, int aZ) {
        return field_145850_b.func_175623_d(M.func_181079_c(getXCoord() + aX, getYCoord() + aY, getZCoord() + aZ));
    }

    @Override
    public final boolean getAirAtSide(byte aSide) {
        return getAirAtSideAndDistance(aSide, 1);
    }

    @Override
    public final boolean getAirAtSideAndDistance(byte aSide, int aDistance) {
        EnumFacing side = EnumFacing.field_82609_l[aSide];
        return getAirOffset(side.func_82601_c() * aDistance, side.func_96559_d() * aDistance, side.func_82599_e() * aDistance);
    }

    @Override
    public final TileEntity getTileEntityOffset(int aX, int aY, int aZ) {
        return field_145850_b.func_175625_s(M.func_181079_c(getXCoord() + aX, getYCoord() + aY, getZCoord() + aZ));
    }

    @Override
    public final TileEntity getTileEntityAtSideAndDistance(byte aSide, int aDistance) {
        EnumFacing side = EnumFacing.field_82609_l[aSide];
        return getTileEntityOffset(side.func_82601_c() * aDistance, side.func_96559_d() * aDistance, side.func_82599_e() * aDistance);
    }

    @Override
    public final IInventory getIInventory(int aX, int aY, int aZ) {
        TileEntity tileEntity = getTileEntity(aX, aY, aZ);
        return tileEntity instanceof IInventory ? (IInventory) tileEntity : null;
    }

    @Override
    public final IInventory getIInventoryOffset(int aX, int aY, int aZ) {
        TileEntity tileEntity = getTileEntityOffset(aX, aY, aZ);
        return tileEntity instanceof IInventory ? (IInventory) tileEntity : null;
    }

    @Override
    public final IInventory getIInventoryAtSide(byte aSide) {
        return getIInventoryAtSideAndDistance(aSide, 1);
    }

    @Override
    public final IInventory getIInventoryAtSideAndDistance(byte aSide, int aDistance) {
        TileEntity tileEntity = getTileEntityAtSideAndDistance(aSide, aDistance);
        return tileEntity instanceof IInventory ? (IInventory) tileEntity : null;
    }

    @Override
    public final IFluidHandler getITankContainer(int aX, int aY, int aZ) {
        TileEntity tileEntity = getTileEntity(aX, aY, aZ);
        return tileEntity instanceof IFluidHandler ? (IFluidHandler) tileEntity : null;
    }

    @Override
    public final IFluidHandler getITankContainerOffset(int aX, int aY, int aZ) {
        TileEntity tileEntity = getTileEntityOffset(aX, aY, aZ);
        return tileEntity instanceof IFluidHandler ? (IFluidHandler) tileEntity : null;
    }

    @Override
    public final IFluidHandler getITankContainerAtSide(byte aSide) {
        return getITankContainerAtSideAndDistance(aSide, 1);
    }

    @Override
    public final IFluidHandler getITankContainerAtSideAndDistance(byte aSide, int aDistance) {
        TileEntity tileEntity = getTileEntityAtSideAndDistance(aSide, aDistance);
        return tileEntity instanceof IFluidHandler ? (IFluidHandler) tileEntity : null;
    }

    @Override
    public final IGregTechTileEntity getIGregTechTileEntity(int aX, int aY, int aZ) {
        TileEntity tileEntity = getTileEntity(aX, aY, aZ);
        return tileEntity instanceof IGregTechTileEntity ? (IGregTechTileEntity) tileEntity : null;
    }

    @Override
    public final IGregTechTileEntity getIGregTechTileEntityOffset(int aX, int aY, int aZ) {
        TileEntity tileEntity = getTileEntityOffset(aX, aY, aZ);
        return tileEntity instanceof IGregTechTileEntity ? (IGregTechTileEntity) tileEntity : null;
    }

    @Override
    public final IGregTechTileEntity getIGregTechTileEntityAtSide(byte aSide) {
        return getIGregTechTileEntityAtSideAndDistance(aSide, 1);
    }

    @Override
    public final IGregTechTileEntity getIGregTechTileEntityAtSideAndDistance(byte aSide, int aDistance) {
        TileEntity tileEntity = getTileEntityAtSideAndDistance(aSide, aDistance);
        return tileEntity instanceof IGregTechTileEntity ? (IGregTechTileEntity) tileEntity : null;
    }

    @Override
    public final Block getBlock(int aX, int aY, int aZ) {
        M.func_181079_c(aX, aY, aZ);
        if (ignoreUnloadedChunks && crossedChunkBorder(aX, aZ) && !field_145850_b.func_175667_e(M))
            return Blocks.field_150350_a;
        return field_145850_b.func_180495_p(M).func_177230_c();
    }

    @Override
    public final byte getMetaID(int aX, int aY, int aZ) {
        M.func_181079_c(aX, aY, aZ);
        if (ignoreUnloadedChunks && crossedChunkBorder(aX, aZ) && !field_145850_b.func_175667_e(M))
            return 0;
        IBlockState blockState = field_145850_b.func_180495_p(M);
        return (byte) blockState.func_177230_c().func_176201_c(blockState);
    }

    @Override
    public final byte getLightLevel(int aX, int aY, int aZ) {
        M.func_181079_c(aX, aY, aZ);
        if (ignoreUnloadedChunks && crossedChunkBorder(aX, aZ) && !field_145850_b.func_175667_e(M))
            return 0;
        return (byte) field_145850_b.func_175699_k(M);
    }

    @Override
    public final boolean getSky(int aX, int aY, int aZ) {
        M.func_181079_c(aX, aY, aZ);
        if (ignoreUnloadedChunks && crossedChunkBorder(aX, aZ) && !field_145850_b.func_175667_e(M))
            return false;
        return field_145850_b.func_175678_i(M);
    }

    @Override
    public final boolean getOpacity(int aX, int aY, int aZ) {
        M.func_181079_c(aX, aY, aZ);
        if (ignoreUnloadedChunks && crossedChunkBorder(aX, aZ) && !field_145850_b.func_175667_e(M))
            return false;
        IBlockState blockState = field_145850_b.func_180495_p(M);
        return blockState.func_185914_p();
    }

    @Override
    public final boolean getAir(int aX, int aY, int aZ) {
        M.func_181079_c(aX, aY, aZ);
        if (ignoreUnloadedChunks && crossedChunkBorder(aX, aZ) && !field_145850_b.func_175667_e(M))
            return false;
        return field_145850_b.func_175623_d(M);
    }

    @Override
    public final TileEntity getTileEntity(int aX, int aY, int aZ) {
        M.func_181079_c(aX, aY, aZ);
        if (ignoreUnloadedChunks && crossedChunkBorder(aX, aZ) && !field_145850_b.func_175667_e(M))
            return null;
        return field_145850_b.func_175625_s(M);
    }

    @Override
    public final TileEntity getTileEntityAtSide(byte aSide) {
        EnumFacing side = EnumFacing.field_82609_l[aSide];
        M.func_181079_c(getXCoord() + side.func_82601_c(), getYCoord() + side.func_96559_d(), getZCoord() + side.func_82599_e());
        
        if (aSide < 0 || aSide >= 6 || mBufferedTileEntities[aSide] == this) return null;
        int tX = getOffsetX(aSide, 1), tY = getOffsetY(aSide, 1), tZ = getOffsetZ(aSide, 1);
        if (crossedChunkBorder(tX, tZ)) {
            mBufferedTileEntities[aSide] = null;
            if (ignoreUnloadedChunks && !field_145850_b.func_175667_e(M)) return null;
        }
        if (mBufferedTileEntities[aSide] == null) {
            mBufferedTileEntities[aSide] = field_145850_b.func_175625_s(M);
            if (mBufferedTileEntities[aSide] == null) {
                mBufferedTileEntities[aSide] = this;
                return null;
            }
            return mBufferedTileEntities[aSide];
        }
        if (mBufferedTileEntities[aSide].func_145837_r()) {
            mBufferedTileEntities[aSide] = null;
            return getTileEntityAtSide(aSide);
        }
        if (mBufferedTileEntities[aSide].func_174877_v().func_177958_n() == tX && mBufferedTileEntities[aSide].func_174877_v().func_177956_o() == tY && mBufferedTileEntities[aSide].func_174877_v().func_177952_p() == tZ) {
            return mBufferedTileEntities[aSide];
        }
        return null;
    }

    @Override
    public IBlockState getBlockState(BlockPos pos) {
        return field_145850_b.func_180495_p(pos);
    }

    @Override
    public boolean setBlockState(BlockPos pos, IBlockState state) {
        return field_145850_b.func_175656_a(pos, state);
    }

    @Override
    public boolean setBlockToAir(BlockPos pos) {
        return field_145850_b.func_175698_g(pos);
    }

    @Override
    public boolean isAir(BlockPos pos) {
        return field_145850_b.func_175623_d(pos);
    }

    @Override
    public int getOffsetX(byte aSide, int aMultiplier) {
        return getXCoord() + EnumFacing.field_82609_l[aSide].func_82601_c() * aMultiplier;
    }

    @Override
    public short getOffsetY(byte aSide, int aMultiplier) {
        return (short) (getYCoord() + EnumFacing.field_82609_l[aSide].func_96559_d() * aMultiplier);
    }

    @Override
    public int getOffsetZ(byte aSide, int aMultiplier) {
        return getZCoord() + EnumFacing.field_82609_l[aSide].func_82599_e() * aMultiplier;
    }


    @Override
    public NBTTagCompound func_189515_b(NBTTagCompound aNBT) {
        super.func_189515_b(aNBT);
        //isDead = true;
        return aNBT;
    }

    @Override
    public boolean isDead() {
        return isDead || isInvalidTileEntity();
    }

    @Override
    public void func_145829_t() {
        clearNullMarkersFromTileEntityBuffer();
        super.func_145829_t();
    }

    @Override
    public void func_145843_s() {
        clearNullMarkersFromTileEntityBuffer();
        super.func_145843_s();
    }

    @Override
    public void onChunkUnload() {
        clearNullMarkersFromTileEntityBuffer();
        super.onChunkUnload();
        isDead = true;
    }

    @Override
    public void func_73660_a() {
        // Well if the TileEntity gets ticked it is alive.
        isDead = false;
    }

    public final void onAdjacentBlockChange(int aX, int aY, int aZ) {
        clearNullMarkersFromTileEntityBuffer();
    }

    @Override
    public final void sendBlockEvent(byte aID, byte aValue) {
        NW.sendToAllAround(field_145850_b, new GT_Packet_Block_Event(getXCoord(),
                                                               (short) getYCoord(),
                                                               getZCoord(),
                                                               aID,
                                                               aValue),
                           getXCoord(),
                           getYCoord(),
                           getZCoord());
    }

    private boolean crossedChunkBorder(int aX, int aZ) {
        return aX >> 4 != getXCoord() >> 4 || aZ >> 4 != getZCoord() >> 4;
    }

    public final void setOnFire() {
        GT_Utility.setCoordsOnFire(field_145850_b, getXCoord(), getYCoord(), getZCoord(), false);
    }

    public final void setToFire() {
        field_145850_b.func_175656_a(func_174877_v(), Blocks.field_150480_ab.func_176223_P());
    }
    
    @Override 
    public void func_70296_d() {/* Do not do the super Function */} 
}
