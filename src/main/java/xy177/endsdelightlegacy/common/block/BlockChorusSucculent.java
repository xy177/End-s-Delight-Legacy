package xy177.endsdelightlegacy.common.block;

import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockChorusSucculent extends BlockBush
{
    public static final PropertyInteger SUCCULENT = PropertyInteger.create("succulent", 1, 3);
    private static final AxisAlignedBB ONE_SHAPE = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.375D, 0.625D);
    private static final AxisAlignedBB TWO_SHAPE = new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.375D, 0.8125D);
    private static final AxisAlignedBB THREE_SHAPE = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.375D, 0.875D);

    public BlockChorusSucculent()
    {
        super(Material.PLANTS);
        this.setHardness(0.0F);
        this.setResistance(0.0F);
        this.setLightLevel(0.1875F);
        this.setSoundType(SoundType.PLANT);
        this.setDefaultState(this.blockState.getBaseState().withProperty(SUCCULENT, 1));
    }

    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.END_STONE || super.canSustainBush(state);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        if (this.getCreativeTabToDisplayOn() == itemIn) {
            Item item = Item.getItemFromBlock(this);
            if (item != null) {
                items.add(new ItemStack(item));
            }
        }
    }

    @Override
    public boolean canPlaceBlockAt(net.minecraft.world.World worldIn, BlockPos pos)
    {
        return super.canPlaceBlockAt(worldIn, pos) && canSustainBush(worldIn.getBlockState(pos.down()));
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
                                            float hitZ, int meta, net.minecraft.entity.EntityLivingBase placer,
                                            EnumHand hand)
    {
        return this.getDefaultState().withProperty(SUCCULENT, 1);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch (state.getValue(SUCCULENT)) {
            case 2:
                return TWO_SHAPE;
            case 3:
                return THREE_SHAPE;
            case 1:
            default:
                return ONE_SHAPE;
        }
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(SUCCULENT) - 1;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(SUCCULENT, Math.max(1, Math.min(3, meta + 1)));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[]{SUCCULENT});
    }
}
