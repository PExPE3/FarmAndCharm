package net.satisfy.farm_and_charm.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.satisfy.farm_and_charm.client.gui.handler.RoasterGuiHandler;
import net.satisfy.farm_and_charm.core.util.FarmAndCharmIdentifier;

@Environment(EnvType.CLIENT)
public class RoasterGui extends AbstractContainerScreen<RoasterGuiHandler> {
    public static final ResourceLocation BACKGROUND = new FarmAndCharmIdentifier("textures/gui/roaster_gui.png");
    public static final int[] ARROW_X = {15, 46, 78};
    public static final int[] ARROW_Y = {16, 17, 16};
    public static final int[][] TEXTURE = {
            {176, 30},
            {176, 48},
            {176, 14}
    };

    public RoasterGui(RoasterGuiHandler handler, Inventory playerInventory, Component title) {
        super(handler, playerInventory, title);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    public void renderProgressArrow(GuiGraphics guiGraphics, int index) {
        int progress;
        switch (index) {
            case 0:
                progress = this.menu.getScaledProgress(this.menu.getSequence1Progress(), 23);
                break;
            case 1:
                progress = this.menu.getScaledProgress(this.menu.getSequence2Progress(), 23);
                break;
            case 2:
                progress = this.menu.getScaledProgress(this.menu.getSequence3Progress(), 23);
                break;
            default:
                progress = 0;
        }
        guiGraphics.blit(BACKGROUND, this.leftPos + ARROW_X[index], this.topPos + ARROW_Y[index], TEXTURE[index][0], TEXTURE[index][1], progress, 30);
    }

    public void renderBurnIcon(GuiGraphics guiGraphics, int posX, int posY) {
        if (this.menu.isBeingBurned()) {
            guiGraphics.blit(BACKGROUND, posX + 125, posY + 46, 176, 0, 17, 15);
        }
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BACKGROUND);
        int posX = this.leftPos;
        int posY = this.topPos;
        guiGraphics.blit(BACKGROUND, posX, posY, 0, 0, this.imageWidth, this.imageHeight);
        for (int i = 0; i < 3; i++) {
            renderProgressArrow(guiGraphics, i);
        }
        renderBurnIcon(guiGraphics, posX, posY);

        if (this.menu.getTotalDuration() > 0) {
            int scaledProgress = this.menu.getScaledProgress(this.menu.getTotalProgress(), 100);
            int sequenceBarHeight = 10;
            int sequenceX = posX + 30;
            int sequenceY = posY + 60;
            guiGraphics.fill(sequenceX, sequenceY, sequenceX + scaledProgress, sequenceY + sequenceBarHeight, 0xFF00FF00);
        }
    }
}
