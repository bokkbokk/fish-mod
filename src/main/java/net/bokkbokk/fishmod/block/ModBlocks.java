package net.bokkbokk.fishmod.block;

import net.bokkbokk.fishmod.FishMod;
import net.bokkbokk.fishmod.block.custom.FishPortalBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ColorCode;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block FISH_GEM_BLOCK = registerBlock("fish_gem_block",
            new Block(AbstractBlock.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

//    public static final Block EYE_BLOCK = registerBlock("eye_block",
//            new Block(AbstractBlock.Settings.create().strength(1f)
//                    .requiresTool().sounds(BlockSoundGroup.HONEY)));

    public static final Block FISH_ORE_BLOCK = registerBlock("fish_ore_block",
            new ColoredFallingBlock(
                    new ColorCode(14406560),
                    AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)
            )
    );

    public static final Block EYE_BLOCK = registerBlock("eye_block",
            new ColoredFallingBlock(
                    new ColorCode(14406560),
                    AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.HONEY)
            )
    );

//    public static final Block FISH_PORTAL_BLOCK = registerBlock("fish_portal_block",
//            new FishPortalBlock(AbstractBlock.Settings.create().strength(99f)
//                    .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK).noCollision()));


    public static final Block FISH_PORTAL_BLOCK = registerBlock(
            "fish_portal_block",
            new FishPortalBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.WATER_BLUE)
                            .noCollision()
                            .luminance(state -> 15)
                            .strength(-1.0F, 3600000.0F)
                            .dropsNothing()
                            .pistonBehavior(PistonBehavior.BLOCK)
            )
    );








    private static Block registerBlock(String name, Block block){
        registerBlockItem(name,block);
        return Registry.register(Registries.BLOCK, Identifier.of(FishMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block){
        Registry.register(Registries.ITEM, Identifier.of(FishMod.MOD_ID,name),
                new BlockItem(block, new Item.Settings()));
    }


    public static void registerModBlocks() {
        // This method will be used to register blocks in the future
        // Currently, it does nothing but can be expanded later
        FishMod.LOGGER.info("Registering mod blocks for " + FishMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
                    fabricItemGroupEntries.add(FISH_GEM_BLOCK);
                });

    }
}
