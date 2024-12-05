package com.leclowndu93150.demagnetizer.client.screen;

import com.leclowndu93150.demagnetizer.DMMain;
import com.leclowndu93150.demagnetizer.content.blockentity.DemagnetizerBlockEntity;
import com.leclowndu93150.demagnetizer.content.menu.DemagnetizerMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;


public class DemagnetizerScreen extends AbstractContainerScreen<DemagnetizerMenu> {
    private static final ResourceLocation background = new ResourceLocation(DMMain.MODID, "textures/gui/demagnetizer.png");
    //private static final ResourceLocation slot = new ResourceLocation("minecraft", "textures/gui/container/slot.png");
    private final DemagnetizerBlockEntity blockEntity;
    private IconButton rsButton;

    public DemagnetizerScreen(DemagnetizerMenu demagnetizerMenu, Inventory inventory, Component component) {
        super(demagnetizerMenu, inventory, component);
        this.blockEntity = demagnetizerMenu.be;
        imageWidth = 176;
        imageHeight = 166;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int balls, int balls2) {
        assert minecraft != null;
        minecraft.getTextureManager().bindForSetup(background);
        guiGraphics.blit(background, getGuiLeft(), getGuiTop()-1, 0, 0, 176, 166);

        ResourceLocation emptySlot = new ResourceLocation(DMMain.MODID, "textures/gui/empty_slot.png");
        minecraft.getTextureManager().bindForSetup(emptySlot);
        for (int i = 0; i < blockEntity.getFilterSize(); i++) {
            guiGraphics.blit(emptySlot, getGuiLeft() + 7 + (i * 18), getGuiTop() + 52, 0, 0, 18, 18);
        }
    }

    @Override
    public void render(GuiGraphics p_283479_, int p_283661_, int p_281248_, float p_281886_) {
        renderBackground(p_283479_);
        super.render(p_283479_, p_283661_, p_281248_, p_281886_);
        renderTooltip(p_283479_, p_283661_, p_281248_);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        int titleX = (this.imageWidth - this.font.width(this.title)) / 2;
        guiGraphics.drawString(this.font, this.title, titleX, 6, 0x404040, false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, 8, this.imageHeight - 96 + 2, 0x404040, false);
        guiGraphics.drawString(this.font,Component.literal("Filter"),8, 42,0x404040,false);
    }

    @Override
    protected <T extends GuiEventListener & Renderable & NarratableEntry> T addRenderableWidget(T p_169406_) {
        return super.addRenderableWidget(p_169406_);
    }


    @Override
    protected void init() {
        super.init();

        addRenderableWidget(new RangeSlider(getGuiLeft() + 7, getGuiTop() + 16, blockEntity.getMaxRange(), blockEntity.getRange()));

        String[] rsStates = {"rsignored", "rson", "rsoff"};
        int currentRSState = switch (blockEntity.getRedstoneSetting()) {
            case POWERED -> 1;
            case UNPOWERED -> 2;
            default -> 0;
        };
        rsButton = new IconButton(getGuiLeft() + 124, getGuiTop() + 16, rsStates, currentRSState, background, 0, 184) {
            @Override
            public void updateState(int currentState) {
                switch (currentState) {
                    case 0 -> blockEntity.setRedstoneSetting(DemagnetizerBlockEntity.RedstoneStatus.REDSTONE_DISABLED);
                    case 1 -> blockEntity.setRedstoneSetting(DemagnetizerBlockEntity.RedstoneStatus.POWERED);
                    case 2 -> blockEntity.setRedstoneSetting(DemagnetizerBlockEntity.RedstoneStatus.UNPOWERED);
                }
            }
        };
        addRenderableWidget(rsButton);

        if (blockEntity.getFilterSize() > 0) {
            String[] whitelistStates = {"blacklist", "whitelist"};
            int currentWhitelistState = blockEntity.isWhitelist() ? 1 : 0;
            IconButton whitelistButton = new IconButton(getGuiLeft() + 148, getGuiTop() + 16, whitelistStates, currentWhitelistState, background, 0, 204) {
                @Override
                public void updateState(int currentState) {
                    blockEntity.setWhitelist(currentState == 1);
                }
            };
            addRenderableWidget(whitelistButton);
        }
    }

    private abstract class IconButton extends AbstractButton {
        private final String[] stateList;
        private int currentState;
        private final ResourceLocation location;
        private final int resourceX;
        private final int resourceY;

        IconButton(int x, int y, String[] stateList, int currentState, ResourceLocation location, int resourceX, int resourceY) {
            super(x, y, 20, 20, Component.empty());
            this.stateList = stateList;
            this.currentState = currentState;
            this.location = location;
            this.resourceX = resourceX;
            this.resourceY = resourceY;
        }

        @Override
        public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
            Minecraft mc = Minecraft.getInstance();
            mc.getTextureManager().bindForSetup(location);
            guiGraphics.blit(location, getX(), getY(), resourceX + currentState * width, resourceY, width, height);
            if (isHovered()) {
                renderTooltip(guiGraphics, mouseX, mouseY);
            }
        }

        public void renderTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY) {
            Minecraft mc = Minecraft.getInstance();
            if (isHovered()) {
                guiGraphics.renderTooltip(mc.font, getNarrationMessage(), mouseX, mouseY);
            }
        }

        @Override
        protected MutableComponent createNarrationMessage() {
            return Component.translatable("label." + DMMain.MODID + ".demagnetizer." + stateList[currentState]);
        }

        @Override
        protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
            narrationElementOutput.add(NarratedElementType.TITLE, createNarrationMessage());
        }

        @Override
        public void onPress() {
            currentState++;
            if (currentState >= stateList.length) {
                currentState = 0;
            }
            updateState(currentState);
        }

        public abstract void updateState(int currentState);
    }



    private class RangeSlider extends AbstractSliderButton {
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
            visible = true;
            active = true;
            setMessage(Component.literal("Range").append(": " + scaledValue));
        }

        @Override
        protected void applyValue() {
            scaledValue = (int) (Math.round(value * (maxValue - minValue)) + minValue);
            blockEntity.setRange(scaledValue);
        }
    }

}
