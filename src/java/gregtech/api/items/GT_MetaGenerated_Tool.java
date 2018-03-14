package gregtech.api.items;

import forestry.api.arboriculture.IToolGrafter;
import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.enchants.Enchantment_Radioactivity;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.TC_Aspects.TC_AspectStack;
import gregtech.api.interfaces.IDamagableItem;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.IToolStats;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * This is an example on how you can create a Tool ItemStack, in this case a Bismuth Wrench:
 * GT_MetaGenerated_Tool.sInstances.get("gt.metatool.01").getToolWithStats(GT_MetaGenerated_Tool_01.WRENCH, 1, Materials.Bismuth, Materials.Bismuth, null);
 */
@Optional.InterfaceList(value = {
        @Optional.Interface(iface = "forestry.api.arboriculture.IToolGrafter", modid = GT_Values.MOD_ID_FR)
        //@Optional.Interface(iface = "mods.railcraft.api.core.items.IToolCrowbar", modid = MOD_ID_RC),
        //@Optional.Interface(iface = "buildcraft.api.tools.IToolWrench", modid = "BuildCraft"),
        //@Optional.Interface(iface = "crazypants.enderio.api.tool.ITool", modid = "EnderIO")
})
public abstract class GT_MetaGenerated_Tool extends GT_MetaBase_Item implements IDamagableItem, IToolGrafter {//, IToolGrafter, IToolCrowbar, IToolWrench, ITool {
    /**
     * All instances of this Item Class are listed here.
     * This gets used to register the Renderer to all Items of this Type, if useStandardMetaItemRenderer() returns true.
     * <p/>
     * You can also use the unlocalized Name gotten from getUnlocalizedName() as Key if you want to get a specific Item.
     */
    public static final HashMap<String, GT_MetaGenerated_Tool> sInstances = new HashMap<>();

	/* ---------- CONSTRUCTOR AND MEMBER VARIABLES ---------- */

    public final HashMap<Short, IToolStats> mToolStats = new HashMap<>();

    /**
     * Creates the Item using these Parameters.
     *
     * @param aUnlocalized The Unlocalized Name of this Item.
     */
    public GT_MetaGenerated_Tool(String aUnlocalized) {
        super(aUnlocalized);
        GT_ModHandler.registerBoxableItemToToolBox(this);
        func_77637_a(GregTech_API.TAB_GREGTECH);
        func_77625_d(1);
        sInstances.put(func_77658_a(), this);
    }

	/* ---------- FOR ADDING CUSTOM ITEMS INTO THE REMAINING 766 RANGE ---------- */

    public static final Materials getPrimaryMaterial(ItemStack aStack) {
        NBTTagCompound aNBT = aStack.func_77978_p();
        if (aNBT != null) {
            aNBT = aNBT.func_74775_l("GT.ToolStats");
            if (aNBT != null) return Materials.getRealMaterial(aNBT.func_74779_i("PrimaryMaterial"));
        }
        return Materials._NULL;
    }

    public static final Materials getSecondaryMaterial(ItemStack aStack) {
        NBTTagCompound aNBT = aStack.func_77978_p();
        if (aNBT != null) {
            aNBT = aNBT.func_74775_l("GT.ToolStats");
            if (aNBT != null) return Materials.getRealMaterial(aNBT.func_74779_i("SecondaryMaterial"));
        }
        return Materials._NULL;
    }

	/* ---------- INTERNAL OVERRIDES ---------- */

    public static final long getToolMaxDamage(ItemStack aStack) {
        NBTTagCompound aNBT = aStack.func_77978_p();
        if (aNBT != null) {
            aNBT = aNBT.func_74775_l("GT.ToolStats");
            if (aNBT != null) return aNBT.func_74763_f("MaxDamage");
        }
        return 0;
    }

    public static final long getToolDamage(ItemStack aStack) {
        NBTTagCompound aNBT = aStack.func_77978_p();
        if (aNBT != null) {
            aNBT = aNBT.func_74775_l("GT.ToolStats");
            if (aNBT != null) return aNBT.func_74763_f("Damage");
        }
        return 0;
    }

    public static final boolean setToolDamage(ItemStack aStack, long aDamage) {
        NBTTagCompound aNBT = aStack.func_77978_p();
        if (aNBT != null) {
            aNBT = aNBT.func_74775_l("GT.ToolStats");
            if (aNBT != null) {
                aNBT.func_74772_a("Damage", aDamage);
                return true;
            }
        }
        return false;
    }

