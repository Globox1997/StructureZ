package net.structurez;

import net.fabricmc.api.ModInitializer;

public class struc implements ModInitializer {

	public static String MOD_ID = "structurez";

	@Override
	public void onInitialize() {
		// Registry.BIOME.forEach(this::vspawn);

		featuring.setupFeatures();
		featuring.addStructures();
	}

	// public void vspawn(Biome biome) {
	// if (biome.getCategory() != Biome.Category.NETHER) {
	// biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION,
	// V_FEATURE.configure(FeatureConfig.DEFAULT)
	// .createDecoratedFeature(Decorator.COUNT_BIASED_RANGE.configure(new
	// RangeDecoratorConfig(300, 8, 8, 256))));
	// }
	// }

}

// You are LOVED!!!
// Jesus loves you unconditional!