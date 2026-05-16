package xy177.endsdelightlegacy.common.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xy177.endsdelightlegacy.common.block.BlockDragonLegWithSauce;

public class ItemBlockDragonLegWithSauce extends ItemBlock
{
    private final BlockDragonLegWithSauce feastBlock;

    public ItemBlockDragonLegWithSauce(Block block)
    {
        super(block);
        this.feastBlock = (BlockDragonLegWithSauce) block;
        setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand,
                                      EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack stack = player.getHeldItem(hand);
        BlockPos placePos = world.getBlockState(pos).getBlock().isReplaceable(world, pos) ? pos : pos.offset(facing);
        EnumFacing blockFacing = player.getHorizontalFacing();
        if (!player.canPlayerEdit(placePos, facing, stack)
            || !player.canPlayerEdit(placePos.offset(blockFacing), facing, stack)
            || !this.feastBlock.canPlaceFeastAt(world, placePos, blockFacing)) {
            return EnumActionResult.FAIL;
        }

        if (!world.isRemote) {
            this.feastBlock.placeFeast(world, placePos, blockFacing);
            if (!player.capabilities.isCreativeMode) {
                stack.shrink(1);
            }
        }
        return EnumActionResult.SUCCESS;
    }
}
