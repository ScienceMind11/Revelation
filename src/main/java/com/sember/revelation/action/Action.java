package com.sember.revelation.action;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.apache.logging.log4j.util.TriConsumer;

public abstract class Action {

    private int cost;

    public Action(int cost) {
        this.cost = cost;
    }

    public static Action create(int cost, TriConsumer<PlayerEntity, ItemStack, World> use) {
        return new Action() {
            @Override
            public void use(PlayerEntity player, ItemStack stack, World world) {
                use.accept(player, stack, world);
            }

            @Override
            public int getCost() {
                return cost;
            }
        };
    }

    public abstract void use(PlayerEntity player, ItemStack stack, World world);

    public abstract int getCost();

}
