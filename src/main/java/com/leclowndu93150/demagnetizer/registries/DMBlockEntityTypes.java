package com.leclowndu93150.demagnetizer.registries;

import com.leclowndu93150.demagnetizer.DMMain;
import com.leclowndu93150.demagnetizer.content.blockentity.DemagnetizerBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class DMBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, DMMain.MODID);

    public static final RegistryObject<BlockEntityType<DemagnetizerBlockEntity>> DEMAGNETIZER = BLOCK_ENTITY_TYPES.register("demagnetizer", () -> BlockEntityType.Builder.of(DemagnetizerBlockEntity::new, validBlocks).build(null));

}
