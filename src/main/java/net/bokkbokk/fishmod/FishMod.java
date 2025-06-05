package net.bokkbokk.fishmod;

import net.bokkbokk.fishmod.block.ModBlocks;
import net.bokkbokk.fishmod.entity.ModEntities;
import net.bokkbokk.fishmod.entity.custom.CrawlerSmallEntity;
import net.bokkbokk.fishmod.helper.ModConverters;
import net.bokkbokk.fishmod.helper.OverlayManager;
import net.bokkbokk.fishmod.item.ModItemGroups;
import net.bokkbokk.fishmod.item.ModItems;
import net.bokkbokk.fishmod.networking.ModMessages;
import net.bokkbokk.fishmod.networking.packet.RedScreenOnS2CPacket;
import net.bokkbokk.fishmod.sound.ModSounds;
import net.bokkbokk.fishmod.world.biome.ModBiomes;
import net.bokkbokk.fishmod.world.dimension.ModDimensions;
import net.bokkbokk.fishmod.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.kyrptonaught.customportalapi.event.CPASoundEventData;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class FishMod implements ModInitializer {

	private final List<ScheduledTask> scheduledTasks = new LinkedList<>();
	private final Map<ServerPlayerEntity, float[]> lockedViews = new ConcurrentHashMap<>();

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

			for (Map.Entry<ServerPlayerEntity, float[]> entry : lockedViews.entrySet()) {
				ServerPlayerEntity player = entry.getKey();
				float[] angles = entry.getValue();
				// Add a small random shake to yaw and pitch
				float shakeYaw = angles[0] + (float)((Math.random() - 0.5) * 4);   // ±2 degrees
				float shakePitch = angles[1] + (float)((Math.random() - 0.5) * 4); // ±2 degrees
				player.setYaw(shakeYaw);
				player.setPitch(shakePitch);
				player.networkHandler.requestTeleport(
						player.getX(), player.getY(), player.getZ(),
						shakeYaw, shakePitch
				);
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

		ModEntities.registerModEntities();

		ModWorldGeneration.generateModWorldGen();

		ModMessages.registerC2SPackets();


		ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((player, fromWorld, toWorld) -> {


//			System.out.println(toWorld.getRegistryKey());
//			System.out.println(ModDimensions.FISHDIM_LEVEL_KEY);
//			System.out.println(ModDimensions.FISH_DIM_TYPE);
//			System.out.println(ModDimensions.FISHDIM_KEY);

			if (toWorld.getRegistryKey().equals(ModDimensions.FISHDIM_LEVEL_KEY)){

				if (player.getInventory().contains(Items.FISHING_ROD.getDefaultStack())) {
					player.sendMessage(Text.literal("YOU DARE BRING THAT SHIT IN HERE?"));
					player.networkHandler.sendPacket(new GameStateChangeS2CPacket(GameStateChangeS2CPacket.ELDER_GUARDIAN_EFFECT, (int) 1.0f));

				}

				player.sendMessage(Text.literal("THE FISH WELCOMES YOU"), true);
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER,-1,4,true,false,false));
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION,-1,4,true,false,false));
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,-1, 2,true,false,false));




			}
			else {
				player.clearStatusEffects();
				OverlayManager.disableRedOverlay(player);
				player.sendMessage(Text.literal("THE FISH BIDS YOU FAREWELL"), true);


			}



		}); {

		}



		ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((world, entity, killedEntity) -> {
			if (killedEntity instanceof FishEntity) {
				LOGGER.info(entity.getName().getString() + " just killed " + " A FISH!!!!!!!! " +killedEntity.getName().getString());

				if (world.getRegistryKey().equals(ModDimensions.FISHDIM_LEVEL_KEY)) {
					LOGGER.info("yeah ts happened in the holy realm");

					if (entity instanceof PlayerEntity) {
						PlayerEntity player = (PlayerEntity) entity;
						player.sendMessage(Text.literal("YOU HAVE MADE A MISTAKE"),true);
						LivingEntity livingEntity = (LivingEntity) entity;
						livingEntity.clearStatusEffects();

						livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 400,4,true,false,false));
						livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 400,4,true,false,false));
						livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 400,4,true,false,false));
						livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 400,4,true,false,false));

						// int duration, int amplifier, boolean ambient, boolean showParticles, boolean showIcon

						ServerPlayerEntity servPlay = (ServerPlayerEntity) entity;

						world.playSound(
								null, // null means all players near the position will hear it
								player.getX(), player.getY(), player.getZ(), // player's current position
								ModSounds.FISH_CURSE, // your custom sound
								SoundCategory.MASTER,
								1.0F, // volume
								1.0F  // pitch
						);

						float yaw = ModConverters.getYaw(player,killedEntity.getBlockPos());
						float pitch = ModConverters.getPitch(player,killedEntity.getBlockPos());
						lockPlayerView(servPlay, yaw, pitch);
						OverlayManager.enableRedOverlay(servPlay);
						int soundDurationTicks = 137; // Replace with actual duration of FISH_CURSE in ticks
						scheduleTask(() -> servPlay.kill(), soundDurationTicks);
					}
				}
			}
		});



		ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer,newPlayer,alive) -> {
			System.out.println("player respawned");
			unlockPlayerView(newPlayer);
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

				.setPortalSearchYRange(40,115)
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
				.registerPostTPPortalAmbience(player -> new CPASoundEventData(
						ModSounds.DIM_ENTRY, // Your custom sound event
						1.0F,                        // Volume
						1.0F                         // Pitch
				))



				.registerPortal();


		FabricDefaultAttributeRegistry.register(ModEntities.CRAWLER_SMALL, CrawlerSmallEntity.createAttributes());




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

	public void lockPlayerView(ServerPlayerEntity player, float yaw, float pitch) {
		lockedViews.put(player, new float[]{yaw, pitch});
	}

	public void unlockPlayerView(ServerPlayerEntity player) {
		lockedViews.remove(player);
	}




}
