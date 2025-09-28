package com.sember.revelation.mixin.client;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.sember.revelation.registry.RevelationComponents;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.render.state.ColoredQuadGuiElementRenderState;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(DrawContext.class)
public abstract class DrawContextMixin {

    @Shadow public abstract void drawHorizontalLine(int x1, int x2, int y, int color);

    @Shadow @Final private Matrix3x2fStack matrices;
    @Shadow @Final public GuiRenderState state;
    @Shadow @Final public DrawContext.ScissorStack scissorStack;

    @Shadow public abstract void fillGradient(int startX, int startY, int endX, int endY, int colorStart, int colorEnd);

    @Unique
    private static final Map<Integer, Integer> COLORS = Map.of(
            1, 0xFF00FFFF,
            2, 0xFF00FF00,
            3, 0xFFFF0000
    );

    @Inject(method = "drawStackOverlay(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V", at = @At(value = "INVOKE", target = "Lorg/joml/Matrix3x2fStack;popMatrix()Lorg/joml/Matrix3x2fStack;"))
    private void revelation$drawMarker(TextRenderer textRenderer, ItemStack stack, int x, int y, String stackCountText, CallbackInfo ci) {

        int marker = stack.getOrDefault(RevelationComponents.MARKER, 0);

        if (marker <= 0 || marker > 3) return;

        int color = COLORS.get(marker);
        this.drawHorizontalLine(x - 1, x + 16, y + 16, color);
        this.fillGradient(x - 1, y, x, y + 16, 0x00000000, color);
        this.fillGradient(x + 16, y, x + 17, y + 16, 0x00000000, color);

    }

}
