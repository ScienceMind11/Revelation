package com.sember.revelation.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.sember.revelation.Revelation;
import com.sember.revelation.registry.RevelationTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.registry.tag.BlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WitherEntity.class)
public class WitherEntityMixin {

    @ModifyReturnValue(method = "canDestroy", at = @At("RETURN"))
    private static boolean revelation$hehe(boolean original, @Local(argsOnly = true, ordinal = 0) BlockState block) {
        return original && !block.isIn(RevelationTags.NETHER);
    }

}
