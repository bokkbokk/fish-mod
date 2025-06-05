package net.bokkbokk.fishmod.sound;

import net.bokkbokk.fishmod.FishMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent FISH_DUST_USE = registerSoundEvent("fish_dust_use");
    public static final SoundEvent FISH_PORTAL_LIGHT = registerSoundEvent("fish_portal_light");
    public static final SoundEvent FISH_CURSE = registerSoundEvent("fish_curse");
    public static final SoundEvent DIM_ENTRY = registerSoundEvent("dim_entry");
    public static final SoundEvent FISH_DIM_LOOP = registerSoundEvent("fish_dim_loop"); //used in fish_biome.json
    public static final SoundEvent CRAWLER_SMALL_HIT = registerSoundEvent("crawler_small_hit");


    private static SoundEvent registerSoundEvent(String name){
        Identifier id = Identifier.of(FishMod.MOD_ID,name);
        return Registry.register(Registries.SOUND_EVENT,id,SoundEvent.of(id));
    }

    public static void registerSounds() {
        FishMod.LOGGER.info("Registering Mod Sounds for " + FishMod.MOD_ID);
    }
}
