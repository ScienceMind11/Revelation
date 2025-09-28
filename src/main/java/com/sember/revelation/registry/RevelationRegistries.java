package com.sember.revelation.registry;

import com.sember.revelation.Revelation;
import com.sember.revelation.action.Action;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class RevelationRegistries {

    public static final RegistryKey<Registry<Action>> ACTION_KEY = RegistryKey.<Action>ofRegistry(Revelation.id("action"));

    public static final Registry<Action> ACTION = FabricRegistryBuilder.createSimple(ACTION_KEY).buildAndRegister();

    public static void register() {

    }

}
