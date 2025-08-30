package com.sember.revelation.registry;

import com.sember.revelation.Revelation;
import com.sember.revelation.component.entity.BooleanComponent;
import com.sember.revelation.component.entity.WitheredComponent;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.scoreboard.ScoreboardComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.scoreboard.ScoreboardComponentInitializer;

public class RevelationScoreboardComponents implements ScoreboardComponentInitializer {

    public static final ComponentKey<BooleanComponent> WITHERED =
            ComponentRegistry.getOrCreate(Revelation.id("withered"), BooleanComponent.class);

    @Override
    public void registerScoreboardComponentFactories(ScoreboardComponentFactoryRegistry registry) {
        registry.registerScoreboardComponent(WITHERED, (scoreboard, server) -> new WitheredComponent());
    }

}
