package gregtech.common.blocks;

import gregtech.api.GregTech_API;
import gregtech.api.enums.GT_Values;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.items.GT_Generic_Block;
import gregtech.api.util.GT_ItsNotMyFaultException;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class GT_Item_Machines extends ItemBlock {
    public GT_Item_Machines(Block par1) {
        super(par1);
        func_77656_e(0);
        func_77627_a(true);
        func_77637_a(GregTech_API.TAB_GREGTECH);
    }

    @Override
    public void func_77624_a(ItemStack aStack, EntityPlayer aPlayer, List<String> aList, boolean par4) {
        try {
            int tDamage = getDamage(aStack);
            if ((tDamage <= 0) || (tDamage >= GregTech_API.METATILEENTITIES.length)) {
                return;
            }
            TileEntity temp = GregTech_API.sBlockMachines.func_149915_a(aPlayer == null ? GT_Values.DW : aPlayer.field_70170_p, GregTech_API.METATILEENTITIES[tDamage] == null ? 0 : GregTech_API.METATILEENTITIES[tDamage].getTileEntityBaseType());
            if (temp != null) {
                temp.func_145834_a(aPlayer == null ? GT_Values.DW : aPlayer.field_70170_p);
                temp.func_174878_a(BlockPos.field_177992_a);
                if ((temp instanceof IGregTechTileEntity)) {
                    IGregTechTileEntity tTileEntity = (IGregTechTileEntity) temp;
                    tTileEntity.setInitialValuesAsNBT(new NBTTagCompound(), (short) tDamage);
                    if (tTileEntity.getDescription() != null) {
                        int i = 0;
                        for (String tDescription : tTileEntity.getDescription()) {
                            if (GT_Utility.isStringValid(tDescription)) {
                                if (tDescription.contains("%%%")) {
                                    String[] tString = tDescription.split("%%%");
                                    if (tString.length>=2) {
                                        aList.add(GT_LanguageManager.addStringLocalization("TileEntity_DESCRIPTION_" + tDamage + "_Index_" + i++, tString[0], !GregTech_API.sPostloadFinished )+" "+tString[1]);
                                    }
                                } else {
                                    String tTranslated = GT_LanguageManager.addStringLocalization("TileEntity_DESCRIPTION_" + tDamage + "_Index_" + i++, tDescription, !GregTech_API.sPostloadFinished );
                                    aList.add(tTranslated.equals("") ? tDescription : tTranslated);
                                }
                            } else i++;
                        }
                    }
                    if (tTileEntity.getEUCapacity() > 0L) {
                        if (tTileEntity.getInputVoltage() > 0L) {
                            aList.add(GT_LanguageManager.addStringLocalization("TileEntity_EUp_IN", "Voltage IN: ", !GregTech_API.sPostloadFinished) + TextFormatting.GREEN + tTileEntity.getInputVoltage() + " (" + GT_Values.VN[GT_Utility.getTier(tTileEntity.getInputVoltage())] + ")" + TextFormatting.GRAY);
                        }
                        if (tTileEntity.getOutputVoltage() > 0L) {
                            aList.add(GT_LanguageManager.addStringLocalization("TileEntity_EUp_OUT", "Voltage OUT: ", !GregTech_API.sPostloadFinished) + TextFormatting.GREEN + tTileEntity.getOutputVoltage() + " (" + GT_Values.VN[GT_Utility.getTier(tTileEntity.getOutputVoltage())] + ")" + TextFormatting.GRAY);
                        }
                        if (tTileEntity.getOutputAmperage() > 1L) {
                            aList.add(GT_LanguageManager.addStringLocalization("TileEntity_EUp_AMOUNT", "Amperage: ", !GregTech_API.sPostloadFinished) + TextFormatting.YELLOW + tTileEntity.getOutputAmperage() + TextFormatting.GRAY);
                        }
                        aList.add(GT_LanguageManager.addStringLocalization("TileEntity_EUp_STORE", "Capacity: ", !GregTech_API.sPostloadFinished) + TextFormatting.BLUE + tTileEntity.getEUCapacity() + TextFormatting.GRAY);
                    }
                }
            }
            NBTTagCompound aNBT = aStack.func_77978_p();
            if (aNBT != null) {
                if (aNBT.func_74767_n("mMuffler")) {
                    aList.add(GT_LanguageManager.addStringLocalization("GT_TileEntity_MUFFLER", "has Muffler Upgrade", !GregTech_API.sPostloadFinished));
                }
                if (aNBT.func_74767_n("mSteamConverter")) {
                    aList.add(GT_LanguageManager.addStringLocalization("GT_TileEntity_STEAMCONVERTER", "has Steam Upgrade", !GregTech_API.sPostloadFinished));
                }
                int tAmount = 0;
                if ((tAmount = aNBT.func_74771_c("mSteamTanks")) > 0) {
                    aList.add(tAmount + " " + GT_LanguageManager.addStringLocalization("GT_TileEntity_STEAMTANKS", "Steam Tank Upgrades", !GregTech_API.sPostloadFinished));
                }
            }
        } catch (Throwable e) {
            e.printStackTrace(GT_Log.err);
        }
    }

    @Override
    public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        return EnumActionResult.PASS;
    }

    @Override
    public String func_77653_i(ItemStack stack) {
        return GT_LanguageManager.getTranslation(func_77667_c(stack) + ".name");
    }

    @Override
    public String func_77667_c(ItemStack aStack) {
        short tDamage = (short) getDamage(aStack);
        if ((tDamage < 0) || (tDamage >= GregTech_API.METATILEENTITIES.length)) {
            return "";
        }
        if (GregTech_API.METATILEENTITIES[tDamage] != null) {
            return func_77658_a() + "." + GregTech_API.METATILEENTITIES[tDamage].getMetaName();
        }
        return "";
    }

    @Override
    public void func_77622_d(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
        super.func_77622_d(aStack, aWorld, aPlayer);
        short tDamage = (short) getDamage(aStack);
        if ((tDamage < 0) || ((tDamage >= GregTech_API.METATILEENTITIES.length) && (GregTech_API.METATILEENTITIES[tDamage] != null))) {
            GregTech_API.METATILEENTITIES[tDamage].onCreated(aStack, aWorld, aPlayer);
        }
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
        short tDamage = (short) getDamage(stack);
        System.out.println("Place block " + field_150939_a + " " + tDamage);
        if (tDamage > 0) {
            if (GregTech_API.METATILEENTITIES[tDamage] == null) {
                return false;
            }
            int tMetaData = GregTech_API.METATILEENTITIES[tDamage].getTileEntityBaseType();
            if (!world.func_175656_a(pos, field_150939_a.func_176203_a(tMetaData))) {
                return false;
            }
            IBlockState placed = world.func_180495_p(pos);
            if (placed != field_150939_a.func_176203_a(tMetaData)) {
                throw new GT_ItsNotMyFaultException("Failed to place Block even though World.setBlockState returned true. It COULD be MCPC/Bukkit causing that. In case you really have that installed, don't report this Bug to me, I don't know how to fix it.");
            }
            if (placed.func_177229_b(GT_Generic_Block.METADATA) != tMetaData) {
                throw new GT_ItsNotMyFaultException("Failed to set the MetaValue of the Block even though World.setBlock returned true. It COULD be MCPC/Bukkit causing that. In case you really have that installed, don't report this Bug to me, I don't know how to fix it.");
            }
            IGregTechTileEntity tTileEntity = (IGregTechTileEntity) world.func_175625_s(pos);
            if (tTileEntity != null) {
                tTileEntity.setInitialValuesAsNBT(tTileEntity.isServerSide() ? stack.func_77978_p() : null, tDamage);
                if (player != null) {
                    tTileEntity.setOwnerName(player.func_70005_c_());
                }
                tTileEntity.getMetaTileEntity().initDefaultModes(stack.func_77978_p());
            }
        } else if (!world.func_175656_a(pos, field_150939_a.func_176203_a(tDamage))) {
            return false;
        }
        IBlockState state = world.func_180495_p(pos);
        if (state.func_177230_c() == field_150939_a) {
            this.field_150939_a.func_180633_a(world, pos, state, player, stack);
        }
        return true;
    }

}
