package net.bokkbokk.fishmod.networking;

import net.bokkbokk.fishmod.FishMod;
import net.bokkbokk.fishmod.networking.packet.RedScreenOffS2CPacket;
import net.bokkbokk.fishmod.networking.packet.RedScreenOnS2CPacket;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier DRINKING_ID = Identifier.of(FishMod.MOD_ID, "drinking_payload");
    public static final Identifier OFF_RED_SCREEN_PACKET_ID = Identifier.of(FishMod.MOD_ID, "off_red_screen_payload");
    public static final Identifier ON_RED_SCREEN_PACKET_ID = Identifier.of(FishMod.MOD_ID, "on_red_screen_payload");

    public static void registerC2SPackets() {
        // Register C2S packets here if needed
        PayloadTypeRegistry.playS2C().register(RedScreenOnS2CPacket.ID, RedScreenOnS2CPacket.CODEC);
        PayloadTypeRegistry.playS2C().register(RedScreenOffS2CPacket.ID, RedScreenOffS2CPacket.CODEC);
    }
    public static void registerS2CPackets() {
        // Register S2C packets here if needed
    }


}
