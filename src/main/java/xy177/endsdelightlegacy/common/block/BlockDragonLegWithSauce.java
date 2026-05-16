package xy177.endsdelightlegacy.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xy177.endsdelightlegacy.common.registry.EDItems;
import xy177.endsdelightlegacy.common.tile.TileEntityDragonLegFeast;

public class BlockDragonLegWithSauce extends Block implements ITileEntityProvider
{
    public static final int MAX_SERVINGS = 6;
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final PropertyInteger SERVINGS = PropertyInteger.create("servings", 0, MAX_SERVINGS);
    public static final PropertyEnum<Part> PART = PropertyEnum.create("part", Part.class);

    public BlockDragonLegWithSauce()
    {
        super(Material.CAKE);
        setHardness(0.5F);
        setResistance(0.5F);
        setSoundType(SoundType.WOOD);
        setDefaultState(this.blockState.getBaseState()
            .withProperty(FACING, EnumFacing.NORTH)
            .withProperty(SERVINGS, MAX_SERVINGS)
            .withProperty(PART, Part.HEAD));
    }

    public static int clampServings(int servings)
    {
        return Math.max(0, Math.min(MAX_SERVINGS, servings));
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityDragonLegFeast();
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
                                            float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }

    public boolean canPlaceFeastAt(World world, BlockPos headPos, EnumFacing facing)
    {
        BlockPos footPos = headPos.offset(facing);
        return canReplace(world, headPos) && canReplace(world, footPos)
            && world.getBlockState(headPos.down()).isSideSolid(world, headPos.down(), EnumFacing.UP)
            && world.getBlockState(footPos.down()).isSideSolid(world, footPos.down(), EnumFacing.UP);
    }

    private boolean canReplace(World world, BlockPos pos)
    {
        return world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isReplaceable(world, pos);
    }

