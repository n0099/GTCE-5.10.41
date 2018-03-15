package gregtech.common.items.armor;

import gregtech.api.damagesources.GT_DamageSources;
import gregtech.api.enums.GT_Values;
import gregtech.api.items.GT_Generic_Item;
import ic2.core.IC2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ModularArmor_Item extends GT_Generic_Item implements ISpecialArmor {

    @SideOnly(Side.CLIENT)
    private TextureAtlasSprite itemIcon;

    public String mName;
    public int timer = 160;
    public Item repairMaterial;
    public int openGuiNr;
    public ArmorData data;
    public int jumpticks;

    // public int maxEU;
    public final EntityEquipmentSlot mType;

    public ModularArmor_Item(EntityEquipmentSlot aType, String name, int gui, String aEnglishName) {
        super(name, aEnglishName, "");
        MinecraftForge.EVENT_BUS.register(this);
        this.mType = aType;
        this.mName = name;
        int mMaxDamage = (gui + 1) * 1024;
        mMaxDamage *= getBaseAbsorptionRatio() * 2.5;
        func_77656_e(mMaxDamage);
        this.repairMaterial = Items.field_151116_aA;
        this.openGuiNr = gui;
    }

    @Override
    public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity) {
        return armorType == mType;
    }

    @Override
    public ActionResult<ItemStack> func_77659_a(ItemStack aStack, World aWorld, EntityPlayer aPlayer, EnumHand hand) {
        if (data == null) {
            data = fillArmorData(aPlayer, aStack);
        }
        if (!aWorld.field_72995_K) {
            aPlayer.openGui(GT_Values.GT, openGuiNr + 1000, aWorld, (int) aPlayer.field_70165_t, (int) aPlayer.field_70163_u, (int) aPlayer.field_70161_v);
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, aStack);
    }

    @Override
    public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        if (data == null) {
            data = fillArmorData((EntityPlayer) player, armor);
        }
        if (player != null && armor != null && source != null) {
            double tmp = 0.0d;
            if (source.func_82725_o()) {
                tmp = data.magicDef;
            } else if (source == GT_DamageSources.getRadioactiveDamage()) {
                tmp = data.radiationDef;
            } else if (source == GT_DamageSources.getElectricDamage()) {
                tmp = data.electricDef;
            } else if (source == DamageSource.field_82727_n) {
                tmp = data.witherDef;
            } else if (source.func_76347_k() || source == GT_DamageSources.getHeatDamage()) {
                tmp = data.fireDef;
            } else if (source.func_94541_c()) {
                tmp = data.explosionDef;
            } else if (source.func_76352_a()) {
                tmp = data.projectileDef;
            } else {
                tmp = data.physicalDef;
            }
            if (data.thorns > 0.1d && source != DamageSource.field_76379_h && source.func_76364_f() != null) {
                source.func_76364_f().func_70097_a(new DamageSource("Thorns"), data.thorns);
            }

            // fallDamage
            if (source == DamageSource.field_76379_h) {
                int fallDef = 0;
                ItemStack stack = player.func_184582_a(EntityEquipmentSlot.FEET);
                if (stack != null && stack.func_77973_b() instanceof ModularArmor_Item) {
                    fallDef = (int) data.boots.fallDef;
                }
                tmp = 1.0d - (fallDef > damage ? 0.0d : (1.0d - tmp) * 0.5d);
            }
            if (tmp == 0.0d) {
                tmp = data.physicalDef;
            }
            if (openGuiNr == 2) {
                tmp = 1.0f - ((1.0f - tmp) / 2.0f);
            }
            return new ISpecialArmor.ArmorProperties(0, data.getBaseAbsorptionRatio() * tmp, 1000);

        } else {
            return new ISpecialArmor.ArmorProperties(0, 0, 0);
        }
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        if (data == null) {
            data = fillArmorData(player, armor);
        }

        return (int) -Math.floor(-(data.getBaseAbsorptionRatio() * 20 * data.physicalDef));
    }

    @Override
    public void func_77624_a(ItemStack itemStack, EntityPlayer player, List<String> info, boolean b) {
        if (data == null) {
            data = fillArmorData(player, itemStack);
        }
        if (data.info != null)
            info.addAll(data.info);
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
        if (data == null) {
            data = fillArmorData((EntityPlayer) entity, stack);
        }

        stack.func_77972_a(damage, entity);
        ContainerModularArmor tmp = new ContainerBasicArmor((EntityPlayer) entity, new InventoryArmor(ModularArmor_Item.class, stack));
        if (stack.func_77952_i() > stack.func_77958_k() / 2 && new Random().nextInt(100) < 5) {
            tmp.func_75139_a(new Random().nextInt(tmp.getSlotCount())).func_75209_a(1);
            tmp.mInvArmor.onGuiSaved((EntityPlayer) entity);
        }

    }

    @SubscribeEvent
    public void onEntityLivingFallEvent(LivingFallEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer() && event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            if (data != null && event != null && data.type == 3 && data.charge >= data.pistonEUusage && event.getDistance() - 3 <= data.fallDef) {
                event.setCanceled(true);
            } else if (data != null && event != null && data.type == 3 && data.charge >= data.pistonEUusage) {
                event.setDistance(event.getDistance() - data.fallDef);
            }
        }
    }

    @Override
    public void onArmorTick(World aWorld, EntityPlayer aPlayer, ItemStack aStack) {
        if (data == null) {
            data = fillArmorData(aPlayer, aStack);
        }
        if (data.tooltipUpdate > 40) {
            data.armorPartsEquipped(aPlayer);
            data.tooltipUpdate = 0;
            data.updateTooltip();
        } else {
            data.tooltipUpdate++;
        }
        if (aPlayer.field_70122_E) {
            jumpticks = 4;
        } else {
            jumpticks--;
        }

        // Breathing
        if (data.type == 0 && aPlayer.func_70086_ai() < 100) {
            int air = 0;
            if (data.fluid.getUnlocalizedName().equals("fluid.oxygen") && data.fluid.amount >= 150) {
                aPlayer.func_70050_g(aPlayer.func_70086_ai() + 150);
                air = 150;
            } else if (data.fluid.getUnlocalizedName().equals("fluid.air") && data.fluid.amount >= 500) {
                aPlayer.func_70050_g(aPlayer.func_70086_ai() + 100);
                air = 500;
            }
            if (air > 0) {
                data.fluid.amount -= air;
                ItemStack stack = aPlayer.func_184582_a(EntityEquipmentSlot.HEAD);
                if (stack != null && stack.func_77973_b() instanceof ModularArmor_Item) {
                    ContainerModularArmor tmp2 = new ContainerBasicArmor(aPlayer, new InventoryArmor(ModularArmor_Item.class, stack));
                    ArmorCalculation.useFluid(tmp2.mInvArmor.parts, air);
                }
            }
        }
        // Fill Air Tank
        if (data.tooltipUpdate == 40 && data.processingPower > data.processingPowerUsed && data.type == 0 && data.fluid != null
                && data.fluid.getUnlocalizedName().equals("oxygen") && data.fluid.amount < data.tankCap && data.charge > data.electrolyzerEUusage) {
            data.charge -= data.electrolyzerEUusage;
            ItemStack stack = aPlayer.func_184582_a(EntityEquipmentSlot.HEAD);
            if (stack != null && stack.func_77973_b() instanceof ModularArmor_Item) {
                ContainerModularArmor tmp2 = new ContainerBasicArmor(aPlayer, new InventoryArmor(ModularArmor_Item.class, stack));
                ArmorCalculation.useFluid(tmp2.mInvArmor.parts, -data.electrolyzerProd);
            }
        }

        if (data.isTopItem) {
            if (IC2.keyboard.isModeSwitchKeyDown(aPlayer) && !aWorld.field_72995_K) {
                int typeMod = 0;
                switch (data.type) {
                    case 0:
                        typeMod = 400;
                        break;
                    case 1:
                        typeMod = 300;
                        break;
                    case 2:
                        typeMod = 200;
                        break;
                    case 3:
                        typeMod = 100;
                        break;
                }
                aPlayer.openGui(GT_Values.GT, openGuiNr + (typeMod), aWorld, (int) aPlayer.field_70165_t, (int) aPlayer.field_70163_u, (int) aPlayer.field_70161_v);
            }
            if (data.helmet != null && data.helmet.openGui) {
                data.helmet.openGui = false;
                aPlayer.openGui(GT_Values.GT, openGuiNr + 400, aWorld, (int) aPlayer.field_70165_t, (int) aPlayer.field_70163_u, (int) aPlayer.field_70161_v);
            }
            if (data.chestplate != null && data.chestplate.openGui) {
                data.chestplate.openGui = false;
                aPlayer.openGui(GT_Values.GT, openGuiNr + 300, aWorld, (int) aPlayer.field_70165_t, (int) aPlayer.field_70163_u, (int) aPlayer.field_70161_v);
            }
            if (data.leggings != null && data.leggings.openGui) {
                data.leggings.openGui = false;
                aPlayer.openGui(GT_Values.GT, openGuiNr + 200, aWorld, (int) aPlayer.field_70165_t, (int) aPlayer.field_70163_u, (int) aPlayer.field_70161_v);
            }
            if (data.boots != null && data.boots.openGui) {
                data.boots.openGui = false;
                aPlayer.openGui(GT_Values.GT, openGuiNr + 100, aWorld, (int) aPlayer.field_70165_t, (int) aPlayer.field_70163_u, (int) aPlayer.field_70161_v);
            }
            // Night Vision
            if (timer >= 200) {
                timer = 0;
                if (data.processingPower > data.processingPowerUsed && data.helmet != null && data.helmet.nightVision && data.charge > 3) {
                    aPlayer.func_70690_d(new PotionEffect(MobEffects.field_76439_r, 500, -3));
                    data.charge -= 4;
                } else {
                    PotionEffect nv = aPlayer.func_70660_b(MobEffects.field_76439_r);
                    if (nv != null && nv.func_76458_c() == -3) {
                        if (aPlayer.field_70170_p.field_72995_K) {
                            aPlayer.func_184596_c(MobEffects.field_76439_r);
                        } else {
                            aPlayer.func_184589_d(MobEffects.field_76439_r);
                        }
                    }
                }
            } else {
                timer++;
            }

            // Item Magnet
            if (data.magnet > 1) {
                double x = aPlayer.field_70165_t;
                double y = aPlayer.field_70163_u - (aPlayer.field_70170_p.field_72995_K ? 1.62 : 0) + 0.75;
                double z = aPlayer.field_70161_v;
                List<EntityItem> items = aPlayer.field_70170_p.func_72872_a(EntityItem.class, new AxisAlignedBB(x - data.magnet, y - data.magnet, z - data.magnet, x + data.magnet, y + data.magnet, z + data.magnet));
                for (EntityItem item : items) {
                    ItemStack stack = item.func_92059_d();
                    if (!item.field_70128_L && stack != null) {
                        setEntityMotionFromVector(item, new Vector3(x, y, z), 0.45F);
                    }
                }
            }
            // Weight limit calcuation
            double motorSpeed = 0;
            if (data.leggings != null) {
                motorSpeed = data.leggings.motorPower;
            }
            if (data.maxWeight > 4000) {
                if (data.leggings != null && data.leggings.charge > data.leggings.motorEUusage) {
                    motorSpeed -= data.maxWeight - 4000;
                    data.leggings.charge -= (data.leggings.motorEUusage / 100);
                } else {
                    aPlayer.field_70159_w *= (4000.0d / data.maxWeight);
                    aPlayer.field_70179_y *= (4000.0d / data.maxWeight);
                }
            }
            if (data.leggings != null && data.leggings.charge > data.leggings.motorEUusage && data.processingPower > data.processingPowerUsed && motorSpeed > 0
                    && aPlayer.func_70051_ag() && jumpticks > 0
                    && (aPlayer.field_70122_E && Math.abs(aPlayer.field_70159_w) + Math.abs(aPlayer.field_70179_y) > 0.10000000149011612D)) {
                data.leggings.charge -= data.leggings.motorEUusage;
                motorSpeed = Math.sqrt(motorSpeed) / 3;

                float var7 = (float) (0.02f * motorSpeed);
                if (aPlayer.func_70090_H()) {
                    var7 = 0.1F;
                    if (aPlayer.field_70181_x > 0) {
                        aPlayer.field_70181_x += 0.10000000149011612D;
                    }
                }

                if (var7 > 0.0F) {
                    aPlayer.func_70060_a(0.0F, 1.0F, var7);
                }
            }

            // jump+step up assist
            if (data.processingPower > data.processingPowerUsed && data.leggings != null) {
                double stepup = data.leggings.pistonJumpboost;
                if (stepup > 1) {
                    aPlayer.field_70138_W = 1.0f;
                }
                if (GameSettings.func_100015_a(Minecraft.func_71410_x().field_71474_y.field_74314_A)) {
                    if (stepup > 0 && jumpticks > 0) {
                        if (data.maxWeight > 2000) {
                            stepup *= 2000.0D / data.maxWeight;
                        }
                        aPlayer.field_70181_x += 0.04 * stepup;
                    }
                    aPlayer.field_70747_aH = aPlayer.func_70689_ay() * .2f;
                }

            }

            // immune effect removal
            List<PotionEffect> effects = new LinkedList<>(aPlayer.func_70651_bq());
            for (PotionEffect effect : effects) {
                if (Potion.func_188409_a(effect.func_188419_a()) == 24 && data.fullRadiationDef) {
                    aPlayer.func_184589_d(effect.func_188419_a());
                }
            }
        }
    }

    public void setEntityMotionFromVector(Entity entity, Vector3 originalPosVector, float modifier) {
        Vector3 entityVector = Vector3.fromEntityCenter(entity);
        Vector3 finalVector = originalPosVector.copy().subtract(entityVector);
        if (finalVector.mag() > 1)
            finalVector.normalize();
        entity.field_70159_w = finalVector.x * modifier;
        entity.field_70181_x = finalVector.y * modifier;
        entity.field_70179_y = finalVector.z * modifier;
    }

    @Override
    public int func_77619_b() {
        return 0;
    }

    @Override
    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
        return false;
    }

    @Override
    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return true;
    }


    @SideOnly(Side.CLIENT)
    public void registerIcons(TextureMap aIconRegister) {
        this.itemIcon = aIconRegister.func_174942_a(new ResourceLocation(GT_Values.MOD_ID + ":items/" + mName));
    }

    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getIcon(ItemStack stack, int pass) {
        return itemIcon;
    }

    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        String armor = GT_Values.RES_PATH_ITEM + "armorhelmet.png";
        String tier = "";
        try {
            if (data == null) {
                data = fillArmorData((EntityPlayer) entity, stack);
            }
            if (this.data.armorTier == 0) {
                tier = "basic";
            } else if (this.data.armorTier == 1) {
                tier = "e1";
            } else if (this.data.armorTier == 2) {
                tier = "e2";
            }
            if (this.data.type == 0 || this.data.type == 1 || this.data.type == 3) {
                armor = GT_Values.RES_PATH_MODEL + "armor/" + tier + "_helmet_chest.png";
            } else {
                armor = GT_Values.RES_PATH_MODEL + "armor/" + tier + "_leggings_boots.png";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return armor;
    }

	/*@Override
	public boolean showNodes(ItemStack aStack, EntityLivingBase aPlayer) {
		if (data == null) {
			data = fillArmorData((EntityPlayer) aPlayer, aStack);
		}
		return data.thaumicGoggles && data.armorTier > 0 && data.charge > 0;
	}

	@Override
	public boolean showIngamePopups(ItemStack aStack, EntityLivingBase aPlayer) {
		if (data == null) {
			data = fillArmorData((EntityPlayer) aPlayer, aStack);
		}
		return data.thaumicGoggles && data.armorTier > 0 && data.charge > 0;
	}*/

    public ArmorData fillArmorData(EntityPlayer player, ItemStack stack) {
        return new ArmorData(player, stack, this.mType.func_188454_b(), openGuiNr);
    }

    public double getBaseAbsorptionRatio() {
        switch (this.mType) {
            case HEAD:
                return 0.15;
            case CHEST:
                return 0.40;
            case LEGS:
                return 0.30;
            case FEET:
                return 0.15;
            default:
                return 0.00;
        }
    }

}
