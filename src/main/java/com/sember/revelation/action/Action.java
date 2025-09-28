package com.sember.revelation.action;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.apache.logging.log4j.util.TriConsumer;

public class Action {

    private final int cost;
    private final Use action;

    public Action(int cost, Use action) {
        this.cost = cost;
        this.action = action;
    }

    public void use(PlayerEntity player, ItemStack stack, World world) {
        this.action.use(player, stack, world);
    }

    public int getCost() {
        return cost;
    }

    @FunctionalInterface
    public interface Use {
        void use(PlayerEntity player, ItemStack stack, World world);
    }

    public enum Marker {
        PRIMARY,
        SECONDARY,
        TERTIARY
    }

}
