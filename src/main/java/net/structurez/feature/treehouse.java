package net.structurez.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.AbstractTempleFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.structurez.struc;
import net.structurez.generator.treehousegenerator;

import java.util.Random;
import java.util.function.Function;

public class treehouse extends AbstractTempleFeature<DefaultFeatureConfig> {
  public treehouse(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configFactory) {
    super(configFactory);
  }

  @Override
  public boolean shouldStartAt(BiomeAccess biomeAccess, ChunkGenerator<?> chunkGenerator, Random random, int chunkZ,
      int i, Biome biome) {
    if (!chunkGenerator.hasStructure(biome, this)) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public StructureStartFactory getStructureStartFactory() {
    return treehouse.Start::new;
  }

  @Override
  public String getName() {
    return struc.MOD_ID + ":treehouse";
  }

  @Override
  public int getRadius() {
    return 6;
  }

  public static class Start extends StructureStart {
    public Start(StructureFeature<?> structureFeature, int chunkX, int chunkZ, BlockBox blockBox, int references,
        long seed) {
      super(structureFeature, chunkX, chunkZ, blockBox, references, seed);
    }

    @Override
    public void initialize(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, int x, int z,
        Biome biome) {
      BlockPos startingPos = new BlockPos(x * 16,
          chunkGenerator.getHeightOnGround(x * 16 + 15, z * 16 + 15, Heightmap.Type.WORLD_SURFACE), z * 16);
      BlockRotation rotation = BlockRotation.NONE;

      treehousegenerator.addPieces(structureManager, startingPos, rotation, this.children, this.random);
      this.setBoundingBoxFromChildren();
    }
  }

  @Override
  protected int getSeedModifier() {
    return 14357618;
  }
}