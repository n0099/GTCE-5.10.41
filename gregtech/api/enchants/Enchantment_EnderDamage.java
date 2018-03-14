package gregtech.api.enchants;

import gregtech.api.enums.ConfigCategories;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_Config;
import gregtech.api.util.GT_LanguageManager;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import net.minecraft.enchantment.Enchantment.Rarity;

public class Enchantment_EnderDamage extends EnchantmentDamage {
    public static Enchantment_EnderDamage INSTANCE;

    public Enchantment_EnderDamage() {
        super(Rarity.UNCOMMON, 2);
        //GT_Config.addIDConfig(ConfigCategories.IDs.enchantments, "Disjunction", 15)
        GT_LanguageManager.addStringLocalization(func_77320_a(), "Disjunction");
        Materials.Silver.setEnchantmentForTools(this, 2);
        Materials.Mercury.setEnchantmentForTools(this, 3);
        Materials.Electrum.setEnchantmentForTools(this, 3);
        Materials.SterlingSilver.setEnchantmentForTools(this, 4);
        Materials.AstralSilver.setEnchantmentForTools(this, 5);
        INSTANCE = this;
    }

    @Override
    public int func_77321_a(int aLevel) {
        return 5 + (aLevel - 1) * 8;
    }

    @Override
    public int func_77317_b(int aLevel) {
        return this.func_77321_a(aLevel) + 20;
    }

    @Override
    public int func_77325_b() {
        return 5;
    }

    @Override
    public void func_151368_a(EntityLivingBase aHurtEntity, Entity aDamagingEntity, int aLevel) {
        if ((aHurtEntity instanceof EntityEnderman || aHurtEntity instanceof EntityDragon || (aHurtEntity.getClass().getName().indexOf(".") >= 0 && aHurtEntity.getClass().getName().substring(aHurtEntity.getClass().getName().lastIndexOf(".")).contains("Ender")))) {
            // Weakness causes Endermen to not be able to teleport with GT being installed.
            aHurtEntity.func_70690_d(new PotionEffect(MobEffects.field_76437_t, aLevel * 200, Math.max(1, (5 * aLevel) / 7)));
            // They also get Poisoned. If you have this Enchant on an Arrow, you can kill the Ender Dragon easier.
            aHurtEntity.func_70690_d(new PotionEffect(MobEffects.field_76436_u, aLevel * 200, Math.max(1, (5 * aLevel) / 7)));
        }
    }

    @Override
    public String func_77320_a() {
        return "enchantment.damage.endermen";
    }
}
