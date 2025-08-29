package com.sember.revelation;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.ramixin.mixson.atp.annotations.events.MixsonEvent;
import net.ramixin.mixson.inline.EventContext;

public class RevelationMixson {

    @MixsonEvent("loot_table/entities/wither_skeleton")
    public static void revelation$changeWitherLootTable(EventContext<JsonElement> context) {
        JsonElement element = context.getFile();
        JsonObject object = element.getAsJsonObject();
        object.getAsJsonArray("pools")
                .get(2)
                .getAsJsonObject()
                .getAsJsonArray("conditions")
                .remove(2);
    }

}
