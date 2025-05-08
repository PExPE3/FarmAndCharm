package net.satisfy.farm_and_charm.platform.forge;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.satisfy.farm_and_charm.FarmAndCharm;
import net.satisfy.farm_and_charm.neoforge.config.FarmAndCharmConfigNeo;

import java.util.function.Supplier;

public class PlatformHelperImpl {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(FarmAndCharm.MOD_ID, Registries.MOB_EFFECT);

    public static boolean isBonemealEffectEnabled() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.ENABLE_BONEMEAL_EFFECT);
    }

    public static int getWaterSprinklerRange() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.WATER_SPRINKLER_RANGE);
    }

    public static boolean isRainGrowthEffectEnabled() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.ENABLE_RAIN_GROWTH_EFFECT);
    }

    public static float getRainGrowthMultiplier() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.RAIN_GROWTH_MULTIPLIER).floatValue();
    }

    public static int getFeedingTroughRange() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.FEEDING_TROUGH_RANGE);
    }

    public static int getFertilizedSoilRange() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.FERTILIZED_SOIL_RANGE);
    }

    public static int getNutrition(String itemName) {
        return switch (itemName) {
            case "oat_pancake" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.OAT_PANCAKE_NUTRITION);
            case "roasted_corn" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.ROASTED_CORN_NUTRITION);
            case "potato_with_roast_meat" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.POTATO_WITH_ROAST_MEAT_NUTRITION);
            case "baked_lamb_ham" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.BAKED_LAMB_HAM_NUTRITION);
            case "farmers_breakfast" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.FARMERS_BREAKFAST_NUTRITION);
            case "stuffed_chicken" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.STUFFED_CHICKEN_NUTRITION);
            case "stuffed_rabbit" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.STUFFED_RABBIT_NUTRITION);
            case "grandmothers_strawberry_cake" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.GRANDMOTHERS_STRAWBERRY_CAKE_NUTRITION);
            case "farmers_bread" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.FARMERS_BREAD_NUTRITION);
            case "farmer_salad" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.FARMER_SALAD_NUTRITION);
            case "goulash" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.GOULASH_NUTRITION);
            case "simple_tomato_soup" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.SIMPLE_TOMATO_SOUP_NUTRITION);
            case "barley_soup" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.BARLEY_SOUP_NUTRITION);
            case "onion_soup" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.ONION_SOUP_NUTRITION);
            case "potato_soup" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.POTATO_SOUP_NUTRITION);
            case "pasta_with_onion_sauce" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.PASTA_WITH_ONION_SAUCE_NUTRITION);
            case "corn_grits" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.CORN_GRITS_NUTRITION);
            case "oatmeal_with_strawberries" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.OATMEAL_WITH_STRAWBERRIES_NUTRITION);
            case "sausage_with_oat_patty" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.SAUSAGE_WITH_OAT_PATTY_NUTRITION);
            case "lamb_with_corn" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.LAMB_WITH_CORN_NUTRITION);
            case "beef_patty_with_vegetables" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.BEEF_PATTY_WITH_VEGETABLES_NUTRITION);
            case "barley_patties_with_potatoes" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.BARLEY_PATTIES_WITH_POTATOES_NUTRITION);
            case "bacon_with_eggs" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.BACON_WITH_EGGS_NUTRITION);
            case "chicken_wrapped_in_bacon" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.CHICKEN_WRAPPED_IN_BACON_NUTRITION);
            case "cooked_salmon" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.COOKED_SALMON_NUTRITION);
            case "cooked_cod" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.COOKED_COD_NUTRITION);
            case "roasted_chicken" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.ROASTED_CHICKEN_NUTRITION);
            default -> 0;
        };
    }

    public static float getSaturationMod(String itemName) {
        return switch (itemName) {
            case "oat_pancake" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.OAT_PANCAKE_SATURATION_MOD).floatValue();
            case "roasted_corn" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.ROASTED_CORN_SATURATION_MOD).floatValue();
            case "potato_with_roast_meat" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.POTATO_WITH_ROAST_MEAT_SATURATION_MOD).floatValue();
            case "baked_lamb_ham" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.BAKED_LAMB_HAM_SATURATION_MOD).floatValue();
            case "farmers_breakfast" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.FARMERS_BREAKFAST_SATURATION_MOD).floatValue();
            case "stuffed_chicken" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.STUFFED_CHICKEN_SATURATION_MOD).floatValue();
            case "stuffed_rabbit" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.STUFFED_RABBIT_SATURATION_MOD).floatValue();
            case "grandmothers_strawberry_cake" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.GRANDMOTHERS_STRAWBERRY_CAKE_SATURATION_MOD).floatValue();
            case "farmers_bread" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.FARMERS_BREAD_SATURATION_MOD).floatValue();
            case "farmer_salad" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.FARMER_SALAD_SATURATION_MOD).floatValue();
            case "goulash" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.GOULASH_SATURATION_MOD).floatValue();
            case "simple_tomato_soup" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.SIMPLE_TOMATO_SOUP_SATURATION_MOD).floatValue();
            case "barley_soup" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.BARLEY_SOUP_SATURATION_MOD).floatValue();
            case "onion_soup" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.ONION_SOUP_SATURATION_MOD).floatValue();
            case "potato_soup" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.POTATO_SOUP_SATURATION_MOD).floatValue();
            case "pasta_with_onion_sauce" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.PASTA_WITH_ONION_SAUCE_SATURATION_MOD).floatValue();
            case "corn_grits" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.CORN_GRITS_SATURATION_MOD).floatValue();
            case "oatmeal_with_strawberries" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.OATMEAL_WITH_STRAWBERRIES_SATURATION_MOD).floatValue();
            case "sausage_with_oat_patty" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.SAUSAGE_WITH_OAT_PATTY_SATURATION_MOD).floatValue();
            case "lamb_with_corn" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.LAMB_WITH_CORN_SATURATION_MOD).floatValue();
            case "beef_patty_with_vegetables" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.BEEF_PATTY_WITH_VEGETABLES_SATURATION_MOD).floatValue();
            case "barley_patties_with_potatoes" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.BARLEY_PATTIES_WITH_POTATOES_SATURATION_MOD).floatValue();
            case "bacon_with_eggs" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.BACON_WITH_EGGS_SATURATION_MOD).floatValue();
            case "chicken_wrapped_in_bacon" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.CHICKEN_WRAPPED_IN_BACON_SATURATION_MOD).floatValue();
            case "cooked_salmon" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.COOKED_SALMON_SATURATION_MOD).floatValue();
            case "cooked_cod" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.COOKED_COD_SATURATION_MOD).floatValue();
            case "roasted_chicken" -> FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.ROASTED_CHICKEN_SATURATION_MOD).floatValue();
            default -> 0.0f;
        };
    }

    public static boolean isTamingEnabled() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.ENABLE_TAMING);
    }

    public static boolean isHorseTamingEnabled() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.ENABLE_HORSE_TAMING);
    }

    public static boolean isHorseEffectsEnabled() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.ENABLE_HORSE_EFFECTS);
    }

    public static boolean isChickenEffectsEnabled() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.ENABLE_CHICKEN_EFFECTS);
    }

    public static boolean enableCatTamingChance() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.ENABLE_CAT_TAMING_CHANCE);
    }

    public static boolean isFertilizerEnabled() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.ENABLE_FERTILIZER);
    }

    public static int getChickenEffectTickInterval() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.CHICKEN_EFFECT_TICK_INTERVAL);
    }

    public static int getChickenEffectEggChance() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.CHICKEN_EFFECT_EGG_CHANCE);
    }

    public static int getChickenEffectFeatherChance() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.CHICKEN_EFFECT_FEATHER_CHANCE);
    }

    public static int getFeastEffectSatiationInterval() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.FEAST_EFFECT_SATIATION_INTERVAL);
    }

    public static int getFeastEffectSustenanceInterval() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.FEAST_EFFECT_SUSTENANCE_INTERVAL);
    }

    public static int getFeastEffectHealAmount() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.FEAST_EFFECT_HEAL_AMOUNT);
    }

    public static int getSustenanceEffectInterval() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.SUSTENANCE_EFFECT_INTERVAL);
    }

    public static int getSustenanceEffectHealAmount() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.SUSTENANCE_EFFECT_HEAL_AMOUNT);
    }

    public static int getSustenanceEffectFoodIncrement() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.SUSTENANCE_EFFECT_FOOD_INCREMENT);
    }

    public static int getSatiationEffectInterval() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.SATIATION_EFFECT_INTERVAL);
    }

    public static int getSatiationEffectHealAmount() {
        return FarmAndCharmConfigNeo.orDefault(FarmAndCharmConfigNeo.SATIATION_EFFECT_HEAL_AMOUNT);
    }

    public static Holder<MobEffect> registerEffect(String name, Supplier<MobEffect> effect) {
        return MOB_EFFECTS.register(name, effect);
    }
}
