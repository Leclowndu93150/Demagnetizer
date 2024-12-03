package com.leclowndu93150.demagnetizer.events;

import com.leclowndu93150.demagnetizer.DMMain;
import com.leclowndu93150.demagnetizer.client.screen.DemagnetizerScreen;
import com.leclowndu93150.demagnetizer.registries.DMMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = DMMain.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DMClientEvents {
    @SubscribeEvent
    public static void registerMenuScreens(FMLClientSetupEvent event) {
        MenuScreens.register(DMMenuTypes.DEMAGNETIZER.get(), DemagnetizerScreen::new);
    }
}
