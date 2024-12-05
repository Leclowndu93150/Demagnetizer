package com.leclowndu93150.demagnetizer.content.block;

import com.leclowndu93150.demagnetizer.content.blockentity.DemagnetizerBlockEntity;
import com.leclowndu93150.demagnetizer.registries.DMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class DemagnetizerBlock extends BaseEntityBlock {
    public DemagnetizerBlock(Properties p_49224_) {
        super(p_49224_);
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
        return createTickerHelper(p_153214_, DMBlockEntityTypes.DEMAGNETIZER.get(), DemagnetizerBlockEntity::tick);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return DMBlockEntityTypes.DEMAGNETIZER.get().create(blockPos, blockState);
    }

    @Override
    public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, InteractionHand p_60507_, BlockHitResult p_60508_) {
        if (p_60504_.getBlockEntity(p_60505_) instanceof DemagnetizerBlockEntity be && p_60506_ instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(serverPlayer, be, p_60505_);
            serverPlayer.swing(p_60507_);
            return InteractionResult.SUCCESS;
        }
        return super.use(p_60503_, p_60504_, p_60505_, p_60506_, p_60507_, p_60508_);
    }
}
