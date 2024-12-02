package com.leclowndu93150.demagnetizer.registries;

import com.leclowndu93150.demagnetizer.DMMain;
import com.leclowndu93150.demagnetizer.content.block.DemagnetizerBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DMBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DMMain.MODID);

    public static final RegistryObject<Block> DEMAGNETIZER = BLOCKS.register("demagnetizer", () -> new DemagnetizerBlock(Block.Properties.copy(Blocks.IRON_BLOCK).strength(3.0F, 3.0F).sound(SoundType.METAL)));

}
