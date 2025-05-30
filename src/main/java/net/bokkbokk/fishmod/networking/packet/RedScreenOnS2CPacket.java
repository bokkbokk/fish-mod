package net.bokkbokk.fishmod.networking.packet;

import net.bokkbokk.fishmod.networking.ModMessages;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record RedScreenOnS2CPacket(BlockPos blockPos) implements CustomPayload {
    public static final CustomPayload.Id<RedScreenOnS2CPacket> ID = new CustomPayload.Id<>(ModMessages.ON_RED_SCREEN_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, RedScreenOnS2CPacket> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, RedScreenOnS2CPacket::blockPos, RedScreenOnS2CPacket::new);
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