package com.leclowndu93150.demagnetizer.content.menu;

import com.leclowndu93150.demagnetizer.content.blockentity.DemagnetizerBlockEntity;
import com.leclowndu93150.demagnetizer.registries.DMBlocks;
import com.leclowndu93150.demagnetizer.registries.DMMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class DemagnetizerMenu extends AbstractContainerMenu {
    public final DemagnetizerBlockEntity be;
    private final ContainerLevelAccess levelAccess;
    private final Player player;

    public DemagnetizerMenu(int containerId, Inventory inventory, DemagnetizerBlockEntity be) {
        super(DMMenuTypes.DEMAGNETIZER.get(), containerId);
        this.be = be;
        this.player = inventory.player;
        this.levelAccess = ContainerLevelAccess.create(be.getLevel(), be.getBlockPos());
        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);
    }

    public DemagnetizerMenu(int i, Inventory inventory, FriendlyByteBuf friendlyByteBuf) {
        this(i, inventory, (DemagnetizerBlockEntity) inventory.player.level().getBlockEntity(friendlyByteBuf.readBlockPos()));
    }

    protected void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 83 + i * 18));
            }
        }
    }

    protected void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 141));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(levelAccess, player, DMBlocks.DEMAGNETIZER.get());
    }

}
