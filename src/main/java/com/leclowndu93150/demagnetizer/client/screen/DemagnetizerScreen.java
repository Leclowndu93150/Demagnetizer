package com.leclowndu93150.demagnetizer.client.screen;

import com.leclowndu93150.demagnetizer.content.menu.DemagnetizerMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class DemagnetizerScreen extends AbstractContainerScreen<DemagnetizerMenu> {
    public DemagnetizerScreen(DemagnetizerMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {

    }
}
