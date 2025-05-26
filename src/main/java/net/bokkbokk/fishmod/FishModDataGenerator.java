package net.bokkbokk.fishmod;

import net.bokkbokk.fishmod.datagen.ModBlockTagProvider;
import net.bokkbokk.fishmod.datagen.ModLootTableProvider;
import net.bokkbokk.fishmod.datagen.ModModelProvider;
import net.bokkbokk.fishmod.datagen.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class FishModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
	}
}
