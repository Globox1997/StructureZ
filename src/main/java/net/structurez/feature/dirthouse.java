package net.structurez.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.structurez.struc;
import net.structurez.generator.dirthousegenerator;

import java.util.Random;
import java.util.function.Function;

public class dirthouse extends StructureFeature<DefaultFeatureConfig> {
  public dirthouse(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configFactory) {
    super(configFactory);
  }

  @Override
  public boolean shouldStartAt(BiomeAccess biomeAccess, ChunkGenerator<?> chunkGenerator, Random random, int chunkX,
      int chunkZ, Biome biome) {
    ChunkPos chunkPos = this.getStart(chunkGenerator, random, chunkX, chunkZ, 0, 0);
    return chunkX == chunkPos.x && chunkZ == chunkPos.z ? chunkGenerator.hasStructure(biome, this) : false;
  }

  @Override
  public StructureStartFactory getStructureStartFactory() {
    return dirthouse.Start::new;
  }

  @Override
  public String getName() {
    return struc.MOD_ID + ":dirt";
  }

  @Override
  public int getRadius() {
    return 8;
  }

  public static class Start extends StructureStart {
    public Start(StructureFeature<?> structureFeature, int chunkX, int chunkZ, BlockBox blockBox, int references,
        long seed) {
      super(structureFeature, chunkX, chunkZ, blockBox, references, seed);
    }

    @Override
    public void initialize(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, int x, int z,
        Biome biome) {
      BlockPos blockPos = new BlockPos(x * 16,
          chunkGenerator.getHeightOnGround(x * 16 + 15, z * 16 + 15, Heightmap.Type.WORLD_SURFACE), z * 16);
      BlockRotation blockRotation = BlockRotation.NONE;
      dirthousegenerator.addPieces(structureManager, blockPos, blockRotation, this.children, this.random);
      this.setBoundingBoxFromChildren();
    }
  }
}