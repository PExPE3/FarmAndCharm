package net.satisfy.farm_and_charm.core.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfy.farm_and_charm.core.block.RoasterBlock;
import net.satisfy.farm_and_charm.client.gui.handler.RoasterGuiHandler;
import net.satisfy.farm_and_charm.core.recipe.RoasterRecipe;
import net.satisfy.farm_and_charm.core.recipe.Sequence;
import net.satisfy.farm_and_charm.core.registry.EntityTypeRegistry;
import net.satisfy.farm_and_charm.core.registry.RecipeTypeRegistry;
import net.satisfy.farm_and_charm.core.registry.TagRegistry;
import net.satisfy.farm_and_charm.core.util.GeneralUtil;
import net.satisfy.farm_and_charm.core.world.ImplementedInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.item.ItemStack.isSameItemSameTags;

public class RoasterBlockEntity extends BlockEntity implements BlockEntityTicker<RoasterBlockEntity>, ImplementedInventory, MenuProvider {
    private static final int MAX_CAPACITY = 8, OUTPUT_SLOT = 7, INGREDIENTS_AREA = 6;

    private final NonNullList<ItemStack> inventory = NonNullList.withSize(MAX_CAPACITY, ItemStack.EMPTY);
    private int roastingTime;
    private boolean isBeingBurned;
    private int currentSequence = 0;
    private int sequenceProgress = 0;
    private int currentSequenceDuration = 0;
    private int totalDuration = 0;
    private int totalProgress = 0;
    private int[] sequenceProgresses = {0, 0, 0};
    private int[] sequenceDurations = {0, 0, 0};

    private final ContainerData delegate = new ContainerData() {
        public int get(int index) {
            return switch (index) {
                case 0 -> roastingTime;
                case 1 -> isBeingBurned ? 1 : 0;
                case 2 -> currentSequence;
                case 3 -> sequenceProgress;
                case 4 -> currentSequenceDuration;
                case 5 -> totalDuration;
                case 6 -> totalProgress;
                case 7 -> sequenceProgresses[0];
                case 8 -> sequenceDurations[0];
                case 9 -> sequenceProgresses[1];
                case 10 -> sequenceDurations[1];
                case 11 -> sequenceProgresses[2];
                case 12 -> sequenceDurations[2];
                default -> 0;
            };
        }

        public void set(int index, int value) {
            switch (index) {
                case 0: roastingTime = value; break;
                case 1: isBeingBurned = value != 0; break;
                case 2: currentSequence = value; break;
                case 3: sequenceProgress = value; break;
                case 4: currentSequenceDuration = value; break;
                case 5: totalDuration = value; break;
                case 6: totalProgress = value; break;
                case 7: sequenceProgresses[0] = value; break;
                case 8: sequenceDurations[0] = value; break;
                case 9: sequenceProgresses[1] = value; break;
                case 10: sequenceDurations[1] = value; break;
                case 11: sequenceProgresses[2] = value; break;
                case 12: sequenceDurations[2] = value; break;
            }
        }

        public int getCount() {
            return 13;
        }
    };

    public RoasterBlockEntity(BlockPos pos, BlockState state) {
        super(EntityTypeRegistry.ROASTER_BLOCK_ENTITY.get(), pos, state);
    }

