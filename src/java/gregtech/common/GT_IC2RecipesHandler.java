package gregtech.common;

import ic2.api.recipe.IMachineRecipeManager;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;

import ic2.api.recipe.IMachineRecipeManager.RecipeIoContainer;

public class GT_IC2RecipesHandler implements IMachineRecipeManager {

    private ArrayList<RecipeIoContainer> recipes = new ArrayList<>();

    @Override
    public boolean addRecipe(IRecipeInput iRecipeInput, NBTTagCompound metadata, boolean replace, ItemStack... itemStacks) {
        RecipeIoContainer ioContainer = new RecipeIoContainer(iRecipeInput, new RecipeOutput(metadata, itemStacks));
        recipes.add(ioContainer);
        return true;
    }

    @Override
    public RecipeOutput getOutputFor(ItemStack input, boolean adjustInput) {
        for(RecipeIoContainer ioContainer : recipes) {
            if (ioContainer.input.matches(input)) {
                if (adjustInput) {
                    if (input.func_77973_b().hasContainerItem(input)) {
                        ItemStack container = input.func_77973_b().getContainerItem(input);
                        input.func_150996_a(container.func_77973_b());
                        input.field_77994_a = container.field_77994_a;
                        input.func_77964_b(container.func_77952_i());
                        input.func_77982_d(container.func_77978_p());
                    } else {
                        input.field_77994_a -= ioContainer.input.getAmount();
                    }
                }
                return ioContainer.output;
            }
        }
        return null;
    }

    @Override
    public Iterable<RecipeIoContainer> getRecipes() {
        return recipes;
    }

    @Override
    public boolean isIterable() {
        return true;
    }

}
