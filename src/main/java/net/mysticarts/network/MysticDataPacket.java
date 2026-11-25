package net.mysticarts.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.VarInt;
import net.minecraft.network.codec.StringBytesCodec;
import net.minecraft.network.protocol.game.CustomGamePacketPayload;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public record MysticDataPacket(int level, List<String> milestones) implements CustomGamePacketPayload {
    public static final Identifier ID = new Identifier("mysticarts", "player_data");
    public static final PacketCodec<PacketByteBuf, MysticDataPacket> CODEC = PacketCodec.id(ID, PacketCodec.tuple(VarInt.of(1), VarInt.of(0), // This is a bit complex for a simple tutorial, let's simplify
        VarInt.of(1), // placeholder
        VarInt.of(0)  // placeholder
    ));
    // Actually, let's use the simpler approach for the demo.
    
    @Override
    public Identifier id() {
        return ID;
    }
}