    public void load(CompoundTag nbt) {
        super.load(nbt);
        ContainerHelper.loadAllItems(nbt, inventory);
        roastingTime = nbt.getInt("RoastingTime");
        isBeingBurned = nbt.getBoolean("IsBeingBurned");
        currentSequence = nbt.getInt("CurrentSequence");
        sequenceProgress = nbt.getInt("SequenceProgress");
        currentSequenceDuration = nbt.getInt("CurrentSequenceDuration");
        totalDuration = nbt.getInt("TotalDuration");
        totalProgress = nbt.getInt("TotalProgress");
        sequenceProgresses[0] = nbt.getInt("Sequence1Progress");
        sequenceDurations[0] = nbt.getInt("Sequence1Duration");
        sequenceProgresses[1] = nbt.getInt("Sequence2Progress");
        sequenceDurations[1] = nbt.getInt("Sequence2Duration");
        sequenceProgresses[2] = nbt.getInt("Sequence3Progress");
        sequenceDurations[2] = nbt.getInt("Sequence3Duration");
    }

    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        ContainerHelper.saveAllItems(nbt, inventory);
        nbt.putInt("RoastingTime", roastingTime);
        nbt.putBoolean("IsBeingBurned", isBeingBurned);
        nbt.putInt("CurrentSequence", currentSequence);
        nbt.putInt("SequenceProgress", sequenceProgress);
        nbt.putInt("CurrentSequenceDuration", currentSequenceDuration);
        nbt.putInt("TotalDuration", totalDuration);
        nbt.putInt("TotalProgress", totalProgress);
        nbt.putInt("Sequence1Progress", sequenceProgresses[0]);
        nbt.putInt("Sequence1Duration", sequenceDurations[0]);
        nbt.putInt("Sequence2Progress", sequenceProgresses[1]);
        nbt.putInt("Sequence2Duration", sequenceDurations[1]);
        nbt.putInt("Sequence3Progress", sequenceProgresses[2]);
        nbt.putInt("Sequence3Duration", sequenceDurations[2]);
    }

    public boolean isBeingBurned() {
        if (level == null) throw new IllegalStateException("Null world not allowed");
        BlockState belowState = level.getBlockState(worldPosition.below());
        return belowState.is(TagRegistry.ALLOWS_COOKING);
    }

    private boolean canCraft(RoasterRecipe recipe, RegistryAccess access) {
        if (recipe == null || recipe.getResultItem(access).isEmpty()) return false;
        if (currentSequence >= recipe.getSequences().size()) return false;
        Sequence currentSeq = recipe.getSequences().get(currentSequence);
        boolean hasIngredients = GeneralUtil.matchesIngredients(this, currentSeq.getIngredients(), 0, INGREDIENTS_AREA);
        boolean isOutputSlotCompatible = getItem(OUTPUT_SLOT).isEmpty() || (isSameItemSameTags(getItem(OUTPUT_SLOT), recipe.getResultItem(access)) && getItem(OUTPUT_SLOT).getCount() < getItem(OUTPUT_SLOT).getMaxStackSize());
        return hasIngredients && isOutputSlotCompatible;
    }

    private void craft(RoasterRecipe recipe, RegistryAccess access) {
        if (!canCraft(recipe, access)) return;
        ItemStack recipeOutput = recipe.getResultItem(access).copy();
        ItemStack outputSlotStack = getItem(OUTPUT_SLOT);
        if (outputSlotStack.isEmpty()) {
            setItem(OUTPUT_SLOT, recipeOutput);
        } else {
            outputSlotStack.grow(recipeOutput.getCount());
        }
    }

    private void consumeAllIngredients(RoasterRecipe recipe) {
        for (Sequence sequence : recipe.getSequences()) {
            for (Ingredient ingredient : sequence.getIngredients()) {
                for (int slot = 0; slot < INGREDIENTS_AREA; slot++) {
                    ItemStack stack = getItem(slot);
                    if (ingredient.test(stack)) {
                        stack.shrink(1);
                        if (stack.isEmpty()) {
                            setItem(slot, ItemStack.EMPTY);
                        }
                        break;
                    }
                }
            }
        }
    }

    private void processSequence(RoasterRecipe recipe, RegistryAccess access, Level world, BlockState state) {
        if (currentSequence >= recipe.getSequences().size()) {
            consumeAllIngredients(recipe);
            craft(recipe, access);
            resetCrafting();
            return;
        }

        Sequence sequence = recipe.getSequences().get(currentSequence);
        if (GeneralUtil.matchesIngredients(this, sequence.getIngredients(), 0, INGREDIENTS_AREA)) {
            if (currentSequence == 0 && totalDuration == 0) {
                totalDuration = recipe.getSequences().stream().mapToInt(Sequence::getDuration).sum();
                delegate.set(5, totalDuration);
            }

            sequenceProgress++;
            roastingTime++;
            totalProgress++;

            sequenceProgresses[currentSequence]++;
            sequenceDurations[currentSequence] = sequence.getDuration();
            delegate.set(7 + currentSequence * 2, sequenceProgresses[currentSequence]);
            delegate.set(8 + currentSequence * 2, sequenceDurations[currentSequence]);

            if (sequenceProgress >= sequence.getDuration()) {
                sequenceProgress = 0;
                currentSequence++;
                if (currentSequence < recipe.getSequences().size()) {
                    Sequence nextSequence = recipe.getSequences().get(currentSequence);
                    currentSequenceDuration = nextSequence.getDuration();
                    delegate.set(4, currentSequenceDuration);
                    boolean canStartNext = GeneralUtil.matchesIngredients(this, nextSequence.getIngredients(), 0, INGREDIENTS_AREA);
                    if (!canStartNext) {
                        world.setBlock(worldPosition, state.setValue(RoasterBlock.ROASTING, false), Block.UPDATE_ALL);
                    }
                } else {
                    consumeAllIngredients(recipe);
                    craft(recipe, access);
                    resetCrafting();
                }
            }
        } else {
            resetCrafting();
        }
    }

    private void resetCrafting() {
        currentSequence = 0;
        sequenceProgress = 0;
        currentSequenceDuration = 0;
        totalProgress = 0;
        totalDuration = 0;
        sequenceProgresses = new int[]{0, 0, 0};
        sequenceDurations = new int[]{0, 0, 0};
        delegate.set(2, currentSequence);
        delegate.set(3, sequenceProgress);
        delegate.set(4, currentSequenceDuration);
        delegate.set(5, totalDuration);
        delegate.set(6, totalProgress);
        delegate.set(7, sequenceProgresses[0]);
        delegate.set(8, sequenceDurations[0]);
        delegate.set(9, sequenceProgresses[1]);
        delegate.set(10, sequenceDurations[1]);
        delegate.set(11, sequenceProgresses[2]);
        delegate.set(12, sequenceDurations[2]);
    }

    @Override
    public void tick(Level world, BlockPos pos, BlockState state, RoasterBlockEntity blockEntity) {
        if (world.isClientSide()) return;

        boolean wasBeingBurned = isBeingBurned;
        isBeingBurned = isBeingBurned();

        if (wasBeingBurned != isBeingBurned || state.getValue(RoasterBlock.LIT) != isBeingBurned) {
            world.setBlock(pos, state.setValue(RoasterBlock.LIT, isBeingBurned), Block.UPDATE_ALL);
        }

        Recipe<?> recipe = world.getRecipeManager().getRecipeFor(RecipeTypeRegistry.ROASTER_RECIPE_TYPE.get(), this, world).orElse(null);
        if (!(recipe instanceof RoasterRecipe roasterRecipe)) {
            resetCrafting();
            if (state.getValue(RoasterBlock.ROASTING)) {
                world.setBlock(pos, state.setValue(RoasterBlock.ROASTING, false), Block.UPDATE_ALL);
            }
            return;
        }

        assert level != null;
        RegistryAccess access = level.registryAccess();
        if (isBeingBurned && canCraft(roasterRecipe, access)) {
            if (!state.getValue(RoasterBlock.ROASTING)) {
                world.setBlock(pos, state.setValue(RoasterBlock.ROASTING, true), Block.UPDATE_ALL);
            }
            processSequence(roasterRecipe, access, world, state);
        } else {
            resetCrafting();
            if (state.getValue(RoasterBlock.ROASTING)) {
                world.setBlock(pos, state.setValue(RoasterBlock.ROASTING, false), Block.UPDATE_ALL);
            }
        }
    }

    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    public boolean stillValid(Player player) {
        if (level == null || level.getBlockEntity(worldPosition) != this) return false;
        return player.distanceToSqr(worldPosition.getX() + 0.5D, worldPosition.getY() + 0.5D, worldPosition.getZ() + 0.5D) <= 64.0D;
    }

    public @NotNull Component getDisplayName() {
        return Component.translatable(getBlockState().getBlock().getDescriptionId());
    }

    @Nullable
    public AbstractContainerMenu createMenu(int syncId, Inventory inv, Player player) {
        return new RoasterGuiHandler(syncId, inv, this, delegate);
    }
}