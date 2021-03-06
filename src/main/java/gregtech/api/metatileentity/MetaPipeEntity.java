package gregtech.api.metatileentity;

import gregtech.api.GregTech_API;
import gregtech.api.enums.Materials;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.objects.GT_ItemStack;
import gregtech.api.util.GT_Config;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_Utility;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static gregtech.api.enums.GT_Values.GT;
import static gregtech.api.enums.GT_Values.V;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * <p/>
 * Extend this Class to add a new MetaPipe
 * Call the Constructor with the desired ID at the load-phase (not preload and also not postload!)
 * Implement the newMetaEntity-Method to return a new ready instance of your MetaTileEntity
 * <p/>
 * Call the Constructor like the following example inside the Load Phase, to register it.
 * "new GT_MetaTileEntity_E_Furnace(54, "GT_E_Furnace", "Automatic E-Furnace");"
 */
public abstract class MetaPipeEntity implements IMetaTileEntity {
    /**
     * The Inventory of the MetaTileEntity. Amount of Slots can be larger than 256. HAYO!
     */
    public final ItemStack[] mInventory;
    /**
     * This variable tells, which directions the Block is connected to. It is a Bitmask.
     */
    public byte mConnections = 0;
    /**
     * Only assigned for the MetaTileEntity in the List! Also only used to get the localized Name for the ItemStack and for getInvName.
     */
    public String mName;
    public boolean doTickProfilingInThisTick = true;
    /**
     * accessibility to this Field is no longer given, see below
     */
    private IGregTechTileEntity mBaseMetaTileEntity;

    /**
     * This registers your Machine at the List.
     * Use only ID's larger than 2048, because i reserved these ones.
     * See also the List in the API, as it has a Description containing all the reservations.
     *
     * @param aID the ID
     * @example for Constructor overload.
     * <p/>
     * public GT_MetaTileEntity_EBench(int aID, String mName, String mNameRegional) {
     * super(aID, mName, mNameRegional);
     * }
     */
    public MetaPipeEntity(int aID, String aBasicName, String aRegionalName, int aInvSlotCount) {
        if (GregTech_API.sPostloadStarted || !GregTech_API.sPreloadStarted)
            throw new IllegalAccessError("This Constructor has to be called in the load Phase");
        if (GregTech_API.METATILEENTITIES[aID] == null) {
            GregTech_API.METATILEENTITIES[aID] = this;
        } else {
            throw new IllegalArgumentException("MetaMachine-Slot Nr. " + aID + " is already occupied!");
        }
        mName = aBasicName.replaceAll(" ", "_").toLowerCase(Locale.ENGLISH);
        setBaseMetaTileEntity(new BaseMetaPipeEntity());
        getBaseMetaTileEntity().setMetaTileID((short) aID);
        GT_LanguageManager.addStringLocalization("gt.blockmachines." + mName + ".name", aRegionalName);
        mInventory = new ItemStack[aInvSlotCount];

        if (GT.isClientSide()) {
            ItemStack tStack = new ItemStack(GregTech_API.sBlockMachines, 1, aID);
            tStack.getItem().addInformation(tStack, null, new ArrayList<>(), true);
        }
    }

    /**
     * This is the normal Constructor.
     */
    public MetaPipeEntity(String aName, int aInvSlotCount) {
        mInventory = new ItemStack[aInvSlotCount];
        mName = aName;
    }

    /**
     * For Pipe Rendering
     */
    public abstract float getThickNess();

    public boolean isConductor() {
        return false;
    }

    public long getMaxVoltage() {
        return 0;
    }

    public long getMaxAmperage() {
        return 0;
    }

    public long getLossPerMeter() {
        return 0;
    }

    public Materials getCableMaterial() {
        return Materials.Air;
    }

    public Materials getInsulationMaterial() {
        return Materials.Air;
    }

    @Override
    public IGregTechTileEntity getBaseMetaTileEntity() {
        return mBaseMetaTileEntity;
    }

