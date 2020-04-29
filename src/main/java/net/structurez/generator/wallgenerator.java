package net.structurez.generator;

import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.*;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.structurez.featuring;
import net.structurez.struc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class wallgenerator {
  private static final Identifier WALL = new Identifier(struc.MOD_ID + ":stuff/wall");

  public static void addPieces(StructureManager manager, BlockPos pos, BlockRotation rotation,
      List<StructurePiece> pieces, Random random) {
    pieces.add(new wallgenerator.Piece(manager, WALL, pos, rotation));

  }

  public static class Piece extends SimpleStructurePiece {
    private final Identifier template;
    private BlockRotation rotation = BlockRotation.NONE;

    public Piece(StructureManager manager, Identifier identifier, BlockPos pos, BlockRotation rotation) {
      super(featuring.WALL_PIECES, 0);
      this.template = identifier;
      this.rotation = rotation;
      this.pos = pos;

      this.initializeStructureData(manager);
    }

    public Piece(StructureManager manager, CompoundTag tag) {
      super(featuring.WALL_PIECES, tag);
      this.template = new Identifier(tag.getString("Template"));
      this.rotation = BlockRotation.valueOf(tag.getString("Rot"));
      this.initializeStructureData(manager);
    }

    private void initializeStructureData(StructureManager manager) {
      Structure structure = manager.getStructureOrBlank(this.template);
      StructurePlacementData structurePlacementData = (new StructurePlacementData()).setRotation(this.rotation)
          .setMirrored(BlockMirror.NONE).setPosition(pos);
      // .addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);

      this.setStructureData(structure, this.pos, structurePlacementData);
    }

    @Override
    protected void toNbt(CompoundTag tag) {
      super.toNbt(tag);
      tag.putString("Template", this.template.toString());
      tag.putString("Rot", this.rotation.name());
    }

    @Override
    protected void handleMetadata(String metadata, BlockPos pos, IWorld world, Random random, BlockBox boundingBox) {
    }

    @Override
    public boolean generate(IWorld world, ChunkGenerator<?> generator, Random random, BlockBox box, ChunkPos pos) {
      this.placementData.addProcessor(BlockIgnoreStructureProcessor.IGNORE_AIR_AND_STRUCTURE_BLOCKS);
      BlockPos dirt = new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ());
      if ((world.getBlockState(dirt.up()).isAir() || world.getBlockState(dirt.up()).getBlock().equals(Blocks.GRASS)
          || world.getBlockState(dirt.up()).getBlock().equals(Blocks.TALL_GRASS))
          && (world.getBlockState(dirt.up().east(10)).isAir()
              || world.getBlockState(dirt.up().east(10)).getBlock().equals(Blocks.GRASS)
              || world.getBlockState(dirt.up().east(10)).getBlock().equals(Blocks.TALL_GRASS))
          && (world.getBlockState(dirt.up().south(13)).isAir()
              || world.getBlockState(dirt.up().south(13)).getBlock().equals(Blocks.GRASS)
              || world.getBlockState(dirt.up().south(13)).getBlock().equals(Blocks.TALL_GRASS))
          && (world.getBlockState(dirt.up().east(10).south(13)).isAir()
              || world.getBlockState(dirt.up().east(10).south(13)).getBlock().equals(Blocks.GRASS)
              || world.getBlockState(dirt.up().east(10).south(13)).getBlock().equals(Blocks.TALL_GRASS))

          && world.getBlockState(dirt).getBlock().equals(Blocks.GRASS_BLOCK)
          && world.getBlockState(dirt.east(10)).getBlock().equals(Blocks.GRASS_BLOCK)
          && world.getBlockState(dirt.south(13)).getBlock().equals(Blocks.GRASS_BLOCK)
          && (world.getBlockState(dirt.east(10).south(13)).getBlock().equals(Blocks.GRASS_BLOCK)
              || world.getBlockState(dirt.east(10).south(13)).getBlock().equals(Blocks.DIRT))

          && (world.getBlockState(dirt.up(10)).isAir()
              || world.getBlockState(dirt.up(10)).getBlock().equals(Blocks.OAK_LEAVES))
          && world.getBlockState(dirt.up(10).east(10).south(13)).isAir()) {

        boolean success = super.generate(world, generator, random, box, pos);
        return success;
      } else
        return false;
    }
  }
}