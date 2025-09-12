package com.sember.revelation.network;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sember.revelation.Revelation;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minidev.asm.Accessor;

public record AccessoriesPacketPayload(boolean bool) implements CustomPayload {

    public static final Id<AccessoriesPacketPayload> ID = new Id<>(Revelation.id("accessories"));

    public static final PacketCodec<RegistryByteBuf, AccessoriesPacketPayload> CODEC = PacketCodec.of(AccessoriesPacketPayload::write, AccessoriesPacketPayload::new);

    public AccessoriesPacketPayload(RegistryByteBuf buf) {
        this(buf.readBoolean());
    }

    public void write(RegistryByteBuf buf) {
        buf.writeBoolean(bool);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

}
