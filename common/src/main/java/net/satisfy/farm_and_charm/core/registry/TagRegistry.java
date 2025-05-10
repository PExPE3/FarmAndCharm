package net.satisfy.farm_and_charm.core.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.satisfy.farm_and_charm.FarmAndCharm;

@SuppressWarnings("unused")
public class TagRegistry {
    public static final TagKey<Block> FARMLAND = TagKey.create(Registries.BLOCK, FarmAndCharm.id("farmland"));
    public static final TagKey<Block> SUPPRESS_CAMPFIRE_SMOKE_PARTICLES = TagKey.create(Registries.BLOCK, FarmAndCharm.id("suppress_campfire_smoke_particles"));
    public static final TagKey<Block> COOKING_POTS = TagKey.create(Registries.BLOCK, FarmAndCharm.id("cooking_pots"));
    public static final TagKey<Block> ALLOWS_COOKING = TagKey.create(Registries.BLOCK, FarmAndCharm.id("allows_cooking"));
    public static final TagKey<Block> WILD_CROPS = TagKey.create(Registries.BLOCK, FarmAndCharm.id("wild_crops"));
    public static final TagKey<Item> HANGABLE = TagKey.create(Registries.ITEM, FarmAndCharm.id("hangable"));
    public static final TagKey<Item> CONTAINER = TagKey.create(Registries.ITEM, FarmAndCharm.id("container"));
    public static final TagKey<Item> FEEDING_TROUGH_FODDER = TagKey.create(Registries.ITEM, FarmAndCharm.id("feeding_trough_fodder"));
}