    /**
     * This adds a Custom Item to the ending Range.
     *
     * @param aID                     The Id of the assigned Tool Class [0 - 32765] (only even Numbers allowed! Uneven ID's are empty electric Items)
     * @param aEnglish                The Default Localized Name of the created Item
     * @param aToolTip                The Default ToolTip of the created Item, you can also insert null for having no ToolTip
     * @param aToolStats              The Food Value of this Item. Can be null as well.
     * @param aOreDictNamesAndAspects The OreDict Names you want to give the Item. Also used to assign Thaumcraft Aspects.
     * @return An ItemStack containing the newly created Item, but without specific Stats.
     */
    public final ItemStack addTool(int aID, String aEnglish, String aToolTip, IToolStats aToolStats, Object... aOreDictNamesAndAspects) {
        if (aToolTip == null) aToolTip = "";
        if (aID >= 0 && aID < 32766 && aID % 2 == 0) {
            GT_LanguageManager.addStringLocalization(func_77658_a() + "." + aID + ".name", aEnglish);
            GT_LanguageManager.addStringLocalization(func_77658_a() + "." + aID + ".tooltip", aToolTip);
            GT_LanguageManager.addStringLocalization(func_77658_a() + "." + (aID + 1) + ".name", aEnglish + " (Empty)");
            GT_LanguageManager.addStringLocalization(func_77658_a() + "." + (aID + 1) + ".tooltip", "You need to recharge it");
            mToolStats.put((short) aID, aToolStats);
            mToolStats.put((short) (aID + 1), aToolStats);
            aToolStats.onStatsAddedToTool(this, aID);
            ItemStack rStack = new ItemStack(this, 1, aID);
            List<TC_AspectStack> tAspects = new ArrayList<TC_AspectStack>();
            for (Object tOreDictNameOrAspect : aOreDictNamesAndAspects) {
                if (tOreDictNameOrAspect instanceof TC_AspectStack)
                    ((TC_AspectStack) tOreDictNameOrAspect).addToAspectList(tAspects);
                else
                    GT_OreDictUnificator.registerOre(tOreDictNameOrAspect, rStack);
            }
            if (GregTech_API.sThaumcraftCompat != null)
                GregTech_API.sThaumcraftCompat.registerThaumcraftAspectsToItem(rStack, tAspects, false);
            return rStack;
        }
        return null;
    }

    /**
     * This Function gets an ItemStack Version of this Tool
     *
     * @param aToolID            the ID of the Tool Class
     * @param aAmount            Amount of Items (well normally you only need 1)
     * @param aPrimaryMaterial   Primary Material of this Tool
     * @param aSecondaryMaterial Secondary (Rod/Handle) Material of this Tool
     * @param aElectricArray     The Electric Stats of this Tool (or null if not electric)
     */
    public final ItemStack getToolWithStats(int aToolID, int aAmount, Materials aPrimaryMaterial, Materials aSecondaryMaterial, long[] aElectricArray) {
        ItemStack rStack = new ItemStack(this, aAmount, aToolID);
        IToolStats tToolStats = getToolStats(rStack);
        if (tToolStats != null) {
            NBTTagCompound tMainNBT = new NBTTagCompound(), tToolNBT = new NBTTagCompound();
            if (aPrimaryMaterial != null) {
                tToolNBT.func_74778_a("PrimaryMaterial", aPrimaryMaterial.mName);
                tToolNBT.func_74772_a("MaxDamage", 100L * (long) (aPrimaryMaterial.mDurability * tToolStats.getMaxDurabilityMultiplier()));
            }
            if (aSecondaryMaterial != null) tToolNBT.func_74778_a("SecondaryMaterial", aSecondaryMaterial.mName);

            if (aElectricArray != null) {
                tToolNBT.func_74757_a("Electric", true);
                tToolNBT.func_74772_a("MaxCharge", aElectricArray[0]);
                tToolNBT.func_74772_a("Voltage", aElectricArray[1]);
                tToolNBT.func_74772_a("Tier", aElectricArray[2]);
                tToolNBT.func_74772_a("SpecialData", aElectricArray[3]);
            }

            tMainNBT.func_74782_a("GT.ToolStats", tToolNBT);
            rStack.func_77982_d(tMainNBT);
        }
        isItemStackUsable(rStack);
        return rStack;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
        IToolStats toolStats = getToolStats(stack);
        return toolStats != null && toolStats.isMinableBlock(state);
    }

    /**
     * Called by the Block Harvesting Event within the GT_Proxy
     */
    public void onHarvestBlockEvent(ArrayList<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, IBlockState aBlock, BlockPos pos, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
        IToolStats tStats = getToolStats(aStack);
        if (isItemStackUsable(aStack) && func_150893_a(aStack, aBlock) > 0.0F)
            doDamage(aStack, tStats.convertBlockDrops(aDrops, aStack, aPlayer, aBlock, pos, aFortune, aSilkTouch, aEvent) * tStats.getToolDamagePerDropConversion());
    }