    public void placeFeast(World world, BlockPos headPos, EnumFacing facing)
    {
        IBlockState headState = getDefaultState().withProperty(FACING, facing).withProperty(PART, Part.HEAD);
        IBlockState footState = getDefaultState().withProperty(FACING, facing).withProperty(PART, Part.FOOT);
        world.setBlockState(headPos, headState, 3);
        world.setBlockState(headPos.offset(facing), footState, 3);
        setServings(world, headPos, MAX_SERVINGS);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
                                    EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (hand != EnumHand.MAIN_HAND) {
            return true;
        }

        BlockPos headPos = getHeadPos(pos, state);
        int servings = getServings(world, headPos);
        if (servings <= 0) {
            if (!world.isRemote) {
                world.playSound(null, pos, SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.PLAYERS, 1.0F, 1.0F);
                clearFeast(world, headPos, state.getValue(FACING), false);
                spawnAsEntity(world, pos, new ItemStack(Items.DYE, 3, 15));
            }
            return true;
        }

        ItemStack heldStack = player.getHeldItem(hand);
        if (heldStack.isEmpty() || heldStack.getItem() != Items.BOWL) {
            if (!world.isRemote) {
                player.sendStatusMessage(new TextComponentTranslation("farmersdelight.block.feast.use_container", new ItemStack(Items.BOWL).getDisplayName()), true);
            }
            return true;
        }

        if (!world.isRemote) {
            if (!player.capabilities.isCreativeMode) {
                heldStack.shrink(1);
            }
            ItemStack serving = new ItemStack(EDItems.DRAGON_LEG_WITH_SAUCE);
            if (!player.inventory.addItemStackToInventory(serving)) {
                player.dropItem(serving, false);
            }
            int newServings = clampServings(servings - 1);
            setServings(world, headPos, newServings);
            world.playSound(null, pos, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
        return true;
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        EnumFacing facing = state.getValue(FACING);
        BlockPos otherPos = state.getValue(PART) == Part.HEAD ? pos.offset(facing) : pos.offset(facing.getOpposite());
        if (world.getBlockState(otherPos).getBlock() != this
            || !world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP)) {
            world.setBlockToAir(pos);
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        if (!world.isRemote) {
            EnumFacing facing = state.getValue(FACING);
            BlockPos otherPos = state.getValue(PART) == Part.HEAD ? pos.offset(facing) : pos.offset(facing.getOpposite());
            IBlockState otherState = world.getBlockState(otherPos);
            if (otherState.getBlock() == this && otherState.getValue(PART) != state.getValue(PART)) {
                world.setBlockToAir(otherPos);
            }
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return state.withProperty(SERVINGS, getRenderServings(world, pos, state));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(FACING).getHorizontalIndex() | (state.getValue(PART) == Part.FOOT ? 4 : 0);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState()
            .withProperty(FACING, EnumFacing.getHorizontal(meta & 3))
            .withProperty(SERVINGS, MAX_SERVINGS)
            .withProperty(PART, (meta & 4) == 4 ? Part.FOOT : Part.HEAD);
    }

    public int getRenderServings(IBlockAccess world, BlockPos pos, IBlockState state)
    {
        return getServings(world, getHeadPos(pos, state));
    }

    private int getServings(IBlockAccess world, BlockPos headPos)
    {
        TileEntity tileEntity = world.getTileEntity(headPos);
        if (tileEntity instanceof TileEntityDragonLegFeast) {
            return clampServings(((TileEntityDragonLegFeast) tileEntity).getServings());
        }
        IBlockState state = world.getBlockState(headPos);
        if (state.getBlock() == this) {
            return clampServings(state.getValue(SERVINGS));
        }
        return MAX_SERVINGS;
    }

    private void setServings(World world, BlockPos headPos, int servings)
    {
        int clamped = clampServings(servings);
        IBlockState headState = world.getBlockState(headPos);
        if (headState.getBlock() != this) {
            return;
        }
        EnumFacing facing = headState.getValue(FACING);
        BlockPos footPos = headPos.offset(facing);
        world.setBlockState(headPos, headState.withProperty(SERVINGS, clamped), 3);
        setTileServings(world, headPos, clamped);

        IBlockState footState = world.getBlockState(footPos);
        if (footState.getBlock() == this) {
            world.setBlockState(footPos, footState.withProperty(SERVINGS, clamped), 3);
            setTileServings(world, footPos, clamped);
        }
    }

    private void setTileServings(World world, BlockPos pos, int servings)
    {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof TileEntityDragonLegFeast) {
            ((TileEntityDragonLegFeast) tileEntity).setServings(servings);
        }
    }

    private BlockPos getHeadPos(BlockPos pos, IBlockState state)
    {
        return state.getValue(PART) == Part.HEAD ? pos : pos.offset(state.getValue(FACING).getOpposite());
    }

    private void clearFeast(World world, BlockPos headPos, EnumFacing facing, boolean dropFull)
    {
        BlockPos footPos = headPos.offset(facing);
        world.setBlockToAir(footPos);
        world.setBlockToAir(headPos);
        if (dropFull) {
            spawnAsEntity(world, headPos, new ItemStack(Item.getItemFromBlock(this)));
        }
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        int servings = clampServings(state.getValue(SERVINGS));
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof TileEntityDragonLegFeast) {
            servings = clampServings(((TileEntityDragonLegFeast) tileEntity).getServings());
        }
        if (servings == MAX_SERVINGS) {
            if (state.getValue(PART) == Part.HEAD) {
                drops.add(new ItemStack(Item.getItemFromBlock(this)));
            }
        } else {
            drops.add(new ItemStack(Items.DYE, 3, 15));
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D);
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos)
    {
        return getServings(world, getHeadPos(pos, state));
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[]{FACING, SERVINGS, PART});
    }

    public enum Part implements IStringSerializable
    {
        HEAD("head"),
        FOOT("foot");

        private final String name;

        Part(String name)
        {
            this.name = name;
        }

        @Override
        public String getName()
        {
            return this.name;
        }
    }
}