    @Override
    public void setBaseMetaTileEntity(IGregTechTileEntity aBaseMetaTileEntity) {
        if (mBaseMetaTileEntity != null && aBaseMetaTileEntity == null) {
            mBaseMetaTileEntity.getMetaTileEntity().inValidate();
            mBaseMetaTileEntity.setMetaTileEntity(null);
        }
        mBaseMetaTileEntity = aBaseMetaTileEntity;
        if (mBaseMetaTileEntity != null) {
            mBaseMetaTileEntity.setMetaTileEntity(this);
        }
    }

    @Override
    public ItemStack getStackForm(long aAmount) {
        return new ItemStack(GregTech_API.sBlockMachines, (int) aAmount, getBaseMetaTileEntity().getMetaTileID());
    }

    public boolean isCoverOnSide(BaseMetaPipeEntity aPipe, EntityLivingBase aEntity) {
        byte aSide = 6;
        double difference = aEntity.posY - (double) aPipe.getPos().getY();
        if (difference > 0.6 && difference < 0.99) {
            aSide = 1;
        }
        if (difference < -1.5 && difference > -1.99) {
            aSide = 0;
        }
        difference = aEntity.posZ - (double) aPipe.getPos().getZ();
        if (difference < -0.05 && difference > -0.4) {
            aSide = 2;
        }
        if (difference > 1.05 && difference < 1.4) {
            aSide = 3;
        }
        difference = aEntity.posX - (double) aPipe.getPos().getX();
        if (difference < -0.05 && difference > -0.4) {
            aSide = 4;
        }
        if (difference > 1.05 && difference < 1.4) {
            aSide = 5;
        }
        boolean tCovered = false;
        if (aSide < 6 && mBaseMetaTileEntity.getCoverIDAtSide(aSide) > 0) {
            tCovered = true;
        }
        if((mConnections & (byte)(Math.pow(2, aSide))) != 0){
        	tCovered = true;
        }
        //System.out.println("Cover: "+mBaseMetaTileEntity.getCoverIDAtSide(aSide));
        //toDo: filter cover ids that actually protect against temperature (rubber/plastic maybe?)
        return tCovered;
    }

    @Override
    public void onServerStart() {/*Do nothing*/}

    @Override
    public void onWorldSave(File aSaveDirectory) {/*Do nothing*/}

    @Override
    public void onWorldLoad(File aSaveDirectory) {/*Do nothing*/}

    @Override
    public void onConfigLoad(GT_Config aConfig) {/*Do nothing*/}

    @Override
    public void setItemNBT(NBTTagCompound aNBT) {/*Do nothing*/}

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(TextureMap aBlockIconRegister) {/*Do nothing*/}

    @Override
    public boolean allowCoverOnSide(byte aSide, GT_ItemStack aCoverID) {
        return true;
    }

    @Override
    public void onScrewdriverRightClick(byte aSide, EntityPlayer aPlayer, float aX, float aY, float aZ) {/*Do nothing*/}

    @Override
    public boolean onWrenchRightClick(byte aSide, byte aWrenchingSide, EntityPlayer aPlayer, float aX, float aY, float aZ) {
        return false;
    }

    @Override
    public void onExplosion() {/*Do nothing*/}

    @Override
    public void onFirstTick(IGregTechTileEntity aBaseMetaTileEntity) {/*Do nothing*/}

