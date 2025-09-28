package com.sember.revelation;

import com.sember.revelation.client.AccessoriesHudElement;
import com.sember.revelation.registry.client.RevelationClientEvents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import static com.sember.revelation.Revelation.*;

public class RevelationClient implements ClientModInitializer {

    public static final KeyBinding OPEN_ACCESSORIES = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.revelation.accessories",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            "category.revelation"
    ));

    @Override
    public void onInitializeClient() {

        ClientTickEvents.END_CLIENT_TICK.register(RevelationClientEvents::switchAccessories);

        HudElementRegistry.addLast(Revelation.id("accessories"), AccessoriesHudElement::render);

        LOGGER.info("Client loaded");

    }

}
