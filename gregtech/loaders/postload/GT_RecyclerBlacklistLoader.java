package gregtech.loaders.postload;

import gregtech.api.GregTech_API;
import gregtech.api.enums.ConfigCategories;
import gregtech.api.enums.ItemList;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_ModHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GT_RecyclerBlacklistLoader
        implements Runnable {
    public void run() {
        GT_Log.out.println("GT_Mod: Adding Stuff to the Recycler Blacklist.");
        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "easymobgrinderrecycling", true)) {
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Items.field_151032_g, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Items.field_151103_aS, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(ItemList.Dye_Bonemeal.get(1L));


            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Items.field_151078_bh, 1, 0));


            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Items.field_151007_F, 1, 0));


            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Items.field_151110_aK, 1, 0));
        }
        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "easystonerecycling", true)) {
            ItemStack tStack = new ItemStack(Blocks.field_150347_e, 1, 0);
            while (tStack != null) {
                GT_ModHandler.addToRecyclerBlackList(tStack);
                tStack = GT_ModHandler.getRecipeOutput(tStack, tStack, tStack, tStack, tStack, tStack, tStack, tStack, tStack);
            }
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.field_150351_n, 1, 32767));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Items.field_151145_ak, 1, 32767));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.field_150463_bK, 1, 32767));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.field_150372_bz, 1, 32767));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.field_150446_ar, 1, 32767));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.field_150390_bg, 1, 32767));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getSmeltingOutput(new ItemStack(Blocks.field_150348_b, 1, 0), false, null));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack(Blocks.field_150359_w, 1, 0), null, null, new ItemStack(Blocks.field_150359_w, 1, 0)));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack(Blocks.field_150348_b, 1, 0), null, null, new ItemStack(Blocks.field_150348_b, 1, 0)));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack(Blocks.field_150347_e, 1, 0), null, null, new ItemStack(Blocks.field_150347_e, 1, 0)));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack(Blocks.field_150348_b, 1, 0), null, new ItemStack(Blocks.field_150348_b, 1, 0), null, new ItemStack(Blocks.field_150348_b, 1, 0)));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack(Blocks.field_150348_b, 1, 0), new ItemStack(Blocks.field_150359_w, 1, 0), new ItemStack(Blocks.field_150348_b, 1, 0)));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack(Blocks.field_150347_e, 1, 0), new ItemStack(Blocks.field_150359_w, 1, 0), new ItemStack(Blocks.field_150347_e, 1, 0)));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack(Blocks.field_150322_A, 1, 0), new ItemStack(Blocks.field_150359_w, 1, 0), new ItemStack(Blocks.field_150322_A, 1, 0)));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack(Blocks.field_150354_m, 1, 0), new ItemStack(Blocks.field_150359_w, 1, 0), new ItemStack(Blocks.field_150354_m, 1, 0)));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack(Blocks.field_150322_A, 1, 0), new ItemStack(Blocks.field_150322_A, 1, 0), new ItemStack(Blocks.field_150322_A, 1, 0), new ItemStack(Blocks.field_150322_A, 1, 0), new ItemStack(Blocks.field_150322_A, 1, 0), new ItemStack(Blocks.field_150322_A, 1, 0)));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack(Blocks.field_150359_w, 1, 0)));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack(Blocks.field_150359_w, 1, 0), new ItemStack(Blocks.field_150359_w, 1, 0)));
        }
    }
}