    @Override
    public boolean onBlockStartBreak(ItemStack aStack, BlockPos blockPos, EntityPlayer aPlayer) {
        if(aPlayer.field_70170_p.field_72995_K){
            return false;
        }
        IToolStats tStats = getToolStats(aStack);
        IBlockState aBlock = aPlayer.field_70170_p.func_180495_p(blockPos);
        if (tStats.isChainsaw()&&(aBlock instanceof IShearable))
        {
            IShearable target = (IShearable)aBlock;
            if ((target.isShearable(aStack, aPlayer.field_70170_p, blockPos)))
            {
                List<ItemStack> drops = target.onSheared(aStack, aPlayer.field_70170_p, blockPos, EnchantmentHelper.func_77506_a(Enchantment.func_180305_b("fortune"), aStack));
                for (ItemStack stack : drops)
                {
                    float f = 0.7F;
                    double d = field_77697_d.nextFloat() * f + (1.0F - f) * 0.5D;
                    double d1 = field_77697_d.nextFloat() * f + (1.0F - f) * 0.5D;
                    double d2 = field_77697_d.nextFloat() * f + (1.0F - f) * 0.5D;
                    EntityItem entityitem = new EntityItem(aPlayer.field_70170_p,
                            blockPos.func_177958_n() + d,
                            blockPos.func_177956_o() + d1,
                            blockPos.func_177952_p() + d2,
                            stack);
                    entityitem.func_174867_a(10);
                    aPlayer.field_70170_p.func_72838_d(entityitem);
                }
                //aPlayer.addStat(net.minecraft.stats.StatList.mine[Block.getIdFromBlock(aBlock.getBlock())], 1);
                func_179218_a(aStack, aPlayer.field_70170_p, aBlock, blockPos, aPlayer);
            }
            return false;
        }
        return super.onBlockStartBreak(aStack, blockPos, aPlayer);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
        IToolStats tStats = getToolStats(aStack);
        if (tStats == null || !isItemStackUsable(aStack)) return true;
        GT_Utility.doSoundAtClient(tStats.getEntityHitSound(), 1, 1.0F);
        if (super.onLeftClickEntity(aStack, aPlayer, aEntity)) return true;
        if (aEntity.func_70075_an() && !aEntity.func_85031_j(aPlayer)) {
            float tMagicDamage = tStats.getMagicDamageAgainstEntity(aEntity instanceof EntityLivingBase ? EnchantmentHelper.func_152377_a(aStack, ((EntityLivingBase) aEntity).func_70668_bt()) : 0.0F, aEntity, aStack, aPlayer);
            float tDamage = tStats.getNormalDamageAgainstEntity((float) aPlayer.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() + getToolCombatDamage(aStack), aEntity, aStack, aPlayer);
            if (tDamage + tMagicDamage > 0.0F) {
                boolean tCriticalHit = aPlayer.field_70143_R > 0.0F &&
                        !aPlayer.field_70122_E && !aPlayer.func_70617_f_() &&
                        !aPlayer.func_70090_H() &&
                        !aPlayer.func_70644_a(MobEffects.field_76440_q) &&
                        aPlayer.func_184187_bx() == null && aEntity instanceof EntityLivingBase;
                if (tCriticalHit && tDamage > 0.0F) tDamage *= 1.5F;
                tDamage += tMagicDamage;
                if (aEntity.func_70097_a(tStats.getDamageSource(aPlayer, aEntity), tDamage)) {
                    if (aEntity instanceof EntityLivingBase)
                        aEntity.func_70015_d(EnchantmentHelper.func_90036_a(aPlayer) * 4);
                    int tKnockcack = (aPlayer.func_70051_ag() ? 1 : 0) + (aEntity instanceof EntityLivingBase ? EnchantmentHelper.func_77501_a((EntityLivingBase) aEntity) : 0);
                    if (tKnockcack > 0) {
                        aEntity.func_70024_g(-MathHelper.func_76126_a(aPlayer.field_70177_z * (float) Math.PI / 180.0F) * tKnockcack * 0.5F, 0.1D, MathHelper.func_76134_b(aPlayer.field_70177_z * (float) Math.PI / 180.0F) * tKnockcack * 0.5F);
                        aPlayer.field_70159_w *= 0.6D;
                        aPlayer.field_70179_y *= 0.6D;
                        aPlayer.func_70031_b(false);
                    }
                    if (tCriticalHit) aPlayer.func_71009_b(aEntity);
                    if (tMagicDamage > 0.0F) aPlayer.func_71047_c(aEntity);
                    if (tDamage >= 18.0F) aPlayer.func_71029_a(AchievementList.field_187973_F);
                    aPlayer.func_130011_c(aEntity);
                    //if (aEntity instanceof EntityLivingBase)
                    //    EnchantmentHelper.((EntityLivingBase) aEntity, aPlayer);
                    //EnchantmentHelper.(aPlayer, aEntity);
                    if (aEntity instanceof EntityLivingBase)
                        aPlayer.func_71064_a(StatList.field_188111_y, Math.round(tDamage * 10.0F));
                    aEntity.field_70172_ad = Math.max(1, tStats.getHurtResistanceTime(aEntity.field_70172_ad, aEntity));
                    aPlayer.func_71020_j(0.3F);
                    doDamage(aStack, tStats.getToolDamagePerEntityAttack());
                }
            }
        }
        if (aStack.field_77994_a <= 0) {
            aPlayer.func_184201_a(EntityEquipmentSlot.MAINHAND, null);
        }
        return true;
    }

