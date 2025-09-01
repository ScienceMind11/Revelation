package com.sember.revelation.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import com.sember.revelation.RevelationClient;
import com.sember.revelation.component.entity.AccessoriesComponent;
import com.sember.revelation.registry.RevelationComponents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.input.Scroller;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "onMouseScroll", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getInventory()Lnet/minecraft/entity/player/PlayerInventory;"), cancellable = true)
    public void revelation$scrollAccessories(long window, double horizontal, double vertical, CallbackInfo ci, @Local int i) {
        if (!RevelationClient.OPEN_ACCESSORIES.isPressed()) return;
        if (client.player == null || client.player.isSneaking()) return;
        AccessoriesComponent accessories = RevelationComponents.ACCESSORIES.get(client.player);
        accessories.setSelectedSlot(Scroller.scrollCycling(i, accessories.getSelectedSlot(), accessories.getNumSlots()));
        ci.cancel();
    }

}
