package xy177.endsdelightlegacy.common.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import xy177.endsdelightlegacy.common.block.BlockDragonLegWithSauce;

public class TileEntityDragonLegFeast extends TileEntity
{
    private int servings = BlockDragonLegWithSauce.MAX_SERVINGS;

    public int getServings()
    {
        return this.servings;
    }

    public void setServings(int servings)
    {
        this.servings = BlockDragonLegWithSauce.clampServings(servings);
        markDirty();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("Servings", this.servings);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.servings = compound.hasKey("Servings", 3)
            ? BlockDragonLegWithSauce.clampServings(compound.getInteger("Servings"))
            : BlockDragonLegWithSauce.MAX_SERVINGS;
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(this.pos, 0, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag)
    {
        readFromNBT(tag);
    }

    @Override
    public void markDirty()
    {
        super.markDirty();
        if (this.world != null && this.pos != null) {
            IBlockState state = this.world.getBlockState(this.pos);
            this.world.notifyBlockUpdate(this.pos, state, state, 3);
        }
    }

    @Override
    public boolean shouldRefresh(net.minecraft.world.World world, net.minecraft.util.math.BlockPos pos,
                                 IBlockState oldState, IBlockState newState)
    {
        return oldState.getBlock() != newState.getBlock();
    }
}
