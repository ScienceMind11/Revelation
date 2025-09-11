package com.sember.revelation;

import com.sember.revelation.component.entity.BooleanComponent;
import com.sember.revelation.registry.RevelationLootTables;
import com.sember.revelation.registry.RevelationMixsonHooks;
import com.sember.revelation.registry.RevelationComponents;
import com.sember.revelation.registry.RevelationRegistries;
import net.fabricmc.api.ModInitializer;
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

        RevelationComponents.registerItemComponents();
        RevelationLootTables.register();
        RevelationMixsonHooks.register();

        LOGGER.info("{} loaded", NAME);

    }

    public static Identifier id(String path) {
        return Identifier.of(ID, path);
    }

    public static BooleanComponent getWitheredComponent(World world) {
        return RevelationComponents.WITHERED.get(world.getScoreboard());
    }

}
