package gregtech.api.util;

import gregtech.api.interfaces.internal.IGT_CraftingRecipe;
import gregtech.api.items.GT_MetaGenerated_Tool;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class GT_Shaped_Recipe extends ShapedOreRecipe implements IGT_CraftingRecipe {
    public final boolean mDismantleable, mRemovableByGT, mKeepingNBT;
    private final Enchantment[] mEnchantmentsAdded;
    private final int[] mEnchantmentLevelsAdded;

    public GT_Shaped_Recipe(ItemStack aResult, boolean aDismantleAble, boolean aRemovableByGT, boolean aKeepingNBT, Enchantment[] aEnchantmentsAdded, int[] aEnchantmentLevelsAdded, Object... aRecipe) {
        super(aResult, aRecipe);
        mEnchantmentsAdded = aEnchantmentsAdded;
        mEnchantmentLevelsAdded = aEnchantmentLevelsAdded;
        mRemovableByGT = aRemovableByGT;
        mKeepingNBT = aKeepingNBT;
        mDismantleable = aDismantleAble;
    }

    @Override
    public boolean func_77569_a(InventoryCrafting aGrid, World aWorld) {
        if (mKeepingNBT) {
            ItemStack tStack = null;
            for (int i = 0; i < aGrid.func_70302_i_(); i++) {
                if (aGrid.func_70301_a(i) != null) {
                    if (tStack != null) {
                        if ((tStack.func_77942_o() != aGrid.func_70301_a(i).func_77942_o()) || (tStack.func_77942_o() && !tStack.func_77978_p().equals(aGrid.func_70301_a(i).func_77978_p())))
                            return false;
                    }
                    tStack = aGrid.func_70301_a(i);
                }
            }
        }
        return super.func_77569_a(aGrid, aWorld);
    }

    @Override
    public ItemStack func_77572_b(InventoryCrafting aGrid) {
        ItemStack rStack = super.func_77572_b(aGrid);
        if (rStack != null) {
            // Update the Stack
            GT_Utility.updateItemStack(rStack);

            // Keeping NBT
            if (mKeepingNBT) for (int i = 0; i < aGrid.func_70302_i_(); i++) {
                if (aGrid.func_70301_a(i) != null && aGrid.func_70301_a(i).func_77942_o()) {
                    rStack.func_77982_d((NBTTagCompound) aGrid.func_70301_a(i).func_77978_p().func_74737_b());
                    break;
                }
            }

            // Charge Values
            if (GT_ModHandler.isElectricItem(rStack)) {
                GT_ModHandler.dischargeElectricItem(rStack, Integer.MAX_VALUE, Integer.MAX_VALUE, true, false, true);
                int tCharge = 0;
                for (int i = 0; i < aGrid.func_70302_i_(); i++)
                    tCharge += GT_ModHandler.dischargeElectricItem(aGrid.func_70301_a(i), Integer.MAX_VALUE, Integer.MAX_VALUE, true, true, true);
                if (tCharge > 0) GT_ModHandler.chargeElectricItem(rStack, tCharge, Integer.MAX_VALUE, true, false);
            }

            // Saving Ingredients inside the Item.
            if (mDismantleable) {
                NBTTagCompound rNBT = rStack.func_77978_p(), tNBT = new NBTTagCompound();
                if (rNBT == null) rNBT = new NBTTagCompound();
                for (int i = 0; i < 9; i++) {
                    ItemStack tStack = aGrid.func_70301_a(i);
                    if (tStack != null && GT_Utility.getContainerItem(tStack, true) == null && !(tStack.func_77973_b() instanceof GT_MetaGenerated_Tool)) {
                        tStack = GT_Utility.copyAmount(1, tStack);
                        if(GT_Utility.isStackValid(tStack)){
                        GT_ModHandler.dischargeElectricItem(tStack, Integer.MAX_VALUE, Integer.MAX_VALUE, true, false, true);
                        tNBT.func_74782_a("Ingredient." + i, tStack.func_77955_b(new NBTTagCompound()));}
                    }
                }
                rNBT.func_74782_a("GT.CraftingComponents", tNBT);
                rStack.func_77982_d(rNBT);
            }

            // Add Enchantments
            for (int i = 0; i < mEnchantmentsAdded.length; i++)
                GT_Utility.ItemNBT.addEnchantment(rStack, mEnchantmentsAdded[i], EnchantmentHelper.func_77506_a(mEnchantmentsAdded[i], rStack) + mEnchantmentLevelsAdded[i]);

            // Update the Stack again
            GT_Utility.updateItemStack(rStack);
        }
        return rStack;
    }

    @Override
    public boolean isRemovable() {
        return mRemovableByGT;
    }
}
