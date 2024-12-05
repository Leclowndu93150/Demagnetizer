package com.leclowndu93150.demagnetizer.content.blockentity;

import com.leclowndu93150.demagnetizer.content.menu.DemagnetizerMenu;
import com.leclowndu93150.demagnetizer.registries.DMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

public class DemagnetizerBlockEntity extends BlockEntity implements MenuProvider {

    public enum RedstoneStatus {
        REDSTONE_DISABLED(0), POWERED(1), UNPOWERED(2);

        private final int num;
        RedstoneStatus(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }

        public static RedstoneStatus parse(int num) {
            for (RedstoneStatus s : RedstoneStatus.values()) {
                if (s.getNum() == num) {
                    return s;
                }
            }
            return null;
        }
    }

    private AABB scanArea;
    private int range;
    private RedstoneStatus redstoneSetting = RedstoneStatus.REDSTONE_DISABLED;
    private boolean filtersWhitelist = false; // Default to using blacklist
    private boolean isPowered = false;

    public DemagnetizerBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(DMBlockEntityTypes.DEMAGNETIZER.get(), p_155229_, p_155230_);
        this.range = getMaxRange();
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Demagnetizer");
    }

    public static void tick(Level level, BlockPos pos, BlockState state, DemagnetizerBlockEntity blockEntity) {
        level.getEntitiesOfClass(ItemEntity.class, blockEntity.getScanArea(), entity -> true).forEach(itemEntity -> {
            if (!itemEntity.getPersistentData().contains("PreventRemoteMovement") && !itemEntity.getPersistentData().contains("AllowMachineRemoteMovement")) {
                itemEntity.getPersistentData().putBoolean("PreventRemoteMovement", true);
                itemEntity.getPersistentData().putBoolean("AllowMachineRemoteMovement", true);
                System.out.println("Prevented remote movement of item entity");
            }
        });
    }

    public AABB getScanArea() {
        if (scanArea == null) {
            scanArea = new AABB(worldPosition).inflate(range);
        }
        return scanArea;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new DemagnetizerMenu(i, inventory, this);
    }


    //Return the config value later
    public int getMaxRange() {
        return 16;
    }

    public int getRange() {
        return this.range;
    }

    public int setRange(int range) {
        this.range = range;
        return range;
    }
}
