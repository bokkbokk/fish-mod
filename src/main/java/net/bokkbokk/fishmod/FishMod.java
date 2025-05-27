package net.bokkbokk.fishmod;

import com.mojang.blaze3d.systems.RenderSystem;
import net.bokkbokk.fishmod.block.ModBlocks;
import net.bokkbokk.fishmod.item.ModItemGroups;
import net.bokkbokk.fishmod.item.ModItems;
import net.bokkbokk.fishmod.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.minecraft.block.Portal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Potions;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FishMod implements ModInitializer {
	public static final String MOD_ID = "fishmod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH");

		ModItems.registerModItems();

		ModBlocks.registerModBlocks();

		ModItemGroups.registerItemGroups();

		ModWorldGeneration.generateModWorldGen();

		ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((world, entity, killedEntity) -> {

//			user.sendMessage(Text.literal(entity.getName().getString()), true);
//			LOGGER.info(entity.getName().getString() + " just killed " + killedEntity.getName().getString());
			if (killedEntity instanceof FishEntity) {
				LOGGER.info(entity.getName().getString() + " just killed " + " A FISH!!!!!!!! " +killedEntity.getName().getString());
				DimensionType dimType = entity.getWorld().getDimension();
				Identifier effects = dimType.effects();

				if (effects.equals(DimensionTypes.THE_END_ID)) {
					LOGGER.info("yeah ts happened in the holy realm");

					if (entity instanceof PlayerEntity) {
						entity.sendMessage(Text.literal("YOU HAVE MADE A MISTAKE"));
						LivingEntity livingEntity = (LivingEntity) entity;
						livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 4000));
						RenderSystem.colorMask(true,false,false,true);


					}







				}






			}
			//return Ac
		});


	}


}