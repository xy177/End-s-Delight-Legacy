package xy177.endsdelightlegacy.common.block;

import com.wdcftgg.farmersdelightlegacy.common.block.BlockPie;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xy177.endsdelightlegacy.common.item.ItemChorusFruitTeleportFood;
import xy177.endsdelightlegacy.common.registry.EDItems;

public class BlockChorusFruitPie extends BlockPie
{
    public BlockChorusFruitPie()
    {
        super("chorus_fruit_pie_slice");
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
                                    EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote) {
            return true;
        }

        ItemStack sliceStack = new ItemStack(EDItems.CHORUS_FRUIT_PIE_SLICE);
        if (!player.inventory.addItemStackToInventory(sliceStack)) {
            player.dropItem(sliceStack, false);
        }

        int bites = state.getValue(BITES);
        if (bites >= 3) {
            world.setBlockToAir(pos);
        } else {
            world.setBlockState(pos, state.withProperty(BITES, bites + 1), 3);
        }

        world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.25F, 1.0F);
        if (player.isSneaking()) {
            ItemChorusFruitTeleportFood.teleportLikeChorusFruit(world, player);
        }
        return true;
    }
}
