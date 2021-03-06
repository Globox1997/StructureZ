package net.structurez.generator;

import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.*;
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

import java.util.List;
import java.util.Random;

public class battletowergenerator {
  private static final Identifier TOWER = new Identifier(struc.MOD_ID + ":stuff/battletower");
  private static final Identifier towerloot = new Identifier("structurez:battletower_loot");

  public static void addPieces(StructureManager manager, BlockPos pos, BlockRotation rotation,
      List<StructurePiece> pieces, Random random) {
    pieces.add(new battletowergenerator.Piece(manager, TOWER, pos, rotation));

  }

  public static class Piece extends SimpleStructurePiece {
    private final Identifier template;
    private final BlockRotation rotation;

    public Piece(StructureManager manager, Identifier identifier, BlockPos pos, BlockRotation rotation) {
      super(featuring.BATTLETOWER_PIECES, 0);
      this.template = identifier;
      this.rotation = rotation;
      this.pos = pos;

      this.initializeStructureData(manager);
    }

    public Piece(StructureManager manager, CompoundTag tag) {
      super(featuring.BATTLETOWER_PIECES, tag);
      this.template = new Identifier(tag.getString("Template"));
      this.rotation = BlockRotation.valueOf(tag.getString("Rot"));
      this.initializeStructureData(manager);
    }

    private void initializeStructureData(StructureManager manager) {
      Structure structure = manager.getStructureOrBlank(this.template);
      StructurePlacementData structurePlacementData = (new StructurePlacementData()).setRotation(this.rotation)
          .setMirrored(BlockMirror.NONE).setPosition(pos);

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

      if (metadata.contains("battletower_loot")) {
        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        BlockEntity blockEntity = world.getBlockEntity(pos.down());

        if (blockEntity instanceof ChestBlockEntity) {
          ((ChestBlockEntity) blockEntity).setLootTable(towerloot, random.nextLong());
        }
      }
    }

    @Override
    public boolean generate(IWorld world, ChunkGenerator<?> generator, Random random, BlockBox box, ChunkPos pos) {
      BlockPos waterblock = new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ());
      if ((world.getBlockState(waterblock.up()).getBlock().equals(Blocks.WATER)
          || world.getBlockState(waterblock.up()).getBlock().equals(Blocks.SEAGRASS)
          || world.getBlockState(waterblock.up()).getBlock().equals(Blocks.GRAVEL)
          || world.getBlockState(waterblock.up()).getBlock().equals(Blocks.KELP)
          || world.getBlockState(waterblock.up()).getBlock().equals(Blocks.KELP_PLANT))
          && (world.getBlockState(waterblock.south(11).up()).getBlock().equals(Blocks.WATER)
              || world.getBlockState(waterblock.south(11).up()).getBlock().equals(Blocks.SEAGRASS)
              || world.getBlockState(waterblock.south(11).up()).getBlock().equals(Blocks.GRAVEL)
              || world.getBlockState(waterblock.south(11).up()).getBlock().equals(Blocks.KELP)
              || world.getBlockState(waterblock.south(11).up()).getBlock().equals(Blocks.KELP_PLANT))
          && (world.getBlockState(waterblock.east(11).up()).getBlock().equals(Blocks.WATER)
              || world.getBlockState(waterblock.east(11).up()).getBlock().equals(Blocks.SEAGRASS)
              || world.getBlockState(waterblock.east(11).up()).getBlock().equals(Blocks.GRAVEL)
              || world.getBlockState(waterblock.east(11).up()).getBlock().equals(Blocks.KELP)
              || world.getBlockState(waterblock.east(11).up()).getBlock().equals(Blocks.KELP_PLANT))
          && (world.getBlockState(waterblock.south(11).east(11).up()).getBlock().equals(Blocks.WATER)
              || world.getBlockState(waterblock.south(11).east(11).up()).getBlock().equals(Blocks.SEAGRASS)
              || world.getBlockState(waterblock.south(11).east(11).up()).getBlock().equals(Blocks.GRAVEL)
              || world.getBlockState(waterblock.south(11).east(11).up()).getBlock().equals(Blocks.KELP)
              || world.getBlockState(waterblock.south(11).east(11).up()).getBlock().equals(Blocks.KELP_PLANT))

          && (world.getBlockState(waterblock.down()).getBlock().equals(Blocks.GRAVEL)
              || world.getBlockState(waterblock.down()).getBlock().equals(Blocks.STONE))
          && (world.getBlockState(waterblock.down().south(11)).getBlock().equals(Blocks.GRAVEL)
              || world.getBlockState(waterblock.down().south(11)).getBlock().equals(Blocks.STONE))
          && world.getBlockState(waterblock.down().east(11)).getBlock().equals(Blocks.GRAVEL)
          && world.getBlockState(waterblock.down().south(11).east(11)).getBlock().equals(Blocks.GRAVEL)

          && world.getBlockState(waterblock.up(30)).getBlock().equals(Blocks.WATER)) {
        boolean success = super.generate(world, generator, random, box, pos);
        return success;
      } else
        return false;
    }
  }
}
