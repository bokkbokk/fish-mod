package net.bokkbokk.fishmod.world;

import net.bokkbokk.fishmod.FishMod;
import net.bokkbokk.fishmod.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public class ModConfiguredFeatures {
    // CF -> PF -> WG/BM
    // what  how  where
    public static final RegistryKey<ConfiguredFeature<?, ?>> FISH_ORE_BLOCK_KEY = registerKey("fish_ore_block");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        //
        RuleTest sandReplaceables = new BlockMatchRuleTest(Blocks.SAND);

        List<OreFeatureConfig.Target> fishdimFishOres =
                List.of(OreFeatureConfig.createTarget(sandReplaceables, ModBlocks.FISH_ORE_BLOCK.getDefaultState()));

        register(context, FISH_ORE_BLOCK_KEY, Feature.ORE, new OreFeatureConfig(fishdimFishOres, 7));

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(FishMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
