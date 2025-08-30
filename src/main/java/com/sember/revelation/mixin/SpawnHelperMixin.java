package com.sember.revelation.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.sember.revelation.Revelation;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SpawnHelper.class)
public class SpawnHelperMixin {

    @ModifyArg(method = "spawnEntitiesInChunk(Lnet/minecraft/entity/SpawnGroup;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/world/chunk/Chunk;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/SpawnHelper$Checker;Lnet/minecraft/world/SpawnHelper$Runner;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/SpawnHelper;createMob(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/EntityType;)Lnet/minecraft/entity/mob/MobEntity;"), index = 1)
    private static EntityType<?> revelation$spawnWitherSkeletons(EntityType<?> type, @Local(argsOnly = true) Chunk chunk, @Local(argsOnly = true) BlockPos pos) {
        return chunk.getBiomeForNoiseGen(pos.getX(), pos.getY(), pos.getZ()).matchesKey(BiomeKeys.SOUL_SAND_VALLEY) &&
                type == EntityType.SKELETON &&
                Math.random() > 0.80 ?
                EntityType.WITHER_SKELETON : type;
    }


    @ModifyReturnValue(method = "canSpawn", at = @At("RETURN"))
    private static boolean revelation$stopMagmaCubeSpawning(boolean original, ServerWorld world, SpawnGroup group, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, SpawnSettings.SpawnEntry spawnEntry, BlockPos.Mutable pos, double squaredDistance) {
        return original && (spawnEntry.type() != EntityType.MAGMA_CUBE || Revelation.getWitheredComponent(world).getValue());
    }

}
