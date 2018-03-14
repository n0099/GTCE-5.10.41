package gregtech.common.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class GT_Entity_Arrow_Potion extends GT_Entity_Arrow {

    private int[] mPotions = new int[0];

    public GT_Entity_Arrow_Potion(World worldIn) {
        super(worldIn);
    }

    public GT_Entity_Arrow_Potion(World worldIn, double x, double y, double z, ItemStack arrowStack, int[] mPotions) {
        super(worldIn, x, y, z, arrowStack);
        this.mPotions = mPotions;
    }

    public GT_Entity_Arrow_Potion(World worldIn, EntityLivingBase shooter, ItemStack arrowStack, int[] mPotions) {
        super(worldIn, shooter, arrowStack);
        this.mPotions = mPotions;
    }

    public void func_70014_b(NBTTagCompound aNBT) {
        super.func_70014_b(aNBT);
        aNBT.func_74783_a("mPotions", this.mPotions);
    }

    public void func_70037_a(NBTTagCompound aNBT) {
        super.func_70037_a(aNBT);
        setPotions(aNBT.func_74759_k("mPotions"));
    }

    public int[] getPotions() {
        return this.mPotions;
    }

    public void setPotions(int... aPotions) {
        if (aPotions != null) {
            this.mPotions = aPotions;
        }
    }

    @Override
    protected void func_184548_a(EntityLivingBase living) {
        for (int i = 3; i < this.mPotions.length; i += 4) {
            if (field_70170_p.field_73012_v.nextInt(100) < this.mPotions[i]) {
                living.func_70690_d(new PotionEffect(
                        Potion.func_188412_a(this.mPotions[(i - 3)]),
                        this.mPotions[(i - 2)],
                        this.mPotions[(i - 1)],
                        false, false));
            }
        }
    }

}
