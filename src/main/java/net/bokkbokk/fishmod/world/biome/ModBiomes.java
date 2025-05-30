package net.bokkbokk.fishmod.world.biome;

import net.bokkbokk.fishmod.FishMod;
import net.minecraft.client.sound.AmbientSoundLoops;
import net.minecraft.client.sound.BiomeEffectSoundPlayer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.MusicType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.OceanPlacedFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class ModBiomes {
    public static final RegistryKey<Biome> FISH_BIOME = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(FishMod.MOD_ID, "fish_biome"));

    public static void bootstrap(Registerable<Biome> context){
        context.register(FISH_BIOME, fishBiome(context));
    }


    public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addSprings(builder);
        DefaultBiomeFeatures.addFrozenTopLayer(builder);
    }




//    public static Biome createWarmOcean(RegistryEntryLookup<PlacedFeature> featureLookup, RegistryEntryLookup<ConfiguredCarver<?>> carverLookup) {
//        SpawnSettings.Builder builder = (new SpawnSettings.Builder()).spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.PUFFERFISH, 15, 1, 3));
//        DefaultBiomeFeatures.addWarmOceanMobs(builder, 10, 4);
//        GenerationSettings.LookupBackedBuilder lookupBackedBuilder = createOceanGenerationSettings(featureLookup, carverLookup).feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.WARM_OCEAN_VEGETATION).feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_WARM).feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEA_PICKLE);
//        return createOcean(builder, 4445678, 270131, lookupBackedBuilder);
//    }









    public static Biome fishBiome(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        //spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.PORCUPINE, 2, 3, 5));

        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COD, 5, 4, 4));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SALMON, 5, 4, 4));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.TROPICAL_FISH, 5, 4, 4));

        //DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        //DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
//        DefaultBiomeFeatures.addMossyRocks(biomeBuilder);
//        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
//        DefaultBiomeFeatures.addExtraGoldOre(biomeBuilder);

//        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS);
//        DefaultBiomeFeatures.addForestFlowers(biomeBuilder);
//        DefaultBiomeFeatures.addLargeFerns(biomeBuilder);

//        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
//        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);

        return new Biome.Builder()
                .precipitation(false)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0xe82e3b)
                        .waterFogColor(0xbf1b26)
                        .skyColor(0x30c918)
                        .grassColor(0x7f03fc)
                        .foliageColor(0xd203fc)
                        .fogColor(0x22a1e6)
                        .moodSound(BiomeMoodSound.CAVE)
                        .music(MusicType.createIngameMusic(RegistryEntry.of(MusicType.UNDERWATER.getSound().value()))).build())
//                        .music(MusicType.createIngameMusic(RegistryEntry.of(MusicType.UNDERWATER))).build())
                .build();
    }
}
