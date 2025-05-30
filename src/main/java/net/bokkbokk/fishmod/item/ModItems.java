package net.bokkbokk.fishmod.item;

import net.bokkbokk.fishmod.FishMod;
import net.bokkbokk.fishmod.item.custom.FishGemDustItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModItems {


    public static final Item PROCESSED_FISH_GEMS = registerItem("processed_fish_gems", new Item(new Item.Settings()));

    public static final Item FISH_GEMS = registerItem("fish_gems", new Item(new Item.Settings()));

    public static final Item FISH_GEM_DUST = registerItem("fish_gem_dust", new FishGemDustItem(new Item.Settings()));




    //putting dim in here for whatever reason  ------ nvm

    //public static final World FISH_DIM = Registry.register(Registries.)

    //eeeeee





    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of( FishMod.MOD_ID, name ), item);
    }

    public static void registerModItems() {
        FishMod.LOGGER.info("Registering mod items for " + FishMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(PROCESSED_FISH_GEMS);
            fabricItemGroupEntries.add(FISH_GEMS);
        });
    }
}
