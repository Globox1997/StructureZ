package net.structurez.feature;

import net.minecraft.structure.PlainsVillageData;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;
import net.structurez.struc;

public class featuring {

        public static StructureFeature<DefaultFeatureConfig> STONESTATUE_STRUCTURE = Registry.register(
                        Registry.STRUCTURE_FEATURE, "stonestatue",
                        new villagerstatue(DefaultFeatureConfig::deserialize));
        public static StructurePieceType STONESTATUE_PIECES = Registry.register(Registry.STRUCTURE_PIECE,
                        "stonestatue_pieces", villagerstatuegenerator.Piece::new);

        public static StructureFeature<DefaultFeatureConfig> WATERVILLAGE_STRUCTURE = Registry.register(
                        Registry.STRUCTURE_FEATURE, "watervillagerstatue",
                        new villagerstatuewater(DefaultFeatureConfig::deserialize));
        public static StructurePieceType WATERVILLAGE_PIECES = Registry.register(Registry.STRUCTURE_PIECE,
                        "watervillagerstatue_pieces", villagerstatuewatergenerator.Piece::new);

        public static StructureFeature<DefaultFeatureConfig> DIRTHOUSE_STRUCTURE = Registry
                        .register(Registry.STRUCTURE_FEATURE, "dirt", new dirthouse(DefaultFeatureConfig::deserialize));
        public static StructurePieceType DIRTHOUSE_PIECES = Registry.register(Registry.STRUCTURE_PIECE, "dirt_pieces",
                        dirthousegenerator.Piece::new);

        public static StructureFeature<DefaultFeatureConfig> TREEHOUSE_STRUCTURE = Registry.register(
                        Registry.STRUCTURE_FEATURE, "treehouse", new treehouse(DefaultFeatureConfig::deserialize));
        public static StructurePieceType TREEHOUSE_PIECES = Registry.register(Registry.STRUCTURE_PIECE,
                        "treehouse_pieces", treehousegenerator.Piece::new);

        public static StructureFeature<DefaultFeatureConfig> TOWER_STRUCTURE = Registry.register(
                        Registry.STRUCTURE_FEATURE, "villagertower", new tower(DefaultFeatureConfig::deserialize));
        public static StructurePieceType TOWER_PIECES = Registry.register(Registry.STRUCTURE_PIECE,
                        "villagertower_pieces", towergenerator.Piece::new);

        public static StructureFeature<DefaultFeatureConfig> FALLENTOWER_STRUCTURE = Registry.register(
                        Registry.STRUCTURE_FEATURE, "fallentower", new fallentower(DefaultFeatureConfig::deserialize));
        public static StructurePieceType FALLENTOWER_PIECES = Registry.register(Registry.STRUCTURE_PIECE,
                        "fallentower_pieces", fallentowergenerator.Piece::new);

        public static StructureFeature<DefaultFeatureConfig> VILLAGERHOUSE_STRUCTURE = Registry.register(
                        Registry.STRUCTURE_FEATURE, "villagerhouse",
                        new villagerhouse(DefaultFeatureConfig::deserialize));
        public static StructurePieceType VILLAGERHOUSE_PIECES = Registry.register(Registry.STRUCTURE_PIECE,
                        "villagerhouse_pieces", villagerhousegenerator.Piece::new);

        public static StructureFeature<DefaultFeatureConfig> WALL_STRUCTURE = Registry
                        .register(Registry.STRUCTURE_FEATURE, "wall", new tower(DefaultFeatureConfig::deserialize));
        public static StructurePieceType WALL_PIECES = Registry.register(Registry.STRUCTURE_PIECE, "wall_pieces",
                        wallgenerator.Piece::new);

        public static StructureFeature<DefaultFeatureConfig> BATTLETOWER_STRUCTURE = Registry.register(
                        Registry.STRUCTURE_FEATURE, "battletower", new battletower(DefaultFeatureConfig::deserialize));
        public static StructurePieceType BATTLETOWER_PIECES = Registry.register(Registry.STRUCTURE_PIECE,
                        "battletower_pieces", battletowergenerator.Piece::new);

        public static void setupFeatures() {
                Feature.STRUCTURES.put(struc.MOD_ID + ":stonestatue", STONESTATUE_STRUCTURE);
                Feature.STRUCTURES.put(struc.MOD_ID + ":watervillagerstatue", WATERVILLAGE_STRUCTURE);
                Feature.STRUCTURES.put(struc.MOD_ID + ":dirt", DIRTHOUSE_STRUCTURE);
                Feature.STRUCTURES.put(struc.MOD_ID + ":villagertreehouse", TREEHOUSE_STRUCTURE);
                Feature.STRUCTURES.put(struc.MOD_ID + ":villagertower", TOWER_STRUCTURE);
                Feature.STRUCTURES.put(struc.MOD_ID + ":fallentower", FALLENTOWER_STRUCTURE);
                Feature.STRUCTURES.put(struc.MOD_ID + ":villagerhouse", VILLAGERHOUSE_STRUCTURE);
                Feature.STRUCTURES.put(struc.MOD_ID + ":wall", WALL_STRUCTURE);
                Feature.STRUCTURES.put(struc.MOD_ID + ":battletower", BATTLETOWER_STRUCTURE);

                PlainsVillageData.initialize();
        }

