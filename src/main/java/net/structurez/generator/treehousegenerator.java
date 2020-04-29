package net.structurez.generator;

import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
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

public class treehousegenerator {
  private static final Identifier TOWER = new Identifier(struc.MOD_ID + ":houses/treehouse");
  private static final Identifier towerloot = new Identifier("structurez:treehouse_loot");

  public static void addPieces(StructureManager manager, BlockPos pos, BlockRotation rotation,
      List<StructurePiece> pieces, Random random) {
    pieces.add(new treehousegenerator.Piece(manager, TOWER, pos, rotation));

  }

  public static class Piece extends SimpleStructurePiece {
    private final Identifier template;
    private final BlockRotation rotation;

    public Piece(StructureManager manager, Identifier identifier, BlockPos pos, BlockRotation rotation) {
      super(featuring.TREEHOUSE_PIECES, 0);
      this.template = identifier;
      this.rotation = rotation;
      this.pos = pos;

      this.initializeStructureData(manager);
    }

    public Piece(StructureManager manager, CompoundTag tag) {
      super(featuring.TREEHOUSE_PIECES, tag);
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
      BlockPos grass = new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ());
      if ((world.getBlockState(grass.up()).isAir() || world.getBlockState(grass.up()).getBlock().equals(Blocks.SNOW)
          || world.getBlockState(grass.up()).getBlock().equals(Blocks.GRASS))
          && (world.getBlockState(grass.up().east(14)).isAir()
              || world.getBlockState(grass.up().east(14)).getBlock().equals(Blocks.SNOW)
              || world.getBlockState(grass.up().east(14)).getBlock().equals(Blocks.GRASS))
          && (world.getBlockState(grass.up().south(14)).isAir()
              || world.getBlockState(grass.up().south(14)).getBlock().equals(Blocks.SNOW)
              || world.getBlockState(grass.up().south(14)).getBlock().equals(Blocks.GRASS))
          && (world.getBlockState(grass.up().east(14).south(14)).isAir()
              || world.getBlockState(grass.up().east(14).south(14)).getBlock().equals(Blocks.SNOW)
              || world.getBlockState(grass.up().east(14).south(14)).getBlock().equals(Blocks.GRASS))

          && world.getBlockState(grass).getBlock().equals(Blocks.GRASS_BLOCK)
          && world.getBlockState(grass.east(14)).getBlock().equals(Blocks.GRASS_BLOCK)
          && world.getBlockState(grass.south(14)).getBlock().equals(Blocks.GRASS_BLOCK)
          && (world.getBlockState(grass.east(14).south(14)).getBlock().equals(Blocks.GRASS_BLOCK)
              || world.getBlockState(grass.east(14).south(14)).getBlock().equals(Blocks.DIRT))

          && world.getBlockState(grass.up(12)).isAir()
          && world.getBlockState(grass.up(12).east(14).south(14)).isAir()) {
        boolean success = super.generate(world, generator, random, box, pos);
        return success;
      } else
        return false;
    }
  }
}
