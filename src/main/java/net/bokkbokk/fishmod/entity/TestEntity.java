package net.bokkbokk.fishmod.entity;

import net.bokkbokk.fishmod.FishMod;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.AutomaticItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class TestEntity extends FallingBlockEntity {
    private BlockState block;
    private boolean destroyedOnLanding;

    public TestEntity(EntityType<? extends FallingBlockEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        if (this.block.isAir()) {
            this.discard();
        } else {
            Block block = this.block.getBlock();
            this.timeFalling++;
            this.applyGravity();
            this.move(MovementType.SELF, this.getVelocity());
            this.tickPortalTeleportation();
            if (!this.getWorld().isClient && (this.isAlive() || this.shouldDupe)) {
                BlockPos blockPos = this.getBlockPos();
                boolean bl = this.block.getBlock() instanceof ConcretePowderBlock;
                boolean bl2 = bl && this.getWorld().getFluidState(blockPos).isIn(FluidTags.WATER);
                double d = this.getVelocity().lengthSquared();
                if (bl && d > 1.0) {
                    BlockHitResult blockHitResult = this.getWorld()
                            .raycast(
                                    new RaycastContext(
                                            new Vec3d(this.prevX, this.prevY, this.prevZ), this.getPos(), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.SOURCE_ONLY, this
                                    )
                            );
                    if (blockHitResult.getType() != HitResult.Type.MISS && this.getWorld().getFluidState(blockHitResult.getBlockPos()).isIn(FluidTags.WATER)) {
                        blockPos = blockHitResult.getBlockPos();
                        bl2 = true;
                    }
                }

                if (this.isOnGround() || bl2) {
                    BlockState blockState = this.getWorld().getBlockState(blockPos);
                    this.setVelocity(this.getVelocity().multiply(0.7, -0.5, 0.7));
                    if (!blockState.isOf(Blocks.MOVING_PISTON)) {
                        if (!this.destroyedOnLanding) {
                            boolean bl3 = blockState.canReplace(new AutomaticItemPlacementContext(this.getWorld(), blockPos, Direction.DOWN, ItemStack.EMPTY, Direction.UP));
                            boolean bl4 = FallingBlock.canFallThrough(this.getWorld().getBlockState(blockPos.down())) && (!bl || !bl2);
                            boolean bl5 = this.block.canPlaceAt(this.getWorld(), blockPos) && !bl4;
                            if (bl3 && bl5) {
                                if (this.block.contains(Properties.WATERLOGGED) && this.getWorld().getFluidState(blockPos).getFluid() == Fluids.WATER) {
                                    this.block = this.block.with(Properties.WATERLOGGED, true);
                                }

                                if (this.getWorld().setBlockState(blockPos, this.block, Block.NOTIFY_ALL)) {
                                    ((ServerWorld)this.getWorld())
                                            .getChunkManager()
                                            .chunkLoadingManager
                                            .sendToOtherNearbyPlayers(this, new BlockUpdateS2CPacket(blockPos, this.getWorld().getBlockState(blockPos)));
                                    this.discard();
                                    if (block instanceof LandingBlock) {
                                        ((LandingBlock)block).onLanding(this.getWorld(), blockPos, this.block, blockState, this);
                                    }

                                    if (this.blockEntityData != null && this.block.hasBlockEntity()) {
                                        BlockEntity blockEntity = this.getWorld().getBlockEntity(blockPos);
                                        if (blockEntity != null) {
                                            NbtCompound nbtCompound = blockEntity.createNbt(this.getWorld().getRegistryManager());

                                            for (String string : this.blockEntityData.getKeys()) {
                                                nbtCompound.put(string, this.blockEntityData.get(string).copy());
                                            }

                                            try {
                                                blockEntity.read(nbtCompound, this.getWorld().getRegistryManager());
                                            } catch (Exception var15) {
                                                FishMod.LOGGER.error("Failed to load block entity from falling block", (Throwable)var15);
                                            }

                                            blockEntity.markDirty();
                                        }
                                    }
                                } else if (this.dropItem && this.getWorld().getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
                                    this.discard();
                                    this.onDestroyedOnLanding(block, blockPos);
                                    this.dropItem(block);
                                }
                            } else {
                                this.discard();
                                if (this.dropItem && this.getWorld().getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
                                    this.onDestroyedOnLanding(block, blockPos);
                                    this.dropItem(block);
                                }
                            }
                        } else {
                            this.discard();
                            this.onDestroyedOnLanding(block, blockPos);
                        }
                    }
                } else if (!this.getWorld().isClient
                        && (this.timeFalling > 100 && (blockPos.getY() <= this.getWorld().getBottomY() || blockPos.getY() > this.getWorld().getTopY()) || this.timeFalling > 600)) {
                    if (this.dropItem && this.getWorld().getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
                        this.dropItem(block);
                    }

                    this.discard();
                }
            }

            this.setVelocity(this.getVelocity().multiply(0.98));
        }
    }

}
