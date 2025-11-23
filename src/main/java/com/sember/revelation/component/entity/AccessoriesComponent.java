package com.sember.revelation.component.entity;

import com.mojang.serialization.Codec;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.entity.C2SSelfMessagingComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AccessoriesComponent implements AutoSyncedComponent {

    private static final int BASE_SLOTS = 5;

    private final PlayerEntity provider;

    private int slots;
    private int selected;
    private SimpleInventory accessories;

    public AccessoriesComponent(PlayerEntity provider) {
        this.provider = provider;
        this.slots = BASE_SLOTS;
        this.selected = 0;
        this.accessories = new SimpleInventory(BASE_SLOTS);
    }

    public ItemStack get(int slot) {
        if (slot < 0 || slot >= this.slots || slot >= this.accessories.size() || this.accessories.getStack(slot) == null) return ItemStack.EMPTY;
        return this.accessories.getStack(slot);
    }

    public void set(int slot, ItemStack stack) {
        if (slot < 0 || slot >= this.slots || slot >= this.accessories.size()) return;
        this.accessories.setStack(slot, stack);
    }

    public int getSelected() {
        return this.selected;
    }
    public void setSelected(int selected) {
        this.selected = selected;
    }

    public int getSlots() {
        return this.slots;
    }
    public void setSlots(int slots) {
        this.slots = slots;
    }

    public boolean isWearing(Item item) {
        return this.accessories.containsAny(stack -> stack.getItem() == item);
    }

    @Override
    public void readData(ReadView view) {
        this.slots = view.getInt("slots", BASE_SLOTS);
        this.selected = view.getInt("selected", 0);
        this.accessories = new SimpleInventory(view
                .read("items", codec())
                .orElse(new ArrayList<>(5))
                .toArray(new ItemStack[5])
        );
    }

    @Override
    public void writeData(WriteView view) {
        view.putInt("slots", this.slots);
        view.putInt("selected", this.selected);
        view.put("items", codec(), this.accessories.getHeldStacks());
    }

    private Codec<List<ItemStack>> codec() {
        return Codec.list(ItemStack.CODEC, 0, this.slots);
    }

}
