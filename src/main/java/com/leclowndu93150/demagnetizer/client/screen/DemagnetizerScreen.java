package com.leclowndu93150.demagnetizer.client.screen;

import com.leclowndu93150.demagnetizer.DMMain;
import com.leclowndu93150.demagnetizer.content.blockentity.DemagnetizerBlockEntity;
import com.leclowndu93150.demagnetizer.content.menu.DemagnetizerMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;


public class DemagnetizerScreen extends AbstractContainerScreen<DemagnetizerMenu> {
    private static final ResourceLocation background = new ResourceLocation(DMMain.MODID, "textures/gui/demagnetizer.png");
    private final DemagnetizerBlockEntity blockEntity;

    public DemagnetizerScreen(DemagnetizerMenu demagnetizerMenu, Inventory inventory, Component component) {
        super(demagnetizerMenu, inventory, component);
        this.blockEntity = demagnetizerMenu.be;
        imageWidth = 176;
        imageHeight = 166;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        assert minecraft != null;
        minecraft.getTextureManager().bindForSetup(background);
        guiGraphics.blit(background, getGuiLeft(), getGuiTop()-1, 0, 0, 176, 166);
        /*
        for (int i = 0; i < blockEntity.getFilterSize(); i++) {
            blit(stack, guiLeft + 7 + (i * 18), guiTop + 52, 0, 166, 18, 18);
        }
         */
    }

    @Override
    public void render(GuiGraphics p_283479_, int p_283661_, int p_281248_, float p_281886_) {
        renderBackground(p_283479_);
        super.render(p_283479_, p_283661_, p_281248_, p_281886_);
        renderTooltip(p_283479_, p_283661_, p_281248_);
    }

    @Override
    protected void init() {
        addWidget(new RangeSlider(getGuiLeft() + 7, getGuiTop() + 16, blockEntity.getMaxRange(), blockEntity.getRange()));
        super.init();
    }

    private static class RangeSlider extends AbstractSliderButton {
        private int scaledValue;
        private final int maxValue;
        private final static int minValue = 1;

        RangeSlider(int x, int y, int maxRange, int value) {
            super(x, y, 113, 20, Component.literal(""), ((float) (value - minValue)) / (float) (maxRange - minValue));
            scaledValue = value;
            maxValue = maxRange;
            updateMessage();
        }

        @Override
        protected void updateMessage() {
            setMessage(Component.literal("label." + DMMain.MODID + ".demagnetizer.range").append(": " + scaledValue));
        }

        @Override
        protected void applyValue() {
            /*
            scaledValue = (int) (Math.round(sliderValue * (maxValue - minValue)) + minValue);
            this.blockEntity.setRange(scaledValue);
             */
        }
    }

}
