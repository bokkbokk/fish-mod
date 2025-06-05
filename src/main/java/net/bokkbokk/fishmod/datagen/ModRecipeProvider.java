package net.bokkbokk.fishmod.datagen;

import net.bokkbokk.fishmod.block.ModBlocks;
import net.bokkbokk.fishmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;

import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        List<ItemConvertible> FISH_ITEMS_SMELT = List.of(ModItems.FISH_GEMS, ModBlocks.FISH_ORE_BLOCK);

        offerSmelting(recipeExporter, FISH_ITEMS_SMELT, RecipeCategory.MISC,ModItems.PROCESSED_FISH_GEMS,0.25f, 200,"fish_gems");
        offerBlasting(recipeExporter, FISH_ITEMS_SMELT, RecipeCategory.MISC,ModItems.PROCESSED_FISH_GEMS,0.25f, 100,"fish_gems");

        offerReversibleCompactingRecipes(recipeExporter,RecipeCategory.BUILDING_BLOCKS,ModItems.PROCESSED_FISH_GEMS, RecipeCategory.MISC,ModBlocks.FISH_GEM_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.EYE_BLOCK)
                .pattern("###")
                .pattern("#B#")
                .pattern("###")
                .input('#', ModItems.FISH_GEM_DUST)
                .input('B', Items.SLIME_BLOCK)
                .criterion(hasItem(ModItems.PROCESSED_FISH_GEMS), conditionsFromItem(ModItems.PROCESSED_FISH_GEMS))
                .offerTo(recipeExporter);

        offerStonecuttingRecipe(recipeExporter,RecipeCategory.MISC,ModItems.FISH_GEM_DUST,ModItems.PROCESSED_FISH_GEMS);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC,ModBlocks.GEM_MAGMA_BLOCK)
                .input(Items.MAGMA_BLOCK)
                .input(ModItems.FISH_GEM_DUST)
                .criterion(hasItem(ModItems.FISH_GEM_DUST), conditionsFromItem(ModItems.FISH_GEM_DUST))
                .offerTo(recipeExporter);

    }
}
