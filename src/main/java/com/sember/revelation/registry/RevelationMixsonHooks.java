package com.sember.revelation.registry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;
import net.ramixin.mixson.inline.EventContext;
import net.ramixin.mixson.inline.Mixson;
import net.ramixin.mixson.inline.MixsonEvent;

public class RevelationMixsonHooks {

    public static void register() {

        registerEvent(
                0,
                Identifier.ofVanilla("loot_table/entities/wither_skeleton"),
                "revelation:change_wither_skeleton_loot",
                RevelationMixsonHooks::changeWitherSkeletonLoot
        );

    }
    
    public static void changeWitherSkeletonLoot(EventContext<JsonElement> context) {
        JsonElement element = context.getFile();
        JsonObject object = element.getAsJsonObject();
        JsonArray conditions = object.getAsJsonArray("pools")
                .get(2)
                .getAsJsonObject()
                .getAsJsonArray("conditions");
        conditions.remove(1);
    }

    private static void registerEvent(int priority, Identifier id, String name, MixsonEvent<JsonElement> event) {
        Mixson.registerEvent(
                priority,
                id::equals,
                name,
                event,
                false
        );
    }

}
