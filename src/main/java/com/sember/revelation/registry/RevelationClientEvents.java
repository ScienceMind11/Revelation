package com.sember.revelation.registry;

import com.sember.revelation.RevelationClient;
import com.sember.revelation.network.AccessoriesPacketPayload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class RevelationClientEvents {

    private static long lastUseTime = 0L;

    public static void switchAccessories(MinecraftClient client) {

        if (client.player == null) return;
        if (!RevelationClient.OPEN_ACCESSORIES.isPressed() || !client.options.useKey.isPressed()) return;

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUseTime < 250) return;

        ClientPlayNetworking.send(new AccessoriesPacketPayload(true));

        lastUseTime = currentTime;

    }

}
