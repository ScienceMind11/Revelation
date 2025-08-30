package com.sember.revelation.mixin;

import com.sember.revelation.registry.RevelationScoreboardComponents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(method = "onKilledOther", at = @At("HEAD"))
    public void revelation$setWorldWithered(ServerWorld world, LivingEntity other, CallbackInfoReturnable<Boolean> cir) {
        if (other instanceof WitherEntity) RevelationScoreboardComponents.WITHERED.get(world.getScoreboard()).setValue(true);
    }

}
