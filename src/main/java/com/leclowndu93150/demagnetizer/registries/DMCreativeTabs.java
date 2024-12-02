package com.leclowndu93150.demagnetizer.registries;

import com.leclowndu93150.demagnetizer.DMMain;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class DMCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DMMain.MODID);

    public static final RegistryObject<CreativeModeTab> DM_TAB = CREATIVE_MODE_TABS.register("dm_tab", () -> CreativeModeTab.builder().icon(() -> DMItems.DEMAGNETIZER.get().getDefaultInstance()).displayItems((parameters, output) -> {
        output.accept(DMBlocks.DEMAGNETIZER.get().asItem().getDefaultInstance());
    }).build());
}
