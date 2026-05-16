package xy177.endsdelightlegacy.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockChorusFruitCrate extends Block
{
    public BlockChorusFruitCrate()
    {
        super(Material.WOOD);
        this.setHardness(2.0F);
        this.setResistance(3.0F);
        this.setSoundType(SoundType.WOOD);
        this.setHarvestLevel("axe", 0);
    }
}
