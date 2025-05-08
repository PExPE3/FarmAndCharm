package net.satisfy.farm_and_charm.neoforge.config;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.*;
import net.satisfy.farm_and_charm.FarmAndCharm;

@EventBusSubscriber(modid = FarmAndCharm.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class FarmAndCharmConfigNeo {
    public static final ModConfigSpec COMMON_CONFIG;

    public static final BooleanValue ENABLE_BONEMEAL_EFFECT;
    public static final IntValue FERTILIZED_SOIL_RANGE;
    public static final IntValue WATER_SPRINKLER_RANGE;
    public static final BooleanValue ENABLE_RAIN_GROWTH_EFFECT;
    public static final DoubleValue RAIN_GROWTH_MULTIPLIER;
    public static final IntValue FEEDING_TROUGH_RANGE;

    public static final BooleanValue ENABLE_FERTILIZER;
    public static final BooleanValue ENABLE_TAMING;
    public static final BooleanValue ENABLE_HORSE_TAMING;
    public static final BooleanValue ENABLE_HORSE_EFFECTS;
    public static final BooleanValue ENABLE_CHICKEN_EFFECTS;
    public static final BooleanValue ENABLE_CAT_TAMING_CHANCE;

    public static final IntValue OAT_PANCAKE_NUTRITION;
    public static final DoubleValue OAT_PANCAKE_SATURATION_MOD;
    public static final IntValue ROASTED_CORN_NUTRITION;
    public static final DoubleValue ROASTED_CORN_SATURATION_MOD;
    public static final IntValue POTATO_WITH_ROAST_MEAT_NUTRITION;
    public static final DoubleValue POTATO_WITH_ROAST_MEAT_SATURATION_MOD;
    public static final IntValue BAKED_LAMB_HAM_NUTRITION;
    public static final DoubleValue BAKED_LAMB_HAM_SATURATION_MOD;
    public static final IntValue FARMERS_BREAKFAST_NUTRITION;
    public static final DoubleValue FARMERS_BREAKFAST_SATURATION_MOD;
    public static final IntValue STUFFED_CHICKEN_NUTRITION;
    public static final DoubleValue STUFFED_CHICKEN_SATURATION_MOD;
    public static final IntValue STUFFED_RABBIT_NUTRITION;
    public static final DoubleValue STUFFED_RABBIT_SATURATION_MOD;
    public static final IntValue GRANDMOTHERS_STRAWBERRY_CAKE_NUTRITION;
    public static final DoubleValue GRANDMOTHERS_STRAWBERRY_CAKE_SATURATION_MOD;
    public static final IntValue FARMERS_BREAD_NUTRITION;
    public static final DoubleValue FARMERS_BREAD_SATURATION_MOD;
    public static final IntValue FARMER_SALAD_NUTRITION;
    public static final DoubleValue FARMER_SALAD_SATURATION_MOD;
    public static final IntValue GOULASH_NUTRITION;
    public static final DoubleValue GOULASH_SATURATION_MOD;
    public static final IntValue SIMPLE_TOMATO_SOUP_NUTRITION;
    public static final DoubleValue SIMPLE_TOMATO_SOUP_SATURATION_MOD;
    public static final IntValue BARLEY_SOUP_NUTRITION;
    public static final DoubleValue BARLEY_SOUP_SATURATION_MOD;
    public static final IntValue ONION_SOUP_NUTRITION;
    public static final DoubleValue ONION_SOUP_SATURATION_MOD;
    public static final IntValue POTATO_SOUP_NUTRITION;
    public static final DoubleValue POTATO_SOUP_SATURATION_MOD;
    public static final IntValue PASTA_WITH_ONION_SAUCE_NUTRITION;
    public static final DoubleValue PASTA_WITH_ONION_SAUCE_SATURATION_MOD;
    public static final IntValue CORN_GRITS_NUTRITION;
    public static final DoubleValue CORN_GRITS_SATURATION_MOD;
    public static final IntValue OATMEAL_WITH_STRAWBERRIES_NUTRITION;
    public static final DoubleValue OATMEAL_WITH_STRAWBERRIES_SATURATION_MOD;
    public static final IntValue SAUSAGE_WITH_OAT_PATTY_NUTRITION;
    public static final DoubleValue SAUSAGE_WITH_OAT_PATTY_SATURATION_MOD;
    public static final IntValue LAMB_WITH_CORN_NUTRITION;
    public static final DoubleValue LAMB_WITH_CORN_SATURATION_MOD;
    public static final IntValue BEEF_PATTY_WITH_VEGETABLES_NUTRITION;
    public static final DoubleValue BEEF_PATTY_WITH_VEGETABLES_SATURATION_MOD;
    public static final IntValue BARLEY_PATTIES_WITH_POTATOES_NUTRITION;
    public static final DoubleValue BARLEY_PATTIES_WITH_POTATOES_SATURATION_MOD;
    public static final IntValue BACON_WITH_EGGS_NUTRITION;
    public static final DoubleValue BACON_WITH_EGGS_SATURATION_MOD;
    public static final IntValue CHICKEN_WRAPPED_IN_BACON_NUTRITION;
    public static final DoubleValue CHICKEN_WRAPPED_IN_BACON_SATURATION_MOD;
    public static final IntValue COOKED_SALMON_NUTRITION;
    public static final DoubleValue COOKED_SALMON_SATURATION_MOD;
    public static final IntValue COOKED_COD_NUTRITION;
    public static final DoubleValue COOKED_COD_SATURATION_MOD;
    public static final IntValue ROASTED_CHICKEN_NUTRITION;
    public static final DoubleValue ROASTED_CHICKEN_SATURATION_MOD;

    public static final IntValue CHICKEN_EFFECT_TICK_INTERVAL;
    public static final IntValue CHICKEN_EFFECT_EGG_CHANCE;
    public static final IntValue CHICKEN_EFFECT_FEATHER_CHANCE;
    public static final IntValue FEAST_EFFECT_SATIATION_INTERVAL;
    public static final IntValue FEAST_EFFECT_SUSTENANCE_INTERVAL;
    public static final IntValue FEAST_EFFECT_HEAL_AMOUNT;
    public static final IntValue SUSTENANCE_EFFECT_INTERVAL;
    public static final IntValue SUSTENANCE_EFFECT_HEAL_AMOUNT;
    public static final IntValue SUSTENANCE_EFFECT_FOOD_INCREMENT;
    public static final IntValue SATIATION_EFFECT_INTERVAL;
    public static final IntValue SATIATION_EFFECT_HEAL_AMOUNT;

    static {
        Builder COMMON_BUILDER = new Builder();

        COMMON_BUILDER.push("Blocks");

        ENABLE_BONEMEAL_EFFECT = COMMON_BUILDER.define("enableBonemealEffect", true);
        FERTILIZED_SOIL_RANGE = COMMON_BUILDER.defineInRange("fertilizedSoilRange", 5, 1, 16);
        WATER_SPRINKLER_RANGE = COMMON_BUILDER.defineInRange("waterSprinklerRange", 8, 1, 32);
        ENABLE_RAIN_GROWTH_EFFECT = COMMON_BUILDER.define("enableRainGrowthEffect", true);
        RAIN_GROWTH_MULTIPLIER = COMMON_BUILDER.defineInRange("rainGrowthMultiplier", 0.5, 0.1, 2.0);
        FEEDING_TROUGH_RANGE = COMMON_BUILDER.defineInRange("feedingTroughRange", 8, 1, 16);

        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("Items");

        ENABLE_FERTILIZER = COMMON_BUILDER.define("enableFertilizer", true);
        ENABLE_TAMING = COMMON_BUILDER.define("enableDogFoodTaming", true);
        ENABLE_HORSE_TAMING = COMMON_BUILDER.define("enableHorseTaming", true);
        ENABLE_HORSE_EFFECTS = COMMON_BUILDER.define("enableHorseEffects", true);
        ENABLE_CHICKEN_EFFECTS = COMMON_BUILDER.define("enableChickenEffects", true);
        ENABLE_CAT_TAMING_CHANCE = COMMON_BUILDER.define("enableCatTamingChance", true);

        COMMON_BUILDER.push("Nutrition");

        OAT_PANCAKE_NUTRITION = COMMON_BUILDER.defineInRange("oatPancakeNutrition", 5, 0, Integer.MAX_VALUE);
        OAT_PANCAKE_SATURATION_MOD = COMMON_BUILDER.defineInRange("oatPancakeSaturationMod", 0.6, 0.0, Double.MAX_VALUE);
        ROASTED_CORN_NUTRITION = COMMON_BUILDER.defineInRange("roastedCornNutrition", 5, 0, Integer.MAX_VALUE);
        ROASTED_CORN_SATURATION_MOD = COMMON_BUILDER.defineInRange("roastedCornSaturationMod", 0.5, 0.0, Double.MAX_VALUE);
        POTATO_WITH_ROAST_MEAT_NUTRITION = COMMON_BUILDER.defineInRange("potatoWithRoastMeatNutrition", 7, 0, Integer.MAX_VALUE);
        POTATO_WITH_ROAST_MEAT_SATURATION_MOD = COMMON_BUILDER.defineInRange("potatoWithRoastMeatSaturationMod", 0.7, 0.0, Double.MAX_VALUE);
        BAKED_LAMB_HAM_NUTRITION = COMMON_BUILDER.defineInRange("bakedLambHamNutrition", 8, 0, Integer.MAX_VALUE);
        BAKED_LAMB_HAM_SATURATION_MOD = COMMON_BUILDER.defineInRange("bakedLambHamSaturationMod", 0.9, 0.0, Double.MAX_VALUE);
        FARMERS_BREAKFAST_NUTRITION = COMMON_BUILDER.defineInRange("farmersBreakfastNutrition", 12, 0, Integer.MAX_VALUE);
        FARMERS_BREAKFAST_SATURATION_MOD = COMMON_BUILDER.defineInRange("farmersBreakfastSaturationMod", 1.2, 0.0, Double.MAX_VALUE);
        STUFFED_CHICKEN_NUTRITION = COMMON_BUILDER.defineInRange("stuffedChickenNutrition", 8, 0, Integer.MAX_VALUE);
        STUFFED_CHICKEN_SATURATION_MOD = COMMON_BUILDER.defineInRange("stuffedChickenSaturationMod", 0.8, 0.0, Double.MAX_VALUE);
        STUFFED_RABBIT_NUTRITION = COMMON_BUILDER.defineInRange("stuffedRabbitNutrition", 9, 0, Integer.MAX_VALUE);
        STUFFED_RABBIT_SATURATION_MOD = COMMON_BUILDER.defineInRange("stuffedRabbitSaturationMod", 0.9, 0.0, Double.MAX_VALUE);
        GRANDMOTHERS_STRAWBERRY_CAKE_NUTRITION = COMMON_BUILDER.defineInRange("grandmothersStrawberryCakeNutrition", 4, 0, Integer.MAX_VALUE);
        GRANDMOTHERS_STRAWBERRY_CAKE_SATURATION_MOD = COMMON_BUILDER.defineInRange("grandmothersStrawberryCakeSaturationMod", 0.7, 0.0, Double.MAX_VALUE);
        FARMERS_BREAD_NUTRITION = COMMON_BUILDER.defineInRange("farmersBreadNutrition", 6, 0, Integer.MAX_VALUE);
        FARMERS_BREAD_SATURATION_MOD = COMMON_BUILDER.defineInRange("farmersBreadSaturationMod", 0.8, 0.0, Double.MAX_VALUE);
        FARMER_SALAD_NUTRITION = COMMON_BUILDER.defineInRange("farmerSaladNutrition", 7, 0, Integer.MAX_VALUE);
        FARMER_SALAD_SATURATION_MOD = COMMON_BUILDER.defineInRange("farmerSaladSaturationMod", 0.6, 0.0, Double.MAX_VALUE);
        GOULASH_NUTRITION = COMMON_BUILDER.defineInRange("goulashNutrition", 8, 0, Integer.MAX_VALUE);
        GOULASH_SATURATION_MOD = COMMON_BUILDER.defineInRange("goulashSaturationMod", 0.9, 0.0, Double.MAX_VALUE);
        SIMPLE_TOMATO_SOUP_NUTRITION = COMMON_BUILDER.defineInRange("simpleTomatoSoupNutrition", 6, 0, Integer.MAX_VALUE);
        SIMPLE_TOMATO_SOUP_SATURATION_MOD = COMMON_BUILDER.defineInRange("simpleTomatoSoupSaturationMod", 0.6, 0.0, Double.MAX_VALUE);
        BARLEY_SOUP_NUTRITION = COMMON_BUILDER.defineInRange("barleySoupNutrition", 5, 0, Integer.MAX_VALUE);
        BARLEY_SOUP_SATURATION_MOD = COMMON_BUILDER.defineInRange("barleySoupSaturationMod", 0.8, 0.0, Double.MAX_VALUE);
        ONION_SOUP_NUTRITION = COMMON_BUILDER.defineInRange("onionSoupNutrition", 7, 0, Integer.MAX_VALUE);
        ONION_SOUP_SATURATION_MOD = COMMON_BUILDER.defineInRange("onionSoupSaturationMod", 0.6, 0.0, Double.MAX_VALUE);
        POTATO_SOUP_NUTRITION = COMMON_BUILDER.defineInRange("potatoSoupNutrition", 5, 0, Integer.MAX_VALUE);
        POTATO_SOUP_SATURATION_MOD = COMMON_BUILDER.defineInRange("potatoSoupSaturationMod", 0.6, 0.0, Double.MAX_VALUE);
        PASTA_WITH_ONION_SAUCE_NUTRITION = COMMON_BUILDER.defineInRange("pastaWithOnionSauceNutrition", 6, 0, Integer.MAX_VALUE);
        PASTA_WITH_ONION_SAUCE_SATURATION_MOD = COMMON_BUILDER.defineInRange("pastaWithOnionSauceSaturationMod", 0.7, 0.0, Double.MAX_VALUE);
        CORN_GRITS_NUTRITION = COMMON_BUILDER.defineInRange("cornGritsNutrition", 6, 0, Integer.MAX_VALUE);
        CORN_GRITS_SATURATION_MOD = COMMON_BUILDER.defineInRange("cornGritsSaturationMod", 0.5, 0.0, Double.MAX_VALUE);
        OATMEAL_WITH_STRAWBERRIES_NUTRITION = COMMON_BUILDER.defineInRange("oatmealWithStrawberriesNutrition", 4, 0, Integer.MAX_VALUE);
        OATMEAL_WITH_STRAWBERRIES_SATURATION_MOD = COMMON_BUILDER.defineInRange("oatmealWithStrawberriesSaturationMod", 0.8, 0.0, Double.MAX_VALUE);
        SAUSAGE_WITH_OAT_PATTY_NUTRITION = COMMON_BUILDER.defineInRange("sausageWithOatPattyNutrition", 8, 0, Integer.MAX_VALUE);
        SAUSAGE_WITH_OAT_PATTY_SATURATION_MOD = COMMON_BUILDER.defineInRange("sausageWithOatPattySaturationMod", 0.9, 0.0, Double.MAX_VALUE);
        LAMB_WITH_CORN_NUTRITION = COMMON_BUILDER.defineInRange("lambWithCornNutrition", 8, 0, Integer.MAX_VALUE);
        LAMB_WITH_CORN_SATURATION_MOD = COMMON_BUILDER.defineInRange("lambWithCornSaturationMod", 0.8, 0.0, Double.MAX_VALUE);
        BEEF_PATTY_WITH_VEGETABLES_NUTRITION = COMMON_BUILDER.defineInRange("beefPattyWithVegetablesNutrition", 6, 0, Integer.MAX_VALUE);
        BEEF_PATTY_WITH_VEGETABLES_SATURATION_MOD = COMMON_BUILDER.defineInRange("beefPattyWithVegetablesSaturationMod", 0.8, 0.0, Double.MAX_VALUE);
        BARLEY_PATTIES_WITH_POTATOES_NUTRITION = COMMON_BUILDER.defineInRange("barleyPattiesWithPotatoesNutrition", 5, 0, Integer.MAX_VALUE);
        BARLEY_PATTIES_WITH_POTATOES_SATURATION_MOD = COMMON_BUILDER.defineInRange("barleyPattiesWithPotatoesSaturationMod", 0.9, 0.0, Double.MAX_VALUE);
        BACON_WITH_EGGS_NUTRITION = COMMON_BUILDER.defineInRange("baconWithEggsNutrition", 6, 0, Integer.MAX_VALUE);
        BACON_WITH_EGGS_SATURATION_MOD = COMMON_BUILDER.defineInRange("baconWithEggsSaturationMod", 0.7, 0.0, Double.MAX_VALUE);
        CHICKEN_WRAPPED_IN_BACON_NUTRITION = COMMON_BUILDER.defineInRange("chickenWrappedInBaconNutrition", 9, 0, Integer.MAX_VALUE);
        CHICKEN_WRAPPED_IN_BACON_SATURATION_MOD = COMMON_BUILDER.defineInRange("chickenWrappedInBaconSaturationMod", 0.9, 0.0, Double.MAX_VALUE);
        COOKED_SALMON_NUTRITION = COMMON_BUILDER.defineInRange("cookedSalmonNutrition", 7, 0, Integer.MAX_VALUE);
        COOKED_SALMON_SATURATION_MOD = COMMON_BUILDER.defineInRange("cookedSalmonSaturationMod", 0.9, 0.0, Double.MAX_VALUE);
        COOKED_COD_NUTRITION = COMMON_BUILDER.defineInRange("cookedCodNutrition", 7, 0, Integer.MAX_VALUE);
        COOKED_COD_SATURATION_MOD = COMMON_BUILDER.defineInRange("cookedCodSaturationMod", 0.9, 0.0, Double.MAX_VALUE);
        ROASTED_CHICKEN_NUTRITION = COMMON_BUILDER.defineInRange("roastedChickenNutrition", 5, 0, Integer.MAX_VALUE);
        ROASTED_CHICKEN_SATURATION_MOD = COMMON_BUILDER.defineInRange("roastedChickenSaturationMod", 0.8, 0.0, Double.MAX_VALUE);

        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("Effects");

        CHICKEN_EFFECT_TICK_INTERVAL = COMMON_BUILDER.defineInRange("chickenEffectTickInterval", 120, 10, 600);
        CHICKEN_EFFECT_EGG_CHANCE = COMMON_BUILDER.defineInRange("chickenEffectEggChance", 20, 0, 100);
        CHICKEN_EFFECT_FEATHER_CHANCE = COMMON_BUILDER.defineInRange("chickenEffectFeatherChance", 20, 0, 100);
        FEAST_EFFECT_SATIATION_INTERVAL = COMMON_BUILDER.defineInRange("feastEffectSatiationInterval", 40, 10, 200);
        FEAST_EFFECT_SUSTENANCE_INTERVAL = COMMON_BUILDER.defineInRange("feastEffectSustenanceInterval", 200, 50, 600);
        FEAST_EFFECT_HEAL_AMOUNT = COMMON_BUILDER.defineInRange("feastEffectHealAmount", 1, 1, 5);
        SUSTENANCE_EFFECT_INTERVAL = COMMON_BUILDER.defineInRange("sustenanceEffectInterval", 200, 50, 600);
        SUSTENANCE_EFFECT_HEAL_AMOUNT = COMMON_BUILDER.defineInRange("sustenanceEffectHealAmount", 1, 1, 5);
        SUSTENANCE_EFFECT_FOOD_INCREMENT = COMMON_BUILDER.defineInRange("sustenanceEffectFoodIncrement", 1, 1, 20);
        SATIATION_EFFECT_INTERVAL = COMMON_BUILDER.defineInRange("satiationEffectInterval", 40, 10, 200);
        SATIATION_EFFECT_HEAL_AMOUNT = COMMON_BUILDER.defineInRange("satiationEffectHealAmount", 1, 1, 5);

        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        COMMON_CONFIG.acceptConfig(event.getConfig().getLoadedConfig());
    }

    public static <T> T orDefault(ConfigValue<T> val) {
        return COMMON_CONFIG.isLoaded() ? val.get() : val.getDefault();
    }
}
