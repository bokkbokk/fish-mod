package net.bokkbokk.fishmod.world.gen;

import net.bokkbokk.fishmod.world.ModPlacedFeatures;
import net.bokkbokk.fishmod.world.biome.ModBiomes;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {

    public static void generateOres(){
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.FISH_BIOME)
                ,GenerationStep.Feature.UNDERGROUND_ORES
                ,ModPlacedFeatures.FISH_ORE_BLOCK_PLACED_KEY);

    }

}
