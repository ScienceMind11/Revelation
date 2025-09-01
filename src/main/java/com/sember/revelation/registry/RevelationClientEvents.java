package com.sember.revelation.registry;

import com.sember.revelation.RevelationClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class RevelationClientEvents {

    public static void switchAccessories(MinecraftClient client) {
        if (client.player == null) return;
        if (RevelationClient.OPEN_ACCESSORIES.isPressed()) {

        }
    }

}
