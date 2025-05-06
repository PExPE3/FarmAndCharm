package net.satisfy.farm_and_charm.core.compat.rei.stove;


import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.SimpleGridMenuDisplay;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.type.EntryDefinition;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.satisfy.farm_and_charm.FarmAndCharm;
import net.satisfy.farm_and_charm.core.recipe.StoveRecipe;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("all")
public class StoveDisplay extends BasicDisplay implements SimpleGridMenuDisplay {

    public static final CategoryIdentifier<StoveDisplay> STOVE_DISPLAY = CategoryIdentifier.of(FarmAndCharm.MOD_ID, "stove_display");


    private final float xp;

    public StoveDisplay(RecipeHolder<StoveRecipe> recipe) {
        this(EntryIngredients.ofIngredients(
                recipe.value().getIngredients()
        ), Collections.singletonList(EntryIngredients.of(
                recipe.value().getResultItem(BasicDisplay.registryAccess())
        )), recipe.value().getExperience(), recipe.id());
    }

    public StoveDisplay(List<EntryIngredient> input, List<EntryIngredient> output, float xp, ResourceLocation id) {
        super(input, output, Optional.of(id));
        this.xp = xp;
    }

    public float getXp() {
        return xp;
    }

    @Override
    public int getWidth() {
        return 3;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return STOVE_DISPLAY;
    }
}