package net.satisfy.farm_and_charm.core.recipe;

import com.google.gson.JsonParseException;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.Container;
import net.minecraft.world.level.Level;
import net.satisfy.farm_and_charm.core.registry.RecipeTypeRegistry;
import net.satisfy.farm_and_charm.core.util.GeneralUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RoasterRecipe implements Recipe<Container> {
    private final ResourceLocation id;
    private final List<Sequence> sequences;
    private final ItemStack output;

    public RoasterRecipe(ResourceLocation id, List<Sequence> sequences, ItemStack output) {
        this.id = id;
        this.sequences = sequences;
        this.output = output;
    }

    @Override
    public boolean matches(Container inventory, Level world) {
        if (sequences.isEmpty()) return false;
        Sequence firstSequence = sequences.get(0);
        return GeneralUtil.matchesIngredients(inventory, firstSequence.getIngredients(), 0, 6);
    }

    @Override
    public @NotNull ItemStack assemble(Container container, RegistryAccess registryAccess) {
        return getResultItem(registryAccess).copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.output.copy();
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return RecipeTypeRegistry.ROASTER_RECIPE_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return RecipeTypeRegistry.ROASTER_RECIPE_TYPE.get();
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> allIngredients = NonNullList.create();
        for (Sequence seq : sequences) {
            allIngredients.addAll(seq.getIngredients());
        }
        return allIngredients;
    }

    public List<Sequence> getSequences() {
        return sequences;
    }

    public static class Serializer implements RecipeSerializer<RoasterRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public @NotNull RoasterRecipe fromJson(ResourceLocation id, JsonObject json) {
            JsonArray sequencesJson = GsonHelper.getAsJsonArray(json, "sequences");
            List<Sequence> sequences = new ArrayList<>();
            for (var element : sequencesJson) {
                sequences.add(Sequence.fromJson(element.getAsJsonObject()));
            }
            if (sequences.isEmpty()) {
                throw new JsonParseException("No sequences for Roaster Recipe");
            }
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            return new RoasterRecipe(id, sequences, result);
        }

        @Override
        public @NotNull RoasterRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            int sequenceCount = buf.readVarInt();
            List<Sequence> sequences = new ArrayList<>();
            for (int i = 0; i < sequenceCount; i++) {
                int ingredientCount = buf.readVarInt();
                List<Ingredient> ingredients = new ArrayList<>();
                for (int j = 0; j < ingredientCount; j++) {
                    ingredients.add(Ingredient.fromNetwork(buf));
                }
                int duration = buf.readVarInt();
                sequences.add(new Sequence(ingredients, duration));
            }
            ItemStack output = buf.readItem();
            return new RoasterRecipe(id, sequences, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, RoasterRecipe recipe) {
            buf.writeVarInt(recipe.sequences.size());
            for (Sequence seq : recipe.sequences) {
                buf.writeVarInt(seq.getIngredients().size());
                for (Ingredient ing : seq.getIngredients()) {
                    ing.toNetwork(buf);
                }
                buf.writeVarInt(seq.getDuration());
            }
            buf.writeItem(recipe.output);
        }
    }
}
