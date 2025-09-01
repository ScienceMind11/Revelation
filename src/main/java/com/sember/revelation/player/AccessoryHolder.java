package com.sember.revelation.player;

import com.sember.revelation.item.AccessoryItem;
import com.sember.revelation.registry.RevelationComponents;
import net.minecraft.item.Item;

public interface AccessoryHolder {

    default boolean isWearingAccessory(Item accessory) {
        return false;
    }

}
