/*
 * package net.structurez.feature;
 * 
 * import net.minecraft.structure.StructureManager; import
 * net.minecraft.structure.StructureStart; import
 * net.minecraft.util.BlockRotation; import net.minecraft.util.math.BlockPos;
 * import net.minecraft.world.biome.Biome; import
 * net.minecraft.world.gen.chunk.ChunkGenerator; import
 * net.minecraft.world.gen.feature.AbstractTempleFeature; import
 * net.minecraft.world.gen.feature.DefaultFeatureConfig; import
 * net.minecraft.world.gen.feature.StructureFeature;
 * 
 * import java.util.Random;
 * 
 * public class test extends AbstractTempleFeature<DefaultFeatureConfig> {
 * 
 * public test() { super(DefaultFeatureConfig::deserialize); }
 * 
 * @Override protected int getSeedModifier() { return 14357618; }
 * 
 * @Override public boolean shouldStartAt(ChunkGenerator<?> chunkGenerator,
 * Random random, int x, int z) { return true; }
 * 
 * @Override public StructureStartFactory getStructureStartFactory() { return
 * teststart::new; }
 * 
 * @Override public String getName() { return "battle-tower"; }
 * 
 * @Override public int getRadius() { return 8; }
 * 
 * public static class teststart extends StructureStart {
 * 
 * public teststart(StructureFeature<?> structureFeature, int int_1, int int_2,
 * Biome biome, MutableIntBoundingBox box, int int_3, long long_1) {
 * super(structureFeature, int_1, int_2, biome, box, int_3, long_1); }
 * 
 * @Override public void initialize(ChunkGenerator<?> chunkGenerator,
 * StructureManager structureManager, int chunkX, int chunkZ, Biome biome) {
 * DefaultFeatureConfig defaultFeatureConfig =
 * chunkGenerator.getStructureConfig(biome, Structures.test);
 * 
 * int x = chunkX * 16; int z = chunkZ * 16;
 * 
 * BlockPos startingPos = new BlockPos(x, 0, z);
 * 
 * // randomized rotation breaks a LOT of stuff so we're removing it for now
 * BlockRotation rotation = BlockRotation.NONE;
 * 
 * BattleTowerGenerator.addParts(structureManager, startingPos, rotation,
 * this.children, this.random, defaultFeatureConfig);
 * this.setBoundingBoxFromChildren(); } } }
 */