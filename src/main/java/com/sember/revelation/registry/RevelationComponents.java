package com.sember.revelation.registry;

import com.mojang.serialization.Codec;
import com.sember.revelation.Revelation;
import com.sember.revelation.action.Action;
import com.sember.revelation.component.entity.AccessoriesComponent;
import com.sember.revelation.component.entity.BooleanComponent;
import com.sember.revelation.component.entity.WitheredComponent;
import com.sun.jna.IntegerType;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.dynamic.Codecs;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.scoreboard.ScoreboardComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.scoreboard.ScoreboardComponentInitializer;

public class RevelationComponents implements EntityComponentInitializer, ScoreboardComponentInitializer {

    // Entities

    public static final ComponentKey<AccessoriesComponent> ACCESSORIES =
            ComponentRegistry.getOrCreate(Revelation.id("accessories"), AccessoriesComponent.class);

    // Scoreboards

    public static final ComponentKey<BooleanComponent> WITHERED =
            ComponentRegistry.getOrCreate(Revelation.id("withered"), BooleanComponent.class);

    // Items

    public static final ComponentType<Identifier> ACTION = ComponentType.<Identifier>builder().codec(Identifier.CODEC).build();
    public static final ComponentType<Integer> MARKER = ComponentType.<Integer>builder().codec(Codec.INT.orElse(0)).build();

    public static void registerItemComponents() {
        registerComponent("action", ACTION);
        registerComponent("marker", MARKER);
    }

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(PlayerEntity.class, ACCESSORIES, AccessoriesComponent::new);
    }

    @Override
    public void registerScoreboardComponentFactories(ScoreboardComponentFactoryRegistry registry) {
        registry.registerScoreboardComponent(WITHERED, (scoreboard, server) -> new WitheredComponent());
    }

    public static <T> void registerComponent(String name, ComponentType<T> type) {
        Registry.register(Registries.DATA_COMPONENT_TYPE, Revelation.id(name), type);
    }

}
