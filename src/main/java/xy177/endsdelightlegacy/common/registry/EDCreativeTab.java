package xy177.endsdelightlegacy.common.registry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import xy177.endsdelightlegacy.EndsDelightLegacy;

public final class EDCreativeTab
{
    public static final CreativeTabs TAB = new CreativeTabs(EndsDelightLegacy.MODID) {
        @Override
        public ItemStack getTabIconItem()
        {
            Item iconItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(EndsDelightLegacy.MODID, "bubble_tea"));
            if (iconItem != null) {
                return new ItemStack(iconItem);
            }
            return ItemStack.EMPTY;
        }

        @Override
        public void displayAllRelevantItems(NonNullList<ItemStack> items)
        {
            add(items, "end_stove");
            add(items, "chorus_fruit_crate");
            add(items, "end_stone_knife");
            add(items, "purpur_knife");
            add(items, "dragon_egg_shell_knife");
            add(items, "dragon_tooth_knife");
            add(items, "end_stone_machete");
            add(items, "purpur_machete");
            add(items, "dragon_egg_shell_machete");
            add(items, "dragon_tooth_machete");
            add(items, "ender_pearl_grain");
            add(items, "chorus_fruit_grain");
            add(items, "chorus_succulent");
            add(items, "dried_chorus_flower");
            add(items, "dragon_tooth");
            add(items, "non_hatchable_dragon_egg");
            add(items, "half_dragon_egg_shell");
            add(items, "liquid_dragon_egg");
            add(items, "fried_dragon_egg");
            add(items, "shulker_meat");
            add(items, "shulker_meat_slice");
            add(items, "roasted_shulker_meat");
            add(items, "roasted_shulker_meat_slice");
            add(items, "dragon_leg");
            add(items, "smoked_dragon_leg");
            add(items, "raw_dragon_meat");
            add(items, "roasted_dragon_meat");
            add(items, "raw_dragon_meat_cuts");
            add(items, "roasted_dragon_meat_cuts");
            add(items, "raw_ender_mite_meat");
            add(items, "dried_endermite_meat");
            add(items, "enderman_gristle");
            add(items, "chorus_sauce");
            add(items, "shulker_omelette_mixture");
            add(items, "shulker_omelette");
            add(items, "raw_ender_sausage");
            add(items, "ender_sausage");
            add(items, "ender_bamboo_rice");
            add(items, "stuffed_rice_cake");
            add(items, "chorus_flower_pie");
            add(items, "chorus_cookie");
            add(items, "chorus_fruit_popsicle");
            add(items, "chorus_fruit_milk_tea");
            add(items, "bubble_tea");
            add(items, "chorus_fruit_wine");
            add(items, "dragon_breath_soda");
            add(items, "chorus_flower_tea");
            add(items, "chorus_fruit_pie");
            add(items, "chorus_fruit_pie_slice");
            add(items, "ender_congee");
            add(items, "dragon_breath_and_chorus_soup");
            add(items, "shulker_soup");
            add(items, "ender_noodle");
            add(items, "enderman_gristle_stew");
            add(items, "stir_fried_shulker_meat");
            add(items, "roasted_dragon_steak");
            add(items, "end_mixed_salad");
            add(items, "assorted_salad");
            add(items, "end_barbecue_stick");
            add(items, "dragon_leg_with_sauce_block");
            add(items, "dragon_leg_with_sauce");
            add(items, "steamed_dragon_egg_block");
            add(items, "steamed_dragon_egg");
            add(items, "dragon_meat_stew_block");
            add(items, "dragon_meat_stew");
            add(items, "grilled_shulker_block");
            add(items, "grilled_shulker");
        }
    };

    private EDCreativeTab()
    {
    }

    private static void add(NonNullList<ItemStack> items, String path)
    {
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(EndsDelightLegacy.MODID, path));
        if (item != null) {
            items.add(new ItemStack(item));
        }
    }
}
