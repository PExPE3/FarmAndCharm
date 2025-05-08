package net.satisfy.farm_and_charm.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.satisfy.farm_and_charm.FarmAndCharm;
import net.satisfy.farm_and_charm.core.registry.CompostableRegistry;
import net.satisfy.farm_and_charm.fabric.core.config.FarmAndCharmConfigFabric;
import net.satisfy.farm_and_charm.fabric.core.world.FarmAndCharmBiomeModification;

public class FarmAndCharmFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        AutoConfig.register(FarmAndCharmConfigFabric.class, GsonConfigSerializer::new);

        FarmAndCharm.init();
        CompostableRegistry.registerCompostable();
        FarmAndCharmBiomeModification.init();
    }
}
