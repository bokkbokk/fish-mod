package net.bokkbokk.fishmod.item;

import net.bokkbokk.fishmod.FishMod;
import net.bokkbokk.fishmod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {


    public static final ItemGroup FISH_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(FishMod.MOD_ID, "fish_items_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.FISH_GEMS))
                    .displayName(Text.translatable("itemGroup.fishmod.fish_item_group"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.FISH_GEMS);
                        entries.add(ModItems.PROCESSED_FISH_GEMS);
                        entries.add(ModItems.FISH_GEM_DUST);

                    })

                    .build());

    public static final ItemGroup FISH_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(FishMod.MOD_ID, "fish_blocks_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.FISH_GEM_BLOCK))
                    .displayName(Text.translatable("itemGroup.fishmod.fish_blocks_group"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.FISH_GEM_BLOCK);
                        entries.add(ModBlocks.FISH_ORE_BLOCK);
                        entries.add(ModBlocks.EYE_BLOCK);

                    })

                    .build());





    public static void registerItemGroups() {
        FishMod.LOGGER.info("Registering Item Groups for " + FishMod.MOD_ID);
    }
}
