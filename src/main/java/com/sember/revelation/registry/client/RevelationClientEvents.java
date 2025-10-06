package com.sember.revelation.registry.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sember.revelation.RevelationClient;
import com.sember.revelation.component.entity.AccessoriesComponent;
import com.sember.revelation.network.AccessoriesPacketPayload;
import com.sember.revelation.registry.RevelationComponents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

@Environment(EnvType.CLIENT)
public class RevelationClientEvents {

    private static long lastUseTime = 0L;

    public static void switchAccessories(MinecraftClient client) {

        PlayerEntity player = client.player;
        if (player == null) return;
        if (!RevelationClient.OPEN_ACCESSORIES.isPressed() || !client.options.useKey.isPressed()) return;
        AccessoriesComponent accessories = RevelationComponents.ACCESSORIES.get(player);

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUseTime < 250) return;

        ClientPlayNetworking.send(new AccessoriesPacketPayload(accessories.getSelected(), true));

        lastUseTime = currentTime;

    }

}