        public static void addStructures() {

                Registry.BIOME.forEach(biome -> biome
                                .addStructureFeature(featuring.STONESTATUE_STRUCTURE.configure(FeatureConfig.DEFAULT)));
                Registry.BIOME.forEach(biome -> biome.addStructureFeature(
                                featuring.WATERVILLAGE_STRUCTURE.configure(FeatureConfig.DEFAULT)));
                Registry.BIOME.forEach(biome -> biome
                                .addStructureFeature(featuring.DIRTHOUSE_STRUCTURE.configure(FeatureConfig.DEFAULT)));
                Registry.BIOME.forEach(biome -> biome
                                .addStructureFeature(featuring.TREEHOUSE_STRUCTURE.configure(FeatureConfig.DEFAULT)));

                Registry.BIOME.forEach(biome -> biome
                                .addStructureFeature(featuring.TOWER_STRUCTURE.configure(FeatureConfig.DEFAULT)));

                Registry.BIOME.forEach(biome -> biome
                                .addStructureFeature(featuring.FALLENTOWER_STRUCTURE.configure(FeatureConfig.DEFAULT)));

                Registry.BIOME.forEach(biome -> biome.addStructureFeature(
                                featuring.VILLAGERHOUSE_STRUCTURE.configure(FeatureConfig.DEFAULT)));

                Registry.BIOME.forEach(biome -> biome
                                .addStructureFeature(featuring.WALL_STRUCTURE.configure(FeatureConfig.DEFAULT)));

                Registry.BIOME.forEach(biome -> biome
                                .addStructureFeature(featuring.BATTLETOWER_STRUCTURE.configure(FeatureConfig.DEFAULT)));

                // Registry.BIOME.forEach(biome ->
                // biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
                // featuring.STONESTATUE_STRUCTURE.configure(FeatureConfig.DEFAULT)
                // .createDecoratedFeature(Decorator.NOPE.configure(DecoratorConfig.DEFAULT))));

                // Registry.BIOME.forEach(biome ->
                // biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
                // featuring.STONESTATUE_STRUCTURE.configure(FeatureConfig.DEFAULT)
                // .createDecoratedFeature(Decorator.COUNT_RANGE.configure(new
                // RangeDecoratorConfig(600, 10, 0, 128)))));

                Biomes.FOREST.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
                                featuring.STONESTATUE_STRUCTURE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(
                                                Decorator.CHANCE_PASSTHROUGH.configure(new ChanceDecoratorConfig(6))));
                Biomes.FLOWER_FOREST.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
                                featuring.STONESTATUE_STRUCTURE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(
                                                Decorator.CHANCE_PASSTHROUGH.configure(new ChanceDecoratorConfig(6))));

                Registry.BIOME.forEach(biome -> biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
                                featuring.WATERVILLAGE_STRUCTURE.configure(FeatureConfig.DEFAULT)
                                                .createDecoratedFeature(Decorator.CHANCE_PASSTHROUGH
                                                                .configure(new ChanceDecoratorConfig(6)))));
                // TEST
                Registry.BIOME.forEach(biome -> biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
                                featuring.TOWER_STRUCTURE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(
                                                Decorator.CHANCE_PASSTHROUGH.configure(new ChanceDecoratorConfig(0)))));

                Biomes.PLAINS.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
                                featuring.DIRTHOUSE_STRUCTURE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(
                                                Decorator.CHANCE_PASSTHROUGH.configure(new ChanceDecoratorConfig(6))));

                Biomes.SNOWY_TUNDRA.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
                                featuring.TREEHOUSE_STRUCTURE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(
                                                Decorator.CHANCE_PASSTHROUGH.configure(new ChanceDecoratorConfig(5))));

                Biomes.MOUNTAINS.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
                                featuring.TOWER_STRUCTURE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(
                                                Decorator.CHANCE_PASSTHROUGH.configure(new ChanceDecoratorConfig(6))));
                Biomes.GRAVELLY_MOUNTAINS.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
                                featuring.TOWER_STRUCTURE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(
                                                Decorator.CHANCE_PASSTHROUGH.configure(new ChanceDecoratorConfig(6))));

                Biomes.PLAINS.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
                                featuring.FALLENTOWER_STRUCTURE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(
                                                Decorator.CHANCE_PASSTHROUGH.configure(new ChanceDecoratorConfig(6))));

                Biomes.TAIGA.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, featuring.VILLAGERHOUSE_STRUCTURE
                                .configure(FeatureConfig.DEFAULT).createDecoratedFeature(
                                                Decorator.CHANCE_PASSTHROUGH.configure(new ChanceDecoratorConfig(6))));

                Biomes.SAVANNA.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
                                featuring.WALL_STRUCTURE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(
                                                Decorator.CHANCE_PASSTHROUGH.configure(new ChanceDecoratorConfig(6))));
                Biomes.SAVANNA_PLATEAU.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
                                featuring.WALL_STRUCTURE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(
                                                Decorator.CHANCE_PASSTHROUGH.configure(new ChanceDecoratorConfig(6))));

                Registry.BIOME.forEach(biome -> biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
                                featuring.BATTLETOWER_STRUCTURE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(
                                                Decorator.CHANCE_PASSTHROUGH.configure(new ChanceDecoratorConfig(8)))));

        }

}