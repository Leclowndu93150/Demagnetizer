package com.leclowndu93150.demagnetizer.content.blockentity;

import com.leclowndu93150.demagnetizer.content.menu.DemagnetizerMenu;
import com.leclowndu93150.demagnetizer.registries.DMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class DemagnetizerBlockEntity extends BlockEntity implements MenuProvider {

    public DemagnetizerBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(DMBlockEntityTypes.DEMAGNETIZER.get(), p_155229_, p_155230_);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Demagnetizer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new DemagnetizerMenu(i, inventory, this);
    }
}
