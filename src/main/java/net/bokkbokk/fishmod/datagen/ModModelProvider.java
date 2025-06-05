package net.bokkbokk.fishmod.datagen;

import net.bokkbokk.fishmod.block.ModBlocks;
import net.bokkbokk.fishmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FISH_GEM_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FISH_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EYE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SECRET_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GEM_MAGMA_BLOCK);


    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.FISH_GEMS, Models.GENERATED);
        itemModelGenerator.register(ModItems.PROCESSED_FISH_GEMS, Models.GENERATED);
        itemModelGenerator.register(ModItems.FISH_GEM_DUST, Models.GENERATED);


    }
}
