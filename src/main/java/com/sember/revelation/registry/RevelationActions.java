package com.sember.revelation.registry;

import com.sember.revelation.Revelation;
import com.sember.revelation.action.Action;
import net.minecraft.registry.Registry;

public class RevelationActions {

    public static final Action DASH = Action.create(30, (player, stack, world) -> {
        if (world.isClient) return;
        player.setPos(player.getX(), player.getY() + 3, player.getZ());
    });

    public static void register() {

        registerAction("dash", DASH);

    }

    public static void registerAction(String name, Action action) {
        Registry.register(RevelationRegistries.ACTION, Revelation.id(name), action);
    }

}
