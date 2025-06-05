package net.bokkbokk.fishmod.block.custom;

import net.bokkbokk.fishmod.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class GemMagmaBlock extends MagmaBlock {

    public boolean powered = false;

    public GemMagmaBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {

    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BetterBubblesBlock.update(world, pos.up(), state);
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (world.isClient) {
            return;
        }
        world.scheduleBlockTick(pos, this, 20);
        powered = world.isReceivingRedstonePower(pos); // if powered then powered else then unpowered
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        System.out.println("is powered?");
        System.out.println(powered);
        return super.onUse(state, world, pos, player, hit);
    }

    public Boolean isPowered() {
        return powered;
    }
}
