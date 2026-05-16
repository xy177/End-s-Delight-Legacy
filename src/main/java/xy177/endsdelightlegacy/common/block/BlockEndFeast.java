package xy177.endsdelightlegacy.common.block;

import com.wdcftgg.farmersdelightlegacy.api.food.AddonPlaceableFoodBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import com.wdcftgg.farmersdelightlegacy.common.tile.TileEntityFeast;
import xy177.endsdelightlegacy.EndsDelightLegacy;

public class BlockEndFeast extends AddonPlaceableFoodBlock
{
    private final ResourceLocation servingItemId;
    private final ItemStack partialDrop;
    private final AxisAlignedBB fullShape;
    private final AxisAlignedBB leftoverShape;

    public BlockEndFeast(String servingItemPath, ItemStack partialDrop, AxisAlignedBB fullShape, AxisAlignedBB leftoverShape)
    {
        super(4, servingItemPath, "minecraft:bowl", true);
        this.servingItemId = new ResourceLocation(EndsDelightLegacy.MODID, servingItemPath);
        this.partialDrop = partialDrop == null ? ItemStack.EMPTY : partialDrop.copy();
        this.fullShape = fullShape;
        this.leftoverShape = leftoverShape == null ? fullShape : leftoverShape;
    }

    @Override
    protected ItemStack getServingStackForServings(int servings)
    {
        Item servingItem = ForgeRegistries.ITEMS.getValue(this.servingItemId);
        return servingItem == null || servings <= 0 ? ItemStack.EMPTY : new ItemStack(servingItem);
    }

    @Override
    protected void appendPartialServingsDrops(NonNullList<ItemStack> drops)
    {
        if (!this.partialDrop.isEmpty()) {
            drops.add(this.partialDrop.copy());
        }
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        int servings = clampServings(state.getValue(getServingsProperty()));
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof TileEntityFeast) {
            servings = clampServings(((TileEntityFeast) tileEntity).getServings());
        }
        if (servings == getMaxServings()) {
            Item selfItem = Item.getItemFromBlock(this);
            if (selfItem != Items.AIR) {
                drops.add(new ItemStack(selfItem));
            }
            return;
        }
        appendPartialServingsDrops(drops);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return state.getValue(getServingsProperty()) == 0 ? this.leftoverShape : this.fullShape;
    }
}
