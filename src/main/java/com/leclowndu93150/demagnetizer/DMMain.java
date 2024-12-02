package com.leclowndu93150.demagnetizer;

import com.leclowndu93150.demagnetizer.registries.DMBlockEntityTypes;
import com.leclowndu93150.demagnetizer.registries.DMBlocks;
import com.leclowndu93150.demagnetizer.registries.DMCreativeTabs;
import com.leclowndu93150.demagnetizer.registries.DMItems;
import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(DMMain.MODID)
public class DMMain {

    public static final String MODID = "demagnetizer";
    private static final Logger LOGGER = LogUtils.getLogger();

    public DMMain() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        DMBlocks.BLOCKS.register(modEventBus);
        DMItems.ITEMS.register(modEventBus);
        DMCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        DMBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DMConfig.SPEC);
    }


}
