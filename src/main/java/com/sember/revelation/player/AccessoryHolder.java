package com.sember.revelation.player;

import net.minecraft.item.Item;

public interface AccessoryHolder {

    default boolean isWearingAccessory(Item accessory) {
        return false;
    }

}
