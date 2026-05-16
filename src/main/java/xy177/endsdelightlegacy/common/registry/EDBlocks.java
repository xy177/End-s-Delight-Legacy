package xy177.endsdelightlegacy.common.registry;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.registries.IForgeRegistry;
import xy177.endsdelightlegacy.EndsDelightLegacy;
import xy177.endsdelightlegacy.common.block.BlockChorusFruitCrate;
import xy177.endsdelightlegacy.common.block.BlockChorusFruitPie;
import xy177.endsdelightlegacy.common.block.BlockChorusSucculent;
import xy177.endsdelightlegacy.common.block.BlockEndStove;
import xy177.endsdelightlegacy.common.block.BlockDragonLegWithSauce;
import xy177.endsdelightlegacy.common.block.BlockEndFeast;
import xy177.endsdelightlegacy.common.item.ItemBlockDragonLegWithSauce;
import xy177.endsdelightlegacy.common.item.ItemBlockFood;

public final class EDBlocks
{
    private static final List<Block> BLOCKS = new ArrayList<>();
    private static final List<Item> BLOCK_ITEMS = new ArrayList<>();
    private static final AxisAlignedBB DRAGON_EGG_FEAST_SHAPE = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.6875D, 0.9375D);
    private static final AxisAlignedBB GRILLED_SHULKER_SHAPE = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.375D, 0.875D);
    private static final AxisAlignedBB GRILLED_SHULKER_LEFTOVER_SHAPE = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.1875D, 0.875D);

    public static final Block END_STOVE = registerBlock("end_stove", new BlockEndStove());
    public static final Block CHORUS_FRUIT_CRATE = registerBlock("chorus_fruit_crate", new BlockChorusFruitCrate());
    public static final Block CHORUS_SUCCULENT = registerBlock("chorus_succulent", new BlockChorusSucculent(),
        block -> new ItemBlockFood(block, 2, 0.8F, false));
    public static final Block CHORUS_FRUIT_PIE = registerBlock("chorus_fruit_pie", new BlockChorusFruitPie());
    public static final Block STEAMED_DRAGON_EGG_BLOCK = registerBlock("steamed_dragon_egg_block",
        new BlockEndFeast("steamed_dragon_egg", new net.minecraft.item.ItemStack(EDItems.HALF_DRAGON_EGG_SHELL), DRAGON_EGG_FEAST_SHAPE, DRAGON_EGG_FEAST_SHAPE),
        EDBlocks::singleStackItemBlock);
    public static final Block DRAGON_MEAT_STEW_BLOCK = registerBlock("dragon_meat_stew_block",
        new BlockEndFeast("dragon_meat_stew", new net.minecraft.item.ItemStack(EDItems.HALF_DRAGON_EGG_SHELL), DRAGON_EGG_FEAST_SHAPE, DRAGON_EGG_FEAST_SHAPE),
        EDBlocks::singleStackItemBlock);
    public static final Block GRILLED_SHULKER_BLOCK = registerBlock("grilled_shulker_block",
        new BlockEndFeast("grilled_shulker", new net.minecraft.item.ItemStack(Items.SHULKER_SHELL), GRILLED_SHULKER_SHAPE, GRILLED_SHULKER_LEFTOVER_SHAPE),
        EDBlocks::singleStackItemBlock);
    public static final Block DRAGON_LEG_WITH_SAUCE_BLOCK = registerBlock("dragon_leg_with_sauce_block",
        new BlockDragonLegWithSauce(), ItemBlockDragonLegWithSauce::new);

    private EDBlocks()
    {
    }

    public static void register(IForgeRegistry<Block> registry)
    {
        for (Block block : BLOCKS) {
            registry.register(block);
        }
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry)
    {
        for (Item item : BLOCK_ITEMS) {
            registry.register(item);
        }
    }

    private static Block registerBlock(String name, Block block)
    {
        return registerBlock(name, block, ItemBlock::new);
    }

    private static Block registerBlock(String name, Block block, java.util.function.Function<Block, Item> itemFactory)
    {
        block.setRegistryName(new ResourceLocation(EndsDelightLegacy.MODID, name));
        block.setUnlocalizedName(EndsDelightLegacy.MODID + "." + name);
        block.setCreativeTab(EndsDelightLegacy.CREATIVE_TAB);
        BLOCKS.add(block);

        Item item = itemFactory.apply(block);
        item.setRegistryName(block.getRegistryName());
        item.setUnlocalizedName(block.getUnlocalizedName());
        item.setCreativeTab(EndsDelightLegacy.CREATIVE_TAB);
        BLOCK_ITEMS.add(item);
        return block;
    }

    private static Item singleStackItemBlock(Block block)
    {
        ItemBlock item = new ItemBlock(block);
        item.setMaxStackSize(1);
        return item;
    }
}
