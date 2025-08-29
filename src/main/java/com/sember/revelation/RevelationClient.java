package com.sember.revelation;

import net.fabricmc.api.ClientModInitializer;

import static com.sember.revelation.Revelation.*;

public class RevelationClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        LOGGER.info("Client loaded");
    }

}
