package com.sember.revelation.client;

import com.sember.revelation.Revelation;
import com.sember.revelation.RevelationClient;
import com.sember.revelation.component.entity.AccessoriesComponent;
import com.sember.revelation.registry.RevelationComponents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class AccessoriesHudElement {

    public static final Identifier ACCESSORIES_TEXTURE = Revelation.id("textures/gui/accessories.png");

    public static void render(DrawContext context, RenderTickCounter renderTickCounter) {

        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;
        if (player == null) return;

        if (!RevelationClient.OPEN_ACCESSORIES.isPressed()) return;

        int centerX = context.getScaledWindowWidth() / 2;
        int centerY = context.getScaledWindowHeight() / 2;

        AccessoriesComponent accessories = RevelationComponents.ACCESSORIES.get(player);

        int selectedSlot = accessories.getSelected();
        int numSlots = accessories.getSlots();
        int left = (centerX - 10) + ((-numSlots / 2) * 20);

        for (int i = 0; i < numSlots; i++) {

            int x = i - numSlots / 2;

            context.drawTexture(
                    RenderPipelines.GUI_TEXTURED,
                    ACCESSORIES_TEXTURE,
                    (centerX - 10) + (x * 20), centerY + 32,
                    0, 0,
                    20, 20,
                    64, 64
            );

            context.drawItem(
                    accessories.get(i),
                    (centerX - 10) + (x * 20) + 2, centerY + 32 + 2
            );

        }

        context.drawBorder(
                left - 1, centerY + 32 - 1,
                numSlots * 20 + 2, 22,
                0xFF000000
        );

        context.drawTexture(
                RenderPipelines.GUI_TEXTURED,
                ACCESSORIES_TEXTURE,
                (left - 2) + (selectedSlot * 20), centerY + 32 - 2,
                20, 0,
                24, 24,
                64, 64
        );

    }

}
