package com.leclowndu93150.demagnetizer.registries;

import com.leclowndu93150.demagnetizer.DMMain;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DMItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DMMain.MODID);

    public static final RegistryObject<Item> DEMAGNETIZER = ITEMS.register("demagnetizer", () -> new BlockItem(DMBlocks.DEMAGNETIZER.get(), new Item.Properties()));

}
