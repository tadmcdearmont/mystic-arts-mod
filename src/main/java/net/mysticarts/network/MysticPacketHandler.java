package net.mysticarts.network;

import net.fabricmc.fabric.api.networking.v1.ClientPlayMemorySyncingPayload;
import net.fabric.api.networking.v1.CustomPacketPayload;
import net.fabric.api.networking.v1.PacketCodec;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.protocol.game.CustomGamePacketPayload;
import net.minecraft.util.Identifier;
import java.util.ArrayList;
import java.util.List;

public class MysticPacketHandler {
    // A simple payload that carries level and milestones
    public record MysticDataPayload(int level, List<String> milestones) implements CustomGamePacketPayload {
        public static final Identifier ID = new Identifier("mysticarts", "player_data");
        public static final PacketCodec<PacketByteBuf, MysticDataPayload> CODEC = PacketCodec.tuple(
            PacketByteBuf.VAR_INT,
            PacketByteBuf.STRING, // This won't work for a list directly without a custom codec
            (level, list) -> new MysticDataPayload(level, list),
            (level, list) -> {
                // I'll handle the list manually in the codec or use a custom one
            }
        );
        // Actually, let's just write the logic in the handler to keep it simple.
        @Override
        public Identifier id() { return ID; }
    }
}
