package net.satisfy.farm_and_charm.core.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.satisfy.farm_and_charm.core.registry.RecipeTypeRegistry;
import net.satisfy.farm_and_charm.core.util.GeneralUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StoveRecipe implements Recipe<RecipeInput> {

    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;
    private final float experience;
    private final boolean requiresLearning;

    public StoveRecipe(List<Ingredient> inputs, ItemStack output, float experience, boolean requiresLearning) {
        this.inputs = GeneralUtil.nonNullList(inputs, Ingredient.class);
        this.output = output;
        this.experience = experience;
        this.requiresLearning = requiresLearning;
    }

    @Override
    public boolean matches(RecipeInput recipeInput, Level level) {
        return GeneralUtil.matchesRecipe(recipeInput, inputs, 0, 2);
    }

    @Override
    public @NotNull ItemStack assemble(RecipeInput recipeInput, HolderLookup.Provider provider) {
        return this.output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    public ItemStack getOutput() {
        return output.copy();
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.output.copy();
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return RecipeTypeRegistry.STOVE_RECIPE_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return RecipeTypeRegistry.STOVE_RECIPE_TYPE.get();
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return this.inputs;
    }

    public float getExperience() {
        return experience;
    }

    public boolean requiresLearning() {
        return requiresLearning;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public static class Serializer implements RecipeSerializer<StoveRecipe> {
        @Override
        public @NotNull MapCodec<StoveRecipe> codec() {
            return RecordCodecBuilder.mapCodec(obj -> obj.group(
                    Ingredient.CODEC.listOf().fieldOf("ingredients").validate(list -> {
                        if (list.isEmpty()) return DataResult.error(() -> "No ingredients for Roaster Recipe");
                        else if (list.size() > 3) return DataResult.error(() -> "Too many ingredients for Roaster Recipe");
                        return DataResult.success(list);
                    }).forGetter(r -> r.getIngredients().subList(0, 2)),
                    ItemStack.CODEC.fieldOf("result").forGetter(StoveRecipe::getOutput),
                    Codec.FLOAT.fieldOf("experience").orElse(0F).forGetter(StoveRecipe::getExperience),
                    Codec.BOOL.fieldOf("requiresLearning").orElse(false).forGetter(StoveRecipe::requiresLearning)
            ).apply(obj, StoveRecipe::new));
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, StoveRecipe> streamCodec() {
            return ByteBufCodecs.fromCodecWithRegistries(codec().codec());
        }
        
    }
}
