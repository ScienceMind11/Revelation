package com.sember.revelation.mixin;

import com.sember.revelation.item.AccessoryItem;
import com.sember.revelation.player.AccessoryHolder;
import com.sember.revelation.registry.RevelationComponents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements AccessoryHolder {

    @Inject(method = "onKilledOther", at = @At("HEAD"))
    public void revelation$setWorldWithered(ServerWorld world, LivingEntity other, CallbackInfoReturnable<Boolean> cir) {
        if (other instanceof WitherEntity) RevelationComponents.WITHERED.get(world.getScoreboard()).setValue(true);
    }

    @Override
    public boolean isWearingAccessory(Item accessory) {
        return RevelationComponents.ACCESSORIES.get((PlayerEntity) (Object) this).isWearing(accessory);
    }

}
