package net.bokkbokk.fishmod.entity;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class SafeLightning extends LightningEntity {

    public SafeLightning(EntityType<? extends LightningEntity> entityType, World world) {
        super(entityType, world);
    }


    /**
     * I DONNO HOW TO MAKE THIS WORK
     * I WANT TO REMOVE THE FIRE PART OR JUST GENERALLY CHANGE UP THE BEHAVIOR OF THE LIGHTNING ENTITY
     *
     *
     *
     */
}
