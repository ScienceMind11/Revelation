package com.sember.revelation;

import com.sember.revelation.component.entity.BooleanComponent;
import com.sember.revelation.network.AccessoriesPacketPayload;
import com.sember.revelation.network.AccessoriesPacketReceiver;
import com.sember.revelation.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Revelation implements ModInitializer {

    public static final String NAME = "Revelation";
    public static final String ID = NAME.toLowerCase();
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    @Override
    public void onInitialize() {

        // IMPORTANT: Registries are created before anything else
        RevelationRegistries.register();

        RevelationActions.register();
        RevelationComponents.registerItemComponents();
        RevelationItems.register();
        RevelationLootTables.register();
        RevelationMixsonHooks.register();

        PayloadTypeRegistry.playC2S().register(AccessoriesPacketPayload.ID, AccessoriesPacketPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(AccessoriesPacketPayload.ID, AccessoriesPacketReceiver::receive);

        LOGGER.info("{} loaded", NAME);

    }

    public static Identifier id(String path) {
        return Identifier.of(ID, path);
    }

    public static BooleanComponent getWitheredComponent(World world) {
        return RevelationComponents.WITHERED.get(world.getScoreboard());
    }

}
