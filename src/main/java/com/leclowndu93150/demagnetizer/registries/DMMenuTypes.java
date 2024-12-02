package com.leclowndu93150.demagnetizer.registries;

import com.leclowndu93150.demagnetizer.DMMain;
import com.leclowndu93150.demagnetizer.container.DemagnetizerContainer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class DMMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, DMMain.MODID);

    public static final RegistryObject<MenuType<DemagnetizerContainer>> DEMAGNETIZER = MENU_TYPES.register("demagnetizer", () -> MenuType.builder.of(DemagnetizerContainer::new).build(null));

}
