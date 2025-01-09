package net.satisfy.farm_and_charm.client.gui.handler;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.satisfy.farm_and_charm.core.recipe.Sequence;
import net.satisfy.farm_and_charm.core.recipe.RoasterRecipe;
import net.satisfy.farm_and_charm.core.registry.ScreenhandlerTypeRegistry;
import net.satisfy.farm_and_charm.core.registry.TagRegistry;

public class RoasterGuiHandler extends AbstractRecipeBookGUIScreenHandler {
    private final ContainerData propertyDelegate;

    public RoasterGuiHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, new SimpleContainer(8), new SimpleContainerData(13));
    }

    public RoasterGuiHandler(int syncId, Inventory playerInventory, Container inventory, ContainerData propertyDelegate) {
        super(ScreenhandlerTypeRegistry.ROASTER_SCREEN_HANDLER.get(), syncId, 7, playerInventory, inventory, propertyDelegate);
        this.buildBlockEntityContainer(inventory);
        this.buildPlayerContainer(playerInventory);
        this.propertyDelegate = propertyDelegate;
        this.addDataSlots(propertyDelegate);
    }

    private void buildBlockEntityContainer(Container inventory) {
        for (int row = 0; row < 2; row++) {
            for (int slot = 0; slot < 3; slot++) {
                this.addSlot(new Slot(inventory, slot + row * 3, 16 + (slot * 18), 36 + (row * 18)));
            }
        }
        this.addSlot(new Slot(inventory, 7, 124, 18) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
    }

    private void buildPlayerContainer(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public boolean isBeingBurned() {
        return this.propertyDelegate.get(1) != 0;
    }

    public int getSequence1Progress() {
        return this.propertyDelegate.get(7);
    }

    public int getSequence1Duration() {
        return this.propertyDelegate.get(8);
    }

    public int getSequence2Progress() {
        return this.propertyDelegate.get(9);
    }

    public int getSequence2Duration() {
        return this.propertyDelegate.get(10);
    }

    public int getSequence3Progress() {
        return this.propertyDelegate.get(11);
    }

    public int getSequence3Duration() {
        return this.propertyDelegate.get(12);
    }

    public int getTotalProgress() {
        return this.propertyDelegate.get(6);
    }

    public int getTotalDuration() {
        return this.propertyDelegate.get(5);
    }

    public int getScaledProgress(int progress, int maxWidth) {
        if (getTotalDuration() == 0) return 0;
        return progress * maxWidth / getTotalDuration();
    }

    @Override
    public boolean hasIngredient(Recipe<?> recipe) {
        if (recipe instanceof RoasterRecipe roasterRecipe) {
            for (Sequence sequence : roasterRecipe.getSequences()) {
                for (net.minecraft.world.item.crafting.Ingredient ingredient : sequence.getIngredients()) {
                    boolean found = false;
                    for (Slot slot : this.slots) {
                        if (ingredient.test(slot.getItem())) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        return false;
                    }
                }
            }
            boolean hasContainer = false;
            for (Slot slot : this.slots) {
                if (slot.getItem().is(TagRegistry.CONTAINER)) {
                    hasContainer = true;
                    break;
                }
            }
            return hasContainer;
        }
        return false;
    }

    @Override
    public int getCraftingSlotCount() {
        return 7;
    }
}