    @Override
    public void onPreTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick) {/*Do nothing*/}

    @Override
    public void onPostTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick) {/*Do nothing*/}

    @Override
    public void inValidate() {/*Do nothing*/}

    @Override
    public void onRemoval() {/*Do nothing*/}

    @Override
    public void initDefaultModes(NBTTagCompound aNBT) {/*Do nothing*/}

    /**
     * When a GUI is opened
     */
    public void onOpenGUI() {/*Do nothing*/}

    /**
     * When a GUI is closed
     */
    public void onCloseGUI() {/*Do nothing*/}

    /**
     * a Player rightclicks the Machine
     * Sneaky rightclicks are not getting passed to this!
     */
    @Override
    public boolean onRightclick(IGregTechTileEntity aBaseMetaTileEntity, EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ, EnumHand hand) {
        return false;
    }

    @Override
    public void onLeftclick(IGregTechTileEntity aBaseMetaTileEntity, EntityPlayer aPlayer, EnumHand hand) {/*Do nothing*/}

    @Override
    public void onValueUpdate(byte aValue) {/*Do nothing*/}

    @Override
    public byte getUpdateData() {
        return 0;
    }

    @Override
    public void doSound(byte aIndex, double aX, double aY, double aZ) {/*Do nothing*/}

    @Override
    public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {/*Do nothing*/}

    @Override
    public void stopSoundLoop(byte aValue, double aX, double aY, double aZ) {/*Do nothing*/}

    @Override
    public final void sendSound(byte aIndex) {
        if (!getBaseMetaTileEntity().hasMufflerUpgrade()) getBaseMetaTileEntity().sendBlockEvent((byte) 4, aIndex);
    }

    @Override
    public final void sendLoopStart(byte aIndex) {
        if (!getBaseMetaTileEntity().hasMufflerUpgrade()) getBaseMetaTileEntity().sendBlockEvent((byte) 5, aIndex);
    }

    @Override
    public final void sendLoopEnd(byte aIndex) {
        if (!getBaseMetaTileEntity().hasMufflerUpgrade()) getBaseMetaTileEntity().sendBlockEvent((byte) 6, aIndex);
    }

    @Override
    public boolean isFacingValid(byte aFacing) {
        return false;
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public boolean isValidSlot(int aIndex) {
        return true;
    }

    @Override
    public boolean setStackToZeroInsteadOfNull(int aIndex) {
        return false;
    }

    @Override
    public ArrayList<String> getSpecialDebugInfo(IGregTechTileEntity aBaseMetaTileEntity, EntityPlayer aPlayer, int aLogLevel, ArrayList<String> aList) {
        return aList;
    }

    @Override
    public boolean isLiquidInput(byte aSide) {
        return true;
    }

    @Override
    public boolean isLiquidOutput(byte aSide) {
        return true;
    }

    /**
     * gets the contained Liquid
     */
    @Override
    public FluidStack getFluid() {
        return null;
    }

    /**
     * tries to fill this Tank
     */
    @Override
    public int fill(FluidStack resource, boolean doFill) {
        return 0;
    }

    /**
     * tries to empty this Tank
     */
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return null;
    }

    /**
     * Tank pressure
     */
    public int getTankPressure() {
        return 0;
    }

    /**
     * Liquid Capacity
     */
    @Override
    public int getCapacity() {
        return 0;
    }

    /**
     * Progress this machine has already made
     */
    public int getProgresstime() {
        return 0;
    }

    /**
     * Progress this Machine has to do to produce something
     */
    public int maxProgresstime() {
        return 0;
    }

    /**
     * Increases the Progress, returns the overflown Progress.
     */
    public int increaseProgress(int aProgress) {
        return 0;
    }

    @Override
    public void onMachineBlockUpdate() {/*Do nothing*/}

    @Override
    public void receiveClientEvent(byte aEventID, byte aValue) {/*Do nothing*/}

    @Override
    public boolean isSimpleMachine() {
        return false;
    }

    @Override
    public byte getComparatorValue(byte aSide) {
        return 0;
    }

    @Override
    public boolean acceptsRotationalEnergy(byte aSide) {
        return false;
    }

    @Override
    public boolean injectRotationalEnergy(byte aSide, long aSpeed, long aEnergy) {
        return false;
    }

    @Override
    public boolean isGivingInformation() {
        return false;
    }

    @Override
    public String[] getInfoData() {
        return new String[]{};
    }

    public boolean isDigitalChest() {
        return false;
    }

    public ItemStack[] getStoredItemData() {
        return null;
    }

    public void setItemCount(int aCount) {/*Do nothing*/}

    public int getMaxItemCount() {
        return 0;
    }

    @Override
    public int getSizeInventory() {
        return mInventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int aIndex) {
        if (aIndex >= 0 && aIndex < mInventory.length) return mInventory[aIndex];
        return null;
    }

    @Override
    public void setInventorySlotContents(int aIndex, ItemStack aStack) {
        if (aIndex >= 0 && aIndex < mInventory.length) mInventory[aIndex] = aStack;
    }

   

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isItemValidForSlot(int aIndex, ItemStack aStack) {
        return getBaseMetaTileEntity().isValidSlot(aIndex);
    }

    @Override
    public ItemStack decrStackSize(int aIndex, int aAmount) {
        ItemStack tStack = getStackInSlot(aIndex), rStack = GT_Utility.copy(tStack);
        if (tStack != null) {
            if (tStack.stackSize <= aAmount) {
                if (setStackToZeroInsteadOfNull(aIndex)) tStack.stackSize = 0;
                else setInventorySlotContents(aIndex, null);
            } else {
                rStack = tStack.splitStack(aAmount);
                if (tStack.stackSize == 0 && !setStackToZeroInsteadOfNull(aIndex))
                    setInventorySlotContents(aIndex, null);
            }
        }
        return rStack;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing aSide) {
        ArrayList<Integer> tList = new ArrayList<Integer>();
        IGregTechTileEntity tTileEntity = getBaseMetaTileEntity();
        boolean tSkip = tTileEntity.getCoverBehaviorAtSide((byte) aSide.getIndex()).letsItemsIn((byte) aSide.getIndex(), tTileEntity.getCoverIDAtSide((byte) aSide.getIndex()), tTileEntity.getCoverDataAtSide((byte) aSide.getIndex()), -2, tTileEntity) || tTileEntity.getCoverBehaviorAtSide((byte) aSide.getIndex()).letsItemsOut((byte) aSide.getIndex(), tTileEntity.getCoverIDAtSide((byte) aSide.getIndex()), tTileEntity.getCoverDataAtSide((byte) aSide.getIndex()), -2, tTileEntity);
        for (int i = 0; i < getSizeInventory(); i++)
            if (isValidSlot(i) && (tSkip || tTileEntity.getCoverBehaviorAtSide((byte) aSide.getIndex()).letsItemsOut((byte) aSide.getIndex(), tTileEntity.getCoverIDAtSide((byte) aSide.getIndex()), tTileEntity.getCoverDataAtSide((byte) aSide.getIndex()), i, tTileEntity) || tTileEntity.getCoverBehaviorAtSide((byte) aSide.getIndex()).letsItemsIn((byte) aSide.getIndex(), tTileEntity.getCoverIDAtSide((byte) aSide.getIndex()), tTileEntity.getCoverDataAtSide((byte) aSide.getIndex()), i, tTileEntity)))
                tList.add(i);
        int[] rArray = new int[tList.size()];
        for (int i = 0; i < rArray.length; i++) rArray[i] = tList.get(i);
        return rArray;
    }
    
    

    @Override
    public boolean canInsertItem(int aIndex, ItemStack aStack, EnumFacing aSide) {
        return isValidSlot(aIndex) && aStack != null && aIndex < mInventory.length && (mInventory[aIndex] == null || GT_Utility.areStacksEqual(aStack, mInventory[aIndex])) && allowPutStack(getBaseMetaTileEntity(), aIndex, (byte) aSide.getIndex(), aStack);
    }

    @Override
    public boolean canExtractItem(int aIndex, ItemStack aStack, EnumFacing aSide) {
        return isValidSlot(aIndex) && aStack != null && aIndex < mInventory.length && allowPullStack(getBaseMetaTileEntity(), aIndex, (byte) aSide.getIndex(), aStack);
    }

    @Override
    public boolean canFill(EnumFacing aSide, Fluid aFluid) {
        return fill(aSide, new FluidStack(aFluid, 1), false) == 1;
    }

    @Override
    public boolean canDrain(EnumFacing aSide, Fluid aFluid) {
        return drain(aSide, new FluidStack(aFluid, 1), false) != null;
    }

    @Override
    public FluidTankInfo[] getTankInfo(EnumFacing aSide) {
        if (getCapacity() <= 0 && !getBaseMetaTileEntity().hasSteamEngineUpgrade()) return new FluidTankInfo[]{};
        return new FluidTankInfo[]{getInfo()};
    }

    public int fill_default(EnumFacing aSide, FluidStack aFluid, boolean doFill) {
        return fill(aFluid, doFill);
    }

    @Override
    public int fill(EnumFacing aSide, FluidStack aFluid, boolean doFill) {
        return fill_default(aSide, aFluid, doFill);
    }

    @Override
    public FluidStack drain(EnumFacing aSide, FluidStack aFluid, boolean doDrain) {
        if (getFluid() != null && aFluid != null && getFluid().isFluidEqual(aFluid))
            return drain(aFluid.amount, doDrain);
        return null;
    }

    @Override
    public FluidStack drain(EnumFacing aSide, int maxDrain, boolean doDrain) {
        return drain(maxDrain, doDrain);
    }

    @Override
    public int getFluidAmount() {
        return 0;
    }

    @Override
    public FluidTankInfo getInfo() {
        return new FluidTankInfo(this);
    }

    @Override
    public String getMetaName() {
        return mName;
    }
    
    @Override
    public boolean doTickProfilingMessageDuringThisTick() {
        return doTickProfilingInThisTick;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return false;
    }

    @Override
    public boolean connectsToItemPipe(byte aSide) {
        return false;
    }

    @Override
    public Object getServerGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
        return null;
    }

    @Override
    public Object getClientGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
        return null;
    }

    @Override
    public float getExplosionResistance(byte aSide) {
        return 10.0F;
    }

    @Override
    public ItemStack[] getRealInventory() {
        return mInventory;
    }

    @Override
    public void markDirty() {
        //
    }

    @Override
    public void onColorChangeServer(byte aColor) {
        //
    }

    @Override
    public void onColorChangeClient(byte aColor) {
        //
    }

    public long injectEnergyUnits(byte aSide, long aVoltage, long aAmperage) {
        return 0;
    }

    @Override
    public void doExplosion(long aExplosionPower) {
        float tStrength = aExplosionPower < V[0] ? 1.0F : aExplosionPower < V[1] ? 2.0F : aExplosionPower < V[2] ? 3.0F : aExplosionPower < V[3] ? 4.0F : aExplosionPower < V[4] ? 5.0F : aExplosionPower < V[4] * 2 ? 6.0F : aExplosionPower < V[5] ? 7.0F : aExplosionPower < V[6] ? 8.0F : aExplosionPower < V[7] ? 9.0F : 10.0F;
        int tX = getBaseMetaTileEntity().getXCoord(), tY = getBaseMetaTileEntity().getYCoord(), tZ = getBaseMetaTileEntity().getZCoord();
        World tWorld = getBaseMetaTileEntity().getWorldObj();
        tWorld.setBlockToAir(new BlockPos(tX, tY, tZ));
        if (GregTech_API.sMachineExplosions)
            tWorld.createExplosion(null, tX + 0.5, tY + 0.5, tZ + 0.5, tStrength, true);
    }

    @Override
    public int getLightOpacity() {
        return 0;
    }

    @Override
    public void addCollisionBoxesToList(World aWorld, int aX, int aY, int aZ, AxisAlignedBB inputAABB, List<AxisAlignedBB> outputAABB, Entity collider) {
        AxisAlignedBB axisalignedbb1 = getCollisionBoundingBoxFromPool(aWorld, aX, aY, aZ);
        if (axisalignedbb1 != null && inputAABB.intersectsWith(axisalignedbb1)) outputAABB.add(axisalignedbb1);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World aWorld, int aX, int aY, int aZ) {
        return new AxisAlignedBB(aX, aY, aZ, aX + 1, aY + 1, aZ + 1);
    }

    @Override
    public void onEntityCollidedWithBlock(World aWorld, int aX, int aY, int aZ, Entity collider) {
        //
    }

    @Override
    public void onCreated(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
        //
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public String getName() {
        return getMetaName();
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString(getName());
    }

    @Override
    public void clear() {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = getStackInSlot(index);
        setInventorySlotContents(index, null);
        return stack;
    }

}