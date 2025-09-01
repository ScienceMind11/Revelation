package com.sember.revelation.component.entity;

import com.sember.revelation.item.AccessoryItem;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.sync.C2SComponentPacketWriter;
import org.ladysnake.cca.api.v3.entity.C2SSelfMessagingComponent;

import java.util.ArrayList;
import java.util.List;

public class AccessoriesComponent implements ListComponent<ItemStack>, AutoSyncedComponent, C2SSelfMessagingComponent {

    private List<ItemStack> accessories;
    private int selectedSlot;
    private int numSlots;

    public AccessoriesComponent() {
        this.accessories = new ArrayList<>(5);
        this.selectedSlot = 0;
        this.numSlots = 5;
    }

    @Override
    public ItemStack get(int index) {
        return this.accessories.get(index);
    }

    @Override
    public void add(ItemStack item) {
        this.accessories.add(item);
    }

    @Override
    public void remove(ItemStack item) {
        this.accessories.remove(item);
    }

    @Override
    public int size() {
        return this.accessories.size();
    }

    @Override
    public List<ItemStack> getList() {
        return this.accessories;
    }

    @Override
    public void setList(List<ItemStack> list) {
        this.accessories = list;
    }

    public boolean isWearing(Item item) {
        return this.accessories.stream().anyMatch(stack -> stack.getItem() == item);
    }

    public boolean newAccessory(ItemStack accessory) {
        if (this.accessories.size() >= numSlots) return false;
        sendC2SMessage(buf -> ItemStack.PACKET_CODEC.encode(buf, accessory));
        return true;
    }

    public int getSelectedSlot() {
        return this.selectedSlot;
    }

    public void setSelectedSlot(int selectedSlot) {
        this.selectedSlot = selectedSlot;
    }

    public int getNumSlots() {
        return this.numSlots;
    }

    public void setNumSlots(int numSlots) {
        this.numSlots = numSlots;
    }

    @Override
    public void readData(ReadView readView) {
        this.numSlots = readView.getInt("slots", 5);
        this.selectedSlot = readView.getInt("selected", 0);
        for (int i = 0; i < this.numSlots; i++) {
            this.accessories.add(readView.read("item" + i, ItemStack.CODEC).orElse(ItemStack.EMPTY));
        }
    }

    @Override
    public void writeData(WriteView writeView) {
        writeView.putInt("slots", this.numSlots);
        writeView.putInt("selected", this.selectedSlot);
        for (int i = 0; i < this.accessories.size(); i++) {
            writeView.put("item" + i, ItemStack.CODEC, this.get(i));
        }
    }

    @Override
    public void handleC2SMessage(RegistryByteBuf buf) {
        this.accessories.add(ItemStack.PACKET_CODEC.decode(buf));
    }

}
