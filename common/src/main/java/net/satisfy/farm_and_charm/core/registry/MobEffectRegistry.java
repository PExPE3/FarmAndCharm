package net.satisfy.farm_and_charm.core.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.satisfy.farm_and_charm.FarmAndCharm;
import net.satisfy.farm_and_charm.core.effect.*;

import java.util.function.Supplier;

public class MobEffectRegistry {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(FarmAndCharm.MOD_ID, Registries.MOB_EFFECT);

    public static final RegistrySupplier<MobEffect> SWEETS = registerEffect("sweets", SweetsEffect::new);
    public static final RegistrySupplier<MobEffect> HORSE_FODDER = registerEffect("horse_fodder", HorseFodderEffect::new);
    public static final RegistrySupplier<MobEffect> DOG_FOOD = registerEffect("dog_food", DogFoodEffect::new);
    public static final RegistrySupplier<MobEffect> CLUCK = registerEffect("cluck", ChickenEffect::new);
    public static final RegistrySupplier<MobEffect> GRANDMAS_BLESSING = registerEffect("grandmas_blessing", GrandmasBlessingEffect::new);
    public static final RegistrySupplier<MobEffect> RESTED = registerEffect("rested", RestedEffect::new);
    public static final RegistrySupplier<MobEffect> FARMERS_BLESSING = registerEffect("farmers_blessing", FarmersBlessingEffect::new);
    public static final RegistrySupplier<MobEffect> SUSTENANCE = registerEffect("sustenance", SustenanceEffect::new);
    public static final RegistrySupplier<MobEffect> SATIATION = registerEffect("satiation", SatiationEffect::new);
    public static final RegistrySupplier<MobEffect> FEAST = registerEffect("feast", FeastEffect::new);

    private static RegistrySupplier<MobEffect> registerEffect(final String path, final Supplier<? extends MobEffect> type) {
        return MOB_EFFECTS.register(FarmAndCharm.id(path), type);
    }

    public static Holder<MobEffect> getReference(RegistrySupplier<MobEffect> input) {
        return MOB_EFFECTS.getRegistrar().getHolder(input.getId());
    }

    public static void init() {
        MOB_EFFECTS.register();
    }
}