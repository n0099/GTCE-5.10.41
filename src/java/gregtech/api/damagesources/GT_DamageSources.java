package gregtech.api.damagesources;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class GT_DamageSources {
    public static DamageSource getElectricDamage() {
        return ic2.api.info.Info.DMG_ELECTRIC;
    }

    public static DamageSource getRadioactiveDamage() {
        return ic2.api.info.Info.DMG_RADIATION;
    }

    public static DamageSource getNukeExplosionDamage() {
        return ic2.api.info.Info.DMG_NUKE_EXPLOSION;
    }

    public static DamageSource getExplodingDamage() {
        return new DamageSourceExploding();
    }

    public static DamageSource getCombatDamage(String aType, EntityLivingBase aPlayer, ITextComponent aDeathMessage) {
        return new DamageSourceCombat(aType, aPlayer, aDeathMessage);
    }

    public static DamageSource getHeatDamage() {
        return new DamageSourceHeat();
    }

    public static DamageSource getFrostDamage() {
        return new DamageSourceFrost();
    }

    private static class DamageSourceCombat extends EntityDamageSource {
        private ITextComponent mDeathMessage;

        public DamageSourceCombat(String aType, EntityLivingBase aPlayer, ITextComponent aDeathMessage) {
            super(aType, aPlayer);
            mDeathMessage = aDeathMessage;
        }

        @Override
        public ITextComponent func_151519_b(EntityLivingBase entityLivingBaseIn) {
            return mDeathMessage == null ? super.func_151519_b(entityLivingBaseIn) : mDeathMessage;
        }
    }

    private static class DamageSourceFrost extends DamageSource {
        public DamageSourceFrost() {
            super("frost");
            func_76351_m();
        }

        @Override
        public ITextComponent func_151519_b(EntityLivingBase aTarget) {
            return new TextComponentString(TextFormatting.RED.toString())
                    .func_150257_a(aTarget.func_145748_c_())
                    .func_150258_a(TextFormatting.WHITE + " got frozen");
        }

    }

    private static class DamageSourceHeat extends DamageSource {
        public DamageSourceHeat() {
            super("steam");
            func_76351_m();
        }

        @Override
        public ITextComponent func_151519_b(EntityLivingBase aTarget) {
            return new TextComponentString(TextFormatting.RED.toString())
                    .func_150257_a(aTarget.func_145748_c_())
                    .func_150258_a(TextFormatting.WHITE + " was boiled alive");
        }
    }

    public static class DamageSourceExploding extends DamageSource {
        public DamageSourceExploding() {
            super("exploded");
            func_76359_i();
            func_76348_h();
            func_151518_m();
        }

        @Override
        public ITextComponent func_151519_b(EntityLivingBase aTarget) {
            return new TextComponentString(TextFormatting.RED.toString())
                    .func_150257_a(aTarget.func_145748_c_())
                    .func_150258_a(TextFormatting.WHITE + " exploded");
        }

    }
}
