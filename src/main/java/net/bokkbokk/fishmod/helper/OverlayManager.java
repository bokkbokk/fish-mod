package net.bokkbokk.fishmod.helper;

import net.bokkbokk.fishmod.networking.packet.RedScreenOffS2CPacket;
import net.bokkbokk.fishmod.networking.packet.RedScreenOnS2CPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;

public class OverlayManager {
    public static void enableRedOverlay(ServerPlayerEntity player){
        ServerPlayNetworking.send(player, new RedScreenOnS2CPacket(player.getBlockPos()));
    }
    public static void disableRedOverlay(ServerPlayerEntity player){
        ServerPlayNetworking.send(player, new RedScreenOffS2CPacket(player.getBlockPos()));
    }

}
