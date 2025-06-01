package net.bokkbokk.fishmod.item.custom;

import net.bokkbokk.fishmod.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleGroup;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;

import static net.minecraft.item.BoneMealItem.useOnFertilizable;
import static net.minecraft.item.BoneMealItem.useOnGround;

public class FishGemDustItem extends Item {

    public FishGemDustItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockPos blockPos2 = blockPos.offset(context.getSide());

        if (useOnFertilizable(context.getStack(), world, blockPos)) {
            if (!world.isClient) {
                context.getPlayer().emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
                world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, blockPos, 15);
            }

            return ActionResult.success(world.isClient);
        } else {

            if (world.getBlockState(blockPos).getBlock().equals(Blocks.DIRT)) {
                if (!world.isClient) {
                    world.playSound(null, blockPos, ModSounds.FISH_DUST_USE, SoundCategory.AMBIENT);
                    world.setBlockState(blockPos, Blocks.GRASS_BLOCK.getDefaultState());
                    context.getStack().decrement(1);
                }
                context.getPlayer().swingHand(context.getHand());

            }
            if (world.getBlockState(blockPos).getBlock().equals(Blocks.COARSE_DIRT)) {
                if (!world.isClient) {
                    world.playSound(null, blockPos, ModSounds.FISH_DUST_USE, SoundCategory.AMBIENT);
                    world.setBlockState(blockPos, Blocks.PODZOL.getDefaultState());
                    context.getStack().decrement(1);
                }
                context.getPlayer().swingHand(context.getHand());
            }


            BlockState blockState = world.getBlockState(blockPos);
            boolean bl = blockState.isSideSolidFullSquare(world, blockPos, context.getSide());
            if (bl && useOnGround(context.getStack(), world, blockPos2, context.getSide())) {
                if (!world.isClient) {
                    context.getPlayer().emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
                    world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, blockPos2, 15);
                }

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        }
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        //user.sendMessage(Text.literal("You used Fish Gem Dust on " + entity.getName().getString()), true);


        entity.getWorld().addParticle(
                ParticleTypes.HAPPY_VILLAGER,
                entity.getX(),
                entity.getY()+1.0,
                entity.getZ(),
                0.0,0.0,0.0
        );

        user.getWorld().playSound(null,entity.getBlockPos(), ModSounds.FISH_DUST_USE, SoundCategory.AMBIENT);
        user.swingHand(hand);



        if (entity instanceof PlayerEntity) {
            return ActionResult.PASS; // Prevents using on players
        }
        if (entity.isAlive() && !entity.isInvulnerable() && !(entity instanceof HostileEntity) && (entity instanceof AnimalEntity)  ) {
            entity.heal(3.0F); // Heals the entity by 3 health points
            ((AnimalEntity) entity).setLoveTicks(100);
            stack.decrement(1);
            if (entity.isBaby()) {

                ((AnimalEntity) entity).growUp(10);
                ((AnimalEntity) entity).setBaby(false);
            }
            // Here you can add any specific effects you want to apply to the entity
            // For example, you could heal the entity or apply a potion effect
            // entity.heal(5.0F); // Example: Heal the entity by 5 health points
        }

        return super.useOnEntity(stack, user, entity, hand);
    }

}
