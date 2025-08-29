package com.sember.revelation;

import com.sember.revelation.registry.RevelationLootTables;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.util.Identifier;
import net.ramixin.mixson.inline.Mixson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Revelation implements ModInitializer {

    public static final String NAME = "Revelation";
    public static final String ID = NAME.toLowerCase();
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    @Override
    public void onInitialize() {

        LootTableEvents.MODIFY.register(RevelationLootTables::addSmithingTable);

        LOGGER.info("{} loaded", NAME);

    }

    public static Identifier id(String path) {
        return Identifier.of(ID, path);
    }

}
