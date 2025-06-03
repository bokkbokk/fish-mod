package net.bokkbokk.fishmod.helper;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public class ModConverters {

    public static float getYaw(PlayerEntity player, BlockPos target) {
        double targetX = target.getX() + 0.5;
        double targetY = target.getY() + 0.5;
        double targetZ = target.getZ() + 0.5;

        double dx = targetX - player.getX();
        double dz = targetZ - player.getZ();


        float yaw = (float)(Math.toDegrees(Math.atan2(dz, dx)) - 90.0F);

        return yaw;

    }
    public static float getPitch(PlayerEntity player, BlockPos target) {
        double targetX = target.getX() + 0.5;
        double targetY = target.getY() + 0.5;
        double targetZ = target.getZ() + 0.5;

        double dx = targetX - player.getX();
        double dy = targetY - (player.getY() + player.getEyeHeight(player.getPose()));
        double dz = targetZ - player.getZ();


        double distanceXZ = Math.sqrt(dx * dx + dz * dz);
        float pitch = (float)(-Math.toDegrees(Math.atan2(dy, distanceXZ)));

        return pitch;
    }
}
