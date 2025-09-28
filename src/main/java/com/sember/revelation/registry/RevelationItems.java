package com.sember.revelation.registry;

import com.sember.revelation.Revelation;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;

public class RevelationItems {

    public static final Item ARMADILLO_HUSK = registerItem("armadillo_husk", settings -> new Item(settings.component(RevelationComponents.MARKER, 2)));

    public static void register() {
        // Add tooltip logic & other item related initialization here later
    }

    public static Item registerItem(String name, Function<Item.Settings, Item> factory) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Revelation.id(name));
        Item item = factory.apply(new Item.Settings().registryKey(key));
        return Registry.register(Registries.ITEM, Revelation.id(name), item);
    }

}
