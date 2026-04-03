package org.sember.revelation;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Revelation implements ModInitializer {

    public static final String NAME = "Revelation";
    public static final String ID = "revelation";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    @Override
    public void onInitialize() {
        LOGGER.info("Successfully initialized");
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(ID, path);
    }

}
