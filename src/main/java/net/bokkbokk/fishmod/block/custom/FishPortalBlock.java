package net.bokkbokk.fishmod.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.EndGatewayBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionTypes;

import java.awt.*;

import static net.bokkbokk.fishmod.world.dimension.ModDimensions.FISHDIM_LEVEL_KEY;

public class FishPortalBlock extends EndGatewayBlock {
    public FishPortalBlock(Settings settings) {
        super(settings);
    }

}
