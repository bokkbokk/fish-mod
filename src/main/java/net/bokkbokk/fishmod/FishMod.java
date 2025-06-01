package net.bokkbokk.fishmod;

import net.bokkbokk.fishmod.block.ModBlocks;
import net.bokkbokk.fishmod.helper.OverlayManager;
import net.bokkbokk.fishmod.item.ModItemGroups;
import net.bokkbokk.fishmod.item.ModItems;
import net.bokkbokk.fishmod.networking.ModMessages;
import net.bokkbokk.fishmod.networking.packet.RedScreenOnS2CPacket;
import net.bokkbokk.fishmod.sound.ModSounds;
import net.bokkbokk.fishmod.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class FishMod implements ModInitializer {

	private final List<ScheduledTask> scheduledTasks = new LinkedList<>();
	public static final String MOD_ID = "fishmod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



	@Override
	public void onInitialize() {



		ServerTickEvents.END_SERVER_TICK.register(server -> {
			Iterator<ScheduledTask> it = scheduledTasks.iterator();
			while (it.hasNext()) {
				ScheduledTask task = it.next();
				if (--task.ticksLeft <= 0) {
					task.run();
					it.remove();
				}
			}
		});



		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH GLORY TO THE FISH");

		ModItems.registerModItems();

		ModBlocks.registerModBlocks();

		ModItemGroups.registerItemGroups();

		ModSounds.registerSounds();

		ModWorldGeneration.generateModWorldGen();

		ModMessages.registerC2SPackets();


		ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((player, fromWorld, toWorld) -> {
			Float amb = toWorld.getDimension().ambientLight();


			if (amb == 0.4f){

				if (player.getInventory().contains(Items.FISHING_ROD.getDefaultStack())) {
					player.sendMessage(Text.literal("YOU DARE BRING THAT SHIT IN HERE?"));

				}

				player.sendMessage(Text.literal("THE FISH WELCOMES YOU"), true);
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER,-1));
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION,-1));
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,-1, 2));




			}
			else {
				player.clearStatusEffects();
				OverlayManager.disableRedOverlay(player);
				player.sendMessage(Text.literal("THE FISH BIDS YOU FAREWELL"), true);


			}



		}); {

		}



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
						PlayerEntity player = (PlayerEntity) entity;
						player.sendMessage(Text.literal("YOU HAVE MADE A MISTAKE"),true);
						LivingEntity livingEntity = (LivingEntity) entity;

						livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 400));
						livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 400));
						ServerPlayerEntity servPlay = (ServerPlayerEntity) entity;
						world.playSound(null,killedEntity.getBlockPos(),ModSounds.FISH_CURSE,SoundCategory.HOSTILE);
						OverlayManager.enableRedOverlay(servPlay);


						int soundDurationTicks = 137; // Replace with actual duration of FISH_CURSE in ticks
						scheduleTask(() -> servPlay.kill(), soundDurationTicks);










						




					}







				}






			}
			//return Ac
		});



		ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer,newPlayer,alive) -> {
			System.out.println("player respawned");
			//ServerPlayNetworking.send(newPlayer, new ExampleC2SPacket(newPlayer.getBlockPos()));
			if ((!Objects.equals(oldPlayer.getWorld(), newPlayer.getWorld()))) {
				System.out.println("Old playerworld isnt equal to newplayerworld");
				OverlayManager.disableRedOverlay(newPlayer);
			}

		});



		CustomPortalBuilder.beginPortal()
				.frameBlock(Blocks.BONE_BLOCK)
				.lightWithWater()
				.destDimID(Identifier.of("fishmod:fish_dim"))
				.tintColor(3,177,252)
				.flatPortal()

				.setPortalSearchYRange(40,60)
				.registerIgniteEvent((player, world, portalPos,framePos,ignitionSource) -> {
					//if (world.isClient()) {
						world.playSound(
								player,
								portalPos,
								SoundEvents.BLOCK_BELL_RESONATE,
								SoundCategory.BLOCKS,
								1.0F,
								1.0F
						);
					//}
//					return true;
				})



				.registerPortal();




	}

	// Call this method to schedule a task
	public void scheduleTask(Runnable action, int delayTicks) {
		scheduledTasks.add(new ScheduledTask(action, delayTicks));
	}

	private static class ScheduledTask {
		Runnable action;
		int ticksLeft;
		ScheduledTask(Runnable action, int ticks) {
			this.action = action;
			this.ticksLeft = ticks;
		}
		void run() {
			action.run();
		}
	}



}