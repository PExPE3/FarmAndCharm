package net.satisfy.farm_and_charm.core.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class Sequence {
    private final List<Ingredient> ingredients;
    private final int duration;

    public Sequence(List<Ingredient> ingredients, int duration) {
        this.ingredients = ingredients;
        this.duration = duration;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public int getDuration() {
        return duration;
    }

    public static Sequence fromJson(JsonObject json) {
        JsonArray ingredientsJson = json.getAsJsonArray("ingredients");
        List<Ingredient> ingredients = new ArrayList<>();
        for (var element : ingredientsJson) {
            ingredients.add(Ingredient.fromJson(element.getAsJsonObject()));
        }
        int duration = json.get("duration").getAsInt();
        return new Sequence(ingredients, duration);
    }
}
