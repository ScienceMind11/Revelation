package com.sember.revelation.network;

import com.sember.revelation.component.entity.AccessoriesComponent;
import com.sember.revelation.registry.RevelationComponents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class AccessoriesPacketReceiver {

    public static void receive(AccessoriesPacketPayload payload, ServerPlayNetworking.Context context) {

        PlayerEntity player = context.player();
        AccessoriesComponent accessories = RevelationComponents.ACCESSORIES.get(player);
        int selected = payload.selected();

        if (!payload.swap()) {
            accessories.setSelected(payload.selected());
            return;
        }

        ItemStack hotbarStack = player.getMainHandStack();
        ItemStack accessoriesStack = accessories.get(selected);

        if (hotbarStack.isEmpty() && accessoriesStack.isEmpty()) return;

        if (hotbarStack.isEmpty()) {
            hotbarStack = accessoriesStack.copyAndEmpty();
            player.setStackInHand(Hand.MAIN_HAND, hotbarStack);
        } else if (accessoriesStack.isEmpty()) {
            accessoriesStack = hotbarStack.copyAndEmpty();
            accessories.set(selected, accessoriesStack);
        } else {
            accessories.set(selected, hotbarStack.copyAndEmpty());
            player.setStackInHand(Hand.MAIN_HAND, accessoriesStack.copyAndEmpty());
        }

        RevelationComponents.ACCESSORIES.sync(player);

    }

}
