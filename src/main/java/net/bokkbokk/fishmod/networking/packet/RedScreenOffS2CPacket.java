package net.bokkbokk.fishmod.networking.packet;

import net.bokkbokk.fishmod.networking.ModMessages;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record RedScreenOffS2CPacket(BlockPos blockPos) implements CustomPayload {
    public static final CustomPayload.Id<RedScreenOffS2CPacket> ID = new CustomPayload.Id<>(ModMessages.OFF_RED_SCREEN_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, RedScreenOffS2CPacket> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, RedScreenOffS2CPacket::blockPos, RedScreenOffS2CPacket::new);
    // should you need to send more data, add the appropriate record parameters and change your codec:
    // public static final PacketCodec<RegistryByteBuf, BlockHighlightPayload> CODEC = PacketCodec.tuple(
    //         BlockPos.PACKET_CODEC, BlockHighlightPayload::blockPos,
    //         PacketCodecs.INTEGER, BlockHighlightPayload::myInt,
    //         Uuids.PACKET_CODEC, BlockHighlightPayload::myUuid,
    //         BlockHighlightPayload::new
    // );


    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}