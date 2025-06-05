package net.bokkbokk.fishmod.entity;

import net.bokkbokk.fishmod.FishMod;
import net.bokkbokk.fishmod.entity.custom.CrawlerSmallEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<CrawlerSmallEntity> CRAWLER_SMALL = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(FishMod.MOD_ID,"crawler_small"),
            EntityType.Builder.create(CrawlerSmallEntity::new, SpawnGroup.MONSTER)
                    .dimensions(.8f,.5f)
                    .makeFireImmune()


                    .build());


    public static void registerModEntities() {
        FishMod.LOGGER.info("Registering Mod Entities for " + FishMod.MOD_ID);

    }
}
