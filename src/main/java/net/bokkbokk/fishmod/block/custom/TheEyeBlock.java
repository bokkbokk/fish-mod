package net.bokkbokk.fishmod.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ColoredFallingBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ColorCode;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TheEyeBlock extends ColoredFallingBlock {
    public TheEyeBlock(ColorCode color, Settings settings) {
        super(color, settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        System.out.println("you used");
        if (player.isSneaking()) {
            player.sendMessage(Text.literal("You did the crouchnclick"));
        }
        return super.onUse(state, world, pos, player, hit);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if ((placer instanceof PlayerEntity) && !world.isClient){
            ((PlayerEntity) placer).sendMessage(Text.literal("It watches"));
            placer.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, -1));
            BlockPos newPos = pos.down();
            BlockState belowState = world.getBlockState(newPos);


        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient) {
            player.sendMessage(Text.literal("You have been punished"));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, -1));

        }
        return super.onBreak(world, pos, state, player);
    }

}
