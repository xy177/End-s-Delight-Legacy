package xy177.endsdelightlegacy.common.world;

import java.util.Random;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import xy177.endsdelightlegacy.common.block.BlockChorusSucculent;
import xy177.endsdelightlegacy.common.registry.EDBlocks;

public class EDWorldGenerator implements IWorldGenerator
{
    private static final int CHORUS_SUCCULENT_RARITY = 8;
    private static final int CHORUS_SUCCULENT_ATTEMPTS = 20;
    private static final int OUTER_END_DISTANCE = 1024;

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        if (world.provider.getDimensionType() != DimensionType.THE_END
            || random.nextInt(CHORUS_SUCCULENT_RARITY) != 0
            || isMainIslandChunk(chunkX, chunkZ)) {
            return;
        }

        generateChorusSucculents(random, chunkX, chunkZ, world);
    }

    private static void generateChorusSucculents(Random random, int chunkX, int chunkZ, World world)
    {
        for (int i = 0; i < CHORUS_SUCCULENT_ATTEMPTS; i++) {
            int x = chunkX * 16 + random.nextInt(16);
            int z = chunkZ * 16 + random.nextInt(16);
            BlockPos pos = world.getHeight(new BlockPos(x, 0, z));

            if (pos.getY() <= 0
                || world.getBiome(pos) != Biomes.SKY
                || !world.isAirBlock(pos)
                || world.getBlockState(pos.down()).getBlock() != Blocks.END_STONE
                || !EDBlocks.CHORUS_SUCCULENT.canPlaceBlockAt(world, pos)) {
                continue;
            }

            world.setBlockState(pos, EDBlocks.CHORUS_SUCCULENT.getDefaultState()
                .withProperty(BlockChorusSucculent.SUCCULENT, random.nextInt(3) + 1), 2);
        }
    }

    private static boolean isMainIslandChunk(int chunkX, int chunkZ)
    {
        int centerX = chunkX * 16 + 8;
        int centerZ = chunkZ * 16 + 8;
        return centerX * centerX + centerZ * centerZ < OUTER_END_DISTANCE * OUTER_END_DISTANCE;
    }
}
