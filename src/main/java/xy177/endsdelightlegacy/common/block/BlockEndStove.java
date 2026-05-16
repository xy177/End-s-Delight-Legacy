package xy177.endsdelightlegacy.common.block;

import com.wdcftgg.farmersdelightlegacy.common.block.BlockStove;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEndStove extends BlockStove
{
    public BlockEndStove()
    {
        super();
        this.setHarvestLevel("pickaxe", 0);
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return state.getValue(LIT) ? 13 : 0;
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        if (!entityIn.isSneaking()) {
            super.onEntityWalk(worldIn, pos, entityIn);
        }
    }
}
