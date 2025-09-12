package com.sember.revelation.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.sember.revelation.player.AccessoryHolder;
import com.sember.revelation.registry.RevelationComponents;
import com.sember.revelation.registry.RevelationItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
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

    @WrapOperation(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    public boolean revelation$negateDamage(PlayerEntity instance, ServerWorld world, DamageSource source, float amount, Operation<Boolean> original) {
        return isWearingAccessory(RevelationItems.ARMADILLO_HUSK) ? amount <= 1.0F : original.call(instance, world, source, amount);
    }

    @Override
    public boolean isWearingAccessory(Item accessory) {
        return RevelationComponents.ACCESSORIES.get(this).isWearing(accessory);
    }

}