    @Override
    public ActionResult<ItemStack> func_77659_a(ItemStack aStack, World aWorld, EntityPlayer aPlayer, EnumHand hand) {
        IToolStats tStats = getToolStats(aStack);
        if (tStats != null && tStats.canBlock()) return ActionResult.newResult(EnumActionResult.PASS, aStack);
        return super.func_77659_a(aStack, aWorld, aPlayer, hand);
    }

    @Override
    public final int func_77626_a(ItemStack aStack) {
        return 72000;
    }

    @Override
    public final EnumAction func_77661_b(ItemStack aStack) {
        IToolStats tStats = getToolStats(aStack);
        if (tStats != null && tStats.canBlock()) return EnumAction.BLOCK;
        return EnumAction.NONE;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public final void func_150895_a(Item var1, CreativeTabs aCreativeTab, List<ItemStack> aList) {
        for (int i = 0; i < 32766; i += 2)
            if (getToolStats(new ItemStack(this, 1, i)) != null) {
                ItemStack tStack = new ItemStack(this, 1, i);
                isItemStackUsable(tStack);
                aList.add(tStack);
            }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public final void registerIcons(TextureMap aIconRegister) {}

    @Override
    public void addAdditionalToolTips(List<String> aList, ItemStack aStack, EntityPlayer aPlayer) {
        long tMaxDamage = getToolMaxDamage(aStack);
        Materials tMaterial = getPrimaryMaterial(aStack);
        IToolStats tStats = getToolStats(aStack);
        int tOffset = getElectricStats(aStack) != null ? 2 : 1;
        if (tStats != null) {
            String name = aStack.func_77977_a();
            if (name.equals("gt.metatool.01.170") || name.equals("gt.metatool.01.172") || name.equals("gt.metatool.01.174") || name.equals("gt.metatool.01.176")) {
                aList.add(tOffset, TextFormatting.WHITE + "Durability: " + TextFormatting.GREEN + (tMaxDamage - getToolDamage(aStack)) + " / " + tMaxDamage + TextFormatting.GRAY);
                aList.add(tOffset + 1, TextFormatting.WHITE + tMaterial.mDefaultLocalName + TextFormatting.YELLOW + " lvl " + getHarvestLevel(aStack, "") + TextFormatting.GRAY);
                aList.add(tOffset + 2, TextFormatting.WHITE + "Turbine Efficency: " + TextFormatting.BLUE + (50.0F + (10.0F * getToolCombatDamage(aStack))) + TextFormatting.GRAY);
                aList.add(tOffset + 3, TextFormatting.WHITE + "Optimal Steam flow: " + TextFormatting.LIGHT_PURPLE + Math.max(Float.MIN_NORMAL, tStats.getSpeedMultiplier() * getPrimaryMaterial(aStack).mToolSpeed * 1000) + TextFormatting.GRAY + "L/sec");
                aList.add(tOffset + 3, TextFormatting.WHITE + "Optimal Gas flow(EU burnvalue per tick): " + TextFormatting.LIGHT_PURPLE + Math.max(Float.MIN_NORMAL, tStats.getSpeedMultiplier() * getPrimaryMaterial(aStack).mToolSpeed * 50) + TextFormatting.GRAY + "EU/t");
                aList.add(tOffset + 3, TextFormatting.WHITE + "Optimal Plasma flow(Plasma energyvalue per tick): " + TextFormatting.LIGHT_PURPLE + Math.max(Float.MIN_NORMAL, tStats.getSpeedMultiplier() * getPrimaryMaterial(aStack).mToolSpeed * 2000) + TextFormatting.GRAY + "EU/t");

            } else {
                aList.add(tOffset + 0, TextFormatting.WHITE + "Durability: " + TextFormatting.GREEN + (tMaxDamage - getToolDamage(aStack)) + " / " + tMaxDamage + TextFormatting.GRAY);
                aList.add(tOffset + 1, TextFormatting.WHITE + tMaterial.mDefaultLocalName + TextFormatting.YELLOW + " lvl " + getHarvestLevel(aStack, "") + TextFormatting.GRAY);
                aList.add(tOffset + 2, TextFormatting.WHITE + "Attack Damage: " + TextFormatting.BLUE + getToolCombatDamage(aStack) + TextFormatting.GRAY);
                aList.add(tOffset + 3, TextFormatting.WHITE + "Mining Speed: " + TextFormatting.LIGHT_PURPLE + Math.max(Float.MIN_NORMAL, tStats.getSpeedMultiplier() * getPrimaryMaterial(aStack).mToolSpeed) + TextFormatting.GRAY);
                NBTTagCompound aNBT = aStack.func_77978_p();
                if (aNBT != null) {
                    aNBT = aNBT.func_74775_l("GT.ToolStats");
                    if (aNBT != null && aNBT.func_74764_b("Heat")){
                        int tHeat = aNBT.func_74762_e("Heat");
                        long tWorldTime = aPlayer.func_130014_f_().func_72820_D();
                        if(aNBT.func_74764_b("HeatTime")){
                            long tHeatTime = aNBT.func_74763_f("HeatTime");
                            if(tWorldTime>(tHeatTime+10)){
                                tHeat = (int) (tHeat - ((tWorldTime-tHeatTime)/10));
                                if(tHeat<300&&tHeat>-10000)tHeat=300;
                            }
                            aNBT.func_74772_a("HeatTime", tWorldTime);
                            if(tHeat>-10000)aNBT.func_74768_a("Heat", tHeat);
                        }

                        aList.add(tOffset + 3, TextFormatting.RED + "Heat: " + aNBT.func_74762_e("Heat")+" K" + TextFormatting.GRAY);
                    }
                }
            }
        }
    }

    @Override
    public Long[] getFluidContainerStats(ItemStack aStack) {
        return null;
    }

    @Override
    public Long[] getElectricStats(ItemStack aStack) {
        NBTTagCompound aNBT = aStack.func_77978_p();
        if (aNBT != null) {
            aNBT = aNBT.func_74775_l("GT.ToolStats");
            if (aNBT != null && aNBT.func_74767_n("Electric"))
                return new Long[]{aNBT.func_74763_f("MaxCharge"), aNBT.func_74763_f("Voltage"), aNBT.func_74763_f("Tier"), aNBT.func_74763_f("SpecialData")};
        }
        return null;
    }

    public float getToolCombatDamage(ItemStack aStack) {
        IToolStats tStats = getToolStats(aStack);
        if (tStats == null) return 0;
        return tStats.getBaseDamage() + getPrimaryMaterial(aStack).mToolQuality;
    }

    @Override
    public final boolean doDamageToItem(ItemStack aStack, int aVanillaDamage) {
        return doDamage(aStack, aVanillaDamage * 100);
    }

    public final boolean doDamage(ItemStack aStack, long aAmount) {
        if (!isItemStackUsable(aStack)) return false;
        Long[] tElectric = getElectricStats(aStack);
        if (tElectric == null) {
            long tNewDamage = getToolDamage(aStack) + aAmount;
            setToolDamage(aStack, tNewDamage);
            if (tNewDamage >= getToolMaxDamage(aStack)) {
                IToolStats tStats = getToolStats(aStack);
                if (tStats == null || GT_Utility.setStack(aStack, tStats.getBrokenItem(aStack)) == null) {
                    if (tStats != null) GT_Utility.doSoundAtClient(tStats.getBreakingSound(), 1, 1.0F);
                    if (aStack.field_77994_a > 0) aStack.field_77994_a--;

                }
            }
            return true;
        }
        if (use(aStack, (int) aAmount, null)) {
            if (java.util.concurrent.ThreadLocalRandom.current().nextInt(0, 25) == 0) {
                long tNewDamage = getToolDamage(aStack) + aAmount;
                setToolDamage(aStack, tNewDamage);
                if (tNewDamage >= getToolMaxDamage(aStack)) {
                    IToolStats tStats = getToolStats(aStack);
                    if (tStats == null || GT_Utility.setStack(aStack, tStats.getBrokenItem(aStack)) == null) {
                        if (tStats != null) GT_Utility.doSoundAtClient(tStats.getBreakingSound(), 1, 1.0F);
                        if (aStack.field_77994_a > 0) aStack.field_77994_a--;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public float func_150893_a(ItemStack aStack, IBlockState state) {
        //defSpeed is default item mining speed divided by 2
        float defSpeed = 0.5f;

        if (isItemStackUsable(aStack)) {
            IToolStats toolStats = getToolStats(aStack);
            if(toolStats != null && toolStats.isMinableBlock(state)) {
                if(getHarvestLevel(aStack, null) >= state.func_177230_c().getHarvestLevel(state)) {
                    float toolSpeed = toolStats.getSpeedMultiplier() * getPrimaryMaterial(aStack).mToolSpeed;
                    return Math.max(defSpeed, toolSpeed);
                }
                return defSpeed;
            }
            return defSpeed;
        }
        return defSpeed;
    }

    @Override
    public final int getHarvestLevel(ItemStack aStack, String aToolClass) {
        IToolStats tStats = getToolStats(aStack);
        return tStats == null ? -1 : tStats.getBaseQuality() + getPrimaryMaterial(aStack).mToolQuality;
    }



    @Override
    public boolean func_179218_a(ItemStack aStack, World aWorld, IBlockState state, BlockPos blockPos, EntityLivingBase aPlayer) {
        if (!isItemStackUsable(aStack)) return false;
        IToolStats tStats = getToolStats(aStack);
        if (tStats == null) return false;
        GT_Utility.doSoundAtClient(tStats.getMiningSound(), 1, 1.0F);
        doDamage(aStack, (int) Math.max(1, state.func_185887_b(aWorld, blockPos) * tStats.getToolDamagePerBlockBreak()));
        return true;
    }

    @Override
    public final ItemStack getContainerItem(ItemStack aStack) {
        return getContainerItem(aStack, true);
    }

    @Override
    public final boolean hasContainerItem(ItemStack aStack) {
        return getContainerItem(aStack, false) != null;
    }

    private ItemStack getContainerItem(ItemStack aStack, boolean playSound) {
        if (!isItemStackUsable(aStack)) return null;
        aStack = GT_Utility.copyAmount(1, aStack);
        IToolStats tStats = getToolStats(aStack);
        if (tStats == null) return null;
        doDamage(aStack, tStats.getToolDamagePerContainerCraft());
        aStack = aStack.field_77994_a > 0 ? aStack : null;
        if (playSound && GT_Mod.gregtechproxy.mTicksUntilNextCraftSound <= 0) {
            GT_Mod.gregtechproxy.mTicksUntilNextCraftSound = 10;
            String sound = (aStack == null) ? tStats.getBreakingSound() : tStats.getCraftingSound();
            GT_Utility.doSoundAtClient(sound, 1, 1.0F);
        }
        return aStack;
    }

    public IToolStats getToolStats(ItemStack aStack) {
        // if(isItemStackUsable(aStack)) { // why?
        return getToolStatsInternal(aStack);
    }

    private IToolStats getToolStatsInternal(ItemStack aStack) {
        return aStack == null ? null : mToolStats.get((short) aStack.func_77952_i());
    }

    /*@Override
    public float getSaplingModifier(ItemStack aStack, World aWorld, EntityPlayer aPlayer, int aX, int aY, int aZ) {
        IToolStats tStats = getToolStats(aStack);
        return tStats != null && tStats.isGrafter() ? Math.min(100.0F, (1 + getHarvestLevel(aStack, "")) * 20.0F) : 0.0F;
    }

    @Override
    public boolean canWhack(EntityPlayer aPlayer, ItemStack aStack, int aX, int aY, int aZ) {
        if (!isItemStackUsable(aStack)) return false;
        IToolStats tStats = getToolStats(aStack);
        return tStats != null && tStats.isCrowbar();
    }

    @Override
    public void onWhack(EntityPlayer aPlayer, ItemStack aStack, int aX, int aY, int aZ) {
        IToolStats tStats = getToolStats(aStack);
        if (tStats != null) doDamage(aStack, tStats.getToolDamagePerEntityAttack());
    }

	@Override
	public boolean canWrench(EntityPlayer player, int x, int y, int z) {
		if(player==null)return false;
		if(player.getCurrentEquippedItem()==null)return false;
        if (!isItemStackUsable(player.getCurrentEquippedItem())) return false;
        IToolStats tStats = getToolStats(player.getCurrentEquippedItem());
        return tStats != null && tStats.isWrench();
	}

	@Override
	public void wrenchUsed(EntityPlayer player, int x, int y, int z) {
		if(player==null)return;
		if(player.getCurrentEquippedItem()==null)return;
        IToolStats tStats = getToolStats(player.getCurrentEquippedItem());
        if (tStats != null) doDamage(player.getCurrentEquippedItem(), tStats.getToolDamagePerEntityAttack());
	}

	@Override
	public boolean canUse(ItemStack stack, EntityPlayer player, int x, int y, int z){
		 return canWrench(player, x, y, z);
	}

	@Override
	public void used(ItemStack stack, EntityPlayer player, int x, int y, int z){
		wrenchUsed(player, x, y, z);
	}

	@Override
	public boolean shouldHideFacades(ItemStack stack, EntityPlayer player) {
		if(player==null)return false;
		if(player.getCurrentEquippedItem()==null)return false;
        if (!isItemStackUsable(player.getCurrentEquippedItem())) return false;
		IToolStats tStats = getToolStats(player.getCurrentEquippedItem());
		return tStats.isWrench();
	}


    @Override
    public boolean canLink(EntityPlayer aPlayer, ItemStack aStack, EntityMinecart cart) {
        if (!isItemStackUsable(aStack)) return false;
        IToolStats tStats = getToolStats(aStack);
        return tStats != null && tStats.isCrowbar();
    }

    @Override
    public void onLink(EntityPlayer aPlayer, ItemStack aStack, EntityMinecart cart) {
        IToolStats tStats = getToolStats(aStack);
        if (tStats != null) doDamage(aStack, tStats.getToolDamagePerEntityAttack());
    }

    @Override
    public boolean canBoost(EntityPlayer aPlayer, ItemStack aStack, EntityMinecart cart) {
        if (!isItemStackUsable(aStack)) return false;
        IToolStats tStats = getToolStats(aStack);
        return tStats != null && tStats.isCrowbar();
    }

    @Override
    public void onBoost(EntityPlayer aPlayer, ItemStack aStack, EntityMinecart cart) {
        IToolStats tStats = getToolStats(aStack);
        if (tStats != null) doDamage(aStack, tStats.getToolDamagePerEntityAttack());
    }*/

    @Override
    public void func_77622_d(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
        IToolStats tStats = getToolStats(aStack);
        if (tStats != null && aPlayer != null) tStats.onToolCrafted(aStack, aPlayer);
        super.func_77622_d(aStack, aWorld, aPlayer);
    }


    @Override
    public final int getItemStackLimit(ItemStack aStack) {
        return 1;
    }

    @Override
    public boolean func_77662_d() {
        return true;
    }

    @Override
    public boolean isItemStackUsable(ItemStack aStack) {
        IToolStats tStats = getToolStatsInternal(aStack);
        if (aStack.func_77952_i() % 2 != 0 || tStats == null) {
            NBTTagCompound aNBT = aStack.func_77978_p();
            if (aNBT != null) aNBT.func_82580_o("ench");
            return false;
        }
        Materials aMaterial = getPrimaryMaterial(aStack);
        HashMap<Enchantment, Integer> tMap = new HashMap<>(), tResult = new HashMap<>();
        if (aMaterial.mEnchantmentTools != null) {
            tMap.put(aMaterial.mEnchantmentTools, (int) aMaterial.mEnchantmentToolsLevel);
            if (aMaterial.mEnchantmentTools == Enchantment.func_180305_b("fortune"))
                tMap.put(Enchantment.func_180305_b("looting"), (int) aMaterial.mEnchantmentToolsLevel);
            if (aMaterial.mEnchantmentTools == Enchantment.func_180305_b("knockback"))
                tMap.put(Enchantment.func_180305_b("power"), (int) aMaterial.mEnchantmentToolsLevel);
            if (aMaterial.mEnchantmentTools == Enchantment.func_180305_b("fire_aspect"))
                tMap.put(Enchantment.func_180305_b("flame"), (int) aMaterial.mEnchantmentToolsLevel);
        }
        Enchantment[] tEnchants = tStats.getEnchantments(aStack);
        int[] tLevels = tStats.getEnchantmentLevels(aStack);
        for (int i = 0; i < tEnchants.length; i++)
            if (tLevels[i] > 0) {
                Integer tLevel = tMap.get(tEnchants[i]);
                tMap.put(tEnchants[i], tLevel == null ? tLevels[i] : tLevel == tLevels[i] ? tLevel + 1 : Math.max(tLevel, tLevels[i]));
            }
        for (Entry<Enchantment, Integer> tEntry : tMap.entrySet()) {
            int id = Enchantment.func_185258_b(tEntry.getKey());
            if (id == 33 || (id == 20 && id > 2) || id == Enchantment.func_185258_b(Enchantment_Radioactivity.INSTANCE))
                tResult.put(tEntry.getKey(), tEntry.getValue());
            else
                switch (tEntry.getKey().field_77351_y) {
                    case WEAPON:
                        if (tStats.isWeapon()) tResult.put(tEntry.getKey(), tEntry.getValue());
                        break;
                    case ALL:
                        tResult.put(tEntry.getKey(), tEntry.getValue());
                        break;
                    case ARMOR:
                    case ARMOR_FEET:
                    case ARMOR_HEAD:
                    case ARMOR_LEGS:
                    case ARMOR_CHEST:
                        break;
                    case BOW:
                        if (tStats.isRangedWeapon()) tResult.put(tEntry.getKey(), tEntry.getValue());
                        break;
                    case BREAKABLE:
                        break;
                    case FISHING_ROD:
                        break;
                    case DIGGER:
                        if (tStats.isMiningTool()) tResult.put(tEntry.getKey(), tEntry.getValue());
                        break;
                }
        }
        EnchantmentHelper.func_82782_a(tResult, aStack);
        return true;
    }

    @Override
    public short getChargedMetaData(ItemStack aStack) {
        return (short) (aStack.func_77952_i() - (aStack.func_77952_i() % 2));
    }

    @Override
    public short getEmptyMetaData(ItemStack aStack) {
        NBTTagCompound aNBT = aStack.func_77978_p();
        if (aNBT != null) aNBT.func_82580_o("ench");
        return (short) (aStack.func_77952_i() + 1 - (aStack.func_77952_i() % 2));
    }

    @Override
    public int func_77619_b() {
        return 0;
    }

    @Override
    public boolean isBookEnchantable(ItemStack aStack, ItemStack aBook) {
        return false;
    }

    @Override
    public boolean func_82789_a(ItemStack aStack, ItemStack aMaterial) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int tintIndex) {
        IToolStats toolStats = getToolStats(stack);
        if(tintIndex > 1) {
            short[] colorsHead = toolStats.getRGBa(true, stack);
            if(colorsHead != null)
                return ITexture.color(colorsHead, true);
        }
        short[] colors = toolStats.getRGBa(false, stack);
        if(colors != null)
            return ITexture.color(colors, true);
        return 0xFFFFFF;
    }

    @Override
    public String func_77653_i(ItemStack stack) {
        return GT_LanguageManager.getTranslation(func_77667_c(stack) + ".name");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getIcon(ItemStack stack, int pass) {
        IToolStats toolStats = getToolStats(stack);
        IIconContainer icon = null;
        switch (pass) {
            case 0:
            case 1:
                // Handle
                icon = toolStats.getIcon(false, stack);
                break;
            case 2:
            case 3:
                // Head
                icon = toolStats.getIcon(true, stack);
                break;
            case 4:
            case 5:
                // Durability Bar
                long tDamage = getToolDamage(stack);
                long tMaxDamage = getToolMaxDamage(stack);
                if (tDamage <= 0L) {
                    icon = gregtech.api.enums.Textures.ItemIcons.DURABILITY_BAR[8];
                } else if (tDamage >= tMaxDamage) {
                    icon = gregtech.api.enums.Textures.ItemIcons.DURABILITY_BAR[0];
                } else {
                    icon = gregtech.api.enums.Textures.ItemIcons.DURABILITY_BAR[((int) java.lang.Math.max(0L, java.lang.Math.min(7L, (tMaxDamage - tDamage) * 8L / tMaxDamage)))];
                }
                break;
            case 6:
            case 7:
                // Energy Bar
                Long[] tStats = getElectricStats(stack);
                if ((tStats != null) && (tStats[3].longValue() < 0L)) {
                long tCharge = getRealCharge(stack);
                    if (tCharge <= 0L) {
                        icon = gregtech.api.enums.Textures.ItemIcons.ENERGY_BAR[0];
                    } else if (tCharge >= tStats[0].longValue()) {
                        icon = gregtech.api.enums.Textures.ItemIcons.ENERGY_BAR[8];
                    } else {
                        icon = gregtech.api.enums.Textures.ItemIcons.ENERGY_BAR[(7 - (int) java.lang.Math.max(0L, java.lang.Math.min(6L, (tStats[0].longValue() - tCharge) * 7L / tStats[0].longValue())))];
                    }
                }
                break;
            default:
                break;
        }
        if (icon != null) {
            if ((pass & 1) == 0) {
                return icon.getIcon();
            } else {
                return icon.getOverlayIcon();
            }
        }
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderPasses(ItemStack stack) {
        return 8;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isHandheld(ItemStack stack) {
        return true;
    }

    @Override
    public float getSaplingModifier(ItemStack stack, World world, EntityPlayer player, BlockPos pos) {
        IToolStats toolStats = getToolStats(stack);
        if(toolStats.isGrafter()) {
            return 0.78F;
        }
        return 0.0F;
    }

}
